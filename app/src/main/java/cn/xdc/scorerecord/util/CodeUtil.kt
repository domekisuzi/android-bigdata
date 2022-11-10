package cn.xdc.scorerecord.util

import android.app.Activity
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import cn.xdc.scorerecord.MyApplication
import java.sql.Timestamp

import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*

/**
 *   author:domekisuzi
 *   time:2022/8/29
 *
 *   方便写代码而创建的工具类
 */

fun  log(str:String){
    Log.d(MyApplication.context.toString(),str )
}
fun  String.showToast(duration: Int= Toast.LENGTH_SHORT){
    Toast.makeText(MyApplication.context,this,duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}

fun timeStampPut(): Timestamp {
    val simpleDateFormat = SimpleDateFormat("yyy-MM-dd HH:mm:ss")
    val date = Date()
    return  Timestamp(date.time)
}

/**
 * 设置全局的常量 TODO("把他用起来，可以优化框架")
 */
fun setGlobalName(name:String){
    val sp =
        MyApplication.context.getSharedPreferences("abaaba", Activity.MODE_PRIVATE)
    var editor = sp.edit()
    editor.putString("global_name","")
    editor.putString("global_name",name)
    editor.commit()
}

fun getGlobalName():String{
    val sp = MyApplication.context.getSharedPreferences("abaaba", Activity.MODE_PRIVATE)
    return sp.getString("global_name","").toString()
}
