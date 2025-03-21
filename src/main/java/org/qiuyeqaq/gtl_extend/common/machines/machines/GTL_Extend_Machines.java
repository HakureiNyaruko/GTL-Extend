package org.qiuyeqaq.gtl_extend.common.machines.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.client.util.TooltipHelper;
import com.gregtechceu.gtceu.common.data.GCyMRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import org.gtlcore.gtlcore.client.renderer.machine.EyeOfHarmonyRenderer;
import org.gtlcore.gtlcore.common.data.GTLBlocks;
import org.gtlcore.gtlcore.common.data.GTLRecipeTypes;
import org.gtlcore.gtlcore.utils.Registries;
import org.gtlcore.gtlcore.utils.TextUtil;
import org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration;
import org.qiuyeqaq.gtl_extend.api.registries.special.AdvancedHarmonyMachine;
import org.qiuyeqaq.gtl_extend.common.data.GTL_Extend_CreativeModeTabs;
import org.qiuyeqaq.gtl_extend.common.data.GetRegistries;
import org.qiuyeqaq.gtl_extend.common.machines.recipes.GTL_Extend_RecipeTypes;

import java.util.List;
import java.util.function.BiConsumer;

import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.api.pattern.util.RelativeDirection.*;
import static com.gregtechceu.gtceu.common.data.GCyMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GCyMRecipeTypes.*;
import static org.gtlcore.gtlcore.common.data.GTLRecipeTypes.COSMOS_SIMULATION_RECIPES;
import static org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration.*;

@SuppressWarnings("unused")
public class GTL_Extend_Machines {

    public static final BiConsumer<ItemStack, List<Component>> GTL_EX_ADD = (itemStack, components) -> components
            .add(Component.translatable("Gtl.registry.add")
                    .withStyle(style -> style.withColor(TooltipHelper.RAINBOW_SLOW.getCurrent())));
    static {
        GTLEXRegistration.REGISTRATE.creativeModeTab(() -> GTL_Extend_CreativeModeTabs.MACHINES_ITEM);
    }

    public static void init() {
        GTL_Extend_SmallMachines.init();
    }

    public static final MachineDefinition SUPERFLUID_GENERAL_ENERGY_FURNACE = GTLEXRegistration.REGISTRATE.multiblock("superfluid_general_energy_furnace", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_HIGH_TEMPERATURE_SMELTING)
            .recipeType(GTRecipeTypes.BLAST_RECIPES)
            .recipeType(GTRecipeTypes.ALLOY_SMELTER_RECIPES)
            .recipeType(GCyMRecipeTypes.ALLOY_BLAST_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.PERFECT_OVERCLOCK_SUBTICK))
            .recipeModifier((machine, recipe, params, result) -> {
                GTRecipe modifiedRecipe = recipe.copy();
                modifiedRecipe.duration = 0;

                return modifiedRecipe;
            })
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("aaaaaaa", "ccccccc", "ccccccc", "ccccccc", "eeeeeee", "ccccccc", "ccccccc", "ccccccc", "aaaaaaa")
                    .aisle("aaaaaaa", "c     c", "c     c", "c     c", "e     e", "c     c", "c     c", "c     c", "aaaaaaa")
                    .aisle("aaaaaaa", "c     c", "c     c", "c     c", "e     e", "c     c", "c     c", "c     c", "aaaaaaa")
                    .aisle("aaaaaaa", "c     c", "c     c", "c     c", "e  f  e", "c     c", "c     c", "c     c", "aaaaaaa")
                    .aisle("aaaaaaa", "c     c", "c     c", "c     c", "e     e", "c     c", "c     c", "c     c", "aaaaaaa")
                    .aisle("aaaaaaa", "c     c", "c     c", "c     c", "e     e", "c     c", "c     c", "c     c", "aaaaaaa")
                    .aisle("aaa~aaa", "ccccccc", "ccccccc", "ccccccc", "eeeeeee", "ccccccc", "ccccccc", "ccccccc", "aaaaaaa")
                    .where('~', Predicates.controller(blocks(definition.getBlock())))
                    .where(' ', Predicates.any())
                    .where("c", Predicates.blocks(GetRegistries.getBlock("gtceu:cleanroom_glass")))
                    .where("a", Predicates.blocks(GetRegistries.getBlock("gtceu:high_temperature_smelting_casing"))
                            .setMinGlobalLimited(10)
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.INPUT_LASER).setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2)))
                    .where("e", Predicates.blocks(GetRegistries.getBlock("gtceu:heat_vent")))
                    .where("f", Predicates.heatingCoils())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/gcym/high_temperature_smelting_casing"),
                    GTCEu.id("block/multiblock/fusion_reactor"), false)
            .tooltips(Component.translatable("block.gtl_extend.superfluid_general_energy_furnace.tooltip"))
            .register();

    public static final MachineDefinition BLACK_HOLE_MATTER_DECOMPRESSOR = GTLEXRegistration.REGISTRATE.multiblock("black_hole_matter_decompressor", AdvancedHarmonyMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(COSMOS_SIMULATION_RECIPES)
            .recipeType(GTL_Extend_RecipeTypes.HORIZON_MATTER_DECOMPRESSION)
            .recipeModifier(AdvancedHarmonyMachine::recipeModifier)
            .tooltips(Component.literal(TextUtil.full_color("由GTL_Extend添加")))
            .appearanceBlock(GTBlocks.HIGH_POWER_CASING)
            .renderer(EyeOfHarmonyRenderer::new)
            .hasTESR(true)
            .register();
}
