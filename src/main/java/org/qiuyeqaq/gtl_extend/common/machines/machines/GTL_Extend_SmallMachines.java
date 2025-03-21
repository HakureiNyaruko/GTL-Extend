package org.qiuyeqaq.gtl_extend.common.machines.machines;

import org.gtlcore.gtlcore.common.machine.generator.MagicEnergyMachine;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.client.renderer.machine.SimpleGeneratorMachineRenderer;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.network.chat.Component;

import org.qiuyeqaq.gtl_extend.api.registries.MachinesRegister;
import org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration;
import org.qiuyeqaq.gtl_extend.common.data.GTL_Extend_CreativeModeTabs;

import static org.qiuyeqaq.gtl_extend.common.machines.machines.GTL_Extend_Machines.GTL_EX_ADD;

public class GTL_Extend_SmallMachines {

    public static void init() {}

    static {
        GTLEXRegistration.REGISTRATE.creativeModeTab(() -> GTL_Extend_CreativeModeTabs.MACHINES_ITEM);
    }
}
