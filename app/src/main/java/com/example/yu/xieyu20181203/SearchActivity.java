package com.example.yu.xieyu20181203;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yu.xieyu20181203.bean.Recent;
import com.example.yu.xieyu20181203.sqlite.SearchDao;
import com.example.yu.xieyu20181203.view.SearchView;
import com.example.yu.xieyu20181203.view.TitleBar;

import java.util.List;
import java.util.UUID;

public class SearchActivity extends AppCompatActivity {

    private String[] strings=new String[]{"考拉三周年人气热销榜","电动牙刷","尤妮佳","豆豆鞋","沐浴露","日东红茶","榴莲"};

    TitleBar titleBar;
    SearchView recent,found;
    SearchDao dao;
    Button delAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

        final List<Recent> listf = dao.selF();
        if(listf.size()==0){
            for(String str:strings){
                UUID uuid=UUID.randomUUID();
                final TextView textView=new TextView(SearchActivity.this);
                textView.setText(str);
                textView.setTag(uuid);
                textView.setTextSize(20);
                textView.setBackgroundResource(R.drawable.text_bg);
                found.addView(textView);
                dao.addF(str,String.valueOf(uuid));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SearchActivity.this,textView.getText(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else{
            for(int i=0;i<listf.size();i++){
                final TextView textView=new TextView(SearchActivity.this);
                textView.setText(listf.get(i).getContent());
                textView.setTag(listf.get(i).getUuid());
                textView.setTextSize(20);
                textView.setBackgroundResource(R.drawable.text_bg);
                found.addView(textView);
                final int finalI = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = dao.selF(listf.get(finalI).getUuid());
                        Toast.makeText(SearchActivity.this,s,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        titleBar.setImageClickListener(new TitleBar.ImageClickListener() {
            @Override
            public void onImageClick(String str) {
                if(!str.equals("")){
                    final TextView textView=new TextView(SearchActivity.this);
                    UUID uuid=UUID.randomUUID();
                    textView.setTag(uuid);
                    textView.setText(str);
                    textView.setTextSize(24);
                    textView.setBackgroundResource(R.drawable.text_bg);
                    dao.addR(str,String.valueOf(uuid));
                    recent.addView(textView);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(SearchActivity.this,textView.getText(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        List<Recent> recents = dao.selR();
        if(recents.size()!=0){
            for(final Recent recen:recents){
                final TextView textView=new TextView(SearchActivity.this);
                textView.setTag(recen.getUuid());
                textView.setText(recen.getContent());
                textView.setTextSize(24);
                textView.setBackgroundResource(R.drawable.text_bg);
                recent.addView(textView);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = dao.selR(recen.getUuid());
                        Toast.makeText(SearchActivity.this,s,Toast.LENGTH_SHORT).show();
                    }
                });
            }
            //清空最近搜索
            delAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dao.delR();
                    recent.removeAllViews();
                }
            });
        }


    }

    private void init() {
        titleBar=findViewById(R.id.titlebar);
        recent=findViewById(R.id.recent);
        found=findViewById(R.id.found);
        dao=new SearchDao(SearchActivity.this);
        delAll=findViewById(R.id.delAll);
    }
}
