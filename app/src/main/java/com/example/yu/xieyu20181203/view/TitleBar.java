package com.example.yu.xieyu20181203.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.yu.xieyu20181203.R;

public class TitleBar extends LinearLayout {

    Context mContext;
    ImageClickListener mimageClickListener;

    public TitleBar(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    private void init() {
        View view=inflate(mContext, R.layout.title,null);
        final EditText editText=view.findViewById(R.id.edit);
        view.findViewById(R.id.add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mimageClickListener!=null){
                    mimageClickListener.onImageClick(editText.getText().toString());
                }
            }
        });
        addView(view);

    }

    public void setImageClickListener(ImageClickListener imageClickListener){
        mimageClickListener=imageClickListener;
    }

    public interface ImageClickListener{
        void onImageClick(String str);
    }

}
