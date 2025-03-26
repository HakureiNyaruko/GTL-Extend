package org.qiuyeqaq.gtl_extend.common.items;

import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration.*;

import net.minecraft.world.item.Item;

import com.tterrag.registrate.util.entry.ItemEntry;

import org.qiuyeqaq.gtl_extend.common.data.GTL_Extend_CreativeModeTabs;

public class Gtl_extend_Item {

    static {
        REGISTRATE.creativeModeTab(() -> GTL_Extend_CreativeModeTabs.GTL_EX_GT_ITEM);
    }

    public static final ItemEntry<Item> ULTMATE_SINGULARITY = REGISTRATE.item("ultmate_singularity", Item::new)
            .lang("Ultimate Singularity")
            .register();

    public static void init() {}
}
