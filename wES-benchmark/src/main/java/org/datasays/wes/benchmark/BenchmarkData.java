package org.datasays.wes.benchmark;

/**
 * Created by watano on 2016/11/28.
 */
public class BenchmarkData {
		private String group;
		private String task;
		private long times;

		public BenchmarkData(String group, String task) {
				this.group = group;
				this.task = task;
				this.times = System.currentTimeMillis();
		}

		public String toText(BenchmarkData parent) {
				if (parent != null && parent.times >= 0) {
						return "["+group+"]"+task + " cost:" + (times - parent.times) + "ms.";
				}
				return "";
		}

		public String getGroup() {
				return group;
		}

		public void setGroup(String group) {
				this.group = group;
		}

		public String getTask() {
				return task;
		}

		public void setTask(String task) {
				this.task = task;
		}

		public long getTimes() {
				return times;
		}

		public void setTimes(long times) {
				this.times = times;
		}
}
