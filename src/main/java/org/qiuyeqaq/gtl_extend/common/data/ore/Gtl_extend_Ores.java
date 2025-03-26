package org.qiuyeqaq.gtl_extend.common.data.ore;

import com.gregtechceu.gtceu.api.data.worldgen.*;
import com.gregtechceu.gtceu.common.data.GTOres;

import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import org.qiuyeqaq.gtl_extend.Gtl_extend;
import org.qiuyeqaq.gtl_extend.common.materials.GTL_Extend_Materials;
import org.qiuyeqaq.gtl_extend.config.GTLExtendConfigHolder;

@SuppressWarnings("unused")
public class Gtl_extend_Ores extends GTOres {

    public static void init() {}

    public static final GTOreDefinition Eternal_Blue_Dream_Vein = GTLExtendConfigHolder.INSTANCE.enableInfinityDreamAndDreamHostCrafting ?
            create(Gtl_extend.id("eternal_blue_dream_vein"), (vein) -> {
                vein
                        .clusterSize(UniformInt.of(25, 150))
                        .density(0.3F)
                        .weight(25)
                        .layer(WorldGenLayers.STONE)
                        .heightRangeUniform(1, 50)
                        .biomes(BiomeTags.IS_OVERWORLD)
                        .layeredVeinGenerator((generator) -> generator
                                .withLayerPattern(() -> GTLayerPattern.builder(OVERWORLD_RULES)
                                        .layer((l) -> l.weight(3).mat(GTL_Extend_Materials.ETERNALBLUEDREAM).size(2, 4))
                                        .build()))
                        .surfaceIndicatorGenerator((indicator) -> indicator
                                .surfaceRock(GTL_Extend_Materials.ETERNALBLUEDREAM));
            }) : null;
}
