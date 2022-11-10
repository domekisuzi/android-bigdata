package cn.xdc.scorerecord.util

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import cn.xdc.scorerecord.activity.BaseActivity
import cn.xdc.scorerecord.activity.MainActivity
import cn.xdc.scorerecord.activity.ResponseActivity
import com.alibaba.fastjson2.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.lang.ref.WeakReference

/**
 *   author:domekisuzi
 *   time:2022/9/14
 */
object RequestUtil {

//TODO("写个RequestUtil")
    val GET_RESPONSE = 1
    fun getResponseByFeedBackId(id:Int,handler:Handler){
        RetrofitBuilder.create(Service::class.java).getAllResponseByFeedbackId(id).enqueue(object :
            Callback<JSONArray> {
            override fun onResponse(call: Call<JSONArray>, response: Response<JSONArray>) {
                val responseList = JSONUtil.arrayToData(
                    response.body()!!,
                    cn.xdc.scorerecord.bean.Response::class.java
                )
                val msg = Message()
                msg.what = GET_RESPONSE
                var bundle = Bundle()
                bundle.putSerializable("list",responseList as Serializable)
                msg.data = bundle
                handler.sendMessage(msg)
            }
            override fun onFailure(call: Call<JSONArray>, t: Throwable) {
                "服务器异常！".showToast()
            }
        })
    }


    fun  operate(operate:String){
        RetrofitBuilder.create(Service::class.java).operate(getGlobalName(),operate).enqueue(object :
            Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
            }
            override fun onFailure(call: Call<Int>, t: Throwable) {
            }
        })
    }


}