package cn.heapstack.usermanage.datamodel;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

 
@Entity(name="USERS")
@Table(name="USERS")
public class User implements Serializable{
	/**
	 * @UniqueConstraint(columnNames="username")
	 */
	private static final long serialVersionUID = -5571945122552021853L;
	
	private String username;
	private String password;
	
	private int uid;
	private UserInfo userInfo;
	
	@Column(unique=true)
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
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public int getUid() {
		return uid;
		//@GenericGenerator(name="user",strategy=GenerationType.IDENTITY,parameters={@Parameter(name="property",value="user")})
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}	
}
