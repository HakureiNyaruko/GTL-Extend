package org.qiuyeqaq.gtl_extend.mixin.gtl;

import net.minecraft.data.recipes.FinishedRecipe;

import org.qiuyeqaq.gtl_extend.common.data.recipe.MiscRecipes;

import java.util.function.Consumer;

import org.gtlcore.gtlcore.GTLGTAddon;
import org.gtlcore.gtlcore.data.recipe.*;
import org.gtlcore.gtlcore.data.recipe.chemistry.MixerRecipes;
import org.gtlcore.gtlcore.data.recipe.processing.Lanthanidetreatment;
import org.gtlcore.gtlcore.data.recipe.processing.StoneDustProcess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GTLGTAddon.class)
public class GTLAddonMixin {

    @Inject(method = "addRecipes", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private void addRecipes(Consumer<FinishedRecipe> provider, CallbackInfo ci) {
        GCyMRecipes.init(provider);
        FuelRecipes.init(provider);
        MachineRecipe.init(provider);
        Misc.init(provider);
        ElementCopying.init(provider);
        StoneDustProcess.init(provider);
        Lanthanidetreatment.init(provider);
        CircuitRecipes.init(provider);
        MixerRecipes.init(provider);
        MiscRecipes.init(provider);
        ci.cancel();
    }
}
