package com.date.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="dob")
	private Date dob;
	@Column(name="age")
	private int age;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(
	name="hobby_user",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="hobby_id"))
	//@JsonManagedReference
	private Set<Hobby> hobbies = new HashSet<>();
	@Column(name="gender")
	private String gender;
	@Column(name="dp")
	private String dp;
	
	public User(String name, Date dob,Set<Hobby> hobbies, String gender, String dp) {
		
		this.name = name;
		this.dob = dob;
		this.age = getAgeFromDob(dob);
		this.hobbies = hobbies;
		this.gender = gender;
		this.dp = dp;
	}
	
	public User() {
	
	}
	public int getAgeFromDob(Date dob)
	{
		LocalDate birthDate= new java.sql.Date(dob.getTime()).toLocalDate();
		int currentAge= Period.between(birthDate,LocalDate.now()).getYears();
		return currentAge;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
		this.age=getAgeFromDob(dob);
	}
	public int getAge() {
		return age;
	}
	
	public Set<Hobby> getHobbies() {
		return hobbies;
	}
	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDp() {
		return dp;
	}
	public void setDp(String dp) {
		this.dp = dp;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addHobbies(Hobby hobby)
	{
		if(hobbies==null)
		{
			hobbies=new HashSet<>();
			
		}
		hobbies.add(hobby);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + ", age=" + age + ", hobbies=" + hobbies
				+ ", gender=" + gender + ", dp=" + dp + "]";
	}

	

	
	
	
	
	
	
	
	
}
