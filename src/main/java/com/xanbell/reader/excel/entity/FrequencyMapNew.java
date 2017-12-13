package com.xanbell.reader.excel.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.xanbell.reader.excel.entity.ResultValueList;

public class FrequencyMapNew {

	Map<Integer, ResultValueList> firstColMap = null;
	Map<Integer, ResultValueList> secondColMap = null;
	Map<Integer, ResultValueList> thirdColMap = null;
	Map<Integer, ResultValueList> forthColMap = null;
	Map<Integer, ResultValueList> fifthColMap = null;
	Map<Integer, ResultValueList> sixthColMap = null;
	Map<Integer, ResultValueList> bonusColMap = null;
	Map<Integer, ResultValueList> metaFrquencyMap = null;
	List <ReportRow> dataList = null;
	
	

	public FrequencyMapNew ()
	{
		firstColMap = new LinkedHashMap <Integer, ResultValueList>();
		secondColMap = new LinkedHashMap <Integer, ResultValueList>();
		thirdColMap = new LinkedHashMap <Integer, ResultValueList>();
		forthColMap = new LinkedHashMap <Integer, ResultValueList>();
		fifthColMap = new LinkedHashMap <Integer, ResultValueList>();
		sixthColMap = new LinkedHashMap <Integer, ResultValueList>();
		bonusColMap = new LinkedHashMap <Integer, ResultValueList>();
		metaFrquencyMap = new LinkedHashMap <Integer, ResultValueList>();
	}
	
	public List<ReportRow> getDataList() {
		return dataList;
	}

	public void setDataList(List<ReportRow> dataList) {
		this.dataList = dataList;
	}
	
	public void setFirstColMap(Map<Integer, ResultValueList> firstColMap) {
		this.firstColMap = firstColMap;
	}

	public void setSecondColMap(Map<Integer, ResultValueList> secondColMap) {
		this.secondColMap = secondColMap;
	}

	public void setThirdColMap(Map<Integer, ResultValueList> thirdColMap) {
		this.thirdColMap = thirdColMap;
	}

	public void setForthColMap(Map<Integer, ResultValueList> forthColMap) {
		this.forthColMap = forthColMap;
	}

	public void setFifthColMap(Map<Integer, ResultValueList> fifthColMap) {
		this.fifthColMap = fifthColMap;
	}

	public void setSixthColMap(Map<Integer, ResultValueList> sixthColMap) {
		this.sixthColMap = sixthColMap;
	}

	public void setBonusColMap(Map<Integer, ResultValueList> bonusColMap) {
		this.bonusColMap = bonusColMap;
	}
	
	public Map<Integer, ResultValueList> getFirstColMap() {
		return firstColMap;
	}
	public Map<Integer, ResultValueList> getSecondColMap() {
		return secondColMap;
	}
	public Map<Integer, ResultValueList> getThirdColMap() {
		return thirdColMap;
	}
	public Map<Integer, ResultValueList> getForthColMap() {
		return forthColMap;
	}
	public Map<Integer, ResultValueList> getFifthColMap() {
		return fifthColMap;
	}
	public Map<Integer, ResultValueList> getSixthColMap() {
		return sixthColMap;
	}
	public Map<Integer, ResultValueList> getBonusColMap() {
		return bonusColMap;
	}


	public Map<Integer, ResultValueList> getMetaFrquencyMap() {
		return metaFrquencyMap;
	}

	public void setMetaFrquencyMap(Map<Integer, ResultValueList> metaFrquencyMap) {
		this.metaFrquencyMap = metaFrquencyMap;
	}

	
	/**
	 * This method add the ReportRow list in the Map.
	 * @param reportRowList
	 * @param element
	 */
	public void addReportRowList (List <ReportRow> reportRowList)
	{
		dataList = reportRowList;
		reportRowList.forEach( (reportRow) -> addReportRow(reportRow) );
	}

	/**
	 * This method add the element in the Map.
	 * @param ReportRow
	 * @param element
	 */
	public void addReportRow (ReportRow row)
	{
		add(firstColMap,row.getFirstCol(), row.getDrawDate());
		add(secondColMap,row.getSecondCol(),row.getDrawDate());
		add(thirdColMap,row.getThirdCol(),row.getDrawDate());
		add(forthColMap,row.getForthCol(),row.getDrawDate());
		add(fifthColMap,row.getFifthCol(),row.getDrawDate());
		add(sixthColMap,row.getSixthCol(),row.getDrawDate());
		add(bonusColMap,row.getBonusCol(),row.getDrawDate());
		
		add(metaFrquencyMap,row.getFirstCol(),row.getDrawDate());
		add(metaFrquencyMap,row.getThirdCol(),row.getDrawDate());
		add(metaFrquencyMap,row.getForthCol(),row.getDrawDate());
		add(metaFrquencyMap,row.getFifthCol(),row.getDrawDate());
		add(metaFrquencyMap,row.getSixthCol(),row.getDrawDate());
		add(metaFrquencyMap,row.getBonusCol(),row.getDrawDate());
		
		
	}
	
	public void add (Map <Integer, ResultValueList> colMap, int element, Date drawDate )
	{
		ResultValueList value = colMap.get(element);
		if (value == null) {
			value = new ResultValueList(drawDate);
			colMap.put(element,value );
		}  else {
			
			value.incrementCounter(drawDate);
			colMap.put(element, value);
		}
	
	}
	
	


	@Override
	public String toString() {
		return "\nFrequencyMap [\nfirstColMap=" + firstColMap + "\nsecondColMap=" + secondColMap + "\nthirdColMap="
				+ thirdColMap + "\nforthColMap=" + forthColMap + "\nfifthColMap=" + fifthColMap + "\nsixthColMap="
				+ sixthColMap + "\nbonusColMap=" + bonusColMap + "\n]";
	}

	
//	Map<String, Integer> wordMap = new HashMap<String, Integer>()
}
