package com.whpowernode.test06;

import com.whpowernode.common.PageInfo;
import com.whpowernode.entity.Student;
import com.whpowernode.test06.impl.StudentDaoImpl;

import java.util.Date;
import java.util.List;

/**
 * @项目 code
 * @描述 TODO
 * @时间 2021-08-11 15:46
 **/
public class TestApp {
    public static void main(String[] args) {
        StudentDao studentDao=new StudentDaoImpl();
        //添加
        //int i=studentDao.addStudent(new Student(null,"小红","北京",22,"女",new Date()));
        //修改
        //studentDao.updateStudent(new Student(11,"小丽","深圳",18,"女",new Date()));

        //删除
//        studentDao.deleteStudentById(11);
//        List<Student> studentList = studentDao.queryAllStudent();
//        for (Student student : studentList) {
//            System.out.println(student);
//        }
        //查询一个
//        Student student = studentDao.queryStudentById(111);
//        System.out.println(student);

        //分页查询
        PageInfo<Student> pageInfo=new PageInfo<>(1L,5L);
        Student student=new Student();
        student.setName("6");
        student.setAddress("1");
        student.setSex("女");
        PageInfo<Student> studentPageInfo = studentDao.queryStudentForPage(pageInfo, student);
        studentPageInfo.getData().forEach(System.out::println);
        System.out.println("当前页:"+studentPageInfo.getPageNum());
        System.out.println("每页条数:"+studentPageInfo.getPageSize());
        System.out.println("总页数:"+studentPageInfo.getTotalPage());
        System.out.println("总条数:"+studentPageInfo.getTotalCount());

    }

}
