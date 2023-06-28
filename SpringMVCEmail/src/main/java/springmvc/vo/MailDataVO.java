package springmvc.vo;

import java.util.Date;

public class MailDataVO {
	private Date TriggeredOn;
	private String message;
	public Date getTriggeredOn() {
		return TriggeredOn;
	}
	public void setTriggeredOn(Date triggeredOn) {
		TriggeredOn = triggeredOn;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
