package funShop.domain.dto;

import funShop.domain.User;

public class UserDTO {

	private Long id;
	private String username;
	private String fullName;
	private boolean admin = false;

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
	
	public static UserDTO userToUserDTO(User user) {
		var userDto = new UserDTO();

		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setFullName(user.getFullName());
		userDto.setAdmin(user.isAdmin());
		
		return userDto;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", fullName=" + fullName + ", admin=" + admin + "]";
	}	

}
