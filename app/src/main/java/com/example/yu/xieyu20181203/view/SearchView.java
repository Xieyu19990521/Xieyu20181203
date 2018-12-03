package com.example.yu.xieyu20181203.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class SearchView extends LinearLayout {

    int mChildHeight;
    int mSpecW=20,mSpecH=20;

    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight=MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(sizeWidth,sizeHeight);
        findMaxChildHeight();

        int left=0,top=0;
        int child=getChildCount();
        for(int i=0;i<child;i++){
            View view=getChildAt(i);
            if(left!=0){
                if(left+view.getMeasuredWidth()>sizeWidth){
                    top+=mChildHeight+mSpecH;
                    left=0;
                }
            }
            left+=view.getMeasuredWidth()+mSpecW;
        }
        setMeasuredDimension(sizeWidth,(top+mChildHeight)>sizeHeight?sizeHeight:top+mChildHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        findMaxChildHeight();
        int left=0,top=0;
        int childCount=getChildCount();
        for(int i=0;i<childCount;i++){
            View view=getChildAt(i);
            if(left!=0){
                if(left+view.getMeasuredWidth()>getWidth()){
                    top+=view.getMeasuredHeight()+mSpecH;
                    left=0;
                }
            }
            view.layout(left,top,left+view.getMeasuredWidth(),top+view.getMeasuredHeight());
            left+=view.getMeasuredWidth()+mSpecW;
        }
    }

    private void findMaxChildHeight() {
        mChildHeight=0;
        int  count=getChildCount();
        for(int i=0;i<count;i++){
            View view=getChildAt(i);
            if(view.getMeasuredHeight()>mChildHeight){
                mChildHeight=view.getMeasuredHeight();
            }
        }
    }
}
