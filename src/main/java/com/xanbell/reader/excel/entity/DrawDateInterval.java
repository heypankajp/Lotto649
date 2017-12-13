package com.xanbell.reader.excel.entity;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DrawDateInterval {
	
	private Date drawDate = null;
	private int interval  = 0;
	
	
	public DrawDateInterval(Date drawDate, int interval) {
		super();
		this.drawDate = drawDate;
		this.interval = interval;
	}



	public Date getDrawDate() {
		return drawDate;
	}



	public void setDrawDate(Date drawDate) {
		this.drawDate = drawDate;
	}



	public int getInterval() {
		return interval;
	}



	public void setInterval(int interval) {
		this.interval = interval;
	}



	@Override
	public String toString() {
		return "[drawDate=" + new SimpleDateFormat("dd/MMM/yyyy").format(drawDate) + ", interval=" + interval + "]";
	}


	
}// End of Class
