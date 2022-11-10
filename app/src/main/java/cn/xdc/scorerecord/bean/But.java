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

public class But implements Serializable {

    private static final long serialVersionUID = 1L;


    private String order;

    private Integer id;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    private String context;

    private String remark;

    private Float score;

    public But() {
    }

    public But(String order, Integer id, String context, String remark, Float score) {
        this.order = order;
        this.id = id;
        this.context = context;
        this.remark = remark;
        this.score = score;
    }

    @Override
    public String toString() {
        return "But{" +
                "order='" + order + '\'' +
                ", id=" + id +
                ", context='" + context + '\'' +
                ", remark='" + remark + '\'' +
                ", score=" + score +
                '}';
    }
}
