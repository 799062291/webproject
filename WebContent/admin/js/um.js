/**
 *   用户管理页面
 */

$(function(){
	//加载t_user表数据，在easyui的datagrid控件显示数据
	
	$('#div1').dialog({  //设置成对话框
		width:500,
		height:400,
		title:'用户信息编辑',
		collapsible:true, //添加折叠按钮
		iconCls:'icon-man',
		closed:true,
		modal:true,
		buttons:[{
			text:'保存修改',
			iconCls:'icon-save',
			handler:function(){
				var isValid = $('#f1').form('validate'); //判断整个表单是否验证通过，即没有错误提示
				
				console.log('6665'+isValid);
				if(isValid){  //通过验证才能执行点击事件操作
				$.ajax({
					type:'post',
					url:'/web-project/usercontroller.do',
				    data:{type:'edit',userid:''+ $('#userid').textbox('getText'),uemail:''+$('#uemail').textbox('getText'),uname:$('#uname').textbox('getText'),upassword:$('#upassword').textbox('getText'),uaddress:$('#uaddress').textbox('getText'),utel:$('#utel').textbox('getText'),usex:$("input[name='usex']:checked").val(),ustateid:$("input[name='ustateid']:checked").val(),uroleid:$("input[name='uroleid']:checked").val()}, //序列化表单数据
				    success:function(data){				    
				    	if(data=="1"){
				    		$.messager.alert('保存成功','用户新信息已保存！','info');
				    		$('#div1').dialog('close');
				    		 $('#tab').datagrid('reload');
				    	}else{
				    		$.messager.alert('出错了','账号不存在或密码错误55！','info');
				    	}
				    }
				});
				}else{
					console.log('6665'+isValid);
		    		$.messager.alert('出错了','请完整填写所有信息，不得为空！','info');
		    	}
			}
		},
        {text:'取消',iconCls:'icon-cancel',handler:function(){
        	 $('#div1').dialog('close');
        }}]
	});
	
	
	
	$('#div2').dialog({  //设置成对话框
		width:500,
		height:400,
		title:'添加新用户信息',
		collapsible:true, //添加折叠按钮
		iconCls:'icon-man',
		closed:true,
		modal:true,
		buttons:[{
			text:'提交',
			iconCls:'icon-add',
			handler:function(){
				var isValid = $('#f2').form('validate'); //判断整个表单是否验证通过，即没有错误提示
				
				console.log('6665'+isValid);
				if(isValid&&$("input[name='usex1']:checked").val()!=null&&$("input[name='ustateid1']:checked").val()!=null){  //通过验证才能执行点击事件操作
				$.ajax({
					type:'post',
					url:'/web-project/usercontroller.do',
				    data:{type:'add',uemail:''+$('#uemail1').textbox('getText'),uname:$('#uname1').textbox('getText'),upassword:$('#upassword1').textbox('getText'),uaddress:$('#uaddress1').textbox('getText'),utel:$('#utel1').textbox('getText'),usex:$("input[name='usex1']:checked").val(),ustateid:$("input[name='ustateid1']:checked").val()}, //序列化表单数据
				    success:function(data){				    
				    	if(data=="1"){
				    		$.messager.alert('添加成功','已成功添加新用户！','info');
				    		$('#div2').dialog('close');
				    		$('#tab').datagrid('reload');
				    		$('#f2').form('clear');
				    	}else{
				    		$.messager.alert('出错了','请完整填写资料信息，不得为空！','info');
				    	}
				    }
				});
				}else{
				
		    		$.messager.alert('出错了','请完整填写所有信息，不得为空！','info');
		    	}
			}
		},
        {text:'取消',iconCls:'icon-cancel',handler:function(){
        	$('#f2').form('clear');
        	$('#div2').dialog('close');
        }}]
	});
	
	
	
	$('#tab').datagrid({    
	    url:'/web-project/usercontroller.do',
	    title:'用户信息',
	    iconCls:'icon-man',
		collapsible:true, //添加折叠按钮
		rownumbers:true, //在表前显示行号
		singleSelect:false,//只允许选一行
		queryParams:{type:'list'}, //设置传递参数到servlet里
		looolbaadmsg:'正在加载，请稍侯......',
		toolbar:[
		         {text:'编辑',iconCls:'icon-edit',handler:function(){
		        	 row = $('#tab').datagrid('getChecked'); 
		        	 if(row.length == 0){
		        		 $.messager.alert('编辑提示','请选择要编辑的行','info'); 
		        	 }else if(row.length>1){ 
		        		 $.messager.alert('编辑提示','最多仅能选择一行数据进行编辑','info'); 
		        	 }else{
		        		 $('#div1').dialog('open');  //将隐藏面板显示
		        		 $('#userid').textbox('setText',row[0].userid);
		        		 $('#uemail').textbox('setText',row[0].uemail);
		        		 $('#uname').textbox('setText',row[0].uname);
		        		 $('#upassword').textbox('setText',row[0].upassword);
		        		 $('#uaddress').textbox('setText',row[0].uaddress);
		        		 $('#utel').textbox('setText',row[0].utel);
		        		 if(row[0].usex=='男'){
		        		 $('#usex1').attr('checked','checked');
		        		 }else{
		        		  $('#usex2').attr('checked','checked');
		        		 }
		        		 if(row[0].ustateid=='B5868B7A06E54DAEB19658343D3A2B28'){
			        		 $('#ustateid1').attr('checked','checked');
			        		 }else{
			        		  $('#ustateid2').attr('checked','checked');
			        		 }
		        		 if(row[0].uroleid=='2'){
			        		 $('#uroleid1').attr('checked','checked');
			        		 }else{
			        		  $('#uroleid2').attr('checked','checked');
			        		 }
		        	 }
		         }},'-',
		         {text:'删除',iconCls:'icon-remove',handler:function(){
		        	 var row = $('#tab').datagrid('getChecked'); 
		        	// var row = $('#tab').datagrid('getSelected');  //返回集合
		        	 if(row.length == 0){
		        		 $.messager.alert('删除提示','请选择要删除的行','info'); 
		        	 }else{  //实现删除
		   
		        		 console.log(row);
		        		 $.messager.confirm('删除确认',"您确认要删除数据："+row[0].uname+"等"+row.length+"条数据吗？",function(r){
		        			 if(r){
		        				 for(var i = 0;i<row.length;i++){
		        					 $.ajax({
			        					 type:'post',
			        					 url:'/web-project/usercontroller.do',
			        					 data:{type:'remove',userid:row[i].userid},
			        					 success:function(data){
			        						 if(data=="1"){
			        							 
			        						 }
			        					 }
			        					 
			        				 });
		        					
		        				 }
		        				 $.messager.alert('删除提示','已成功删除'+row.length+'条数据！','info');
    						     //重新刷新页面
    							 $('#tab').datagrid('reload');
		        			
		        			 }
		        		 })
		        	 }
		         }},'-',
		         {text:'添加',iconCls:'icon-add',handler:function(){
		        	 $('#div2').dialog('open');  //将隐藏面板显示
		         }}
		         ],
	    columns:[[
	        {field:'chk',checkbox:true},
	        {field:'userid',title:'编号'},    
	        {field:'uemail',title:'邮箱'},    
	        {field:'uname',title:'账户名'},
	        {field:'upassword',title:'密码'},
	        {field:'usex',title:'性别'},    
	        {field:'uaddress',title:'联系地址'}, 
	        {field:'utel',title:'联系电话'},
	        {field:'ustateid',title:'状态编号'},
	        {field:'uroleid',title:'角色编号'},    
	    ]]    
	});  

});