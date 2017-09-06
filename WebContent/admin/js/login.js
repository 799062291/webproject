/**
 *    登陆页面的js文件，用来描绘登录对话框
 */
$(function(){
	$('#login').dialog({  //设置成对话框
		width:400,
		height:200,
		title:'用户登录',
		collapsible:true, //添加折叠按钮
		iconCls:'icon-man',
		buttons:[{
			text:'登录',
			iconCls:'icon-ok',
			handler:function(){
				var isValid = $('form').form('validate'); //判断整个表单是否验证通过，即没有错误提示
				if(isValid){  //通过验证才能执行点击事件操作
				$.ajax({
					type:'post',
					url:'/web-project/logincontroller.do',
				    data:$('form').serialize(), //序列化表单数据
				    success:function(data){
				    
				    	if(data=="1"){
				    		window.location="index.html";
				    	}else{
				    		$.messager.alert('出错了','账号不存在或密码错误555！','error');
				    	}
				    }
				});
				}else{
		    		$.messager.alert('出错了','账号或密码未完整填写！','error');
		    	}
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){}
		}]
	});
})