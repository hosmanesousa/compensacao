package sba.comps.findfiles;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FindFiles {
	
	private File file;
	private String[] ficheiros = {"DST5","ORI5","MOV5","CLN5","RMB5"};
	Set<String> set = new HashSet<String>();
	
	public File getFile() {
		
		return this.file;
	}
	
	public void setFile(String ficheiro) {
		
		this.file = new File(ficheiro);	 
	}
	
	public Set<String> determineIfFileExists(){
		
		String[] container = null;
	
		container = getFile().list();
		
		for ( int i = 0; i < ficheiros.length; i++) {
							
			for ( String contains: container) {
				
				if (((String) contains.subSequence(0, 4)).equalsIgnoreCase((ficheiros[i]))){
					
					set.add(contains);
				} 
			}
		}
		return set.size() == 5 ? set : null;
	}
	
	public String[] fromSetToArray(Set<String> set) {
		
		String setToArray[] = set.toArray(new String[set.size()]);
		return setToArray;
	}
}
