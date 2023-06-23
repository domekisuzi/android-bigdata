package cn.xdc.scorerecord.adapter;


import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

import cn.xdc.scorerecord.R;
import cn.xdc.scorerecord.bean.Admin;
import cn.xdc.scorerecord.bean.BannerImage;

/**
 * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
 */
public class ImageAdapter extends BannerAdapter<BannerImage, ImageAdapter.BannerViewHolder> {


    public ImageAdapter(List<BannerImage> datas) {
        super(datas);
        for (BannerImage ban:datas
             ) {
            System.out.println(ban);
        }
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

//    这里放入第一张图片,成功插入了gif，不过以后要不要考虑缓存之类的问题？
    @Override
    public void onBindView(BannerViewHolder holder, BannerImage data, int position, int size) {
        Glide.with(holder.itemView)
                .load(data.getPictureRes())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.imageView);
    }
    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
