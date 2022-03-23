<%--
  Created by IntelliJ IDEA.
  User: 82107
  Date: 2021-11-14
  Time: 오후 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/IncludeTop.jsp"%>
<div><stripes:form
        beanclass="org.mybatis.jpetstore.web.actions.BoardActionBean"
        focus="">

        <h3>게시물 작성</h3>

        <table>
            <tr>
                <td>Title : </td>
                <td><stripes:text name="title"/></td>
            </tr>
            <tr>
                <td>Contents : </td>
                <td><stripes:textarea name="contents" /></td>
            </tr>
        </table>

        <stripes:submit name="newPost" value="Save Post" />
</stripes:form></div>

<%@ include file="../common/IncludeBottom.jsp"%>

