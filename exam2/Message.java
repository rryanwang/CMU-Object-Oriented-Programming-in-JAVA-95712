package exam2;

public abstract class Message implements Encryptable{
	public String message;
	public String key;
	
	public void setMessage(String message, String key) {
		this.message = message;
		this.key = key;
	}
}
