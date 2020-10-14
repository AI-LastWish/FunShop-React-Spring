package funShop.exceptions;

public class ExceptionRes {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExceptionRes(String message) {
		this.message = message;
	}
}
