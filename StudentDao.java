package com.whpowernode.test06;

import com.whpowernode.common.PageInfo;
import com.whpowernode.entity.Student;

import java.util.List;

/**
 * @项目 code
 * @描述 TODO 传门处理学生的CRUD
 * @时间 2021-08-11 15:39
 * dao ==data access Object
 **/
public interface StudentDao {

    /**
     * 添加学生
     */
    int addStudent(Student student);
    /**
     * 修改学生
     */
    int updateStudent(Student student);
    /**
    /**
     * 删除学生
     */
    int deleteStudentById(Integer id);

    /**
     * 查询一个学生
     */
    Student queryStudentById(Integer id);

    /**
     * 全查询学生
     */
    List<Student> queryAllStudent();


    /**
     * 分页+关键字查询
     * @param pageInfo 分页的对象
     * @param student 查询条件
     */
    PageInfo<Student> queryStudentForPage(PageInfo<Student> pageInfo,Student student);
}
