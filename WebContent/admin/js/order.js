$(function(){
	
	
	$.extend($.fn.validatebox.defaults.rules, {   //拓展方法，比较两次密码输入是否一致
		xiaoshu: {
			validator: function(value) {
				
				return /^([0-9]+)$/.test(value); 
			},
			message: '请输入整数'
		},
		checkNum: {
			validator: function(value) {
				
				return (/^([0-9]+)$/.test(value))||(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value)); 
			},
			message: '请输入整数或小数'
		}
	
	});

	
	$('#tb').datagrid({    
	    url:'/web-project/ordercontroller.do',
	    title:'订单信息',
	    iconCls:'icon-search',
		collapsible:true, //添加折叠按钮
		rownumbers:true, //在表前显示行号
		singleSelect:false,//只允许选一行
		queryParams:{type:'order'}, //设置传递参数到servlet里
		looolbaadmsg:'正在加载，请稍侯......',
		toolbar:[
		         {text:'编辑订单',iconCls:'icon-edit',handler:function(){
		        	 row = $('#tb').datagrid('getChecked'); 
	        		
		        	 if(row.length == 0){
		        		 $.messager.alert('编辑提示','请选择要编辑的行','info'); 
		        	 }else if(row.length>1){ 
		        		 $.messager.alert('编辑提示','最多仅能选择一行数据进行编辑','info'); 
		        	 }else{
		        		// $('#cate').combobox('setValue',row[0].userid);
		        			
		        			$('#cate').combobox({    
		        		         width:140,
		        		         url:'/web-project/usercontroller.do?type=list',
		        		         valueField:'userid',
		        		 		 textField:'uname',
		        		 		 required:true,
		        		 		 missingMessage:'请选择用户',
		        		 		 prompt:'请输入选择用户',
		        		 		 onSelect:function(data){ //选择项发生改变时触发事件,参数为当前选择的项
		        					var userid = $('#cate').combobox('getValue');
		        					
		        				}, 
		        				onLoadSuccess:function(){  //当数据加载完毕时触发
		        				
		        				       var datas = $(this).combobox('getData');	//拿到下拉框里的所有值的集合
		        				       if(datas.length>0){  //数据加载不为空,表示已经绑定了数据
		        				    	   $(this).combobox('setValue',row[0].uesrid);
		        				    	   var cid = $('#cate').combobox('getValue');
		        				          
		        				       }
		        				    }
		        		});
		        		 $('#totalprice').textbox('setValue',row[0].totalprice);
		        		 $('#totalprice').textbox('setText',row[0].totalprice);
		        		 $('#totalprice').textbox({disabled:true}); //设定easyui属性（是否禁用）
		        		 var data = new Date(row[0].orderDate);  //------------------将时间格式化为：YYYY-MM-DD
		 	        	 var year = data.getFullYear();
		 	        	 var mouth = data.getMonth()+1;
		 	        	 if(mouth<=10){
		 	        		mouth = '0'+mouth;
		 	        	 }
		 	        	var day = data.getDate();
		 	        	if(day<10){
		 	        		day = '0'+day;
		 	        	}
		 	        	var sum = year+'-'+mouth+'-'+day;
		 	        	 
		        		 $('#orderdate').datebox('setValue', sum);
		        		
		        		 $('#div1').dialog({  //设置成对话框
		        				width:500,
		        				height:240,
		        				title:'编辑订单',
		        				collapsible:true, //添加折叠按钮
		        				iconCls:'icon-edit',
		        				closed:false,
		        				modal:true,
		        				buttons:[{
		        					text:'保存修改',
		        					iconCls:'icon-save',
		        					handler:function(){
		        						var isValid = $('#f1').form('validate'); //判断整个表单是否验证通过，即没有错误提示
		        
		        						if(isValid){  //通过验证才能执行点击事件操作
		        							console.log($('#f1').serialize());
		        						$.ajax({
		        							type:'post',
		        							url:'/web-project/ordercontroller.do?type=edit&&userid='+row[0].uesrid+'&&orderid='+row[0].orderid+'&&totalprice='+row[0].totalprice,
		        						    data:$('#f1').serialize(), //序列化表单数据
		        						    //data:{gtitle:row[0].gtitle,gauthor:row[0].gauthor,gsaleprice:row[0].gsaleprice,ginprice:},
		        							success:function(data){				    
		        						    	if(data=="1"){
		        						    		$.messager.alert('保存成功','用户新信息已保存！','info');
		        						    		$('#f1').form('clear');	    		
		        						    		$('#div1').dialog('close');
		        						    		$('#tb').datagrid('reload');
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
		        	 }
		        	
		         }},'-',
		         {text:'删除订单',iconCls:'icon-remove',handler:function(){
		         	 var row = $('#tb').datagrid('getChecked'); 
			        	// var row = $('#tab').datagrid('getSelected');  //返回集合
			        	 if(row.length == 0){
			        		 $.messager.alert('删除提示','请选择要删除的行','info'); 
			        	 }else{  //实现删除
			   
			        		 console.log(row);
			        		 $.messager.confirm('删除确认',"您确认要删除订单编号："+row[0].orderid+"等"+row.length+"条数据吗？",function(r){
			        			 if(r){
			        				 for(var i = 0;i<row.length;i++){
			        					 $.ajax({
				        					 type:'post',
				        					 url:'/web-project/ordercontroller.do?type=remove&&orderid='+row[i].orderid,
				        					 data:{type:'remove',userid:row[i].userid},
				        					 success:function(data){
				        						 if(data=="1"){
				        							 
				        						 }
				        					 }
				        					 
				        				 });
			        					
			        				 }
			        				 $.messager.alert('删除提示','已成功删除'+row.length+'条数据！','info');
	    						     //重新刷新页面
	    							 $('#tb').datagrid('reload');
			        			
			        			 }
			        		 })
			        	 }
		        	
		         }},'-',
		         {text:'添加订单',iconCls:'icon-add',handler:function(){
		        	
		        	 $('#f1').form('clear');	  
		        	 $('#cate').combobox({    //-----------下拉框载入数据
        		         width:140,
        		         url:'/web-project/usercontroller.do?type=list',
        		         valueField:'userid',
        		 		 textField:'uname',
        		 		 required:true,
        		 		 missingMessage:'请选择用户',
        		 		 prompt:'请输入选择用户',
        		 		 onSelect:function(data){ //选择项发生改变时触发事件,参数为当前选择的项
        					var userid = $('#cate').combobox('getValue');
        					
        				} ,	
        				onLoadSuccess:function(){  //当数据加载完毕时触发
	        				
     				       var datas = $(this).combobox('getData');	//拿到下拉框里的所有值的集合
     				       if(datas.length>0){  //数据加载不为空,表示已经绑定了数据
     				    	   $(this).combobox('setValue',null);
     				    	   var cid = $('#cate').combobox('getValue');
     				          
     				       }
     				    }
        				
        		});
		        	 $('#totalprice').textbox('setValue','0');
		  
		        	 $('#totalprice').textbox({disabled:true}); 
		        	//--------------------------------------设置面板
		        	 $('#div1').dialog({  //设置成对话框
	        				width:500,
	        				height:240,
	        				title:'添加订单',
	        				collapsible:true, //添加折叠按钮
	        				iconCls:'icon-add',
	        				closed:false,
	        				modal:true,
	        				buttons:[{
	        					text:'提交订单',
	        					iconCls:'icon-save',
	        					handler:function(){
	        						var isValid = $('#f1').form('validate'); //判断整个表单是否验证通过，即没有错误提示
	        
	        						if(isValid){  //通过验证才能执行点击事件操作
	        							console.log($('#f1').serialize());
	        						$.ajax({
	        							type:'post',
	        							url:'/web-project/ordercontroller.do?type=addorder',
	        						    data:$('#f1').serialize(), //序列化表单数据
	        						    //data:{gtitle:row[0].gtitle,gauthor:row[0].gauthor,gsaleprice:row[0].gsaleprice,ginprice:},
	        							success:function(data){				    
	        						    	if(data=="1"){
	        						    		$.messager.alert('提交成功','已生成新订单！','info');
	        						    		$('#f1').form('clear');	    		
	        						    		$('#div1').dialog('close');
	        						    		$('#tb').datagrid('reload');
	        						    	}else{
	        						    		$.messager.alert('出错了','服务器出现异常！','info');
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
		        	 
		         }},'-',
		         {
		        	text:'添加商品',iconCls:'icon-tip',handler:function(){
		        		 $('#f2').form('clear');	  
			        	 $('#orderid').combobox({    //-----------下拉框载入数据
	        		         width:140,
	        		         url:'/web-project/ordercontroller.do?type=order',
	        		         valueField:'orderid',
	        		 		 textField:'orderid',
	        		 		 required:true,
	        		 		 missingMessage:'请选择用户',
	        		 		 prompt:'请输入选择用户',
	        		 		 onSelect:function(data){ //选择项发生改变时触发事件,参数为当前选择的项
	        					
	        				} 
	        				
	        		});
			        	 $('#totalprice').textbox({disabled:false}); 
			        	//--------------------------------------设置面板
			        	 $('#div2').dialog({  //设置成对话框
		        				width:500,
		        				height:240,
		        				title:'添加商品',
		        				collapsible:true, //添加折叠按钮
		        				iconCls:'icon-tip',
		        				closed:false,
		        				modal:true,
		        				buttons:[{
		        					text:'保存商品',
		        					iconCls:'icon-save',
		        					handler:function(){
		        						var isValid = $('#f2').form('validate'); //判断整个表单是否验证通过，即没有错误提示
		        
		        						if(isValid){  //通过验证才能执行点击事件操作
		        							console.log($('#f2').serialize());
		        						$.ajax({
		        							type:'post',
		        							url:'/web-project/ordercontroller.do?type=addgoods',
		        						    data:$('#f2').serialize(), //序列化表单数据
		        						    //data:{gtitle:row[0].gtitle,gauthor:row[0].gauthor,gsaleprice:row[0].gsaleprice,ginprice:},
		        							success:function(data){				    
		        						    	if(data=="1"){
		        						    		$.messager.alert('提交成功','已添加新商品！','info');
		        						    		$('#f2').form('clear');	    		
		        						    		$('#div2').dialog('close');
		        						    		$('#tb').datagrid('reload');
		        						    	}else{
		        						    		$.messager.alert('出错了','服务器出现异常！','info');
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
			         }
		         }
		         ],
	    columns:[[
	        {field:'chk',checkbox:true},
	        {field:'orderid',title:'订单编号'},    
	        {field:'uesrid',title:'所有者'},    
	        {field:'totalprice',title:'订单总价'},
	        {field:'orderDate',title:'订单日期',formatter:function(value,row,index){
	        	var data = new Date(value);
	        	var year = data.getFullYear();
	        	var mouth = data.getMonth()+1;
	        	if(mouth<=10){
	        		mouth = '0'+mouth;
	        	}
	    
	        	var day = data.getDate();
	        	if(day<10){
	        		day = '0'+day;
	        	}
	        	var sum = year+'-'+mouth+'-'+day;
	        	return sum;
	        }},
	    ]],
        view:detailview,
        detailFormatter: function (index, row) {
            return '<div style="padding:5px"><table id="tbDataAuthorityItem-' + index + '"></table></div>';
        },
        onExpandRow: function (index, row) {
        	$('#tbDataAuthorityItem-' + index).datagrid({
                url: '/web-project/ordercontroller.do?type=detail&&id=' + row.orderid,
                fitColumns: true,
                singleSelect: true,
                rownumbers: true,
                loadMsg: '',
                height: 'auto',
                columns: [[
                    { field: 'orderdetailid', title: '商品下单编号', width: 50 },
                    { field: 'gtitle', title: '商品名称', width: 50 },
                    { field: 'gsaleprice', title: '商品单价', width: 50 },
                    { field: 'gid', title: '商品编号', width: 50},
                    { field: 'gnumber', title: '商品数量', width: 50}
                ]],
          
            });

        }

	});  

})