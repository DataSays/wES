<#import "/inc/fn.ftl" as c>
<#import "/inc/adminlte.ftl" as a>
<#import "/inc/form.ftl" as f>
<@a.adminpage activeMenu='a99a9af5b1e6476cb8568b1bb45ba60a' deps=[ 'bootstrap_table'] extEndFile=[ '/static/common/scripts/storeSaleSettle.js']>
	<@a.contentHeader title='交易记录查询' />
	<@a.queryDataGrid id='queryDataGrid1'>
		<@a.queryForm>
			<@f.input name='payId' label='交易流水号' duplex="(@query.payId)|escape" />
			<@f.input name='companyNameFrom' label='付款方'duplex="(@query.companyNameFrom)|escape" />
			<@f.input name='companyNameTo' label='收款方' duplex="(@query.companyNameTo)|escape" />
			<@f.dateRange name='timeSection' label='创建时间' code='' />
			<@f.select name='payTransaction' label='交易类目' duplex='@query.payTransaction' options='@query.PayBusinessTypeNames'/>
		<div class="${a.col3(1)}">
			<button class="btn btn-info" ms-on-click="@search()">查询</button>
		</div>
		</@a.queryForm>
		<@a.dataGrid>
			<@a.table cls='table-hover' headers=['交易流水号','付款方','收款方','金额','交易类目','创建时间','状态','操作']>
			<tr ms-for="item in @items">
				<td>{{item.id}}</td>
				<td>{{item.payFrom.companyName}}</td>
				<td>{{item.payTo.companyName}}</td>
				<td :css="[item.money>=0 && {color:'green'}, item.money<0 && {color:'red'}]">{{item.money|currency}}
				</td>
				<td>{{item.payBusinessTypeName}}</td>
				<td>{{(item.createDate)|date2}}</td>
				<td><span :class="item.payStaus|payStausCls">{{item.payStaus|payStausText}}</span></td>
				<td>
					<div class="btn-group">
						<@a.actionButton icon='info' ext='ms-on-click="@actionEdit(item)" ms-if="item.Permission == 1"'>
							支付</@a.actionButton>
						<@a.actionButton icon='info' ext='ms-on-click="@actionEdit(item)" ms-if="item.Permission == 2"'>
							到账确认</@a.actionButton>
						<@a.actionButton ext='ms-on-click="@actionView(item)"'>查看</@a.actionButton>
					</div>
				</td>
			</tr>
			</@a.table>
			<@a.dataAgg></@a.dataAgg>
		</@a.dataGrid>
	</@a.queryDataGrid>
</@a.adminpage>
