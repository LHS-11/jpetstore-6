<%--
  Created by IntelliJ IDEA.
  User: Lim
  Date: 2021-11-09
  Time: 오후 4:55
  To change this template use File | Settings | File Templates.
--%>
<<%@ include file="../common/IncludeTop.jsp"%>

<h2>Popular Pets</h2>

<table>
  <tr>
    <th>ItemID</th>
    <th>Description</th>
    <th>Quantity</th>
  </tr>

  <c:forEach var="lineItem" items="${actionBean.lineItems}">
    <tr>
      <td><stripes:link
              beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
              event="viewItem">
        <stripes:param name="itemId" value="${lineItem.itemId}" />
        ${lineItem.itemId}
      </stripes:link>
      </td>
      <td>
          ${lineItem.attr1}
      </td>

      <td>${lineItem.sum}
      </td>
    </tr>
  </c:forEach>
</table>

<%@ include file="../common/IncludeBottom.jsp"%>

