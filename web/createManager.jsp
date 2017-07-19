<%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/14
  Time: 10:35
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

  <link href="css/style.css" rel="stylesheet" type="text/css"/>
  <script>
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
  <script>
      function checkPassword() {
          var $password = $("input[name='password']");
          var $rePassword = $("input[name='rePassword']");
          if ($password.attr("value") === $rePassword.attr("value")) {
              return true;
          } else {
              alert("密码与确认密码不相同");
              return false;
          }
      }
  </script>
</head>
<body>
<div class="formtitle"><span>经理信息</span></div>
<form action="server?service=createManager" method="post" onsubmit="return checkPassword()">
  <ul class="forminfo">
    <li><label>经理用户名</label><input required="required" name="username" type="text" class="dfinput"/></li>
    <li><label>密码</label><input required="required" name="password" type="password" class="dfinput"/></li>
    <li><label>确认密码</label><input required="required" name="rePassword" type="password" class="dfinput"/></li>
    <li><label>所在超市ID</label><input required="required" name="marketID" type="text" class="dfinput"/></li>
    <li><label>&nbsp;</label><input type="submit" class="btn" value="创建"/></li>
  </ul>
</form>
</body>
</html>
