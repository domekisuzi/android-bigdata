package cn.xdc.scorerecord.util

import cn.xdc.scorerecord.bean.Admin
import cn.xdc.scorerecord.bean.Feedback
import cn.xdc.scorerecord.bean.JSONObject
import cn.xdc.scorerecord.bean.Response
import com.alibaba.fastjson2.JSONArray
import retrofit2.Call
import retrofit2.http.*

interface Service {


    @GET("/shixun/but/getButListByName")
    fun getButByName(@Query("name") name: String): Call<JSONArray>

    @GET("/shixun/student/getAllStudent")
    fun getAllStudent(): Call<JSONArray>


    @GET("/shixun/student/getStudentByName")
    fun getStudentByName(@Query("name") name: String): Call<JSONObject>

    @GET("/shixun/but/getAllButs")
    fun getAllButs() : Call<JSONArray>


    @GET("/shixun/response/getOne")
    fun  getResponse(@Query("id")id:Int):Call<com.alibaba.fastjson2.JSONObject>

    @GET("/shixun/response/all")
    fun getAllResponse(@Query("name")name:String):Call<JSONArray>

    @POST("/shixun/response/delete")
    fun deleteResponse(@Query("id")id:Int):Call<Int>

    @GET("/shixun/feedback/subAll")
    fun getAllResponseByFeedbackId(@Query("id") id:Int) : Call<JSONArray>


    @GET("/shixun/feedback/all")
    fun getAllFeedback(@Query("name")name:String):Call<JSONArray>

    @GET("/shixun/feedback/allFeedback")
    fun getAllFeedback():Call<JSONArray>

    @GET("/shixun/feedback/delete")
    fun deleteFeedback(@Query("id")id:Int):Call<Int>

    @POST("/shixun/feedback/createFeedback")
    fun createFeedback(@Body feedback: Feedback):Call<Int>
    /**
     * 注意这种写法，用于url中有id的情况
     */
    @POST("/shixun/feedback/responseFeedback/{id}")
    fun responseFeedback(@Path("id") id:Int,@Body response: Response):Call<Int>

    @GET("/shixun/admin/get")
    fun getAdminByName(@Query("name")name:String):Call<com.alibaba.fastjson2.JSONObject>

    @POST("/shixun/admin/insert")


    fun insertAdmin(@Body admin: Admin):Call<Int>
    @POST("/shixun/admin/login")
    fun loginByPassword(@Field("passwd") passwd:String):Call<com.alibaba.fastjson2.JSONObject>


    @GET("/shixun/operate/operate")
    fun operate(@Query("name")name:String,@Query("operate") operate:String):Call<Int>

}