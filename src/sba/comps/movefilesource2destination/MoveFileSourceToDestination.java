package sba.comps.movefilesource2destination;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveFileSourceToDestination {
	
	private String sourceDirectory;
	private String destinationDirectory;
	
	public MoveFileSourceToDestination(String sourceDirectory) {
		
		this.sourceDirectory = sourceDirectory;
	}
	
	public MoveFileSourceToDestination(String sourceDirectory, String destinationDirectory) {
		
		this.destinationDirectory = destinationDirectory;
		this.sourceDirectory = sourceDirectory;
	}
	
	public String getSourceDirectory() {
		return sourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}

	public String getDestinationDirectory() {
		return destinationDirectory;
	}

	public void setDestinationDirectory(String destinationDirectory) {
		this.destinationDirectory = destinationDirectory;
	}

	public void moveFilesSourceToDestination() {
		
		    try {
		    	Files.move(Paths.get(getSourceDirectory()), Paths.get(getDestinationDirectory()), StandardCopyOption.REPLACE_EXISTING);
		    } catch (IOException e) {
		    	e.printStackTrace();
		        e.getMessage();
		    }	
	}
	
	public void moveOneFileFromSourceToDestination(String fileName) throws IOException{
		
	  File sourceFolder = new File(getSourceDirectory() + "\\" + fileName);
      File destinationFolder = new File(getDestinationDirectory() + "\\" + fileName);
      
      try {
    	  
    	moveFile(sourceFolder, destinationFolder);  
      } catch( IOException ex) {
    	  ex.printStackTrace();
      } 
	}
	
    private static void moveFile(File src, File dest) throws IOException{
    	
    	try {
    	 Files.move(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING  );
    	} catch(FileNotFoundException ex) {
    		ex.printStackTrace();
    		ex.getMessage();
    	} 
    }	  
      
    public void deleteOriginFile(String fileName) {
    	  
    	File sourceFolder = new File(getSourceDirectory() + "\\" + fileName);
    	  
    	if ( fileName.substring(0, 4).equalsIgnoreCase("DST5")) {
    		  sourceFolder.delete();
   }
  }

}
