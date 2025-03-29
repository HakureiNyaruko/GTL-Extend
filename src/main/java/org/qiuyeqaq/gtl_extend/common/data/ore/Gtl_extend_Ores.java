package org.qiuyeqaq.gtl_extend.common.data.ore;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.GTLayerPattern;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.common.data.GTOres;

import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import org.qiuyeqaq.gtl_extend.config.GTLExtendConfigHolder;

import static org.qiuyeqaq.gtl_extend.common.materials.GTL_Extend_Materials.ETERNALBLUEDREAM;

@SuppressWarnings("unused")
public class Gtl_extend_Ores extends GTOres {

    public static GTOreDefinition Eternal_Blue_Dream_Vein;

    static {
        if (GTLExtendConfigHolder.INSTANCE.enableInfinityDreamAndDreamHostCrafting) {
            Eternal_Blue_Dream_Vein = create(GTCEu.id("eternal_blue_dream_vein"), (vein) -> {
                vein
                        .clusterSize(UniformInt.of(25, 150))
                        .density(0.3F)
                        .weight(25)
                        .layer(WorldGenLayers.STONE)
                        .heightRangeUniform(1, 40)
                        .biomes(BiomeTags.IS_OVERWORLD)
                        .layeredVeinGenerator((generator) -> generator
                                .withLayerPattern(() -> GTLayerPattern.builder(OVERWORLD_RULES)
                                        .layer((l) -> l.weight(10).mat(ETERNALBLUEDREAM)
                                                .size(20, 50))
                                        .build()));
            });
        }
    };

    public static void init() {}
}
