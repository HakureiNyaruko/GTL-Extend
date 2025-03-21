package org.qiuyeqaq.gtl_extend.config;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;
import org.qiuyeqaq.gtl_extend.Gtl_extend;

@Config(id = Gtl_extend.MODID)
public class GTLExtendConfigHolder {

    public static GTLExtendConfigHolder INSTANCE;
    private static final Object LOCK = new Object();

    public static void init() {
        synchronized (LOCK) {
            if (INSTANCE == null) {
                INSTANCE = Configuration.registerConfig(GTLExtendConfigHolder.class, ConfigFormats.yaml())
                        .getConfigInstance();
            }
        }
    }

    @Configurable
    @Configurable.Comment("开启永恒蓝梦和蓝梦主机合成表的注册，注意这可能会影响游戏平衡")
    public boolean enableInfinityDreamAndDreamHostCrafting = false;
    @Configurable
    @Configurable.Comment("开启黑洞物质解压器")
    public boolean enableBlackHoleMatterDecompressor = true;
    @Configurable
    @Configurable.Comment("开启超维度发电机")
    public boolean enableHyperDimensionalPower = true;
}
