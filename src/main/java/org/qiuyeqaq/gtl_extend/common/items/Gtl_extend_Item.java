package org.qiuyeqaq.gtl_extend.common.items;

import net.minecraft.world.item.Item;

import com.tterrag.registrate.util.entry.ItemEntry;
import org.qiuyeqaq.gtl_extend.common.data.GTL_Extend_CreativeModeTabs;

import static org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration.REGISTRATE;

public class Gtl_extend_Item {

    public static final ItemEntry<Item> FOREVER = REGISTRATE.item("forever", Item::new)
            .lang("Forever")
            .register();
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_LV_PROCESSOR_MAINFRAME = registerMainframe("lv");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_MV_PROCESSOR_MAINFRAME = registerMainframe("mv");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_HV_PROCESSOR_MAINFRAME = registerMainframe("hv");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_EV_PROCESSOR_MAINFRAME = registerMainframe("ev");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_IV_PROCESSOR_MAINFRAME = registerMainframe("iv");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_LUV_PROCESSOR_MAINFRAME = registerMainframe("luv");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_ZPM_PROCESSOR_MAINFRAME = registerMainframe("zpm");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_UV_PROCESSOR_MAINFRAME = registerMainframe("uv");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_UHV_PROCESSOR_MAINFRAME = registerMainframe("uhv");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_UEV_PROCESSOR_MAINFRAME = registerMainframe("uev");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_UIV_PROCESSOR_MAINFRAME = registerMainframe("uiv");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_UXV_PROCESSOR_MAINFRAME = registerMainframe("uxv");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_OPV_PROCESSOR_MAINFRAME = registerMainframe("opv");
    public static final ItemEntry<Item> ETERNALBLUE_DREAM_MAX_PROCESSOR_MAINFRAME = registerMainframe("max");

    static {
        REGISTRATE.creativeModeTab(() -> GTL_Extend_CreativeModeTabs.GTL_EX_GT_ITEM);
    }
    // 继续添加剩余层级（ev, iv, luv, zpm, uv, uhv, uev, uiv, uxv, opv, max）

    // 辅助方法：批量注册
    private static ItemEntry<Item> registerMainframe(String tier) {
        return REGISTRATE.item("eternalbluedream_" + tier + "_processor_mainferame", Item::new)
                .lang("Eternal Blue Dream " + tier.toUpperCase() + " Processor Mainframe")
                .register();
    }

    public static void init() {}
}
