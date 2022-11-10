package cn.xdc.scorerecord

import android.app.Application
import android.content.Context
import com.jaredrummler.cyanea.Cyanea

class MyApplication : Application() {

    companion object{
        public  lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
//        Cyanea.init(this,resources)
    }
}