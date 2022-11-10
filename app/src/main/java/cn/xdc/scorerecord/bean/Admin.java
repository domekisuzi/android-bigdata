package cn.xdc.scorerecord.bean;


import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author domekisuzi
 * @since 2022-08-26
 */

public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer id;

    private String password;

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String name, Integer id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }
}
