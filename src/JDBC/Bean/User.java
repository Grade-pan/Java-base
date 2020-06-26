package JDBC.Bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/11
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @JSONField(name = "username", ordinal = 1)
    private String username;

    @JSONField(name = "Email", ordinal = 2)
    private String email;

    @JSONField(name = "Password", ordinal = 3)
    private String password;

    @JSONField(name = "Age", ordinal = 4)
    private String age;

    @JSONField(name = "Telephone", ordinal = 5)
    private String Tel;

    @JSONField(name = "Address", ordinal = 6)
    private String address;

    @JSONField(name = "IP", ordinal = 7)
    private String ip;

    @JSONField(name = "Sex", ordinal = 8)
    private String sex;

    @JSONField(name = "SuperRoot", ordinal = 9)
    private String superRoot;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSuperRoot() {
        return superRoot;
    }

    public void setSuperRoot(String superRoot) {
        this.superRoot = superRoot;
    }
}
