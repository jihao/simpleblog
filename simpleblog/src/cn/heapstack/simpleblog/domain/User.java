package cn.heapstack.simpleblog.domain;


public class User {
	
	private String userId;
	
	private String password;

	/**
     * 
     */
	private String email;

	/**
	 * Used for post article or comments
	 */
	private String nickName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "User:" + " userId : " + userId + " password : " + password +
			"nickName : " + nickName + " email : " + email + "\n";
	}

}
