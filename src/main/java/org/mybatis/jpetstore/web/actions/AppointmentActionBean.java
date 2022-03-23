package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Account;
import org.mybatis.jpetstore.domain.Appointment;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.service.AppointmentService;
import org.mybatis.jpetstore.service.CatalogService;
import org.mybatis.jpetstore.service.MailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class AppointmentActionBean extends AbstractActionBean{

    private static final String APPOINTMENT="/WEB-INF/jsp/appointment/Appointment.jsp";
    private static final String VIEW_APPOINTMENT="/WEB-INF/jsp/appointment/ViewAppointment.jsp";
    private static final String MODIFY_APPOINTMENT="/WEB-INF/jsp/appointment/ModifyAppointment.jsp";

    private static final List<String> TIME_LIST;

    static {
        TIME_LIST = Collections.unmodifiableList(
                (Arrays.asList("4:13","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00"))
        );
    }

    @SpringBean
    private transient AppointmentService appointmentService;

    @SpringBean
    private transient CatalogService catalogService;


    @SpringBean
    private transient MailService mailService;

    private Account account = new Account();

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private int id;
    private String userid;
    private String appointmentDate;
    private String appointmentTime;
    private String itemId;

    int limitAppointment = 5;

    public int getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public String getUserid(){
        return account.getUsername();
    }

    public void setUserid(String userid){
        account.setUsername(userid);
    }

    public void setAppointmentDate(String date){
        this.appointmentDate = date;
    }

    public String getAppointmentDate(){
        return this.appointmentDate;
    }

    public void setAppointmentTime(String time){
        this.appointmentTime = time;
    }

    public String getAppointmentTime(){
        return this.appointmentTime;
    }

    public String getItemId(){
        return this.itemId;
    }

    public void setItemId(){
        this.itemId = itemId;
    }

    private Item item = new Item();

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<String> getTimeList() {
        return TIME_LIST;
    }

    public Appointment appointment = new Appointment();

    public Appointment getAppointment(){
        return this.appointment;
    }

    public Resolution newAppointmentForm(){
        HttpSession session = context.getRequest().getSession();
        HttpServletRequest request = context.getRequest();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("accountBean");

        if(accountBean == null || !accountBean.isAuthenticated()){
            setMessage("You must sign on before attempting to check out.  Please sign on and try checking out again.");
            return new ForwardResolution(AccountActionBean.class);

        }else{
            appointment.setItemId(request.getParameter("itemId"));
            return new ForwardResolution(APPOINTMENT);

        }
    }

    public Resolution newAppointment(){
        HttpSession session = context.getRequest().getSession();
        HttpServletRequest request = context.getRequest();

        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");

        appointment.setUserid(accountBean.getUsername());
        appointment.setAppointmentTime(request.getParameter("appointmentTime"));
        appointment.setAppointmentDate(request.getParameter("appointmentDate"));
        appointment.setItemId(request.getParameter("itemId"));

        LocalDate nowDate = LocalDate.now();//오늘 날짜
        String stringDate = appointment.getAppointmentDate(); //예약 날짜
        LocalDate localDate = LocalDate.parse(stringDate); //예약 날짜

        if(appointmentService.getAppointment(accountBean.getUsername()) == null){

            if(!nowDate.isBefore(localDate)) {
                setMessage("예약이 불가능한 날짜입니다.");

                return new ForwardResolution(APPOINTMENT);
            }

            else if(appointmentService.getCountByAppointment(
                    appointment.getAppointmentTime(), appointment.getAppointmentDate()) >= limitAppointment){
                setMessage("해당 날짜 예약 가능 인원이 꽉 찼습니다.");

                return new ForwardResolution(APPOINTMENT);
            }

            else {
                String firstname = accountBean.getFirstname();
                String lastname = accountBean.getLastname();
                String fromAdd = "ghktjq1118@gmail.com";
                String toAddress = accountBean.getUsermail();
                String userID = accountBean.getId();
                String title= userID + " 님 JpetStore 예약 정보입니다. ";
                String content = "예약자 이름 : " +firstname + lastname + "\n"
                        + "날짜: "+appointment.getAppointmentDate() +"\n"
                        + "시간 : "+ appointment.getAppointmentTime() +"\n";
                appointmentService.insertAppointment(appointment);
                mailService.sendEmail(toAddress,fromAdd,title,content);

            }

            return new ForwardResolution(VIEW_APPOINTMENT);
        }

        else{

            appointment = null;
            String value = "Already Exist Appointment.";
            setMessage(value);

            appointment = appointmentService.getAppointment(accountBean.getUsername());
            return new ForwardResolution(VIEW_APPOINTMENT);
        }
    }

    public ForwardResolution viewAppointment(){

        HttpSession session = context.getRequest().getSession();
        HttpServletRequest request = context.getRequest();

        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("accountBean");

        if(accountBean == null || !accountBean.isAuthenticated()){
            setMessage("You must sign on before attempting to check out.  Please sign on and try checking out again.");
            return new ForwardResolution(AccountActionBean.class);

        }else{

            appointment = appointmentService.getAppointment(accountBean.getUsername());
            return new ForwardResolution(VIEW_APPOINTMENT);
        }

    }


    public ForwardResolution deleteAppointment(){

        HttpSession session = context.getRequest().getSession();

        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("accountBean");

        if(appointmentService.getAppointment(accountBean.getUsername()) == null){
            String value = "You Don't Have Appointment";
            setMessage(value);
            return new ForwardResolution(VIEW_APPOINTMENT);
        }
        else{
            appointmentService.deleteAppointment(accountBean.getUsername());

            return new ForwardResolution(VIEW_APPOINTMENT);
        }
    }

    public ForwardResolution modifyAppointmentForm(){
        HttpSession session = context.getRequest().getSession();

        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("accountBean");

        appointment = appointmentService.getAppointment(accountBean.getUsername());


        
        return new ForwardResolution(MODIFY_APPOINTMENT);
    }

    public ForwardResolution modifyAppointment(){
        HttpSession session = context.getRequest().getSession();
        HttpServletRequest request = context.getRequest();

        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("accountBean");

        appointment.setUserid(accountBean.getUsername());
        appointment.setAppointmentDate(request.getParameter("appointmentDate"));
        appointment.setAppointmentTime(request.getParameter("appointmentTime"));
        appointment.setItemId(request.getParameter("itemId"));

        LocalDate nowDate = LocalDate.now();
        String stringDate = appointment.getAppointmentDate();
        LocalDate localDate = LocalDate.parse(stringDate);

        if(appointmentService.getAppointment(accountBean.getUsername()) == null){
            setMessage("수정 가능한 예약이 없습니다.");
            appointment = null;
            return new ForwardResolution(VIEW_APPOINTMENT);
        }
        else{
//            if(!nowDate.isBefore(localDate)) {
//                setMessage("이미 지난 날짜입니다.");
//
//                appointment = appointmentService.getAppointment(accountBean.getUsername());
//                return new ForwardResolution(MODIFY_APPOINTMENT);
//            }
            if(appointmentService.getCountByAppointment(
                    appointment.getAppointmentTime(), appointment.getAppointmentDate()) >= limitAppointment){
                setMessage("해당 날짜 예약 가능 인원이 꽉 찼습니다.");

                return new ForwardResolution(MODIFY_APPOINTMENT);
            }
            else{
                appointmentService.modifyAppointment(appointment);
            }
        }

        String firstname = accountBean.getFirstname();
        String lastname = accountBean.getLastname();
        String fromAdd = "ghktjq1118@gmail.com";
        String toAddress = accountBean.getUsermail();
        String userID = accountBean.getId();
        String title= userID + " 님 JpetStore 예약 수정 확인 메일입니다. ";
        String content = "예약자 이름 : " +firstname + lastname + "\n"
                + "날짜: "+appointment.getAppointmentDate() +"\n"
                + "시간 : "+ appointment.getAppointmentTime() +"\n";
        mailService.sendEmail(toAddress,fromAdd,title,content);

        return new ForwardResolution(VIEW_APPOINTMENT);
    }
}