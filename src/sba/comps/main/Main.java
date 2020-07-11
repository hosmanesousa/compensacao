/*
* 	Comps MVP1
* 	Designed by: Hosmane Sousa
* 	Date: 11/07/2020
*
* */

package sba.comps.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import sba.comps.movefilesource2destination.MoveFileSourceToDestination;
import sba.comps.findfiles.FindFiles;
import sba.comps.cleanspecialchars.CleanSpecialChars;
import sba.comps.processfiledates.ProcessFileDates;;

public class Main {
	
public static void main(String[] args) throws ParseException, IOException {
		

		
		String strDateOfNewFiles = null;
		String strDateOfOldFiles = null;
		
		ArrayList<Integer> dateOfNewFilesArr = new ArrayList<>();
		ArrayList<Integer> dateOfOldFilesArr = new ArrayList<>();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		Set<String> set = new HashSet<String>();
		
		FindFiles findFile = new FindFiles();
		
		/*
		 * /T24AEB00/r17aeb7/bnk/bnk.run/EMISIN.BP
		 * /T24AEB00/r17aeb7/bnk/bnk.run/EMIS.CLEARING
		 * /T24ARC/archive/EMIS.CLEARING.ARC
		 */
		
//		String emisIn = "\\\\10.224.40.10\\T24AEB00\\r17aeb7\\bnk\\bnk.run\\EMISIN.BP\\";
//		String emisClearing = "\\\\10.224.40.10\\T24AEB00\\r17aeb7\\bnk\\bnk.run\\EMIS.CLEARING\\";
//		String emisClearingArc = "\\\\10.224.40.10\\T24ARC\\archive\\EMIS.CLEARING.ARC\\";
			
		String emisIn = "C:\\EMIS\\current\\";
		String emisClearing = "C:\\EMIS\\destino\\";
		String emisClearingArc = "C:\\EMIS\\yesterday\\";
		//String emisClearingArc = "\\\\10.224.10.90\\emis.clearing.arc\\";
		
		
		
		String folderToFindFiles = emisIn;
		
		findFile.setFile(folderToFindFiles); 
	
		set = findFile.determineIfFileExists();
			
		MoveFileSourceToDestination moveFile = new MoveFileSourceToDestination(folderToFindFiles);
		
		moveFile.setDestinationDirectory(emisClearing);    // Final Destination 
		
		String[] files = findFile.fromSetToArray(set);
		
		System.out.println("Check values");
		
		CleanSpecialChars cleanCharacters = null;
		BufferedReader firstLine = null;

		
		for ( int h = 0; h < files.length; h++) {
			ProcessFileDates validarDataCurrent = new ProcessFileDates(emisIn,files[h]);   // EMIS.IN
			int day = validarDataCurrent.getCurrentFileDate(); 
			strDateOfNewFiles = String.valueOf(day);
			
			dateOfNewFilesArr.add(day);  // ArrayList #1
		}
//		dateOfNewFiles = dateFormat.parse(strDateOfNewFiles);		
		
		String textLine = null;
		String fileName = null;
		
		int day = 0;
		
		
		for ( int t = 0; t < files.length; t++) {
			
			System.out.println("Check for files in old list array ... " +  files[t]);
			firstLine = new BufferedReader(new FileReader(emisIn + files[t]));
			textLine = firstLine.readLine();
			firstLine.close();  // Troublesome line
	
			if ( files[t].startsWith("M") || files[t].startsWith("m")) {
				
				fileName = files[t].substring(0, 5) + "_" + textLine.substring(33, 41);
			} else {
				
				fileName = files[t].substring(0, 4) + "_" + textLine.substring(33, 41);
			}
			sba.comps.processfiledates.ProcessFileDates validarOldFicheiro = new sba.comps.processfiledates.ProcessFileDates(emisClearingArc);    // Emis.Clearing.Arc
			
			day = validarOldFicheiro.getOldFileDate(fileName);
			
			dateOfOldFilesArr.add(day); 
			
			}	
		strDateOfOldFiles = String.valueOf(day);
				
	if (dateOfNewFilesArr.equals(dateOfOldFilesArr)) {
		try {		
			for ( int i = 0; i < files.length; i++) {
				
					if (files[i].substring(0, 4).equalsIgnoreCase("DST5")) {
						cleanCharacters = new CleanSpecialChars(files[i], moveFile.getSourceDirectory(), files[i], moveFile.getDestinationDirectory());
						cleanCharacters.readAndWriteToFile();
						moveFile.deleteOriginFile(files[i]);
						}
						else{
							moveFile.moveOneFileFromSourceToDestination(files[i]); 
						}
			}
		}catch(FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (@SuppressWarnings("hiding") IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("DATES DO NOT MATCH");
		}
	}
}
