package org.qiuyeqaq.gtl_extend.common.multiblock;

import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.common.data.GCyMBlocks.CASING_HIGH_TEMPERATURE_SMELTING;
import static com.gregtechceu.gtceu.common.data.GTBlocks.HIGH_POWER_CASING;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GCyMRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import net.minecraft.network.chat.Component;

import org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration;
import org.qiuyeqaq.gtl_extend.client.renderer.machine.BlackHoleMatterDecompressor_Render;
import org.qiuyeqaq.gtl_extend.common.data.GTL_Extend_CreativeModeTabs;
import org.qiuyeqaq.gtl_extend.common.data.GetRegistries;
import org.qiuyeqaq.gtl_extend.common.machines.machines.GTL_Extend_SmallMachines;
import org.qiuyeqaq.gtl_extend.common.machines.recipes.GTL_Extend_RecipeTypes;
import org.qiuyeqaq.gtl_extend.common.multiblock.electric.BlackHoleMatterDecompressor;
import org.qiuyeqaq.gtl_extend.common.multiblock.structure.BlackHoleMatterDecompressor.BlackHoleMatterDecompressor_MultiBlockStructure;
import org.qiuyeqaq.gtl_extend.common.multiblock.structure.MultiBlockStructure;

import org.gtlcore.gtlcore.common.data.GTLRecipeTypes;
import org.gtlcore.gtlcore.utils.TextUtil;

public class MultiBlockMachine {

    public static final MultiblockMachineDefinition SUPERFLUID_GENERAL_ENERGY_FURNACE;
    public static final MultiblockMachineDefinition BLACK_HOLE_MATTER_DECOMPRESSOR;

    /*
     * public static final BiConsumer<ItemStack, List<Component>> GTL_EX_ADD = (itemStack, components) -> components
     * .add(Component.translatable("Gtl.registry.add")
     * .withStyle(style -> style.withColor(TooltipHelper.RAINBOW_SLOW.getCurrent())));
     */

    static {
        GTLEXRegistration.REGISTRATE.creativeModeTab(() -> GTL_Extend_CreativeModeTabs.MACHINES_ITEM);
    }

    public MultiBlockMachine() {}

    public static void init() {
        GTL_Extend_SmallMachines.init();
    }

    static {
        SUPERFLUID_GENERAL_ENERGY_FURNACE = GTLEXRegistration.REGISTRATE.multiblock("superfluid_general_energy_furnace", WorkableElectricMultiblockMachine::new)
                .rotationState(RotationState.NON_Y_AXIS)
                .appearanceBlock(CASING_HIGH_TEMPERATURE_SMELTING)
                .recipeType(GTRecipeTypes.BLAST_RECIPES)
                .recipeType(GTRecipeTypes.ALLOY_SMELTER_RECIPES)
                .recipeType(GCyMRecipeTypes.ALLOY_BLAST_RECIPES)
                .tooltips(Component.literal(TextUtil.full_color("由GTL_Extend添加")))
                .appearanceBlock(GTBlocks.HIGH_TEMPERATURE_HAZARD_SIGN_BLOCK)
                .pattern(definition -> MultiBlockStructure.GENERAL_ENERGY_FURNACE
                        .where('~', Predicates.controller(blocks(definition.getBlock())))
                        .where(' ', Predicates.any())
                        .where("c", blocks(GetRegistries.getBlock("gtceu:cleanroom_glass")))
                        .where("a", Predicates.blocks(GetRegistries.getBlock("gtceu:high_temperature_smelting_casing"))
                                .setMinGlobalLimited(10)
                                .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.INPUT_LASER).setMaxGlobalLimited(1))
                                .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2)))
                        .where("e", blocks(GetRegistries.getBlock("gtceu:heat_vent")))
                        .where("f", Predicates.heatingCoils())
                        .build())
                .additionalDisplay(GTL_Extend_MultiBlockMachineModifier.MAX_PARALLEL)
                .workableCasingRenderer(GTCEu.id("block/casings/gcym/high_temperature_smelting_casing"),
                        GTCEu.id("block/multiblock/fusion_reactor"), false)
                .register();
        BLACK_HOLE_MATTER_DECOMPRESSOR = GTLEXRegistration.REGISTRATE.multiblock("black_hole_matter_decompressor", BlackHoleMatterDecompressor::new)
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(GTLRecipeTypes.COSMOS_SIMULATION_RECIPES)
                .recipeType(GTL_Extend_RecipeTypes.HORIZON_MATTER_DECOMPRESSION_RECIPES)
                .recipeModifier(BlackHoleMatterDecompressor::recipeModifier)
                .tooltips(Component.literal(TextUtil.full_color("由GTL_Extend添加")))
                .appearanceBlock(GTBlocks.HIGH_POWER_CASING)
                .pattern(definition -> BlackHoleMatterDecompressor_MultiBlockStructure.BLACK_HOLE_DECOMPRESSION
                        .where('~', Predicates.controller(blocks(definition.getBlock())))
                        .where(" ", Predicates.any())
                        .where('A', Predicates.blocks(GTBlocks.HIGH_POWER_CASING.get())
                                .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1)))
                        .where('B', blocks(GetRegistries.getBlock("kubejs:dyson_control_casing")))
                        .where('C', blocks(GetRegistries.getBlock("gtlcore:graviton_field_constraint_casing")))
                        .where('D', blocks(GetRegistries.getBlock("kubejs:containment_field_generator")))
                        .where('E', blocks(GetRegistries.getBlock("gtlcore:hyper_core")))
                        .where('F', blocks(GetRegistries.getBlock("gtlcore:naquadah_alloy_casing")))
                        .where('G', blocks(GetRegistries.getBlock("gtlcore:rhenium_reinforced_energy_glass")))
                        .where('H', blocks(GetRegistries.getBlock("kubejs:hollow_casing")))
                        .where('I', blocks(GetRegistries.getBlock("kubejs:dyson_receiver_casing")))
                        .where('J', blocks(GetRegistries.getBlock("gtlcore:degenerate_rhenium_constrained_casing")))
                        .where('K', blocks(GetRegistries.getBlock("gtlcore:hyper_mechanical_casing")))
                        .where('L', blocks(GetRegistries.getBlock("kubejs:restraint_device")))
                        .where('M', blocks(GetRegistries.getBlock("gtceu:fusion_glass")))
                        .where('N', blocks(GetRegistries.getBlock("kubejs:spacetime_compression_field_generator")))
                        .where('O', blocks(GetRegistries.getBlock("kubejs:annihilate_core")))
                        .where('P', blocks(GetRegistries.getBlock("kubejs:dimensional_stability_casing")))
                        .where('Q', blocks(GetRegistries.getBlock("gtlcore:infinity_glass")))
                        .build())
                .renderer(BlackHoleMatterDecompressor_Render::new)
                .hasTESR(true)
                .register();
    }
}
