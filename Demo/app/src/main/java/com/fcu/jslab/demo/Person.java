package com.fcu.jslab.demo;

/**
 * Created by Administrator on 2016/10/13.
 */

public class Person {
    private String account;
    private String password;
    private String name = "Roger";
    private String country = "Taiwan";
    private String twitter = "66666";

    public String getAccount(){
        return this.account;
    }
    public String getPassword(){
        return this.password;
    }
    public void setAccount(String account){
        this.account = account;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getName(){
        return this.name;
    }
    public  String getCountry(){
        return this.country;
    }
    public String getTwitter(){
        return  this.twitter;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
