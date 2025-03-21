package org.qiuyeqaq.gtl_extend.common.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import org.qiuyeqaq.gtl_extend.config.GTLExtendConfigHolder;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.qiuyeqaq.gtl_extend.common.items.Gtl_extend_Item.*;
import static org.qiuyeqaq.gtl_extend.common.machines.recipes.GTL_Extend_RecipeTypes.*;

public class CustomRecipe {

    public static void init(Consumer<FinishedRecipe> provider) {
        if (GTLExtendConfigHolder.INSTANCE.enableInfinityDreamAndDreamHostCrafting) {

        }
    }
}
