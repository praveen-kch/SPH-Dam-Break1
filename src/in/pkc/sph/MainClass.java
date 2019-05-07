package in.pkc.sph;

import java.io.File;
import java.sql.Timestamp;

public class MainClass {

	public static void main (String[] args){
		/*
		 * Simulating and writing data into text files
		 */
		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("  :Calling the Run Method of Simulator Class");
		String dirName="res"+File.separator+"run5";
		//Simulator.continueRun(dirName,1400,20000);
		Simulator.run(dirName, 10000);

		
		/*
		 * Reading and displaying the water
		 */
		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("  :Calling the Run Method of Emulator Class");		



		
	}
	
	
	
	
}
