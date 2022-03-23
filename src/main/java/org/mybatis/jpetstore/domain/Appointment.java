package org.mybatis.jpetstore.domain;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {

    private static final long serialVersionUID = 1341153579667308942L;

    private int id;
    private String appointmentDate;
    private String appointmentTime;
    private String userid;
    private String itemId;
    //Appoint 도메인
    private String email;
    private int valid;

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

}