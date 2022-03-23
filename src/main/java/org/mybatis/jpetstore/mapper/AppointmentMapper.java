package org.mybatis.jpetstore.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.jpetstore.domain.Appointment;

import java.util.List;

public interface AppointmentMapper {

    //예약
    void insertAppointment(Appointment appointment);

    //Mapper 예약 확인, 예약 충돌
    Appointment getAppointmentByUserid(String userid);

    //예약 변경
    void modifyAppointment(Appointment appointment);

    //예약 취소
    void deleteAppointment(String userid);

    int getCountByAppointment(@Param("appointmentTime") String appointmentTime, @Param("appointmentDate")String appointmentDate);

    //전체 예약 리스트 가져오기
    List<Appointment> getAppointmentList();

    //이메일 한번만 보내기 위한 체킹
    void sendEmailChecking(String userid);

}