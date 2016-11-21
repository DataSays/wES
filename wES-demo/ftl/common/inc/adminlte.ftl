<#import "fn.ftl" as f>
<#assign adminpage_activeMenu={} />
<#assign adminpage_LoadJs='' />
<#assign adminpage_LoadBootJs='' />
<#--
deps: 'jvectormap', 'layer' , 'chartJS', 'fineUploader'
-->
<#macro adminpage deps=[] activeMenu='' extStartFile=[] extEndFile=[]>
    <#assign Site=Page.site >
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Cache-Control" content="max-age=86400" must-revalidate />
    <title>${Page.pageTitle!''}</title>
    <link rel="shortcut icon" href="/static/${Page.site.theme}/imgs/site/favicon.ico">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="/static/adminlte/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/adminlte/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/static/adminlte/ionicons/css/ionicons.min.css">
    <#if deps?seq_contains('daterangepicker2')>
    <link rel="stylesheet" href="/static/bootstrap-daterangepicker/daterangepicker.css">
    <#elseif deps?seq_contains('daterangepicker136')>
    <link rel="stylesheet" href="/static/adminlte/plugins/daterangepicker1.3.6/daterangepicker-bs3.css">
    <#else>
    <link rel="stylesheet" href="/static/adminlte/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/adminlte/plugins/datetimepicker/css/datetimepicker.css">
    <link rel="stylesheet" href="/static/adminlte/plugins/daterangepicker/daterangepicker-bs3.css">
    </#if>
    
    <link rel="stylesheet" href="/static/adminlte/plugins/iCheck/all.css">
    <#if deps?seq_contains('jvectormap')>
        <!-- jvectormap -->
        <link rel="stylesheet" href="/static/adminlte/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    </#if>
    <#if deps?seq_contains('fineUploader')>
    	 <link href="/static/fine-uploader/fine-uploader-new.css" rel="stylesheet">
       <@fineUploaderTpl/>
    </#if>
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/adminlte/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/static/adminlte/css/skins/_all-skins.min.css">
    <#if deps?seq_contains('switch')>
      <!-- bootstrap-switch -->
    	<link rel="stylesheet" href="/static/adminlte/plugins/switch/css/bootstrap-switch.min.css">
    </#if>    
	<#if deps?seq_contains('select2')>
	<!-- select2 -->
    <link rel="stylesheet" href="/static/adminlte/plugins/select2/select2.css"></script>
    </#if>
    <link rel="stylesheet" href="/static/common/css/admin.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="/static/adminlte/html5shiv/html5shiv.min.js"></script>
    <script src="/static/adminlte/respond/respond.min.js"></script>
    <![endif]-->
		<script src="/static/adminlte/plugins/jQuery/jQuery-2.2.0.min.js"></script>
		<link href="/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
		<script src="/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
    <!-- jQuery 2.2.0 -->

    <script src="/static/layer/layer.js" type="text/javascript"></script>
    <script src="/static/layer/extend/layer.ext.js" type="text/javascript"></script>
    <script src="/static/common/common.js" type="text/javascript"></script>
	<@loadFile files=extStartFile />

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <header class="main-header">
        <!-- Logo -->
        <a href="/" class="logo" target="_blank">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">${Site.name}</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg">${Site.name}</span>
        </a>
        <@navbar />
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <@sidebarMenu />
        </section>
        <!-- /.sidebar -->
    </aside>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
				<#nested/>
    </div>
    <!-- /.content-wrapper -->
    <@footer />
    <#-- <@controlSidebar /> -->
</div>
<!-- ./wrapper -->
<!-- Bootstrap 3.3.5 -->
<script src="/static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="/static/adminlte/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/static/adminlte/js/app.min.js"></script>
<!-- Sparkline -->
<script src="/static/adminlte/plugins/sparkline/jquery.sparkline.min.js"></script>
    <#if deps?seq_contains('jvectormap')>
    <!-- jvectormap -->
    <script src="/static/adminlte/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="/static/adminlte/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    </#if>
		<!-- SlimScroll 1.3.0 -->
		<script src="/static/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <#if deps?seq_contains('chartJS')>
    <!-- ChartJS 1.0.1 -->
    <script src="/static/adminlte/plugins/chartjs/Chart.min.js"></script>
    </#if>

    <#if deps?seq_contains('fineUploader')>
    <!-- fineUploader -->
    <script src="/static/fine-uploader/fine-uploader.js"></script>
		</#if>
    <#if deps?seq_contains('mustache')>
    <!-- mustache -->
		<script src="/static/common/mustache.min.js" type="text/javascript"></script>
		</#if>
	<#if deps?seq_contains('select2')>
	<!-- select2 -->
    <script src="/static/adminlte/plugins/select2/select2.full.min.js"></script>
    <script src="/static/adminlte/plugins/select2/i18n/zh-CN.js"></script>
    </#if>
    <!-- iCheck 1.0.1 -->
    <script src="/static/adminlte/plugins/iCheck/icheck.min.js"></script>
    <#if deps?seq_contains('daterangepicker2')>
    <script src="/static/bootstrap-daterangepicker/moment.js"></script>
    <script src="/static/bootstrap-daterangepicker/daterangepicker.js"></script>
    <#elseif deps?seq_contains('daterangepicker136')>
    <script src="/static/adminlte/plugins/daterangepicker1.3.6/moment.js"></script>
    <script src="/static/adminlte/plugins/daterangepicker1.3.6/daterangepicker.js"></script>
    <#else>
    <script src="/static/adminlte/plugins/daterangepicker/moment.min.js"></script>
    <script src="/static/adminlte/plugins/daterangepicker/daterangepicker.js"></script>
    <!-- date-range-picker -->
    <script src="/static/adminlte/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="/static/adminlte/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="/static/adminlte/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/static/adminlte/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    </#if>
		<#-- AdminLTE dashboard demo (This is only for demo purposes)
		<script src="/static/adminlte/js/pages/dashboard2.js"></script>-->
		<#-- FIXME -->
		<!-- AdminLTE for demo purposes -->
		<script src="/static/adminlte/js/demo.js"></script>
		<#if deps?seq_contains('switch')>
      <!-- bootstrap-switch -->
    	<script src="/static/adminlte/plugins/switch/js/bootstrap-switch.min.js"></script>
    	<script src="/static/jquery-jbox/2.3/docs/jbox-demo-depends/highlight/highlight.js"></script>
    	<script src="/static/adminlte/plugins/switch/js/main.js"></script>
   	</#if>
		<#-- AVALON -->
		<#if deps?seq_contains('avalon')>
    <script src="/static/avalon/avalon.js"></script>
		</#if>
		<#if deps?seq_contains('avalon2')>
    <script src="/static/avalon2/avalon.js"></script>
		</#if>
		<#if deps?seq_contains('avalon158')>
    <script src="/static/avalon/avalon.1.5.8.min.js"></script>
		</#if>

		<#if deps?seq_contains('mui')>
    	<link rel="stylesheet" href="/static/adminlte/plugins/mui-master/dist/css/mui.min.css">
    	<script src="/static/adminlte/plugins/mui-master/dist/js/mui.min.js"></script>
		</#if>

		<!--bootstrap-table-->
		<#if deps?seq_contains('bootstrap_table')>
    	<link rel="stylesheet" href="/static/bootstrap-table/bootstrap-table.css">
    	<script src="/static/bootstrap-table/bootstrap-table.js"></script>
    	<script src="/static/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    	<script src="/static/bootstrap-table/extensions/mobile/bootstrap-table-mobile.js"></script>
    	<script src="/static/common/bootstrap-table-ynzl.js"></script>
		</#if>
		<@loadFile files=extEndFile />

		<script type="text/javascript">
		    ${adminpage_LoadJs!''}
            $(function(){
		        ${adminpage_LoadBootJs!''}
				});
		</script>
		</body>
		</html>
</#macro>

<#macro navbar>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar navbar-static-top" role="navigation">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">切换导航栏</span>
    </a>
    <!-- Navbar Right Menu -->
    <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
            <!-- User Account: style can be found in dropdown.less -->
            <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <img src="${f.out((Page.user.photo)!'','/static/adminlte/img/user2-160x160.jpg')}"
                         class="user-image" alt="${(Page.user.name)!''}">
                    <span class="hidden-xs">${(Page.user.name)!''}</span>
                </a>
                <ul class="dropdown-menu">
                    <!-- User image -->
                    <li class="user-header">
                        <img src="${f.out((Page.user.photo)!'','/static/adminlte/img/user2-160x160.jpg')}"
                             class="img-circle" alt="${(Page.user.name)!''}">
                        <p>
                        ${f.out((Page.user.company.name)!'', '未知')}
                            <small>${f.out((Page.user.office.name)!'','未知')}</small>
                        </p>
                    </li>
                    <!-- Menu Body -->
                <#--
                <li class="user-body">
                  <div class="row">
                    <div class="col-xs-4 text-center">
                      <a href="#">Followers</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Sales</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Friends</a>
                    </div>
                  </div>-->
                    <!-- /.row -->
                <#-- </li> -->
                    <!-- Menu Footer-->
                    <li class="user-footer">
                        <div class="pull-left">
                            <a href="/a//sys/user/info" target="mainFrame" class="btn btn-default btn-flat">个人信息</a>
                        </div>
                    <#--
                    <div class="pull-right">
                      <a href="/a/logout" class="btn btn-default btn-flat">退出</a>
                    </div>
                       -->
                    </li>
                </ul>
            </li>
            <#-- 
            <!-- Control Sidebar Toggle Button
            <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
            </li> -->
            <li>
                <a href="/a/logout"><i class="fa fa-sign-out"></i></a>
            </li>
        </ul>
    </div>

</nav>
</#macro>

<#macro sidebarMenu>
<!-- sidebar menu: : style can be found in sidebar.less -->
<ul class="sidebar-menu">
	<#--  
	<#list Page.getAdminMenu().childs as tdItem>
	<@MenuTree td=tdItem parentId='0' />
	</#list>
	-->
</ul>
</#macro>

<#macro MenuTree td parentId>
<#assign active=adminpage_activeMenuIds?contains(','+td.id+',') />
<!--${adminpage_activeMenu.id}     ==>    ${td.id} ==> ${active?string}-->
<#if td.childs?size gt 0>
	<li class="${active?string('active ', '')} treeview">
		<a href="#">
			<i class="fa ${td.icon!'fa-circle-o'}"></i> <span>${td.name}</span> <i class="fa fa-angle-left pull-right"></i>
		</a>
		<ul class="treeview-menu">
		<#list td.childs as tdItem>
			<@MenuTree td=tdItem parentId=td.id/>
		</#list>
		</ul>
	</li>
<#else>
	<!-- ${td.id} -->
	<li class="${active?string('active ', '')}"><a href="${((td.target!'') =='mainFrame')?string('/a/toIndex?menuId='+td.id, td.href!'')}"><i class="fa ${td.icon!'fa-circle-o'}"></i> <span>${td.name!''}</span></a></li>
</#if>
</#macro>

<#macro controlSidebar>
<!-- Control Sidebar -->
<aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
        <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-wrench"></i></a></li>
        <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
        <div class="tab-pane active" id="control-sidebar-home-tab">
            <h4 class="control-sidebar-heading">皮肤</h4>
            <ul class="list-unstyled clearfix">
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-blue"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 7px; background: #367fa9;"></span><span
                            class="bg-light-blue" style="display: block; width: 80%; float: left; height: 7px;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #222d32;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">蓝色</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-black"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div style="box-shadow: 0 0 2px rgba(0, 0, 0, 0.1)" class="clearfix">
                        <span style="display: block; width: 20%; float: left; height: 7px; background: #fefefe;"></span><span
                            style="display: block; width: 80%; float: left; height: 7px; background: #fefefe;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #222;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">黑色</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-purple"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 7px;"
                              class="bg-purple-active"></span><span class="bg-purple"
                                                                    style="display: block; width: 80%; float: left; height: 7px;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #222d32;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">紫色</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-green"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 7px;"
                              class="bg-green-active"></span><span class="bg-green"
                                                                   style="display: block; width: 80%; float: left; height: 7px;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #222d32;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">绿色</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-red"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 7px;"
                              class="bg-red-active"></span><span class="bg-red"
                                                                 style="display: block; width: 80%; float: left; height: 7px;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #222d32;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">红色</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-yellow"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 7px;"
                              class="bg-yellow-active"></span><span class="bg-yellow"
                                                                    style="display: block; width: 80%; float: left; height: 7px;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #222d32;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">黄色</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-blue-light"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 7px; background: #367fa9;"></span><span
                            class="bg-light-blue" style="display: block; width: 80%; float: left; height: 7px;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">亮蓝</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-black-light"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div style="box-shadow: 0 0 2px rgba(0, 0, 0, 0.1)" class="clearfix">
                        <span style="display: block; width: 20%; float: left; height: 7px; background: #fefefe;"></span><span
                            style="display: block; width: 80%; float: left; height: 7px; background: #fefefe;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">亮黑</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-purple-light"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 7px;"
                              class="bg-purple-active"></span><span class="bg-purple"
                                                                    style="display: block; width: 80%; float: left; height: 7px;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">亮紫</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-green-light"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 7px;"
                              class="bg-green-active"></span><span class="bg-green"
                                                                   style="display: block; width: 80%; float: left; height: 7px;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">亮绿</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-red-light"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 7px;"
                              class="bg-red-active"></span><span class="bg-red"
                                                                 style="display: block; width: 80%; float: left; height: 7px;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">亮红</p></li>
                <li style="float: left; width: 33.33333%; padding: 5px;"><a href="javascript:void(0);"
                                                                            data-skin="skin-yellow-light"
                                                                            style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
                                                                            class="clearfix full-opacity-hover">
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 7px;"
                              class="bg-yellow-active"></span><span class="bg-yellow"
                                                                    style="display: block; width: 80%; float: left; height: 7px;"></span>
                    </div>
                    <div>
                        <span style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
                            style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
                    </div>
                </a>
                    <p class="text-center no-margin">亮黄</p></li>
            </ul>
        </div>

        <div class="tab-pane" id="control-sidebar-settings-tab">
            <h4 class="control-sidebar-heading">布局选项</h4>
            <div class="form-group">
                <label class="control-sidebar-subheading"><input type="checkbox" data-layout="fixed" class="pull-right">
                    Fixed layout</label>
                <p>Activate the fixed layout. You can't use fixed and boxed layouts together</p>
            </div>
            <div class="form-group">
                <label class="control-sidebar-subheading"><input type="checkbox" data-layout="layout-boxed"
                                                                 class="pull-right"> Boxed Layout</label>
                <p>Activate the boxed layout</p>
            </div>
            <div class="form-group">
                <label class="control-sidebar-subheading"><input type="checkbox" data-layout="sidebar-collapse"
                                                                 class="pull-right"> Toggle Sidebar</label>
                <p>Toggle the left sidebar's state (open or collapse)</p>
            </div>
            <div class="form-group">
                <label class="control-sidebar-subheading"><input type="checkbox" data-enable="expandOnHover"
                                                                 class="pull-right"> Sidebar Expand on Hover</label>
                <p>Let the sidebar mini expand on hover</p>
            </div>
            <div class="form-group">
                <label class="control-sidebar-subheading"><input type="checkbox"
                                                                 data-controlsidebar="control-sidebar-open"
                                                                 class="pull-right"> Toggle Right Sidebar Slide</label>
                <p>Toggle between slide over content and push content effects</p>
            </div>
            <div class="form-group">
                <label class="control-sidebar-subheading"><input type="checkbox" data-sidebarskin="toggle"
                                                                 class="pull-right"> Toggle Right Sidebar Skin</label>
                <p>Toggle between dark and light skins for the right sidebar</p>
            </div>
        </div>
    </div>
</aside>
<!-- /.control-sidebar -->
<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
<div class="control-sidebar-bg"></div>
</#macro>

<#-- AdminLte 内容标题 -->
<#macro contentHeader title='' subTitle=''>
<section class="content-header">
  <@breadcrumb />

	<#if title!=''>
	  <h1>
	    ${title}<#if subTitle!=''>
	    <small>${subTitle}</small></#if>
	  </h1>
  <#else>
	   <ul class="nav nav-tabs">
  		<#nested>
	   </ul>
  </#if>
</section>
</#macro>

<#-- AdminLte 导航条 -->
<#macro breadcrumb>
<ol class="breadcrumb">
    <li><a href="/" target="_blank">首页</a></li>
    <#if (adminpage_activeMenu.parent.name)??>
    	<li <a href="${(adminpage_activeMenu.parent.href)!'#'}" target="_blank">${(adminpage_activeMenu.parent.name)!''}</a></li>
    </#if>
    <li class="active">${(adminpage_activeMenu.name)!''}</li>
</ol>
</#macro>
<#-- AdminLte 页面标题 -->
<#macro pageHeader title>
 	<div class="row">
	 	<div class="col-md-12 page-title">
				<div class="col-md-12 text-center" ><h1>${title!'未知标题'}</h1></div>
				<div class="col-md-12 text-center">
					<#nested>
				</div>
			</div>
		</div>
</#macro>
<#-- AdminLte 表单-->
<#macro ViewForm>
<div class="row">
	<div class="col-sm-12">
		<#nested>
	</div>
</div>
</#macro>

<#macro ViewFormItem title="" col=6>
<div class="${a.autoCol(col)}">
<div class="${autoCol(3)} WFormItemLabel">${title}：</div>
<div class="${autoCol(9)} WFormItemValue">
	<#nested>&nbsp;
</div>
</div>
</#macro>

<#--
style:horizontal/''
 -->
<#macro WForm id='' action='' modelAttribute="" style='horizontal'  method='post' ext=''>
<form
	<#if id != ''>id="${id}"</#if>
	<#if modelAttribute != ''>modelAttribute="${modelAttribute}"</#if>
	<#if action != ''>action="${action}"</#if>
	class="form-${style}" method="${method}" ${ext}>
	<#nested>
</form>
</#macro>

<#macro WFormItem2 id label='' name=id type='text' placeholder='' col=4 code='' value=''>
<#if type=='hidden'>
<input type="hidden" id="${id}" name="${name}" value="${value}" ${code}><#nested>
<#else>
<#assign body><#nested></#assign>
<div class="${a.autoCol(col)}">
<div class="form-group">
  <label for="${id}" class="col-xs-4 col-sm-3 control-label ">${label}</label>
  <div class="col-xs-${(body?trim?length>0)?string('8','8')} col-sm-${(body?trim?length>0)?string('6','9')}">
  	<#if type=='text' || type=='email' || type=='password'>
    	<input type="${type}" class="form-control" id="${id}" name="${name}" placeholder="${placeholder}" value="${value}" ${code}>
    	<#if body?trim?length gt 0>
  			</div>
  			<div class="col-xs-8 col-sm-3 control-label-right">${body}</#if>
  		<#elseif type=='readonly'>
    		<input type="text" class="form-control" id="${id}" name="${name}" placeholder="${placeholder}" readonly="readonly" value="${value}" ${code}>
    	<#if body?trim?length gt 0>
  			</div>
  			<div class="col-xs-8 col-sm-3 control-label-right">${body}</#if>
    	<#elseif type=='textarea'>
    		<textarea id="${id}" class="form-control" name="${name}" placeholder="${placeholder}" ${code}>${value}</textarea>
    	<#else>
    	<#if body?trim?length gt 0>
  			</div>
  			<div class="col-xs-8 col-sm-3 control-label-right">${body}
    	</#if>
    </#if>
  </div>
</div>
</div>
</#if>
</#macro>

<#macro fineUploaderTpl>
	 <!-- fineUploader -->
  <script type="text/template" id="fineUploaderTpl">
  <div class="qq-uploader-selector qq-uploader" qq-drop-area-text="拖拽文件到这里">
      <div class="qq-total-progress-bar-container-selector qq-total-progress-bar-container">
          <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-total-progress-bar-selector qq-progress-bar qq-total-progress-bar"></div>
      </div>
      <div class="qq-upload-drop-area-selector qq-upload-drop-area" qq-hide-dropzone>
          <span class="qq-upload-drop-area-text-selector"></span>
      </div>
      <div class="buttons">
          <div class="qq-upload-button-selector qq-upload-button">
              <div>选择文件</div>
          </div>
          <button type="button" id="trigger-upload" class="btn btn-primary">
              <i class="icon-upload icon-white"></i> 上传
          </button>
      </div>
      <span class="qq-drop-processing-selector qq-drop-processing">
          <span>正在处理拖拽上传的文件...</span>
          <span class="qq-drop-processing-spinner-selector qq-drop-processing-spinner"></span>
      </span>
      <ul class="qq-upload-list-selector qq-upload-list" aria-live="polite" aria-relevant="additions removals">
          <li>
              <div class="qq-progress-bar-container-selector">
                  <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-progress-bar-selector qq-progress-bar"></div>
              </div>
              <span class="qq-upload-spinner-selector qq-upload-spinner"></span>
              <img class="qq-thumbnail-selector" qq-max-size="100" qq-server-scale>
              <span class="qq-upload-file-selector qq-upload-file"></span>
              <span class="qq-edit-filename-icon-selector qq-edit-filename-icon" aria-label="编辑文件名"></span>
              <input class="qq-edit-filename-selector qq-edit-filename" tabindex="0" type="text">
              <span class="qq-upload-size-selector qq-upload-size"></span>
              <button type="button" class="qq-btn qq-upload-cancel-selector qq-upload-cancel">取消</button>
              <button type="button" class="qq-btn qq-upload-retry-selector qq-upload-retry">重试</button>
              <button type="button" class="qq-btn qq-upload-delete-selector qq-upload-delete">删除</button>
              <span role="status" class="qq-upload-status-text-selector qq-upload-status-text"></span>
          </li>
      </ul>
  </div>
	</script>
</#macro>

<#-- 上传组件 查看
files: 已上传文件com.ynzl.common.file.entity.Files[]
-->
<#macro VIewFormItemUpFile label files=[] col=12>
	<div class="${a.autoCol(col)}">
		<div class="form-group">
		  <label class="${autoCol(3)} WFormItemLabel">${label}：</label>
			<div  class="${autoCol(9)} table-responsive no-padding">
				<table class="table table-hover">
						<#list files as file>
                <tr>
									<td>${file.filename }${file.filetype }</td>
									<td style="text-align: center;width:60px;"><a href="/a/file/files/downloadFile?id=${file.id}">下载</a></td>
                </tr>
            </#list>
		      </table>
			  </div>
		 </div>
	</div>
</#macro>
<#-- 上传组件
cutSize: 图片切割参数 imgsize1, imgsize2 @see com.ynzl.util.ImgUtil.Cut_Img_Size_1
groupId:com.ynzl.common.file.entity.Files.groupId
files: 已上传文件com.ynzl.common.file.entity.Files[]
-->
<#macro WFormItemUpFile2 id label name=id cutSize='' col=3 groupId='' files=[] delete=false>
<div class="${a.autoCol(12)}">
<div class="form-group">
  <label class="${autoCol(col)} control-label">${label}：</label>
	<div id="${id}" class="${autoCol(9)}"></div>
		<@LoadJs>
		var fu_${id} = new qq.FineUploader({
						element: document.getElementById('${id}'),
            template: 'fineUploaderTpl',
            request: {
                endpoint: '/a/file/files/uploadFile2'
            },
				    <#if delete>deleteFile: {
				        enabled: true,
				        forceConfirm: true,
				        endpoint: '/a/file/files/deleteFile2'
				    },</#if>
            thumbnails: {
                placeholders: {
                    waitingPath: '/static/fine-uploader/placeholders/waiting-generic.png',
                    notAvailablePath: '/static/fine-uploader/placeholders/not_available-generic.png'
                }
            },
            showMessage:function(message){
            	//console.log(message);
            },
            failUpload:'上传失败',
            autoUpload: false
        	});
		$('#${id}').click(function() {
			fu_${id}.setParams({groupId:'${groupId}', isCut:${(cutSize!='')?string('true', 'false')}, cutSize:'${cutSize}'});
			fu_${id}.uploadStoredFiles();
		});
		<#if files?size gt 0>
		var fu_${id}Files = [];
		<#list files as file>
		fu_${id}Files.push({uuid:"${file.id}", name:"${file.filename}", size:"", thumbnailUrl:"${file.filepath}", status: qq.status.UPLOAD_SUCCESSFUL});
		</#list>
		fu_${id}.addInitialFiles(fu_${id}Files);
		</#if>

    </@LoadJs>
  </div>
</div>
</#macro>

<#-- AdminLte tab-->
<#macro WTab items=[] active=0 tabId='tab'>
<#assign WTab_tabId=tabId />
<#assign WTab_index=0 />
<#assign WTab_active=active />
<div class="nav-tabs-custom">
	<ul class="nav nav-tabs">
	<#list items as item>
	<#if item_index == active>
		<li class="active"><a href="#${tabId}_${item_index}" data-toggle="tab">${item}</a></li>
	<#else>
		<li><a href="#${tabId}_${item_index}" data-toggle="tab">${item}</a></li>
	</#if>
	</#list>

	</ul>
	<div class="tab-content">
	<#nested>
	</div>
	<!-- /.tab-content -->
</div>
<!-- nav-tabs-custom -->
<#assign WTab_tabId='' />
<#assign WTab_index='' />
<#assign WTab_active='' />
</#macro>

<#-- AdminLte tab panel-->
<#macro WTabPanel>
	<#if WTab_active==WTab_index>
		<div class="tab-pane active" id="${WTab_tabId}_${WTab_index}">
	<#else>
		<div class="tab-pane" id="${WTab_tabId}_${WTab_index}">
	</#if>
			<#nested>
		</div>
		<!-- /.tab-pane -->
<#assign WTab_index=WTab_index+1 />
</#macro>

<#-- AdminLte Title row-->
<#macro TitleRow title subTitle=''>
      <!-- title row -->
      <div class="row">
        <div class="col-xs-12">
          <h2 class="page-header">
            ${title}
            <#if subTitle != ''><small class="pull-right">${subTitle}</small></#if>
          </h2>
        </div>
        <!-- /.col -->
      </div>
</#macro>

<#-- AdminLte Text button-->
<#macro TextBtn text id=''cls='default' icon='' ext='' >
<button <#if id!=''> id="${id}" name="${id}" </#if> type="button" class="btn btn-${cls}" ${ext}><#if icon!=''><i class="fa ${icon} hidden-xs"></i></#if> ${text} </button>
</#macro>

<#macro footer>
<footer class="main-footer">
    <div class="pull-right hidden-xs">
        <b>Version</b> 2.0
    </div>
    COPYRIGHT &copy; ${(Page.site.company)!''}版权所有.
</footer>
</#macro>

<#-- 分页按钮
page：分页page对象
formId：from标签的id，默认值为"searchForm"
-->
<#macro pagination page formId='searchForm'>
			<ul class="pagination pagination-sm no-margin pull-right">${page.getAdminPageHtml()}</ul>
			<@LoadJs>
				function page(n,s){
					$("#${formId}").append("<input id='pageNo' name='pageNo' type='hidden' value='${page.pageNo}'/><input id='page.size' name='pageSize' type='hidden' value='${page.pageSize}'/>");
					$("#pageNo").val(n);
					$("#pageSize").val(s);
					$("#${formId}").submit();
			        return false;
	    	}
	    </@LoadJs>
</#macro>

<#-- 获取admin page 的 url-->
<#function adminPageUrl>
<#return AdminPageHref!(Page?api.menuUrl(adminpage_activeMenu))>
</#function>

<#-- 自动根据col计算grid class-->
<#function autoCol col>
<#if col==3>
<#return 'col-md-12 col-lg-4'>
<#elseif col==4>
<#return 'col-md-12 col-lg-3'>
<#elseif col==6>
<#return 'col-md-12 col-lg-6'>
<#elseif col==8>
<#return 'col-md-12 col-lg-9'>
<#elseif col==9>
<#return 'col-md-12 col-lg-8'>
<#elseif col==12>
<#return 'col-md-12'>
<#else>
<#return 'col-md-12 col-lg-${col}'>
</#if>
</#function>

<#-- 3等分整个容器,count表示在这种布局中占几列 -->
<#function col3 count>
<#if count==1>
<#return 'col-xs-12 col-sm-6 col-md-4 col-lg-4'>
<#elseif count==2>
<#return 'col-xs-12 col-sm-12 col-md-6 col-lg-6'>
<#elseif count==3>
<#return 'col-xs-12 col-sm-12 col-md-12 col-lg-12'>
</#if>
</#function>

<#-- 在页面完后运行js -->
<#macro LoadJs boot=false>
<#if !boot>
<#assign adminpage_LoadJsTmp>
	<#nested>
</#assign>
<#assign adminpage_LoadJs=adminpage_LoadJs+adminpage_LoadJsTmp/>
<#else>
<#assign adminpage_LoadBootJsTmp>
	<#nested>
</#assign>
<#assign adminpage_LoadBootJs=adminpage_LoadBootJs+adminpage_LoadBootJsTmp/>
</#if>
</#macro>

<#-- 列表中的操作按钮 -->
<#macro actionButton icon='default' onClick='' ext=''>
<button type="button" class="btn btn-xs btn-${icon}" ${f.htmlAttr('onClick', onClick)} ${ext}><#nested></button>
</#macro>

<#-- 表单操作按钮 -->
<#macro formActionBtns backBtn=true back="true">
			<div class="row action-btn">
      	<div class="col-sm-12 invoice-col">
					<#nested>
					<#if back == 'true'>
						<@a.TextBtn text='返回' ext='onclick="history.go(-1)"' />
					</#if>
      	</div>
      </div>
      <!-- /.row -->
</#macro>

<#------ checkbox组件-
<#nested>内容
<#list items as i>
		    <label>
		      <input type="checkbox" name="${id}" class="${style}" value="${i.value}">
		    </label>
		    ${i.label}
		    </#list>
------->
<#macro checkbox id label style='minimal' checkboxClass='icheckbox_minimal-blue'  col=4>
<div class="${autoCol(col)}">
		<div class="form-group">
	    <label for="${id}" class="${autoCol(3)} control-label">${label}：</label>
	    <div class="${autoCol(9)}">
		      <#nested>
				<@LoadJs>
				$('input[type="checkbox"].${style}').iCheck({
            checkboxClass: '${checkboxClass}'
           <#----默认是蓝色，如果需要其他颜色可以根据adminlte组件进行自行传参------>
        });
        </@LoadJs>
	  </div>
  </div>
</div>
</#macro>
<#------ radio组件  nested内容
<#list items as i>
<label>
  <input type="radio" name="${id}" class="${style}" value="${i.value}">
</label>
${i.label}
</#list>
-------->
<#macro radio id label style='minimal' radioClass='iradio_minimal-blue' col=4>
<div class="${autoCol(col)}">
		<div class="form-group">
	    <#if label != ''><label for="${id}" class="${autoCol(3)} control-label">${label}：</label></#if>
	    <div class="${autoCol(9)}">
		      <#nested>
				<@LoadJs>
				$('input[type="radio"].${style}').iCheck({
          radioClass: '${radioClass}'
        });
        </@LoadJs>
	  </div>
  </div>
</div>
</#macro>

<#-------select2----------->
<#macro select2 id label col=4 ext="">
	<div class="${autoCol(col)}">
		<div class="form-group">
	    <label for="${id}" class="col-xs-4 col-sm-3 control-label">${label}</label>
		  <div class="col-xs-8 col-sm-9">
		     <select id="${id}" name="${id}" class="form-control select2" data-placeholder="请选择${label}" style="width: 100%;"  ${ext}>
		      <#nested>
		     </select>
		  </div>
	  </div>
  </div>
</#macro>

<#macro selectDict id label dict selected='' col=4 ext="">
<@select2 id=id label=label col=col ext=ext>
	<#list dict?keys as key>
	<option value="${key}" ${(key==selected)?string('selected','')}>${dict[key]}</option>
	</#list>>
</@select2>
</#macro>


<#--日期组件-->
<#macro date id label col=4 format='yyyy-mm-dd' ext=''>
	<div class="${autoCol(col)}">
		<div class="form-group">
	    <label for="${id}" class="col-xs-4 col-sm-3 control-label">${label}</label>
		  <div class="col-xs-8 col-sm-9">
		  <input type="text" id="${id}"  name="${id}" class="form-control pull-right" ${ext} readonly>
		  <@LoadJs>
		     $('#${id}').datepicker({
            language: "zh-CN",
            clearBtn: true,
            autoclose:true,
            format: "${format}"
        });
        </@LoadJs>
		  </div>
	  </div>
  </div>
</#macro>

<#--日期组件-->
<#macro dateTime id label col=4 format='yyyy-mm-dd hh:ii' ext=''>
	<div class="${autoCol(col)}">
		<div class="form-group">
	    <label for="${id}" class="col-xs-4 col-sm-3 control-label">${label}</label>
		  <div class="col-xs-8 col-sm-9">
		  <div class="input-group">
		  <input type="text" id="${id}"  name="${id}" class="form-control pull-right" ${ext} readonly><span class="input-group-addon" onclick="datetime_remove_${id}();"><i class="fa fa-remove"></i></span>
		  <@LoadJs>
		     $('#${id}').datetimepicker({
            language: "zh-CN",
            autoclose:true,
            format: "${format}"
        });
        function datetime_remove_${id}(){
        	$("#${id}").val("");
        }
        </@LoadJs>
		  </div>
      </div>
	  </div>
  </div>
</#macro>
<#--------日期时间范围选择控件
type 分别为 date dateTime dateBtn
---------->
 <#macro dateRange id label col=4 timeStart='' timeEnd='' type='date'>
<div class="${autoCol(col)}">
	<div class="form-group">
		<label for="${id}" class="col-xs-4 col-sm-3 control-label">${label}</label>
		<div class="col-xs-8 col-sm-9">
		  <div class="input-group">
		    <div class="input-group-addon" <#if type == 'dateBtn'>style='padding:0;border:none;'</#if>>
		      <#if type == 'date'>
		      <i class="fa fa-calendar"></i>
		      <@LoadJs>
						$('#${id}').daterangepicker({
							format: 'YYYY-MM-DD',
							 "locale": {
					        "separator": " - ",
					        "applyLabel": "确认",
					        "cancelLabel": "取消",
					        "fromLabel": "From",
					        "toLabel": "至",
					        "customRangeLabel": "自定义",
					        "weekLabel": "W",
					        "daysOfWeek": [
					            "日",
					            "一",
					            "二",
					            "三",
					            "四",
					            "五",
					            "六"
					        ],
					        "monthNames": [
					            "一月",
					            "二月",
					            "三月",
					            "四月",
					            "五月",
					            "六月",
					            "七月",
					            "八月",
					            "九月",
					            "十月",
					            "十一月",
					            "十二月"
					        ],
					        "firstDay": 1
					    }
						},
			        function (start, end) {
			          //直接改这个控件上的value竟然改不了
			          $('#${id}').val(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
			          //所以另起一个hidden来存储选择以后的值，这个才能改
			          $('#${id}_hidden_time_start').val(start.format('YYYY-MM-DD HH:mm:ss'));
			          $('#${id}_hidden_time_end').val(end.format('YYYY-MM-DD HH:mm:ss'));
			        });
					</@LoadJs>
		      <#elseif type== 'dateTime'>
		      <i class="fa fa-clock-o"></i>
		      <@LoadJs>
						$('#${id}').daterangepicker({
							timePicker: true,
							timePickerIncrement: 20,
							format: 'YYYY-MM-DD H:mm A'
						});
					</@LoadJs>
		      <#elseif type== 'dateBtn'>
		      	<button class="btn btn-default pull-right" type="button" id="${id}">
	            <i class="fa fa-calendar"></i> 日期范围 <i class="fa fa-caret-down"></i>
	          </button>
		      <@LoadJs>
						$('#${id}').daterangepicker({
								format : 'YYYY-MM-DD',
	              ranges: {
	                '今天': [moment(), moment()],
	                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	                '过去7天': [moment().subtract(6, 'days'), moment()],
	                '过去30天': [moment().subtract(29, 'days'), moment()],
	                '当月': [moment().startOf('month'), moment().endOf('month')],
	                '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	              },
	              startDate: moment().subtract(29, 'days'),
	              endDate: moment()
	            },
			        function (start, end) {
			          $('#${id}').val(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
			        }
		        );
					</@LoadJs>
		      </#if>

		    </div>
		    <div>${timeStart} - ${timeEnd}</div>
		    <input type="hidden" id="${id}_hidden_time_start" name="timeStart" value="${timeStart}" />
		    <input type="hidden" id="${id}_hidden_time_end" name="timeEnd" value="${timeEnd}" />
		    <input type="text" id="${id}"  class="form-control pull-right" readonly>
		  </div><!-- /.input group -->
		</div>
	</div>
</div>
</#macro>
<#macro dateRangeForDate id label col=4 timeStart='' timeEnd='' type='date'>
<div class="${autoCol(col)}">
	<div class="form-group">
		<label for="${id}" class="col-xs-4 col-sm-3 control-label">${label}</label>
		<div class="col-xs-8 col-sm-9">
		  <div class="input-group" style="width:100%">
		    <button type="button" class="btn btn-default pull-right" id="${id}" style="width:100%;text-align:left;background-color:#fff;border-radius:0">
        	<i class="fa fa-calendar" style="margin-right:16px;"></i><span><#if timeStart!=''&&timeEnd!=''>${timeStart} - ${timeEnd}</#if></span>
        </button>
        <input type="hidden" id="${id}_hidden_time_start" name="timeStart" value="${timeStart}" />
		    <input type="hidden" id="${id}_hidden_time_end" name="timeEnd" value="${timeEnd}" />
		      <#if type == 'date'>
		      <@LoadJs>
						$('#${id}').daterangepicker({
                format: 'YYYY-MM-DD', //控件中from和to 显示的日期格式
                locale: {
                    applyLabel: '确定',
                    cancelLabel: '取消',
                    fromLabel: '起始时间',
                    toLabel: '结束时间',
                    customRangeLabel: '自定义',
                    daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                        '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    firstDay: 1
                },
						},
			        function (start, end) {
			        	var s = start.format('YYYY-MM-D');
                var e = end.format('YYYY-MM-D');
                var t = s + ' - ' + e;

                if (start._isValid == false && end._isValid == false) {
                    s = "";
                    e = "";
                    t ="请选择日期范围"
                }

                $('#${id}').find('span').html(t);
                $('#${id}')
                        .next().val(s)
                        .next().val(e);
			        });
					</@LoadJs>
		      <#elseif type== 'dateTime'>
		      <i class="fa fa-clock-o"></i>
		      <@LoadJs>
						$('#${id}').daterangepicker({
							timePicker: true,
							timePickerIncrement: 20,
							format: 'YYYY-MM-DD H:mm A'
						});
					</@LoadJs>
		      <#elseif type== 'dateBtn'>
		      	<button class="btn btn-default pull-right" type="button" id="${id}">
	            <i class="fa fa-calendar"></i> 日期范围 <i class="fa fa-caret-down"></i>
	          </button>
		      <@LoadJs>
						$('#${id}').daterangepicker({
								format : 'YYYY-MM-DD',
	              ranges: {
	                '今天': [moment(), moment()],
	                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	                '过去7天': [moment().subtract(6, 'days'), moment()],
	                '过去30天': [moment().subtract(29, 'days'), moment()],
	                '当月': [moment().startOf('month'), moment().endOf('month')],
	                '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	              },
	              startDate: moment().subtract(29, 'days'),
	              endDate: moment()
	            },
			        function (start, end) {
			          $('#${id}').val(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
			        }
		        );
					</@LoadJs>
		      </#if>
		  </div><!-- /.input group -->
		</div>
	</div>
</div>
</#macro>

<#---------消息提示 alerts--
style =info   danger warning success
------>
<#macro alertsMsg msg='' type=''>
	<#if msg?? && msg!= ''>
     <#if type?? && type != ''>
     	<#if type = 'info'>
     		<@alerts msg=msg style=type icon='info' text='消息'/>
     	<#elseif type = 'danger'>
     		<@alerts msg=msg style=type icon='ban' text='危险'/>
     	<#elseif type = 'warning'>
     		<@alerts msg=msg style=type icon='warning' text='警告'/>
     	<#elseif type = 'success'>
     		<@alerts msg=msg style=type icon='check' text='成功'/>
     	</#if>
     <#else>
     	<#if msg?index_of("失败") !=-1>
     		<@alerts msg=msg style='warning' icon='warning' text='警告'/>
			<#else>
				<@alerts msg=msg style='success' icon='check' text='成功'/>
     	</#if>
   	</#if>
   </#if>
</#macro>

<#macro alerts msg style cls='close' icon='' text='Alert'  ext='times' >
    <div class="alert alert-${style} alert-dismissible">
     <button type="button" class="${cls}" data-dismiss="alert" aria-hidden="true">&${ext};</button>
      <h4><i class="icon fa fa-${icon}"></i> ${text}!</h4>
      ${msg}
     </div>
</#macro>

<#-- modal操作按钮-->
<#macro modalButton target id="" cls='default' icon=''  ext="">
<button <#if id!=''> id="${id}" name="${id}" </#if> type="button" class="btn btn-${cls}" data-toggle="modal" data-target="#${target}" ${ext}>
	<#if icon!=''><i class="fa ${icon}"></i></#if><#nested>
</button>
</#macro>

<#--弹出框：style= default primary info warning success danger
[['确认生产','success','onclick="createQuote()";'],['确认生产','outline','']]二维数组参数 按钮名称 按钮样式 按钮事件   需保留三个参数，可为null
-->
<#macro modalDialog id cls='' title='信息' closeText='关闭' btns=[] ext='times' width="">
<div class="modal <#if cls!=''>modal-${cls}</#if>" id="${id}">
  <div class="modal-dialog" >
    <div class="modal-content"<#if width!=''> style="width:${width}px;" </#if>>
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&${ext};</span></button>
        <h4 class="modal-title">${title}</h4>
      </div>
      <div class="modal-body">
       	<#nested>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">${closeText}</button>
        <#list btns as btn>
        	<button type="button" class="btn btn-<#if btn[1] != ''>${btn[1]}<#else>primary</#if>"
	        		<#if btn[2] != ''>${btn[2]}<#else> data-dismiss="modal"</#if>
        	>${btn[0]}</button>
        </#list>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</#macro>

<#macro info  id="info" label="提示" cls="error">
	<div class="${autoCol(12)}" id="${id}Div" style="display:none;margin-top:6px;">
		<div class="form-group has-${cls}">
			<label  for="${id}Label" class="${autoCol(3)} control-label">${label}：</label>
			<div class="${autoCol(9)}">
				<label id="${id}Label"><#nested></label>
			</div>
		</div>
	</div>
</#macro>
<#--
参数 http://blog.csdn.net/mafan121/article/details/50402070
size = mini、small、normal、large
Color= primary、info、success、warning、danger、default
-->
<#macro swtich id  size="small" onText="ON" offText="OFF" onColor="primary" offColor="info" col=12 ext="">
	<div class="${autoCol(col)}">
		<div class="form-group">
			<label  for="${id}Label" class="${autoCol(3)} control-label"><#nested>：</label>
			<div class="${autoCol(9)}">
				<input type="checkbox" id="${id}" data-size="${size}" data-on-text="${onText}" data-off-text="${offText}" data-on-color="${onColor}" data-off-color="${offColor}" ${ext}>
			</div>
		</div>
	</div>
</#macro>

<#macro box id='' cls='primary' ext=''>
	<div ${f.htmlAttr('id', id)} class="box box-${cls}" ${ext}><#nested></div>
</#macro>

<#macro boxHeader title=''  subTitle='' id='' cls='' ext=''  icon='' >
		<div class="box-header ${cls}" ${f.htmlAttr('id', id)}  ${ext}>
			<#if icon!= ''><i class="fa fa-${icon}"></i></#if>
    	<#if title!= ''><h2 class="box-title">${title}<#if subTitle!=''>
	    <small>&nbsp;${subTitle}</small></#if></h2></#if>
			<#nested>
    </div>
</#macro>

<#macro boxBody id='' cls='' ext=''>
	<div class="box-body ${cls}" ${f.htmlAttr('id', id)} ${ext}>
		<#nested>
	</div>
</#macro>

<#macro boxFooter  id='' cls='' ext=''>
	<div class="box-footer ${cls}" ${f.htmlAttr('id', id)} ${ext}>
		<#nested>
	</div>
</#macro>

<#macro box3col>
	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
		<#nested>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4 hidden-xs hidden-sm">
		<#nested>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4 hidden-xs hidden-sm hidden-md">
		<#nested>
	</div>
</#macro>

<#function msclass cnd base yes no=''>
     <#return "ms-class=\"{{(${cnd})?'${base} ${yes}':'${base} ${no}'}}\"">
</#function>

<#-- 通用查询列表 -->
<#macro queryDataGrid id='queryDataGrid'>
<#assign QueryDataGridInitJs = ''/>
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12 ms-controller" ms-controller="${id}">
		<#nested>
		</div>
	</div>
</section>
<@LoadJs>
${QueryDataGridInitJs}
</@LoadJs>
</#macro>

<#-- 通用查询列表的过滤条件 -->
<#macro queryForm id='queryDiv' action='ajax'>
	<@box>
	<@boxBody>
        <#if action='ajax'>
		<span class="form-horizontal">
		<#nested>
		</span>
        <#else>
		<@WForm id=id+'_form' action=action>
		<#nested>
		</@WForm>
        </#if>
	</@boxBody>
	</@box>
</#macro>

<#-- 通用查询列表的数据显示table -->
<#macro dataGrid headers=[] cls='' code='' id='dataDiv'>
	<@box>
	<@boxBody cls='table-responsive no-padding'>
	<@table cls=cls headers=headers code=code>
	<#nested>
	</@table>
	</@boxBody>
	<@boxFooter cls='clearfix'>
		<@a.pagination2 id=id+'_page'/>
	</@boxFooter>
	</@box>
</#macro>

<#-- 通用查询列表的数据汇总信息 -->
<#macro dataAgg>
	<#nested>
</#macro>

<#-- 分页按钮 
page：分页page对象
formId：from标签的id，默认值为"searchForm"
-->
<#macro pagination2 id=''>
    <span class="inline no-margin pull-right" style="line-height: 30px;">共 {{@page.total}} 条</span>
    <ul class="pagination pagination-sm inline no-margin pull-right" ${f.htmlAttr('id', id)} >
        <li><a href="javascript:;" ms-click="@changePage(1)"><i class="fa fa-angle-double-left"></i></a></li>
        <#--<li ms-class="{'disabled' : @page.pageNo == 1}"><a href="javascript:;" ms-click="@changePage(@page.pageNo-1)"><i class="fa fa-angle-left"></i></a></li>-->
        <li ms-for="el in @page.pageItems" ms-class="{'active' : @page.pageNo == el}" ms-click="@changePage(el)"><a href="javascript:;">{{el}}</a></li>
        <#--<li ms-class="{'disabled' : @page.pageNo == @page.pageTotal}"><a href="javascript:;" ms-click="@changePage(@page.pageNo+1)"><i class="fa fa-angle-right"></i></a></li>-->
        <li><a href="javascript:;" ms-click="@changePage(@page.pageTotal)"><i class="fa fa-angle-double-right"></i></a></li>
    </ul>
</#macro>

<#-- 简单的表格 -->
<#macro table headers=[] cls='' code=''>
<table class="table ${cls}" ${code}>
  <tr>
	<#list headers as row>
	<#if row?is_collection>
		<th ${row[0]}>${row[1]}</th>	
	<#elseif row?is_string>
		<th>${row}</th>	
	</#if>
	</#list>
	</tr>
	<#nested>
</table>
</#macro>

<#macro loadFile files>
<#list files as file>
<#if file?ends_with('js')>
	<script src="${file}" type="text/javascript"></script>
<#elseif file?ends_with('css')>
	<link href="${file}" type="text/css" rel="stylesheet" />
</#if>
</#list>
</#macro>
