package org.qiuyeqaq.gtl_extend.common.data.recipe;

import com.gregtechceu.gtceu.api.GTValues;
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
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;
import static org.gtlcore.gtlcore.common.data.GTLRecipeTypes.SUPRACHRONAL_ASSEMBLY_LINE_RECIPES;
import static org.qiuyeqaq.gtl_extend.common.blocks.Gtl_extend_Blocks.DIMENSION_CORE;
import static org.qiuyeqaq.gtl_extend.common.blocks.Gtl_extend_Blocks.VOID_WORLD_BLOCK;
import static org.qiuyeqaq.gtl_extend.common.machines.recipes.GTL_Extend_RecipeTypes.PLATINUM_BASE_DPROCESSING_HUB_RECIPES;
import static org.qiuyeqaq.gtl_extend.common.machines.recipes.GTL_Extend_RecipeTypes.VOID_PUMP_RECIPES;

public class MiscRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        VOID_PUMP_RECIPES.recipeBuilder("gtl_extend_oil")
                .circuitMeta(1)
                .outputFluids(GTMaterials.Oil.getFluid(100000))
                .duration(500)
                .EUt(GTValues.V[GTValues.EV])
                .addData("CRTier", 1)
                .save(provider);

        PLATINUM_BASE_DPROCESSING_HUB_RECIPES.recipeBuilder("platinum_treatment")
                .inputItems(Registries.getItem("gtceu:platinum_group_sludge_dust"), 5000)
                .inputItems(Registries.getItem("gtceu:sulfur_dust"), 139)
                .inputFluids(GTMaterials.Hydrogen.getFluid(625000))
                .inputFluids(GTMaterials.Oxygen.getFluid(1111000))
                .inputFluids(GTMaterials.Chlorine.getFluid(125000))
                .outputItems(Registries.getItem("gtceu:platinum_dust"), 555)
                .outputItems(Registries.getItem("gtceu:palladium_dust"), 555)
                .outputItems(Registries.getItem("gtceu:ruthenium_dust"), 555)
                .outputItems(Registries.getItem("gtceu:iridium_dust"), 278)
                .outputItems(Registries.getItem("gtceu:osmium_dust"), 278)
                .outputItems(Registries.getItem("gtceu:rhodium_dust"), 417)
                .outputFluids(GTMaterials.Hydrogen.getFluid(25000))
                .outputFluids(GTMaterials.Chlorine.getFluid(56000))
                .outputFluids(GTMaterials.Water.getFluid(62500))
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
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(16000))
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
                .inputFluids(GTMaterials.Ruthenium.getFluid(32000))
                .inputFluids(GTMaterials.Rhodium.getFluid(32000))
                .inputFluids(GTMaterials.Iridium.getFluid(32000))
                .inputFluids(GTMaterials.Osmium.getFluid(32000))
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
                .inputFluids(GTMaterials.Dubnium.getFluid(16000))
                .inputFluids(GTMaterials.Titanium.getFluid(16000))
                .inputFluids(GTLMaterials.MutatedLivingSolder.getFluid(16000))
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(16000))
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
                .inputFluids(GTMaterials.Neutronium.getFluid(100000))
                .inputFluids(GTLMaterials.Infinity.getFluid(16000))
                .outputItems(MultiBlockMachine.BLACK_HOLE_MATTER_DECOMPRESSOR)
                .duration(4400)
                .EUt(V[MAX] * 16384)
                .stationResearch(b -> b.researchStack(Registries.getItem("gtladditions:arcanic_astrograph").getDefaultInstance())
                        .dataStack(Gtl_extend_Item.ADVANCED_DATA_MODULE.asStack())
                        .EUt(VA[MAX])
                        .CWUt(16384))
                .save(provider);
    }
}
