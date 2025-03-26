package org.qiuyeqaq.gtl_extend.common.multiblock;

import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.function.BiConsumer;

public class GTL_Extend_MultiBlockMachineModifier {

    public static final BiConsumer<IMultiController, List<Component>> MAX_PARALLEL = getMaxParallelConsumer(Long.MAX_VALUE);

    // 通用的 BiConsumer 工厂方法
    public static BiConsumer<IMultiController, List<Component>> getMaxParallelConsumer(long parallelNumber) {
        return (controller, components) -> {
            if (controller.isFormed()) {
                components.add(
                        Component.translatable("gtceu.multiblock.parallel",
                                Component.literal(String.valueOf(parallelNumber))
                                        .withStyle(ChatFormatting.DARK_PURPLE) // 数值颜色
                ).withStyle(ChatFormatting.GRAY) // 整体文本颜色
                );
            }
        };
    }
}
