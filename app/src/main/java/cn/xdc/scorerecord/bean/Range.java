package cn.xdc.scorerecord.bean;

/**
 * author:domekisuzi
 * time:2022/8/16
 */
public enum Range {
    BAD("0~60"),MIDDLE("60~80"),GOOD("80~90"),GREAE("90~100");
    private String range;
    private Range(String str){
        this.range = str;
    }

    @Override
    public String toString() {
        return range;
    }
}
