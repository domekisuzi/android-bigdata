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

public class Response implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String date;

    private String content;

    public Response() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Response(Integer id, String date, String content, String name) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.name = name;
    }

    public Response(String content, String name) {
        this.content = content;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Response(Integer id, String content, String name) {
        this.id = id;
        this.content = content;
        this.name = name;
    }

    private String name;


}
