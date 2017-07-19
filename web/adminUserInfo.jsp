<%@ page import="entiry.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: EVA-08
  Date: 2017/7/11
  Time: 14:25
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
      function updateUserInfo() {
          $("#updateUserInfo-dlg").dialog("open");
      }
      function updatePassword() {
          $("#updatePassword-dlg").dialog("open");
      }
      <%
          Boolean updateSucceeded = (Boolean)session.getAttribute("updateSucceeded");
          if (updateSucceeded != null) {
              if (updateSucceeded) {
                  out.print("self.parent.frames['topFrame'].location = 'top.jsp';");
                  out.print("alert('更新成功');");

              } else {
                  out.print("alert('更新失败');");
              }
              session.removeAttribute("updateSucceeded");
          }
        %>
  </script>
</head>
<body>
<div>
  <%
    UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
  %>
  <p>用户名:<%out.print(userInfo.getUsername());%></p>
  <p>姓名:<%out.print(userInfo.getName() == null ? "" : userInfo.getName());%></p>
  <p>权限:<%out.print(userInfo.getAuthority());%></p>
  <p>性别:<%out.print(userInfo.getGender() == null ? "" : userInfo.getGender());%></p>
  <p>年龄:<%out.print(userInfo.getAge() == null ? "" : userInfo.getAge());%></p>
  <p>电话:<%out.print(userInfo.getTel() == null ? "" : userInfo.getTel());%></p>
  <p>所属超市:<%out.print(userInfo.getMarketID());%></p>
</div>
<div>
  <button class="easyui-linkbutton" iconCls="icon-edit" onclick="updateUserInfo()">修改信息</button>
  <button class="easyui-linkbutton" iconCls="icon-edit" onclick="updatePassword()">修改密码</button>
</div>

<div ID="updateUserInfo-dlg" class="easyui-dialog" style="padding:5px;width:400px;height:200px;"
     title="修改信息" iconCls="icon-ok"
     buttons="#updateUserInfo-dlg-buttons"
     modal="true" closed="true">
  <form action="server?service=updateUserInfo" method="post">
    用户名: <span></span><br>
    姓名: <input type="text" name="name" value="<%=userInfo.getName() == null? "" : userInfo.getName() %>"><br>
    性别: <input type="text" name="gender" value="<%=userInfo.getGender() == null? "" : userInfo.getGender()%>"><br>
    年龄: <input type="text" name="age" value="<%=userInfo.getAge() == null? "" : userInfo.getAge()%>"><br>
    电话: <input type="text" name="tel" value="<%=userInfo.getTel() == null? "" : userInfo.getTel()%>"><br>
  </form>
</div>

<div ID="updateUserInfo-dlg-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="$('#updateUserInfo-dlg form').submit()">确认</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
     onclick="$('#updateUserInfo-dlg').dialog('close')">取消</a>
</div>

<div ID="updatePassword-dlg" class="easyui-dialog" style="padding:5px;width:400px;height:200px;"
     title="修改信息" iconCls="icon-ok"
     buttons="#updatePassword-dlg-buttons"
     modal="true" closed="true">
  <form action="server?service=updatePassword" method="post">
    旧密码: <input required="required" type="password" name="oldPassword"><br>
    新密码: <input required="required" type="password" name="newPassword"><br>
    确认密码: <input required="required" type="password" name="rePassword"><br>
  </form>
</div>

<div ID="updatePassword-dlg-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="$('#updatePassword-dlg form').submit()">确认</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
     onclick="$('#updatePassword-dlg').dialog('close')">取消</a>
</div>
</body>
</html>
