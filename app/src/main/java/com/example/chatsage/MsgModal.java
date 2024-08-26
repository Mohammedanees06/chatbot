package com.example.chatsage;

// we have to create modal class to fetch data ,so tht  we can store this data inside modal class
//this msg modal holds the data tht we get from api
public class MsgModal {
    private String cnt;

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public MsgModal(String cnt) {
        this.cnt = cnt;
    }
}
