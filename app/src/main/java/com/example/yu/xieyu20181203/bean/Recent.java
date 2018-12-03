package com.example.yu.xieyu20181203.bean;

public class Recent {

    String content;
    String uuid;

    public Recent(String content, String uuid) {
        this.content = content;
        this.uuid = uuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
