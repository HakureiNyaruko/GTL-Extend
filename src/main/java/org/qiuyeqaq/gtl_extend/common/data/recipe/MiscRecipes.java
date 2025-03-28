package org.qiuyeqaq.gtl_extend.common.data.recipe;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import org.qiuyeqaq.gtl_extend.common.multiblock.MultiBlockMachine;

import java.util.function.Consumer;

import org.gtlcore.gtlcore.utils.Registries;

public class MiscRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
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
                .EUt(GTValues.V[GTValues.UHV])
                .save(provider);
    }
}
