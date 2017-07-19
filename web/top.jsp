<%@ page import="entiry.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/7
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>无标题文档</title>
  <link href="css/style.css" rel="stylesheet" type="text/css"/>
  <script language="JavaScript" src="js/jquery.js"></script>
  <script type="text/javascript">
      $(function () {
          //顶部导航切换
          $(".nav li a").click(function () {
              $(".nav li a.selected").removeClass("selected")
              $(this).addClass("selected");
          })
      })
  </script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

<div class="topleft">
  <a href="main.jsp" target="_parent"><img src="images/logo.png" title="系统首页"/></a>
</div>

<div class="topright">
  <ul>
    <li><a href="login.jsp" target="_parent">退出</a></li>
  </ul>

  <div class="user">
    <span>
      <%
        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
        String name = userInfo.getName();
         if (name == null) {
             out.print(userInfo.getUsername());
         } else {
             out.print(name);
         }
      %>
    </span>
  </div>

</div>

</body>
</html>

