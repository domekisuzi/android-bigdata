package cn.xdc.scorerecord.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.view.AnimationButton
import com.jaredrummler.cyanea.app.CyaneaAppCompatActivity

open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = 5894;

    }

    protected open fun initView() {

    }

    protected open fun initEvent() {
    }
}