package JDBC.Bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/26
 */
public class resultJson {

    /**
     * @param information
     * @return
     * @desc 普通用户登录
     */
    public String userLogin(String information) {
        JSONObject jsonObject = new JSONObject();
        if (information == null) {
            jsonObject.put("code", 404);
            jsonObject.put("messages", "用户名或密码错误");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("messages", "登录成功");
        }
        return jsonObject.toString();
    }

    /**
     * @param information
     * @return
     * @desc 各种操作
     */
    public String register(String information) {
        JSONObject jsonObject = new JSONObject();
        if (information.equals("success")) {
            jsonObject.put("code", 200);
            jsonObject.put("messages", "注册成功");
        } else {
            jsonObject.put("code", 404);
            jsonObject.put("messages", "用户名已被注册");
        }
        return jsonObject.toString();
    }


    public String operator(String information) {
        JSONObject jsonObject = new JSONObject();
        if (information.equals("success")) {
            jsonObject.put("code", 200);
            jsonObject.put("messages", "操作成功");
        } else {
            jsonObject.put("code", 404);
            jsonObject.put("messages", "操作失败");
        }
        return jsonObject.toString();
    }

}
