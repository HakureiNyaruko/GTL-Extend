package org.qiuyeqaq.gtl_extend.client.renderer.machine;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.client.renderer.machine.IControllerRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.WorkableCasingMachineRenderer;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.SimpleModelState;
import net.minecraftforge.client.model.data.ModelData;

import com.lowdragmc.lowdraglib.client.bakedpipeline.FaceQuad;
import com.lowdragmc.lowdraglib.client.model.ModelFactory;

import org.jetbrains.annotations.Nullable;

import org.qiuyeqaq.gtl_extend.Gtl_extend;
import org.qiuyeqaq.gtl_extend.common.multiblock.electric.BlackHoleMatterDecompressor;

import java.util.List;
import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Transformation;
import org.gtlcore.gtlcore.client.ClientUtil;
import org.joml.Quaternionf;

public class BlackHoleMatterDecompressor_Render extends WorkableCasingMachineRenderer implements IControllerRenderer {

    // 黑洞核心模型
    private static final ResourceLocation EVENT_HORIZON_MODEL = Gtl_extend.id("textures/rendered/black_hole");
    // 吸积盘模型
    private static final ResourceLocation ACCRETION_DISK_MODEL = Gtl_extend.id("textures/rendered/accretion_disk");
    // 光晕纹理
    private static final ResourceLocation GLOW_TEXTURE = Gtl_extend.id("textures/entity/black_hole_glow.png");
    // 吸积盘层数
    private static final int DISK_LAYERS = 3;

    public BlackHoleMatterDecompressor_Render() {
        super(GTCEu.id("block/casings/hpca/high_power_casing"), GTCEu.id("block/multiblock/cosmos_simulation"));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(BlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int combinedLight, int combinedOverlay) {
        if (blockEntity instanceof IMachineBlockEntity machineBlockEntity &&
                machineBlockEntity.getMetaMachine() instanceof BlackHoleMatterDecompressor machine &&
                machine.isActive()) { // 模式变量简化
            float tick = machine.getOffsetTimer() + partialTicks;

            // 调整渲染中心点
            poseStack.pushPose();
            adjustPositionByFacing(machine.getFrontFacing(), poseStack);

            // 渲染事件视界（黑洞核心）
            renderEventHorizon(poseStack, buffer);

            // 渲染吸积盘
            renderAccretionDisk(tick, poseStack, buffer);

            // 渲染光晕效果
            renderGravitationalGlow(tick, poseStack, buffer);

            poseStack.popPose();
        }
    }

    // 根据机器朝向调整位置
    private void adjustPositionByFacing(Direction facing, PoseStack poseStack) {
        double x = 0.5, y = 54.5, z = 0.5;
        switch (facing) {
            case NORTH -> z = 39.5;
            case SOUTH -> z = -39.5;
            case WEST -> x = 39.5;
            case EAST -> x = -39.5;
        }
        poseStack.translate(x, y, z);
    }

    // 事件视界渲染（黑洞核心）
    private void renderEventHorizon(PoseStack poseStack, MultiBufferSource buffer) {
        poseStack.pushPose();
        poseStack.scale(0.8F, 0.8F, 0.8F); // 80% 原始大小

        // 完全黑暗的光照参数
        int packedLight = LightTexture.pack(0, 0);

        ClientUtil.modelRenderer().renderModel(
                poseStack.last(),
                buffer.getBuffer(RenderType.solid()),
                null,
                ClientUtil.getBakedModel(EVENT_HORIZON_MODEL),
                0.1f, 0.1f, 0.1f, // 深黑色
                packedLight,
                OverlayTexture.NO_OVERLAY,
                ModelData.EMPTY,
                RenderType.solid());
        poseStack.popPose();
    }

    // 吸积盘渲染
    private void renderAccretionDisk(float tick, PoseStack poseStack, MultiBufferSource buffer) {
        for (int layer = 0; layer < DISK_LAYERS; layer++) {
            poseStack.pushPose();

            // --- 计算层级属性 ---
            final float scale = 1.2f + layer * 0.4f;         // 尺寸递增
            final float rotationSpeed = 2.0f / (layer + 1);  // 旋转速度递减
            final float thickness = 0.05f - layer * 0.01f;   // 厚度递减

            // --- 应用变换（严格顺序）---
            // 1. 层级偏移（基于厚度）
            poseStack.translate(0, thickness * layer, 0);
            // 2. 动态旋转（基于速度和时间）
            poseStack.mulPose(new Quaternionf().rotationY((tick * rotationSpeed) % 360));
            // 3. 缩放（确保在偏移和旋转后应用）
            poseStack.scale(scale, scale, scale); // 明确使用 scale 变量

            // 渲染吸积盘模型
            ClientUtil.modelRenderer().renderModel(
                    poseStack.last(),
                    buffer.getBuffer(RenderType.translucent()),
                    null,
                    ClientUtil.getBakedModel(ACCRETION_DISK_MODEL),
                    1.0f, 0.3f, 0.1f, // 橙红色
                    LightTexture.FULL_BRIGHT, // 自发光
                    OverlayTexture.NO_OVERLAY,
                    ModelData.EMPTY,
                    RenderType.translucent());

            poseStack.popPose();
        }
    }

    // 引力光晕效果
    private void renderGravitationalGlow(float tick, PoseStack poseStack, MultiBufferSource buffer) {
        VertexConsumer glowBuffer = buffer.getBuffer(RenderType.entityTranslucentCull(GLOW_TEXTURE));

        // 定义光晕的包围盒（中心对齐，尺寸1x1x1）
        AABB glowAABB = new AABB(-0.5, -0.5, -0.5, 0.5, 0.5, 0.5);

        // 创建 ModelState（假设 SimpleModelState 可用）
        ModelState modelState = new SimpleModelState(Transformation.identity(), false);

        // 基础变换
        poseStack.pushPose();
        poseStack.scale(2.5F, 2.5F, 2.5F); // 光晕范围

        // 动态脉动效果
        float pulse = (float) (Math.sin(tick * 0.2) * 0.1 + 1.0);
        poseStack.scale(pulse, pulse, pulse);

        // 渲染光晕平面
        BakedQuad glowQuad = FaceQuad.bakeFace(
                glowAABB,               // AABB
                Direction.UP,           // 面方向
                ModelFactory.getBlockSprite(GLOW_TEXTURE), // 纹理
                modelState,             // ModelState
                0x80FFFFFF,             // ARGB颜色（半透明白色）
                OverlayTexture.NO_OVERLAY, // 覆盖纹理
                false,                  // 不应用Diffuse Lighting
                false                   // 不翻转面
        );
        // 将 BakedQuad 写入 VertexConsumer
        glowBuffer.putBulkData(
                poseStack.last(),       // Pose
                glowQuad,               // BakedQuad
                1.0f, 1.0f, 1.0f,      // RGB颜色乘数（此处保持原色）
                LightTexture.FULL_BRIGHT, // 光照
                OverlayTexture.NO_OVERLAY // 覆盖纹理
        );

        poseStack.popPose();
    }

    // 注册所需模型
    @Override
    public void onAdditionalModel(Consumer<ResourceLocation> registry) {
        super.onAdditionalModel(registry);
        registry.accept(EVENT_HORIZON_MODEL);
        registry.accept(ACCRETION_DISK_MODEL);
        registry.accept(GLOW_TEXTURE);
    }

    // 多方块部件渲染（与原代码类似）
    @Override
    @OnlyIn(Dist.CLIENT)
    public void renderPartModel(List<BakedQuad> list, IMultiController controller, IMultiPart part,
                                Direction side, @Nullable Direction face, RandomSource random,
                                Direction modelFacing, ModelState modelState) {
        if (face != null && modelFacing != null) {
            list.add(FaceQuad.bakeFace(
                    modelFacing,
                    ModelFactory.getBlockSprite(GTCEu.id("block/casings/event_horizon_casing")),
                    modelState, // 使用传入的ModelState
                    0xFFFFFFFF, // 白色不透明
                    OverlayTexture.NO_OVERLAY,
                    true,  // 应用Diffuse
                    false  // 不翻转
            ));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasTESR(BlockEntity blockEntity) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isGlobalRenderer(BlockEntity blockEntity) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getViewDistance() {
        return 1024; // 扩大渲染距离
    }
}
