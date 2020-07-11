package sba.comps.processfiledates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProcessFileDates {
	
//	DateOfLastProcessedFile<Date> dateValue= new DateOfLastProcessedFile<Date>();
	
	String inputLine = null;
	BufferedReader readFile = null;
	private File lastProcessedFile;
	private String dirWhereOldFileIsLocated = null;
	DateFormat dateFormat = null;
	
public ProcessFileDates(String dirName) {
	dirWhereOldFileIsLocated = dirName;
}


public String getDirWhereOldFileIsLocated() {
	return dirWhereOldFileIsLocated;
} 
	
public ProcessFileDates(String lastFileNamePath, String lastFileName) {
		
		lastProcessedFile = new File(lastFileNamePath + lastFileName);
		dateFormat = new SimpleDateFormat("yyyyMMdd");
		//dateFormat = new SimpleDateFormat("yyyy-MM-dd");
}

public int getCurrentFileDate() throws IOException, ParseException{
	
	int date = 0;
		
		try{
			readFile = new BufferedReader(new FileReader(lastProcessedFile));
	    	inputLine = readFile.readLine();
	    	System.out.println("Current File... " + inputLine);
	    	String todayDayDate = inputLine.substring(33,41);
	    	System.out.println("Current date... " + todayDayDate);
	    	date = Integer.parseInt(todayDayDate);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	readFile.close();
	    }
		return date;
}
	
public int getOldFileDate(String fileName) throws IOException, ParseException{
	
	int date = 0;
	
	Set<String> oldFilesFolder = new HashSet<String>();
	
	File oldDirPathFolderName = new File(getDirWhereOldFileIsLocated());
	String contents[] = oldDirPathFolderName.list();
	
	String contentFinal = null;
	
	for (String content : contents) {
		
		if ( content.startsWith("M") || content.startsWith("m")) {
			contentFinal = content.substring(0, 14).toLowerCase();

		} else {
			contentFinal = content.substring(0, 13).toLowerCase();
		}
		
	if ( contentFinal.equalsIgnoreCase(fileName)) {
		oldFilesFolder.add(content.toLowerCase());
		System.out.println("The files on the set " + oldFilesFolder);
	}
	
}

Iterator<String> iterator = oldFilesFolder.iterator();
	while (iterator.hasNext()) {
	String element = (String) iterator.next();
	//System.out.println("Value of element " + (String) iterator.next());
	System.out.println("Show element ........ " + element);
		try {
			System.out.println("The old folder path " + getDirWhereOldFileIsLocated() + element);
			readFile = new BufferedReader(new FileReader(getDirWhereOldFileIsLocated() + element));
			inputLine = readFile.readLine();
			System.out.println("Old File... " + inputLine);
			String todayDayDate = inputLine.substring(22,30);
			System.out.println("Today date... " + todayDayDate);
			date = Integer.parseInt(todayDayDate); // To fix # 1
			System.out.println("The value of date at this point is #1 " + date);
//			dateValue.push(dateFormat.parse(todayDayDate));
		} catch (IOException ex) {
		    ex.printStackTrace();
		} finally {
			readFile.close();
		}
	}
	System.out.println("The value of date at this point is #2 " + date);
	return date;


}
	
	
//	for (String content : contents) {
//		
//		if ( content.startsWith("M") || content.startsWith("m")) {
//			
//			String value = content.substring(6, 14);
//			
//			if ( value.equals(fileName)) {
//				
//				finalCheck1 = true;
//				
//			}
//		} 
//		
//		if (!(content.startsWith("M") || content.startsWith("m"))){
//			
//			String value = content.substring(5, 13);
//			
//			if ( value.equals(fileName)) {
//				
//				finalCheck2 = true;
//			}
//		}
//	if ( content.substring(0, 13).equalsIgnoreCase(fileName)) {
//		oldFilesFolder.add(content.toLowerCase());
//		System.out.println("The files on the set " + oldFilesFolder);
//	}


	
	
	
	
//	Date date = null;;
//	Date dates = null;
//	
//	
//	Set<String> oldFilesFolder = new HashSet<String>();
//
//	File oldDirPathFolderName = new File(getDirWhereOldFileIsLocated());
//	String contents[] = oldDirPathFolderName.list();
//
//	for (String content : contents) {
//		if ( content.substring(0, 13).equalsIgnoreCase(fileName)) {
//			oldFilesFolder.add(content.toLowerCase());
//			System.out.println("The files on the set " + oldFilesFolder);
//		}
//	}
//	Iterator<String> iterator = oldFilesFolder.iterator();
//		while (iterator.hasNext()) {
//		String element = (String) iterator.next();
//		System.out.println("Show element " + element);
//			try {
//				System.out.println("The old folder path " + getDirWhereOldFileIsLocated() + element);
//				readFile = new BufferedReader(new FileReader(getDirWhereOldFileIsLocated() + element));
//				inputLine = readFile.readLine();
//				System.out.println("Old File... " + inputLine);
//				String todayDayDate = inputLine.substring(22,30);
//				System.out.println("Today date... " + todayDayDate);
//				date = dateFormat.parse(todayDayDate); // To fix # 1
//				System.out.println("The value of date at this point is #1" + date);
////				dateValue.push(dateFormat.parse(todayDayDate));
//			} catch (IOException ex) {
//		    	ex.printStackTrace();
//			}
//		}
//		dates = date;
//	System.out.println("The value of date at this point is #2 " + dates);
//	return dates;


//public static void main(String[] args) throws IOException, ParseException {
//	
//	Date dday = new Date();
//	
//	processedFileDates process = new processedFileDates("C:\\dev-workspace\\EMIS-Comps\\yesterday\\");
//	
//	File files = new File("C:\\dev-workspace\\EMIS-Comps\\yesterday\\");
//	
//	String filenam[] = files.list();
//	
//	for ( String namFile : filenam) {
//		
//		System.out.println("Support info. " +namFile);
//		
//		dday = process.getOldFileDate(namFile);	
//		System.out.println("And the day is " + dday);
//	}
//	
//	
//	
}
