package cn.xdc.scorerecord.util

import android.widget.Toast
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

object RetrofitBuilder {

//    本地版和服务器版本
    private const val BASE_URL = "http://domekisuzi.fun:7777"
    private const val LOCAL_BASE_URL = "http://10.0.2.2:7777"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

}

/**
 * 通过kotlin的拓展函数简化接口回调,使用 reified 推断T的类型， 外部还是要包装一层异步 //TODO("该部分代码尚未完成")
 */

//
//public inline fun <reified T> Call<T>.getData(clazz: Class<T>): Class<T> {
//    var result: Any? = null
//    var flage = true
//    thread {
//        this.enqueue(object : Callback<T> {
//            override fun onResponse(call: Call<T>, response: Response<T>) {
//                when (T::class.java) {
//                    JSONArray::class.java -> {
//                        //使用let 判空
//                        result = response.body().let {
//                            JSONArray.parseArray(it?.toString()).toList(clazz)
//                        }
//                        flage = false
//                    }
//                    JSONObject::class.java -> {
//                        result = response.body().let {
//                            JSONObject.parseObject(it?.toString(), clazz)
//                        }
//                        flage = false
//                    }
//                    else -> {
//                        flage = false
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<T>, t: Throwable) {
//
//            }
//        })
//    }
//    //设置一个flag，当正确获取数据时再return
//    while (flage) {
//        Thread.sleep(50)
//    }
//    return result as Class<T>
//}
