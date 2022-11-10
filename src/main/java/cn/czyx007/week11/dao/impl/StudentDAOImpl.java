package cn.czyx007.week11.dao.impl;

import cn.czyx007.week11.bean.Student;
import cn.czyx007.week11.dao.StudentDAO;
import cn.czyx007.week11.utils.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/9
 */
public class StudentDAOImpl implements StudentDAO {
    @Override
    public int insert(Student student) {
        Connection connection = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into tstudent values(?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, student.getId());
            ps.setObject(2, student.getName());
            ps.setObject(3, student.getGender());
            ps.setObject(4, student.getBirth());
            ps.setObject(5, student.getScore());
            count = ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.close(null, ps, null);
        }
        return count;
    }

    @Override
    public Student selectById(String id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from tstudent where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                Date birth = rs.getDate("birth");
                double score = rs.getDouble("score");
                student = new Student(id, name, gender, birth, score);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.close(null, ps, rs);
        }
        return student;
    }
}
