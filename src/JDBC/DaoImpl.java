package JDBC;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/11
 */
public class DaoImpl implements Dao {

    /**
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     * @desc 多条查询
     */

    @Override
    public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        int index = 1;
        Connection connection = Druid.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(index++, params.get(i));
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_values = resultSet.getObject(cols_name);
                if (cols_values == null) {
                    cols_values = "";
                }
                map.put(cols_name, cols_values);
            }
            list.add(map);
        }
        //关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return list;
    }

    /**
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     * @desc 增、删、改
     */
    @Override
    public String update(String sql, List<Object> params) {
        String information = "";
        int result = -1;
        try {
            Connection connection = Druid.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            if (params != null && !params.isEmpty()) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(index++, params.get(i));
                }
            }
            result = preparedStatement.executeUpdate();
            information = result > 0 ? "success" : "用户名已被注册";
            //关闭连接
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }


    /**
     * @param sql
     * @return
     * @desc 返回记录总数
     */
    @Override
    public Integer Count(String sql) {
        ResultSet result = null;
        int count = 0;
        try {
            Connection connection = Druid.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            result.next();
            count = result.getInt(1);
            //关闭连接
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * @param sql
     * @param params
     * @param cls
     * @return
     * @desc 反射查询, 单条查询
     */
    @Override
    public <T> T findSimpleRefResult(String sql, List<Object> params, Class<T> cls) {
        T resultObject = null;
        int index = 1;
        try {
            Connection connection = Druid.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (params != null && !params.isEmpty()) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(index++, params.get(i));
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int cols_len = metaData.getColumnCount();
            while (resultSet.next()) {
                //通过反射机制创建一个实例
                resultObject = cls.newInstance();
                for (int i = 0; i < cols_len; i++) {
                    String cols_name = metaData.getColumnName(i + 1);
                    Object cols_value = resultSet.getObject(cols_name);
                    if (cols_value == null) {
                        cols_value = "";
                    }
                    Field field = cls.getDeclaredField(cols_name);
                    field.setAccessible(true);
                    field.set(resultObject, cols_value);
                }
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    /**
     * @param sql
     * @param params
     * @param cls
     * @return
     * @desc 查询多条记录
     */
    @Override
    public <T> List<T> findMoreRefResult(String sql, List<Object> params, Class<T> cls) {
        List<T> list = new ArrayList<>();
        int index = 1;
        Connection connection = Druid.getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (params != null && !params.isEmpty()) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(index++, params.get(i));
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int cols_len = metaData.getColumnCount();
            while (resultSet.next()) {
                T resultObject = cls.newInstance();
                for (int i = 0; i < cols_len; i++) {
                    String cols_name = metaData.getColumnName(i + 1);
                    Object cols_value = resultSet.getObject(cols_name);
                    if (cols_value == null) {
                        cols_value = "";
                    }
                    Field field = cls.getDeclaredField(cols_name);
                    field.setAccessible(true);
                    field.set(resultObject, cols_value);
                }
                list.add(resultObject);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * @param sql
     * @param params
     * @return
     * @desc 单条查询
     */
    @Override
    public Map<String, Object> findSimpleResult(String sql, List<Object> params) {
        Map<String, Object> map = new HashMap<>();
        int index = 1;
        Connection connection = Druid.getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (params != null && !params.isEmpty()) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(index++, params.get(i));
                }
                ResultSet resultSet = preparedStatement.executeQuery();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int cols_len = metaData.getColumnCount();
                while (resultSet.next()) {
                    for (int i = 0; i < cols_len; i++) {
                        String cols_name = metaData.getColumnName(i + 1);
                        Object cols_value = resultSet.getObject(cols_name);
                        if (cols_value == null) {
                            cols_value = "";
                        }
                        map.put(cols_name, cols_value);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
