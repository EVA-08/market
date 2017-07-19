<%@ page import="entiry.UserInfo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/14
  Time: 22:57
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
      <%
        Boolean passSucceeded = (Boolean)session.getAttribute("passSucceeded");
          if (passSucceeded != null) {
              if (passSucceeded) {
                  out.print("alert('成功通过');");
              } else {
                  out.print("alert('通过失败');");
              }
              session.removeAttribute("passSucceeded");
          }
      %>

      <%
        Boolean refuseSucceeded = (Boolean)session.getAttribute("refuseSucceeded");
          if (refuseSucceeded != null) {
              if (refuseSucceeded) {
                  out.print("alert('成功拒绝');");
              } else {
                  out.print("alert('拒绝失败');");
              }
              session.removeAttribute("refuseSucceeded");
          }
      %>


      function pass() {
          var $row = $('#dg').datagrid('getSelected');
          if ($row === null) {
              alert("请先选择");
              return;
          }
          window.location = "server?service=passPending&username=" + $row.username;
      }

      function refuse() {
          var $row = $('#dg').datagrid('getSelected');
          if ($row === null) {
              alert("请先选择");
              return;
          }
          window.location = "server?service=refusePending&username=" + $row.username;
      }
  </script>
</head>
<body>
<div ID="tb">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="pass()">通过</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="refuse()">拒绝</a>
</div>
<table id="dg" title="待审核" class="easyui-datagrid" style="width:550px;height:250px"
       toolbar="#tb" 
       rownumbers="true" fitColumns="true" singleSelect="true">
  <thead>
  <tr>
    <th field="username">用户名</th>
    <th field="name">名字</th>
    <th field="gender">性别</th>
    <th field="age">年龄</th>
    <th field="tel">联系方式</th>

  </tr>
  </thead>
  <tbody>
  <%
    List<UserInfo> userInfos = (List<UserInfo>) session.getAttribute("userInfos");
    for (UserInfo userInfo : userInfos) {
      out.print("<tr>");
      out.print("<td>" + userInfo.getUsername() + "</td>");
      out.print("<td>" + (userInfo.getName() == null ? "" : userInfo.getName()) + "</td>");
      out.print("<td>" + (userInfo.getGender() == null ? "" : userInfo.getGender()) + "</td>");
      out.print("<td>" + (userInfo.getAge() == null ? "" : userInfo.getAge()) + "</td>");
      out.print("<td>" + (userInfo.getTel() == null ? "" : userInfo.getTel()) + "</td>");
      out.print("</tr>");
    }
  %>
  </tbody>
</table>
</body>
</html>
