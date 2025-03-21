package org.qiuyeqaq.gtl_extend.common.blocks;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GTModels;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import org.qiuyeqaq.gtl_extend.Gtl_extend;
import org.qiuyeqaq.gtl_extend.common.data.GTL_Extend_CreativeModeTabs;

import java.util.function.Supplier;

import static org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration.REGISTRATE;

public class Gtl_extend_Blocks {

    public static void init() {}

    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }

    @SuppressWarnings("all")
    public static BlockEntry<Block> createCasingBlock(String name,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        BlockEntry<Block> blockEntry = REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, entity) -> false))
                .addLayer(type)
                .blockstate(GTModels.cubeAllModel(name, texture))
                .tag(GTToolType.WRENCH.harvestTags.get(0), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();

        REGISTRATE.setCreativeTab(blockEntry, GTL_Extend_CreativeModeTabs.BLOCKS_ITEM);
        return blockEntry;
    }

    @SuppressWarnings("all")
    public static final BlockEntry<Block> DIMENSION_CORE = createCasingBlock("dimension_core", Gtl_extend.id("block/dimension_core"));
}
