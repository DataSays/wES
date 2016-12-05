wES-client User Guide
====

<!-- TOC insertAnchor:false withLinks:true -->

- [Overview](#overview)
- [wES-client Goals](#wes-client-goals)
- [wES-client Benchmark](#wes-client-benchmark)
- [Using wES-client](#using-wes-client)
  - [Using wES-client for Maven](#using-wes-client-for-maven)
  - [Using wES-client for Gradle](#using-wes-client-for-gradle)
  - [implement `org.datasays.wes.core.IConvert`](#implement-orgdatasayswescoreiconvert)
  - [create `org.datasays.wes.client.EsHelper` instance and init it](#create-orgdatasayswesclienteshelper-instance-and-init-it)
  - [Indexing a doc](#indexing-a-doc)
  - [search a doc](#search-a-doc)
- [special usage](#special-usage)
  - [setup global HTTP cache(read only scene)](#setup-global-http-cacheread-only-scene)
  - [HTTP authentication](#http-authentication)
- [wES-client core class](#wes-client-core-class)
  - [`org.datasays.wes.client.EsHelper`](#orgdatasayswesclienteshelper)
  - [`org.datasays.wes.core.IConvert`](#orgdatasayswescoreiconvert)
  - [`org.datasays.wes.client.EsService`](#orgdatasayswesclientesservice)
- [CodeGen on `wES-toolkit` project](#codegen-on-wes-toolkit-project)
  - [`org.datasays.wes.toolkit.codegen.EsRestSpecGen`](#orgdatasayswestoolkitcodegenesrestspecgen)
  - [`org.datasays.wes.toolkit.codegen.EsRestSpecGen4Retrofit`](#orgdatasayswestoolkitcodegenesrestspecgen4retrofit)
- [ElasticSearch Compatibility](#elasticsearch-compatibility)

<!-- /TOC -->

## Overview
A Java Retrofit2/OkHttp client, it was generated from [ElasticSearch Rest API spec](https://github.com/elastic/elasticsearch/tree/master/rest-api-spec). It has two ways to visit ElasticSearch: OkHttp3 way or Retrofit2 way. So it's only depend on OkHttp3/Retrofit2, and it can support any popular Java Json lib by implement **org.datasays.wes.core.IConvert** interface. You can use it as you need approaches.

## wES-client Goals
* Base on official [ElasticSearch Rest API spec](https://github.com/elastic/elasticsearch/tree/master/rest-api-spec) codegen core codes, keep it support the lastest ES v5.x version, and re-codegen specified ES API version. 
* Use Retrofit2/OkHttp for http request, keep scalability and usability.
* Each API comment for the official ES API Documentation URL.
* Use interface `IConvert` abstracting Json serialize and deserialize, u can customize for many Json java lib.e.g.[Gson](https://github.com/google/gson/), [jackson](https://github.com/FasterXML/jackson), [fastjson](https://github.com/alibaba/fastjson).
* mini class library dependencies, keep it simple and Highly customizable. If u need out-of-the-box, u can use [wES-toolkit](https://github.com/DataSays/wES/blob/master/docs/wESToolkit/UserGuide.md).

## wES-client Benchmark
[wES-benchmark](https://github.com/DataSays/wES/blob/master/wES-benchmark/README.md): A benchmark report for all java ElasticSearch client. supported API:
  + [wES-client](https://github.com/DataSays/wES)[DONE]
  + [Jest](https://github.com/searchbox-io/Jest)[DONE]
  + [elastic Java API 5.0](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html)
  + [elastic Java REST Client 5.0](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html)

## Using wES-client
wES-client use gradle build as a jar project, it's supported for Maven or Gradle project. Or download the jar file then add it into classpath.

### Using wES-client for Maven
On ur pom.xml file, add wES-client dependency into dependencies part like this:
```xml
  <dependency>
    <groupId>io.github.datasays</groupId>
    <artifactId>wES-client</artifactId>
    <version>1.0</version>
    <scope>compile</scope>
  </dependency>
```

### Using wES-client for Gradle
On ur build.gradle file, add wES-client dependency into dependencies part like this:
```groovy
  compile 'io.github.datasays:wES-client:1.0'
```

### implement `org.datasays.wes.core.IConvert`
Because of mini class library dependencies, wES-client don't include any Json convert implments, u need implment it or use [wES-toolkit](https://github.com/DataSays/wES/tree/master/wES-toolkit) `org.datasays.wes.toolkit.WGsonConvert`. `WGsonConvert` is a Gson implement.

### create `org.datasays.wes.client.EsHelper` instance and init it
```java
  EsHelper esHelper = new EsHelper("http://127.0.0.1:9200");
  //set base auth info for all action
  esHelper.baseAuth(user, password);
  //set log config
  esHelper.setLogFlag(LOG.isDebugEnabled(), LOG.isDebugEnabled(), LOG.isDebugEnabled());
  //init
  esHelper.init(new OkHttpClient.Builder().build(), new WGsonConvert());	
```

### Indexing a doc
```java
  Index action = esHelper.index(index, type, id);
  action.setBody(body);
  return esHelper.post(action, Object.class);
```

### search a doc
```java
  Search action = esHelper.search(index, type, id);
  action.setBody(queryDsl);
  return esHelper.post(action, WSearchResult.class, TestDoc.class);
```

## special usage
### setup global HTTP cache(read only scene)
```java
  OkHttpClient.Builder cBuilder = new OkHttpClient.Builder();
  cBuilder.cache(new Cache(new File("./tmp"), 10000));
  esHelper.init(cBuilder.build(), new WGsonConvert());
```

### HTTP authentication
+ authentication for all actions once
```java
  esHelper.baseAuth(user, password);  	
```

+ authentication for one action
```java
  action.baseAuth(user, password);
```

## wES-client core class
### `org.datasays.wes.client.EsHelper`
The okhttp3 like API to visit the ElasticSearch. It support all ElasticSearch API `org.datasays.wes.actions` and Enum types `org.datasays.wes.types`. You can use URL ways or send GET/POST/PUT/HEAD/DELETE request with a Json request body. The source code include all comments like this:
![Url Params](../docs/images/Api1.png "Url Params")
![Url Part, Request Body and supported HTTP methods](../docs/images/Api2.png  "Url Part, Request Body and supported HTTP methods")

### `org.datasays.wes.core.IConvert`
A Java interface for JSON serializer and parser.

### `org.datasays.wes.client.EsService`
The retrofit2 like API to visit the ElasticSearch. It only support to convert Json response to Object and can't pass the params on URL. So it can't used on complex scene. we will fix it with a better implement ASAP.

## CodeGen on `wES-toolkit` project
### `org.datasays.wes.toolkit.codegen.EsRestSpecGen`
It's the CodeGen java program for the okhttp3 like API. It will gen all codes on package 'org.datasays.wes.actions' and 'org.datasays.wes.types'. The code is will simple, you can modify it as your way. 

You can run it as a normal Java program or run it by shell command on wES-toolkit path:
```shell
    gradle EsRestSpecGen
```

### `org.datasays.wes.toolkit.codegen.EsRestSpecGen4Retrofit`
It's like the EsRestSpecGen, but it's for The retrofit2 like API **EsService**.
```shell
    gradle EsRestSpecGen4Retrofit
```

## ElasticSearch Compatibility
Current version support the lastest ES version v5.x. You also can get another ES Rest API version from https://github.com/elastic/elasticsearch/tree/master/rest-api-spec by other Git branch versions, copy them to override all **/wES-toolkit/api/** json files and re-Gen the codes. 