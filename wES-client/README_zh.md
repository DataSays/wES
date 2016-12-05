wES-client 用户手册
====

<!-- TOC insertAnchor:false withLinks:true -->

- [概述](#%E6%A6%82%E8%BF%B0)
- [wES-client目标](#wes-client%E7%9B%AE%E6%A0%87)
- [wES-client性能](#wes-client%E6%80%A7%E8%83%BD)
- [使用wES-client](#%E4%BD%BF%E7%94%A8wes-client)
  - [通过Maven使用wES-client](#%E9%80%9A%E8%BF%87maven%E4%BD%BF%E7%94%A8wes-client)
  - [通过Gradle使用wES-client](#%E9%80%9A%E8%BF%87gradle%E4%BD%BF%E7%94%A8wes-client)
  - [实现`org.datasays.wes.core.IConvert`](#%E5%AE%9E%E7%8E%B0orgdatasayswescoreiconvert)
  - [创建并初始化`org.datasays.wes.client.EsHelper`实例](#%E5%88%9B%E5%BB%BA%E5%B9%B6%E5%88%9D%E5%A7%8B%E5%8C%96orgdatasayswesclienteshelper%E5%AE%9E%E4%BE%8B)
  - [索引一个文档](#%E7%B4%A2%E5%BC%95%E4%B8%80%E4%B8%AA%E6%96%87%E6%A1%A3)
  - [搜索一个文档](#%E6%90%9C%E7%B4%A2%E4%B8%80%E4%B8%AA%E6%96%87%E6%A1%A3)
- [特殊用法](#%E7%89%B9%E6%AE%8A%E7%94%A8%E6%B3%95)
  - [设置全局HTTP缓存(只读场景使用)](#%E8%AE%BE%E7%BD%AE%E5%85%A8%E5%B1%80http%E7%BC%93%E5%AD%98%E5%8F%AA%E8%AF%BB%E5%9C%BA%E6%99%AF%E4%BD%BF%E7%94%A8)
  - [HTTP认证](#http%E8%AE%A4%E8%AF%81)
- [wES-client核心类](#wes-client%E6%A0%B8%E5%BF%83%E7%B1%BB)
  - [`org.datasays.wes.client.EsHelper`](#orgdatasayswesclienteshelper)
  - [`org.datasays.wes.core.IConvert`](#orgdatasayswescoreiconvert)
  - [`org.datasays.wes.client.EsService`](#orgdatasayswesclientesservice)
- [代码生成器](#%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90%E5%99%A8)
  - [`org.datasays.wes.toolkit.codegen.EsRestSpecGen`](#orgdatasayswestoolkitcodegenesrestspecgen)
  - [`org.datasays.wes.toolkit.codegen.EsRestSpecGen4Retrofit`](#orgdatasayswestoolkitcodegenesrestspecgen4retrofit)
- [ElasticSearch版本兼容](#elasticsearch%E7%89%88%E6%9C%AC%E5%85%BC%E5%AE%B9)

<!-- /TOC -->

## 概述
一个基于Retrofit2/OkHttp的Java客户端, 她是基于官方的[ElasticSearch Rest API规范](https://github.com/elastic/elasticsearch/tree/master/rest-api-spec)生成的. 她包含两种访问ElasticSearch的实现: OkHttp3版本的和Retrofit2版本. 而且她只依赖okhttp3/retrofit2,并且可以通过实现**org.datasays.wes.core.IConvert**接口支持任意一种Java Json类库. 你可以按照你的想法使用她.

## wES-client目标
* 基于官方的[ElasticSearch Rest API规范](https://github.com/elastic/elasticsearch/tree/master/rest-api-spec)生成核心代码,保证支持最近版本的ES v5.x,并且可重新生成特定版本的ES API.
* 使用Retrofit2/OkHttp作为http访问层,保证可扩展性及易用性.
* 每个API都注释了ES官方API文档链接.
* 使用`IConvert`接口抽象Json序列化,可定制支持多种Json序列化类库.例如:[Gson](https://github.com/google/gson/), [jackson](https://github.com/FasterXML/jackson), [fastjson](https://github.com/alibaba/fastjson).
* 最小类库依赖,尽可能简单.高度可定制化,如果只需要开箱即用,请使用[wES-toolkit](https://github.com/DataSays/wES/blob/master/docs/wESToolkit/UserGuide.md).

## wES-client性能
[wES-benchmark](https://github.com/DataSays/wES/blob/master/wES-benchmark/README_zh.md): 一份针对以下java ElasticSearch客户端的性能测试报告. 支持以下API对比:
  + [wES-client](https://github.com/DataSays/wES)[已完成]
  + [Jest](https://github.com/searchbox-io/Jest)[已完成]
  + [elastic Java API 5.0](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html)
  + [elastic Java REST Client 5.0](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html)

## 使用wES-client
wES-client使用gradle构建为一个jar工程,所以可以支持Maven和Gradle项目使用,也可以下载jar后直接加入classpath使用.

### 通过Maven使用wES-client
在你的pom.xml文件中的dependencies部分增加wES-client类库依赖:
```xml
  <dependency>
    <groupId>io.github.datasays</groupId>
    <artifactId>wES-client</artifactId>
    <version>1.0</version>
    <scope>compile</scope>
  </dependency>
```

### 通过Gradle使用wES-client
在你的build.gradle文件中的dependencies部分增加wES-client类库依赖:
```groovy
  compile 'io.github.datasays:wES-client:1.0'
```

### 实现`org.datasays.wes.core.IConvert`
为了保持最小依赖,所以wES-client不包含任何Json实现,使用前需要自行实现一个或者使用[wES-toolkit](https://github.com/DataSays/wES/tree/master/wES-toolkit)中的`org.datasays.wes.toolkit.WGsonConvert`. `WGsonConvert`是Gson版本的实现.

### 创建并初始化`org.datasays.wes.client.EsHelper`实例
```java
  EsHelper esHelper = new EsHelper("http://127.0.0.1:9200");
  //set base auth info for all action
  esHelper.baseAuth(user, password);
  //set log config
  esHelper.setLogFlag(LOG.isDebugEnabled(), LOG.isDebugEnabled(), LOG.isDebugEnabled());
  //init
  esHelper.init(new OkHttpClient.Builder().build(), new WGsonConvert());	
```

### 索引一个文档
```java
  Index action = esHelper.index(index, type, id);
  action.setBody(body);
  return esHelper.post(action, Object.class);
```

### 搜索一个文档
```java
  Search action = esHelper.search(index, type, id);
  action.setBody(queryDsl);
  return esHelper.post(action, WSearchResult.class, TestDoc.class);
```

## 特殊用法
### 设置全局HTTP缓存(只读场景使用)
```java
  OkHttpClient.Builder cBuilder = new OkHttpClient.Builder();
  cBuilder.cache(new Cache(new File("./tmp"), 10000));
  esHelper.init(cBuilder.build(), new WGsonConvert());
```

### HTTP认证
+ 所有action统一认证
```java
  esHelper.baseAuth(user, password);  	
```

+ 单独为一个action进行认证
```java
  action.baseAuth(user, password);
```

## wES-client核心类
### `org.datasays.wes.client.EsHelper`
使用`okhttp3`样式的API访问ElasticSearch. 她支持所有ElasticSearch REST API:`org.datasays.wes.actions`和枚举类型:`org.datasays.wes.types`. 你可以设置URL的query parameter或者使用GET/POST/PUT/HEAD/DELETE请求带着一个Json的request body. 源代码中包含相关的文档和参数说明,就像这样:
![Url Params](../docs/images/Api1.png "Url Params")
![Url Part, Request Body and supported HTTP methods](../docs/images/Api2.png  "Url Part, Request Body and supported HTTP methods")

### `org.datasays.wes.core.IConvert`
通用的Request Body和Response Body序列化和反序列化接口,实现方式可以参见:[wES-toolkit](https://github.com/DataSays/wES/tree/master/wES-toolkit)的`org.datasays.wes.toolkit.WGsonConvert`

### `org.datasays.wes.client.EsService`
使用`retrofit2`样式的API访问ElasticSearch. 她只支持转化Json Response Body到Object, 并且不能传递URL query parameter.所以不能在复杂场景中使用. 我们将尽快修复这个问题.

## 代码生成器
### `org.datasays.wes.toolkit.codegen.EsRestSpecGen`
这是一个Java代码生成程序生成okhttp3样式的API:`org.datasays.wes.client.EsHelper`. 并且生成所有包`org.datasays.wes.actions`和`org.datasays.wes.types`下的代码. 这些代码很简单,你能按照你的方式修改她们. 
    
你可以像一个常规java程序运行她或者在`wES-toolkit`目录下运行shell命令:
```shell
  gradle EsRestSpecGen
```

### `org.datasays.wes.toolkit.codegen.EsRestSpecGen4Retrofit`
类似EsRestSpecGen, 她是生成retrofit2样式的API `org.datasays.wes.client.EsService`.
```shell
    gradle EsRestSpecGen4Retrofit
```

## ElasticSearch版本兼容
当前版本支持最新版本的v5.x. 如果需要兼容以前版本,可以从https://github.com/elastic/elasticsearch/tree/master/rest-api-spec获取不同Git分支的特定版本的ES Rest API定义文件, 复制他们覆盖所有目录**/wES-toolkit/api/**下的Json文件,再重新生成代码.
