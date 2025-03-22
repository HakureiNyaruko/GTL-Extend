package org.qiuyeqaq.gtl_extend.common.machines.machines;

import org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration;
import org.qiuyeqaq.gtl_extend.common.data.GTL_Extend_CreativeModeTabs;

public class GTL_Extend_SmallMachines {

    public static void init() {}

    static {
        GTLEXRegistration.REGISTRATE.creativeModeTab(() -> GTL_Extend_CreativeModeTabs.MACHINES_ITEM);
    }
}
