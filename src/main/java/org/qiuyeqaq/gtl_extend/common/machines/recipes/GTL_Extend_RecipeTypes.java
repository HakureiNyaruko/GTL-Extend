package org.qiuyeqaq.gtl_extend.common.machines.recipes;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class GTL_Extend_RecipeTypes {

    public static void init() {}

    public final static GTRecipeType HORIZON_MATTER_DECOMPRESSION = register("horizon_matter_decompression", MULTIBLOCK)
            .setMaxIOSize(1, 0, 2, 34)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MOTOR);
}
