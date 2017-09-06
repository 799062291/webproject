$(function(){   //----ajax数据分页
	$('#pageination').pagination({
		pageSize:5,
		pageIndex:0,
		total:100,
		firstBtnText:'首页',
		lastBtnText:'尾页',
		prevBtnText:'上一页',
		nextBtnText:'下一页',
		showInfo:true,
		noInfoText:'没有数据',
		showJump:true,   //直接输入跳转到某页
		showPageSizes:true,
		pageSizeItems:[10,20,30],
		remote:{
			url:'page.do',
			totalName:'totalNumber',
			success:function(pageinfo){
				$('#content').empty();
				var ul="<ul>";
				$(pageinfo.datas).each(function(index,goods){
					ul+="<li>"+goods.gtitle+"<li>"
				})
				ul+="</ul>";
				$('#content').append(ul);
			}
		}
	});
})