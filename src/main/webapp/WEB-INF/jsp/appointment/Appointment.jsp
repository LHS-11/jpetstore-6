<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/IncludeTop.jsp"%>

<stripes:form beanclass="org.mybatis.jpetstore.web.actions.AppointmentActionBean">
    <h3>예약</h3>
    <p>ITEM ID : ${actionBean.appointment.itemId}</p>
    <p>DATE</p>
    <p><input type="date" name="appointmentDate"></p>

    <p>TIME</p>
    <p><stripes:select name="appointmentTime">
        <stripes:options-collection collection="${actionBean.timeList}" />
    </stripes:select>
    </p>
    <!--     <p><input type="time" step="3600" name="appointmentTime"></p> -->

    <stripes:param name="itemId" value="${actionBean.appointment.itemId}"/>
    <stripes:submit name="newAppointment" value="예약신청" />

</stripes:form>

<%@ include file="../common/IncludeBottom.jsp"%>