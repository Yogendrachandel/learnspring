package com.example.demo.enitity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user_tbl")
public class User {
	
	@Id
	@Column
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_generator",
            initialValue = 101
    )
	private int id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public User( String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
