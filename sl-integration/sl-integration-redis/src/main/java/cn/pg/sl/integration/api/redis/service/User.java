package cn.pg.sl.integration.api.redis.service;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/12 4:20 下午
 */

public class User {
    private long id;
    private String nickname;
    private String mobile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //在输出的Json数据中隐藏密码，只能输入不输出
    private String password;
    private String role;

    public User(long id, String nickname, String mobile, String password, String role) {
        this.id = id;
        this.nickname = nickname;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }

    public User() {
        super();
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}