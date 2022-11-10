package cn.xdc.scorerecord.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.xdc.scorerecord.MyApplication.Companion.context
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.adapter.ResponseAdapter
import cn.xdc.scorerecord.bean.But
import cn.xdc.scorerecord.bean.Feedback
import cn.xdc.scorerecord.util.*
import com.alibaba.fastjson2.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 *   author:domekisuzi
 *   time:2022/9/2
 */
class ResponseActivity:BaseActivity() {
    companion object{
        const val GET_RESPONSE = 1
    }
    private lateinit var feedbackName:TextView
    private lateinit var feedbackContent:TextView
    private lateinit var feedbackTime:TextView
    private lateinit var feedbackRecycler:RecyclerView
    private lateinit var name:String
    private lateinit var feedback: Feedback
    private lateinit var responseList:MutableList<cn.xdc.scorerecord.bean.Response>
    private val handler = object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
             when(msg.what){
                 GET_RESPONSE->{
                     if (::responseList.isInitialized){
                         val adapter = ResponseAdapter(responseList)
                         feedbackRecycler.adapter = adapter

                     }
                 }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)
        name = intent.getStringExtra("studentName").toString()
        feedback = intent.getSerializableExtra("feedback") as Feedback
        initView()
        initEvent()

        RequestUtil.operate("查看反馈详情")
    }

    override fun initEvent() {
        super.initEvent()
    }

    override fun initView() {
        super.initView()
        feedbackName = findViewById(R.id.feedback_response_name)
        feedbackContent = findViewById(R.id.feedback_response_content)
        feedbackTime = findViewById(R.id.feedback_response_time)
        feedback.name?.let {
//            feedbackName.text =  it
            feedbackName.text = ""
        }




        feedbackContent.text = feedback.content
        feedbackTime.text = feedback.date

        feedbackRecycler = findViewById(R.id.feedback_response_recyclerView)
        val layoutManager = LinearLayoutManager(this)
        feedbackRecycler.layoutManager = layoutManager

        if (::feedback.isInitialized) {
            RetrofitBuilder.create(Service::class.java).getAllResponseByFeedbackId(feedback.id)
                .enqueue(object :
                    Callback<JSONArray> {
                    /**
                     * 所有不确定能否拿到的数据都进行判空检查
                     */
                    override fun onResponse(call: Call<JSONArray>, response: Response<JSONArray>) {
                        response.body()?.let {
                            responseList = JSONUtil.arrayToData(
                                response.body()!!,
                                cn.xdc.scorerecord.bean.Response::class.java
                            )
                            val msg = Message()
                            msg.what = GET_RESPONSE
                            handler.sendMessage(msg)
                        }
                    }

                    override fun onFailure(call: Call<JSONArray>, t: Throwable) {
                        Toast.makeText(this@ResponseActivity, "失败，服务器异常", Toast.LENGTH_LONG).show()
                    }
                })
        }



    }

}