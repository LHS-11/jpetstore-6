package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class Board implements Serializable {

    private static final long serialVersionUID = 8751202105532159742L;

    private int bno;
    private String userid;
    private String title;
    private String contents;

    public int getBno() {
        return bno;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public void setUser(Account account){
        userid = account.getUsername();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}
