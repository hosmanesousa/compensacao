package sba.comps.cleanspecialchars;

import java.io.*;

public class CleanSpecialChars {
			
			private String originName;
			private String originNamePath;
			private String destinationName;
			private String destinationNamePath;
			
//			public CleanSpecialCharacters() {
//				
//				this.originName = "before.txt";
//				this.originNamePath = "C:\\eclipse_workspace\\";
//				this.destinationName = "after_dst5.txt";
//				this.destinationNamePath = "C:\\eclipse_workspace\\";
//				
//			}
			
			public CleanSpecialChars(String originName, String originNamePath) {
				
				this.originName = originName;
				this.originNamePath = originNamePath;
//				this.destinationName = destinationName;
//				this.destinationNamePath = destinationNamePath;
			}
			
			public CleanSpecialChars(String originName, String originNamePath, String destinationName, String destinationNamePath) {
				
				this.originName = originName;
				this.originNamePath = originNamePath;
				this.destinationName = destinationName;
				this.destinationNamePath = destinationNamePath;
			}
			
			public String getOriginName() {
				return originName;
			}

			public void setOriginName(String originName) {
				this.originName = originName;
			}

			public String getOriginNamePath() {
				return originNamePath;
			}

			public void setOriginNamePath(String originNamePath) {
				this.originNamePath = originNamePath;
			}

			public String getDestinationName() {
				return destinationName;
			}

			public void setDestinationName(String destinationName) {
				this.destinationName = destinationName;
			}

			public String getDestinationNamePath() {
				return destinationNamePath;
			}


			public void setDestinationNamePath(String destinationNamePath) {
				this.destinationNamePath = destinationNamePath;
			}
			
			public void readAndWriteToFile() throws FileNotFoundException{
			
				String inputLine = null;
				BufferedWriter writeFile = null;
		        File originFile = new File(getOriginNamePath() ,getOriginName());	  //
		        BufferedReader readFile = new BufferedReader(new FileReader(originFile));
				
				  try {
			        	writeFile = new BufferedWriter(new FileWriter(getDestinationNamePath() + getDestinationName()));
			            while((inputLine = readFile.readLine()) != null) {
			            	String cleanedInputLine = inputLine.replaceAll("[^a-zA-Z0-9]", " ");
			            	//System.out.println(cleanedInputLine);
			            	writeFile.write(cleanedInputLine + "\n");
			            }
			        }catch(IOException ex) {
			            System.err.println("An IOException was caught reading the files");
			            ex.printStackTrace();
			        } finally {
			        	closeFiles(readFile, writeFile);
			        }
			}
				
			public void closeFiles(BufferedReader readFile, BufferedWriter writeFile){
				
				try {
					readFile.close();
		    		writeFile.close();
		    	}catch (IOException ex) {
		    		System.err.println("An IOException was caught closing the files");
		    		ex.printStackTrace();
		    	}
			}
		}

