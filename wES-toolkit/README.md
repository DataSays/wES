wES-toolkit
====
Some tools and utility for wES-client and ElasticSearch.

## wES-toolkit core class

+ CodeGen `org.datasays.wes.toolkit.codegen.EsRestSpecGen` and `org.datasays.wes.toolkit.codegen.EsRestSpecGen4Retrofit`.
+ `org.datasays.wes.EsHelper2` is a org.datasays.wes.client.EsHelper implement. It's include basic API for visit ElasticSearch and use Gson for JSON serializer and parser. This is a standard version wES-client for wES-demo.
+ `org.datasays.wes.toolkit.EsDataHelper` is a sub-class for EsHelper2. It's focus on ElasticSearch Data and Scheme maintain. e.g.
    - backup/recover ES data/mappings by Json files.
    - delete all data from index/type/query.
    - reindex a index to tmp-index, re-create index, put mappings and recover data back.

+ `org.datasays.wes.toolkit.WGsonConvert` is a Gson version  implement for `IConvert`. **u can refer to implement other JSON lib.**
+ package `org.datasays.wes.vo`: some base java POJO class.

## CodeGen on `wES-toolkit` project
pls read [wES/README.md](https://github.com/DataSays/wES/blob/master/README.md)



