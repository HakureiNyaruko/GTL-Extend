package org.qiuyeqaq.gtl_extend;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.MaterialCasingCollectionEvent;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;

import org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration;
import org.qiuyeqaq.gtl_extend.common.blocks.Gtl_extend_Blocks;
import org.qiuyeqaq.gtl_extend.common.data.ore.Gtl_extend_Ores;
import org.qiuyeqaq.gtl_extend.common.data.recipe.CustomRecipe;
import org.qiuyeqaq.gtl_extend.common.items.Gtl_extend_Item;
import org.qiuyeqaq.gtl_extend.common.materials.GTL_Extend_Elements;

import java.util.function.Consumer;

@com.gregtechceu.gtceu.api.addon.GTAddon
public class Gtl_Extend_Addon implements IGTAddon {

    private static Consumer<FinishedRecipe> provider;

    @Override
    public GTRegistrate getRegistrate() {
            return GTLEXRegistration.REGISTRATE;
    }

    @Override
    public void initializeAddon() {
        Gtl_extend_Item.init();
        Gtl_extend_Blocks.init();
    }

    @Override
    public void registerElements() {
        GTL_Extend_Elements.init();
    }

    @Override
    public String addonModId() {
        return Gtl_extend.MODID;
    }
    @Override
    public void collectMaterialCasings(MaterialCasingCollectionEvent event) {
        IGTAddon.super.collectMaterialCasings(event);
    }

    @Override
    public void registerSounds() {}

    @Override
    public void registerOreVeins() {
        Gtl_extend_Ores.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        CustomRecipe.init(provider);
    }
}
