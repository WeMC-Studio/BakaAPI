package top.wemc.bakaapi;

import org.bukkit.plugin.java.JavaPlugin;
import top.wemc.bakaapi.config.DefaultConfig;
import top.wemc.bakaapi.config.MysqlConfig;

/**
 * @author WYH2004
 * @date 2021/11/01
 **/
public class Main extends JavaPlugin {
    private static Main instance;

    public static Main getInstance(){
        return instance;
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onEnable() {
        instance = this;
        regConfig();
//        if (MysqlConfig.mysqlEnabled){
//            MysqlImpl.connectMysql();
//        }
        getLogger().info("§e插件已启用");
    }

    @Override
    public void onDisable() {
        getLogger().info("§e插件已关闭");
    }

    public void regConfig(){
        DefaultConfig.defaultConfig();
        new MysqlConfig();

        saveConfig();
    }

    public static void reloadConfigs() {
        Main.getInstance().reloadConfig();
    }

}
