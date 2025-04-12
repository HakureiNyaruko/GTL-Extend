package org.qiuyeqaq.gtl_extend.common.machines.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.client.renderer.machine.SimpleGeneratorMachineRenderer;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import net.minecraft.network.chat.Component;
import org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration;
import org.qiuyeqaq.gtl_extend.api.registries.MachinesRegister;
import org.qiuyeqaq.gtl_extend.common.data.GTL_Extend_CreativeModeTabs;
import org.qiuyeqaq.gtl_extend.common.machines.generator.DragonEggEnergyMachine;
import org.qiuyeqaq.gtl_extend.common.machines.generator.MagicEnergyMachine;
import org.qiuyeqaq.gtl_extend.common.machines.recipes.GTL_Extend_RecipeTypes;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static org.qiuyeqaq.gtl_extend.common.multiblock.MultiBlockMachine.GTL_EX_ADD;

public class GTL_Extend_SmallMachines {

    public static final MachineDefinition[] PRIMITIVE_MAGIC_ENERGY;
    public static final MachineDefinition[] DRAGON_EGG_ENERGY;

    static {
        GTLEXRegistration.REGISTRATE.creativeModeTab(() -> GTL_Extend_CreativeModeTabs.MACHINES_ITEM);
    }

    static {
        PRIMITIVE_MAGIC_ENERGY = MachinesRegister.registerTieredMachines(
                "primitive_magic_energy",
                MagicEnergyMachine::new,
                (tier, builder) -> builder
                        .langValue("%s Primitive Magic Energy %s".formatted(VLVH[tier], VLVT[tier]))
                        .rotationState(RotationState.NON_Y_AXIS)
                        .renderer(() -> new SimpleGeneratorMachineRenderer(tier,
                                GTCEu.id("block/generators/primitive_magic_energy")))
                        .tooltips(Component.translatable("gtceu.machine.primitive_magic_energy.tooltip.0"))
                        .tooltips(Component.translatable("gtceu.universal.tooltip.ampere_out", 64))
                        .tooltips(Component.translatable("gtceu.universal.tooltip.voltage_out",
                                FormattingUtil.formatNumbers(V[tier]), VNF[tier]))
                        .tooltips(Component.translatable("gtceu.universal.tooltip.energy_storage_capacity",
                                FormattingUtil.formatNumbers(V[tier] * 2048L)))
                        .tooltipBuilder(GTL_EX_ADD)
                        .recipeTypes(GTL_Extend_RecipeTypes.MAGIC_ENERGY_RECIPES)
                        .register(),
                MV, HV, EV, IV, LuV, ZPM);

        DRAGON_EGG_ENERGY = MachinesRegister.registerTieredMachines(
                "primitive_dragon_egg_energy",
                DragonEggEnergyMachine::new,
                (tier, builder) -> builder
                        .langValue("%s Primitive Dradon Egg Energy %s".formatted(VLVH[tier], VLVT[tier]))
                        .rotationState(RotationState.NON_Y_AXIS)
                        .renderer(() -> new SimpleGeneratorMachineRenderer(tier,
                                GTCEu.id("block/generators/primitive_magic_energy")))
                        .tooltips(Component.translatable("gtceu.machine.primitive_dradon_egg_energy.tooltip.0"))
                        .tooltips(Component.translatable("gtceu.universal.tooltip.ampere_out", 256))
                        .tooltips(Component.translatable("gtceu.universal.tooltip.voltage_out",
                                FormattingUtil.formatNumbers(VEX[tier]), VNF[tier]))
                        .tooltips(Component.translatable("gtceu.universal.tooltip.energy_storage_capacity",
                                FormattingUtil.formatNumbers(VEX[tier] * 16384)))
                        .tooltipBuilder(GTL_EX_ADD)
                        .recipeType(GTL_Extend_RecipeTypes.DRAGON_EGG_ENERGY_RECIPES)
                        .register(),
                UV, UHV, UEV, UIV);
    }

    public static void init() {}
}
