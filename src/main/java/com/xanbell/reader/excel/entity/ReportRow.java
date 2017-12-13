package com.xanbell.reader.excel.entity;


import java.util.Date;

public class ReportRow {
	
	private boolean isLottoMax = false;
	private Date drawDate = null;
	private int firstCol  = 0;
	private int secondCol = 0;
	private int thirdCol  = 0;
	
	private int forthCol = 0;
	private int fifthCol = 0;
	private int sixthCol = 0;
	private int seventhCol = 0;
	private int bonusCol = 0;
	
	
	
	public boolean isLottoMax() {
		return isLottoMax;
	}



	public void setLottoMax(boolean isLottoMax) {
		this.isLottoMax = isLottoMax;
	}



	public int getSeventhCol() {
		return seventhCol;
	}



	public void setSeventhCol(int seventhCol) {
		this.seventhCol = seventhCol;
	}



	public Date getDrawDate() {
		return drawDate;
	}



	public void setDrawDate(Date drawDate) {
		this.drawDate = drawDate;
	}



	public int getFirstCol() {
		return firstCol;
	}



	public void setFirstCol(int firstCol) {
		this.firstCol = firstCol;
	}



	public int getSecondCol() {
		return secondCol;
	}



	public void setSecondCol(int secondCol) {
		this.secondCol = secondCol;
	}



	public int getThirdCol() {
		return thirdCol;
	}



	public void setThirdCol(int thirdCol) {
		this.thirdCol = thirdCol;
	}



	public int getForthCol() {
		return forthCol;
	}



	public void setForthCol(int forthCol) {
		this.forthCol = forthCol;
	}



	public int getFifthCol() {
		return fifthCol;
	}



	public void setFifthCol(int fifthCol) {
		this.fifthCol = fifthCol;
	}



	public int getSixthCol() {
		return sixthCol;
	}



	public void setSixthCol(int sixthCol) {
		this.sixthCol = sixthCol;
	}



	public int getBonusCol() {
		return bonusCol;
	}



	public void setBonusCol(int bonusCol) {
		this.bonusCol = bonusCol;
	}


	 
	  public boolean anyEquals(int v) {
	        
		  return firstCol == v || secondCol == v || thirdCol == v 
				  || forthCol == v || fifthCol == v || sixthCol == v|| seventhCol == v ||bonusCol== v;

	        
	  }

	@Override
	public String toString() {
		return "ReportRow [Draw Date=" + drawDate  + ", First Col="
				+ firstCol+ ", Second Col=" + secondCol +", Third Col=" + thirdCol 
				+  ", Forth Col=" + forthCol + ", Fifth Col=" + fifthCol 
				+ ", Sixth Col=" + sixthCol + ( isLottoMax ? ", Seventh Col=" + seventhCol:"") +", Bonus Col=" + bonusCol + "]";
	}
	
}// End of Class
