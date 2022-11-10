package cn.xdc.scorerecord.util

import cn.xdc.scorerecord.bean.Range
import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject

object JSONUtil {
    fun dataToJSON(vararg s:Any): String  {
        val jsonArray = JSONArray()
        for (any in s) {
            jsonArray.add(any)
        }

        return jsonArray.toJSONString()
    }

    fun <T> arrayToData(jsonArray: JSONArray,clazz: Class<T>): MutableList<T>  {
        val result = JSON.parseArray(jsonArray.toString(), clazz)
        return result
    }
}
//idea