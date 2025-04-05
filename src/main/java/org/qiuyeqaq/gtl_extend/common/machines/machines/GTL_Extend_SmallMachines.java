package org.qiuyeqaq.gtl_extend.common.machines.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.client.renderer.machine.SimpleGeneratorMachineRenderer;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.network.chat.Component;

import org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration;
import org.qiuyeqaq.gtl_extend.api.registries.MachinesRegister;
import org.qiuyeqaq.gtl_extend.common.data.GTL_Extend_CreativeModeTabs;
import org.qiuyeqaq.gtl_extend.common.machines.generator.MagicEnergyMachine;

import static org.qiuyeqaq.gtl_extend.common.multiblock.MultiBlockMachine.GTL_EX_ADD;

public class GTL_Extend_SmallMachines {

    public static final MachineDefinition[] PRIMITIVE_MAGIC_ENERGY;

    static {
        GTLEXRegistration.REGISTRATE.creativeModeTab(() -> GTL_Extend_CreativeModeTabs.MACHINES_ITEM);
    }

    static {
        PRIMITIVE_MAGIC_ENERGY = MachinesRegister.registerTieredMachines(
                "primitive_magic_energy",
                MagicEnergyMachine::new,
                (tier, builder) -> builder
                        .langValue("%s Primitive Magic Energy %s".formatted(GTValues.VLVH[tier], GTValues.VLVT[tier]))
                        .rotationState(RotationState.NON_Y_AXIS)
                        .renderer(() -> new SimpleGeneratorMachineRenderer(tier,
                                GTCEu.id("block/generators/primitive_magic_energy")))
                        .tooltips(Component.translatable("gtceu.machine.primitive_magic_energy.tooltip.0"))
                        .tooltips(Component.translatable("gtceu.universal.tooltip.ampere_out", 64))
                        .tooltips(Component.translatable("gtceu.universal.tooltip.voltage_out",
                                FormattingUtil.formatNumbers(GTValues.V[tier]), GTValues.VNF[tier]))
                        .tooltips(Component.translatable("gtceu.universal.tooltip.energy_storage_capacity",
                                FormattingUtil.formatNumbers(GTValues.V[tier] * 2048L)))
                        .tooltipBuilder(GTL_EX_ADD)
                        .register(),
                GTValues.MV, GTValues.HV, GTValues.EV, GTValues.IV, GTValues.LuV, GTValues.ZPM);
    }

    public static void init() {}
}
