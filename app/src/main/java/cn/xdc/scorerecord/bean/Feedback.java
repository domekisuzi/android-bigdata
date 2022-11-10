package cn.xdc.scorerecord.bean;


import static cn.xdc.scorerecord.util.CodeUtilKt.timeStampPut;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author domekisuzi
 * @since 2022-08-26
 */

public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 加上 `` 防止由于重复二导致的更新失败
     */

    private Integer id;

    private String date;

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

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }



    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                ", pictures='" + pictures + '\'' +
                ", responseId='" + responseId + '\'' +
                '}';
    }

    public Feedback() {
    }

    public Feedback(String content, String name) {
        this.content = content;
        this.name = name;
        this.date  = timeStampPut().toString();
    }

    public Feedback(Integer id, String date, String content, String name, String pictures, String responseId) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.name = name;
        this.pictures = pictures;
        this.responseId = responseId;
    }

    private String content;

    private String name;

    private String pictures;

    private String responseId;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
