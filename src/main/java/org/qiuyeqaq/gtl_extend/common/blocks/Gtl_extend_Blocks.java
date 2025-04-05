package org.qiuyeqaq.gtl_extend.common.blocks;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import org.qiuyeqaq.gtl_extend.Gtl_extend;
import org.qiuyeqaq.gtl_extend.common.data.GTL_Extend_CreativeModeTabs;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.qiuyeqaq.gtl_extend.api.registries.GTLEXRegistration.REGISTRATE;

public class Gtl_extend_Blocks {

    /**
     * 创建带有自定义属性的技术类方块（如机器外壳）
     *
     * @param name           方块注册名称（无需命名空间）
     * @param texture        纹理路径（需包含子路径，如 "block/..."）
     * @param renderType     渲染类型（例如透明或剪切）
     * @param baseProperties 基础属性参考方块
     * @return 注册完成的方块条目
     *         () -> RenderType::translucent // 替换为透明渲染
     *         () -> RenderType::cutoutMipped //不透明
     */

    public static Map<Integer,Supplier<Block>> crmap = new HashMap<>();
    public static final BlockEntry<Block> DIMENSION_CORE = createCasingBlock(
            "dimension_core",
            Gtl_extend.id("block/dimension_core"),
            () -> RenderType::solid,
            () -> Blocks.NETHERITE_BLOCK,
            crmap, 2);
    public static final BlockEntry<Block> VOID_WORLD_BLOCK = createCasingBlock(
            "void_world_block",
            Gtl_extend.id("block/void_world_block"),
            () -> RenderType::cutoutMipped,
            () -> Blocks.NETHERITE_BLOCK,
            crmap,1);

    public static void init() {}

    public static BlockEntry<Block> createCasingBlock(
                                                      String name,
                                                      ResourceLocation texture,
                                                      Supplier<Supplier<RenderType>> renderType,
                                                      NonNullSupplier<? extends Block> baseProperties) {                              // 新增参数：层级标识) {
        BlockEntry<Block> blockEntry = REGISTRATE.block(name, Block::new)
                .initialProperties(baseProperties)
                .properties(p -> p
                        .strength(4.0f, 1000.0f)
                        .sound(SoundType.METAL)
                        .noOcclusion()
                        .isValidSpawn((state, level, pos, entity) -> false)
                        .requiresCorrectToolForDrops())
                .addLayer(renderType)
                .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().getExistingFile(
                        Gtl_extend.id("block/" + ctx.getName()) // 直接加载自定义模型
                )))
                .tag(GTToolType.WRENCH.harvestTags.get(0), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), "gtl_extend:block/" + name))
                .build()
                .register();
        REGISTRATE.setCreativeTab(blockEntry, GTL_Extend_CreativeModeTabs.BLOCKS_ITEM);
        return blockEntry;
    }
    public static BlockEntry<Block> createCasingBlock(
            String name,
            ResourceLocation texture,
            Supplier<Supplier<RenderType>> renderType,
            NonNullSupplier<? extends Block> baseProperties,
            Map<Integer, Supplier<Block>> targetMap,
            int tier) {
        BlockEntry<Block> blockEntry = createCasingBlock(name, texture, renderType, baseProperties);
        targetMap.put(tier, blockEntry::get);
        return blockEntry;
    }

}
