package cn.xdc.scorerecord.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.adapter.StudentResultAdapter
import cn.xdc.scorerecord.bean.But
import cn.xdc.scorerecord.util.JSONUtil
import cn.xdc.scorerecord.util.RequestUtil
import cn.xdc.scorerecord.util.RetrofitBuilder
import cn.xdc.scorerecord.util.Service
import com.alibaba.fastjson2.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *   author:domekisuzi
 *   time:2022/8/17
 *   学生查看自己的答辩记录的activity
 */

class CheckFragment(val name:String) : Fragment() {
    constructor() : this("") {

    }
    //    private lateinit var moonImage: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var buts:List<But>
    private val handler =object :Handler(Looper.getMainLooper())
    {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                1-> {
                    //TODO("?")
                    RequestUtil.operate("查看所有答辩内容")
                    recyclerView.adapter = StudentResultAdapter(buts,context!!)

                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.frament_student_result,container,false)
        recyclerView = view.findViewById(R.id.student_score_recycler)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        RetrofitBuilder.create(Service::class.java).getButByName(name).enqueue(object :Callback<JSONArray>{
                override fun onResponse(call: Call<JSONArray>, response: Response<JSONArray>) {
                    val message = Message()
                    message.what = 1
                    buts = JSONUtil.arrayToData(response.body()!!,But::class.java)
                    handler.sendMessage(message)
                }

                override fun onFailure(call: Call<JSONArray>, t: Throwable) {
                    Toast.makeText(context, "服务器错误！", Toast.LENGTH_LONG).show()
                }
            })


//        moonImage = view.findViewById(R.id.moonImage)
//        //加载装饰用照片(我是真的爱这个)
//        Glide.with(this).load(R.drawable.moon).placeholder(androidx.appcompat.R.color.material_grey_50).into(moonImage)
        return  view
    }


}