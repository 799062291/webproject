window.onload = function(){
	
	var xmlhttp = new XMLHttpRequest(); //省去对IE浏览器（<7.0）创建次对象的兼容判断
	
	document.querySelector('#checkemail').onclick = function(){
	
		var email = document.querySelector('#uemail').value;
	
		xmlhttp.open("POST", "email.do", true);//参数：1.传递方法。2.传递路径。3.是否异步
	    //发送请求与get不同
		var data = "email="+email;
		//设置http请求头
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xmlhttp.send(data);
		xmlhttp.onreadystatechange = emailcallback;
	}
	//ajax与服务器通信是通过XMLHttpRequest实现的；
	//1.得到一个异步通信的组件对象

	document.querySelector('#checkUname').onclick = function(){
		//将账号拿到，再传到服务器去验证，验证后拿到服务器的返回结果
		var uname = document.querySelector('#uname').value;
		
	    //2.使用此对象与服务器通信
		//A.建立与服务器某一资源的通信；
		xmlhttp.open("GET", "check.do?uname="+uname, true);//参数：1.传递方法。2.传递路径。3.是否异步
	    //B.发送请求
		xmlhttp.send(null);
		//C.接受服务器返回的数据
		
	    xmlhttp.onreadystatechange = namecallback;
	}
	

	
	function namecallback(){
	
		if(xmlhttp.readyState==4){  //数据传输完成
			if(xmlhttp.status == 200){ //成功在客户端接收到数据
				//可以取服务器返回的数据
				var data = xmlhttp.responseText; //接收服务器返回的字符串
				var sp = document.querySelector('#sp');
				if(data==1){
					sp.innerHTML = '账号被注册！';
					sp.style.color = 'red';
						
				}else if(data==0){
					sp.innerHTML = '恭喜！账号可以使用！';
					sp.style.color = 'green';
				}
				
			}
		}
	}
	function emailcallback(){
		
		if(xmlhttp.readyState==4){  //数据传输完成
			
			if(xmlhttp.status == 200){ //成功在客户端接收到数据
				//可以取服务器返回的数据
				var data = xmlhttp.responseText; //接收服务器返回的字符串
			
				var sp = document.querySelector('#sp1');
				if(data==1){
					sp.innerHTML = '邮箱被注册！';
					sp.style.color = 'red';
						
				}else if(data==0){
					sp.innerHTML = '恭喜！邮箱可以使用！';
					sp.style.color = 'green';
				}
				
			}
		}
	}
}
