package Root.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/14
 *
 * @des 解析前台传来json数据
 */
public class analysis {
    private Reader jsonString;
    private List<String> list;

    public analysis(Reader jsonString, List<String> list) {
        this.jsonString = jsonString;
        this.list = list;
    }

    public Map<Object, Object> Username() throws IOException {

        Map<Object, Object> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(jsonString);
        String json = reader.readLine();
        JSONObject jsonObject = JSON.parseObject(json);
        for (String string : list) {
            map.put(string, jsonObject.getString(string));
        }
        return map;
    }
}
