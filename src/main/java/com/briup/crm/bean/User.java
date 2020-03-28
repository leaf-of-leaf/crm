package com.briup.crm.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;

    private Integer flag;

    @JoinColumn(name = "role_id")
    @ManyToOne
    @JsonIgnore //将来返回给前台时忽略当前属性
    //@ManyToOne要求相关联的两个javabean都有@Entity,且数据库中表不对应
    //要加入@Table(name = "")
    private Role role;

    public User() {
		super();
	}

	public User(Integer id) {
		super();
		this.id = id;
	}

	public User(Integer id, String name, String password, Integer flag, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.flag = flag;
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", name=" + name +
        ", password=" + password +
        ", flag=" + flag +
        ", role=" + role +
        "}";
    }
}
