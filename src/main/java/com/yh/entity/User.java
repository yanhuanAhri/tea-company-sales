package com.yh.entity;

import java.sql.Date;

public class User{

	private Long id;
	private String userName;
	private String password;
	private String nickName;
	private String Email;
	private String Phone;
	private String headPortrair;
	private String sex;
	private Date birthday;
	private Long integral;
	
	public Long getId() {
		return id;   
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getHeadPortrair() {
		return headPortrair;
	}
	public void setHeadPortrair(String headPortrair) {
		this.headPortrair = headPortrair;
	}
	public String getSex() {
		return sex;
	}
	//0：女；  1：男
	public void setSex(Integer sex) {
		if(sex==0) {
			this.sex = "女";
		}else {
			this.sex = "男";
		}
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Long getIntegral() {
		return integral;
	}
	public void setIntegral(Long integral) {
		this.integral = integral;
	}
	
}
