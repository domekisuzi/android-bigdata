package cn.xdc.scorerecord.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.bean.But
import cn.xdc.scorerecord.bean.ChartData
import cn.xdc.scorerecord.bean.Range
import cn.xdc.scorerecord.util.*
import cn.xdc.scorerecord.view.EchartsView
import com.alibaba.fastjson2.JSONArray
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : BaseActivity() {
    private lateinit var moonImage: ImageView
    private lateinit var order:TextView
    private lateinit var remark:TextView
    private lateinit var butContext:TextView
    private lateinit var score:TextView
    private lateinit var but:But
    private lateinit var rangeEcharts:EchartsView
    //写个接口，拿到所有but
    private lateinit var buts:MutableList<But>
    private lateinit var echartsData:String
    companion object{
        const val INFLATE_ECHARTS =  1
    }
    private val handler = object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
               INFLATE_ECHARTS->{
                  startEcharts()
               }

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        but = intent.getSerializableExtra("but") as But
        initView()
        initEvent()
        RequestUtil.operate("查看答辩详情")
    }
    //张丹

    //TODO("可以制作一个 预加载骨架")
    override fun initView() {
        moonImage = findViewById(R.id.moonImage)
        //加载装饰用照片(我是真的爱这个)
        Glide.with(this).load(R.drawable.moon).placeholder(androidx.appcompat.R.color.material_grey_50).into(moonImage)
        order = findViewById(R.id.detail_order)
        remark = findViewById(R.id.detail_remark)
        butContext = findViewById(R.id.detail_context)
        score = findViewById(R.id.detail_score)
        rangeEcharts  =  findViewById(R.id.range_echarts)

        RetrofitBuilder.create(Service::class.java).getAllButs().enqueue(object :Callback<JSONArray>{
            override fun onResponse(call: Call<JSONArray>, response: Response<JSONArray>) {
                buts = JSONUtil.arrayToData(response.body()!!, But::class.java)
                echartsData = ArithmeticUtil.getChartDataByBut(buts, but.order)
                val msg = Message()
                msg.what = INFLATE_ECHARTS
                handler.sendMessage(msg)
            }

            override fun onFailure(call: Call<JSONArray>, t: Throwable) {
                Toast.makeText(this@DetailActivity,"失败，服务器异常",Toast.LENGTH_LONG).show()
            }

        })

    }
    override fun initEvent() {
        order.text = but.order.toString()
        remark.text = but.remark.toString()
        butContext.text = but.context.toString()
        score.text = but.score.toInt().toString()
    }



    @SuppressLint("JavascriptInterface")
    private fun startEcharts() {
        //加载本地html
        rangeEcharts.loadUrl("file:///android_asset/pie.html")
        //配置接口
        rangeEcharts.addJavascriptInterface(this, "bridge")

        rangeEcharts.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                rangeEcharts.setPieChartData(echartsData);
                //获取buts？！
            }
        }
    }
}