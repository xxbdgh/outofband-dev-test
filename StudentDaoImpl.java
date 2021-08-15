package com.whpowernode.test06.impl;

import com.whpowernode.common.PageInfo;
import com.whpowernode.entity.Student;
import com.whpowernode.test06.BaseDao;
import com.whpowernode.test06.StudentDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目 code
 * @描述 TODO
 * @时间 2021-08-11 15:42
 **/
public class StudentDaoImpl extends BaseDao implements StudentDao {
    @Override
    public int addStudent(Student student) {
        String sql="INSERT INTO STUDENT(name,address,age,sex,birth) VALUES(?,?,?,?,?)";
        Object [] params={student.getName(),student.getAddress(),student.getAge()
        ,student.getSex(),student.getBirth()};
        return super.executeUpdate(sql,params);
    }

    @Override
    public int updateStudent(Student student) {
        String sql="UPDATE STUDENT SET name=?,address=?,age=?,sex=?,birth=? WHERE ID=?";
        Object [] params={student.getName(),student.getAddress(),student.getAge()
                ,student.getSex(),student.getBirth(),student.getId()};
        return super.executeUpdate(sql,params);
    }

    @Override
    public int deleteStudentById(Integer id) {
        String sql="DELETE FROM  STUDENT  WHERE ID=?";
        Object [] params={id};
        return super.executeUpdate(sql,params);
    }

    @Override
    public Student queryStudentById(Integer id) {
        String sql="SELECT * FROM STUDENT WHERE ID=?";
        Object [] params= {id};
        return super.selectOne(sql,Student.class,params);
    }

    @Override
    public List<Student> queryAllStudent() {
        String sql="SELECT * FROM STUDENT";
        Object [] params=null;
        return super.selectList(sql,Student.class,params);
    }

    @Override
    public PageInfo<Student> queryStudentForPage(PageInfo<Student> pageInfo, Student student) {
        //条件里面默认设置最多只有name  address 模糊查询 根据sex等值查询
        StringBuffer sql=new StringBuffer(" SELECT * FROM STUDENT WHERE 1=1 ");
        //创建一个集合存放参数
        List<Object> list=new ArrayList<>();
        if(student.getName()!=null&&!student.getName().equals("")){
            sql.append(" AND NAME LIKE CONCAT('%',?,'%') ");
            list.add(student.getName());
        }
        if (student.getAddress()!=null&&!student.getAddress().equals("")){
            sql.append(" AND ADDRESS LIKE CONCAT('%',?,'%')");
            list.add(student.getAddress());
        }
        if(student.getSex()!=null&&!student.getSex().equals("")){
            sql.append(" AND SEX=? ");
            list.add(student.getSex());
        }
        Object[] params = list.toArray();
        return super.queryForPage(sql.toString(),pageInfo,Student.class,params);
    }
}
