package org.qiuyeqaq.gtl_extend.common.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

public class GTL_Extend_Materials {

    public static Material ETERNALBLUEDREAM;

    public GTL_Extend_Materials() {}

    public static void init() {
        GTL_Extend_Elements.init(); // 先初始化元素
        GTL_Extend_MaterialsBuilder.init(); // 再初始化材料
    }
}
