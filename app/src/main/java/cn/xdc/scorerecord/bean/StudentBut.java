package cn.xdc.scorerecord.bean;

import java.io.Serializable;


/**
 *
 * @author domekisuzi
 * @since 2022-08-04
 */


public class StudentBut implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer butId;

    private Integer studentId;

    @Override
    public String toString() {
        return "StudentBut{" +
                "butId=" + butId +
                ", studentId=" + studentId +
                '}';
    }

    public Integer getButId() {
        return butId;
    }

    public void setButId(Integer butId) {
        this.butId = butId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public StudentBut() {
    }

    public StudentBut(Integer butId, Integer studentId) {
        this.butId = butId;
        this.studentId = studentId;
    }
}
