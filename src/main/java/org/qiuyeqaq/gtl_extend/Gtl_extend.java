package org.qiuyeqaq.gtl_extend;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.gtlcore.gtlcore.GTLCore;
import org.gtlcore.gtlcore.utils.StorageManager;
import org.qiuyeqaq.gtl_extend.client.ClientProxy;
import org.qiuyeqaq.gtl_extend.common.CommonProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Mod(Gtl_extend.MODID)
public class Gtl_extend {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "gtl_extend",
            NAME = "GTL Extend";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static StorageManager STORAGE_INSTANCE = new StorageManager();

    public Gtl_extend() {
        Gtl_extend.init();
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(GTLCore::worldTick);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static void init() {}

    public static void worldTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.START && event.side.isServer()) {
            STORAGE_INSTANCE = StorageManager.getInstance(Objects.requireNonNull(event.level.getServer()));
        }
    }
}
