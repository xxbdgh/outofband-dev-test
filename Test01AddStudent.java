package com.whpowernode.test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @项目 code
 * @描述 TODO 使用JDBC完成学生信息的添加
 * @时间 2021-08-10 09:46
 **/
public class Test01AddStudent {
    //数据库驱动地址
    public static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    //数据的连接地址
    public static final String JDBC_URL="jdbc:mysql://127.0.0.1:3306/whpowernode?useUnicode=true&useSSL=false&characterEncoding=UTF8";
    //数据的登陆名
    public static final String JDBC_USERNAME="root";
    //数据库的登陆密码
    public static final String JDBC_PASSWORD="123456";

    public static void main(String[] args) throws  Exception {
        //1,加载驱动
        Class.forName(JDBC_DRIVER);
        //2,使用驱动管理器得到连接数据库的连接 对象
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        //3,从连接里面获取发送SQL的对象
        Statement statement = conn.createStatement();
        //4,写SQL
        String sql="INSERT INTO STUDENT(name,address,age,sex,birth) VALUES('小明','武汉',22,'男','2020-1-1')";
        //5,使用第3步里的sql发送器把第4步的SQL发出到数据库里面去执行
        int i = statement.executeUpdate(sql);//这里面返回值就是受影响的行数
        //6,处理结果
        if(i>0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
        //7,关闭资源
        statement.close();
        conn.close();
    }
}
