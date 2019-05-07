package in.pkc.sph;

import java.io.File;
import java.sql.Timestamp;

import in.pkc.file.Operations;
import in.pkc.file.Operations2;

public class Simulator {

	public static void run(String dirName, int nst){

		int i;
		/*
		 * Initial set up	
		 */
		
		String dirName2=dirName+File.separator+"SV";
		
		
		File file = new File(dirName);
		if(!file.exists())
			file.mkdirs();
		file=new File(dirName2);
		if(!file.exists())
			file.mkdirs();
		
		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("   : Initialising the setup");
		SimParams sp = new SimParams();
		FluidSpreader icond= (FluidSpreader) new DamBreak0();
		StateVars sv= new StateVars(icond, sp);
		
		String fpath=dirName2+File.separator+"SV0.txt";
		Operations2.write9e3Nums(sv.getTotalData(), fpath);
		
		fpath=dirName+File.separator+"SP.txt";
		Operations2.write9e3Nums(sp.getSimParams(), fpath);
		
		/*
		 * Leaping in time
		 */
		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Started Iterations");
		LeapFrogs.firstLeap(sv, sp);
		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Finished Time Step 1");
		fpath=dirName2+File.separator+"SV1.txt";
		Operations2.write9e3Nums(sv.getTotalData(), fpath);

		for(i=2;i<nst;i++){
			LeapFrogs.leapStep(sv, sp);
			System.out.print(new Timestamp(System.currentTimeMillis()));
			System.out.println("    : Finished Time Step "+i);
			if(i%100==0){
				fpath=dirName2+File.separator+"SV"+i+".txt";
				Operations2.write9e3Nums(sv.getTotalData(), fpath);
			}
		}

		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Finished All Timesteps Started writing data to files");
		
		fpath=dirName2+File.separator+"SV"+i+".txt";
		Operations2.write9e3Nums(sv.getTotalData(), fpath);

		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Finished writing data to files");

	}
	
	public static void continueRun(String dirName, int i, int nst){

		/*
		 * Initial set up	
		 */
		String dirName2 = dirName+File.separator+"SV";
		String spfName  = dirName+File.separator+"SP.txt";
		String svfName  = dirName2+File.separator+"SV"+i+".txt";
		
		/*
		 * Checking the existence of the files
		 */
		File file = new File(spfName);
		if(!file.exists()){
			System.out.println("The File: "+spfName+" Does not exist");
			System.exit(0);
		}
			
		
		file=new File(svfName);
		if(!file.exists()){
			System.out.println("The File: "+svfName+" Does not exist");
			System.exit(0);
		}
		
		
		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("   : Initialising the setup");
		double[][] dumb;
		dumb=Operations2.readAllNums(spfName);
		

		SimParams sp = new SimParams(dumb);
		
		dumb=Operations2.readAllNums(svfName);
		
		StateVars sv= new StateVars(dumb);
				
		String fpath;
		
		fpath=dirName2+File.separator+"TempSV"+i+".txt";
		Operations2.write9e3Nums(sv.getTotalData(), fpath);
		
		fpath=dirName+File.separator+"TempSP.txt";
		Operations2.write9e3Nums(sp.getSimParams(), fpath);
		
		/*
		 * Leaping in time
		 */
		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Started Iterations");
		
		if(i==0){
		LeapFrogs.firstLeap(sv, sp);
		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Finished Time Step 1");
		i++;
		}

		for(i=i+1;i<nst;i++){
			LeapFrogs.leapStep(sv, sp);
			System.out.print(new Timestamp(System.currentTimeMillis()));
			System.out.println("    : Finished Time Step "+i);
			if(i%100==0){
				fpath=dirName2+File.separator+"SV"+i+".txt";
				Operations2.write9e3Nums(sv.getTotalData(), fpath);
			}
		}

		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Finished All Timesteps Started writing data to files");
		
		fpath=dirName2+File.separator+"SV"+i+".txt";
		Operations2.write9e3Nums(sv.getTotalData(), fpath);

		System.out.print(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Finished writing data to files");
		
	}
	
}

