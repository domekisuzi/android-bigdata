package cn.xdc.scorerecord.bean;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import android.view.RemotableViewMethod;
import java.util.ArrayList;
import java.util.List;

import cn.xdc.scorerecord.R;

public class BannerImage {
    @android.view.RemotableViewMethod(asyncImpl="setImageResourceAsync")
    public BannerImage(@DrawableRes int  pictureRes){
        this.pictureRes = pictureRes;
    }
    public BannerImage(){

    }
    private Integer pictureRes  ;
    @android.view.RemotableViewMethod(asyncImpl="setImageResourceAsync")
    public void setPictureLists(@DrawableRes  Integer  pictureRes) {
        this.pictureRes = pictureRes;
    }

    @NonNull
    @Override
    public String toString() {
        return pictureRes.toString();
    }

    public Integer getPictureRes() {
        return pictureRes;
    }
}
