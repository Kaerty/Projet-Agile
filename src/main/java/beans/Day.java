package beans;

import java.sql.Date;

public class Day {

	private int idDay;
	private Date date;
	private String activity;
	
	public int getIdDay() {
		return idDay;
	}
	public void setIdDay(int idDay) {
		this.idDay = idDay;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	
}
