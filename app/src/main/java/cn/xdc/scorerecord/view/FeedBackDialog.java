package cn.xdc.scorerecord.view;



import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import cn.xdc.scorerecord.R;

/**
 * author:domekisuzi
 * time:2022/8/30
 * 学长会的，为数不多的自定义view
 */

public class FeedBackDialog extends Dialog {

    private Button sureBtm,cancelBtm;
    private TextView title;
    private EditText editText;

    private String textTitle;
    private String editContent;

    public FeedBackDialog(@NonNull Context context) {
        super(context);
    }
    public FeedBackDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FeedBackDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_feedback_dialog);
        getWindow().setGravity(Gravity.CENTER);//设置显示在屏幕中央
//        WindowManager windowManager = getWindow().getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        initView();
        initEvent();
    }

    public String getTextTitle() {
        if(Objects.nonNull(title)){
            return textTitle;
        }
        else {
            return  " ";
        }
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
        if (title!=null)
        title.setText(textTitle);
    }

    public String getEditContent() {
        if (Objects.nonNull(editText))
        return editText.getText().toString();
        else return  editContent;
    }

    public void setEditContent(String editContent) {
        this.editContent = editContent;
        if (editText!=null)
        editText.setText(editContent);
    }

    private void initView(){
        sureBtm = findViewById(R.id.dialog_sure);
        cancelBtm = findViewById(R.id.dialog_cancel);
        editText = findViewById(R.id.dialog_edit);
        title = findViewById(R.id.dialog_title);
    }
    private void initEvent(){
        sureBtm.setOnClickListener(view -> {
            if (onClickBottomListener!=null){
                onClickBottomListener.onPositiveClick();
            }
        });
        cancelBtm.setOnClickListener(view -> {
            if (onClickBottomListener!=null){
                onClickBottomListener.onNegativeClick();
            }
        });
    }

    /**
     * 开放接口让子类去继承
     */
     public interface  OnClickBottomListener{
        void onPositiveClick();
        void onNegativeClick();
    }
    public OnClickBottomListener onClickBottomListener;

    public  FeedBackDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener){
        this.onClickBottomListener  = onClickBottomListener;
        return  this;
    }
}

