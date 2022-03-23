<%--
  Created by IntelliJ IDEA.
  User: 0419r
  Date: 2021-11-30
  Time: 오전 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/IncludeTop.jsp"%>
<stripes:form beanclass="org.mybatis.jpetstore.web.actions.AppointmentActionBean"
              focus="">
  <h2>예약상태</h2>
  <table>
    <tr>
      <td>UserID</td>
      <td>${actionBean.appointment.userid}</td>
    </tr>
    <tr>
      <td>DATE</td>
      <td>${actionBean.appointment.appointmentDate}</td>
    </tr>
    <tr>
      <td>TIME</td>
      <td>${actionBean.appointment.appointmentTime}</td>
    </tr>
    <tr>
      <td>ITEM ID</td>
      <td>${actionBean.appointment.itemId}</td>
    </tr>
  </table>
  <h2>예약수정</h2>
  <p>DATE</p>
  <p><input type="date" name="appointmentDate"></p>

  <p>TIME</p>
  <p>
    <stripes:select name="appointmentTime">
      <stripes:options-collection collection="${actionBean.timeList}" />
    </stripes:select>
  </p>

  <stripes:param name="itemId" value="${actionBean.appointment.itemId}" />
  <stripes:submit name="modifyAppointment" value="submit" />
</stripes:form>
<%@ include file="../common/IncludeBottom.jsp"%>