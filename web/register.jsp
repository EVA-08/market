<%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/14
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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

  <title>Title</title>
  <script>
      <%
        Boolean usernameUsed = (Boolean)session.getAttribute("usernameUsed");
          if (usernameUsed != null) {
              if (usernameUsed) {
                  out.print("alert('用户名已存在');");
              }
              session.removeAttribute("usernameUsed");
          }
        Boolean addSucceeded = (Boolean)session.getAttribute("registerSucceeded");
          if (addSucceeded != null) {
              if (addSucceeded) {
                  out.print("alert('注册成功，等待审核');");
                  out.print("window.location = \"login.jsp\"");
              } else {
                  out.print("alert('注册失败');");
              }
              session.removeAttribute("registerSucceeded");
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
<div class="formtitle"><span>注册信息</span></div>
<form action="server?service=register" method="post" onsubmit="return checkPassword();">
  <ul class="forminfo">
    <li><label>用户名</label><input required="required" name="username" type="text" class="dfinput"/></li>
    <li><label>密码</label><input required="required" name="password" type="password" class="dfinput"/></li>
    <li><label>确认密码</label><input required="required" name="rePassword" type="password" class="dfinput"/></li>
    <li><label>姓名</label><input name="name" type="text" class="dfinput"/></li>
    <li><label>性别</label>
      <select class="dfinput" name="gender">
        <option value="男">男</option>
        <option value="女">女</option>
      </select>
    </li>
    <li><label>年龄</label><input name="age" type="text" class="dfinput"/></li>
    <li><label>电话</label><input name="tel" type="text" class="dfinput"/></li>
    <li><label>所属超市ID</label><input required="required" name="marketID" type="text" class="dfinput"/></li>
    <li><label>&nbsp;</label><input type="submit" class="btn" value="注册"/></li>
  </ul>
</form>
</body>
</html>
