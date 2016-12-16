package exception;

public class WebServerException extends Exception{

	private static final long serialVersionUID = 5690079245207915366L;
	
	public WebServerException(String string) {
		super(string);
	}
	
	public WebServerException(){
		super();
	}
}
