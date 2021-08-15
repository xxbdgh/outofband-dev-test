package com.whpowernode.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @项目 code
 * @描述 TODO
 * @时间 2021-08-10 11:04
 **/
public class DBUtils {
    //数据库驱动地址
    public static  String JDBC_DRIVER;
    //数据的连接地址
    public static  String JDBC_URL;
    //数据的登陆名
    public static  String JDBC_USERNAME;
    //数据库的登陆密码
    public static  String JDBC_PASSWORD;

    static {
        try{
            //解析properties文件
            Properties properties=new Properties();
            //DBUtils.class===得到的是DBUtils这个字节码文件加载到内存之后的那个Class对象
            InputStream is=DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
            //装载is到 properties
            properties.load(is);
            //解析properties
            JDBC_DRIVER=properties.getProperty("jdbc.driver");
            JDBC_URL=properties.getProperty("jdbc.url");
            JDBC_USERNAME=properties.getProperty("jdbc.username");
            JDBC_PASSWORD=properties.getProperty("jdbc.password");
            //加载驱动
            Class.forName(JDBC_DRIVER);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("驱动加载失败");
        }
    }

    /**
     * 提供一个获取连接的方法
     */
    public static Connection getConnection(){
        Connection connection=null;
        try{
            connection= DriverManager.getConnection(JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("获取连接失败");
        }
        return connection;
    }


    /**
     * 提供一个关闭所有对象方法
     */
    public static void close(AutoCloseable closeable){
        if(closeable!=null){
            try{
                closeable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
