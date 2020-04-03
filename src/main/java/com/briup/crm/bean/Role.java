package com.briup.crm.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "role")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String description;

	private Integer flag;

	/**
	 * mappedBy = "role" 中的role为User中和表相关联的属性
	 * cascade = CascadeType.ALL 表示绑定级联的所有操作,这个也是级联查询的关键
	 * fetch = FetchType.EAGER 表示需要立即加载，默认为懒加载
	 */
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<User> user;

	
	public Role() {
	}
	
	public Role(Integer id) {
		super();
		this.id = id;
	}


	/*public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}*/

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", name=" + name + ", description=" + description + ", flag=" + flag + "}";
	}

}
