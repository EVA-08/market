<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="entiry.Commodity" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="entiry.UserInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/8
  Time: 19:28
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
  <script>
      function update() {
          var row = $('#dg').datagrid('getSelected');
          if (row === null) {
              alert("请先选择");
              return;
          }
          var inputs = $("#update-dlg input");
          inputs[0].setAttribute("value", row.name);
          inputs[1].setAttribute("value", row.type);
          inputs[2].setAttribute("value", row.price);
          inputs[3].setAttribute("value", row.count);
          inputs[4].setAttribute("value", row.supplier);
          inputs[5].setAttribute("value", row.productionDate);
          inputs[6].setAttribute("value", row.guaranteePeriod);
          inputs[7].setAttribute("value", row.ID);
          $("#update-dlg span").html(row.ID);
          $("#update-dlg").dialog("open");
      }

      function add() {
          $("#add-dlg").dialog("open");
      }

      function remove() {
          var row = $('#dg').datagrid('getSelected');
          if (row === null) {
              alert("请先选择");
              return;
          }
          if (confirm("确认删除？")) {
              window.location = "/server?service=removeCommodity&ID=" + row.ID;
          }
      }

      <%
        Boolean updateSucceeded = (Boolean)session.getAttribute("updateSucceeded");
        if (updateSucceeded != null) {
            if (updateSucceeded) {
                out.print("alert('更新成功');");
            } else {
                out.print("alert('更新失败');");
            }
            session.removeAttribute("updateSucceeded");
        }
      %>
      <%
        Boolean addSucceeded = (Boolean)session.getAttribute("addSucceeded");
        if (addSucceeded != null) {
            if (addSucceeded) {
                out.print("alert('插入成功');");
            } else {
                out.print("alert('插入失败');");
            }
            session.removeAttribute("addSucceeded");
        }
      %>
      <%
        Boolean removeSucceeded = (Boolean)session.getAttribute("removeSucceeded");
        if (removeSucceeded != null) {
            if (removeSucceeded) {
                out.print("alert('删除成功');");
            } else {
                out.print("alert('删除失败');");
            }
            session.removeAttribute("removeSucceeded");
        }
      %>

  </script>

</head>
<body>
<div ID="tb">
  <%
    UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
    String authority = userInfo.getAuthority();
  %>
  <%
    if ("employee".equals(authority)) {
      out.print("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"add()\">插入</a>");
      out.print("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" plain=\"true\" onclick=\"update()\">修改</a>");
      out.print("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" onclick=\"remove()\">删除</a>");
    }
  %>
  <span>商品名:</span>
  <form action="server?service=queryCommodities" method="post" style="display: inline">
    <input name="name">
  </form>
  <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="$('#tb form').submit()">查询</a>
</div>
<table ID="dg" class="easyui-datagrid" style="width:600px;height:250px"
       toolbar="#tb"
       title="商品管理"
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
  </tr>
  </thead>
  <tbody>
  <%
    List<Commodity> commodities = (List<Commodity>) session.getAttribute("commodities");
    for (Commodity commodity : commodities) {
      out.print("<tr>");
      out.print("<td>" + commodity.getID() + "</td>");
      out.print("<td>" + commodity.getName() + "</td>");
      out.print("<td>" + commodity.getType() + "</td>");
      out.print("<td>" + commodity.getPrice() + "</td>");
      out.print("<td>" + commodity.getCount() + "</td>");
      out.print("<td>" + commodity.getSupplier() + "</td>");
      out.print("<td>" + commodity.getProductionDate() + "</td>");
      out.print("<td>" + commodity.getGuaranteePeriod() + "</td>");
    }
  %>
  </tbody>
</table>
<div ID="update-dlg" class="easyui-dialog" style="padding:5px;width:400px;height:200px;"
     title="修改商品信息" iconCls="icon-ok"
     buttons="#update-dlg-buttons"
     modal="true" closed="true">
  <form action="server?service=updateCommodity" method="post">
    ID: <span></span><br>
    商品名称: <input required="required" type="text" name="name"><br>
    种类: <input required="required" type="text" name="type"><br>
    价格: <input required="required" type="text" name="price"><br>
    数量: <input required="required" type="text" name="count"><br>
    供应商: <input required="required" type="text" name="supplier"><br>
    生产日期：<input required="required" type="date" name="productionDate"><br>
    保质期: <input required="required" type="text" name="guaranteePeriod"><br>
    <input type="hidden" name="ID">
  </form>
</div>

<div ID="update-dlg-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="return $('#update-dlg form').submit()">修改</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#update-dlg').dialog('close')">取消</a>
</div>

<div ID="add-dlg" class="easyui-dialog" style="padding:5px;width:400px;height:200px;"
     title="增加商品信息" iconCls="icon-ok"
     buttons="#add-dlg-buttons"
     modal="true" closed="true">
  <form action="server?service=addCommodity" method="post">
    ID: <input required="required" type="text" name="ID"><br>
    商品名称: <input required="required" type="text" name="name"><br>
    种类: <input required="required" type="text" name="type"><br>
    价格: <input required="required" type="text" name="price"><br>
    数量: <input required="required"type="text" name="count"><br>
    供应商: <input required="required" type="text" name="supplier"><br>
    生产日期：<input required="required" type="date" name="productionDate"><br>
    保质期: <input required="required" type="text" name="guaranteePeriod"><br>
  </form>
</div>

<div ID="add-dlg-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="$('#add-dlg form').submit()">增加</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#add-dlg').dialog('close')">取消</a>
</div>

</body>
</html>
