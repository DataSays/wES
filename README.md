wES -- [中文版](https://github.com/DataSays/wES/blob/master/README_zh.md)
====

**wES** is set of open source Java ElasticSearch client and tools; compact, yet powerful.

**wES** = Java Retrofit2/OkHttp client(Don't depend on any Java Json lib, Highly customizable) + toolkit + spring-boot demo + usefull ElasticSearch env Dockerfile

## wES Modules

**wES** is split into many modules, so choose what to use.
+ `wUtil`: Some usefull helper and utils class.
+ [wES-client](https://github.com/DataSays/wES/tree/master/wES-client): A Java Retrofit2/OkHttp client, it was generated from [ElasticSearch Rest API spec](https://github.com/elastic/elasticsearch/tree/master/rest-api-spec). It has two ways to visit ElasticSearch: OkHttp3 way or Retrofit2 way. So it's only depend on OkHttp3/Retrofit2, and it can support any popular Java Json lib by implement **org.datasays.wes.core.IConvert** interface. You can use it as you need approaches.
+ [wES-toolkit](https://github.com/DataSays/wES/tree/master/wES-toolkit): Some tools and utility for wES-client and ElasticSearch.
+ [wES-benchmark](https://github.com/DataSays/wES/blob/master/docs/Benchmark_zh.md): A benchmark report for all java client.
+ [wES-demo](https://github.com/DataSays/wES/tree/master/wES-demo): A Spring-boot + Vue.js web application for show some common ElasticSearch usage scenarios.
+ [wES-docker](https://github.com/DataSays/wES/tree/master/wES-docker): Some Dockerfiles and shell scripts for build ElasticSearch dev/production env.

## wES Documentation
+ [Dependencies](https://github.com/DataSays/wES/blob/master/docs/Dependencies.md)
+ [Building wES from source](https://github.com/DataSays/wES/blob/master/docs/build.md)
+ [wES-client](https://github.com/DataSays/wES/blob/master/wES-client/README.md)
+ [wES-toolkit](https://github.com/DataSays/wES/blob/master/wES-toolkit/README.md)
+ [wES-demo](https://github.com/DataSays/wES/blob/master/wES-demo/README.md)
+ [wES-benchmark](https://github.com/DataSays/wES/blob/master/wES-benchmark/README.md)
+ [wES-docker](https://github.com/DataSays/wES/tree/master/wES-docker)
+ [TODO list](https://github.com/DataSays/wES/blob/master/docs/TODO.md)
+ [Contribute](https://github.com/DataSays/wES/blob/master/docs/Contribute.md)

## About DataSays Team
+ The DataSays Team includes some full stack coder. We were worked for many company and build many commercial projects. Because of some reasons, we use many open source project but didn't contribute to them. Now, we will amend this. This is the first open project, **pls keep an eye to monitor, remind and help us, tks**.


:rocket: