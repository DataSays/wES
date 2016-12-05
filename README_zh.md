wES -- [English version](https://github.com/DataSays/wES/blob/master/README.md)
====

**wES** 是一组开源的Java ElasticSearch客户端和工具; 简洁但是很勥 :)

**wES** = Java Retrofit2/OkHttp版本的客户端(不依赖Json类库,高度可定制) + 工具包 + spring-boot demo + 常用的ElasticSearch环境Dockerfile

## wES 模块

**wES** 分割成许多模块, 可以按需选择.
+ `wUtil`: 一些有用的帮助类和工具类.
+ [wES-client](https://github.com/DataSays/wES/tree/master/wES-client): 一个基于Retrofit2/OkHttp的Java客户端, 她是基于官方的[ElasticSearch Rest API规范](https://github.com/elastic/elasticsearch/tree/master/rest-api-spec)生成的. 她包含两种访问ElasticSearch的实现: OkHttp3版本的和Retrofit2版本. 而且她只依赖okhttp3/retrofit2,并且可以通过实现**org.datasays.wes.core.IConvert**接口支持任意一种Java Json类库. 你可以按照你的想法使用她.
+ [wES-toolkit](https://github.com/DataSays/wES/tree/master/wES-toolkit): 一些使用wES-client和ElasticSearch的工具包. 她包含了一套基于Gson的标准IConvert实现及封装代码库.
+ [wES-benchmark](https://github.com/DataSays/wES/blob/master/docs/Benchmark_zh.md): 一份针对以下java ElasticSearch客户端的性能测试报告.
+ [wES-demo](https://github.com/DataSays/wES/tree/master/wES-demo): 一个Spring-boot + Vue.js的web应用, 用于展示一些通用的ElasticSearch使用场景.
+ [wES-docker](https://github.com/DataSays/wES/tree/master/wES-docker): 一些构建ElasticSearch开发/生产环境的常用Dockerfiles和shell脚本.

## wES文档
+ [类库依赖](https://github.com/DataSays/wES/blob/master/docs/Dependencies.md)
+ [从源码构建wES](https://github.com/DataSays/wES/blob/master/docs/build.md)
+ [wES-client](https://github.com/DataSays/wES/blob/master/wES-client/README_zh.md)
+ [wES-toolkit](https://github.com/DataSays/wES/blob/master/wES-toolkit/README_zh.md)
+ [wES-demo](https://github.com/DataSays/wES/blob/master/wES-demo/README_zh.md)
+ [wES-benchmark](https://github.com/DataSays/wES/blob/master/wES-benchmark/README_zh.md)
+ [wES-docker](https://github.com/DataSays/wES/tree/master/wES-docker)
+ [TODO清单](https://github.com/DataSays/wES/blob/master/docs/TODO.md)
+ [更新记录](https://github.com/DataSays/wES/blob/master/docs/ChangeLogs.md)
+ [捐赠](https://github.com/DataSays/wES/blob/master/docs/Contribute.md)

## 关于DataSays小组
+ DataSays小组包含许多全栈攻城狮. 我们为许多公司工作,并构建了许多商业软件项目. 由于一些原因, 我们使用了许多开源软件,但是并没与进行任何形式的捐赠. **现在我们想改变这点!** 这是我们的第一个开源项目, **请大家持续关注,提醒,帮助我们, 谢谢**.

:rocket: