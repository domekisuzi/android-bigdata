package cn.xdc.scorerecord.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.bean.But
import cn.xdc.scorerecord.bean.JSONObject
import cn.xdc.scorerecord.util.RequestUtil.operate
import cn.xdc.scorerecord.util.RetrofitBuilder
import cn.xdc.scorerecord.util.Service
import cn.xdc.scorerecord.util.setGlobalName

import cn.xdc.scorerecord.view.AnimationButton
import com.alibaba.fastjson2.JSONArray
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException

class LoginActivity : BaseActivity() {


    //静态代码块，里面相当于java的static
    companion object {
        const val START_STUDENT_ACTIVITY = 1
        const val START_ADMIN_ACTIVITY = 2
    }

    private lateinit var sureButton: AnimationButton
    private lateinit var passwdEt: TextInputEditText
    private lateinit var nameEt: TextInputEditText
    private lateinit var nameTextLayout: TextInputLayout
    private lateinit var passwdTextLayout: TextInputLayout
    private val api = RetrofitBuilder.create(Service::class.java)
    private lateinit var studentName:String



    //活动 ，  页面跳转，说明开启了新的活动    他们是独立的
    //在活动之间传递我们需要的数据
    /**
     * 这里处理异步操作，当从服务器正确接受完数据的时候，我们通知主线程收到结果并进行相关操作
     */
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {

            /**
             * 启动对应活动，并 销毁登录活动
             *
             */
            when (msg.what) {
                START_STUDENT_ACTIVITY -> {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("student_name",studentName)
                    setGlobalName(studentName)
                    operate("登录")
                    startActivity(intent)
                    finish()
                }

                START_ADMIN_ACTIVITY -> {
                    val intent = Intent(this@LoginActivity, AdminActivity::class.java)
                    setGlobalName("管理员")
                    operate("登录")
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private val message = Message()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //填充整个屏幕

        getWindow().getDecorView().setSystemUiVisibility(5894);
        setContentView(R.layout.activity_login)
        initView()
        initEvent()
    }

    override fun initView() {
        sureButton = findViewById(R.id.sure)
        nameEt = findViewById(R.id.name_et)
        passwdEt = findViewById(R.id.password_et)
        nameTextLayout = findViewById(R.id.name_til)
        passwdTextLayout = findViewById(R.id.passwd_til)
        //记住密码
        val prefs = getSharedPreferences("name",Context.MODE_PRIVATE)
        val name  = prefs.getString("name","")
        val magic = prefs.getString("magic", "")
        nameEt.setText(name)
        passwdEt.setText(magic)
    }
//张丹
    override fun initEvent() {
        val editor = getSharedPreferences("name",Context.MODE_PRIVATE).edit()

        sureButton.setAnimationButtonListener(object : AnimationButton.AnimationButtonListener {
            override fun animationFinish() {
                sureButton.isClickable = false
//                when(message.what){
//                    START_ADMIN_ACTIVITY->{
//                        //TODO("还没做管理员接口")
////                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
////                        startActivity(intent)
//                    }
//                    START_STUDENT_ACTIVITY->{
//                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                        startActivity(intent)
//                    }
//                }
//
                handler.sendMessage(message)
            }

            override fun onClickListener() {
                if (passwdEt.text.toString() == "qqq") {
                    editor.putString("magic",passwdEt.text.toString().trim())
                    editor.apply()
                    sureButton.start()
                    message.what = START_ADMIN_ACTIVITY
                    Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_LONG).show()

                } else {
                    if (passwdEt.text.toString().isNotEmpty()) {
                        passwdTextLayout.error = "你的，魔法似乎不正确！"
                    }
                    else{
                        studentName = nameEt.text.toString().trim()

                        editor.putString("name",studentName)
                        //别忘了加这个apply
                        editor.apply()
                        api.getStudentByName(studentName).enqueue(object : Callback<JSONObject> {
                            override fun onResponse(
                                call: Call<JSONObject>,
                                response: Response<JSONObject>
                            ) {
                                sureButton.start()
                                message.what = START_STUDENT_ACTIVITY
                            }
                            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                                Toast.makeText(this@LoginActivity, "请求失败！", Toast.LENGTH_LONG).show()
                            }

                        })
                    }
                }
            }
        })

    }
}