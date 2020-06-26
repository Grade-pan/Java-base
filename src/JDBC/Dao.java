package JDBC;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/11
 */
public interface Dao {
    /**
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     * @desc 多条查询
     */
    List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException;

    /**
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     * @desc 增、删、改
     */
    String update(String sql, List<Object> params);

    /**
     * @param sql
     * @return
     * @desc 返回记录总数
     */
    Integer Count(String sql);

    /**
     * @param sql
     * @param params
     * @param cls
     * @param <T>
     * @return
     * @desc 反射查询
     */
    <T> T findSimpleRefResult(String sql, List<Object> params, Class<T> cls);

    /**
     * @param sql
     * @param params
     * @param cls
     * @param <T>
     * @return
     * @desc 查询多条记录
     */
    <T> List<T> findMoreRefResult(String sql, List<Object> params, Class<T> cls);

    /**
     *
     * @param sql
     * @param params
     * @return
     * @desc 单条查询
     */
    Map<String, Object> findSimpleResult(String sql, List<Object> params);
}
