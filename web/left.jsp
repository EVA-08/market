<%@ page import="entiry.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/7
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>无标题文档</title>
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <script language="JavaScript" src="js/jquery.js"></script>

  <script type="text/javascript">
      $(function(){
          //导航切换
          $(".menuson li").click(function(){
              $(".menuson li.active").removeClass("active")
              $(this).addClass("active");
          });

          $('.title').click(function(){
              var $ul = $(this).next('ul');
              $('dd').find('ul').slideUp();
              if($ul.is(':visible')){
                  $(this).next('ul').slideUp();
              }else{
                  $(this).next('ul').slideDown();
              }
          });
      })
  </script>


</head>

<body style="background:#f0f9fd;">

<dl class="leftmenu">
<%
  UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
  String authority = userInfo.getAuthority();
%>
  <dd>
    <ul class="menuson">
      <li><cite></cite><a href="index.jsp" target="rightFrame">首页</a><i></i></li>
      <%
        if ("employee".equals(authority)) {
            out.print("<li><cite></cite><a href=\"server?service=queryCommodities\" target=\"rightFrame\">商品管理</a><i></i></li>");
        } else if ("manager".equals(authority)) {
          out.print("<li><cite></cite><a href=\"server?service=queryCommodities\" target=\"rightFrame\">商品查看</a><i></i></li>");
        }
      %>
      <%
        if ("employee".equals(authority)) {
          out.print("<li><cite></cite><a href=\"server?service=queryTypes\" target=\"rightFrame\">类别管理</a><i></i></li>");
        } else if ("manager".equals(authority)) {
          out.print("<li><cite></cite><a href=\"server?service=queryTypes\" target=\"rightFrame\">类别查看</a><i></i></li>");
        }
      %>
      <%
        if ("employee".equals(authority)) {
          out.print("<li><cite></cite><a href=\"server?service=querySuppliers\" target=\"rightFrame\">供应商管理</a><i></i></li>");
        } else if ("manager".equals(authority)) {
          out.print("<li><cite></cite><a href=\"server?service=querySuppliers\" target=\"rightFrame\">供应商查看</a><i></i></li>");
        }
      %>
      <%
        if ("admin".equals(authority)) {
          out.print("<li><cite></cite><a href=\"server?service=queryMarkets\" target=\"rightFrame\">超市管理</a><i></i>");
          out.print("<li><cite></cite><a href=\"createManager.jsp\" target=\"rightFrame\">创建经理</a><i></i>");
        }
      %>
      <%
        if ("manager".equals(authority)) {
            out.print("<li><cite></cite><a href=\"server?service=queryPending\" target=\"rightFrame\">注册审核</a><i></i>");
            out.print("<li><cite></cite><a href=\"server?service=queryLogs\" target=\"rightFrame\">出入库记录</a><i></i>");
        }
      %>
      <li><cite></cite><a href="server?service=queryUserInfo" target="rightFrame">账户信息</a><i></i>
    </ul>
  </dd>

</dl>

</body>
</html>
