package org.mybatis.jpetstore.service;

import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Appointment;
import org.mybatis.jpetstore.domain.LineItem;
import org.mybatis.jpetstore.mapper.AppointmentMapper;
import org.mybatis.jpetstore.web.actions.AccountActionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private MailService mailService;


    @Transactional
    public void insertAppointment(Appointment appointment){
        appointmentMapper.insertAppointment(appointment);
    }

    @Transactional
    public Appointment getAppointment(String userid){
        return appointmentMapper.getAppointmentByUserid(userid);
    }

    @Transactional
    public void modifyAppointment(Appointment appointment){
        appointmentMapper.modifyAppointment(appointment);
    }

    @Transactional
    public void deleteAppointment(String userid){
        appointmentMapper.deleteAppointment(userid);
    }

    @Transactional
    public int getCountByAppointment(String appointmentTime, String appointmentDate){

        return appointmentMapper.getCountByAppointment(appointmentTime, appointmentDate);
    }

    @Transactional
    public void sendEmailChecking(String userid) {appointmentMapper.sendEmailChecking(userid);}

    //시간이 지난 예약 자동 취소
    @Transactional
    @Scheduled(fixedDelay=10000) //10초 간격
    public void AutoDeleteAppointment(){
        List<Appointment> appointmentList = appointmentMapper.getAppointmentList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        for(Appointment appointment : appointmentList){
            LocalDate nowDate = LocalDate.now();
            LocalTime nowTime = LocalTime.now();
            String stringDate = appointment.getAppointmentDate();
            LocalDate localDate = LocalDate.parse(stringDate);

            String stringTime = appointment.getAppointmentTime();
            LocalTime localTime = LocalTime.parse(stringTime, formatter);

            if(nowDate.isEqual(localDate) && nowTime.isAfter(localTime)){
                appointmentMapper.deleteAppointment(appointment.getUserid());
                System.out.println("현재날짜 : " + nowDate + ", 현재시간 : "  + nowTime + appointment.getUserid() + "님의 예약시간이 지나 취소되었습니다.");
            }
        }
    }

    //하루 전 예약 전송
    @Transactional
    @Scheduled(fixedDelay=10000) //하루 간격
    public void AutoSendAppointment(){
        List<Appointment> appointmentList1 = appointmentMapper.getAppointmentList();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

        Calendar day=Calendar.getInstance();
        day.add(Calendar.DATE,1);
        String beforeDate=new SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
        String beforeTime=new SimpleDateFormat("HH:mm").format(day.getTime());

        System.out.println("test");
        for(Appointment appointment : appointmentList1){

            String stringDate = appointment.getAppointmentDate();
            String stringTime = appointment.getAppointmentTime();



            Appointment appointment1 = appointmentMapper.getAppointmentByUserid(appointment.getUserid());


            if(stringDate.equals(beforeDate)) {
                System.out.print("날짜 확인");
                String fromAdd = "ghktjq1118@gmail.com";
                String toAddress = appointment1.getEmail();
                String userID = appointment1.getUserid();
                String itemId = appointment1.getItemId();
                String appointmentDate = appointment1.getAppointmentDate();
                String appointmentTime = appointment1.getAppointmentTime();
                int valid = appointment1.getValid();
                System.out.println(appointment1);
                String title = userID + " 님 JpetStore 예약 하루 전 안내드립니다. ";
                String content = "주문자 : " + userID + "\n" + "예약한 동물: " + itemId + "\n" +
                        "방문 일시 : " + appointmentDate + " " + appointmentTime;

                if (valid == 0) {
                    mailService.sendEmail(toAddress, fromAdd, title, content);
                    appointmentMapper.sendEmailChecking(userID);
                }
            }

        }
    }
}