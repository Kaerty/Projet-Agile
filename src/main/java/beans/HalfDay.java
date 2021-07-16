package beans;

import java.sql.Date;

public class HalfDay {

	private int idHd;
	private Date date;
	private boolean morning;
	
	public int getIdHd() {
		return idHd;
	}
	public void setIdHd(int idHd) {
		this.idHd = idHd;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isMorning() {
		return morning;
	}
	public void setMorning(boolean morning) {
		this.morning = morning;
	}
	
	
}
