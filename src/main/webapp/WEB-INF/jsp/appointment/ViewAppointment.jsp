<%--
  Created by IntelliJ IDEA.
  User: 0419r
  Date: 2021-11-27
  Time: 오전 12:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/IncludeTop.jsp"%>

<stripes:form beanclass="org.mybatis.jpetstore.web.actions.AppointmentActionBean" focus="">

  <h2>예약 조회</h2>
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
      <c:if test="${actionBean.appointment.itemId != null}">
      <td><stripes:link
              beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
              event="viewItem">
        <stripes:param name="itemId" value="${actionBean.appointment.itemId}" />
        ${actionBean.appointment.itemId}
      </stripes:link>
        </c:if>
      </td>
    </tr>
  </table>
  <stripes:link class="Button"
                beanclass="org.mybatis.jpetstore.web.actions.AppointmentActionBean"
                event="deleteAppointment">
    <stripes:param name="userid" value="${actionBean.appointment.userid}" />
    예약취소
  </stripes:link>

  <td>
    <stripes:link class="Button"
                  beanclass="org.mybatis.jpetstore.web.actions.AppointmentActionBean"
                  event="modifyAppointmentForm">
      예약수정
    </stripes:link>
  </td>
</stripes:form>
<%@ include file="../common/IncludeBottom.jsp"%>