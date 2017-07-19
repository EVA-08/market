<%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/7
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>欢迎登录后台管理系统</title>
  <link href="css/style.css" rel="stylesheet" type="text/css"/>
  <script language="JavaScript" src="js/jquery.js"></script>
  <script src="js/cloud.js" type="text/javascript"></script>
  <%
    String login = request.getParameter("login");
    if (login != null) {
      if ("false".equals(login)) {
        out.print("<script> alert(\"请先登录\")</script>");
      }
      if ("fail".equals(login)) {
        out.print("<script> alert(\"账户或密码错误\")</script>");
      }
    }

  %>

  <script language="javascript">
      $(function () {
          $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
          $(window).resize(function () {
              $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
          })
      });
  </script>

</head>

<body
    style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div ID="mainBody">
  <div ID="cloud1" class="cloud"></div>
  <div ID="cloud2" class="cloud"></div>
</div>


<div class="logintop">
  <span>欢迎登录后台管理界面平台</span>
  <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
  </ul>
</div>

<div class="loginbody">

  <span class="systemlogo"></span>

  <div class="loginbox">
    <form action="server?service=login" method="post">
      <ul>
        <li><input required="required" name="username" type="text" class="loginuser"  onclick="this.value=''"/></li>
        <li><input required="required" name="password" type="password" class="loginpwd" onclick="this.value=''"/></li>
        <li><input name="" type="submit" class="loginbtn" value="登录"/>
          <lable><a href="register.jsp">注册</a></lable>
        </li>
        <label><a href=""></a></label>
      </ul>
    </form>

  </div>

</div>


<div class="loginbm">版权所有 2013 <a href="http://www.uimaker.com">uimaker.com</a> 仅供学习交流，勿用于任何商业用途</div>


</body>

</html>
