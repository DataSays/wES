<#import "/inc/fn.ftl" as c>
<#import "/inc/adminlte.ftl" as a>
<#macro item id label='' col=1>
<div class="${a.col3(col)}">
	<div class="form-group">
		<label for="${id}" class="col-xs-4 col-sm-3 control-label ">${label}</label>
		<div class="col-xs-8 col-sm-9">
			<#nested>
		</div>
	</div>
</div>
</#macro>

<#-- type:text/email/password -->
<#macro input name label id=name value='' type='text' placeholder='' code='' col=1 duplex=''>
	<@item id=id label=label col=col>
	<input type="${type}"
	       class="form-control" ${c.attrs({'id':id,'name':name,'value':value,'placeholder':placeholder, 'ms-duplex':duplex})} ${code}>
	</@item>
</#macro>

<#macro textarea name label id=name value='' code='' col=1 duplex=''>
	<@item id=id label=label col=col>
	<textarea ${c.attrs({'id':id,'name':name,'ms-duplex':duplex})} ${code}>${value}
</textarea>
	</@item>
</#macro>

<#macro dateRange name label id=name code='' col=1 startDate='2016-01-01' endDate='2016-12-31'>
	<@item id=id label=label col=col>
	<div class="input-group">
		<div class="input-group-addon">
			<i class="fa fa-calendar"></i>
		</div>
		<input type="text" ${c.attrs({'id':id})} class="form-control pull-right" ${code}>
	</div>
	</@item>
	<@a.LoadJs boot=false>
	var objDateRange_${id} = newDateRange('#${id}', '${startDate}', '${endDate}');
	</@a.LoadJs>
</#macro>

<#-- 原生的select,不带搜索,适合少量数据显示-->
<#macro select name label id=name value='' nvs={} code='' col=1 duplex='' options=''>
	<@item id=id label=label col=col>
	<select class="form-control" ${c.attrs({'id':id,'name':name,'value':value, 'ms-duplex':duplex})}>
		<#list nvs?keys as name>
			<option value="${name}" ${(name==value)?string('selected','')}>${nvs[name]}</option>
		</#list>
		<#if options != ''>
			<option ms-for="(k,v) in ${options}" ms-attr="{value: k}">{{v}}</option>
		</#if>
	</select>
	</@item>
</#macro>

<#-- 带搜索功能的select-->
<#macro select2 name label id=name value='' nvs={} code='' col=1>
	<@item id=id label=label col=col>
	<select class="form-control" ${c.attrs({'id':id,'name':name,'value':value})}>
		<#list nvs?keys as name>
			<option value="${name}" ${(name==value)?string('selected','')}>${nvs[name]}</option>
		</#list>
	</select>
	</@item>
	<@a.LoadJs boot=true>
	var objSelect2_${id} = newSelect2('#${id}');
	</@a.LoadJs>
</#macro>
