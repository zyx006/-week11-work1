package cn.czyx007.week11;

import cn.czyx007.week11.bean.Student;
import cn.czyx007.week11.dao.impl.StudentDAOImpl;
import cn.czyx007.week11.utils.DBUtil;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/10
 */
public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
            connection.commit();
            //输入学生各项信息，保存到数据库
            System.out.print("请输入待添加学生的学号：");
            String id = scanner.nextLine();
            System.out.print("请输入待添加学生的姓名：");
            String name = scanner.nextLine();
            System.out.print("请输入待添加学生的性别：");
            String gender = scanner.nextLine();
            System.out.print("请输入待添加学生的出生年月日(yyyy-MM-dd)：");
            Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
            System.out.print("请输入待添加学生的成绩：");
            Double score = Double.valueOf(scanner.nextLine());
            studentDAO.insert(new Student(id, name, gender, birth, score));
            System.out.println("添加成功！");

            // 输入学生学号，查询显示该学号学生的所有记录
            System.out.println("请输入待查询学生的学号：");
            id = scanner.nextLine();
            System.out.println(studentDAO.selectById(id));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, null, null);
        }
    }
}
