package org.qiuyeqaq.gtl_extend.common.multiblock.electric;

import static org.qiuyeqaq.gtl_extend.common.materials.GTL_Extend_Materials.ETERNALBLUEDREAM;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.logic.OCParams;
import com.gregtechceu.gtceu.api.recipe.logic.OCResult;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import org.qiuyeqaq.gtl_extend.config.GTLExtendConfigHolder;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import javax.annotation.ParametersAreNonnullByDefault;

import com.hepdd.gtmthings.api.misc.WirelessEnergyManager;
import com.hepdd.gtmthings.utils.TeamUtil;
import org.gtlcore.gtlcore.api.machine.multiblock.NoEnergyMultiblockMachine;
import org.gtlcore.gtlcore.utils.MachineIO;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlackHoleMatterDecompressor extends NoEnergyMultiblockMachine {

    // 最大安全 EU 值（根据实际需求调整）
    private static final long MAX_SAFE_EUT = Long.MAX_VALUE - 1;

    // 添加配置依赖判断方法
    private boolean isInfinityDreamEnabled() {
        return GTLExtendConfigHolder.INSTANCE != null && GTLExtendConfigHolder.INSTANCE.enableInfinityDreamAndDreamHostCrafting && ETERNALBLUEDREAM != null; // 确保材料存在
    }

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            BlackHoleMatterDecompressor.class, NoEnergyMultiblockMachine.MANAGED_FIELD_HOLDER);

    // 配置常量
    private static final long BASE_STARTUP_EUT = 52_776_558_108_672_00L;
    private static final int BASE_PARALLEL = 100;
    private static final int PARALLEL_PER_BUCKET = 1000;
    private static final int OVERCLOCK_THRESHOLD = 10000;
    private static final double POWER_MULTIPLIER_THRESHOLD = 2.0;
    // 新增实例变量，存储最终允许的EUt
    private long finalEUt;

    /**
     * 仅处理输入 EU 的电网限制，不涉及超频计算
     * 
     * @param inputEUt 原始输入 EU 值（通常来自配方）
     * @return 经过电网限制后的最终 EU 值
     */
    public long applyWirelessEULimit(long inputEUt) {
        long finalEUt = inputEUt;

        // 无线电网1%电量限制逻辑
        if (this.userId != null) {
            BigInteger totalEU = WirelessEnergyManager.getUserEU(this.userId);
            if (totalEU.compareTo(BigInteger.ZERO) > 0) {
                // 计算允许的最大 EU（总能量的1%，整数除法截断）
                BigInteger maxAllowedEU = totalEU.divide(BigInteger.valueOf(100));

                // 处理溢出：当 maxAllowedEU 超过 Long.MAX_VALUE 时取最大值
                long maxAllowedEULong;
                try {
                    maxAllowedEULong = maxAllowedEU.longValueExact();
                } catch (ArithmeticException e) {
                    maxAllowedEULong = Long.MAX_VALUE;
                }

                // 限制输入 EU 不超过电网允许值
                finalEUt = Math.min(inputEUt, maxAllowedEULong);
            }
        }

        return finalEUt;
    }

    @Persisted
    private int circuitConfig = 0; // 当前电路配置 (1-4)
    @Persisted
    private long eternalbluedream = 0; // 液态永恒蓝梦储量(mB)
    @Persisted
    private UUID userId;

    protected ConditionalSubscriptionHandler advancedSubs;

    public BlackHoleMatterDecompressor(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.advancedSubs = new ConditionalSubscriptionHandler(this, this::advancedUpdate, this::isFormed);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    public long getRecipeEUt() {
        // 返回自定义启动耗能（若需动态计算可在此扩展）
        return this.finalEUt;
    }

    // 每20tick更新资源
    protected void advancedUpdate() {
        if (getOffsetTimer() % 20 == 0) {
            // 输入永恒蓝梦
            if (isInfinityDreamEnabled() && ETERNALBLUEDREAM != null) {
                if (MachineIO.inputFluid(this, ETERNALBLUEDREAM.getFluid(100000000))) {
                    eternalbluedream += 100000000;
                }
            }

            // 检测电路配置（优先级4 > 3 > 2 > 1）
            circuitConfig = 0;
            for (int i = 4; i >= 1; i--) {
                if (MachineIO.notConsumableCircuit(this, i)) {
                    circuitConfig = i;
                    break;
                }
            }
        }
    }

    // 获取当前并行数
    private int calculateParallel() {
        int parallel = BASE_PARALLEL;

        if (isInfinityDreamEnabled()) {
            // 永恒蓝梦模式逻辑
            long buckets = (long) (eternalbluedream / 1000);
            parallel += (int) Math.min(buckets * PARALLEL_PER_BUCKET, Integer.MAX_VALUE - parallel);

            // 超频处理
            boolean overclock = parallel > OVERCLOCK_THRESHOLD;
            if (overclock) {
                parallel = OVERCLOCK_THRESHOLD * 2;
            }
        } else {
            // 功率超频模式逻辑
            double powerMultiplier = calculateOverclockTimes();
            parallel *= (long) Math.floor(powerMultiplier);
        }

        // 公共处理
        if (calculateOverclockTimes() >= POWER_MULTIPLIER_THRESHOLD) {
            parallel = (int) Math.min(parallel * 2L, Integer.MAX_VALUE);
        }

        return parallel;
    }

    /**
     * 计算超频次数（倍数），公式：超频次数 = limitedEUt / baseEUt
     * 
     * @return 超频倍数（若无法计算则返回 1.0）
     */
    private long calculateOverclockTimes() {
        // 1. 获取原始输入 EU（例如来自配方）
        long baseEUt = BASE_STARTUP_EUT;
        if (baseEUt <= 0) {
            return 1; // 基础 EU 无效时默认不超频
        }

        // 2. 应用电网限制后的 EU 值
        long limitedEUt = applyWirelessEULimit(baseEUt);

        // 3. 计算整数超频次数（向下取整）
        long overclockTimes = limitedEUt / baseEUt;

        // 应用电路配置的功率倍率
        double circuitMultiplier = getCircuitPowerMultiplier();
        overclockTimes = (long) (overclockTimes * circuitMultiplier);

        // 4. 限制最小值为 1（不允许降频或零超频）
        return Math.max(overclockTimes, 1L);
    }

    // 配方修改器
    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, @NotNull GTRecipe recipe,
                                          @NotNull OCParams params, @NotNull OCResult result) {
        return processRecipe(machine, recipe, true);
    }

    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, GTRecipe recipe) {
        return processRecipe(machine, recipe, false);
    }

    @Nullable
    private static GTRecipe processRecipe(MetaMachine machine, GTRecipe recipe, boolean withParallel) {
        if (!(machine instanceof BlackHoleMatterDecompressor instance)) return null;

        // ========== 1. 基础参数计算 ==========
        long baseEUt = withParallel ? instance.applyWirelessEULimit(BASE_STARTUP_EUT) : BASE_STARTUP_EUT;
        long overclockTimes = instance.calculateOverclockTimes();

        // 溢出检测
        if (baseEUt > MAX_SAFE_EUT) {
            GTCEu.LOGGER.error("[Overload] Base EU exceeds safety limit: {} EU/t", baseEUt);
            return null;
        }

        // ========== 2. 最终参数计算 ==========
        instance.finalEUt = baseEUt * overclockTimes;
        int finalParallel = instance.calculateParallel();

        // 直接生成单个配方，并行数限制在Integer.MAX_VALUE
        GTRecipe modifiedRecipe = buildSingleRecipe(instance, recipe, instance.finalEUt,
                calculateDuration(instance, recipe, withParallel), finalParallel);

        // 立即扣除能量
        if (modifiedRecipe != null && instance.userId != null) {
            BigInteger totalEnergy = BigInteger.valueOf(instance.finalEUt)
                    .multiply(BigInteger.valueOf(modifiedRecipe.duration));
            boolean success = WirelessEnergyManager.addEUToGlobalEnergyMap(
                    instance.userId,
                    totalEnergy.negate(),
                    machine);
            if (!success) {
                GTCEu.LOGGER.warn("[Energy Deduct Failed] Insufficient energy");
                return null;
            }
        }

        return modifiedRecipe;
    }

    // ========== 工具方法 ==========
    private static int calculateDuration(BlackHoleMatterDecompressor machine, GTRecipe recipe, boolean withParallel) {
        // 持续时间计算逻辑
        double durationMultiplier = switch (machine.circuitConfig) {
            case 2 -> 0.5;
            case 3 -> 0.25;
            case 4 -> 0.125;
            default -> 1.0;
        };

        // 溢出保护
        if (!machine.isInfinityDreamEnabled()) durationMultiplier *= 0.8;

        long rawDuration = withParallel ?
                (long) (recipe.duration * durationMultiplier) :
                recipe.duration / machine.calculateOverclockTimes();

        return (int) Math.min(rawDuration, Integer.MAX_VALUE);
    }

    @Nullable
    private static GTRecipe buildSingleRecipe(BlackHoleMatterDecompressor machine, GTRecipe original,
                                              long eut, int duration, int parallel) {
        try {
            GTRecipe parallelRecipe = GTRecipeModifiers.accurateParallel(machine, original, parallel, false).getFirst();
            return new GTRecipeBuilder(parallelRecipe, original.recipeType)
                    .input(EURecipeCapability.CAP, eut)
                    .duration(duration)
                    .buildRawRecipe();
        } catch (Exception e) {
            GTCEu.LOGGER.error("[Build Failed] Error: {}", e.getMessage());
            return null;
        }
    }

    private static GTRecipe mergeBatchRecipes(List<GTRecipe> batches, long totalEUt) {
        // 4.1 使用第一个配方作为模板
        int totalDuration = batches.stream().mapToInt(recipe -> recipe.duration).sum();
        GTRecipe template = batches.get(0);
        GTRecipeBuilder builder = new GTRecipeBuilder(template, template.recipeType);

        // 4.3 设置总能源需求
        return builder
                .input(EURecipeCapability.CAP, totalEUt)
                .duration(template.duration) // 持续时间取首个分块值
                .buildRawRecipe();
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
            textList.add(Component.translatable("gtl_extend_machine_mode",
                    isInfinityDreamEnabled() ? "蓝梦模式" : "功率模式"));

            if (isInfinityDreamEnabled()) {
                textList.add(Component.literal("永恒蓝梦: " +
                        FormattingUtil.formatNumbers(eternalbluedream) + " mB"));
                textList.add(Component.literal("基础并行: " + BASE_PARALLEL));
            } else {
                double actualMultiplier = calculateOverclockTimes() / getCircuitPowerMultiplier(); // 显示纯电网倍率
                String powerMultiplierDisplay = actualMultiplier >= Double.MAX_VALUE / 1e3 ? "∞" : FormattingUtil.formatNumbers(actualMultiplier);
                textList.add(Component.literal("当前功率倍率: " + powerMultiplierDisplay));
                // 显示无线电网允许的最大功率（仅在非蓝梦模式下显示）
                if (userId != null) {
                    BigInteger totalEU = WirelessEnergyManager.getUserEU(userId);
                    if (totalEU.compareTo(BigInteger.ZERO) > 0) {
                        BigInteger maxAllowedEU = totalEU.divide(BigInteger.valueOf(100));
                        long maxAllowedEULong;
                        try {
                            maxAllowedEULong = maxAllowedEU.longValueExact();
                        } catch (ArithmeticException e) {
                            maxAllowedEULong = Long.MAX_VALUE;
                        }
                        textList.add(Component.literal("最大允许功率: " + FormattingUtil.formatNumbers(maxAllowedEU) + " EU/t"));
                    }
                }
            }
            // 用户无线电网信息（公共显示部分）
            if (userId != null) {
                textList.add(Component.translatable("gtmthings.machine.wireless_energy_monitor.tooltip.0",
                        TeamUtil.GetName(getLevel(), userId)));
                textList.add(Component.translatable("gtmthings.machine.wireless_energy_monitor.tooltip.1",
                        FormattingUtil.formatNumbers(WirelessEnergyManager.getUserEU(userId))));
            }
            // 公共信息
            textList.add(Component.literal("启动耗能：" + FormattingUtil.formatNumbers(getRecipeEUt()) + "EU"));
            textList.add(Component.literal("最终并行: " + calculateParallel()));
            textList.add(Component.translatable("gtl_extend_machine_circuit",
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

    // 新增方法：获取电路配置对应的功率倍率
    private double getCircuitPowerMultiplier() {
        return switch (this.circuitConfig) {
            case 2 -> 4.0;
            case 3 -> 16.0;
            case 4 -> 64.0;
            default -> 1.0;
        };
    }
}
