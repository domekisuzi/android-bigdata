package cn.xdc.scorerecord.bean;


import java.io.Serializable;


/**
 * <p>
 *
 * </p>
 *
 * @author domekisuzi
 * @since 2022-08-04
 */

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;


    private Integer id;


    private String idNumber;


    private String way;

    public String getName() {
        return name;
    }

    public Student() {
    }

    public Student(String name, Integer id, String idNumber, String way) {
        this.name = name;
        this.id = id;
        this.idNumber = idNumber;
        this.way = way;
    }


    public void StudentsetName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

}
