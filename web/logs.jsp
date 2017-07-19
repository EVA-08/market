<%@ page import="entiry.Commodity" %>
<%@ page import="java.util.List" %>
<%@ page import="entiry.Log" %>
<%@ page import="entiry.Action" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/16
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Title</title>
  <script src="${pageContext.request.contextPath}/jquery-easyui-1.5.2/jquery.min.js"></script>
  <!-- 引入EasyUI -->
  <script type="text/javascript"
          src="${pageContext.request.contextPath}/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
  <!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
  <script type="text/javascript"
          src="${pageContext.request.contextPath}/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
  <!-- 引入EasyUI的样式文件-->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.2/themes/default/easyui.css"
        type="text/css"/>
  <!-- 引入EasyUI的图标样式文件-->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.2/themes/icon.css" type="text/css"/>

</head>

<body>
<div ID="tb">
  <%
    Date startDate = (Date) session.getAttribute("startDate");
    Date endDate = (Date) session.getAttribute("endDate");
  %>
  <form action="server?service=queryLogs" method="post">
    开始时间:<input name="startDate" type="text" class="easyui-datebox" required="required"
    <%="value=\"" + startDate + "\""%>
  >
    结束时间:<input name="endDate" type="text" class="easyui-datebox" required="required"
    <%="value=\"" + endDate + "\""%>
  >
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="$('#tb form').submit()">查询</a>
  </form>

</div>
<table ID="dg" class="easyui-datagrid" style="width:600px;height:250px"
       toolbar="#tb"
    <%
      out.print("title=\"" + startDate + " ---- " + endDate + "进出库记录\"");
    %>
       rownumbers="true" 
       toolbar="#tb" singleSelect="true">
  <thead>
  <tr>
    <th field="ID" align="center">ID</th>
    <th field="name" align="center">商品名</th>
    <th field="type" align="center">种类</th>
    <th field="price" align="center">单价</th>
    <th field="count" align="center">数量</th>
    <th field="supplier" align="center">供应商</th>
    <th field="productionDate" align="center">生产时间</th>
    <th field="guaranteePeriod" align="center">保质期</th>
    <th field="action" align="center">进出库</th>
    <th field="date" align="center">进出库时间</th>
  </tr>
  </thead>
  <tbody>
  <%
    List<Log> logs = (List<Log>) session.getAttribute("logs");
    for (Log log : logs) {
      Commodity commodity = log.getCommodity();
      Action action = log.getAction();
      out.print("<tr>");
      out.print("<td>" + commodity.getID() + "</td>");
      out.print("<td>" + commodity.getName() + "</td>");
      out.print("<td>" + commodity.getType() + "</td>");
      out.print("<td>" + commodity.getPrice() + "</td>");
      out.print("<td>" + commodity.getCount() + "</td>");
      out.print("<td>" + commodity.getSupplier() + "</td>");
      out.print("<td>" + commodity.getProductionDate() + "</td>");
      out.print("<td>" + commodity.getGuaranteePeriod() + "</td>");
      out.print("<td>" + action.getAction() + "</td>");
      out.print("<td>" + action.getDate() + "</td>");
    }
  %>
  </tbody>
</table>

<p>
  总收入:<%=(BigDecimal) session.getAttribute("incomeSum")%>
</p>
<p>
  总支出:<%=(BigDecimal) session.getAttribute("expenseSum")%>
</p>
</body>
</html>
