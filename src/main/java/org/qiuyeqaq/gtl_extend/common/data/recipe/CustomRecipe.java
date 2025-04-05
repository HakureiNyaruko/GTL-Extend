package org.qiuyeqaq.gtl_extend.common.data.recipe;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.gtlcore.gtlcore.utils.Registries;
import org.qiuyeqaq.gtl_extend.common.data.GetRegistries;
import org.qiuyeqaq.gtl_extend.common.items.Gtl_extend_Item;
import org.qiuyeqaq.gtl_extend.common.materials.GTL_Extend_Materials;
import org.qiuyeqaq.gtl_extend.common.multiblock.MultiBlockMachine;
import org.qiuyeqaq.gtl_extend.config.GTLExtendConfigHolder;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.EXTRACTOR_RECIPES;
import static org.gtlcore.gtlcore.common.data.GTLRecipeTypes.SUPRACHRONAL_ASSEMBLY_LINE_RECIPES;
import static org.qiuyeqaq.gtl_extend.common.blocks.Gtl_extend_Blocks.VOID_WORLD_BLOCK;
import static org.qiuyeqaq.gtl_extend.common.machines.recipes.GTL_Extend_RecipeTypes.GENERAL_PURPOSE_AE_PRODUCTION_RECIPES;
import static org.qiuyeqaq.gtl_extend.common.machines.recipes.GTL_Extend_RecipeTypes.VOID_PUMP_RECIPES;
import static org.qiuyeqaq.gtl_extend.common.materials.GTL_Extend_Materials.ETERNALBLUEDREAM;

public class CustomRecipe {

    public static void init(Consumer<FinishedRecipe> provider) {
        Object[][] magicEnergyConfigs = new Object[][] {
                // 参数格式: [电压等级, 输出物品所在Mod的命名空间, 电缆材料名]
                {"lv",   "gtceu",    "tin",          CustomTags.LV_CIRCUITS},
                {"mv",   "gtl_extend", "copper",      CustomTags.MV_CIRCUITS},
                {"hv",   "gtl_extend", "gold",        CustomTags.HV_CIRCUITS},
                {"ev",   "gtl_extend", "aluminium",   CustomTags.EV_CIRCUITS},
                {"iv",   "gtl_extend", "graphene",    CustomTags.IV_CIRCUITS},
                {"luv",  "gtl_extend", "niobium_nitride", CustomTags.LuV_CIRCUITS},
                {"zpm",  "gtl_extend", "naquadah",    CustomTags.ZPM_CIRCUITS}
        };

        for (Object[] config : magicEnergyConfigs) {
            String tier = (String) config[0];
            String namespace = (String) config[1];
            String cableMaterial = (String) config[2];
            TagKey<Item> circuitTag = (TagKey<Item>) config[3];

            VanillaRecipeHelper.addShapedRecipe(
                    provider,
                    true,
                    tier + "_primitive_magic_energy",
                    new ItemStack(GetRegistries.getItem(namespace + ":" + tier + "_primitive_magic_energy")),
                    "ADA", "ABA", "CDC",
                    'A', GetRegistries.getItem("gtceu:" + tier + "_machine_casing"),
                    'B', GetRegistries.getItem("minecraft:end_crystal"),
                    'C', GetRegistries.getItem("gtceu:" + cableMaterial + "_single_cable"),
                    'D', circuitTag
            );
        }
        VanillaRecipeHelper.addShapedRecipe(provider, true, "void_world_block",
                VOID_WORLD_BLOCK.asStack(),
                "AAA",
                "ABA",
                "AAA",
                'A', GetRegistries.getItem("minecraft:obsidian"),
                'B', GetRegistries.getItem("minecraft:ender_pearl"));
        if (GTLExtendConfigHolder.INSTANCE.enableGeneralPurposeSteamEngine) {
            VanillaRecipeHelper.addShapedRecipe(provider, true, "the_general_steam_engine",
                    MultiBlockMachine.GENERAL_PURPOSE_STEAM_ENGINE.asStack(),
                    "ABA",
                    "BCB",
                    "ABA",
                    'A', GetRegistries.getItem("gtceu:steam_machine_casing"),
                    'B', GetRegistries.getItem("gtl_extend:void_world_block"),
                    'C', GetRegistries.getItem("kubejs:precision_steam_mechanism"));
        }
        if (GTLExtendConfigHolder.INSTANCE.enableGeneralAEManufacturingMachine) {
            VanillaRecipeHelper.addShapedRecipe(provider, true, "general_ae_production",
                    MultiBlockMachine.GENERAL_PURPOSE_AE_PRODUCTION.asStack(),
                    "AAA",
                    "ABA",
                    "AAA",
                    'A', GetRegistries.getItem("ae2:sky_stone_block"),
                    'B', CustomTags.EV_CIRCUITS);

            EXTRACTOR_RECIPES.recipeBuilder("rtt_fluid_fluix")
                    .inputItems(Registries.getItem("ae2:fluix_dust"))
                    .outputFluids(GTL_Extend_Materials.FLUIXCRYSTAL.getFluid(144))
                    .duration(200)
                    .EUt(GTValues.V[GTValues.LV])
                    .save(provider);

            Object[][] aeCableConfigs = new Object[][] {
                    // 格式: [circuitMeta, 输入物品, 输出物品, 输出数量]
                    {1, "ae2:quartz_fiber",     "ae2:fluix_glass_cable",            16},
                    {2, "ae2:quartz_fiber",     "ae2:fluix_covered_cable",          16},
                    {3, "ae2:quartz_fiber",     "ae2:fluix_smart_cable",            16},
                    {4, "ae2:quartz_fiber",     "ae2:fluix_covered_dense_cable",    16},
                    {5, "ae2:quartz_fiber",     "ae2:fluix_smart_dense_cable",      16},
                    {1, "ae2:blank_pattern",    "ae2:blank_pattern",                8} // 特殊处理
            };

            for (Object[] cableConfig : aeCableConfigs) {
                int circuitMeta = (int) cableConfig[0];
                String inputItem = (String) cableConfig[1];
                String outputItem = (String) cableConfig[2];
                int outputCount = (int) cableConfig[3];

                // 生成配方名称后缀（移除命名空间前缀）
                String suffix = outputItem.replace("ae2:", "");
                if (outputItem.equals("ae2:blank_pattern")) {
                    suffix = "blank_pattern"; // 保持与原名称一致
                }

                GENERAL_PURPOSE_AE_PRODUCTION_RECIPES.recipeBuilder("gtl_ex_ae2_fluix_cable_" + suffix)
                        .circuitMeta(circuitMeta)
                        .chancedInput(new ItemStack(Registries.getItem(inputItem)), 9000, 0) // 动态指定输入
                        .inputFluids(GTL_Extend_Materials.FLUIXCRYSTAL.getFluid(144))
                        .outputItems(Registries.getItem(outputItem), outputCount)
                        .duration(512)
                        .EUt(GTValues.V[GTValues.IV])
                        .save(provider);
            }
        }
        if (GTLExtendConfigHolder.INSTANCE.enableInfinityDreamAndDreamHostCrafting) {

            VOID_PUMP_RECIPES.recipeBuilder("eternal_blue_dream_vein_fluid")
                    .circuitMeta(2)
                    .outputFluids(ETERNALBLUEDREAM.getFluid(1296))
                    .duration(500)
                    .EUt(GTValues.VA[GTValues.LuV])
                    .addData("CRier", 2)
                    .save(provider);

            // 生成 LV 配方（基础）
            SUPRACHRONAL_ASSEMBLY_LINE_RECIPES.recipeBuilder("eternalbluedream_lv_processor_mainframe")
                    .inputItems(Registries.getItem("minecraft:sand"), 64)
                    .inputFluids(ETERNALBLUEDREAM.getFluid(9216))
                    .outputItems(Gtl_extend_Item.ETERNALBLUE_DREAM_LV_PROCESSOR_MAINFRAME.get())
                    .EUt(GTValues.V[GTValues.UEV] * 10L)
                    .duration(1)
                    .save(provider);

            // 从 MV 开始，逐级生成高阶配方
            String[] tiers = { "lv", "mv", "hv", "ev", "iv", "luv", "zpm", "uv", "uhv", "uev", "uiv", "uxv", "opv", "max" };
            for (int i = 1; i < tiers.length; i++) {
                String currentTier = tiers[i];
                String prevTier = tiers[i - 1];

                SUPRACHRONAL_ASSEMBLY_LINE_RECIPES.recipeBuilder("eternalbluedream_" + currentTier + "_processor_mainframe")
                        .inputItems(getItemEntry(prevTier).get()) // 输入前一级物品
                        .inputFluids(ETERNALBLUEDREAM.getFluid(9216)) // 固定流体输入
                        .outputItems(getItemEntry(currentTier).get()) // 输出当前级物品
                        .EUt(GTValues.V[GTValues.UEV] * 10L)
                        .duration(1)
                        .save(provider);
            }
        }
    }

    // 辅助方法：根据层级名称获取对应的 ItemEntry
    private static ItemEntry<Item> getItemEntry(String tier) {
        return switch (tier) {
            case "lv" -> Gtl_extend_Item.ETERNALBLUE_DREAM_LV_PROCESSOR_MAINFRAME;
            case "mv" -> Gtl_extend_Item.ETERNALBLUE_DREAM_MV_PROCESSOR_MAINFRAME;
            case "hv" -> Gtl_extend_Item.ETERNALBLUE_DREAM_HV_PROCESSOR_MAINFRAME;
            case "ev" -> Gtl_extend_Item.ETERNALBLUE_DREAM_EV_PROCESSOR_MAINFRAME;
            case "iv" -> Gtl_extend_Item.ETERNALBLUE_DREAM_IV_PROCESSOR_MAINFRAME;
            case "luv" -> Gtl_extend_Item.ETERNALBLUE_DREAM_LUV_PROCESSOR_MAINFRAME;
            case "zpm" -> Gtl_extend_Item.ETERNALBLUE_DREAM_ZPM_PROCESSOR_MAINFRAME;
            case "uv" -> Gtl_extend_Item.ETERNALBLUE_DREAM_UV_PROCESSOR_MAINFRAME;
            case "uhv" -> Gtl_extend_Item.ETERNALBLUE_DREAM_UHV_PROCESSOR_MAINFRAME;
            case "uev" -> Gtl_extend_Item.ETERNALBLUE_DREAM_UEV_PROCESSOR_MAINFRAME;
            case "uiv" -> Gtl_extend_Item.ETERNALBLUE_DREAM_UIV_PROCESSOR_MAINFRAME;
            case "uxv" -> Gtl_extend_Item.ETERNALBLUE_DREAM_UXV_PROCESSOR_MAINFRAME;
            case "opv" -> Gtl_extend_Item.ETERNALBLUE_DREAM_OPV_PROCESSOR_MAINFRAME;
            case "max" -> Gtl_extend_Item.ETERNALBLUE_DREAM_MAX_PROCESSOR_MAINFRAME;
            default -> throw new IllegalArgumentException("Unknown tier: " + tier);
        };
    }
}
