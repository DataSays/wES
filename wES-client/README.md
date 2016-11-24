wES-client
====

A Java retrofit2 client, it was generated from [ElasticSearch Rest API spec](https://github.com/elastic/elasticsearch/tree/master/rest-api-spec). It has two ways to visit ElasticSearch: okhttp3 like api or retrofit2 like. So it's only depend on okhttp3/retrofit2, and it can support any popular Java Json lib by implement **org.datasays.wes.core.IConvert** interface. You can use it as you need approaches.

## wES-client core class

+ `org.datasays.wes.client.BaseEsHelper`: The okhttp3 like API to visit the ElasticSearch. It support all ElasticSearch API `org.datasays.wes.actions` and Enum types `org.datasays.wes.types`. You can use URL ways or send GET/POST/PUT/HEAD/DELETE request with a Json request body. The source code include all comments like this:
![Url Params](../docs/images/Api1.png "Url Params")
![Url Part, Request Body and supported HTTP methods](../docs/images/Api2.png  "Url Part, Request Body and supported HTTP methods")
+ `org.datasays.wes.core.IConvert`: A Java interface for JSON serializer and parser.
+ `org.datasays.wes.client.EsService`: The retrofit2 like API to visit the ElasticSearch. It only support to convert Json response to Object and can't pass the params on URL. So it can't used on complex scene. we will fix it with a better implement ASAP.

## CodeGen on `wES-toolkit` project
+ `org.datasays.wes.toolkit.codegen.EsRestSpecGen`: It's the CodeGen java program for the okhttp3 like API. It will gen all codes on package 'org.datasays.wes.actions' and 'org.datasays.wes.types'. The code is will simple, you can modify it as your way. You also can get another ES Rest API version from https://github.com/elastic/elasticsearch/tree/master/rest-api-spec by other Git branch versions, copy them to override all **/wES-toolkit/api/** json files and re-Gen the codes. You can run it as a normal Java program or run it by shell command on wES-toolkit path:

    gradle EsRestSpecGen

+ `org.datasays.wes.toolkit.codegen.EsRestSpecGen4Retrofit`: It's like the EsRestSpecGen, but it's for The retrofit2 like API **EsService**.

## TODO list
+ Add gradle bintray plugins to publish all wES project files into Maven Repository.
+ A better retrofit2 like API.
+ Full Junit test case for all API.
+ A benchmark report for all java client:
    - [wES-client](https://github.com/DataSays/wES)
    - [elastic Java API 5.0](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html)
    - [elastic Java REST Client 5.0](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html)
    - [Jest](https://github.com/searchbox-io/Jest) 
+ A Spring Data API for wES-client.

