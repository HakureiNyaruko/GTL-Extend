package org.qiuyeqaq.gtl_extend.api.machine;

import org.gtlcore.gtlcore.api.machine.multiblock.ParallelMachine;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;

import org.jetbrains.annotations.NotNull;
import org.qiuyeqaq.gtl_extend.api.registries.GTLEXMultipleRecipes;

public class GTLEXSuperfluidGeneralEnergyFurnaceMachine extends WorkableElectricMultiblockMachine implements ParallelMachine {

    public GTLEXSuperfluidGeneralEnergyFurnaceMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    public @NotNull RecipeLogic createRecipeLogic(@NotNull Object... args) {
        return new GTLEXMultipleRecipes(this);
    }

    public @NotNull GTLEXMultipleRecipes getRecipeLogic() {
        return (GTLEXMultipleRecipes) super.getRecipeLogic();
    }

    @Override
    public int getMaxParallel() {
        return Integer.MAX_VALUE;
    }
}
