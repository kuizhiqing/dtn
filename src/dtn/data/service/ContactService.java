package dtn.data.service;

import java.io.*;

public class ContactService {
	
	
	public static void read(String file) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = br.readLine();
		while(line!=null){
			
		}
	}

}
