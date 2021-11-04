package top.wemc.bakaapi.mysql;

import top.wemc.bakaapi.Main;
import top.wemc.bakaapi.Metrics;
import top.wemc.bakaapi.mysql.api.MysqlAPI;
import top.wemc.bakaapi.mysql.entity.ColumnEntity;
import top.wemc.bakaapi.mysql.entity.DataEntity;
import top.wemc.bakaapi.mysql.entity.TableEntity;
import top.wemc.bakaapi.mysql.enums.DataType;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @anthor WYH2004
 * @date 2021/11/03
 **/
public class MysqlManager {
    private static MysqlAPI mysqlAPI;
    private static Main instance = Main.getInstance();

    public static MysqlAPI getMysqlAPI() {
        return mysqlAPI;
    }

    @SuppressWarnings("unchecked")
    public static boolean regMysql(){
        if(instance.getConfig().getBoolean("mysql.enabled")){
            try{
                mysqlAPI = new MysqlAPIImpl();
                return true;
            }catch (Exception ex){
                instance.getLogger().info("§c未知错误");
                instance.getLogger().info((Supplier<String>) ex);
                return false;
            }
        }else {
            instance.getLogger().info("§c MySQL功能已禁用");
            return false;
        }
    }

    public static boolean isTableExists(String tableName){
        if (instance.getConfig().getBoolean("mysql.debug")){
            instance.getLogger().info("§e Cmd: isTableExists");
            instance.getLogger().info("§e 目标MySQL表: " + tableName);
            if (mysqlAPI.isTableExists(tableName)){
                instance.getLogger().info("§e MySQL表已存在");
                return true;
            }else {
                instance.getLogger().info("§c MySQL表不存在");
                return false;
            }
        }else {
            return mysqlAPI.isTableExists(tableName);
        }
    }

    public static boolean createTable(String tableName){
        TableEntity tableEntity = new TableEntity(tableName);
        if (instance.getConfig().getBoolean("mysql.debug")){
            instance.getLogger().info("§e Cmd: createTable");
            instance.getLogger().info("§e 新建MySQL表名称: " + tableName);
            if (mysqlAPI.createTable(tableEntity)){
                instance.getLogger().info("§e 创建MySQL表成功");
                return true;
            } else {
                instance.getLogger().info("§c 创建MySQL表失败");
                return false;
            }
        }else {
            return mysqlAPI.createTable(tableEntity);
        }
    }

    public static boolean removeTable(String tableName){
        if (instance.getConfig().getBoolean("mysql.debug")){
            instance.getLogger().info("§e Cmd: removeTable");
            instance.getLogger().info("§e 目标MySQL表: " + tableName);
            if (mysqlAPI.dropTable(tableName)){
                instance.getLogger().info("§e 删除MySQL表成功");
                return true;
            } else {
                instance.getLogger().info("§c 删除MySQL表失败");
                return false;
            }
        }else {
            return mysqlAPI.dropTable(tableName);
        }
    }

    public static boolean isColumnExists(String tableName, String columnName){
        if(instance.getConfig().getBoolean("mysql.debug")){
            instance.getLogger().info("§e Cmd: isColumnExists");
            instance.getLogger().info("§e 目标MySQL表: " + tableName);
            instance.getLogger().info("§e 目标MySQL列: " + columnName);
            if (mysqlAPI.isColumnExists(tableName, columnName)){
                instance.getLogger().info("§e MySQL列已存在 ");
                return true;
            }else {
                instance.getLogger().info("§c MySQL列不存在 ");
                return false;
            }
        }else {
            return mysqlAPI.isColumnExists(tableName, columnName);
        }
    }

    public static boolean addColumn(String tableName, String columnName, DataType dataType){
        ColumnEntity columnEntity = new ColumnEntity(columnName,new DataEntity(dataType));
        mysqlAPI.addColumn(tableName, columnEntity);
        if(instance.getConfig().getBoolean("mysql.debug")){
            instance.getLogger().info("§e Cmd: addColumn");
            instance.getLogger().info("§e 目标MySQL表: " + tableName);
            instance.getLogger().info("§e 新建MySQL列名称: " + columnName);
            instance.getLogger().info("§e 新建MySQL列类型: " + dataType);
            if (mysqlAPI.isColumnExists(tableName, columnName)){
                instance.getLogger().info("§e 创建MySQL列成功");
                return true;
            }else {
                instance.getLogger().info("§c 创建MySQL列失败");
                return false;
            }
        }else {
            return mysqlAPI.isColumnExists(tableName, columnName);
        }
    }

    public static boolean isRecordExists(String tableName, String pivotColumnName, String id){
        if(instance.getConfig().getBoolean("mysql.debug")){
            instance.getLogger().info("§e Cmd: isRecordExists");
            instance.getLogger().info("§e 目标MySQL表: " + tableName);
            instance.getLogger().info("§e 目标MySQL列: " + pivotColumnName);
            instance.getLogger().info("§e 目标MySQL记录id: " + id);
            if (mysqlAPI.isRecordExists(tableName, pivotColumnName, id)){
                instance.getLogger().info("§e MySQL数据已存在 ");
                return true;
            }else {
                instance.getLogger().info("§c MySQL数据不存在 ");
                return false;
            }
        }else {
            return mysqlAPI.isRecordExists(tableName, pivotColumnName, id);
        }
    }

    public static boolean insertData(String tableName, Map<String, String> dataMap){
        if(instance.getConfig().getBoolean("mysql.debug")){
            instance.getLogger().info("§e Cmd: insertData");
            instance.getLogger().info("§e 目标MySQL表: " + tableName);
            if (mysqlAPI.insertData(tableName, dataMap)){
                instance.getLogger().info("§e MySQL数据创建成功 ");
                return true;
            }else {
                instance.getLogger().info("§c MySQL数据创建失败 ");
                return false;
            }
        }else {
            return mysqlAPI.insertData(tableName, dataMap);
        }
    }

    public static boolean updateData(String tableName, String pivotColumnName, String primaryColumnName, String id, String value){
        if(instance.getConfig().getBoolean("mysql.debug")){
            instance.getLogger().info("§e Cmd: updateData");
            instance.getLogger().info("§e 目标MySQL表: " + tableName);
            instance.getLogger().info("§e 目标MySQL列: " + pivotColumnName);
            instance.getLogger().info("§e 目标MySQL数据列: " + primaryColumnName);
            instance.getLogger().info("§e 目标MySQL记录id: " + id);
            instance.getLogger().info("§e 目标MySQL更新值: " + value);
            if (mysqlAPI.updateData(tableName, pivotColumnName, primaryColumnName, id, value)){
                instance.getLogger().info("§e MySQL数据更新成功");
                return true;
            } else {
                instance.getLogger().info("§c MySQL数据更新失败");
                return false;
            }
        }else {
            return mysqlAPI.updateData(tableName, pivotColumnName, primaryColumnName, id, value);
        }
    }

    public static Map<String, String> getRecordFromTable(String tableName, String primaryColumnName, String id){
        Map<String, String> mysqlData = mysqlAPI.getRecordFromTable(tableName, primaryColumnName, id);
        if(instance.getConfig().getBoolean("mysql.debug")){
            instance.getLogger().info("§e Cmd: getRecordFromTable");
            instance.getLogger().info("§e 目标MySQL表: " + tableName);
            instance.getLogger().info("§e 目标MySQL列: " + primaryColumnName);
            instance.getLogger().info("§e 目标MySQL记录id: " + id);
        }
        return mysqlData;
    }

}
