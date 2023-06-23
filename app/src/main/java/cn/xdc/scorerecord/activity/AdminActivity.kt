package cn.xdc.scorerecord.activity

import android.os.Bundle
import cn.xdc.scorerecord.R

//页面跳转，其实我们开辟另一个activity
class AdminActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
    }

    override fun initEvent() {

    }

    override fun initView() {

    }
}
