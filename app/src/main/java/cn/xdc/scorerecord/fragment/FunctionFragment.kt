package cn.xdc.scorerecord.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.adapter.ImageAdapter
import cn.xdc.scorerecord.bean.BannerImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator


class FunctionFragment:Fragment() {
    private lateinit var banner: Banner<BannerImage,ImageAdapter>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_function,container,false)
        val layoutManager = LinearLayoutManager(context)
//        banner.addBannerLifecycleObserver(this) //添加生命周期观察者
//            .setAdapter(ImageAdapter(arrayListOf(BannerImage(R.drawable.__function),
//                BannerImage(R.drawable.ic______feedback)
//            ))).indicator =
//            CircleIndicator(this)
        val banner1 = "banner1"
        val banner2 = "banner2"
        val banner3 = "anime"
//       如果R里面没注册就用这种方式使用资源
        val resID3 = requireContext().resources.getIdentifier(banner3,"mipmap",requireContext().packageName)
        val resID1 = requireContext().resources.getIdentifier(banner1,"mipmap",requireContext().packageName)
        val resID2 = requireContext().resources.getIdentifier(banner2,"mipmap",requireContext().packageName)
        val imageAdapter = ImageAdapter(arrayListOf(BannerImage(resID3),BannerImage(resID1),
            BannerImage(resID2)
        ))
        banner = view.findViewById(R.id.banner)
        banner.setAdapter(imageAdapter)
            .addBannerLifecycleObserver(this).indicator = CircleIndicator(context)
//        moonImage = view.findViewById(R.id.moonImage)
//        //加载装饰用照片(我是真的爱这个)
//        Glide.with(this).load(R.drawable.moon).placeholder(androidx.appcompat.R.color.material_grey_50).into(moonImage)
        return  view
    }
}