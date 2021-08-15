package com.whpowernode.test06;

import com.whpowernode.common.PageInfo;
import com.whpowernode.entity.Student;
import com.whpowernode.utils.DBUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @项目 code
 * @描述 TODO 作用，封装公共的添加 修改 删除   查询及分页查询的方法
 * @时间 2021-08-11 15:31
 **/
public class BaseDao {
    /**
     * 封装一个添加修改 删除 的方法
     * @param  sql 动态SQL
     * @param  params  SQL里面占位符的参数
     */
    public int executeUpdate(String sql,Object [] params){
        //默认受影响的行数为0
        int affectedRows=0;
        //1,得到连接对象
        Connection connection = DBUtils.getConnection();
        //2,声明发送SQL的对象
        PreparedStatement statement=null;
        try{
            //3,创建statement并预编译SQL
            statement=connection.prepareStatement(sql);
            //4,设置占位符
            if(params!=null&&params.length>0){
                for (int i = 0; i <params.length ; i++) {
                    statement.setObject(i+1,params[i]);
                }
            }
            //5，执行SQL
            affectedRows=statement.executeUpdate();
            if(affectedRows>0){
                System.out.println("操作成功");
            }else{
                System.out.println("操作失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return affectedRows;
    }


    /**
     * 声明一个全查询的公共方法
     */
    public <T> List<T> selectList(String sql,Class<T> cls,Object [] params){
        List<T> data=new ArrayList<>();
        //1,得到连接对象
        Connection connection = DBUtils.getConnection();
        //2,声明发送SQL的对象
        PreparedStatement statement=null;
        //3,声明一个接收结果集合的对象
        ResultSet rs=null;
        try{
            //4,创建statement并预编译SQL
            statement=connection.prepareStatement(sql);
            //5,设置占位符
            if(params!=null&&params.length>0){
                for (int i = 0; i <params.length ; i++) {
                    statement.setObject(i+1,params[i]);
                }
            }
            //6,执行查询得到结果集
            rs=statement.executeQuery();
            //7,得到结果集里面的标签封装对象
            ResultSetMetaData metaData = rs.getMetaData();
            //8.得到标签的个数
            int columnCount = metaData.getColumnCount();
            while (rs.next()){
                //使用反射创建cls对象
                T obj = cls.newInstance();
                //9.循环获取标签的名字
                for (int i = 1; i <=columnCount ; i++) {
                    //得到标签名
                    String columnLabel=metaData.getColumnLabel(i);
                    //使用反射根据columnLabel名去字节码里面取columnLabel名字相同的属性
                    Field field = cls.getDeclaredField(columnLabel);
                    //打破访问权限
                    field.setAccessible(true);
                    //根据标签名取出数据库里面当前行的数据
                    Object value=rs.getObject(columnLabel);
                    //使用反射给field属性设置值
                    field.set(obj,value);
                }
                //再把obj对象放到data
                data.add(obj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return data;
    }

    /**
     * 根据ID查询一个
     * @param sql
     * @param cls
     * @param params
     * @param <T>
     * @return
     */
    public <T> T selectOne(String sql, Class<T> cls, Object[] params) {
        //声明返回的对象
        T obj=null;
        //使用SQL查询集合 如果集合里面有数据就取第一条返回出去
        List<T> selectList = this.selectList(sql, cls, params);
        if(selectList!=null&&selectList.size()>0){
            obj=selectList.get(0);
        }
        return obj;
    }

    /**
     * 分页的封装
     * @param sql
     * @param pageInfo
     * @param cls
     * @param params
     * @return
     */
    protected <T> PageInfo<T> queryForPage(String sql,PageInfo<T> pageInfo, Class<T> cls, Object[] params) {
        //1,先查询总条数
        Long totalCount=queryCount(sql,params);
        //设置到pageInfo
        pageInfo.setTotalCount(totalCount);
        if(totalCount>0){
            //2,如果总条数大于0 就做数据查询
            String newSql=sql+" limit "+(pageInfo.getPageNum()-1)*pageInfo.getPageSize()+
                        ","+pageInfo.getPageSize();
            List<T> selectList = this.selectList(newSql, cls, params);
            //放到pageInfo
            pageInfo.setData(selectList);
        }
        return pageInfo;
    }

    /**
     * 根据SQL+参数查询数量
     * @param sql
     * @param params
     * @return
     */
    private Long queryCount(String sql, Object[] params) {
        //声明一个总条数的返回值 默认为0L
        Long totalCount=0L;
        //得到连接
        Connection connection = DBUtils.getConnection();
        //创建发送SQL的对象
        PreparedStatement statement=null;
        //声明一个接收结果集的对象
        ResultSet rs=null;
        try{
            String newSql="select count(1) from ("+sql+") t";
            statement=connection.prepareStatement(newSql);
            //设置占位符
            if(params!=null&&params.length>0){
                for (int i = 0; i <params.length ; i++) {
                    statement.setObject(i+1,params[i]);
                }
            }
            //执行查询
            rs=statement.executeQuery();
            if(rs.next()){
                totalCount=rs.getLong(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return totalCount;
    }
}
