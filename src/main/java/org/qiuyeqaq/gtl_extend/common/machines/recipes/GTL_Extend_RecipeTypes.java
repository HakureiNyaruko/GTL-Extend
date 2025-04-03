package org.qiuyeqaq.gtl_extend.common.machines.recipes;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.register;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class GTL_Extend_RecipeTypes {

    public final static GTRecipeType HORIZON_MATTER_DECOMPRESSION_RECIPES = register("horizon_matter_decompression", MULTIBLOCK)
            .setMaxIOSize(1, 0, 2, 34)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.SCIENCE);
    public final static GTRecipeType PLATINUM_BASE_DPROCESSING_HUB_RECIPES = register("one_stop_platinum_treatment", MULTIBLOCK)
            .setMaxIOSize(2, 6, 3, 3)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FURNACE);

    public static void init() {}
}
