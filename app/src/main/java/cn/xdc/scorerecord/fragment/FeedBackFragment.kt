package cn.xdc.scorerecord.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.xdc.scorerecord.MyApplication
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.adapter.FeedBackAdapter
import cn.xdc.scorerecord.bean.Feedback
import cn.xdc.scorerecord.util.*
import cn.xdc.scorerecord.view.FeedBackDialog
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *   author:domekisuzi
 *   time:2022/8/17
 *
 *   反馈fragment
 */
class FeedBackFragment(private val studentName: String) : Fragment() {

    companion object {
        const val FLAG = 1
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var feedbacklists: MutableList<Feedback>
    private lateinit var add: FloatingActionButton
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                FLAG -> {
                    //判空处理，防止因为没数据直接推出
                    val adapter = context?.let {
                        if (::feedbacklists.isInitialized) {
//                            "实例化过了".showToast()
                            RequestUtil.operate("查看所有反馈信息")
                            FeedBackAdapter(feedbacklists, it, studentName)

                        } else {
//                            "尚未".showToast()
                            FeedBackAdapter(arrayListOf(), it, studentName)


                        }
                    }
                    recyclerView.adapter = adapter
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feedback, container, false)
        recyclerView = view.findViewById(R.id.feedback_recycler)
        add = view.findViewById(R.id.feedback_add)

        add.setOnClickListener {
            val feedBackDialog = FeedBackDialog(requireContext())
            feedBackDialog.textTitle = "反馈"
            feedBackDialog.onClickBottomListener = object : FeedBackDialog.OnClickBottomListener {
                override fun onNegativeClick() {
                    feedBackDialog.cancel()
                }

                //重写确定取消按钮
                override fun onPositiveClick() {
                    val feedback = Feedback(feedBackDialog.editContent, studentName)
                    RetrofitBuilder.create(Service::class.java).createFeedback(feedback)
                        .enqueue(object : Callback<Int> {
                            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                                response.body()?.let {
                                    if (it != 0) {
                                        "发布成功".showToast()
                                        RequestUtil.operate("发布帖子")
                                        feedBackDialog.cancel()
                                        (recyclerView.adapter as FeedBackAdapter).addItem(feedback)
                                    } else {
                                        "发布失败，原因不明".showToast()
                                    }
                                }
                            }

                            override fun onFailure(call: Call<Int>, t: Throwable) {
                                "发送失败，网络错误".showToast()
                            }

                        })
                }
            }
            feedBackDialog.show()
        }


        //获取所有人的feedback
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        RetrofitBuilder.create(Service::class.java).getAllFeedback()
            .enqueue(object : Callback<JSONArray> {
                override fun onResponse(call: Call<JSONArray>, response: Response<JSONArray>) {
                    response.body()?.let {
                        feedbacklists = JSONUtil.arrayToData(it, Feedback::class.java)

                    }
                    val msg = Message()
                    msg.what = FLAG
                    handler.sendMessage(msg)
                }

                //张丹
                override fun onFailure(call: Call<JSONArray>, t: Throwable) {
                    "服务器无响应".showToast()
                }
            })
        return view
    }


}