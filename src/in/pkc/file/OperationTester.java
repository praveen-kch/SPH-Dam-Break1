package in.pkc.file;
import java.io.*;

public class OperationTester{
	public static void main (String[] args){
		long stime;
		String currentDirectory;
		currentDirectory = System.getProperty("user.dir");
		System.out.println(currentDirectory);
		String fpath="res"+File.separator+"RSV0.txt";
		String fpath2="res"+File.separator+"WSV0.txt";
		double[][] a= new double[10][10];
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		System.out.println("Reading through first method");
		stime=System.nanoTime();
		a= Operations.readAllNums(fpath);
		System.out.println(System.nanoTime()-stime);
		
		
		System.out.println("Writing through first method");
		stime=System.nanoTime();
		Operations.write9e3Nums(a,fpath2);
		System.out.println(System.nanoTime()-stime);
		
		System.out.println("Reading through second method");
		stime=System.nanoTime();
		a= Operations2.readAllNums(fpath);
		System.out.println(System.nanoTime()-stime);
		
		
		System.out.println("Writing through second method");
		stime=System.nanoTime();
		Operations2.write9e3Nums(a,fpath2);
		System.out.println(System.nanoTime()-stime);
	}
}