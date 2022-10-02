package commons.rest;

public class JsonMessage {
	private int statusCode;
	private boolean success;
	private String message;
	private String resultCode; // API 관리 코드 
	private Object data; // 응답 데이터 
	
	
	public JsonMessage() {}
	
	public JsonMessage(String message) {
		this(200, true, message);
	}
	
	public JsonMessage(int statusCode, boolean success, String message) {
		this(statusCode, success, message, null, null);
	}
	
	public JsonMessage(int statusCode, boolean success, String message, String resultCode, Object data) {
		this.statusCode = statusCode;
		this.success = success;
		this.message = message;
		this.resultCode = resultCode;
		this.data = data;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
}
