package cn.czyx007.week11.dao;

import cn.czyx007.week11.bean.Student;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/9
 */
public interface StudentDAO {
    int insert(Student student);

    Student selectById(String id);
}
