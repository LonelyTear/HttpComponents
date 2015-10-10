package com.bobo.code.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class BankAccount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7734857689901352680L;
	@NotNull
	@NumberFormat(style=Style.NUMBER)
	private Integer id;//主键
	@Size(max=20)
	private String name;
	private Double balance;//余额
	private Date updateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public BankAccount(Integer id, String name, Double balance, Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.updateTime = updateTime;
	}
	public BankAccount() {
		super();
	}

	





	
}
