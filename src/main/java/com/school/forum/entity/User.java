package com.school.forum.entity;

public class User {
    private int user_id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String bio;
    private String avatar_url;

    public User() {
    }

    public User(String account_id, String name, String token, Long gmt_create, Long gmt_modified, String bio, String avatar_url) {
        this.account_id = account_id;
        this.name = name;
        this.token = token;
        this.gmt_create = gmt_create;
        this.gmt_modified = gmt_modified;
        this.bio = bio;
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", account_id='" + account_id + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", gmt_create=" + gmt_create +
                ", gmt_modified=" + gmt_modified +
                ", bio='" + bio + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }

    public User(int user_id, String account_id, String name, String token, Long gmt_create, Long gmt_modified, String bio, String avatar_url) {
        this.user_id = user_id;
        this.account_id = account_id;
        this.name = name;
        this.token = token;
        this.gmt_create = gmt_create;
        this.gmt_modified = gmt_modified;
        this.bio = bio;
        this.avatar_url = avatar_url;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Long getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Long gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
