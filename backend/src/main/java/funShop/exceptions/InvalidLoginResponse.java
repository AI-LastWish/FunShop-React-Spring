package funShop.exceptions;

public class InvalidLoginResponse {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InvalidLoginResponse() {
		this.message = "Invalid Email or Password";
	}

}
