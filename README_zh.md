wES -- [English version](https://github.com/DataSays/wES/blob/master/README.md)
====

**wES** 是一组开源的Java ElasticSearch客户端和工具; 简洁但是很勥 :)

**wES** = Java Retrofit2/OkHttp版本的客户端(不依赖Json类库,高度可定制) + 工具包 + spring-boot demo + 常用的ElasticSearch环境Dockerfile

## wES 模块

**wES** 分割成许多模块, 可以按需选择.
+ `wUtil`: 一些有用的帮助类和工具类.
+ `wES-client`: 一个基于Retrofit2/OkHttp的Java客户端, 她是基于官方的[ElasticSearch Rest API规范](https://github.com/elastic/elasticsearch/tree/master/rest-api-spec)生成的. 她包含两种访问ElasticSearch的实现: OkHttp3版本的和Retrofit2版本. 而且她只依赖okhttp3/retrofit2,并且可以通过实现**org.datasays.wes.core.IConvert**接口支持任意一种Java Json类库. 你可以按照你的想法使用她.
+ `wES-toolkit`: 一些使用wES-client和ElasticSearch的工具包. 她包含了一套基于Gson的标准IConvert实现及封装代码库.
+ `wES-demo`: 一个Spring-boot + Vue.js的web应用, 用于展示一些通用的ElasticSearch使用场景.
+ `wES-docker`: 一些构建ElasticSearch开发/生产环境的常用Dockerfiles和shell脚本.
+ `wES-awesome`: 一组关于ElasticSearch的awesome清单列表.

## 类库依赖
### wUtil
+ [Slf4j](http://www.slf4j.org)
+ [Jodd](http://jodd.org/)
+ [Gson](https://github.com/google/gson)
+ [Retrofit2](https://github.com/square/retrofit)+[OkHttp](https://github.com/square/okhttp)
+ [Junit4](http://junit.org/)

### wES-client
+ [Slf4j-api](http://www.slf4j.org)
+ [Retrofit2](https://github.com/square/retrofit)+[OkHttp](https://github.com/square/okhttp)

### wES-toolkit
+ wUtil
+ wES-client

### wES-demo
+ wES-toolkit
+ wUtil
+ wES-client
+ [Spring-boot](http://projects.spring.io/spring-boot/)
+ [Muse UI](https://github.com/museui/muse-ui)
+ [TypeScript](http://www.typescriptlang.org/)

## 从源码构建wES

**wES**使用[Gradle](http://gradle.org/)构建, 使用JDK8,生成目标java 1.8代码. 除了[Git](http://help.github.com/set-up-git-redirect)和Java JDK, 你不需要安装其他工具.

### 检出源码

简单地clone **wES** Git repo:

    git clone https://github.com/DataSays/wES.git wES

### 编译,测试,构建jars

你能使用以下命令构建wES工程:

    gradlew build

它将构建所有jars并且运行所有单元测试.
为了跳过测试(更快的构建速度),执行:

    gradlew build -x test

## 捐赠

自由捐赠! 按照以下步骤:

只是第一次执行:

+ fork **wES**仓库 (`upstream`)到你的GitHub帐号(`origin`).
+ clone `origin` 到你本地的 `local`仓库

每次都执行:

+ 从`upstream`更新`origin`和`local`仓库
+ 为一个新功能或者bug修复创建一个分支
+ 经常commit :)
+ 一旦工作做完,就push本地改动到你的`origin`
+ 给我们发送一个pull request (PR)

我们将从中挑选 :)

## TODO清单
### wES
+ _中文版本的README._ [已完成]
+ _添加gradle bintray插件发布所有wES项目文件到Maven Repository._ [已完成但是有bug!]

### wES-client
+ 一个更好的Retrofit2实现版本API.

### wES-toolkit
+ 为所有的wES-client API创建完整的Junit单元测试.
+ _一份针对以下java ElasticSearch客户端的性能测试报告:_
    - _[wES-client](https://github.com/DataSays/wES)[已完成]_
    - _[Jest](https://github.com/searchbox-io/Jest)[已完成]_
    - [elastic Java API 5.0](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html)
    - [elastic Java REST Client 5.0](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html)
+ 为wES-client实现一个Spring Data版本的API.

### wES-demo
+ **目前, wES-demo只是一个Spring boot的脚手架, 请耐心等待.**

### wES-docker
+ 一个基于apline Linux和Orcale JDK8, 用于构建单机开发环境的Dockerfile `wES-standalone`.
+ 一个基于apline Linux和Orcale JDK8, 用于构建集群生产环境的Dockerfile `wES-cluster`.
+ 构建一个包含通用插件及工具的Dockerfile. 例如: elasticsearch-head, elasticsearch-analysis-ik等等
+ 一组Elastic Stack全部产品的Dockerfile.

### wES-awesome
+ 一组关于ElasticSearch的awesome清单列表.

## 关于DataSays小组
+ DataSays小组包含许多全栈攻城狮. 我们为许多公司工作,并构建了许多商业软件项目. 由于一些原因, 我们使用了许多开源软件,但是并没与进行任何形式的捐赠. **现在我们想改变这点!** 这是我们的第一个开源项目, **请大家持续关注,提醒,帮助我们, 谢谢**.

:rocket: