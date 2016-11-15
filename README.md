wES
====

**wES** is set of open source Java ElasticSearch and tools; compact, yet powerful.

**wES = java retrofit2 client + toolkit + spring-boot demo + docker files**

## wES Modules

**wES** is split into many modules, so choose what to use.
Some tools and utility modules are:

+ `wES-client` contains a java retrofit2 client, it's only depend on retrofit2.
+ `wES-toolkit`, some tools and utility for ElasticSearch. 
+ `wES-demo` A Spring-boot + Vue.js Application for show some common ElasticSearch usage scenarios.
+ `wES-docker` some Docker Files and shell scripts for build ElasticSearch env.
+ `wES-awesome` A curated list of awesome things related to ElasticSearch.

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

:rocket: