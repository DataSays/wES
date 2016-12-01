package org.datasays.wes.vo;

import com.google.gson.annotations.SerializedName;
import org.datasays.util.collection.StrObjMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by watano on 2016/12/1.
 */
public class DeleteByQueryResult {

		@SerializedName("took")
		private Integer took;
		@SerializedName("timed_out")
		private Boolean timedOut;
		@SerializedName("total")
		private Integer total;
		@SerializedName("deleted")
		private Integer deleted;
		@SerializedName("batches")
		private Integer batches;
		@SerializedName("version_conflicts")
		private Integer versionConflicts;
		@SerializedName("noops")
		private Integer noops;
		@SerializedName("retries")
		private StrObjMap retries;
		@SerializedName("throttled_millis")
		private Integer throttledMillis;
		@SerializedName("requests_per_second")
		private Integer requestsPerSecond;
		@SerializedName("throttled_until_millis")
		private Integer throttledUntilMillis;
		@SerializedName("failures")
		private List<Object> failures = new ArrayList<Object>();

		public Integer getTook() {
				return took;
		}

		public void setTook(Integer took) {
				this.took = took;
		}

		public Boolean getTimedOut() {
				return timedOut;
		}

		public void setTimedOut(Boolean timedOut) {
				this.timedOut = timedOut;
		}

		public Integer getTotal() {
				return total;
		}

		public void setTotal(Integer total) {
				this.total = total;
		}

		public Integer getDeleted() {
				return deleted;
		}

		public void setDeleted(Integer deleted) {
				this.deleted = deleted;
		}

		public Integer getBatches() {
				return batches;
		}

		public void setBatches(Integer batches) {
				this.batches = batches;
		}

		public Integer getVersionConflicts() {
				return versionConflicts;
		}

		public void setVersionConflicts(Integer versionConflicts) {
				this.versionConflicts = versionConflicts;
		}

		public Integer getNoops() {
				return noops;
		}

		public void setNoops(Integer noops) {
				this.noops = noops;
		}

		public StrObjMap getRetries() {
				return retries;
		}

		public void setRetries(StrObjMap retries) {
				this.retries = retries;
		}

		public Integer getThrottledMillis() {
				return throttledMillis;
		}

		public void setThrottledMillis(Integer throttledMillis) {
				this.throttledMillis = throttledMillis;
		}

		public Integer getRequestsPerSecond() {
				return requestsPerSecond;
		}

		public void setRequestsPerSecond(Integer requestsPerSecond) {
				this.requestsPerSecond = requestsPerSecond;
		}

		public Integer getThrottledUntilMillis() {
				return throttledUntilMillis;
		}

		public void setThrottledUntilMillis(Integer throttledUntilMillis) {
				this.throttledUntilMillis = throttledUntilMillis;
		}

		public List<Object> getFailures() {
				return failures;
		}

		public void setFailures(List<Object> failures) {
				this.failures = failures;
		}
}
