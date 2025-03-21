package org.qiuyeqaq.gtl_extend;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import org.qiuyeqaq.gtl_extend.client.ClientProxy;
import org.qiuyeqaq.gtl_extend.common.CommonProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Gtl_extend.MODID)
public class Gtl_extend {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "gtl_extend",
            NAME = "GTL Extend";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public Gtl_extend() {
        Gtl_extend.init();
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }

    public static void init() {}

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }
}
