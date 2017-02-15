<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath"></c:set>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- bootstrap 样式 -->
    <link rel="stylesheet" href="${rootPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootPath}/assets/bootstrap-table-master/dist/bootstrap-table.css">
    <!-- 基础的js -->
    <!--[if lte IE 9]><!-->
    <script type="text/javascript" src="${rootPath}/assets/dependenceJS/jquery/jquery-1.10.1.min.js"></script>
    <!--<[!endif]-->
    <!--[if gt IE 9]><!-->
    <script type="text/javascript" src="${rootPath}/assets/dependenceJS/jquery/jquery-2.0.3.min.js"></script>
    <!--<![endif]-->
    <!--[if !IE]><!-->
    <script type="text/javascript" src="${rootPath}/assets/dependenceJS/jquery/jquery-2.0.3.min.js"></script>
    <!--<![endif]-->

    <!-- form 表单的ajax 提交 -->
    <script src="${rootPath}/assets/jquery-plugins/jquery.form.js"></script>

    <!-- bootstrap的js -->
    <script src="${rootPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    <!-- 中文 -->
    <script src="${rootPath}/assets/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.js"></script>



    <title>用户列表</title>
</head>
<body>
<h3>UserList</h3>

<button  class="btn btn-success"  type="button" data-toggle="modal" data-target="#UserModal" >Add User</button><br/>
<div id="container">
    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Mobile</td>
                <td>CreateTime</td>
                <td>Delete</td>
                <td>Update</td>
            </tr>
            <c:forEach items="${userlist}" var="user">
                <tr>
                    <td>${user.id }</td>
                    <td>${user.name }</td>
                    <td>${user.mobile }</td>
                    <td>${user.createTime }</td>
                    <td><button  class="btn btn-warning"  type="button" onclick="deleteUser(${user.id})">Delete</button></td>
                    <td><button  class="btn btn-success"  type="button" onclick="editUser(${user.id})">Update</button></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="UserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加用户</h4>
            </div>
            <div class="modal-body">
                <!-- 主体部分放的是form表单 -->
                <form id="form_add" class="form-horizontal" role="form">
                    <input type="hidden" id="id" name="id" value=""/>
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">名字<span style="color: red;"> *</span></label>
                        <div class="col-sm-9">
                            <input name="name" id="name" type="text" class="form-control"
                                   placeholder="请输入名字" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobile" class="col-sm-3 control-label">电话号码<span style="color: red;"> *</span></label>
                        <div class="col-sm-9">
                            <input name="mobile" id="mobile" type="text"
                                   class="form-control" placeholder="请输电话号码" required="true">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary"  onclick="saveUser()">保存</button>
                        <button type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</div>
<script type="text/javascript">
    var $form = $("#form_add");
    function saveUser(){
        var userName = $('input[name=name]').val();
        var mobile = $('input[name=mobile]').val();
        console.log(userName.trim(),mobile.trim());
        if(userName.trim() == '' || mobile.trim() == ''){
            alert('用户名和电话不为空！');
            return;
        }
        $form.ajaxSubmit({
            url:"/user/addOrUpdateUser.do",
            type:"post",
            resetForm:true,
            beforeSend: function () {

            },
            error:function(){
                alert("未知错误！");
            },
            success: function(data){
                alert(JSON.stringify(data));
            }

        });
    }

    function editUser(id){
//        alert(JSON.stringify(id));
        $.ajax({
            url:'/user/getUserById.do',
            type:'post',
            dataType:'JSON',
            data:{id:id},
            beforeSend: function () {

            },
            error:function(){
                alert("未知错误！");
            },
            success: function(data){
                if(data.code == 1){
                    var user = data.data;
                    $("input[name=id]").val(user.id);
                    $("input[name=name]").val(user.name);
                    $("input[name=mobile]").val(user.mobile);
                }
//                alert(JSON.stringify(data));
            }

        });
        $("#UserModal").modal('show');
    }

    function deleteUser(id){
        $.ajax({
            url:'/user/deleteUser.do',
            type:'post',
            dataType:'JSON',
            data:{id:id},
            beforeSend: function () {

            },
            error:function(){
                alert("未知错误！");
            },
            success: function(data){
                alert(JSON.stringify(data));
                //成功的话，删除当前行
                //简单一点就刷新当前页
                location.replace(document.referrer);
            }

        });
    }
</script>

</body>
</html>