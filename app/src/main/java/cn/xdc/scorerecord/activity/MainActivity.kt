package cn.xdc.scorerecord.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import cn.xdc.scorerecord.MyApplication
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.adapter.FragmentAdapter
import cn.xdc.scorerecord.bean.Student
import cn.xdc.scorerecord.fragment.CheckFragment
import cn.xdc.scorerecord.fragment.FeedBackFragment
import cn.xdc.scorerecord.fragment.FunctionFragment
import cn.xdc.scorerecord.util.getGlobalName
import cn.xdc.scorerecord.util.showToast
import me.majiajie.pagerbottomtabstrip.MaterialMode
import me.majiajie.pagerbottomtabstrip.NavigationController
import me.majiajie.pagerbottomtabstrip.PageNavigationView
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener


class MainActivity : BaseActivity() {
    //    private lateinit var pieChart: EchartsView
    private lateinit var viewPager: ViewPager
    private lateinit var tab: PageNavigationView
    var testColors = intArrayOf(-0xbaa59c, -0xff8695, -0x86aab8, -0xa4b6b9, -0xa8400)
    private lateinit var studentName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(5894);
        setContentView(R.layout.activity_main)

        try {
            studentName = intent.getStringExtra("student_name").toString()
        }catch (e:Exception){
            e.printStackTrace()
            Log.e("tag","未正确获取人名")
        }

        initView()
        initEvent()
    }

    override fun initEvent() {
    }

//    在这里加fragment

    override fun initView() {
        tab = findViewById(R.id.tab)
        val controller = tab.material()
//            .addItem(R.drawable.ic_ondemand_video_black_24dp, "Movies & TV", testColors[0])
//            .addItem(R.drawable.ic_audiotrack_black_24dp, "Music", testColors[1])
//            .addItem(R.drawable.ic_book_black_24dp, "Books", testColors[2])
//            .addItem(R.drawable.ic_news_black_24dp, "Newsstand", testColors[3])
            .addItem(R.drawable.ic___result,"我的成绩",testColors[1])
            .addItem(R.drawable.ic______feedback,"反馈",testColors[2])
            .addItem(R.drawable.__function,"功能",testColors[3])
            .setDefaultColor(0x89FFFFFF.toInt())//未选中状态的颜色
            .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR or MaterialMode.HIDE_TEXT)//这里可以设置样式模式，总共可以组合出4种效果
            .build()
        viewPager = findViewById(R.id.viewpager)
        //如果studentname被初始化了
        if(::studentName.isInitialized) {

            val fragments = mutableListOf<Fragment>(CheckFragment(studentName), FeedBackFragment(studentName),FunctionFragment())
            val adapter = FragmentAdapter(fragments, supportFragmentManager)
            viewPager.adapter = adapter
            controller.setupWithViewPager(viewPager)
            controller.addTabItemSelectedListener(object : OnTabItemSelectedListener {
                override fun onSelected(index: Int, old: Int) {
                    Log.e("main", "index: " + index + "old: " + old)
                }
                override fun onRepeat(index: Int) {
                    Log.e("main", "repeat: $index")
                }

            })

        }
    }

//    @SuppressLint("JavascriptInterface")
//    private fun startEcharts() {
//        //加载本地html
//        pieChart.loadUrl("file:///android_asset/pie.html")
//        //配置接口
//        pieChart.addJavascriptInterface(this, "bridge")
//
//        pieChart.webViewClient = object : WebViewClient() {
//            override fun onReceivedError(
//                view: WebView?,
//                request: WebResourceRequest?,
//                error: WebResourceError?
//            ) {
//                super.onReceivedError(view, request, error)
//            }
//
//            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                super.onPageStarted(view, url, favicon)
//            }
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                val str = JSONUtil.dataToJSON(
//                    ChartData(Range.BAD.toString(), 123),
//                    ChartData(Range.GOOD.toString(), 23),
//                    ChartData(Range.GREAE.toString(), 152),
//                    ChartData(Range.MIDDLE.toString(), 123)
//                )
//                pieChart.setPieChartData(str);
//                Log.d("详情界面", "数据填充完毕")
//            }
//        }
//    }


}


