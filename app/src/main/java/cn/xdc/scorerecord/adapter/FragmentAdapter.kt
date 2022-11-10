package cn.xdc.scorerecord.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *   author:domekisuzi
 *   time:2022/8/17
 *   用的viewpager1，被viewpager2折磨了很久就没更新了
 */


class FragmentAdapter(val fragments: MutableList<Fragment>, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = fragments.size


    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

}