package org.qiuyeqaq.gtl_extend.config;

import org.qiuyeqaq.gtl_extend.Gtl_extend;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id = Gtl_extend.MODID)
public class GTLExtendConfigHolder {

    private static final Object LOCK = new Object();
    public static GTLExtendConfigHolder INSTANCE;
    @Configurable
    @Configurable.Comment("开启永恒蓝梦和蓝梦主机合成表的注册，注意这可能会影响游戏平衡")
    public boolean enableInfinityDreamAndDreamHostCrafting = false;
    @Configurable
    @Configurable.Comment("开启黑洞物质解压器实体渲染,当延迟过大时关闭此项")
    public boolean enableBlackHoleMatterDecompressor = true;
    @Configurable
    @Configurable.Comment("开启超维度发电机")
    public boolean enableHyperDimensionalPower = true;

    public static void init() {
        synchronized (LOCK) {
            if (INSTANCE == null) {
                INSTANCE = Configuration.registerConfig(GTLExtendConfigHolder.class, ConfigFormats.yaml())
                        .getConfigInstance();
            }
        }
    }
}
