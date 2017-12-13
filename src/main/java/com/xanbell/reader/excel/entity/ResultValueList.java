package com.xanbell.reader.excel.entity;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ResultValueList {
	
	private Date privDrawDate = null;
	private Date lastDrawDate = null;
	private Integer counter = 0;
	private LinkedList<Date> drawDateList = null;
	private LinkedList <Integer> intervalList = null;
	private LinkedList<DrawDateInterval> drawDateIntervalList = null;
	
	public ResultValueList( Date drawDate)
	{
		counter = 1;
		drawDateList = new LinkedList<Date>();
		drawDateList.add(drawDate);
		lastDrawDate = drawDate;
		privDrawDate = drawDate;
		intervalList = new LinkedList<Integer>(); 
		intervalList.add(0); 
		drawDateIntervalList = new LinkedList<DrawDateInterval>();
		drawDateIntervalList.add(new DrawDateInterval(drawDate,LottoUtils.getDateDiffinDays(drawDate,new java.util.Date())  ));
		
	}
	public Date getLastDrawDate() {
		return privDrawDate;
	}



	public void setLastDrawDate(Date drawDate) {
		this.privDrawDate = drawDate;
	}



	public LinkedList<Date> getDrawDateList() {
		return drawDateList;
	}



	public void setDrawDateList(LinkedList<Date> drawDateList) {
		this.drawDateList = drawDateList;
	}



	public LinkedList<Integer> getIntervalList() {
		return intervalList;
	}



	public void setIntervalList(LinkedList<Integer> intervalList) {
		this.intervalList = intervalList;
	}

	


	public Integer getCounter() {
		return counter;
	}



	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	

	public void incrementCounter(Date drawDate) {
		this.counter++;
		intervalList.add(LottoUtils.getDateDiffinDays(drawDate,privDrawDate));
		drawDateList.add(drawDate);
		
		drawDateIntervalList.add(new DrawDateInterval(drawDate,LottoUtils.getDateDiffinDays(drawDate,new java.util.Date()) ));
		// Replace the drawDate with last date
		privDrawDate = drawDate;
	}
	
	public void initlizeCounter() {
		this.counter = 1;
	}
	

	@Override
	public String toString() {
		return " [lastDrawDate=" + new SimpleDateFormat("dd/MMM/yyyy").format(lastDrawDate) + ", counter=" + counter 
				+", Interval= "+LottoUtils.getDateDiffinDays(privDrawDate,new java.util.Date())+ "]";
	}







		
}// End of Class
