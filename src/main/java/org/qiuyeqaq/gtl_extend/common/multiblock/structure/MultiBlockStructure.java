package org.qiuyeqaq.gtl_extend.common.multiblock.structure;

import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;

public class MultiBlockStructure {

    public MultiBlockStructure() {}

    public static final FactoryBlockPattern GENERAL_ENERGY_FURNACE;

    static {
        GENERAL_ENERGY_FURNACE = FactoryBlockPattern.start()
                .aisle("aaaaaaa", "ccccccc", "ccccccc", "ccccccc", "eeeeeee", "ccccccc", "ccccccc", "ccccccc", "aaaaaaa")
                .aisle("aaaaaaa", "c     c", "c     c", "c     c", "e     e", "c     c", "c     c", "c     c", "aaaaaaa")
                .aisle("aaaaaaa", "c     c", "c     c", "c fff c", "e fff e", "c fff c", "c     c", "c     c", "aaaaaaa")
                .aisle("aaaaaaa", "c     c", "c     c", "c fff c", "e f f e", "c fff c", "c     c", "c     c", "aaaaaaa")
                .aisle("aaaaaaa", "c     c", "c     c", "c fff c", "e fff e", "c fff c", "c     c", "c     c", "aaaaaaa")
                .aisle("aaaaaaa", "c     c", "c     c", "c     c", "e     e", "c     c", "c     c", "c     c", "aaaaaaa")
                .aisle("aaa~aaa", "ccccccc", "ccccccc", "ccccccc", "eeeeeee", "ccccccc", "ccccccc", "ccccccc", "aaaaaaa");

    }
}
