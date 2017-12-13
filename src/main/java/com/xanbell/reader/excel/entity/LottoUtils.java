package com.xanbell.reader.excel.entity;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LottoUtils {

	
	
	public static void sortFrequencyMapNew(FrequencyMapNew newFrequencyMap)
	{
		
		
		
		newFrequencyMap.setFirstColMap(sortResultMapByValue(newFrequencyMap.firstColMap));
		newFrequencyMap.setSecondColMap(sortResultMapByValue(newFrequencyMap.secondColMap));
		newFrequencyMap.setThirdColMap(sortResultMapByValue(newFrequencyMap.thirdColMap));
		newFrequencyMap.setForthColMap(sortResultMapByValue(newFrequencyMap.forthColMap));
		newFrequencyMap.setFifthColMap(sortResultMapByValue(newFrequencyMap.fifthColMap));
		newFrequencyMap.setSixthColMap(sortResultMapByValue(newFrequencyMap.sixthColMap));
		newFrequencyMap.setBonusColMap(sortResultMapByValue(newFrequencyMap.bonusColMap));
		newFrequencyMap.setMetaFrquencyMap(sortResultMapByValue(newFrequencyMap.metaFrquencyMap));
	}
	
	public static void sortFrequencyMap(FrequencyMap uFrequencyMap)
	{
		
		
		
		uFrequencyMap.setFirstColMap(sortMapByValue(uFrequencyMap.firstColMap));
		uFrequencyMap.setSecondColMap(sortMapByValue(uFrequencyMap.secondColMap));
		uFrequencyMap.setThirdColMap(sortMapByValue(uFrequencyMap.thirdColMap));
		uFrequencyMap.setForthColMap(sortMapByValue(uFrequencyMap.forthColMap));
		uFrequencyMap.setFifthColMap(sortMapByValue(uFrequencyMap.fifthColMap));
		uFrequencyMap.setSixthColMap(sortMapByValue(uFrequencyMap.sixthColMap));
		uFrequencyMap.setBonusColMap(sortMapByValue(uFrequencyMap.bonusColMap));
		uFrequencyMap.setMetaFrquencyMap(sortMapByValue(uFrequencyMap.metaFrquencyMap));
	}
	
	public static Map<Integer, Integer> sortMapByKey(Map<Integer, Integer> unsortMap)
	{
		Map<Integer, Integer> ascSortedMap = new TreeMap<Integer, Integer>();
		ascSortedMap.putAll(unsortMap);
		
	
		// Forcing the descending order by creating our own comparator then passing it to the sorted map at creation time
		System.out.println("\nSorted Map in descending order......");
		Map<Integer,Integer> desSortedMap = new TreeMap <Integer, Integer>(
				new Comparator<Integer>() {
	
					@Override
					public int compare(Integer o1, Integer o2) {
						return o2.compareTo(o1);
					}
				});
		desSortedMap.putAll(unsortMap);
		
		return desSortedMap;
	}
	
	 public static Map<Integer, ResultValueList> sortResultMapByValue(Map<Integer, ResultValueList> unsortedMap) {

	        // 1. Convert Map to List of Map
	        List<Map.Entry<Integer, ResultValueList>> list =
	                new LinkedList<Map.Entry<Integer, ResultValueList>>(unsortedMap.entrySet());

	        // 2. Sort list with Collections.sort(), provide a custom Comparator
	        //    Try switch the o1 o2 position for a different order
	        Collections.sort(list, new Comparator<Map.Entry<Integer, ResultValueList>>() {
	            public int compare(Map.Entry<Integer, ResultValueList> o1,
	                               Map.Entry<Integer, ResultValueList> o2) {
	                return (o2.getValue()).getCounter().compareTo(o1.getValue().getCounter());
	            }
	        });

	        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
	        Map<Integer, ResultValueList> sortedResultMap = new LinkedHashMap<Integer, ResultValueList>();
	        for (Map.Entry<Integer, ResultValueList> entry : list) {
	        	sortedResultMap.put(entry.getKey(), entry.getValue());
	        }

	        /*
	        //classic iterator example
	        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
	            Map.Entry<String, Integer> entry = it.next();
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }*/


	        return sortedResultMap;
	    }

	 public static Map<Integer, Integer> sortMapByValue(Map<Integer, Integer> unsortMap) {

	        // 1. Convert Map to List of Map
	        List<Map.Entry<Integer, Integer>> list =
	                new LinkedList<Map.Entry<Integer, Integer>>(unsortMap.entrySet());

	        // 2. Sort list with Collections.sort(), provide a custom Comparator
	        //    Try switch the o1 o2 position for a different order
	        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
	            public int compare(Map.Entry<Integer, Integer> o1,
	                               Map.Entry<Integer, Integer> o2) {
	                return (o2.getValue()).compareTo(o1.getValue());
	            }
	        });

	        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
	        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
	        for (Map.Entry<Integer, Integer> entry : list) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        /*
	        //classic iterator example
	        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
	            Map.Entry<String, Integer> entry = it.next();
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }*/


	        return sortedMap;
	    }
	 

/**
 * Prints the map.
 *
 * @param map the map
 */
public static void printMap(Map<Integer, Integer> map) {
	for (Map.Entry entry : map.entrySet()) {
		System.out.println("Key : " + entry.getKey() + " Value : "
				+ entry.getValue());
	}
}

public static String[] asStrings(Object... objArray) {
    String[] strArray = new String[objArray.length];
    for (int i = 0; i < objArray.length; i++)
        strArray[i] = String.valueOf(objArray[i]);
    return strArray;
}
public static List<String> asStringList(Object... objArray) {
    List <String>strList = new LinkedList<String>();
    for (int i = 0; i < objArray.length; i++)
    	strList.add(String.valueOf(objArray[i]));
    return strList;
}

public static List<String[]> asStringArratList(Object... objArray) {
    List <String[]>strList = new LinkedList<String[]>();
    String[] strArray = new String[objArray.length];
    for (int i = 0; i < objArray.length; i++)
    	 strArray[i] = String.valueOf(objArray[i]);
    	strList.add(strArray);
    return strList;
}
public static void csvToXLSX(String csvFileAddress, String xlsxFileAddress, String sheetName) {
    try {
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet(sheetName);
        String currentLine=null;
        int RowNum=0;
        BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
        while ((currentLine = br.readLine()) != null) {
            String str[] = currentLine.split(",");
            RowNum++;
            XSSFRow currentRow=sheet.createRow(RowNum);
            for(int i=0;i<str.length;i++){
                currentRow.createCell(i).setCellValue(str[i]);
            }
        }

        FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFileAddress);
        workBook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("Done");
    } catch (Exception ex) {
        System.out.println(ex.getMessage()+"Exception in try");
    }
}


/**
 * Get a diff between two dates
 * @param date1 the oldest date
 * @param date2 the newest date
 * @param timeUnit the unit in which you want the diff
 * @return the diff value, in the provided unit
 */
public static int getDateDiffinDays(Date date1, Date date2) {
    long diffInMillies = date2.getTime() - date1.getTime();
    return (int)(diffInMillies/(1000*24*60*60));
}

}
