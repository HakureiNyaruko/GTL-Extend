package org.qiuyeqaq.gtl_extend.common.materials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static org.qiuyeqaq.gtl_extend.common.materials.GTL_Extend_Materials.*;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;

public class GTL_Extend_MaterialsBuilder {

    public static void init() {
        ETERNALBLUEDREAM = new Material.Builder(GTCEu.id("eternalbluedream"))
                .gem()
                .liquid(new FluidBuilder().temperature(1).state(FluidState.LIQUID))
                .element(GTL_Extend_Elements.ETERNALBLUEDREAM)
                .color(0x3F8EFF)
                .iconSet(BRIGHT)
                .flags(GENERATE_FRAME)
                .buildAndRegister();
    }
}
