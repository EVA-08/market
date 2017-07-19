<%@ page import="java.util.List" %>
<%@ page import="entiry.Market" %><%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/11
  Time: 10:18
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
          $("#update-dlg span").html(row.marketID);
          inputs[0].setAttribute("value", row.name);
          inputs[1].setAttribute("value", row.address);
          inputs[2].setAttribute("value", row.tel);
          inputs[3].setAttribute("value", row.marketID);
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
              window.location = "/server?service=removeMarket&marketID=" + row.marketID;
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

      <%
          Boolean createSucceeded = (Boolean)session.getAttribute("createSucceeded");
          if (createSucceeded != null) {
              if (createSucceeded) {
                  out.print("alert('创建成功');");
              } else {
                  out.print("alert('创建失败');");
              }
              session.removeAttribute("createSucceeded");
          }
        %>


  </script>

</head>
<body>
<table id="dg" title="超市管理" class="easyui-datagrid" style="width:550px;height:250px"
       toolbar="#tb" 
       rownumbers="true" fitColumns="true" singleSelect="true">
  <thead>
  <tr>
    <th field="marketID">超市ID</th>
    <th field="name">超市名</th>
    <th field="address">地址</th>
    <th field="tel">联系电话</th>

  </tr>
  </thead>
  <tbody>
  <%
    List<Market> markets = (List<Market>) session.getAttribute("markets");
    for (Market market: markets) {
      out.print("<tr>");
      out.print("<td>" + market.getMarketID() + "</td>");
      out.print("<td>" + (market.getName() == null? "" : market.getName()) + "</td>");
      out.print("<td>" + (market.getAddress() == null ? "" : market.getAddress()) + "</td>");
      out.print("<td>" + (market.getTel() == null? "" : market.getTel()) + "</td>");
      out.print("</tr>");
    }
  %>
  </tbody>
</table>
<div id="tb">
  <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">插入</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="remove()">删除</a>
  <span>超市名:</span>
  <form action="server?service=queryMarkets" method="post" style="display: inline">
    <input required="required" name="name">
  </form>
  <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="$('#tb form').submit()">查询</a>
</div>

<div ID="add-dlg" class="easyui-dialog" style="padding:5px;width:400px;height:200px;"
     title="增加超市" iconCls="icon-ok"
     buttons="#add-dlg-buttons"
     modal="true" closed="true">
  <form action="server?service=addMarket" method="post">
    超市ID: <input required="required" type="text" name="marketID"><br>
    超市名: <input required="required" type="text" name="name"><br>
    地址: <input type="text" name="address"><br>
    联系电话: <input type="text" name="tel"><br>
  </form>
</div>

<div ID="add-dlg-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="$('#add-dlg form').submit()">增加</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#add-dlg').dialog('close')">取消</a>
</div>

<div ID="update-dlg" class="easyui-dialog" style="padding:5px;width:400px;height:200px;"
     title="修改超市" iconCls="icon-ok"
     buttons="#update-dlg-buttons"
     modal="true" closed="true">
  <form action="server?service=updateMarket" method="post">
    超市ID: <span></span><br>
    超市名: <input required="required" type="text" name="name"><br>
    地址: <input type="text" name="address"><br>
    联系电话: <input type="text" name = "tel"><br>
    <input type="hidden" name="marketID"><br>
  </form>
</div>

<div ID="update-dlg-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="$('#update-dlg form').submit()">修改</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#update-dlg').dialog('close')">取消</a>
</div>
</body>
</html>
