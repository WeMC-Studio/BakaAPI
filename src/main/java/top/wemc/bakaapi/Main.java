package top.wemc.bakaapi;

import org.bukkit.plugin.java.JavaPlugin;
import top.wemc.bakaapi.mysql.MysqlAPIImpl;
import top.wemc.bakaapi.mysql.MysqlManager;
import top.wemc.bakaapi.mysql.api.MysqlAPI;

/**
 * @anthor WYH2004
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
        if (getConfig().getBoolean("mysql.enabled")){
            Metrics metrics = new Metrics(this, 11311);
        }
        getLogger().info("§e插件已启用");
    }

    @Override
    public void onDisable() {
        getLogger().info("§e插件已关闭");
    }

    public void regConfig(){
        getConfig().options().copyDefaults(true);
        getConfig().addDefault("mysql.enabled", true);
        getConfig().addDefault("mysql.debug", false);
        getConfig().addDefault("database.username", "root");
        getConfig().addDefault("database.password", "password");
        getConfig().addDefault("database.host", "localhost");
        getConfig().addDefault("database.port", 3306);
        getConfig().addDefault("database.name", "wemc");
        getConfig().addDefault("database.useUnicode", true);
        getConfig().addDefault("database.characterEncoding", "utf8");
        getConfig().addDefault("database.useSSL", true);

        getConfig().addDefault("datasourse.connectionTimeout", 2000);
        getConfig().addDefault("datasourse.idleTimeout", 60000);
        getConfig().addDefault("datasourse.maximumPoolSize", 10);

        saveConfig();
    }

    public static void reloadConfigs() {
        Main.getInstance().reloadConfig();
    }

}
