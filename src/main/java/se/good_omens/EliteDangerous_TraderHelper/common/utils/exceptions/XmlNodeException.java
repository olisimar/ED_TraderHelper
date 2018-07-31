package se.good_omens.EliteDangerous_TraderHelper.common.utils.exceptions;

public class XmlNodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public XmlNodeException() {
		super("No message available");
	}
	
	public XmlNodeException(String message) {
		super(message);
	}
	
	public XmlNodeException(Throwable exception) {
		super(exception);
	}
}
