package org.qiuyeqaq.gtl_extend.common.multiblock;

import org.gtlcore.gtlcore.common.data.GTLRecipeTypes;
import org.gtlcore.gtlcore.utils.TextUtil;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.*;

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
import org.qiuyeqaq.gtl_extend.common.multiblock.structure.Platinum_basedProcessingHub.Platinum_basedProcessingHub_MultiBlockStructure;

import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.common.data.GCyMBlocks.CASING_HIGH_TEMPERATURE_SMELTING;
import static com.gregtechceu.gtceu.common.data.GTBlocks.ADVANCED_COMPUTER_CASING;
import static com.gregtechceu.gtceu.common.data.GTBlocks.HIGH_POWER_CASING;

public class MultiBlockMachine {

    public static final MultiblockMachineDefinition SUPERFLUID_GENERAL_ENERGY_FURNACE;
    public static final MultiblockMachineDefinition BLACK_HOLE_MATTER_DECOMPRESSOR;
    public static final MultiblockMachineDefinition PLATINUM_BASE_DPROCESSING_HUB;

    static {
        GTLEXRegistration.REGISTRATE.creativeModeTab(() -> GTL_Extend_CreativeModeTabs.MACHINES_ITEM);
    }

    static {
        SUPERFLUID_GENERAL_ENERGY_FURNACE = GTLEXRegistration.REGISTRATE.multiblock("superfluid_general_energy_furnace", WorkableElectricMultiblockMachine::new)
                .rotationState(RotationState.NON_Y_AXIS)
                .appearanceBlock(CASING_HIGH_TEMPERATURE_SMELTING)
                .recipeType(GTRecipeTypes.BLAST_RECIPES)
                .recipeType(GTRecipeTypes.ALLOY_SMELTER_RECIPES)
                .recipeType(GCyMRecipeTypes.ALLOY_BLAST_RECIPES)
                .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH,
                        GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.PERFECT_OVERCLOCK_SUBTICK))
                .recipeModifier((machine, recipe, params, result) -> {
                    GTRecipe recipe_s = recipe.copy();
                    recipe_s.duration = 1;
                    recipe_s = GTRecipeModifiers.fastParallel(machine, recipe_s, 2147483647, false).getFirst();
                    return recipe_s;
                })
                .tooltips(Component.literal(TextUtil.full_color("最大并行数：int")))
                .tooltips(Component.literal(TextUtil.full_color("26个线圈就可以让你获得无与伦比的并行和跨配方并行")))
                .tooltips(Component.literal(TextUtil.full_color("所有配方都为1t")))
                .tooltips(Component.translatable("gtceu.multiblock.laser.tooltip"))
                .tooltips(Component.translatable("gtceu.machine.perfect_oc"))
                .tooltips(Component.translatable("gtceu.machine.available_recipe_map_3.tooltip",
                        Component.translatable("gtceu.electric_blast_furnace"),
                        Component.translatable("gtceu.alloy_blast_smelter"),
                        Component.translatable("gtceu.alloy_smelter")))
                .tooltips(Component.literal(TextUtil.full_color("由GTL_Extend添加")))
                .pattern(definition -> MultiBlockStructure.GENERAL_ENERGY_FURNACE
                        .where('~', Predicates.controller(blocks(definition.getBlock())))
                        .where(' ', Predicates.any())
                        .where("c", blocks(GetRegistries.getBlock("gtceu:cleanroom_glass")))
                        .where("a", Predicates.blocks(GCyMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.get())
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
                .workableCasingRenderer(GTCEu.id("block/casings/gcym/high_temperature_smelting_casing"),
                        GTCEu.id("block/multiblock/fusion_reactor"), false)
                .register();

        BLACK_HOLE_MATTER_DECOMPRESSOR = GTLEXRegistration.REGISTRATE.multiblock("black_hole_matter_decompressor", BlackHoleMatterDecompressor::new)
                .rotationState(RotationState.NON_Y_AXIS)
                .appearanceBlock(HIGH_POWER_CASING)
                .recipeType(GTLRecipeTypes.COSMOS_SIMULATION_RECIPES)
                .recipeType(GTL_Extend_RecipeTypes.HORIZON_MATTER_DECOMPRESSION_RECIPES)
                .recipeModifier(BlackHoleMatterDecompressor::recipeModifier)
                .tooltips(Component.literal(TextUtil.full_color("创造一个黑洞，并从里面获取无限的资源")))
                .tooltips(Component.literal("这台机器需要巨量的EU，无法使用常规手段供能"))
                .tooltips(Component.literal("由无线电网直接供电，具体数值可在GUI內查看"))
                .tooltips(Component.literal(TextUtil.full_color("如果开启蓝梦模式则执行特殊并行")))
                .tooltips(Component.literal(TextUtil.full_color("每多提供1000B液态永恒蓝梦则翻倍并行")))
                .tooltips(Component.translatable("gtceu.machine.available_recipe_map_2.tooltip",
                        Component.translatable("gtceu.cosmos_simulation"),
                        Component.translatable("gtceu.horizon_matter_decompression")))
                .tooltips(Component.literal(TextUtil.full_color("由GTL_Extend添加")))
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

        PLATINUM_BASE_DPROCESSING_HUB = GTLEXRegistration.REGISTRATE.multiblock("platinum_based_rocessing_hub", WorkableElectricMultiblockMachine::new)
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(GTL_Extend_RecipeTypes.PLATINUM_BASE_DPROCESSING_HUB_RECIPES)
                .appearanceBlock(ADVANCED_COMPUTER_CASING)
                .pattern(definition -> Platinum_basedProcessingHub_MultiBlockStructure.PLATINUM_BASE_DPROCESSING_HUB
                        .where('~', Predicates.controller(blocks(definition.getBlock())))
                        .where(' ', Predicates.any())
                        .where('A', Predicates.blocks(ADVANCED_COMPUTER_CASING.get())
                                .or(Predicates.abilities(PartAbility.EXPORT_ITEMS))
                                .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                                .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS))
                                .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                                .or(Predicates.abilities(PartAbility.INPUT_LASER).setMaxGlobalLimited(1))
                                .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2)))
                        .where('B', Predicates.blocks(GetRegistries.getBlock("gtceu:tritanium_frame")))
                        .where('C', Predicates.blocks(GetRegistries.getBlock("gtceu:tritanium_coil_block")))
                        .where('D', Predicates.blocks(GetRegistries.getBlock("gtlcore:extreme_strength_tritanium_casing")))
                        .where('E', Predicates.blocks(GetRegistries.getBlock("gtceu:plascrete")))
                        .where('F', Predicates.blocks(GetRegistries.getBlock("gtl_extend:dimension_core")))
                        .where('G', Predicates.blocks(GetRegistries.getBlock("gtceu:high_power_casing")))
                        .where('H', Predicates.blocks(GetRegistries.getBlock("gtlcore:iridium_casing")))
                        .build())
                .workableCasingRenderer(GTCEu.id("block/casings/hpca/advanced_computer_casing/back"),
                        GTCEu.id("block/multiblock/cosmos_simulation"), false)
                .register();
    }

    public MultiBlockMachine() {}

    public static void init() {
        GTL_Extend_SmallMachines.init();
    }
}
