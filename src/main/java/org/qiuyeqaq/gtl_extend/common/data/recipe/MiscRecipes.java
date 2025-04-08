package org.qiuyeqaq.gtl_extend.common.data.recipe;

import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import net.minecraft.data.recipes.FinishedRecipe;
import org.gtlcore.gtlcore.common.data.GTLMaterials;
import org.gtlcore.gtlcore.utils.Registries;
import org.qiuyeqaq.gtl_extend.common.items.Gtl_extend_Item;
import org.qiuyeqaq.gtl_extend.common.multiblock.MultiBlockMachine;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;
import static org.gtlcore.gtlcore.common.data.GTLMaterials.*;
import static org.gtlcore.gtlcore.common.data.GTLRecipeTypes.ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES;
import static org.gtlcore.gtlcore.common.data.GTLRecipeTypes.SUPRACHRONAL_ASSEMBLY_LINE_RECIPES;
import static org.qiuyeqaq.gtl_extend.common.blocks.Gtl_extend_Blocks.DIMENSION_CORE;
import static org.qiuyeqaq.gtl_extend.common.blocks.Gtl_extend_Blocks.VOID_WORLD_BLOCK;
import static org.qiuyeqaq.gtl_extend.common.machines.recipes.GTL_Extend_RecipeTypes.*;

public class MiscRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CATTLE_CATTLE_MACHINE_RECIPES.recipeBuilder("gtl_extend_milk")
                .notConsumable(Registries.getItem("minecraft:cow_spawn_egg"))
                .outputFluids(Milk.getFluid(10000))
                .duration(20)
                .EUt(V[HV])
                .addData("CRTier", 1)
                .save(provider);

        CATTLE_CATTLE_MACHINE_RECIPES.recipeBuilder("gtl_extend_liquidstarlight")
                .inputFluids(GTLMaterials.Mana.getFluid(10000))
                .outputFluids(GTLMaterials.LiquidStarlight.getFluid(10000))
                .duration(20)
                .EUt(V[UXV])
                .addData("CRTier", 2)
                .save(provider);

        VOID_PUMP_RECIPES.recipeBuilder("gtl_extend_oil")
                .circuitMeta(1)
                .outputFluids(Oil.getFluid(100000))
                .duration(500)
                .EUt(V[EV])
                .addData("CRTier", 1)
                .save(provider);

        ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES.recipeBuilder("large_world_void_pump")
                .inputItems(Registries.getItem("gtceu:ev_fluid_drilling_rig"), 64)
                .inputItems(Registries.getItem("gtceu:ev_fluid_drilling_rig"), 64)
                .outputItems(MultiBlockMachine.LARGE_VOID_PUMP)
                .duration(2048)
                .EUt(V[IV])
                .save(provider);

        PLATINUM_BASE_DPROCESSING_HUB_RECIPES.recipeBuilder("platinum_treatment")
                .inputItems(Registries.getItem("gtceu:platinum_group_sludge_dust"), 5000)
                .inputItems(Registries.getItem("gtceu:sulfur_dust"), 139)
                .inputFluids(Hydrogen.getFluid(625000))
                .inputFluids(Oxygen.getFluid(1111000))
                .inputFluids(Chlorine.getFluid(125000))
                .outputItems(Registries.getItem("gtceu:platinum_dust"), 555)
                .outputItems(Registries.getItem("gtceu:palladium_dust"), 555)
                .outputItems(Registries.getItem("gtceu:ruthenium_dust"), 555)
                .outputItems(Registries.getItem("gtceu:iridium_dust"), 278)
                .outputItems(Registries.getItem("gtceu:osmium_dust"), 278)
                .outputItems(Registries.getItem("gtceu:rhodium_dust"), 417)
                .outputFluids(Hydrogen.getFluid(25000))
                .outputFluids(Chlorine.getFluid(56000))
                .outputFluids(Water.getFluid(62500))
                .duration(200)
                .EUt(VA[LuV])
                .save(provider);

        PLATINUM_BASE_DPROCESSING_HUB_RECIPES.recipeBuilder("platinum_treatment_1")
                .inputItems(Registries.getItem("gtceu:platinum_group_sludge_dust"), 5000)
                .inputItems(Registries.getItem("gtceu:sulfur_dust"), 139)
                .inputFluids(Hydrogen.getFluid(625000))
                .inputFluids(Oxygen.getFluid(1111000))
                .inputFluids(Chlorine.getFluid(125000))
                .inputFluids(Argon.getFluid(FluidStorageKeys.PLASMA, 1000))
                .outputItems(Registries.getItem("gtceu:platinum_dust"), 833)
                .outputItems(Registries.getItem("gtceu:palladium_dust"), 833)
                .outputItems(Registries.getItem("gtceu:ruthenium_dust"), 833)
                .outputItems(Registries.getItem("gtceu:iridium_dust"), 417)
                .outputItems(Registries.getItem("gtceu:osmium_dust"), 417)
                .outputItems(Registries.getItem("gtceu:rhodium_dust"), 625)
                .outputFluids(Hydrogen.getFluid(37500))
                .outputFluids(Chlorine.getFluid(84000))
                .outputFluids(Water.getFluid(93750))
                .duration(200)
                .EUt(VA[LuV])
                .save(provider);

        PLATINUM_BASE_DPROCESSING_HUB_RECIPES.recipeBuilder("platinum_treatment_2")
                .inputItems(Registries.getItem("gtceu:platinum_group_sludge_dust"), 5000)
                .inputItems(Registries.getItem("gtceu:sulfur_dust"), 139)
                .inputFluids(Hydrogen.getFluid(625000))
                .inputFluids(Oxygen.getFluid(1111000))
                .inputFluids(Chlorine.getFluid(125000))
                .inputFluids(Iron.getFluid(FluidStorageKeys.PLASMA, 1000))
                .outputItems(Registries.getItem("gtceu:platinum_dust"), 555 * 2)
                .outputItems(Registries.getItem("gtceu:palladium_dust"), 555 * 2)
                .outputItems(Registries.getItem("gtceu:ruthenium_dust"), 555 * 2)
                .outputItems(Registries.getItem("gtceu:iridium_dust"), 278 * 2)
                .outputItems(Registries.getItem("gtceu:osmium_dust"), 278 * 2)
                .outputItems(Registries.getItem("gtceu:rhodium_dust"), 417 * 2)
                .outputFluids(Hydrogen.getFluid(25000 * 2))
                .outputFluids(Chlorine.getFluid(56000 * 2))
                .outputFluids(Water.getFluid(62500 * 2))
                .duration(200)
                .EUt(VA[LuV])
                .save(provider);

        PLATINUM_BASE_DPROCESSING_HUB_RECIPES.recipeBuilder("platinum_treatment_3")
                .inputItems(Registries.getItem("gtceu:platinum_group_sludge_dust"), 5000)
                .inputItems(Registries.getItem("gtceu:sulfur_dust"), 139)
                .inputFluids(Hydrogen.getFluid(625000))
                .inputFluids(Oxygen.getFluid(1111000))
                .inputFluids(Chlorine.getFluid(125000))
                .inputFluids(Nickel.getFluid(FluidStorageKeys.PLASMA, 1000))
                .outputItems(Registries.getItem("gtceu:platinum_dust"), 555 * 3)
                .outputItems(Registries.getItem("gtceu:palladium_dust"), 555 * 3)
                .outputItems(Registries.getItem("gtceu:ruthenium_dust"), 555 * 3)
                .outputItems(Registries.getItem("gtceu:iridium_dust"), 278 * 3)
                .outputItems(Registries.getItem("gtceu:osmium_dust"), 278 * 3)
                .outputItems(Registries.getItem("gtceu:rhodium_dust"), 417 * 3)
                .outputFluids(Hydrogen.getFluid(25000 * 3))
                .outputFluids(Chlorine.getFluid(56000 * 3))
                .outputFluids(Water.getFluid(62500 * 3))
                .duration(200)
                .EUt(VA[LuV])
                .save(provider);

        PLATINUM_BASE_DPROCESSING_HUB_RECIPES.recipeBuilder("platinum_treatment_4")
                .inputItems(Registries.getItem("gtceu:platinum_group_sludge_dust"), 5000)
                .inputItems(Registries.getItem("gtceu:sulfur_dust"), 139)
                .inputFluids(Hydrogen.getFluid(625000))
                .inputFluids(Oxygen.getFluid(1111000))
                .inputFluids(Chlorine.getFluid(125000))
                .inputFluids(GTLMaterials.DegenerateRhenium.getFluid(FluidStorageKeys.PLASMA, 1000))
                .outputItems(Registries.getItem("gtceu:platinum_dust"), 555 * 5)
                .outputItems(Registries.getItem("gtceu:palladium_dust"), 555 * 5)
                .outputItems(Registries.getItem("gtceu:ruthenium_dust"), 555 * 5)
                .outputItems(Registries.getItem("gtceu:iridium_dust"), 278 * 5)
                .outputItems(Registries.getItem("gtceu:osmium_dust"), 278 * 5)
                .outputItems(Registries.getItem("gtceu:rhodium_dust"), 417 * 5)
                .outputFluids(Hydrogen.getFluid(25000 * 5))
                .outputFluids(Chlorine.getFluid(56000 * 5))
                .outputFluids(Water.getFluid(62500 * 5))
                .duration(200)
                .EUt(VA[LuV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("superfluid_general_energy_furnace")
                .inputItems(Registries.getItem("gtceu:electric_blast_furnace"), 8)
                .inputItems(Registries.getItem("gtceu:alloy_blast_smelter"), 8)
                .inputItems(Registries.getItem("gtceu:multi_smelter"), 8)
                .inputItems(Registries.getItem("gtceu:ptfe_pipe_casing"), 64)
                .inputItems(Registries.getItem("gtceu:heat_vent"), 64)
                .inputItems(Registries.getItem("gtceu:high_temperature_smelting_casing"), 64)
                .inputFluids(SolderingAlloy.getFluid(16000))
                .outputItems(MultiBlockMachine.SUPERFLUID_GENERAL_ENERGY_FURNACE)
                .duration(4096)
                .EUt(V[UHV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("advanced_data_module")
                .inputItems(Registries.getItem("gtceu:data_module"), 4)
                .inputItems(Registries.getItem("kubejs:supracausal_printed_circuit_board"), 2)
                .inputItems(Registries.getItem("kubejs:supracausal_processing_core"))
                .inputItems(Registries.getItem("gtceu:infinity_single_wire"), 8)
                .inputFluids(GTLMaterials.Infinity.getFluid(144))
                .outputItems(Gtl_extend_Item.ADVANCED_DATA_MODULE)
                .duration(534)
                .EUt(V[MAX])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("cattle_cattle_machine")
                .inputFluids(GTMaterials.Milk.getFluid(10000000000L))
                .outputItems(MultiBlockMachine.CATTLE_CATTLE_MACHINE)
                .duration(20000)
                .EUt(V[LuV])
                .stationResearch(b -> b.researchStack(Registries.getItem("gtceu:void_fluid_drilling_rig").getDefaultInstance())
                        .dataStack(GTItems.TOOL_DATA_MODULE.asStack())
                        .EUt(VA[LuV])
                        .CWUt(128))
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("platinum_based_rocessing_hub")
                .inputItems(Registries.getItem("gtceu:chemical_plant"), 16)
                .inputItems(Registries.getItem("gtceu:large_distillery"), 4)
                .inputItems(Registries.getItem("gtceu:large_sifting_funnel"), 4)
                .inputItems(Registries.getItem("gtceu:large_pyrolyse_oven"), 4)
                .inputItems(CustomTags.UV_CIRCUITS, 32)
                .inputItems(Registries.getItem("gtceu:luv_robot_arm"), 32)
                .inputItems(Registries.getItem("gtceu:ruridit_frame"), 64)
                .inputItems(Registries.getItem("gtceu:double_ruthenium_plate"), 32)
                .inputItems(Registries.getItem("gtceu:double_rhodium_plate"), 32)
                .inputFluids(Ruthenium.getFluid(32000))
                .inputFluids(Rhodium.getFluid(32000))
                .inputFluids(Iridium.getFluid(32000))
                .inputFluids(Osmium.getFluid(32000))
                .outputItems(MultiBlockMachine.PLATINUM_BASE_DPROCESSING_HUB)
                .duration(6400)
                .EUt(V[LuV])
                .stationResearch(b -> b.researchStack(Registries.getItem("gtceu:isa_mill").getDefaultInstance())
                        .dataStack(GTItems.TOOL_DATA_MODULE.asStack())
                        .EUt(VA[LuV])
                        .CWUt(128))
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("dimension_core")
                .inputItems(VOID_WORLD_BLOCK, 64)
                .inputItems(Registries.getItem("gtceu:assembler_module"), 32)
                .inputItems(Registries.getItem("gtceu:resource_collection"), 32)
                .inputItems(Registries.getItem("gtceu:large_void_miner"), 32)
                .inputItems(Registries.getItem("gtceu:large_greenhouse"), 16)
                .inputItems(Registries.getItem("gtceu:large_incubator"), 16)
                .inputFluids(Dubnium.getFluid(16000))
                .inputFluids(Titanium.getFluid(16000))
                .inputFluids(GTLMaterials.MutatedLivingSolder.getFluid(16000))
                .inputFluids(SolderingAlloy.getFluid(16000))
                .outputItems(DIMENSION_CORE)
                .duration(1200)
                .EUt(V[ZPM])
                .stationResearch(b -> b.researchStack(VOID_WORLD_BLOCK.asStack())
                        .dataStack(GTItems.TOOL_DATA_MODULE.asStack())
                        .EUt(VA[ZPM])
                        .CWUt(1024))
                .save(provider);

        SUPRACHRONAL_ASSEMBLY_LINE_RECIPES.recipeBuilder("black_hole_matter_decompressor")
                .inputItems(Registries.getItem("gtlcore:dimension_injection_casing"), 64)
                .inputItems(Registries.getItem("kubejs:spacetime_compression_field_generator"), 64)
                .inputItems(Registries.getItem("kubejs:dimensional_stability_casing"), 64)
                .inputItems(Registries.getItem("gtladditions:fuxi_bagua_heaven_forging_furnace"), 4)
                .inputItems(Registries.getItem("gtladditions:arcanic_astrograph"), 8)
                .inputItems(Registries.getItem("gtceu:eye_of_harmony"), 64)
                .inputItems(Registries.getItem("kubejs:ctc_computational_unit"), 64)
                .inputItems(Registries.getItem("kubejs:stabilized_wormhole_generator"), 64)
                .inputItems(CustomTags.MAX_CIRCUITS, 32)
                .inputItems(Registries.getItem("gtceu:eternity_nanoswarm"), 8)
                .inputItems(Registries.getItem("gtlcore:max_emitter"), 64)
                .inputItems(Registries.getItem("gtlcore:max_sensor"), 64)
                .inputItems(Registries.getItem("gtlcore:max_robot_arm"), 64)
                .inputItems(Registries.getItem("kubejs:time_dilation_containment_unit"), 64)
                .inputItems(DIMENSION_CORE, 8)
                .inputItems(Registries.getItem("gtceu:double_chaos_plate"), 64)
                .inputFluids(GTLMaterials.SuperMutatedLivingSolder.getFluid(480000))
                .inputFluids(GTLMaterials.DegenerateRhenium.getFluid(100000))
                .inputFluids(Neutronium.getFluid(100000))
                .inputFluids(GTLMaterials.Infinity.getFluid(16000))
                .outputItems(MultiBlockMachine.BLACK_HOLE_MATTER_DECOMPRESSOR)
                .duration(4400)
                .EUt(V[MAX] * 16384)
                .stationResearch(b -> b.researchStack(Registries.getItem("gtladditions:arcanic_astrograph").getDefaultInstance())
                        .dataStack(Gtl_extend_Item.ADVANCED_DATA_MODULE.asStack())
                        .EUt(VA[MAX])
                        .CWUt(16384))
                .save(provider);

        HORIZON_MATTER_DECOMPRESSION_RECIPES.recipeBuilder("horizon_matter_decompression")
                .inputItems(Registries.getItem("kubejs:quantum_chromodynamic_charge"))
                .inputFluids(GTLMaterials.CosmicElement.getFluid(1024000))
                .outputFluids(Argon.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Helium.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Iron.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Nickel.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Nitrogen.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Oxygen.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Silver.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Adamantium.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Vibranium.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Chaos.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Mithril.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Crystalmatrix.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Echoite.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Legendarium.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        DraconiumAwakened.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Starmetal.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Orichalcum.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Infuscolium.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Enderium.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        DegenerateRhenium.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        HeavyQuarkDegenerateMatter.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        MetastableHassium.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        QuantumChromodynamicallyConfinedMatter.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        AstralTitanium.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        CelestialTungsten.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Quasifissioning.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        Flyb.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        TaraniumRichLiquidHelium4.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        QuarkGluon.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        DenseNeutron.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        HighEnergyQuarkGluon.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        RawStarMatter.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        CosmicMesh.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        ActiniumSuperhydride.getFluid(FluidStorageKeys.PLASMA, 131072000),
                        DimensionallyTranscendentCrudeCatalyst.getFluid(131072000),
                        Eternity.getFluid(131072000))
                .duration(1200)
                .save(provider);
    }
}
