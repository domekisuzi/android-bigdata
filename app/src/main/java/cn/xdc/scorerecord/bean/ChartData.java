package cn.xdc.scorerecord.bean;

/**
 * author:domekisuzi
 * time:2022/8/16
 */
public class ChartData {

    @Override
    public String toString() {
        return "ChartData{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ChartData(String name, Integer number) {
        this.name = name;
        this.value = number;
    }

    private Integer value;
    private String name;
}
