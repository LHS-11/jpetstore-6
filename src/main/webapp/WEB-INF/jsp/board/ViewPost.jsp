<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/IncludeTop.jsp"%>

<h3>게시글 조회</h3>

<h2>${actionBean.board.title}</h2>
<table>
    <tr>
        <td>TITLE : </td>
        <td><c:out value="${actionBean.board.title}" /></td>
    </tr>
    <tr>
        <td>WRITER : </td>
        <td><c:out value="${actionBean.board.userid}" /></td>
    </tr>
    <tr>
        <td>CONTENTS : </td>
        <td><c:out value="${actionBean.board.contents}" /></td>
    </tr>

</table>
<tr></tr>
<stripes:link class="Button"
              beanclass="org.mybatis.jpetstore.web.actions.BoardActionBean"
              event="listBoards">
    글목록</stripes:link>

<%@ include file="../common/IncludeBottom.jsp"%>