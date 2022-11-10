package cn.czyx007.week11.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/9
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private String id;
    private String name;
    private String gender;
    private Date birth;
    private Double score;
}
