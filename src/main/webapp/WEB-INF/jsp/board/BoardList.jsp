<%--
  Created by IntelliJ IDEA.
  User: 82107
  Date: 2021-11-11
  Time: 오후 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/IncludeTop.jsp"%>

<h2>게시물 조회</h2>

<table>
    <tr>
        <th>게시물 번호</th>
        <th>Writer</th>
        <th>Title</th>
    </tr>
    <c:forEach var="board" items="${actionBean.boardList}">
        <tr>
            <td><stripes:link
                beanclass="org.mybatis.jpetstore.web.actions.BoardActionBean"
                event="viewPost">
                <stripes:param name="bno" value="${board.bno}" />
                ${board.bno}
            </stripes:link></td>
            <td>${board.userid}</td>
            <td>${board.title}</td>
        </tr>
    </c:forEach>
</table>


<stripes:link class="Button"
        beanclass="org.mybatis.jpetstore.web.actions.BoardActionBean"
        event="newPostForm">
    글쓰기</stripes:link>



<%@ include file="../common/IncludeBottom.jsp"%>

