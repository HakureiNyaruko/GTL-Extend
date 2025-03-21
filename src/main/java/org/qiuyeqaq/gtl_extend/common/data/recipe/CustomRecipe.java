package org.qiuyeqaq.gtl_extend.common.data.recipe;

import org.gtlcore.gtlcore.utils.Registries;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.Tags;

import org.qiuyeqaq.gtl_extend.common.data.GetRegistries;
import org.qiuyeqaq.gtl_extend.common.machines.machines.GTL_Extend_Machines;
import org.qiuyeqaq.gtl_extend.common.materials.GTL_Extend_Materials;
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