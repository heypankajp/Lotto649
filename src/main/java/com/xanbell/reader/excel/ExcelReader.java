package com.xanbell.reader.excel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVWriter;
import com.xanbell.reader.excel.entity.FrequencyMap;
import com.xanbell.reader.excel.entity.ReportRow;
import com.xanbell.reader.excel.entity.LottoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ExcelReader {

	/**
	 * TODO Move these to properties file
	 */
	private static final String FILE_NAME = "/Users/pankajpathak/temp/649_results.xlsx";
	private static final String OUT_FILE_NAME = "/Users/pankajpathak/temp/649_Out_";
	
	private static List<String> headerKeywordsList = Arrays.asList("#Draw Date","#1","#2","#3","#4","#5","#6","#Bonus");

	//private static final String transectionDateFormat = "dd/MM/yyyy";	
	private static final String strDrawDateFormat = "EEE, MMM dd, yyyy"; // Sat, Jun 10, 2017
	private static SimpleDateFormat drawDateFormat = new SimpleDateFormat(strDrawDateFormat);
	private static String [] csvHeader_Two = "Number#2,Ocurrances#2".split(",");
	private static String [] csvHeader_Five = "Number#5,Ocurrances#5".split(",");
	private static String [] csvHeader = null;
	private static String fiveYearSheetNamePattern = "649_5Year";
	private static String twoYearSheetNamePattern = "2 Year";
	
	
	private static List<String> drawDateSynonymsList = Arrays.asList( "Date", "#Draw Date", "Draw Date","#DrawDate","DrawDate" );
	private static List<String> firstColSynonymsList = Arrays.asList( "#1", "1", "First" );
	private static List<String> secondColSynonymsList = Arrays.asList( "#2", "2", "Second" );	
	private static List<String> thirdColSynonymsList = Arrays.asList( "#3", "3", "Third" );
	private static List<String> forthColSynonymsList = Arrays.asList( "#4", "4", "Forth" );
	private static List<String> fifthColSynonymsList =Arrays.asList( "#5", "5", "Fifth" );
	private static List<String> sixthColSynonymsList =Arrays.asList( "#6", "6", "Sixth" );
	private static List<String> bonusColSynonymsList = Arrays.asList( "#Bonus", "Bonus", "7","#7","Seventh" );

	
	public static void main(String[] args) {

		try {

			Iterator<Row> iterator = null;
			Map<String, Integer> headerMap = null;
			List<ReportRow> reportColList = null;
			Sheet transectionSheet = null;
			String transectionSheetName = null;
			Workbook workbook = null;
			List <String > headerDataList = null ;
			
			FrequencyMap masterFrequencyMap = new FrequencyMap();
			
			
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			workbook = new XSSFWorkbook(excelFile);
			transectionSheetName = getTwoYearSheetName(workbook);
			if (transectionSheetName == null) 
				{
					System.out.println("Transection Sheet Not Found in the uploded Excel.");
					System.exit(0);
				}
				
			transectionSheet = workbook.getSheet(transectionSheetName);
			iterator = transectionSheet.iterator();
			
			List <String> rowValues = null;
			Row currentRow = null;
			String headerRowString = null;
			headerDataList = new ArrayList<String>();
			
			while (iterator.hasNext()) {
				
				currentRow = iterator.next();
				rowValues = getRowValues(currentRow);
				
				// Adding the data to headerDataList to be printed in output reports. 
				//headerRowString = String.join(", ", rowValues);
				//headerDataList.add(headerRowString);
				
				System.out.println(rowValues);
				headerMap = getHeaderMap(rowValues);
				if (headerMap.size() > 0) break;
				
			}// End of while
			
			// Using the same iterator to read the rest of the rows
			reportColList = getReportColumnValues(iterator, headerMap);
			
			//System.out.println(System.lineSeparator()+"UnSorted List ............Total Rows = "+reportColList.size()+System.lineSeparator());
		//	reportColList.stream().forEach(System.out::println);
			// For debug Print the list
			//sortDataReportList(reportColList);
			//System.out.println(System.lineSeparator()+"Sorted List ............Total Rows = "+reportColList.size()+System.lineSeparator());
		//	reportColList.stream().forEach(System.out::println);
			
			masterFrequencyMap.addReportRowList(reportColList);
			LottoUtils.sortFrequencyMap(masterFrequencyMap);
			
			//System.out.println(frequencyMap);
			System.out.println("Master Frequency Map ------------");
			System.out.println(masterFrequencyMap.getMetaFrquencyMap());
			
			Set <Integer> topValueSet = masterFrequencyMap.getMetaFrquencyMap().keySet();
			List<Integer> topValueList = new ArrayList<Integer>(topValueSet);
			// Processing the First iteration
		
			
			if (transectionSheetName.contains("2"))
				csvHeader = csvHeader_Two;
			else
				csvHeader = csvHeader_Five;
			String[] strArray = new String[12];		
			List <StringBuilder> masterCsvBuilder= new LinkedList<StringBuilder>();
			StringBuilder strBuilder = null;
			for (int idx = 0; idx <=5 ; idx ++)
			{
				Map <Integer, Integer> aMetaFrequencyMap =  getMetaFrequencyMap(reportColList, idx, topValueList);
				int idx2 = 0;
				for (Map.Entry entry : aMetaFrequencyMap.entrySet()) {
					
					if (idx == 0 )
					{
						strBuilder = new StringBuilder(entry.getKey()+","+entry.getValue());
						masterCsvBuilder.add(strBuilder);
					
					}
					else
					{
						strBuilder = masterCsvBuilder.get(idx2);
						strBuilder.append(",,"+entry.getKey()+","+entry.getValue());
						masterCsvBuilder.set(idx2,strBuilder);
					}
					
					//System.out.println(masterCsvBuilder.size());
					
					idx2++;
				//	writer.writeNext(strArray);
				}
				//writer.close();
				
				//System.out.println(masterCsvBuilder.size());
				//System.out.println(masterCsvBuilder);
				System.out.println( "Data Fille "+ transectionSheetName+"  Index No #  " +idx  +" Top Value =  "+topValueList.get(idx));
				System.out.println(aMetaFrequencyMap);
			}
			write(masterCsvBuilder);
			
				
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	} // End of Main

	public static void write(List <StringBuilder> masterCsvBuilder)
	{
	     
	     
	    
	     try (FileWriter fw = new FileWriter("/Users/pankajpathak/temp/my.csv"))
	     {
	    	 fw.append( new StringBuilder("No#1,Count#1,,No#2,Count#2,,No#3,Count#3,,No#4,Count#4,,No#5,Count#5\n\n"));
	    	 for (StringBuilder row : masterCsvBuilder)
		     {
	    		// System.out.println(row);
	    		 fw.append(row+"\n");
		     }
	     }
	     catch (IOException e)
	     {
	         e.printStackTrace();
	     }
	}
	
	/**
	 * Returns the 2 Year sheet name
	 * @param aWorkbook
	 * @return
	 */
	public static String getTwoYearSheetName(Workbook aWorkbook)
	{
		for (int i=0; i<aWorkbook.getNumberOfSheets(); i++) {
			//System.out.println(aWorkbook.getSheetName(i));
		    if (aWorkbook.getSheetName(i).contains(fiveYearSheetNamePattern)) 
		    	return aWorkbook.getSheetName(i);
		    
		}
		return null;
	}
	/**
	 * Method to get the header index from the excel.
	 * @param rowValues
	 * @return
	 */
	public static Map<String, Integer> getHeaderMap(List<String> rowValues) {

		Map<String, Integer> headerMap = new HashMap<String, Integer>(); // Create
																			// map

		Set<String> headerKeywordsSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		headerKeywordsSet.addAll(headerKeywordsList);
		// Add all values of list2 in a case insensitive collection
		Set<String> rowValuesSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		rowValuesSet.addAll(rowValues);
		rowValuesSet.retainAll(headerKeywordsList);
		//System.out.println("Header Found" + rowValuesSet);

		if (rowValuesSet.size() > 2) // Checking for header
			for (int i = 0; i < rowValues.size(); i++) {
				headerMap.put(rowValues.get(i), i);
			}

		return headerMap;
	}// End of checkHeader

	/**
	 * Method to get the column values for the given header
	 * @param iterator
	 * @param headerMap
	 * @return
	 */
	public static List<ReportRow>  getReportColumnValues(Iterator<Row> iterator, Map<String, Integer> headerMap) {

		
		List<ReportRow> listOfDataFromReport = null;
		ReportRow rr = null;
		Row currentRow = null;
		int idxDrawDate = -1;
		int idxFirstCol = -1;
		int idxSecondCol = -1;
		int idxForthCol = -1;
		int idxThirdCol = -1;
		int idxFifthCol = -1;
		int idxSixthCol = -1;
		int idxBonusCol = -1;
	
		
		
		listOfDataFromReport = new ArrayList<ReportRow>();
		while (iterator.hasNext()) {

			currentRow = iterator.next();
			rr = new ReportRow(); // Data structure to hold the data from the xls file.
											
			String drawDateHeaderName = drawDateSynonymsList.stream().filter((synonyms) -> headerMap.get(synonyms) != null).findAny().orElse(null); 
			//System.out.println("--------------------------"+drawDateHeaderName);
			idxDrawDate = headerMap.get(drawDateHeaderName);
			String firstColHeaderName = firstColSynonymsList.stream().filter((synonyms) -> headerMap.get(synonyms) != null).findAny().orElse(null); 
			//System.out.println("--------------------------"+securityHeaderName);
			idxFirstCol = headerMap.get(firstColHeaderName);
			String secondColHeaderName = secondColSynonymsList.stream().filter((synonyms) -> headerMap.get(synonyms) != null).findAny().orElse(null);
			idxSecondCol = headerMap.get(secondColHeaderName);
			String forthColHeaderName = forthColSynonymsList.stream().filter((synonyms) -> headerMap.get(synonyms) != null).findAny().orElse(null);
			idxForthCol = headerMap.get(forthColHeaderName);
			String thirdColHeaderName = thirdColSynonymsList.stream().filter((synonyms) -> headerMap.get(synonyms) != null).findAny().orElse(null);
			idxThirdCol = headerMap.get(thirdColHeaderName);
			String fifthRateHeaderName = fifthColSynonymsList.stream().filter((synonyms) -> headerMap.get(synonyms) != null).findAny().orElse(null);
			idxFifthCol = headerMap.get(fifthRateHeaderName);			
			String sixthColHeaderName = sixthColSynonymsList.stream().filter((synonyms) -> headerMap.get(synonyms) != null).findAny().orElse(null);
			idxSixthCol = headerMap.get(sixthColHeaderName);
			String bonusColHeaderName = bonusColSynonymsList.stream().filter((synonyms) -> headerMap.get(synonyms) != null).findAny().orElse(null);
			idxBonusCol = headerMap.get(bonusColHeaderName);
			
			Cell drawDateCell = currentRow.getCell(idxDrawDate);
			
			// Break from the loop if date field is blank
			if ( drawDateCell.getCellTypeEnum() == CellType.BLANK) break;
					
				
	        Cell firstColCell = currentRow.getCell(idxFirstCol);
			Cell secondColCell = currentRow.getCell(idxSecondCol);
			Cell thirdColCell = currentRow.getCell(idxThirdCol);
			Cell forthColCell = currentRow.getCell(idxForthCol);
			Cell fifthRateCell = currentRow.getCell(idxFifthCol);
			Cell sixthRateCell = currentRow.getCell(idxSixthCol);
			Cell bonusCell = currentRow.getCell(idxBonusCol);
		
			// Read the Draw Date
			try {
				if (drawDateCell.getCellTypeEnum() == CellType.NUMERIC) {
					if (DateUtil.isCellDateFormatted(drawDateCell)) {
						rr.setDrawDate(drawDateCell.getDateCellValue());
					} else {

						rr.setDrawDate(HSSFDateUtil.getJavaDate(drawDateCell.getNumericCellValue()));
					}
				} else {

					rr.setDrawDate(drawDateFormat.parse(drawDateCell.getStringCellValue()));
				}
			} catch (ParseException e) {
				// TODO log
				// If there is exception in reading Date break from the loop
				e.printStackTrace();
				break;

			}
			
			
			rr.setFirstCol((int)firstColCell.getNumericCellValue());
			rr.setSecondCol((int)secondColCell.getNumericCellValue());
			rr.setThirdCol((int)thirdColCell.getNumericCellValue());
			rr.setForthCol((int) forthColCell.getNumericCellValue());
			rr.setFifthCol((int)fifthRateCell.getNumericCellValue());
			rr.setSixthCol((int)sixthRateCell.getNumericCellValue());
			rr.setBonusCol((int)bonusCell.getNumericCellValue());
			
			listOfDataFromReport.add(rr);
			
			

		}
		
		return listOfDataFromReport;
	}
	
	/**
	 * Method to sort the rows 1. Security Name   2. TransectionDate
	 * @param rowList
	 * @return
	 */
	public static List<ReportRow> sortDataReportList(List<ReportRow> rowList)
	{
		
		//rowList.sort(Comparator.comparing(ReportRow::getDrawDate).thenComparing(ReportRow::getFirstCol));
		rowList.sort(Comparator.comparing(ReportRow::getDrawDate));
		
		return rowList;
	}
	
	/**
	 * Method to get the Row Values from the excel row
	 * @param aRow
	 * @return
	 */
	public static List<String> getRowValues(Row aRow) {
		List<String> columnDataList = null;
		columnDataList = new ArrayList<String>();

		Iterator<Cell> cellIterator = aRow.iterator();

		Cell currentCell = null;
		while (cellIterator.hasNext()) {

			currentCell = cellIterator.next();
			
			if (currentCell.getCellTypeEnum() == CellType.STRING) {
				columnDataList.add(currentCell.getStringCellValue());

			} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
				columnDataList.add(currentCell.getNumericCellValue() + "");
			}

		}
		return columnDataList;
	}// End of getRowValue
	
	public static Map <Integer, Integer> getMetaFrequencyMap(List<ReportRow> reportDataList , int index, List <Integer> topValueList)
	{
		
		FrequencyMap indexFrequencyMap = new FrequencyMap();
		List<ReportRow> indexReportRowList = reportDataList.stream()
		    .filter(x -> x.anyEquals( topValueList.get(index)))
		    .collect(Collectors.toList());
		System.out.println("\n Index No # "+ index +"  Top Value =  " +topValueList.get(index)+" List Size =  "+ indexReportRowList.size());
		//indexReportRowList.stream().forEach(System.out::println);
	
		indexFrequencyMap.addReportRowList(indexReportRowList);
		LottoUtils.sortFrequencyMap(indexFrequencyMap);
		
		return indexFrequencyMap.getMetaFrquencyMap();
	}
	
}// End of Class.