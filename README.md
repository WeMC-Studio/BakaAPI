# BakaAPI
## MySQL API
### MysqlAPI实现类
`MysqlAPI mysqlAPI = new MysqlAPIImpl();`  
### 创建表  
##### 新建一个列对象，传入列名称、数据类型  
`ColumnEntity uuid = new ColumnEntity("uuid", new DataEntity(DataType.VARCHAR, "(200)"));`  
##### 另一个新建列对象的方法，可以只传入列名称，数据类型默认是TEXT  
`ColumnEntity playerName = new ColumnEntity("player_name");`  
##### 新建一个表对象，传入表名称、id列名称（可选）、所有的列对象（可无限添加） 
`TableEntity playerDataTable = new TableEntity("lottery_player_data", "uuid", uuid, playerName);`
##### 使用api创建表，直接传入表对象
`mysqlAPI.createTable(playerDataTable);`

### 查询与添加  
```java
Player player = event.getPlayer();
// 从主类获取MysqlAPI对象，我这里省略了主类的代码
MysqlAPI mysqlAPI = Main.getInstance().getMysqlAPI();
String playerUuid = player.getUniqueId().toString();
// 调用isRecordExists方法查询玩家数据
if (!mysqlAPI.isRecordExists("lottery_player_data", "uuid", playerUuid)) {
    // 插入数据需要将数据全部做成一个map
    Map<String, String> data = new HashMap<>();
    data.put("uuid", playerUuid);
    data.put("player_name", player.getName());
    mysqlAPI.insertData("lottery_player_data", data);
}
```