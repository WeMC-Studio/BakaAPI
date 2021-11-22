package top.wemc.bakaapi.config;

/**
 * @author WYH2004
 * @date 2021/11/08
 **/
public class MysqlConfig extends ConfigManager {
    public static MysqlConfig config;

    public MysqlConfig() {
        super("MysqlConfig");
        config = this;
    }

    @Config(path = "Mysql")
    public static boolean mysqlEnabled = true;

    @Config(path = "DebugMode")
    public static boolean debugMode = false;

    @Config(path = "database.name")
    public static String name = "database";

    @Config(path = "database.host")
    public static String host = "localhost";

    @Config(path = "database.port")
    public static int port = 3306;

    @Config(path = "database.username")
    public static String username = "root";

    @Config(path = "database.password")
    public static String password = "password";

    @Config(path = "database.useUnicode")
    public static boolean useUnicode = true;

    @Config(path = "database.characterEncoding")
    public static String characterEncoding = "utf8";

    @Config(path = "datasourse.connectionTimeout")
    public static int connectionTimeout = 2000;

    @Config(path = "datasourse.idleTimeout")
    public static int idleTimeout = 60000;

    @Config(path = "datasourse.maximumPoolSize")
    public static int maximumPoolSize = 10;
}
