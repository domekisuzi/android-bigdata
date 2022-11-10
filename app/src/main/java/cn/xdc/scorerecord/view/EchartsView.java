package cn.xdc.scorerecord.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


/**
 * echartview,里面封装了echart的配置
 */
public class EchartsView extends WebView {
    public EchartsView(@NonNull Context context) {
        super(context);
        init();
        Log.d("webview", "调用的第一个");
    }

    public EchartsView( @NonNull  Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        Log.d("webview", "调用的第二个");

    }

    public EchartsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        Log.d("webview", "调用的第三个");
    }

    @SuppressLint("SetJavaScriptEnabled")
    //配置webview的配置,包括开启JavaScript等
    public void init() {
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);
        //可以在这里开启
    }


    /**
     * 当直接使用整个javascript时,可以成功,但会导致变得非常卡
     */

    public void setPieChartData(String str){
        String call = "javascript:setPieChartData('"+str+ "')";
        System.out.println("call = " + call);
        loadUrl(call);
    }

//    public void setEntry(){
//        String call = "javascript:setEntry("+ ")";
//        Log.e("echartView",call);
//        loadUrl(call);
//    }
//

//    public void setDetailNew(String data){
//        String call = "javascript:newDetail('"+data+"')";
//        Log.v("详情数据为",call);
//        loadUrl(call);
//    }
}