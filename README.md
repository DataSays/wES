wES
====

**wES** is set of open source Java ElasticSearch client and tools; compact, yet powerful.

**wES = java retrofit2 client + toolkit + spring-boot demo + docker files**

## wES Modules

**wES** is split into many modules, so choose what to use.
Some tools and utility modules are:

+ `wUtil`: Some usefull helper and utils class.
+ `wES-client`: A Java retrofit2 client, it was generated from [ElasticSearch Rest API spec](https://github.com/elastic/elasticsearch/tree/master/rest-api-spec). It has two ways to visit ElasticSearch: okhttp3 like api or retrofit2 like. So it's only depend on okhttp3/retrofit2, and it can support any popular Java Json lib by implement **org.datasays.wes.core.IConvert** interface. You can use it as you need approaches.
+ `wES-toolkit`: Some tools and utility for wES-client and ElasticSearch.
+ `wES-demo`: A Spring-boot + Vue.js Application for show some common ElasticSearch usage scenarios.
+ `wES-docker`: Some Docker Files and shell scripts for build ElasticSearch env.
+ `wES-awesome`: A curated list of awesome things related to ElasticSearch.

## Dependencies
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

## Building wES from source

**wES** is built with [Gradle](http://gradle.org/) on JDK8,
targeting Java 1.8. You don't have to install anything,
the only prerequisites are [Git](http://help.github.com/set-up-git-redirect)
and Java JDK.

### Check out sources

Simply clone **wES** Git repo:

    git clone https://github.com/DataSays/wES.git wES

### Compile and test, build jars

You can build the wES project with:

    gradlew build

This will build all jars and run all unit tests.
To skip the tests (for faster build), execute:

    gradlew build -x test

## Contribute

Feel free to contribute! Follow these steps:

First time only:

+ fork the **wES** repo (`upstream`) to your GitHub account (`origin`).
+ clone `origin` as your `local` repo

Every other time:

+ update both `origin` and `local` repos from `upstream`
+ create new branch for a feature or bug fix
+ commit often :)
+ once when work is done, push local changes to your `origin`
+ send us a pull request (PR)

We will pickup up from there :)

## TODO list
### wES
+ A chinese version README.
+ Add gradle bintray plugins to publish all wES project files into Maven Repository.

### wES-client
+ A better retrofit2 like API.

### wES-toolkit
+ Full Junit test case for all wES-client API.
+ A benchmark report for all java client:
    - [wES-client](https://github.com/DataSays/wES)
    - [elastic Java API 5.0](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html)
    - [elastic Java REST Client 5.0](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html)
    - [Jest](https://github.com/searchbox-io/Jest) 
+ A Spring Data API for wES-client.

### wES-demo
+ **Now, wES-demo only a Spring boot scaffold, pls wait it.**

### wES-docker
+ A Dockerfile `wES-standalone` based on apline Linux and Orcale JDK8 for standalone dev env.
+ A Dockerfile `wES-cluster` based on apline Linux and Orcale JDK8 for cluster production env.
+ build in some popular plugins Dockerfile. e.g. elasticsearch-head, elasticsearch-analysis-ik...
+ A Dockerfile `wES-ElasticStack` for Elastic Stack.

### wES-awesome
+ A curated list of awesome things related to ElasticSearch.

## About DataSays Team
+ The DataSays Team includes some full stack coder. We were worked for many company and build many commercial projects. Because of some reasons, we use many open source project but diddo not contribute to them. Now, we will amend this. This is the first open project, **pls keep an eye to monitor, remind and help us, tks**.


:rocket: