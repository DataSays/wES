package org.datasays.wes.client;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.datasays.wes.actions.*;
import org.datasays.wes.core.IConvert;
import org.datasays.wes.core.WHttpClient;

public class EsHelper extends WHttpClient {
	protected HttpUrl server;

	public EsHelper() {
		super();
	}

	public void init(String server, OkHttpClient client, IConvert convert) {
		if (server.trim().endsWith("/")) {
			server = server.trim().substring(0, server.trim().length() - 1);
		}
		this.server = HttpUrl.parse(server);
		super.init(client, convert);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-bulk.html
	public Bulk bulk(String index, String type) {
		return new Bulk(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-alias.html
	public CatAliases catAliases(String name) {
		return new CatAliases(server).setParts(name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-allocation.html
	public CatAllocation catAllocation(String node_id) {
		return new CatAllocation(server).setParts(node_id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-count.html
	public CatCount catCount(String index) {
		return new CatCount(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-fielddata.html
	public CatFielddata catFielddata(String fields) {
		return new CatFielddata(server).setParts(fields);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-health.html
	public CatHealth catHealth() {
		return new CatHealth(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat.html
	public CatHelp catHelp() {
		return new CatHelp(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-indices.html
	public CatIndices catIndices(String index) {
		return new CatIndices(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-master.html
	public CatMaster catMaster() {
		return new CatMaster(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-nodeattrs.html
	public CatNodeattrs catNodeattrs() {
		return new CatNodeattrs(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-nodes.html
	public CatNodes catNodes() {
		return new CatNodes(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-pending-tasks.html
	public CatPendingTasks catPendingTasks() {
		return new CatPendingTasks(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-plugins.html
	public CatPlugins catPlugins() {
		return new CatPlugins(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-recovery.html
	public CatRecovery catRecovery(String index) {
		return new CatRecovery(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-repositories.html
	public CatRepositories catRepositories() {
		return new CatRepositories(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-segments.html
	public CatSegments catSegments(String index) {
		return new CatSegments(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-shards.html
	public CatShards catShards(String index) {
		return new CatShards(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-snapshots.html
	public CatSnapshots catSnapshots(String repository) {
		return new CatSnapshots(server).setParts(repository);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
	public CatTasks catTasks() {
		return new CatTasks(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-templates.html
	public CatTemplates catTemplates(String name) {
		return new CatTemplates(server).setParts(name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-thread-pool.html
	public CatThreadPool catThreadPool(String thread_pool_patterns) {
		return new CatThreadPool(server).setParts(thread_pool_patterns);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-request-scroll.html
	public ClearScroll clearScroll(String scroll_id) {
		return new ClearScroll(server).setParts(scroll_id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-allocation-explain.html
	public ClusterAllocationExplain clusterAllocationExplain() {
		return new ClusterAllocationExplain(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-update-settings.html
	public ClusterGetSettings clusterGetSettings() {
		return new ClusterGetSettings(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-health.html
	public ClusterHealth clusterHealth(String index) {
		return new ClusterHealth(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-pending.html
	public ClusterPendingTasks clusterPendingTasks() {
		return new ClusterPendingTasks(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-update-settings.html
	public ClusterPutSettings clusterPutSettings() {
		return new ClusterPutSettings(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-reroute.html
	public ClusterReroute clusterReroute() {
		return new ClusterReroute(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-state.html
	public ClusterState clusterState(String index, String metric) {
		return new ClusterState(server).setParts(index, metric);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-stats.html
	public ClusterStats clusterStats(String node_id) {
		return new ClusterStats(server).setParts(node_id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-count.html
	public Count count(String index, String type) {
		return new Count(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-percolate.html
	public CountPercolate countPercolate(String index, String type, String id) {
		return new CountPercolate(server).setParts(index, type, id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-index_.html
	public Create create(String index, String type, String id) {
		return new Create(server).setParts(index, type, id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-delete.html
	public Delete delete(String index, String type, String id) {
		return new Delete(server).setParts(index, type, id);
	}

	//documentation: https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-delete-by-query.html
	public DeleteByQuery deleteByQuery(String index, String type) {
		return new DeleteByQuery(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-scripting.html
	public DeleteScript deleteScript(String id, String lang) {
		return new DeleteScript(server).setParts(id, lang);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-template.html
	public DeleteTemplate deleteTemplate(String id) {
		return new DeleteTemplate(server).setParts(id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-get.html
	public Exists exists(String index, String type, String id) {
		return new Exists(server).setParts(index, type, id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-explain.html
	public Explain explain(String index, String type, String id) {
		return new Explain(server).setParts(index, type, id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-field-stats.html
	public FieldStats fieldStats(String index) {
		return new FieldStats(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-get.html
	public Get get(String index, String type, String id) {
		return new Get(server).setParts(index, type, id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-scripting.html
	public GetScript getScript(String id, String lang) {
		return new GetScript(server).setParts(id, lang);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-get.html
	public GetSource getSource(String index, String type, String id) {
		return new GetSource(server).setParts(index, type, id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-template.html
	public GetTemplate getTemplate(String id) {
		return new GetTemplate(server).setParts(id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-index_.html
	public Index index(String index, String type, String id) {
		return new Index(server).setParts(index, type, id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-analyze.html
	public IndicesAnalyze indicesAnalyze(String index) {
		return new IndicesAnalyze(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-clearcache.html
	public IndicesClearCache indicesClearCache(String index) {
		return new IndicesClearCache(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-open-close.html
	public IndicesClose indicesClose(String index) {
		return new IndicesClose(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-create-index.html
	public IndicesCreate indicesCreate(String index) {
		return new IndicesCreate(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-delete-index.html
	public IndicesDelete indicesDelete(String index) {
		return new IndicesDelete(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
	public IndicesDeleteAlias indicesDeleteAlias(String index, String name) {
		return new IndicesDeleteAlias(server).setParts(index, name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
	public IndicesDeleteTemplate indicesDeleteTemplate(String name) {
		return new IndicesDeleteTemplate(server).setParts(name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-exists.html
	public IndicesExists indicesExists(String index) {
		return new IndicesExists(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
	public IndicesExistsAlias indicesExistsAlias(String index, String name) {
		return new IndicesExistsAlias(server).setParts(index, name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
	public IndicesExistsTemplate indicesExistsTemplate(String name) {
		return new IndicesExistsTemplate(server).setParts(name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-types-exists.html
	public IndicesExistsType indicesExistsType(String index, String type) {
		return new IndicesExistsType(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-flush.html
	public IndicesFlush indicesFlush(String index) {
		return new IndicesFlush(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-synced-flush.html
	public IndicesFlushSynced indicesFlushSynced(String index) {
		return new IndicesFlushSynced(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-forcemerge.html
	public IndicesForcemerge indicesForcemerge(String index) {
		return new IndicesForcemerge(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-index.html
	public IndicesGet indicesGet(String index, String feature) {
		return new IndicesGet(server).setParts(index, feature);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
	public IndicesGetAlias indicesGetAlias(String index, String name) {
		return new IndicesGetAlias(server).setParts(index, name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-field-mapping.html
	public IndicesGetFieldMapping indicesGetFieldMapping(String index, String type, String fields) {
		return new IndicesGetFieldMapping(server).setParts(index, type, fields);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-mapping.html
	public IndicesGetMapping indicesGetMapping(String index, String type) {
		return new IndicesGetMapping(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-settings.html
	public IndicesGetSettings indicesGetSettings(String index, String name) {
		return new IndicesGetSettings(server).setParts(index, name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
	public IndicesGetTemplate indicesGetTemplate(String name) {
		return new IndicesGetTemplate(server).setParts(name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-upgrade.html
	public IndicesGetUpgrade indicesGetUpgrade(String index) {
		return new IndicesGetUpgrade(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-open-close.html
	public IndicesOpen indicesOpen(String index) {
		return new IndicesOpen(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
	public IndicesPutAlias indicesPutAlias(String index, String name) {
		return new IndicesPutAlias(server).setParts(index, name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-put-mapping.html
	public IndicesPutMapping indicesPutMapping(String index, String type) {
		return new IndicesPutMapping(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-update-settings.html
	public IndicesPutSettings indicesPutSettings(String index) {
		return new IndicesPutSettings(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
	public IndicesPutTemplate indicesPutTemplate(String name) {
		return new IndicesPutTemplate(server).setParts(name);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-recovery.html
	public IndicesRecovery indicesRecovery(String index) {
		return new IndicesRecovery(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-refresh.html
	public IndicesRefresh indicesRefresh(String index) {
		return new IndicesRefresh(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-rollover-index.html
	public IndicesRollover indicesRollover(String alias, String new_index) {
		return new IndicesRollover(server).setParts(alias, new_index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-segments.html
	public IndicesSegments indicesSegments(String index) {
		return new IndicesSegments(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-shards-stores.html
	public IndicesShardStores indicesShardStores(String index) {
		return new IndicesShardStores(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-shrink-index.html
	public IndicesShrink indicesShrink(String index, String target) {
		return new IndicesShrink(server).setParts(index, target);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-stats.html
	public IndicesStats indicesStats(String index, String metric) {
		return new IndicesStats(server).setParts(index, metric);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
	public IndicesUpdateAliases indicesUpdateAliases() {
		return new IndicesUpdateAliases(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-upgrade.html
	public IndicesUpgrade indicesUpgrade(String index) {
		return new IndicesUpgrade(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-validate.html
	public IndicesValidateQuery indicesValidateQuery(String index, String type) {
		return new IndicesValidateQuery(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/
	public Info info() {
		return new Info(server).setParts();
	}

	//documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
	public IngestDeletePipeline ingestDeletePipeline(String id) {
		return new IngestDeletePipeline(server).setParts(id);
	}

	//documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
	public IngestGetPipeline ingestGetPipeline(String id) {
		return new IngestGetPipeline(server).setParts(id);
	}

	//documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
	public IngestPutPipeline ingestPutPipeline(String id) {
		return new IngestPutPipeline(server).setParts(id);
	}

	//documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
	public IngestSimulate ingestSimulate(String id) {
		return new IngestSimulate(server).setParts(id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-multi-get.html
	public Mget mget(String index, String type) {
		return new Mget(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-percolate.html
	public Mpercolate mpercolate(String index, String type) {
		return new Mpercolate(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-multi-search.html
	public Msearch msearch(String index, String type) {
		return new Msearch(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/current/search-multi-search.html
	public MsearchTemplate msearchTemplate(String index, String type) {
		return new MsearchTemplate(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-multi-termvectors.html
	public Mtermvectors mtermvectors(String index, String type) {
		return new Mtermvectors(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-nodes-hot-threads.html
	public NodesHotThreads nodesHotThreads(String node_id) {
		return new NodesHotThreads(server).setParts(node_id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-nodes-info.html
	public NodesInfo nodesInfo(String node_id, String metric) {
		return new NodesInfo(server).setParts(node_id, metric);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-nodes-stats.html
	public NodesStats nodesStats(String metric, String index_metric, String node_id) {
		return new NodesStats(server).setParts(metric, index_metric, node_id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-percolate.html
	public Percolate percolate(String index, String type, String id) {
		return new Percolate(server).setParts(index, type, id);
	}

	//documentation: http://www.elastic.co/guide/
	public Ping ping() {
		return new Ping(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-scripting.html
	public PutScript putScript(String id, String lang) {
		return new PutScript(server).setParts(id, lang);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-template.html
	public PutTemplate putTemplate(String id) {
		return new PutTemplate(server).setParts(id);
	}

	//documentation: https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-reindex.html
	public Reindex reindex() {
		return new Reindex(server).setParts();
	}

	//documentation: https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-reindex.html
	public ReindexRethrottle reindexRethrottle(String task_id) {
		return new ReindexRethrottle(server).setParts(task_id);
	}

	//documentation: http://www.elasticsearch.org/guide/en/elasticsearch/reference/master/search-template.html
	public RenderSearchTemplate renderSearchTemplate(String id) {
		return new RenderSearchTemplate(server).setParts(id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-request-scroll.html
	public Scroll scroll(String scroll_id) {
		return new Scroll(server).setParts(scroll_id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-search.html
	public Search search(String index, String type) {
		return new Search(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-shards.html
	public SearchShards searchShards(String index, String type) {
		return new SearchShards(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/current/search-template.html
	public SearchTemplate searchTemplate(String index, String type) {
		return new SearchTemplate(server).setParts(index, type);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	public SnapshotCreate snapshotCreate(String repository, String snapshot) {
		return new SnapshotCreate(server).setParts(repository, snapshot);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	public SnapshotCreateRepository snapshotCreateRepository(String repository) {
		return new SnapshotCreateRepository(server).setParts(repository);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	public SnapshotDelete snapshotDelete(String repository, String snapshot) {
		return new SnapshotDelete(server).setParts(repository, snapshot);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	public SnapshotDeleteRepository snapshotDeleteRepository(String repository) {
		return new SnapshotDeleteRepository(server).setParts(repository);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	public SnapshotGet snapshotGet(String repository, String snapshot) {
		return new SnapshotGet(server).setParts(repository, snapshot);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	public SnapshotGetRepository snapshotGetRepository(String repository) {
		return new SnapshotGetRepository(server).setParts(repository);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	public SnapshotRestore snapshotRestore(String repository, String snapshot) {
		return new SnapshotRestore(server).setParts(repository, snapshot);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	public SnapshotStatus snapshotStatus(String repository, String snapshot) {
		return new SnapshotStatus(server).setParts(repository, snapshot);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	public SnapshotVerifyRepository snapshotVerifyRepository(String repository) {
		return new SnapshotVerifyRepository(server).setParts(repository);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-suggesters.html
	public Suggest suggest(String index) {
		return new Suggest(server).setParts(index);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
	public TasksCancel tasksCancel(String task_id) {
		return new TasksCancel(server).setParts(task_id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
	public TasksGet tasksGet(String task_id) {
		return new TasksGet(server).setParts(task_id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
	public TasksList tasksList() {
		return new TasksList(server).setParts();
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-termvectors.html
	public Termvectors termvectors(String index, String type, String id) {
		return new Termvectors(server).setParts(index, type, id);
	}

	//documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-update.html
	public Update update(String index, String type, String id) {
		return new Update(server).setParts(index, type, id);
	}

	//documentation: https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-update-by-query.html
	public UpdateByQuery updateByQuery(String index, String type) {
		return new UpdateByQuery(server).setParts(index, type);
	}

}
