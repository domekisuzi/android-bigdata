package cn.xdc.scorerecord.util

import android.util.Log
import cn.xdc.scorerecord.bean.But
import cn.xdc.scorerecord.bean.ChartData
import cn.xdc.scorerecord.bean.Range

object ArithmeticUtil {

    fun getChartDataByBut(buts: List<But>,order:String): String  {
//        BAD("0~60"),MIDDLE("60~80"),GOOD("80~90"),GREAE("90~100");

        var bad =0
        var middle = 0
        var good = 0
        var great = 0
        Log.e("传进来的buts为",buts.toString())
        for (but in buts) {
            if (but.order.equals(order)) {
                if (but.score <60){
                    bad++
                }
                else if ((but.score >=60) and (but.score < 80)  ){
                    middle++
                }
                else if ((but.score >=80) and (but.score < 90)  ){
                    good++
                }
                else if ((but.score >=90) and (but.score < 100)  ){
                    great++
                }
            }
        }

         return JSONUtil.dataToJSON(
            ChartData(Range.BAD.toString(), bad),
            ChartData(Range.GOOD.toString(), good),
            ChartData(Range.GREAE.toString(), great),
            ChartData(Range.MIDDLE.toString(), middle)
        )
    }

}