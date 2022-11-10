package cn.czyx007.week11.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/9
 */
public class DBUtil {
    //服务器上只会有一个ThreadLocal，每个线程向其中绑定自己的数据(连接对象)
    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    private static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String username = bundle.getString("username");
    private static String password = bundle.getString("password");

    //不让创建对象，因为工具类中的方法都是静态的，不需要创建对象
    private DBUtil(){}

    //DBUtil类加载时注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 没有使用数据库连接池，直接创建连接对象
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = local.get();
        if (connection == null) {
            connection = DriverManager.getConnection(url,username,password);
            local.set(connection);
        }
        return connection;
    }

    public static void close(Connection connection, PreparedStatement ps, ResultSet rs){
        if (connection != null) {
            try {
                connection.close();
                local.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
