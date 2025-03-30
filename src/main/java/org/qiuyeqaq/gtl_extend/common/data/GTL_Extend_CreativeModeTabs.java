package org.qiuyeqaq.gtl_extend.common.data;

import static com.gregtechceu.gtceu.common.data.GTCreativeModeTabs.RegistrateDisplayItemsGenerator;

import org.qiuyeqaq.gtl_extend.Gtl_extend;
import org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration;
import org.qiuyeqaq.gtl_extend.common.blocks.Gtl_extend_Blocks;
import org.qiuyeqaq.gtl_extend.common.items.Gtl_extend_Item;
import org.qiuyeqaq.gtl_extend.common.multiblock.MultiBlockMachine;

import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.world.item.CreativeModeTab;

public class GTL_Extend_CreativeModeTabs {

    public static RegistryEntry<CreativeModeTab> MACHINES_ITEM;
    public static RegistryEntry<CreativeModeTab> BLOCKS_ITEM;
    public static RegistryEntry<CreativeModeTab> GTL_EX_GT_ITEM;

    static {
        MACHINES_ITEM = GTLEXRegistration.REGISTRATE.defaultCreativeTab("machines_item", (builder) -> {
            builder.displayItems(new RegistrateDisplayItemsGenerator("machines_item", GTLEXRegistration.REGISTRATE))
                    .icon(MultiBlockMachine.SUPERFLUID_GENERAL_ENERGY_FURNACE::asStack)
                    .title(GTLEXRegistration.REGISTRATE.addLang("itemGroup", Gtl_extend.id("machines_item"), "GTL Extend Machines Items"))
                    .build();
        }).register();

        BLOCKS_ITEM = GTLEXRegistration.REGISTRATE.defaultCreativeTab("blocks_item", (builder) -> {
            builder.displayItems(new RegistrateDisplayItemsGenerator("blocks_item", GTLEXRegistration.REGISTRATE))
                    .icon(Gtl_extend_Blocks.DIMENSION_CORE::asStack)
                    .title(GTLEXRegistration.REGISTRATE.addLang("itemGroup", Gtl_extend.id("blocks_item"), "GTL Extend Blocks Items"))
                    .build();
        }).register();

        GTL_EX_GT_ITEM = GTLEXRegistration.REGISTRATE.defaultCreativeTab("gtl_ex_gt_item", (builder) -> {
            builder.displayItems(new RegistrateDisplayItemsGenerator("gtl_ex_gt_item", GTLEXRegistration.REGISTRATE))
                    .icon(Gtl_extend_Item.FOREVER::asStack)
                    .title(GTLEXRegistration.REGISTRATE.addLang("itemGroup", Gtl_extend.id("gtl_ex_gt_item"), "GTL Extend GT Items"))
                    .build();
        }).register();
    }

    public GTL_Extend_CreativeModeTabs() {}

    public static void init() {}
}
