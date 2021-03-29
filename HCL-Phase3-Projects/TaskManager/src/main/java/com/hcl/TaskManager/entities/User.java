package com.hcl.TaskManager.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String userName;
    private String password;
    private boolean active;
	private String roles;

	public User(String name, String userName, String password)
	{
		this.name = name;
		this.active = true;
		this.roles = "USER";
		this.userName = userName;
		this.password = password;
	}
	
	// Empty Constructor
	public User()
	{
		
	}
	
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
    public String getUserName() {
		return userName;
	}
    
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
	public boolean isActive() {
		return active;
	}
    
    public void setActive(boolean active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}