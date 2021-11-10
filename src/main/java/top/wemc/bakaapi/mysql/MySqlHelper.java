package top.wemc.bakaapi.mysql;

import top.wemc.bakaapi.config.MysqlConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WYH2004
 * @date 2021/11/09
 **/
public class MySqlHelper {
    public static String driver,url,userName,password;
    public static Connection connection;
    public static Statement st;
    public static PreparedStatement pst;
    public static ResultSet rs;

    static {
        try{
            url = "jdbc:mysql://" + MysqlConfig.host + ":" + MysqlConfig.port + "/" + MysqlConfig.name + "?serverTimezone=UTC";
            userName = MysqlConfig.username;
            password = MysqlConfig.password;
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("[BakaAPI-MySQL] MySQL驱动加载成功!");
        } catch (ClassNotFoundException ex) {
            System.out.println("[BakaAPI-MySQL] MySQL驱动加载失败!");
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        if(connection != null){
            return connection;
        }
        connection = DriverManager.getConnection(url,userName,password);
        return connection;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static Statement getStatement() throws SQLException {
        if (st != null){
            return st;
        }
        if (connection != null){
            getConnection();
        }
        st=connection.createStatement();
        return st;
    }

    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        if (pst != null){
            return pst;
        }
        if (connection != null){
            getConnection();
        }
        pst = connection.prepareStatement(sql);
        return pst;
    }

    /**
     * 执行增 删 改操作
     * @param sql
     * @param parameters
     * @throws SQLException
     */
    public static void execute(String sql, Object ...parameters) throws SQLException {
        try{
            if (parameters.length <= 0){
                getConnection();
                getStatement();
                connection.setAutoCommit(false);
                st.execute(sql);
                connection.commit();
            } else {
                getConnection();
                getPreparedStatement(sql);
                connection.setAutoCommit(false);
                for (int i=0; i<parameters.length; i++){
                    pst.setObject(i+1,parameters[i]);
                }
                pst.execute();
                connection.commit();
            }
        }catch (SQLException ex){
            connection.rollback();
            System.out.println("[BakaAPI-MySQL] 操作发生错误，操作已回滚");
            System.out.println(ex.getMessage());
        }finally {
            close();
        }
    }

    /**
     * SQL查询操作
     * @param sql
     * @param parameters
     * @return
     * @throws SQLException
     */
    public static List<Object> executeQuery(String sql, Object ...parameters) throws SQLException {
        if (parameters.length <= 0){
            getConnection();
            getStatement();
            rs = st.executeQuery(sql);
        }else {
            getConnection();
            getPreparedStatement(sql);
            for (int i=0; i<parameters.length; i++){
                pst.setObject(i+1,parameters[i]);
            }
            rs = pst.executeQuery();
        }
        List<Object> result = new ArrayList<>();
        ResultSetMetaData rsm = rs.getMetaData();
        while (rs.next()){
            Map<String, Object> tempMap = new HashMap<String ,Object>();
            for (int i = 0; i < rsm.getColumnCount(); i++){
                tempMap.put(rsm.getColumnName(i+1),rs.getObject(i+1));
            }
            result.add(tempMap);
        }
        close();
        return result;
    }

    /**
     * 统一关闭资源
     * @throws SQLException
     */
    public static void close() throws SQLException {
        if (rs != null){
            rs.close();
        }
        if (st != null){
            st.close();
        }
        if (pst != null){
            pst.close();
        }
        if (connection != null){
            connection.close();
        }
    }

}
