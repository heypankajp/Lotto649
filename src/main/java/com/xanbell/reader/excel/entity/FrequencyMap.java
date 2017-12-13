package com.xanbell.reader.excel.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FrequencyMap {

	Map<Integer, Integer> firstColMap = null;
	Map<Integer, Integer> secondColMap = null;
	Map<Integer, Integer> thirdColMap = null;
	Map<Integer, Integer> forthColMap = null;
	Map<Integer, Integer> fifthColMap = null;
	Map<Integer, Integer> sixthColMap = null;
	Map<Integer, Integer> bonusColMap = null;
	Map<Integer, Integer> metaFrquencyMap = null;
	List <ReportRow> dataList = null;
	
	

	public FrequencyMap ()
	{
		firstColMap = new LinkedHashMap <Integer, Integer>();
		secondColMap = new LinkedHashMap <Integer, Integer>();
		thirdColMap = new LinkedHashMap <Integer, Integer>();
		forthColMap = new LinkedHashMap <Integer, Integer>();
		fifthColMap = new LinkedHashMap <Integer, Integer>();
		sixthColMap = new LinkedHashMap <Integer, Integer>();
		bonusColMap = new LinkedHashMap <Integer, Integer>();
		metaFrquencyMap = new LinkedHashMap <Integer, Integer>();
	}
	
	public List<ReportRow> getDataList() {
		return dataList;
	}

	public void setDataList(List<ReportRow> dataList) {
		this.dataList = dataList;
	}
	
	public void setFirstColMap(Map<Integer, Integer> firstColMap) {
		this.firstColMap = firstColMap;
	}

	public void setSecondColMap(Map<Integer, Integer> secondColMap) {
		this.secondColMap = secondColMap;
	}

	public void setThirdColMap(Map<Integer, Integer> thirdColMap) {
		this.thirdColMap = thirdColMap;
	}

	public void setForthColMap(Map<Integer, Integer> forthColMap) {
		this.forthColMap = forthColMap;
	}

	public void setFifthColMap(Map<Integer, Integer> fifthColMap) {
		this.fifthColMap = fifthColMap;
	}

	public void setSixthColMap(Map<Integer, Integer> sixthColMap) {
		this.sixthColMap = sixthColMap;
	}

	public void setBonusColMap(Map<Integer, Integer> bonusColMap) {
		this.bonusColMap = bonusColMap;
	}
	
	public Map<Integer, Integer> getFirstColMap() {
		return firstColMap;
	}
	public Map<Integer, Integer> getSecondColMap() {
		return secondColMap;
	}
	public Map<Integer, Integer> getThirdColMap() {
		return thirdColMap;
	}
	public Map<Integer, Integer> getForthColMap() {
		return forthColMap;
	}
	public Map<Integer, Integer> getFifthColMap() {
		return fifthColMap;
	}
	public Map<Integer, Integer> getSixthColMap() {
		return sixthColMap;
	}
	public Map<Integer, Integer> getBonusColMap() {
		return bonusColMap;
	}


	public Map<Integer, Integer> getMetaFrquencyMap() {
		return metaFrquencyMap;
	}

	public void setMetaFrquencyMap(Map<Integer, Integer> metaFrquencyMap) {
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
		add(firstColMap,row.getFirstCol());
		add(secondColMap,row.getSecondCol());
		add(thirdColMap,row.getThirdCol());
		add(forthColMap,row.getForthCol());
		add(fifthColMap,row.getFifthCol());
		add(sixthColMap,row.getSixthCol());
		add(bonusColMap,row.getBonusCol());
		
		add(metaFrquencyMap,row.getFirstCol());
		add(metaFrquencyMap,row.getSecondCol());
		add(metaFrquencyMap,row.getThirdCol());
		add(metaFrquencyMap,row.getForthCol());
		add(metaFrquencyMap,row.getFifthCol());
		add(metaFrquencyMap,row.getSixthCol());
		add(metaFrquencyMap,row.getBonusCol());
		
		
	}
	
	public void add (Map <Integer, Integer> colMap, int element)
	{
		Integer value = colMap.get(element);
		if (value == null) {
			
			colMap.put(element, 1);
		}  else {
			colMap.put(element, value+1);
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
