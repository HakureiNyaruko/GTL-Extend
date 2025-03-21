package org.qiuyeqaq.gtl_extend.api.registries.special;

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import org.gtlcore.gtlcore.api.machine.multiblock.NoEnergyMultiblockMachine;
import org.gtlcore.gtlcore.utils.MachineIO;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.logic.OCParams;
import com.gregtechceu.gtceu.api.recipe.logic.OCResult;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.qiuyeqaq.gtl_extend.config.GTLExtendConfigHolder;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.UUID;

import static org.qiuyeqaq.gtl_extend.common.materials.GTL_Extend_Materials.ETERNALBLUEDREAM;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AdvancedHarmonyMachine extends NoEnergyMultiblockMachine {

    // 最大安全 EU 值（根据实际需求调整）
    private static final long MAX_SAFE_EUT = Long.MAX_VALUE / 1024;

    @Persisted
    private long currentEUt;
    private long getRecipeEUt() {
        return currentEUt;
    }

    // 添加配置依赖判断方法
    private boolean isInfinityDreamEnabled() {
        return GTLExtendConfigHolder.INSTANCE != null &&
                GTLExtendConfigHolder.INSTANCE.enableInfinityDreamAndDreamHostCrafting;
    }

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            AdvancedHarmonyMachine.class, NoEnergyMultiblockMachine.MANAGED_FIELD_HOLDER);

    // 配置常量
    private static final int BASE_PARALLEL = 100;
    private static final int PARALLEL_PER_BUCKET = 1000;
    private static final int OVERCLOCK_THRESHOLD = 10000;
    private static final double POWER_MULTIPLIER_THRESHOLD = 2.0;

    @Persisted
    private long circuitConfig = 1; // 当前电路配置 (1-4)
    @Persisted
    private long eternalbluedream = 0; // 液态永恒蓝梦储量(mB)
    @Persisted
    private UUID userId;

    protected ConditionalSubscriptionHandler advancedSubs;

    public AdvancedHarmonyMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.advancedSubs = new ConditionalSubscriptionHandler(this, this::advancedUpdate, this::isFormed);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    // 每20tick更新资源
    protected void advancedUpdate() {
        if (getOffsetTimer() % 20 == 0) {
            // 输入永恒蓝梦
            if (MachineIO.inputFluid(this, ETERNALBLUEDREAM.getFluid(100000000))) {
                eternalbluedream += 100000000;
            }

            // 检测电路配置（优先级4 > 3 > 2 > 1）
            circuitConfig = 1;
            for (int i = 4; i >= 1; i--) {
                if (MachineIO.notConsumableCircuit(this, i)) {
                    circuitConfig = i;
                    break;
                }
            }
        }
    }

    // 获取当前并行数
    private long calculateParallel() {
        long parallel = BASE_PARALLEL;

        if (isInfinityDreamEnabled()) {
            // 永恒蓝梦模式逻辑
            long buckets = (long) (eternalbluedream / 1000);
            parallel += buckets * PARALLEL_PER_BUCKET;

            // 超频处理
            boolean overclock = parallel > OVERCLOCK_THRESHOLD;
            if (overclock) {
                parallel = OVERCLOCK_THRESHOLD * 2;
            }
        } else {
            // 功率超频模式逻辑
            double powerMultiplier = getPowerMultiplier();
            parallel *= (long) Math.floor(powerMultiplier);
        }

        // 公共处理
        if (getPowerMultiplier() >= POWER_MULTIPLIER_THRESHOLD) {
            parallel *= 2;
        }

        return Math.min(parallel, Long.MAX_VALUE - 1);
    }
    // 添加 getMaxInputVoltage 方法
    private long getMaxInputVoltage() {
        return Long.MAX_VALUE; // 根据实际逻辑调整
    }
    // 获取功率倍数（机器电压/配方需求）
    private double getPowerMultiplier() {
        long machineVoltage = getMaxInputVoltage();
        long recipeVoltage = getRecipeEUt() * 20; // 假设配方EU/t
        return machineVoltage / (double) recipeVoltage;
    }

    // 配方修改器
    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, @NotNull GTRecipe recipe,
                                          @NotNull OCParams params, @NotNull OCResult result) {
        if (!(machine instanceof AdvancedHarmonyMachine harmonyMachine)) return null;

        // 获取原始EUt
        long baseEUt = harmonyMachine.getRecipeEUt();

        // 电路配置计算
        double durationMultiplier = switch ((int) harmonyMachine.circuitConfig) {
            case 2 -> 0.5;
            case 3 -> 0.25;
            case 4 -> 0.125;
            default -> 1.0;
        };

        double powerMultiplier = switch ((int) harmonyMachine.circuitConfig) {
            case 2 -> harmonyMachine.isInfinityDreamEnabled() ? 4.0 : 2.0;
            case 3 -> harmonyMachine.isInfinityDreamEnabled() ? 16.0 : 4.0;
            case 4 -> harmonyMachine.isInfinityDreamEnabled() ? 64.0 : 8.0;
            default -> 1.0;
        };

        // 计算修改后的EUt和耗时
        long modifiedEUt = (long) (baseEUt * powerMultiplier);
        if (!harmonyMachine.isInfinityDreamEnabled()) {
            durationMultiplier *= 0.8; // 功率模式额外加速
        }
        // 获取总并行数
        long totalParallel = harmonyMachine.calculateParallel();

        // 分块处理（每批最大 Integer.MAX_VALUE）
        while (totalParallel > 0) {
            int batchSize = (int) Math.min(totalParallel, Integer.MAX_VALUE);
            GTRecipe batchResult = GTRecipeModifiers.accurateParallel(machine, recipe, batchSize, false).getFirst();
            if (batchResult == null) break; // 处理失败时终止
        }
        // 使用 GTRecipeBuilder 构建新配方
        return new GTRecipeBuilder(recipe, recipe.recipeType)
                .input(EURecipeCapability.CAP, modifiedEUt)
                .duration((int) (recipe.duration * durationMultiplier))
                .buildRawRecipe(); // 使用 buildRawRecipe() 返回 GTRecipe 对象

    }
    private int getOverclockLevel() {
        // 示例逻辑：根据电路配置返回超频等级
        return (int) circuitConfig - 1; // 假设电路配置 1-4 对应等级 0-3
    }
    // 处理配方修改
    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, GTRecipe recipe) {
        AdvancedHarmonyMachine instance = (AdvancedHarmonyMachine) machine;

        long baseEUt = instance.getRecipeEUt();
        int ocLevel = instance.getOverclockLevel();

        // 溢出检测
        if (baseEUt > MAX_SAFE_EUT) {
            GTCEu.LOGGER.error("Base EU too high: {}", baseEUt);
            return null;
        }

        // 计算最终 EU
        long finalEUt = baseEUt * (1L << ocLevel);

        // 使用 GTRecipeBuilder 构建新配方
        return new GTRecipeBuilder(recipe, recipe.recipeType)
                .input(EURecipeCapability.CAP, finalEUt) // 设置能源输入
                .duration(recipe.duration / (1 << ocLevel))
                .buildRawRecipe(); // 返回 GTRecipe 实例
    }
    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        advancedSubs.initialize(getLevel());
    }

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        if (this.userId == null || !this.userId.equals(player.getUUID())) {
            this.userId = player.getUUID();
        }
        return true;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if (isFormed()) {
            // 模式状态显示
            textList.add(Component.translatable("gtl_extend.machine.mode",
                    isInfinityDreamEnabled() ? "蓝梦模式" : "功率模式"));

            if (isInfinityDreamEnabled()) {
                textList.add(Component.literal("永恒蓝梦: " +
                        FormattingUtil.formatNumbers(eternalbluedream) + " mB"));
                textList.add(Component.literal("基础并行: " + BASE_PARALLEL));
            } else {
                textList.add(Component.literal("当前功率倍率: " +
                        FormattingUtil.formatNumbers(getPowerMultiplier())));
            }

            // 公共信息
            textList.add(Component.literal("最终并行: " + calculateParallel()));
            textList.add(Component.translatable("gtl_extend.machine.circuit",
                    circuitConfig, getPowerMultiplier((int) circuitConfig)));
        }
    }

    private String getDurationMultiplier(int circuit) {
        return switch (circuit) {
            case 2 -> "0.5";
            case 3 -> "0.25";
            case 4 -> "0.125";
            default -> "1.0";
        };
    }

    private String getPowerMultiplier(int circuit) {
        return switch (circuit) {
            case 2 -> "4.0";
            case 3 -> "16.0";
            case 4 -> "64.0";
            default -> "1.0";
        };
    }
}