package org.datasays.wes.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EsService {

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-bulk.html
	 * <p>
	 * param: string wait_for_active_shards: Sets the number of shard copies that must be active before proceeding with the bulk operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)
	 * param: enum refresh: If `true` then refresh the effected shards to make this operation visible to search, if `wait_for` then wait for a refresh to make this operation visible to search, if `false` (the default) then do nothing with refreshes.
	 * param: string routing: Specific routing value
	 * param: time timeout: Explicit operation timeout
	 * param: string type: Default document type for items which don't provide one
	 * param: list fields: Default comma-separated list of fields to return in the response for updates, can be overridden on each sub-request
	 * param: list _source: True or false to return the _source field or not, or default list of fields to return, can be overridden on each sub-request
	 * param: list _source_exclude: Default list of fields to exclude from the returned _source field, can be overridden on each sub-request
	 * param: list _source_include: Default list of fields to extract and return from the _source field, can be overridden on each sub-request
	 * param: string pipeline: The pipeline id to preprocess incoming documents with
	 * body*:The operation definition and data (action-data pairs), separated by newlines
	 *
	 * @param string index: Default index for items which don't provide one
	 * @param string type: Default document type for items which don't provide one
	 */
	@POST("_bulk")
	public Call<Object> bulk(@Body Object body);

	@POST("{index}/_bulk")
	public Call<Object> bulk(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_bulk")
	public Call<Object> bulk(@Path("index") String index, @Path("type") String type, @Body Object body);

	@PUT("_bulk2")
	public Call<Object> bulk2(@Body Object body);

	@PUT("{index}/_bulk2")
	public Call<Object> bulk2(@Path("index") String index, @Body Object body);

	@PUT("{index}/{type}/_bulk2")
	public Call<Object> bulk2(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-alias.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 *
	 * @param list name: A comma-separated list of alias names to return
	 */
	@GET("_cat/aliases")
	public Call<Object> cat_aliases();

	@GET("_cat/aliases/{name}")
	public Call<Object> cat_aliases(@Path("name") String name);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-allocation.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: enum bytes: The unit in which to display byte values
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 *
	 * @param list node_id: A comma-separated list of node IDs or names to limit the returned information
	 */
	@GET("_cat/allocation")
	public Call<Object> cat_allocation();

	@GET("_cat/allocation/{node_id}")
	public Call<Object> cat_allocation(@Path("node_id") String node_id);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-count.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names to limit the returned information
	 */
	@GET("_cat/count")
	public Call<Object> cat_count();

	@GET("_cat/count/{index}")
	public Call<Object> cat_count(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-fielddata.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: enum bytes: The unit in which to display byte values
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * param: list fields: A comma-separated list of fields to return in the output
	 * body:null
	 *
	 * @param list fields: A comma-separated list of fields to return the fielddata size
	 */
	@GET("_cat/fielddata")
	public Call<Object> cat_fielddata();

	@GET("_cat/fielddata/{fields}")
	public Call<Object> cat_fielddata(@Path("fields") String fields);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-health.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean ts: Set to false to disable timestamping
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 */
	@GET("_cat/health")
	public Call<Object> cat_health();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat.html
	 * <p>
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * body:null
	 */
	@GET("_cat")
	public Call<Object> cat_help();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-indices.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: enum bytes: The unit in which to display byte values
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: enum health: A health status ("green", "yellow", or "red" to filter only indices matching the specified health status
	 * param: boolean help: Return help information
	 * param: boolean pri: Set to true to return stats only for primary shards
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names to limit the returned information
	 */
	@GET("_cat/indices")
	public Call<Object> cat_indices();

	@GET("_cat/indices/{index}")
	public Call<Object> cat_indices(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-master.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 */
	@GET("_cat/master")
	public Call<Object> cat_master();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-nodeattrs.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 */
	@GET("_cat/nodeattrs")
	public Call<Object> cat_nodeattrs();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-nodes.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean full_id: Return the full node ID instead of the shortened version (default: false)
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 */
	@GET("_cat/nodes")
	public Call<Object> cat_nodes();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-pending-tasks.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 */
	@GET("_cat/pending_tasks")
	public Call<Object> cat_pending_tasks();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-plugins.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 */
	@GET("_cat/plugins")
	public Call<Object> cat_plugins();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-recovery.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: enum bytes: The unit in which to display byte values
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names to limit the returned information
	 */
	@GET("_cat/recovery")
	public Call<Object> cat_recovery();

	@GET("_cat/recovery/{index}")
	public Call<Object> cat_recovery(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-repositories.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean local: Return local information, do not retrieve the state from master node
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 */
	@GET("_cat/repositories")
	public Call<Object> cat_repositories();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-segments.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names to limit the returned information
	 */
	@GET("_cat/segments")
	public Call<Object> cat_segments();

	@GET("_cat/segments/{index}")
	public Call<Object> cat_segments(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-shards.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names to limit the returned information
	 */
	@GET("_cat/shards")
	public Call<Object> cat_shards();

	@GET("_cat/shards/{index}")
	public Call<Object> cat_shards(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-snapshots.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean ignore_unavailable: Set to true to ignore unavailable snapshots
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 *
	 * @param list repository: Name of repository from which to fetch the snapshot information
	 */
	@GET("_cat/snapshots")
	public Call<Object> cat_snapshots();

	@GET("_cat/snapshots/{repository}")
	public Call<Object> cat_snapshots(@Path("repository") String repository);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: list node_id: A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes
	 * param: list actions: A comma-separated list of actions that should be returned. Leave empty to return all.
	 * param: boolean detailed: Return detailed task information (default: false)
	 * param: string parent_node: Return tasks with specified parent node.
	 * param: number parent_task: Return tasks with specified parent task id. Set to -1 to return all.
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 */
	@GET("_cat/tasks")
	public Call<Object> cat_tasks();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-templates.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 *
	 * @param string name: A pattern that returned template names must match
	 */
	@GET("_cat/templates")
	public Call<Object> cat_templates();

	@GET("_cat/templates/{name}")
	public Call<Object> cat_templates(@Path("name") String name);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-thread-pool.html
	 * <p>
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 * param: enum size: The multiplier in which to display values
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: list h: Comma-separated list of column names to display
	 * param: boolean help: Return help information
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 * param: boolean v: Verbose mode. Display column headers
	 * body:null
	 *
	 * @param list thread_pool_patterns: A comma-separated list of regular-expressions to filter the thread pools in the output
	 */
	@GET("_cat/thread_pool")
	public Call<Object> cat_thread_pool();

	@GET("_cat/thread_pool/{thread_pool_patterns}")
	public Call<Object> cat_thread_pool(@Path("thread_pool_patterns") String thread_pool_patterns);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-request-scroll.html
	 * <p>
	 * body:A comma-separated list of scroll IDs to clear if none was specified via the scroll_id parameter
	 *
	 * @param list scroll_id: A comma-separated list of scroll IDs to clear
	 */
	@DELETE("_search/scroll/{scroll_id}")
	public Call<Object> clear_scroll(@Path("scroll_id") String scroll_id);

	@DELETE("_search/scroll")
	public Call<Object> clear_scroll();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-allocation-explain.html
	 * <p>
	 * param: boolean include_yes_decisions: Return 'YES' decisions in explanation (default: false)
	 * param: boolean include_disk_info: Return information about disk usage and shard sizes (default: false)
	 * body:The index, shard, and primary flag to explain. Empty means 'explain the first unassigned shard'
	 */
	@GET("_cluster/allocation/explain")
	public Call<Object> cluster_allocation_explain();

	@POST("_cluster/allocation/explain")
	public Call<Object> cluster_allocation_explain(@Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-update-settings.html
	 * <p>
	 * param: boolean flat_settings: Return settings in flat format (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: time timeout: Explicit operation timeout
	 * param: boolean include_defaults: Whether to return all default clusters setting.
	 * body:null
	 */
	@GET("_cluster/settings")
	public Call<Object> cluster_get_settings();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-health.html
	 * <p>
	 * param: enum level: Specify the level of detail for returned information
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: time timeout: Explicit operation timeout
	 * param: string wait_for_active_shards: Wait until the specified number of shards is active
	 * param: string wait_for_nodes: Wait until the specified number of nodes is available
	 * param: enum wait_for_events: Wait until all currently queued events with the given priority are processed
	 * param: boolean wait_for_no_relocating_shards: Whether to wait until there are no relocating shards in the cluster
	 * param: enum wait_for_status: Wait until cluster is in a specific state
	 * body:null
	 *
	 * @param list index: Limit the information returned to a specific index
	 */
	@GET("_cluster/health")
	public Call<Object> cluster_health();

	@GET("_cluster/health/{index}")
	public Call<Object> cluster_health(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-pending.html
	 * <p>
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Specify timeout for connection to master
	 * body:null
	 */
	@GET("_cluster/pending_tasks")
	public Call<Object> cluster_pending_tasks();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-update-settings.html
	 * <p>
	 * param: boolean flat_settings: Return settings in flat format (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: time timeout: Explicit operation timeout
	 * body:The settings to be updated. Can be either `transient` or `persistent` (survives cluster restart).
	 */
	@PUT("_cluster/settings")
	public Call<Object> cluster_put_settings(@Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-reroute.html
	 * <p>
	 * param: boolean dry_run: Simulate the operation only and return the resulting state
	 * param: boolean explain: Return an explanation of why the commands can or cannot be executed
	 * param: boolean retry_failed: Retries allocation of shards that are blocked due to too many subsequent allocation failures
	 * param: list metric: Limit the information returned to the specified metrics. Defaults to all but metadata
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: time timeout: Explicit operation timeout
	 * body:The definition of `commands` to perform (`move`, `cancel`, `allocate`)
	 */
	@POST("_cluster/reroute")
	public Call<Object> cluster_reroute(@Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-state.html
	 * <p>
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: time master_timeout: Specify timeout for connection to master
	 * param: boolean flat_settings: Return settings in flat format (default: false)
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 * @param list metric: Limit the information returned to the specified metrics
	 */
	@GET("_cluster/state")
	public Call<Object> cluster_state();

	@GET("_cluster/state/{metric}")
	public Call<Object> cluster_state(@Path("metric") String metric);

	@GET("_cluster/state/{metric}/{index}")
	public Call<Object> cluster_state(@Path("index") String index, @Path("metric") String metric);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-stats.html
	 * <p>
	 * param: boolean flat_settings: Return settings in flat format (default: false)
	 * param: boolean human: Whether to return time and byte values in human-readable format.
	 * param: time timeout: Explicit operation timeout
	 * body:null
	 *
	 * @param list node_id: A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes
	 */
	@GET("_cluster/stats")
	public Call<Object> cluster_stats();

	@GET("_cluster/stats/nodes/{node_id}")
	public Call<Object> cluster_stats(@Path("node_id") String node_id);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-count.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: number min_score: Include only documents with a specific `_score` value in the result
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: string routing: Specific routing value
	 * param: string q: Query in the Lucene query string syntax
	 * param: string analyzer: The analyzer to use for the query string
	 * param: boolean analyze_wildcard: Specify whether wildcard and prefix queries should be analyzed (default: false)
	 * param: enum default_operator: The default operator for query string query (AND or OR)
	 * param: string df: The field to use as default where no field prefix is given in the query string
	 * param: boolean lenient: Specify whether format-based query failures (such as providing text to a numeric field) should be ignored
	 * body:A query to restrict the results specified with the Query DSL (optional)
	 *
	 * @param list index: A comma-separated list of indices to restrict the results
	 * @param list type: A comma-separated list of types to restrict the results
	 */
	@POST("_count")
	public Call<Object> count(@Body Object body);

	@POST("{index}/_count")
	public Call<Object> count(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_count")
	public Call<Object> count(@Path("index") String index, @Path("type") String type, @Body Object body);

	@GET("_count")
	public Call<Object> count();

	@GET("{index}/_count")
	public Call<Object> count(@Path("index") String index);

	@GET("{index}/{type}/_count")
	public Call<Object> count(@Path("index") String index, @Path("type") String type);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-percolate.html
	 * <p>
	 * param: list routing: A comma-separated list of specific routing values
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: string percolate_index: The index to count percolate the document into. Defaults to index.
	 * param: string percolate_type: The type to count percolate document into. Defaults to type.
	 * param: number version: Explicit version number for concurrency control
	 * param: enum version_type: Specific version type
	 * body:The count percolator request definition using the percolate DSL
	 *
	 * @param string index: The index of the document being count percolated.
	 * @param string type: The type of the document being count percolated.
	 * @param string id: Substitute the document in the request body with a document that is known by the specified id. On top of the id, the index and type parameter will be used to retrieve the document from within the cluster.
	 */
	@GET("{index}/{type}/_percolate/count")
	public Call<Object> count_percolate(@Path("index") String index, @Path("type") String type);

	@GET("{index}/{type}/{id}/_percolate/count")
	public Call<Object> count_percolate(@Path("index") String index, @Path("type") String type, @Path("id") String id);

	@POST("{index}/{type}/_percolate/count")
	public Call<Object> count_percolate(@Path("index") String index, @Path("type") String type, @Body Object body);

	@POST("{index}/{type}/{id}/_percolate/count")
	public Call<Object> count_percolate(@Path("index") String index, @Path("type") String type, @Path("id") String id, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-index_.html
	 * <p>
	 * param: string wait_for_active_shards: Sets the number of shard copies that must be active before proceeding with the index operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)
	 * param: string parent: ID of the parent document
	 * param: enum refresh: If `true` then refresh the affected shards to make this operation visible to search, if `wait_for` then wait for a refresh to make this operation visible to search, if `false` (the default) then do nothing with refreshes.
	 * param: string routing: Specific routing value
	 * param: time timeout: Explicit operation timeout
	 * param: time timestamp: Explicit timestamp for the document
	 * param: time ttl: Expiration time for the document
	 * param: number version: Explicit version number for concurrency control
	 * param: enum version_type: Specific version type
	 * param: string pipeline: The pipeline id to preprocess incoming documents with
	 * body*:The document
	 *
	 * @param string id: Document ID
	 * @param string index: The name of the index
	 * @param string type: The type of the document
	 */
	@PUT("{index}/{type}/{id}/_create")
	public Call<Object> create(@Path("index") String index, @Path("type") String type, @Path("id") String id, @Body Object body);

	@POST("{index}/{type}/{id}/_create2")
	public Call<Object> create2(@Path("index") String index, @Path("type") String type, @Path("id") String id, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-delete.html
	 * <p>
	 * param: string wait_for_active_shards: Sets the number of shard copies that must be active before proceeding with the delete operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)
	 * param: string parent: ID of parent document
	 * param: enum refresh: If `true` then refresh the effected shards to make this operation visible to search, if `wait_for` then wait for a refresh to make this operation visible to search, if `false` (the default) then do nothing with refreshes.
	 * param: string routing: Specific routing value
	 * param: time timeout: Explicit operation timeout
	 * param: number version: Explicit version number for concurrency control
	 * param: enum version_type: Specific version type
	 * body:null
	 *
	 * @param string id: The document ID
	 * @param string index: The name of the index
	 * @param string type: The type of the document
	 */
	@DELETE("{index}/{type}/{id}")
	public Call<Object> delete(@Path("index") String index, @Path("type") String type, @Path("id") String id);

	/**
	 * documentation: https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-delete-by-query.html
	 * <p>
	 * param: string analyzer: The analyzer to use for the query string
	 * param: boolean analyze_wildcard: Specify whether wildcard and prefix queries should be analyzed (default: false)
	 * param: enum default_operator: The default operator for query string query (AND or OR)
	 * param: string df: The field to use as default where no field prefix is given in the query string
	 * param: number from: Starting offset (default: 0)
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum conflicts: What to do when the delete-by-query hits version conflicts?
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean lenient: Specify whether format-based query failures (such as providing text to a numeric field) should be ignored
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: string q: Query in the Lucene query string syntax
	 * param: list routing: A comma-separated list of specific routing values
	 * param: time scroll: Specify how long a consistent view of the index should be maintained for scrolled search
	 * param: enum search_type: Search operation type
	 * param: time search_timeout: Explicit timeout for each search request. Defaults to no timeout.
	 * param: number size: Number of hits to return (default: 10)
	 * param: list sort: A comma-separated list of <field>:<direction> pairs
	 * param: list _source: True or false to return the _source field or not, or a list of fields to return
	 * param: list _source_exclude: A list of fields to exclude from the returned _source field
	 * param: list _source_include: A list of fields to extract and return from the _source field
	 * param: number terminate_after: The maximum number of documents to collect for each shard, upon reaching which the query execution will terminate early.
	 * param: list stats: Specific 'tag' of the request for logging and statistical purposes
	 * param: boolean version: Specify whether to return document version as part of a hit
	 * param: boolean request_cache: Specify if request cache should be used for this request or not, defaults to index level setting
	 * param: boolean refresh: Should the effected indexes be refreshed?
	 * param: time timeout: Time each individual bulk request should wait for shards that are unavailable.
	 * param: string wait_for_active_shards: Sets the number of shard copies that must be active before proceeding with the delete by query operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)
	 * param: number scroll_size: Size on the scroll request powering the update_by_query
	 * param: boolean wait_for_completion: Should the request should block until the delete-by-query is complete.
	 * param: number requests_per_second: The throttle for this request in sub-requests per second. -1 means no throttle.
	 * param: integer slices: The number of slices this task should be divided into. Defaults to 1 meaning the task isn't sliced into subtasks.
	 * body*:The search definition using the Query DSL
	 *
	 * @param list index: A comma-separated list of index names to search; use `_all` or empty string to perform the operation on all indices
	 * @param list type: A comma-separated list of document types to search; leave empty to perform the operation on all types
	 */
	@POST("{index}/_delete_by_query")
	public Call<Object> delete_by_query(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_delete_by_query")
	public Call<Object> delete_by_query(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-scripting.html
	 * <p>
	 * body:null
	 *
	 * @param string id: Script ID
	 * @param string lang: Script language
	 */
	@DELETE("_scripts/{lang}/{id}")
	public Call<Object> delete_script(@Path("id") String id, @Path("lang") String lang);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-template.html
	 * <p>
	 * body:null
	 *
	 * @param string id: Template ID
	 */
	@DELETE("_search/template/{id}")
	public Call<Object> delete_template(@Path("id") String id);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-get.html
	 * <p>
	 * param: string parent: The ID of the parent document
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: boolean realtime: Specify whether to perform the operation in realtime or search mode
	 * param: boolean refresh: Refresh the shard containing the document before performing the operation
	 * param: string routing: Specific routing value
	 * body:null
	 *
	 * @param string id: The document ID
	 * @param string index: The name of the index
	 * @param string type: The type of the document (use `_all` to fetch the first document matching the ID across all types)
	 */
	@HEAD("{index}/{type}/{id}")
	public Call<Void> exists(@Path("index") String index, @Path("type") String type, @Path("id") String id);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-explain.html
	 * <p>
	 * param: boolean analyze_wildcard: Specify whether wildcards and prefix queries in the query string query should be analyzed (default: false)
	 * param: string analyzer: The analyzer for the query string query
	 * param: enum default_operator: The default operator for query string query (AND or OR)
	 * param: string df: The default field for query string query (default: _all)
	 * param: list stored_fields: A comma-separated list of stored fields to return in the response
	 * param: boolean lenient: Specify whether format-based query failures (such as providing text to a numeric field) should be ignored
	 * param: string parent: The ID of the parent document
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: string q: Query in the Lucene query string syntax
	 * param: string routing: Specific routing value
	 * param: list _source: True or false to return the _source field or not, or a list of fields to return
	 * param: list _source_exclude: A list of fields to exclude from the returned _source field
	 * param: list _source_include: A list of fields to extract and return from the _source field
	 * body:The query definition using the Query DSL
	 *
	 * @param string id: The document ID
	 * @param string index: The name of the index
	 * @param string type: The type of the document
	 */
	@GET("{index}/{type}/{id}/_explain")
	public Call<Object> explain(@Path("index") String index, @Path("type") String type, @Path("id") String id);

	@POST("{index}/{type}/{id}/_explain")
	public Call<Object> explain(@Path("index") String index, @Path("type") String type, @Path("id") String id, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-field-stats.html
	 * <p>
	 * param: list fields: A comma-separated list of fields for to get field statistics for (min value, max value, and more)
	 * param: enum level: Defines if field stats should be returned on a per index level or on a cluster wide level
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * body:Field json objects containing the name and optionally a range to filter out indices result, that have results outside the defined bounds
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 */
	@GET("_field_stats")
	public Call<Object> field_stats();

	@GET("{index}/_field_stats")
	public Call<Object> field_stats(@Path("index") String index);

	@POST("_field_stats")
	public Call<Object> field_stats(@Body Object body);

	@POST("{index}/_field_stats")
	public Call<Object> field_stats(@Path("index") String index, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-get.html
	 * <p>
	 * param: list stored_fields: A comma-separated list of stored fields to return in the response
	 * param: string parent: The ID of the parent document
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: boolean realtime: Specify whether to perform the operation in realtime or search mode
	 * param: boolean refresh: Refresh the shard containing the document before performing the operation
	 * param: string routing: Specific routing value
	 * param: list _source: True or false to return the _source field or not, or a list of fields to return
	 * param: list _source_exclude: A list of fields to exclude from the returned _source field
	 * param: list _source_include: A list of fields to extract and return from the _source field
	 * param: number version: Explicit version number for concurrency control
	 * param: enum version_type: Specific version type
	 * body:null
	 *
	 * @param string id: The document ID
	 * @param string index: The name of the index
	 * @param string type: The type of the document (use `_all` to fetch the first document matching the ID across all types)
	 */
	@GET("{index}/{type}/{id}")
	public Call<Object> get(@Path("index") String index, @Path("type") String type, @Path("id") String id);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-scripting.html
	 * <p>
	 * body:null
	 *
	 * @param string id: Script ID
	 * @param string lang: Script language
	 */
	@GET("_scripts/{lang}/{id}")
	public Call<Object> get_script(@Path("id") String id, @Path("lang") String lang);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-get.html
	 * <p>
	 * param: string parent: The ID of the parent document
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: boolean realtime: Specify whether to perform the operation in realtime or search mode
	 * param: boolean refresh: Refresh the shard containing the document before performing the operation
	 * param: string routing: Specific routing value
	 * param: list _source: True or false to return the _source field or not, or a list of fields to return
	 * param: list _source_exclude: A list of fields to exclude from the returned _source field
	 * param: list _source_include: A list of fields to extract and return from the _source field
	 * param: number version: Explicit version number for concurrency control
	 * param: enum version_type: Specific version type
	 * body:null
	 *
	 * @param string id: The document ID
	 * @param string index: The name of the index
	 * @param string type: The type of the document; use `_all` to fetch the first document matching the ID across all types
	 */
	@GET("{index}/{type}/{id}/_source")
	public Call<Object> get_source(@Path("index") String index, @Path("type") String type, @Path("id") String id);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-template.html
	 * <p>
	 * body:null
	 *
	 * @param string id: Template ID
	 */
	@GET("_search/template/{id}")
	public Call<Object> get_template(@Path("id") String id);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-index_.html
	 * <p>
	 * param: string wait_for_active_shards: Sets the number of shard copies that must be active before proceeding with the index operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)
	 * param: enum op_type: Explicit operation type
	 * param: string parent: ID of the parent document
	 * param: enum refresh: If `true` then refresh the affected shards to make this operation visible to search, if `wait_for` then wait for a refresh to make this operation visible to search, if `false` (the default) then do nothing with refreshes.
	 * param: string routing: Specific routing value
	 * param: time timeout: Explicit operation timeout
	 * param: time timestamp: Explicit timestamp for the document
	 * param: time ttl: Expiration time for the document
	 * param: number version: Explicit version number for concurrency control
	 * param: enum version_type: Specific version type
	 * param: string pipeline: The pipeline id to preprocess incoming documents with
	 * body*:The document
	 *
	 * @param string id: Document ID
	 * @param string index: The name of the index
	 * @param string type: The type of the document
	 */
	@POST("{index}/{type}")
	public Call<Object> index(@Path("index") String index, @Path("type") String type, @Body Object body);

	@POST("{index}/{type}/{id}")
	public Call<Object> index(@Path("index") String index, @Path("type") String type, @Path("id") String id, @Body Object body);

	@PUT("{index2}/{type}")
	public Call<Object> index2(@Path("index2") String index2, @Path("type") String type, @Body Object body);

	@PUT("{index2}/{type}/{id}")
	public Call<Object> index2(@Path("id") String id, @Path("index2") String index2, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-analyze.html
	 * <p>
	 * param: string index: The name of the index to scope the operation
	 * param: boolean prefer_local: With `true`, specify that a local shard should be used if available, with `false`, use a random shard (default: true)
	 * param: enum format: Format of the output
	 * body:Define analyzer/tokenizer parameters and the text on which the analysis should be performed
	 *
	 * @param string index: The name of the index to scope the operation
	 */
	@GET("_analyze")
	public Call<Object> indices_analyze();

	@GET("{index}/_analyze")
	public Call<Object> indices_analyze(@Path("index") String index);

	@POST("_analyze")
	public Call<Object> indices_analyze(@Body Object body);

	@POST("{index}/_analyze")
	public Call<Object> indices_analyze(@Path("index") String index, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-clearcache.html
	 * <p>
	 * param: boolean field_data: Clear field data
	 * param: boolean fielddata: Clear field data
	 * param: list fields: A comma-separated list of fields to clear when using the `field_data` parameter (default: all)
	 * param: boolean query: Clear query caches
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: list index: A comma-separated list of index name to limit the operation
	 * param: boolean recycler: Clear the recycler cache
	 * param: boolean request: Clear request cache
	 * body:null
	 *
	 * @param list index: A comma-separated list of index name to limit the operation
	 */
	@POST("_cache/clear")
	public Call<Object> indices_clear_cache(@Body Object body);

	@POST("{index}/_cache/clear")
	public Call<Object> indices_clear_cache(@Path("index") String index, @Body Object body);

	@GET("_cache/clear")
	public Call<Object> indices_clear_cache();

	@GET("{index}/_cache/clear")
	public Call<Object> indices_clear_cache(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-open-close.html
	 * <p>
	 * param: time timeout: Explicit operation timeout
	 * param: time master_timeout: Specify timeout for connection to master
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * body:null
	 *
	 * @param list index: A comma separated list of indices to close
	 */
	@POST("{index}/_close")
	public Call<Object> indices_close(@Path("index") String index, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-create-index.html
	 * <p>
	 * param: string wait_for_active_shards: Set the number of active shards to wait for before the operation returns.
	 * param: time timeout: Explicit operation timeout
	 * param: time master_timeout: Specify timeout for connection to master
	 * param: boolean update_all_types: Whether to update the mapping for all fields with the same name across all types or not
	 * body:The configuration for the index (`settings` and `mappings`)
	 *
	 * @param string index: The name of the index
	 */
	@PUT("{index}")
	public Call<Object> indices_create(@Path("index") String index, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-delete-index.html
	 * <p>
	 * param: time timeout: Explicit operation timeout
	 * param: time master_timeout: Specify timeout for connection to master
	 * body:null
	 *
	 * @param list index: A comma-separated list of indices to delete; use `_all` or `*` string to delete all indices
	 */
	@DELETE("{index}")
	public Call<Object> indices_delete(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
	 * <p>
	 * param: time timeout: Explicit timestamp for the document
	 * param: time master_timeout: Specify timeout for connection to master
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names (supports wildcards); use `_all` for all indices
	 * @param list name: A comma-separated list of aliases to delete (supports wildcards); use `_all` to delete all aliases for the specified indices.
	 */
	@DELETE("{index}/_alias/{name}")
	public Call<Object> indices_delete_alias(@Path("index") String index, @Path("name") String name);

	@DELETE("{index}/_aliases/{name}")
	public Call<Object> indices_delete_alias2(@Path("index") String index, @Path("name") String name);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
	 * <p>
	 * param: time timeout: Explicit operation timeout
	 * param: time master_timeout: Specify timeout for connection to master
	 * body:null
	 *
	 * @param string name: The name of the template
	 */
	@DELETE("_template/{name}")
	public Call<Object> indices_delete_template(@Path("name") String name);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-exists.html
	 * <p>
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * body:null
	 *
	 * @param list index: A comma-separated list of indices to check
	 */
	@HEAD("{index}")
	public Call<Void> indices_exists(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names to filter aliases
	 * @param list name: A comma-separated list of alias names to return
	 */
	@HEAD("_alias/{name}")
	public Call<Void> indices_exists_alias(@Path("name") String name);

	@HEAD("{index}/_alias/{name}")
	public Call<Void> indices_exists_alias(@Path("index") String index, @Path("name") String name);

	@HEAD("{index}/_alias")
	public Call<Void> indices_exists_alias2(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * body:null
	 *
	 * @param string name: The name of the template
	 */
	@HEAD("_template/{name}")
	public Call<Void> indices_exists_template(@Path("name") String name);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-types-exists.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` to check the types across all indices
	 * @param list type: A comma-separated list of document types to check
	 */
	@HEAD("{index}/_mapping/{type}")
	public Call<Void> indices_exists_type(@Path("index") String index, @Path("type") String type);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-flush.html
	 * <p>
	 * param: boolean force: Whether a flush should be forced even if it is not necessarily needed ie. if no changes will be committed to the index. This is useful if transaction log IDs should be incremented even if no uncommitted changes are present. (This setting can be considered as internal)
	 * param: boolean wait_if_ongoing: If set to true the flush operation will block until the flush can be executed if another flush operation is already executing. The default is true. If set to false the flush will be skipped iff if another flush operation is already running.
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string for all indices
	 */
	@POST("_flush")
	public Call<Object> indices_flush(@Body Object body);

	@POST("{index}/_flush")
	public Call<Object> indices_flush(@Path("index") String index, @Body Object body);

	@GET("_flush")
	public Call<Object> indices_flush();

	@GET("{index}/_flush")
	public Call<Object> indices_flush(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-synced-flush.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string for all indices
	 */
	@POST("_flush/synced")
	public Call<Object> indices_flush_synced(@Body Object body);

	@POST("{index}/_flush/synced")
	public Call<Object> indices_flush_synced(@Path("index") String index, @Body Object body);

	@GET("_flush/synced")
	public Call<Object> indices_flush_synced();

	@GET("{index}/_flush/synced")
	public Call<Object> indices_flush_synced(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-forcemerge.html
	 * <p>
	 * param: boolean flush: Specify whether the index should be flushed after performing the operation (default: true)
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: number max_num_segments: The number of segments the index should be merged into (default: dynamic)
	 * param: boolean only_expunge_deletes: Specify whether the operation should only expunge deleted documents
	 * param: null operation_threading: TODO: ?
	 * param: boolean wait_for_merge: Specify whether the request should block until the merge process is finished (default: true)
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 */
	@POST("_forcemerge")
	public Call<Object> indices_forcemerge(@Body Object body);

	@POST("{index}/_forcemerge")
	public Call<Object> indices_forcemerge(@Path("index") String index, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-index.html
	 * <p>
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: boolean ignore_unavailable: Ignore unavailable indexes (default: false)
	 * param: boolean allow_no_indices: Ignore if a wildcard expression resolves to no concrete indices (default: false)
	 * param: enum expand_wildcards: Whether wildcard expressions should get expanded to open or closed indices (default: open)
	 * param: boolean flat_settings: Return settings in flat format (default: false)
	 * param: boolean human: Whether to return version and creation date values in human-readable format.
	 * param: boolean include_defaults: Whether to return all default setting for each of the indices.
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names
	 * @param list feature: A comma-separated list of features
	 */
	@GET("{index}")
	public Call<Object> indices_get(@Path("index") String index);

	@GET("{index}/{feature}")
	public Call<Object> indices_get(@Path("index") String index, @Path("feature") String feature);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names to filter aliases
	 * @param list name: A comma-separated list of alias names to return
	 */
	@GET("_alias")
	public Call<Object> indices_get_alias();

	@GET("_alias/{name}")
	public Call<Object> indices_get_alias(@Path("name") String name);

	@GET("{index}/_alias/{name}")
	public Call<Object> indices_get_alias(@Path("index") String index, @Path("name") String name);

	@GET("{index}/_alias")
	public Call<Object> indices_get_alias2(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-field-mapping.html
	 * <p>
	 * param: boolean include_defaults: Whether the default mapping values should be returned as well
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names
	 * @param list type: A comma-separated list of document types
	 * @param list fields: A comma-separated list of fields
	 */
	@GET("_mapping/field/{fields}")
	public Call<Object> indices_get_field_mapping(@Path("fields") String fields);

	@GET("{index}/_mapping/field/{fields}")
	public Call<Object> indices_get_field_mapping(@Path("index") String index, @Path("fields") String fields);

	@GET("_mapping/{type}/field/{fields}")
	public Call<Object> indices_get_field_mapping2(@Path("type") String type, @Path("fields") String fields);

	@GET("{index}/_mapping/{type}/field/{fields}")
	public Call<Object> indices_get_field_mapping(@Path("index") String index, @Path("type") String type, @Path("fields") String fields);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-mapping.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names
	 * @param list type: A comma-separated list of document types
	 */
	@GET("_mapping")
	public Call<Object> indices_get_mapping();

	@GET("{index}/_mapping")
	public Call<Object> indices_get_mapping(@Path("index") String index);

	@GET("_mapping/{type}")
	public Call<Object> indices_get_mapping2(@Path("type") String type);

	@GET("{index}/_mapping/{type}")
	public Call<Object> indices_get_mapping(@Path("index") String index, @Path("type") String type);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-settings.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean flat_settings: Return settings in flat format (default: false)
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: boolean human: Whether to return version and creation date values in human-readable format.
	 * param: boolean include_defaults: Whether to return all default setting for each of the indices.
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 * @param list name: The name of the settings that should be included
	 */
	@GET("_settings")
	public Call<Object> indices_get_settings();

	@GET("{index}/_settings")
	public Call<Object> indices_get_settings(@Path("index") String index);

	@GET("{index}/_settings/{name}")
	public Call<Object> indices_get_settings(@Path("index") String index, @Path("name") String name);

	@GET("_settings/{name}")
	public Call<Object> indices_get_settings2(@Path("name") String name);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
	 * <p>
	 * param: boolean flat_settings: Return settings in flat format (default: false)
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * body:null
	 *
	 * @param list name: The comma separated names of the index templates
	 */
	@GET("_template")
	public Call<Object> indices_get_template();

	@GET("_template/{name}")
	public Call<Object> indices_get_template(@Path("name") String name);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-upgrade.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean human: Whether to return time and byte values in human-readable format.
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 */
	@GET("_upgrade")
	public Call<Object> indices_get_upgrade();

	@GET("{index}/_upgrade")
	public Call<Object> indices_get_upgrade(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-open-close.html
	 * <p>
	 * param: time timeout: Explicit operation timeout
	 * param: time master_timeout: Specify timeout for connection to master
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * body:null
	 *
	 * @param list index: A comma separated list of indices to open
	 */
	@POST("{index}/_open")
	public Call<Object> indices_open(@Path("index") String index, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
	 * <p>
	 * param: time timeout: Explicit timestamp for the document
	 * param: time master_timeout: Specify timeout for connection to master
	 * body:The settings for the alias, such as `routing` or `filter`
	 *
	 * @param list   index: A comma-separated list of index names the alias should point to (supports wildcards); use `_all` to perform the operation on all indices.
	 * @param string name: The name of the alias to be created or updated
	 */
	@PUT("{index}/_alias/{name}")
	public Call<Object> indices_put_alias(@Path("index") String index, @Path("name") String name, @Body Object body);

	@PUT("{index}/_aliases/{name}")
	public Call<Object> indices_put_alias2(@Path("index") String index, @Path("name") String name, @Body Object body);

	@POST("{index}/_alias/{name}")
	public Call<Object> indices_put_alias3(@Path("index") String index, @Path("name") String name, @Body Object body);

	@POST("{index}/_aliases/{name}")
	public Call<Object> indices_put_alias4(@Path("index") String index, @Path("name") String name, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-put-mapping.html
	 * <p>
	 * param: time timeout: Explicit operation timeout
	 * param: time master_timeout: Specify timeout for connection to master
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean update_all_types: Whether to update the mapping for all fields with the same name across all types or not
	 * body*:The mapping definition
	 *
	 * @param list   index: A comma-separated list of index names the mapping should be added to (supports wildcards); use `_all` or omit to add the mapping on all indices.
	 * @param string type: The name of the document type
	 */
	@PUT("{index}/{type}/_mapping")
	public Call<Object> indices_put_mapping(@Path("index") String index, @Path("type") String type, @Body Object body);

	@PUT("{index}/_mapping/{type}")
	public Call<Object> indices_put_mapping2(@Path("index") String index, @Path("type") String type, @Body Object body);

	@PUT("_mapping/{type}")
	public Call<Object> indices_put_mapping(@Path("type") String type, @Body Object body);

	@PUT("{index}/{type}/_mappings")
	public Call<Object> indices_put_mapping3(@Path("index") String index, @Path("type") String type, @Body Object body);

	@PUT("{index}/_mappings/{type}")
	public Call<Object> indices_put_mapping4(@Path("index") String index, @Path("type") String type, @Body Object body);

	@PUT("_mappings/{type}")
	public Call<Object> indices_put_mapping2(@Path("type") String type, @Body Object body);

	@POST("{index}/{type}/_mapping")
	public Call<Object> indices_put_mapping5(@Path("index") String index, @Path("type") String type, @Body Object body);

	@POST("{index}/_mapping/{type}")
	public Call<Object> indices_put_mapping6(@Path("index") String index, @Path("type") String type, @Body Object body);

	@POST("_mapping/{type}")
	public Call<Object> indices_put_mapping3(@Path("type") String type, @Body Object body);

	@POST("{index}/{type}/_mappings")
	public Call<Object> indices_put_mapping7(@Path("index") String index, @Path("type") String type, @Body Object body);

	@POST("{index}/_mappings/{type}")
	public Call<Object> indices_put_mapping8(@Path("index") String index, @Path("type") String type, @Body Object body);

	@POST("_mappings/{type}")
	public Call<Object> indices_put_mapping4(@Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-update-settings.html
	 * <p>
	 * param: time master_timeout: Specify timeout for connection to master
	 * param: boolean preserve_existing: Whether to update existing settings. If set to `true` existing settings on an index remain unchanged, the default is `false`
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean flat_settings: Return settings in flat format (default: false)
	 * body*:The index settings to be updated
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 */
	@PUT("_settings")
	public Call<Object> indices_put_settings(@Body Object body);

	@PUT("{index}/_settings")
	public Call<Object> indices_put_settings(@Path("index") String index, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
	 * <p>
	 * param: number order: The order for this template when merging multiple matching ones (higher numbers are merged later, overriding the lower numbers)
	 * param: boolean create: Whether the index template should only be added if new or can also replace an existing one
	 * param: time timeout: Explicit operation timeout
	 * param: time master_timeout: Specify timeout for connection to master
	 * param: boolean flat_settings: Return settings in flat format (default: false)
	 * body*:The template definition
	 *
	 * @param string name: The name of the template
	 */
	@PUT("_template/{name}")
	public Call<Object> indices_put_template(@Path("name") String name, @Body Object body);

	@POST("_template/{name}")
	public Call<Object> indices_put_template2(@Path("name") String name, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-recovery.html
	 * <p>
	 * param: boolean detailed: Whether to display detailed information about shard recovery
	 * param: boolean active_only: Display only those recoveries that are currently on-going
	 * param: boolean human: Whether to return time and byte values in human-readable format.
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 */
	@GET("_recovery")
	public Call<Object> indices_recovery();

	@GET("{index}/_recovery")
	public Call<Object> indices_recovery(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-refresh.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean force: Force a refresh even if not required
	 * param: null operation_threading: TODO: ?
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 */
	@POST("_refresh")
	public Call<Object> indices_refresh(@Body Object body);

	@POST("{index}/_refresh")
	public Call<Object> indices_refresh(@Path("index") String index, @Body Object body);

	@GET("_refresh")
	public Call<Object> indices_refresh();

	@GET("{index}/_refresh")
	public Call<Object> indices_refresh(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-rollover-index.html
	 * <p>
	 * param: time timeout: Explicit operation timeout
	 * param: boolean dry_run: If set to true the rollover action will only be validated but not actually performed even if a condition matches. The default is false
	 * param: time master_timeout: Specify timeout for connection to master
	 * param: string wait_for_active_shards: Set the number of active shards to wait for on the newly created rollover index before the operation returns.
	 * body:The conditions that needs to be met for executing rollover
	 *
	 * @param string alias: The name of the alias to rollover
	 * @param string new_index: The name of the rollover index
	 */
	@POST("{alias}/_rollover")
	public Call<Object> indices_rollover(@Path("alias") String alias, @Body Object body);

	@POST("{alias}/_rollover/{new_index}")
	public Call<Object> indices_rollover(@Path("alias") String alias, @Path("new_index") String new_index, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-segments.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean human: Whether to return time and byte values in human-readable format.
	 * param: null operation_threading: TODO: ?
	 * param: boolean verbose: Includes detailed memory usage by Lucene.
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 */
	@GET("_segments")
	public Call<Object> indices_segments();

	@GET("{index}/_segments")
	public Call<Object> indices_segments(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-shards-stores.html
	 * <p>
	 * param: list status: A comma-separated list of statuses used to filter on shards to get store information for
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: null operation_threading: TODO: ?
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 */
	@GET("_shard_stores")
	public Call<Object> indices_shard_stores();

	@GET("{index}/_shard_stores")
	public Call<Object> indices_shard_stores(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-shrink-index.html
	 * <p>
	 * param: time timeout: Explicit operation timeout
	 * param: time master_timeout: Specify timeout for connection to master
	 * param: string wait_for_active_shards: Set the number of active shards to wait for on the shrunken index before the operation returns.
	 * body:The configuration for the target index (`settings` and `aliases`)
	 *
	 * @param string index: The name of the source index to shrink
	 * @param string target: The name of the target index to shrink into
	 */
	@PUT("{index}/_shrink/{target}")
	public Call<Object> indices_shrink(@Path("index") String index, @Path("target") String target, @Body Object body);

	@POST("{index}/_shrink/{target}")
	public Call<Object> indices_shrink2(@Path("index") String index, @Path("target") String target, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-stats.html
	 * <p>
	 * param: list completion_fields: A comma-separated list of fields for `fielddata` and `suggest` index metric (supports wildcards)
	 * param: list fielddata_fields: A comma-separated list of fields for `fielddata` index metric (supports wildcards)
	 * param: list fields: A comma-separated list of fields for `fielddata` and `completion` index metric (supports wildcards)
	 * param: list groups: A comma-separated list of search groups for `search` index metric
	 * param: boolean human: Whether to return time and byte values in human-readable format.
	 * param: enum level: Return stats aggregated at cluster, index or shard level
	 * param: list types: A comma-separated list of document types for the `indexing` index metric
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 * @param list metric: Limit the information returned the specific metrics.
	 */
	@GET("_stats")
	public Call<Object> indices_stats();

	@GET("_stats/{metric}")
	public Call<Object> indices_stats(@Path("metric") String metric);

	@GET("{index}/_stats")
	public Call<Object> indices_stats2(@Path("index") String index);

	@GET("{index}/_stats/{metric}")
	public Call<Object> indices_stats(@Path("index") String index, @Path("metric") String metric);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
	 * <p>
	 * param: time timeout: Request timeout
	 * param: time master_timeout: Specify timeout for connection to master
	 * body*:The definition of `actions` to perform
	 */
	@POST("_aliases")
	public Call<Object> indices_update_aliases(@Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-upgrade.html
	 * <p>
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean wait_for_completion: Specify whether the request should block until the all segments are upgraded (default: false)
	 * param: boolean only_ancient_segments: If true, only ancient (an older Lucene major release) segments will be upgraded
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 */
	@POST("_upgrade")
	public Call<Object> indices_upgrade(@Body Object body);

	@POST("{index}/_upgrade")
	public Call<Object> indices_upgrade(@Path("index") String index, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-validate.html
	 * <p>
	 * param: boolean explain: Return detailed information about the error
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: null operation_threading: TODO: ?
	 * param: string q: Query in the Lucene query string syntax
	 * param: string analyzer: The analyzer to use for the query string
	 * param: boolean analyze_wildcard: Specify whether wildcard and prefix queries should be analyzed (default: false)
	 * param: enum default_operator: The default operator for query string query (AND or OR)
	 * param: string df: The field to use as default where no field prefix is given in the query string
	 * param: boolean lenient: Specify whether format-based query failures (such as providing text to a numeric field) should be ignored
	 * param: boolean rewrite: Provide a more detailed explanation showing the actual Lucene query that will be executed.
	 * body:The query definition specified with the Query DSL
	 *
	 * @param list index: A comma-separated list of index names to restrict the operation; use `_all` or empty string to perform the operation on all indices
	 * @param list type: A comma-separated list of document types to restrict the operation; leave empty to perform the operation on all types
	 */
	@GET("_validate/query")
	public Call<Object> indices_validate_query();

	@GET("{index}/_validate/query")
	public Call<Object> indices_validate_query(@Path("index") String index);

	@GET("{index}/{type}/_validate/query")
	public Call<Object> indices_validate_query(@Path("index") String index, @Path("type") String type);

	@POST("_validate/query")
	public Call<Object> indices_validate_query(@Body Object body);

	@POST("{index}/_validate/query")
	public Call<Object> indices_validate_query(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_validate/query")
	public Call<Object> indices_validate_query(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/
	 * <p>
	 * body:null
	 */
	@GET("")
	public Call<Object> info();

	/**
	 * documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: time timeout: Explicit operation timeout
	 * body:null
	 *
	 * @param string id: Pipeline ID
	 */
	@DELETE("_ingest/pipeline/{id}")
	public Call<Object> ingest_delete_pipeline(@Path("id") String id);

	/**
	 * documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * body:null
	 *
	 * @param string id: Comma separated list of pipeline ids. Wildcards supported
	 */
	@GET("_ingest/pipeline")
	public Call<Object> ingest_get_pipeline();

	@GET("_ingest/pipeline/{id}")
	public Call<Object> ingest_get_pipeline(@Path("id") String id);

	/**
	 * documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: time timeout: Explicit operation timeout
	 * body*:The ingest definition
	 *
	 * @param string id: Pipeline ID
	 */
	@PUT("_ingest/pipeline/{id}")
	public Call<Object> ingest_put_pipeline(@Path("id") String id, @Body Object body);

	/**
	 * documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
	 * <p>
	 * param: boolean verbose: Verbose mode. Display data output for each processor in executed pipeline
	 * body*:The simulate definition
	 *
	 * @param string id: Pipeline ID
	 */
	@GET("_ingest/pipeline/_simulate")
	public Call<Object> ingest_simulate();

	@GET("_ingest/pipeline/{id}/_simulate")
	public Call<Object> ingest_simulate(@Path("id") String id);

	@POST("_ingest/pipeline/_simulate")
	public Call<Object> ingest_simulate(@Body Object body);

	@POST("_ingest/pipeline/{id}/_simulate")
	public Call<Object> ingest_simulate(@Path("id") String id, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-multi-get.html
	 * <p>
	 * param: list stored_fields: A comma-separated list of stored fields to return in the response
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: boolean realtime: Specify whether to perform the operation in realtime or search mode
	 * param: boolean refresh: Refresh the shard containing the document before performing the operation
	 * param: string routing: Specific routing value
	 * param: list _source: True or false to return the _source field or not, or a list of fields to return
	 * param: list _source_exclude: A list of fields to exclude from the returned _source field
	 * param: list _source_include: A list of fields to extract and return from the _source field
	 * body*:Document identifiers; can be either `docs` (containing full document information) or `ids` (when index and type is provided in the URL.
	 *
	 * @param string index: The name of the index
	 * @param string type: The type of the document
	 */
	@GET("_mget")
	public Call<Object> mget();

	@GET("{index}/_mget")
	public Call<Object> mget(@Path("index") String index);

	@GET("{index}/{type}/_mget")
	public Call<Object> mget(@Path("index") String index, @Path("type") String type);

	@POST("_mget")
	public Call<Object> mget(@Body Object body);

	@POST("{index}/_mget")
	public Call<Object> mget(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_mget")
	public Call<Object> mget(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-percolate.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * body*:The percolate request definitions (header & body pair), separated by newlines
	 *
	 * @param string index: The index of the document being count percolated to use as default
	 * @param string type: The type of the document being percolated to use as default.
	 */
	@GET("_mpercolate")
	public Call<Object> mpercolate();

	@GET("{index}/_mpercolate")
	public Call<Object> mpercolate(@Path("index") String index);

	@GET("{index}/{type}/_mpercolate")
	public Call<Object> mpercolate(@Path("index") String index, @Path("type") String type);

	@POST("_mpercolate")
	public Call<Object> mpercolate(@Body Object body);

	@POST("{index}/_mpercolate")
	public Call<Object> mpercolate(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_mpercolate")
	public Call<Object> mpercolate(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-multi-search.html
	 * <p>
	 * param: enum search_type: Search operation type
	 * param: number max_concurrent_searches: Controls the maximum number of concurrent searches the multi search api will execute
	 * body*:The request definitions (metadata-search request definition pairs), separated by newlines
	 *
	 * @param list index: A comma-separated list of index names to use as default
	 * @param list type: A comma-separated list of document types to use as default
	 */
	@GET("_msearch")
	public Call<Object> msearch();

	@GET("{index}/_msearch")
	public Call<Object> msearch(@Path("index") String index);

	@GET("{index}/{type}/_msearch")
	public Call<Object> msearch(@Path("index") String index, @Path("type") String type);

	@POST("_msearch")
	public Call<Object> msearch(@Body Object body);

	@POST("{index}/_msearch")
	public Call<Object> msearch(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_msearch")
	public Call<Object> msearch(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/current/search-multi-search.html
	 * <p>
	 * param: enum search_type: Search operation type
	 * body*:The request definitions (metadata-search request definition pairs), separated by newlines
	 *
	 * @param list index: A comma-separated list of index names to use as default
	 * @param list type: A comma-separated list of document types to use as default
	 */
	@GET("_msearch/template")
	public Call<Object> msearch_template();

	@GET("{index}/_msearch/template")
	public Call<Object> msearch_template(@Path("index") String index);

	@GET("{index}/{type}/_msearch/template")
	public Call<Object> msearch_template(@Path("index") String index, @Path("type") String type);

	@POST("_msearch/template")
	public Call<Object> msearch_template(@Body Object body);

	@POST("{index}/_msearch/template")
	public Call<Object> msearch_template(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_msearch/template")
	public Call<Object> msearch_template(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-multi-termvectors.html
	 * <p>
	 * param: list ids: A comma-separated list of documents ids. You must define ids as parameter or set "ids" or "docs" in the request body
	 * param: boolean term_statistics: Specifies if total term frequency and document frequency should be returned. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	 * param: boolean field_statistics: Specifies if document count, sum of document frequencies and sum of total term frequencies should be returned. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	 * param: list fields: A comma-separated list of fields to return. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	 * param: boolean offsets: Specifies if term offsets should be returned. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	 * param: boolean positions: Specifies if term positions should be returned. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	 * param: boolean payloads: Specifies if term payloads should be returned. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random) .Applies to all returned documents unless otherwise specified in body "params" or "docs".
	 * param: string routing: Specific routing value. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	 * param: string parent: Parent id of documents. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	 * param: boolean realtime: Specifies if requests are real-time as opposed to near-real-time (default: true).
	 * param: number version: Explicit version number for concurrency control
	 * param: enum version_type: Specific version type
	 * body:Define ids, documents, parameters or a list of parameters per document here. You must at least provide a list of document ids. See documentation.
	 *
	 * @param string index: The index in which the document resides.
	 * @param string type: The type of the document.
	 */
	@GET("_mtermvectors")
	public Call<Object> mtermvectors();

	@GET("{index}/_mtermvectors")
	public Call<Object> mtermvectors(@Path("index") String index);

	@GET("{index}/{type}/_mtermvectors")
	public Call<Object> mtermvectors(@Path("index") String index, @Path("type") String type);

	@POST("_mtermvectors")
	public Call<Object> mtermvectors(@Body Object body);

	@POST("{index}/_mtermvectors")
	public Call<Object> mtermvectors(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_mtermvectors")
	public Call<Object> mtermvectors(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-nodes-hot-threads.html
	 * <p>
	 * param: time interval: The interval for the second sampling of threads
	 * param: number snapshots: Number of samples of thread stacktrace (default: 10)
	 * param: number threads: Specify the number of threads to provide information for (default: 3)
	 * param: boolean ignore_idle_threads: Don't show threads that are in known-idle places, such as waiting on a socket select or pulling from an empty task queue (default: true)
	 * param: enum type: The type to sample (default: cpu)
	 * param: time timeout: Explicit operation timeout
	 * body:null
	 *
	 * @param list node_id: A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes
	 */
	@GET("_cluster/nodes/hotthreads")
	public Call<Object> nodes_hot_threads();

	@GET("_cluster/nodes/hot_threads")
	public Call<Object> nodes_hot_threads2();

	@GET("_cluster/nodes/{node_id}/hotthreads")
	public Call<Object> nodes_hot_threads(@Path("node_id") String node_id);

	@GET("_cluster/nodes/{node_id}/hot_threads")
	public Call<Object> nodes_hot_threads2(@Path("node_id") String node_id);

	@GET("_nodes/hotthreads")
	public Call<Object> nodes_hot_threads3();

	@GET("_nodes/hot_threads")
	public Call<Object> nodes_hot_threads4();

	@GET("_nodes/{node_id}/hotthreads")
	public Call<Object> nodes_hot_threads3(@Path("node_id") String node_id);

	@GET("_nodes/{node_id}/hot_threads")
	public Call<Object> nodes_hot_threads4(@Path("node_id") String node_id);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-nodes-info.html
	 * <p>
	 * param: boolean flat_settings: Return settings in flat format (default: false)
	 * param: boolean human: Whether to return time and byte values in human-readable format.
	 * param: time timeout: Explicit operation timeout
	 * body:null
	 *
	 * @param list node_id: A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes
	 * @param list metric: A comma-separated list of metrics you wish returned. Leave empty to return all.
	 */
	@GET("_nodes")
	public Call<Object> nodes_info();

	@GET("_nodes/{node_id}")
	public Call<Object> nodes_info(@Path("node_id") String node_id);

	@GET("_nodes/{metric}")
	public Call<Object> nodes_info2(@Path("metric") String metric);

	@GET("_nodes/{node_id}/{metric}")
	public Call<Object> nodes_info(@Path("node_id") String node_id, @Path("metric") String metric);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-nodes-stats.html
	 * <p>
	 * param: list completion_fields: A comma-separated list of fields for `fielddata` and `suggest` index metric (supports wildcards)
	 * param: list fielddata_fields: A comma-separated list of fields for `fielddata` index metric (supports wildcards)
	 * param: list fields: A comma-separated list of fields for `fielddata` and `completion` index metric (supports wildcards)
	 * param: boolean groups: A comma-separated list of search groups for `search` index metric
	 * param: boolean human: Whether to return time and byte values in human-readable format.
	 * param: enum level: Return indices stats aggregated at index, node or shard level
	 * param: list types: A comma-separated list of document types for the `indexing` index metric
	 * param: time timeout: Explicit operation timeout
	 * body:null
	 *
	 * @param list metric: Limit the information returned to the specified metrics
	 * @param list index_metric: Limit the information returned for `indices` metric to the specific index metrics. Isn't used if `indices` (or `all`) metric isn't specified.
	 * @param list node_id: A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes
	 */
	@GET("_nodes/stats")
	public Call<Object> nodes_stats();

	@GET("_nodes/{node_id}/stats")
	public Call<Object> nodes_stats(@Path("node_id") String node_id);

	@GET("_nodes/stats/{metric}")
	public Call<Object> nodes_stats2(@Path("metric") String metric);

	@GET("_nodes/{node_id}/stats/{metric}")
	public Call<Object> nodes_stats(@Path("metric") String metric, @Path("node_id") String node_id);

	@GET("_nodes/stats/{metric}/{index_metric}")
	public Call<Object> nodes_stats2(@Path("metric") String metric, @Path("index_metric") String index_metric);

	@GET("_nodes/{node_id}/stats/{metric}/{index_metric}")
	public Call<Object> nodes_stats(@Path("metric") String metric, @Path("index_metric") String index_metric, @Path("node_id") String node_id);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-percolate.html
	 * <p>
	 * param: list routing: A comma-separated list of specific routing values
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: string percolate_index: The index to percolate the document into. Defaults to index.
	 * param: string percolate_type: The type to percolate document into. Defaults to type.
	 * param: string percolate_routing: The routing value to use when percolating the existing document.
	 * param: string percolate_preference: Which shard to prefer when executing the percolate request.
	 * param: enum percolate_format: Return an array of matching query IDs instead of objects
	 * param: number version: Explicit version number for concurrency control
	 * param: enum version_type: Specific version type
	 * body:The percolator request definition using the percolate DSL
	 *
	 * @param string index: The index of the document being percolated.
	 * @param string type: The type of the document being percolated.
	 * @param string id: Substitute the document in the request body with a document that is known by the specified id. On top of the id, the index and type parameter will be used to retrieve the document from within the cluster.
	 */
	@GET("{index}/{type}/_percolate")
	public Call<Object> percolate(@Path("index") String index, @Path("type") String type);

	@GET("{index}/{type}/{id}/_percolate")
	public Call<Object> percolate(@Path("index") String index, @Path("type") String type, @Path("id") String id);

	@POST("{index}/{type}/_percolate")
	public Call<Object> percolate(@Path("index") String index, @Path("type") String type, @Body Object body);

	@POST("{index}/{type}/{id}/_percolate")
	public Call<Object> percolate(@Path("index") String index, @Path("type") String type, @Path("id") String id, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/
	 * <p>
	 * body:null
	 */
	@HEAD("")
	public Call<Void> ping();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-scripting.html
	 * <p>
	 * body*:The document
	 *
	 * @param string id: Script ID
	 * @param string lang: Script language
	 */
	@PUT("_scripts/{lang}/{id}")
	public Call<Object> put_script(@Path("id") String id, @Path("lang") String lang, @Body Object body);

	@POST("_scripts/{lang}/{id}")
	public Call<Object> put_script2(@Path("id") String id, @Path("lang") String lang, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-template.html
	 * <p>
	 * body*:The document
	 *
	 * @param string id: Template ID
	 */
	@PUT("_search/template/{id}")
	public Call<Object> put_template(@Path("id") String id, @Body Object body);

	@POST("_search/template/{id}")
	public Call<Object> put_template2(@Path("id") String id, @Body Object body);

	/**
	 * documentation: https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-reindex.html
	 * <p>
	 * param: boolean refresh: Should the effected indexes be refreshed?
	 * param: time timeout: Time each individual bulk request should wait for shards that are unavailable.
	 * param: string wait_for_active_shards: Sets the number of shard copies that must be active before proceeding with the reindex operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)
	 * param: boolean wait_for_completion: Should the request should block until the reindex is complete.
	 * param: number requests_per_second: The throttle to set on this request in sub-requests per second. -1 means no throttle.
	 * param: integer slices: The number of slices this task should be divided into. Defaults to 1 meaning the task isn't sliced into subtasks.
	 * body*:The search definition using the Query DSL and the prototype for the index request.
	 */
	@POST("_reindex")
	public Call<Object> reindex(@Body Object body);

	/**
	 * documentation: https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-reindex.html
	 * <p>
	 * param: number requests_per_second: The throttle to set on this request in floating sub-requests per second. -1 means set no throttle.
	 * body:null
	 *
	 * @param string task_id: The task id to rethrottle
	 */
	@POST("_reindex/{task_id}/_rethrottle")
	public Call<Object> reindex_rethrottle(@Path("task_id") String task_id, @Body Object body);

	@POST("_update_by_query/{task_id}/_rethrottle")
	public Call<Object> reindex_rethrottle2(@Path("task_id") String task_id, @Body Object body);

	@POST("_delete_by_query/{task_id}/_rethrottle")
	public Call<Object> reindex_rethrottle3(@Path("task_id") String task_id, @Body Object body);

	/**
	 * documentation: http://www.elasticsearch.org/guide/en/elasticsearch/reference/master/search-template.html
	 * <p>
	 * body:The search definition template and its params
	 *
	 * @param string id: The id of the stored search template
	 */
	@GET("_render/template")
	public Call<Object> render_search_template();

	@GET("_render/template/{id}")
	public Call<Object> render_search_template(@Path("id") String id);

	@POST("_render/template")
	public Call<Object> render_search_template(@Body Object body);

	@POST("_render/template/{id}")
	public Call<Object> render_search_template(@Path("id") String id, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-request-scroll.html
	 * <p>
	 * param: time scroll: Specify how long a consistent view of the index should be maintained for scrolled search
	 * param: string scroll_id: The scroll ID for scrolled search
	 * body:The scroll ID if not passed by URL or query parameter.
	 *
	 * @param string scroll_id: The scroll ID
	 */
	@GET("_search/scroll")
	public Call<Object> scroll();

	@GET("_search/scroll/{scroll_id}")
	public Call<Object> scroll(@Path("scroll_id") String scroll_id);

	@POST("_search/scroll")
	public Call<Object> scroll(@Body Object body);

	@POST("_search/scroll/{scroll_id}")
	public Call<Object> scroll(@Path("scroll_id") String scroll_id, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-search.html
	 * <p>
	 * param: string analyzer: The analyzer to use for the query string
	 * param: boolean analyze_wildcard: Specify whether wildcard and prefix queries should be analyzed (default: false)
	 * param: enum default_operator: The default operator for query string query (AND or OR)
	 * param: string df: The field to use as default where no field prefix is given in the query string
	 * param: boolean explain: Specify whether to return detailed information about score computation as part of a hit
	 * param: list stored_fields: A comma-separated list of stored fields to return as part of a hit
	 * param: list docvalue_fields: A comma-separated list of fields to return as the docvalue representation of a field for each hit
	 * param: list fielddata_fields: A comma-separated list of fields to return as the docvalue representation of a field for each hit
	 * param: number from: Starting offset (default: 0)
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean lenient: Specify whether format-based query failures (such as providing text to a numeric field) should be ignored
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: string q: Query in the Lucene query string syntax
	 * param: list routing: A comma-separated list of specific routing values
	 * param: time scroll: Specify how long a consistent view of the index should be maintained for scrolled search
	 * param: enum search_type: Search operation type
	 * param: number size: Number of hits to return (default: 10)
	 * param: list sort: A comma-separated list of <field>:<direction> pairs
	 * param: list _source: True or false to return the _source field or not, or a list of fields to return
	 * param: list _source_exclude: A list of fields to exclude from the returned _source field
	 * param: list _source_include: A list of fields to extract and return from the _source field
	 * param: number terminate_after: The maximum number of documents to collect for each shard, upon reaching which the query execution will terminate early.
	 * param: list stats: Specific 'tag' of the request for logging and statistical purposes
	 * param: string suggest_field: Specify which field to use for suggestions
	 * param: enum suggest_mode: Specify suggest mode
	 * param: number suggest_size: How many suggestions to return in response
	 * param: string suggest_text: The source text for which the suggestions should be returned
	 * param: time timeout: Explicit operation timeout
	 * param: boolean track_scores: Whether to calculate and return scores even if they are not used for sorting
	 * param: boolean version: Specify whether to return document version as part of a hit
	 * param: boolean request_cache: Specify if request cache should be used for this request or not, defaults to index level setting
	 * body:The search definition using the Query DSL
	 *
	 * @param list index: A comma-separated list of index names to search; use `_all` or empty string to perform the operation on all indices
	 * @param list type: A comma-separated list of document types to search; leave empty to perform the operation on all types
	 */
	@GET("_search")
	public Call<Object> search();

	@GET("{index}/_search")
	public Call<Object> search(@Path("index") String index);

	@GET("{index}/{type}/_search")
	public Call<Object> search(@Path("index") String index, @Path("type") String type);

	@POST("_search")
	public Call<Object> search(@Body Object body);

	@POST("{index}/_search")
	public Call<Object> search(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_search")
	public Call<Object> search(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-shards.html
	 * <p>
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: string routing: Specific routing value
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * body:null
	 *
	 * @param list index: A comma-separated list of index names to search; use `_all` or empty string to perform the operation on all indices
	 * @param list type: A comma-separated list of document types to search; leave empty to perform the operation on all types
	 */
	@GET("_search_shards")
	public Call<Object> search_shards();

	@GET("{index}/_search_shards")
	public Call<Object> search_shards(@Path("index") String index);

	@GET("{index}/{type}/_search_shards")
	public Call<Object> search_shards(@Path("index") String index, @Path("type") String type);

	@POST("_search_shards")
	public Call<Object> search_shards(@Body Object body);

	@POST("{index}/_search_shards")
	public Call<Object> search_shards(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_search_shards")
	public Call<Object> search_shards(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/current/search-template.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: list routing: A comma-separated list of specific routing values
	 * param: time scroll: Specify how long a consistent view of the index should be maintained for scrolled search
	 * param: enum search_type: Search operation type
	 * param: boolean explain: Specify whether to return detailed information about score computation as part of a hit
	 * param: boolean profile: Specify whether to profile the query execution
	 * body:The search definition template and its params
	 *
	 * @param list index: A comma-separated list of index names to search; use `_all` or empty string to perform the operation on all indices
	 * @param list type: A comma-separated list of document types to search; leave empty to perform the operation on all types
	 */
	@GET("_search/template")
	public Call<Object> search_template();

	@GET("{index}/_search/template")
	public Call<Object> search_template(@Path("index") String index);

	@GET("{index}/{type}/_search/template")
	public Call<Object> search_template(@Path("index") String index, @Path("type") String type);

	@POST("_search/template")
	public Call<Object> search_template(@Body Object body);

	@POST("{index}/_search/template")
	public Call<Object> search_template(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_search/template")
	public Call<Object> search_template(@Path("index") String index, @Path("type") String type, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: boolean wait_for_completion: Should this request wait until the operation has completed before returning
	 * body:The snapshot definition
	 *
	 * @param string repository: A repository name
	 * @param string snapshot: A snapshot name
	 */
	@PUT("_snapshot/{repository}/{snapshot}")
	public Call<Object> snapshot_create(@Path("repository") String repository, @Path("snapshot") String snapshot, @Body Object body);

	@POST("_snapshot/{repository}/{snapshot}")
	public Call<Object> snapshot_create2(@Path("repository") String repository, @Path("snapshot") String snapshot, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: time timeout: Explicit operation timeout
	 * param: boolean verify: Whether to verify the repository after creation
	 * body*:The repository definition
	 *
	 * @param string repository: A repository name
	 */
	@PUT("_snapshot/{repository}")
	public Call<Object> snapshot_create_repository(@Path("repository") String repository, @Body Object body);

	@POST("_snapshot/{repository}")
	public Call<Object> snapshot_create_repository2(@Path("repository") String repository, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * body:null
	 *
	 * @param string repository: A repository name
	 * @param string snapshot: A snapshot name
	 */
	@DELETE("_snapshot/{repository}/{snapshot}")
	public Call<Object> snapshot_delete(@Path("repository") String repository, @Path("snapshot") String snapshot);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: time timeout: Explicit operation timeout
	 * body:null
	 *
	 * @param list repository: A comma-separated list of repository names
	 */
	@DELETE("_snapshot/{repository}")
	public Call<Object> snapshot_delete_repository(@Path("repository") String repository);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: boolean ignore_unavailable: Whether to ignore unavailable snapshots, defaults to false which means a SnapshotMissingException is thrown
	 * body:null
	 *
	 * @param string repository: A repository name
	 * @param list   snapshot: A comma-separated list of snapshot names
	 */
	@GET("_snapshot/{repository}/{snapshot}")
	public Call<Object> snapshot_get(@Path("repository") String repository, @Path("snapshot") String snapshot);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 * body:null
	 *
	 * @param list repository: A comma-separated list of repository names
	 */
	@GET("_snapshot")
	public Call<Object> snapshot_get_repository();

	@GET("_snapshot/{repository}")
	public Call<Object> snapshot_get_repository(@Path("repository") String repository);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: boolean wait_for_completion: Should this request wait until the operation has completed before returning
	 * body:Details of what to restore
	 *
	 * @param string repository: A repository name
	 * @param string snapshot: A snapshot name
	 */
	@POST("_snapshot/{repository}/{snapshot}/_restore")
	public Call<Object> snapshot_restore(@Path("repository") String repository, @Path("snapshot") String snapshot, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: boolean ignore_unavailable: Whether to ignore unavailable snapshots, defaults to false which means a SnapshotMissingException is thrown
	 * body:null
	 *
	 * @param string repository: A repository name
	 * @param list   snapshot: A comma-separated list of snapshot names
	 */
	@GET("_snapshot/_status")
	public Call<Object> snapshot_status();

	@GET("_snapshot/{repository}/_status")
	public Call<Object> snapshot_status(@Path("repository") String repository);

	@GET("_snapshot/{repository}/{snapshot}/_status")
	public Call<Object> snapshot_status(@Path("repository") String repository, @Path("snapshot") String snapshot);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
	 * <p>
	 * param: time master_timeout: Explicit operation timeout for connection to master node
	 * param: time timeout: Explicit operation timeout
	 * body:null
	 *
	 * @param string repository: A repository name
	 */
	@POST("_snapshot/{repository}/_verify")
	public Call<Object> snapshot_verify_repository(@Path("repository") String repository, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-suggesters.html
	 * <p>
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: string routing: Specific routing value
	 * body*:The request definition
	 *
	 * @param list index: A comma-separated list of index names to restrict the operation; use `_all` or empty string to perform the operation on all indices
	 */
	@POST("_suggest")
	public Call<Object> suggest(@Body Object body);

	@POST("{index}/_suggest")
	public Call<Object> suggest(@Path("index") String index, @Body Object body);

	@GET("_suggest")
	public Call<Object> suggest();

	@GET("{index}/_suggest")
	public Call<Object> suggest(@Path("index") String index);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
	 * <p>
	 * param: list node_id: A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes
	 * param: list actions: A comma-separated list of actions that should be cancelled. Leave empty to cancel all.
	 * param: string parent_node: Cancel tasks with specified parent node.
	 * param: string parent_task: Cancel tasks with specified parent task id (node_id:task_number). Set to -1 to cancel all.
	 * body:null
	 *
	 * @param string task_id: Cancel the task with specified task id (node_id:task_number)
	 */
	@POST("_tasks/_cancel")
	public Call<Object> tasks_cancel(@Body Object body);

	@POST("_tasks/{task_id}/_cancel")
	public Call<Object> tasks_cancel(@Path("task_id") String task_id, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
	 * <p>
	 * param: boolean wait_for_completion: Wait for the matching tasks to complete (default: false)
	 * body:null
	 *
	 * @param string task_id: Return the task with specified id (node_id:task_number)
	 */
	@GET("_tasks/{task_id}")
	public Call<Object> tasks_get(@Path("task_id") String task_id);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
	 * <p>
	 * param: list node_id: A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes
	 * param: list actions: A comma-separated list of actions that should be returned. Leave empty to return all.
	 * param: boolean detailed: Return detailed task information (default: false)
	 * param: string parent_node: Return tasks with specified parent node.
	 * param: string parent_task: Return tasks with specified parent task id (node_id:task_number). Set to -1 to return all.
	 * param: boolean wait_for_completion: Wait for the matching tasks to complete (default: false)
	 * param: enum group_by: Group tasks by nodes or parent/child relationships
	 * body:null
	 */
	@GET("_tasks")
	public Call<Object> tasks_list();

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-termvectors.html
	 * <p>
	 * param: boolean term_statistics: Specifies if total term frequency and document frequency should be returned.
	 * param: boolean field_statistics: Specifies if document count, sum of document frequencies and sum of total term frequencies should be returned.
	 * param: list fields: A comma-separated list of fields to return.
	 * param: boolean offsets: Specifies if term offsets should be returned.
	 * param: boolean positions: Specifies if term positions should be returned.
	 * param: boolean payloads: Specifies if term payloads should be returned.
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random).
	 * param: string routing: Specific routing value.
	 * param: string parent: Parent id of documents.
	 * param: boolean realtime: Specifies if request is real-time as opposed to near-real-time (default: true).
	 * param: number version: Explicit version number for concurrency control
	 * param: enum version_type: Specific version type
	 * body:Define parameters and or supply a document to get termvectors for. See documentation.
	 *
	 * @param string index: The index in which the document resides.
	 * @param string type: The type of the document.
	 * @param string id: The id of the document, when not specified a doc param should be supplied.
	 */
	@GET("{index}/{type}/_termvectors")
	public Call<Object> termvectors(@Path("index") String index, @Path("type") String type);

	@GET("{index}/{type}/{id}/_termvectors")
	public Call<Object> termvectors(@Path("index") String index, @Path("type") String type, @Path("id") String id);

	@POST("{index}/{type}/_termvectors")
	public Call<Object> termvectors(@Path("index") String index, @Path("type") String type, @Body Object body);

	@POST("{index}/{type}/{id}/_termvectors")
	public Call<Object> termvectors(@Path("index") String index, @Path("type") String type, @Path("id") String id, @Body Object body);

	/**
	 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-update.html
	 * <p>
	 * param: string wait_for_active_shards: Sets the number of shard copies that must be active before proceeding with the update operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)
	 * param: list fields: A comma-separated list of fields to return in the response
	 * param: list _source: True or false to return the _source field or not, or a list of fields to return
	 * param: list _source_exclude: A list of fields to exclude from the returned _source field
	 * param: list _source_include: A list of fields to extract and return from the _source field
	 * param: string lang: The script language (default: groovy)
	 * param: string parent: ID of the parent document. Is is only used for routing and when for the upsert request
	 * param: enum refresh: If `true` then refresh the effected shards to make this operation visible to search, if `wait_for` then wait for a refresh to make this operation visible to search, if `false` (the default) then do nothing with refreshes.
	 * param: number retry_on_conflict: Specify how many times should the operation be retried when a conflict occurs (default: 0)
	 * param: string routing: Specific routing value
	 * param: time timeout: Explicit operation timeout
	 * param: time timestamp: Explicit timestamp for the document
	 * param: time ttl: Expiration time for the document
	 * param: number version: Explicit version number for concurrency control
	 * param: enum version_type: Specific version type
	 * body:The request definition using either `script` or partial `doc`
	 *
	 * @param string id: Document ID
	 * @param string index: The name of the index
	 * @param string type: The type of the document
	 */
	@POST("{index}/{type}/{id}/_update")
	public Call<Object> update(@Path("index") String index, @Path("type") String type, @Path("id") String id, @Body Object body);

	/**
	 * documentation: https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-update-by-query.html
	 * <p>
	 * param: string analyzer: The analyzer to use for the query string
	 * param: boolean analyze_wildcard: Specify whether wildcard and prefix queries should be analyzed (default: false)
	 * param: enum default_operator: The default operator for query string query (AND or OR)
	 * param: string df: The field to use as default where no field prefix is given in the query string
	 * param: number from: Starting offset (default: 0)
	 * param: boolean ignore_unavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 * param: boolean allow_no_indices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 * param: enum conflicts: What to do when the update by query hits version conflicts?
	 * param: enum expand_wildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 * param: boolean lenient: Specify whether format-based query failures (such as providing text to a numeric field) should be ignored
	 * param: string pipeline: Ingest pipeline to set on index requests made by this action. (default: none)
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random)
	 * param: string q: Query in the Lucene query string syntax
	 * param: list routing: A comma-separated list of specific routing values
	 * param: time scroll: Specify how long a consistent view of the index should be maintained for scrolled search
	 * param: enum search_type: Search operation type
	 * param: time search_timeout: Explicit timeout for each search request. Defaults to no timeout.
	 * param: number size: Number of hits to return (default: 10)
	 * param: list sort: A comma-separated list of <field>:<direction> pairs
	 * param: list _source: True or false to return the _source field or not, or a list of fields to return
	 * param: list _source_exclude: A list of fields to exclude from the returned _source field
	 * param: list _source_include: A list of fields to extract and return from the _source field
	 * param: number terminate_after: The maximum number of documents to collect for each shard, upon reaching which the query execution will terminate early.
	 * param: list stats: Specific 'tag' of the request for logging and statistical purposes
	 * param: boolean version: Specify whether to return document version as part of a hit
	 * param: boolean version_type: Should the document increment the version number (internal) on hit or not (reindex)
	 * param: boolean request_cache: Specify if request cache should be used for this request or not, defaults to index level setting
	 * param: boolean refresh: Should the effected indexes be refreshed?
	 * param: time timeout: Time each individual bulk request should wait for shards that are unavailable.
	 * param: string wait_for_active_shards: Sets the number of shard copies that must be active before proceeding with the update by query operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)
	 * param: number scroll_size: Size on the scroll request powering the update_by_query
	 * param: boolean wait_for_completion: Should the request should block until the update by query operation is complete.
	 * param: number requests_per_second: The throttle to set on this request in sub-requests per second. -1 means no throttle.
	 * param: integer slices: The number of slices this task should be divided into. Defaults to 1 meaning the task isn't sliced into subtasks.
	 * body:The search definition using the Query DSL
	 *
	 * @param list index: A comma-separated list of index names to search; use `_all` or empty string to perform the operation on all indices
	 * @param list type: A comma-separated list of document types to search; leave empty to perform the operation on all types
	 */
	@POST("{index}/_update_by_query")
	public Call<Object> update_by_query(@Path("index") String index, @Body Object body);

	@POST("{index}/{type}/_update_by_query")
	public Call<Object> update_by_query(@Path("index") String index, @Path("type") String type, @Body Object body);

}
