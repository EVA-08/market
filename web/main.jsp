<%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/7
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>信息管理系统界面</title>
  <script>
      <%
        Boolean excessOfAuthority = (Boolean) session.getAttribute("excessOfAuthority");
        if (excessOfAuthority != null) {
            out.print("alert('越权操作')");
        }
        session.removeAttribute("excessOfAuthority");
      %>
  </script>
</head>
<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize" ID="topFrame" title="topFrame" />
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="left.jsp" name="leftFrame" scrolling="No" noresize="noresize" ID="leftFrame" title="leftFrame" />
    <frame src="index.jsp" name="rightFrame" ID="rightFrame" title="rightFrame" />
  </frameset>
</frameset>
<noframes><body>
</body></noframes>
</html>
