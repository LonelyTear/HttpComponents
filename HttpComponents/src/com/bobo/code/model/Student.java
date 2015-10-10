package com.bobo.code.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4596405917591742863L;
	//这里的属性必须用是对象类型(Integer,Double),如果用基本(int,double),有时候前台传后台会报未知名异常
	@NotNull
	@NumberFormat(style=Style.NUMBER)
	private Integer id;//主键
	@Size(max=20)
	private String name;
	private String address;
	private Double tuition;//学费
	/**如果没有@DateTimeFormat 那么会报绑定错误 如下-->
	 * org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 1 errors
	Field error in object 'student' on field 'birthDate': rejected value [1988-01-03]; codes [typeMismatch.student.birthDate,typeMismatch.birthDate,typeMismatch.java.util.Date,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [student.birthDate,birthDate]; arguments []; default message [birthDate]]; default message [Failed to convert property value of type 'java.lang.String' to required type 'java.util.Date' for property 'birthDate'; nested exception is org.springframework.core.convert.ConversionFailedException: Unable to convert value "1988-01-03" from type 'java.lang.String' to type 'java.util.Date'; nested exception is java.lang.IllegalArgumentException]
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	private Date updateTime;
	
	public Student() {
		super();
	}

	
	public Student(Integer id, String name, String address, Double tuition,
			Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.tuition = tuition;
		this.birthDate = birthDate;
	}


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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Double getTuition() {
		return tuition;
	}


	public void setTuition(Double tuition) {
		this.tuition = tuition;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", address=" + address
				+ ", tuition=" + tuition + ", birthDate=" + birthDate
				+ ", updateTime=" + updateTime + "]";
	}







	
}
