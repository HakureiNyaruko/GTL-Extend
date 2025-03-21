package org.qiuyeqaq.gtl_extend.common.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;

import org.qiuyeqaq.gtl_extend.config.GTLExtendConfigHolder;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static org.qiuyeqaq.gtl_extend.common.materials.GTL_Extend_Materials.*;

public class GTL_Extend_MaterialsBuilder {

    public static void init() {
        ETERNALBLUEDREAM = GTLExtendConfigHolder.INSTANCE.enableInfinityDreamAndDreamHostCrafting ? new Material.Builder(GTCEu.id("eternalbluedream"))
                .gem()
                .liquid(new FluidBuilder().temperature(1).customStill())
                .element(GTL_Extend_Elements.ETERNALBLUEDREAM)
                .ore()
                .color(0x3F8EFF)
                .secondaryColor(0x297CFF)
                .iconSet(BRIGHT)
                .flags(GENERATE_FRAME)
                .buildAndRegister() : null;
    }
}
