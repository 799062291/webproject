/**
 *   商品列表展示页面，实现分页操作，下拉框联动
 */

$(function(){
	
	//-----验证规则
  
	window.data=null;
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
	
	
	
	
	
 
	$('#cate').combobox({    
	    url:'/web-project/catecontroller.do',
		valueField:'cid',
		textField:'cname',
		onSelect:function(data){ //选择项发生改变时触发事件,参数为当前选择的项
			var cid = $('#cate').combobox('getValue');
			loadGoods(cid);
		},
	    onLoadSuccess:function(){  //当数据加载完毕时触发
	       var datas = $(this).combobox('getData');	//拿到下拉框里的所有值的集合
	       if(datas.length>0){  //数据加载不为空,表示已经绑定了数据
	    	   $(this).combobox('setValue',datas[0].cid);
	    	   var cid = $('#cate').combobox('getValue');
	           loadGoods(cid);
	       }
	    }
});
	
	function loadGoods(cid,address){
	
		$('#tb').datagrid({
			url:'/web-project/goodsController.do?type=list',
			queryParams:{cid:cid},  //将参数传递过去
			pagination:true,
			rownumbers:true,
			title:'商品数据',
			iconCls:'icon-ok',
			collapsible:true,
			//remoteSort:true,
			//idField:'gid',
			pageList:[10,20,30,40,50],
			rownumbers:true, //在表前显示行号
			toolbar:[
			         {
			        	 text:'编辑商品',iconCls:'icon-edit',handler:function(){
			        			$('#f1').form('clear');
			        		 row = $('#tb').datagrid('getChecked'); 
			        		 console.log(row.length);
				        	 if(row.length == 0){
				        		 $.messager.alert('编辑提示','请选择要编辑的行','info'); 
				        	 }else if(row.length>1){ 
				        		 $.messager.alert('编辑提示','最多仅能选择一行数据进行编辑','info'); 
				        	 }else{
				        		 //----初始化
				        			console.log(typeof row[0].gclick);
				        		 $('#gtitle').textbox('setValue',row[0].gtitle);
				        		 $('#gauthor').textbox('setValue',row[0].gauthor);
				        		 $('#gsaleprice').textbox('setValue',row[0].gsaleprice);
				        		 $('#ginprice').textbox('setValue',row[0].ginprice);
				        		 $('#gdesc').textbox('setValue',row[0].gdesc);
//				        		 $('#gimg').filebox({
//				        			 value:row[0].gimg,
//				        			 width:145,
//				        			 buttonText:'上传图片'
//				        		 });
				        		 $('#gimg').textbox('setText','保持原路径不变');
				        	
				        		 if(row[0].gclick==0){
				        			
				        			 console.log('这是0');
				        		 $('#gclick').textbox('setValue',"0");
				        		 }else{
				       
				        			 $('#gclick').textbox('setValue',row[0].gclick); 
				        		 }
				        		 $('#cid').textbox('setValue',row[0].cid);
				        		 $('#pid').textbox('setValue',row[0].pid);
				        		 $('#div1').dialog({  //设置成对话框
				        				width:500,
				        				height:440,
				        				title:'编辑商品',
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
				        							//判断上传图片类型
				        							var type = $('#gimg').textbox('getText').substring($('#gimg').textbox('getText').lastIndexOf(".")+1);
				        							if((type=='保持原路径不变')||(type!='保持原路径不变'&&(type=='jpg'||type=='png'||type=='gif'))){
				        						$.ajax({
				        							type:'post',
				        							url:'/web-project/goodsController.do?type=edit&&gid='+row[0].gid+'&&gimg='+$('#gimg').textbox('getText'),
				        						    data:$('#f1').serialize(), //序列化表单数据
				        						    //data:{gtitle:row[0].gtitle,gauthor:row[0].gauthor,gsaleprice:row[0].gsaleprice,ginprice:},
				        							success:function(data){				    
				        						    	if(data=="1"){
				        						    		$.messager.alert('保存成功','用户新信息已保存！','info');
				        						    		$('#f1').form('clear');
				        						    		
				        						    		
				        						    		$('#div1').dialog('close');
				        						    		$('#tb').datagrid('reload');
				        						    	}else{
				        						    		$.messager.alert('出错了','服务器出现异常','info');
				        						    	}
				        						    }
				        						});
				        					}else{
				        						$.messager.alert('出错了','上传文件不为图片格式','info');
				        					}
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
			         },'-',{
			        	 text:'添加商品',iconCls:'icon-add',handler:function(){
			        			$('#f1').form('clear');
//			        			 $('#gimg').filebox({
//				        			
//				        			 width:145,
//				        			 buttonText:'上传图片'
//				        		 });
				        	
				        		 $('#div1').dialog({  //设置成对话框
				        				width:500,
				        				height:440,
				        				title:'添加商品',
				        				collapsible:true, //添加折叠按钮
				        				iconCls:'icon-add',
				        				closed:false,
				        				modal:true,
				        				buttons:[{
				        					text:'提交',
				        					iconCls:'icon-add',
				        					handler:function(){
				        						var isValid = $('#f1').form('validate'); //判断整个表单是否验证通过，即没有错误提示
				        
				        						if(isValid){  //通过验证才能执行点击事件操作
				        							console.log($('#f1').serialize());
				        			
				        							//判断文件上传类型是否为图片
				        							var type = $('#gimg').textbox('getText').substring($('#gimg').textbox('getText').lastIndexOf(".")+1);
				        						    if(type=='jpg'||type=='png'||type=='gif'){
				        							$.ajax({
				        							type:'post',
				        							url:'/web-project/goodsController.do?type=add&&gimg='+$('#gimg').textbox('getText'),
				        						    data:$('#f1').serialize(), //序列化表单数据
				        						    //data:{gtitle:row[0].gtitle,gauthor:row[0].gauthor,gsaleprice:row[0].gsaleprice,ginprice:},
				        							success:function(data){				    
				        						    	if(data=="1"){
				        						    		$.messager.alert('保存成功','用户新信息已保存！','info');
				        						    		$('#f1').form('clear');	
				        						    		
				        						    		$('#div1').dialog('close');
				        						    		$('#tb').datagrid('reload');
				        						    	}
				        						    }
				        						});
				        						}else{
				        							$.messager.alert('出错了','上传文件不为图片格式','info');
				        						}
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
			         },'-',{
			        	 text:'删除商品',iconCls:'icon-remove',handler:function(){
			        		 var row = $('#tb').datagrid('getChecked'); 
			        		 if(row.length == 0){
				        		 $.messager.alert('删除提示','请选择要删除的行','info'); 
				        	 }else{  //实现删除
				   
				        		 console.log(row);
				        		 $.messager.confirm('删除确认',"您确认要删除数据："+row[0].gtitle+"等"+row.length+"条数据吗？",function(r){
				        			 if(r){
				        				 for(var i = 0;i<row.length;i++){
				        					 $.ajax({
					        					 type:'post',
					        					 url:'/web-project/goodsController.do',
					        					 data:{type:'remove',gid:row[i].gid},
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
			        	 }
			         },'-',{
			        	 text:'修改封面',iconCls:'icon-man',handler:function(){
			        		 row = $('#tb').datagrid('getChecked'); 
			        						        	 if(row.length == 0){
				        		 $.messager.alert('编辑提示','请选择要编辑的行','info'); 
				        	 }else if(row.length>1){ 
				        		 $.messager.alert('编辑提示','最多仅能选择一行数据进行编辑','info'); 
				        	 }else{
				        		 $('#gid1').textbox('setValue',row[0].gid);
				        		 $('#gid1').focus(); 
				        		 $("#gid1").textbox('readonly',true); 
				        		 $('#gtitle1').textbox('setValue',row[0].gtitle);
				        		 $('#gauthor1').textbox('setValue',row[0].gauthor);
				        		 $('#gsaleprice1').textbox('setValue',row[0].gsaleprice);
				        		 $('#ginprice1').textbox('setValue',row[0].ginprice);
				        		 $('#gdesc1').textbox('setValue',row[0].gdesc);
				        		 if(row[0].gclick==0){
					        			
				        			 console.log('这是0');
				        		 $('#gclick1').textbox('setValue',"0");
				        		 }else{
				       
				        			 $('#gclick1').textbox('setValue',row[0].gclick); 
				        		 }
				        		 $('#cid1').textbox('setValue',row[0].cid);
				        		 $('#pid1').textbox('setValue',row[0].pid);
				        		 $('#div2').dialog({  //设置成对话框
				        			 width:400,
				        				height:440,
				        				title:'修改封面',
				        				collapsible:true, //添加折叠按钮
				        				iconCls:'icon-man',
				        				closed:false,
				        				modal:true
				        		 });
				        		 
				        		 document.querySelector('#ff').onsubmit = function(){
				        			 console.log($('#gid1').val())
				        			 if(!$('#ff').form('validate')||$('#filename').val()==""){		
				        				 $.messager.alert('错误提示','表单不得为空！','info');
				        				 return false;
				        			 }
				        		 }; //对表单进行最后提交
				        	 }
			        	
			        	 }
			           
			        	 
			        	 
			        	 
			         },'-',{
			        	 text:'<input type="text" class="easyui-textbox" name="textfield" id="searchtext" list="ds"/>',
			        	},{
			        		 text:'搜索',iconCls:'icon-search',handler:function(){
	                             var sdata = $('#searchtext').val();
			        			 $.ajax({
			        				 type:'post',
			        				 url:'/web-project/searchservlet.do?key='+$('#searchtext').val()+'&&cid='+cid+"&&type=load",
			        				 
			        				 success:function(datas){
			        					 if(datas=="reload"){
			        						 reload();			        						 
			        					 }else{			        					
			        					 data = datas;
			        					 search(cid,sdata);
			        					 }
//			        					 $.ajax({
//			        						 type:'post',
//			        						 url:'/web-project/goodsController.do?data='+data+'&&type=search',
//			        						 
//			        						 success:function(datas){
//			        						     
//			        						
//			        						 }
//			        						 
//			        					 });
			        				 }
			        				 
			        			 });
			        			 
//			        			 $.ajax({
//			        				 type:'post',
//			        				 url:'/web-project/searchservlet.do?key='+$('#searchtext').val()+'&&cid='+cid+"&&type=load&&curr="+curr,
//			        				 
//			        				 success:function(datas){
//			        					 if(datas=="reload"){
//			        						 reload();			        						 
//			        					 }else{			        					
//			        						 $('#tb').datagrid({
//			        							 url:'/web-project/searchservlet.do?key='+$('#searchtext').val()+'&&cid='+cid+"&&type=load&&curr="+curr,
//			        						     
//			        							 pageSize:10,
//			        							pagination:true
//			        						 })
//			        					 }
//			        		
//			        				 }
//			        				 
//			        			 });
			        		 }
			        	}
			         ],
			columns:[
			         [{"title":"全选","colspan":1},{"title":"商品名称与编号","colspan":2},{"title":"作者到访问量","colspan":5},{"title":"类别编号","colspan":2}],
			         [
			          {field:'chk',checkbox:true},
				        {field:'gid',title:'编号',"rowspan":1},    
				        {field:'gtitle',title:'商品名称',sortable:true,"rowspan":1},    
				        {field:'gauthor',title:'作者',"rowspan":1},
				        {field:'gsaleprice',title:'售价',"rowspan":1},
				        {field:'ginprice',title:'进价',"rowspan":1},    
				        {field:'gimg',title:'封面图片来源',"rowspan":1,formatter:function(value,row,index){
				           
				        	var sim = "<img onclick=dimgloadwin(\""+value+"\")  src='../images/bookcover/"+value+".jpg' style='width:20px;height:20px' />";
				        	return sim;
				        }}, 
				        {field:'gclick',title:'访问量',"rowspan":1},
				        {field:'cid',title:'类别编号',"rowspan":1},
				        {field:'pid',title:'出版社编号',"rowspan":1}, 
			          ]]
		});
		

		
		$('#searchtext').on('input',function(){
			 var options = $("#tb").datagrid("getPager" ).data("pagination").options;
			 var curr = options.pageNumber;
			 $.ajax({
				 type:'post',
				 url:'/web-project/searchservlet.do?key='+$('#searchtext').val()+'&&cid='+cid+"&&type=look&&curr="+curr,				 
				 success:function(datas){
					
					 	
				   
					 var options = "";
					if(datas!='reload'){
					 for(var i = 0;i<datas.length;i++){
						 
							options += "<option value='"+datas[i].gtitle+"'/>";
						}
						var ds = document.querySelector('#ds');
						 
						ds.innerHTML = options;
					 
				 }
			 }
				 
			 });
		})
		
	}
	

})

 
	
	
function search(cid,sdata){

	
	 $('#tb').datagrid({
		 url:'/web-project/searchservlet.do?key='+$('#searchtext').val()+'&&cid='+cid+"&&type=load",
		// data:data,
		// pageSize:10,
		pagination:true
	 })
	 $('#searchtext').val(sdata);
	 
		$('#searchtext').on('input',function(){
			 var options = $("#tb").datagrid("getPager" ).data("pagination").options;
			 var curr = options.pageNumber;
			 $.ajax({
				 type:'post',
				 url:'/web-project/searchservlet.do?key='+$('#searchtext').val()+'&&cid='+cid+"&&type=look&&curr="+curr,				 
				 success:function(datas){
					
					 	
				   
					 var options = "";
					if(datas!='reload'){
					 for(var i = 0;i<datas.length;i++){
						 
							options += "<option value='"+datas[i].gtitle+"'/>";
						}
						var ds = document.querySelector('#ds');
						 
						ds.innerHTML = options;
					 
				 }
			 }
				 
			 });
		})
	 
}

function reload(){
	
	 $('#tb').datagrid({
		data:null,
	    url:'/web-project/goodsController.do?type=list',
		
	 })
	
}

function dimgloadwin(img){
       
        var simg =  "../images/bookcover/"+img+".jpg";    
        $('#dahuikuimg').dialog({    
            title: '封面图片预览',    
            width: 144,    
            height:234,    
            resizable:true,    
            closed: false,    
            cache: false,    
            modal: true    
        });    
        $("#simg").attr("src",simg);    
    }       




	
