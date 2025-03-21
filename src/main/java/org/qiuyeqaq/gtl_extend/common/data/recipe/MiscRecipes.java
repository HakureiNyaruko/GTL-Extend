package org.qiuyeqaq.gtl_extend.common.data.recipe;

import org.gtlcore.gtlcore.common.data.*;
import org.gtlcore.gtlcore.common.data.machines.AdvancedMultiBlockMachine;
import org.gtlcore.gtlcore.utils.Registries;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;

import committee.nova.mods.avaritia.init.registry.ModItems;
import org.qiuyeqaq.gtl_extend.common.items.Gtl_extend_Item;
import org.qiuyeqaq.gtl_extend.common.machines.machines.GTL_Extend_Machines;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.UXV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.qiuyeqaq.gtl_extend.common.machines.recipes.GTL_Extend_RecipeTypes.*;

public class MiscRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("Gtl_Extend_superfluid_general_energy_furnace")
                .inputItems(Registries.getItem("gtceu:electric_blast_furnace"), 4)
                .inputItems(Registries.getItem("gtceu:alloy_blast_smelter"), 4)
                .inputItems(Registries.getItem("gtceu:ptfe_pipe_casing"), 64)
                .inputItems(Registries.getItem("gtceu:heat_vent"), 64)
                .inputItems(Registries.getItem("gtceu:high_temperature_smelting_casing"), 64)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(16000))
                .outputItems(GTL_Extend_Machines.SUPERFLUID_GENERAL_ENERGY_FURNACE)
                .duration(4096)
                .EUt(GTValues.V[GTValues.IV])
                .save(provider);    }
}
