package artur.rso.api.users.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class APIResponseModel {
	
	private int httpResponseId;
	private String httpCode;
	private String message;
	private LocalDate date;
	private LocalTime time;
	
	public APIResponseModel() {}

	public APIResponseModel(int httpResponseId, String httpCode, String message, LocalDate date, LocalTime time) {
		this.httpResponseId = httpResponseId;
		this.httpCode = httpCode;
		this.message = message;
		this.date = date;
		this.time = time;
	}

	public int getHttpResponseId() {
		return httpResponseId;
	}

	public void setHttpResponseId(int httpResponseId) {
		this.httpResponseId = httpResponseId;
	}

	public String getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("HTTP Response ID: " + this.httpResponseId + "\n");
		s.append("HTTP CODE: " + this.httpCode + "\n");
		s.append("Message: " + this.message + "\n");
		s.append("Date: " + this.date + "\n");
		s.append("Time: " + this.time + "\n");
		s.append("\n");
		
		return s.toString();
	}
}
