package com.example.yu.xieyu20181203.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yu.xieyu20181203.bean.Recent;

import java.util.ArrayList;
import java.util.List;

public class SearchDao {

    SQLiteDatabase database;


    public SearchDao(Context context) {
        database=new SqliteHelper(context).getWritableDatabase();
    }
    //最近搜索数据的更改

    //添加
    public void addR(String content,String uuid){
        ContentValues values=new ContentValues();
        values.put("content",content);
        values.put("uuid",uuid);
        database.insert("recent",null,values);
    }
    public void delR(String uuid){
        database.delete("recent","uuid=?",new String[]{uuid});
    }

    public void delR(){
        database.delete("recent",null,null);
    }
    public String selR(String uuid) {
        String content=null;
        Cursor query = database.query("recent", null, "uuid=?", new String[]{uuid}, null, null, null);
        while(query.moveToNext()){
            content = query.getString(query.getColumnIndex("content"));
        }
        return content;
    }
    public List<Recent> selR(){
        List<Recent> list=new ArrayList<>();
        Cursor query = database.query("recent", null, null, null, null, null, null);
        while (query.moveToNext()){
            String content = query.getString(query.getColumnIndex("content"));
            String uuid = query.getString(query.getColumnIndex("uuid"));
            list.add(new Recent(content,uuid));
        }
        return list;
    }
    //搜索发现的数据更改
    public List<Recent> selF() {
        List<Recent> list=new ArrayList<>();
        Cursor query = database.query("found", null, null, null, null, null, null);
        while (query.moveToNext()){
            String content = query.getString(query.getColumnIndex("content"));
            String uuid = query.getString(query.getColumnIndex("uuid"));
            list.add(new Recent(content,uuid));
        }
        return list;
    }
    public void addF(String content,String uuid) {
        ContentValues values=new ContentValues();
        values.put("content",content);
        values.put("uuid",uuid);
        database.insert("found",null,values);
    }

    public String selF(String uuid) {
        String content=null;
        Cursor query = database.query("found", null, "uuid=?", new String[]{uuid}, null, null, null);
        while(query.moveToNext()){
            content = query.getString(query.getColumnIndex("content"));
        }
        return content;
    }
}
