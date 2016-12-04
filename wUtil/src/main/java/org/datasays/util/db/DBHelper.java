package org.datasays.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBHelper {
	private static final boolean DEBUG = true;
	private static final int QueryTimeout = 30;
	private Connection connection = null;

	public DBHelper(String driver, String url) throws Exception {
		super();
		Class.forName(driver);
		connection = DriverManager.getConnection(url);
	}

	private PreparedStatement buildPreparedStatement(String sql, Object... params)
			throws SQLException {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setQueryTimeout(QueryTimeout);
		String debugsql = sql;
		if (params != null) {
			int parameterIndex = 1;
			for (Object p : params) {
				statement.setString(parameterIndex++, p.toString());
				if (DEBUG) {
					if (p instanceof Number) {
						debugsql = debugsql.replaceFirst("\\?", p.toString());
					} else {
						debugsql = debugsql.replaceFirst("\\?", "'" + p.toString() + "'");
					}
				}
			}
		}
		if (DEBUG) {
			System.out.println(debugsql);
		}
		return statement;
	}

	private Integer getGeneratedKey(PreparedStatement ps) throws SQLException {
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		return null;
	}

	public ResultSet executeQuery(String sql, List<Object> params) throws SQLException {
		return buildPreparedStatement(sql, params.toArray(new Object[]{})).executeQuery();
	}

	public ResultSet executeQuery(String sql, Object... params) throws SQLException {
		return buildPreparedStatement(sql, params).executeQuery();
	}

	public int executeUpdate(String sql, List<Object> params) throws SQLException {
		return buildPreparedStatement(sql, params.toArray(new Object[]{})).executeUpdate();
	}

	public int executeUpdate(String sql, Object... params) throws SQLException {
		return buildPreparedStatement(sql, params).executeUpdate();
	}

	public Integer insertTable(String table, List<String> cols, List<Object> params)
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		sql.append(table);
		sql.append("(");
		StringBuffer sql2 = new StringBuffer();
		for (int i = 0; i < cols.size(); i++) {
			sql.append(cols.get(i));
			sql2.append("?");
			if (i < cols.size() - 1) {
				sql.append(",");
				sql2.append(",");
			}
		}
		sql.append(")values(");
		sql.append(sql2.toString());
		sql.append(")");
		return executeInsert(sql.toString(), params);
	}

	public Integer executeInsert(String sql, List<Object> params) throws SQLException {
		return getGeneratedKey(buildPreparedStatement(sql, params.toArray(new Object[]{})));
	}

	public Integer executeInsert(String sql, Object... params) throws SQLException {
		return getGeneratedKey(buildPreparedStatement(sql, params));
	}

	public Long getLong(String sql, Object... params) throws SQLException {
		ResultSet rs = null;
		try {
			rs = executeQuery(sql, params);
			while (rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return null;
	}

	public static String insert(String table, String[] columns, String extsql) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		sql.append(table);
		sql.append("(");
		StringBuffer values = new StringBuffer();
		for (int i = 0; i < columns.length - 1; i++) {
			sql.append("'");
			sql.append(columns[i]);
			sql.append("',");
			values.append("?,");
		}
		sql.append("'");
		sql.append(columns[columns.length - 1]);
		sql.append("'");
		values.append("?");
		sql.append(")values(");
		sql.append(values);
		sql.append(") ");
		if (extsql != null) {
			sql.append(extsql);
		}
		return sql.toString();
	}

	public static String update(String table, String[] columns, String extsql) {
		StringBuffer sql = new StringBuffer();
		sql.append("update ");
		sql.append(table);
		sql.append(" set ");
		for (int i = 0; i < columns.length - 1; i++) {
			sql.append(columns[i]);
			sql.append("=?,");
		}
		sql.append(columns[columns.length - 1]);
		sql.append("=? ");
		if (extsql != null) {
			sql.append(extsql);
		}
		return sql.toString();
	}

	public static String selectFieldSql(String[] columns, String prefix) {
		StringBuffer sql = new StringBuffer();
		for (String col : columns) {
			if (prefix == null) {
				sql.append(col + ",");
			} else {
				sql.append(prefix + "." + col + " as " + prefix + "_" + col + ",");
			}
		}
		String sqlText = sql.toString().trim();
		if (sqlText.length() > 0) {
			sqlText = sqlText.substring(0, sqlText.length() - 1);
		}
		return sqlText;
	}

	public static String cnd(boolean cnd, String prefix, String sql) {
		if (cnd) {
			return prefix + sql;
		}
		return "";
	}

	public static void close(ResultSet s) {
		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement s) {
		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}

	public void batchSql(List<String> sqls) {
		if (sqls == null || sqls.size() < 1) {
			return;
		}
		Statement s = null;
		try {
			s = connection.createStatement();
			for (String sql : sqls) {
				s.addBatch(sql);
			}
			s.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(s);
		}
	}
}
