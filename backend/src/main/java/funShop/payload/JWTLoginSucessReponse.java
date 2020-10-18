package funShop.payload;

public class JWTLoginSucessReponse {
	private Long id;
	private String username;
	private String fullName;
	private boolean admin;
	private String token;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JWTLoginSucessReponse(Long id, String username, String fullName, boolean admin, String token) {
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.admin = admin;
		this.token = token;
	}

	@Override
	public String toString() {
		return "JWTLoginSucessReponse [id=" + id + ", username=" + username + ", fullName=" + fullName + ", admin="
				+ admin + ", token=" + token + "]";
	}

}