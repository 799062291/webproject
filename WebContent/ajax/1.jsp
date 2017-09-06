<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
  $(function(){
	  $('#uname').on('input',function(){
		  //通过ajax请求服务器资源
	    var xmlhttp = $.ajax({
			   data:{'uname':$(this).val()},//向服务器传递的数据
			   dataType:'text', //接收服务器传来数据的类型
			   type:'GET',
			   url:'/web-project/check.do',
			   success:function(data1,b,c){ //成功后执行的操作，data1是服务器返回的数据,b:是状态码（可选）；c:返回xmlhttprequest对象（可选）
				   
				   if(data1 == 1){
				   $('span:first').html('已被注册');
				   }
			       if(data1 == 0){
			       $('span:first').html('恭喜！可以使用');   
			       }
			   }
			   
			  
		  }); 
	/* 	  $.get(
			  '/web-project/check.do',
			 {'uname':$(this).val()},//向服务器传递的数据
			 function(data1){ //成功后执行的操作，data1是服务器返回的数据
				   if(data1 == 1){
				   $('span:first').html('已被注册');
				   }
			       if(data1 == 0){
			       $('span:first').html('恭喜！可以使用');   
			       }
			   }
		  ) */
		  
		  /* $.post(
				  '/web-project/check.do',
					 {'uname':$(this).val()},//向服务器传递的数据
					 function(data1){ //成功后执行的操作，data1是服务器返回的数据
						   if(data1 == 1){
						   $('span:first').html('已被注册');
						   }
					       if(data1 == 0){
					       $('span:first').html('恭喜！可以使用');   
					       }
					   }	  
		  
		  ); */
	  })
  })
</script>

</head>
<body>
   账号：<input type="text" id="uname"/><span></span><br>
   密码：<input type="text" id="uemail"/>
</body>
</html>