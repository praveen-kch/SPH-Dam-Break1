package in.pkc.file;
/*
 * Function : provides static methods for reading and writing Numbers from text files
 * All Fields		: None
 * Static Methods	: write12f3Nums
 * 					: fwriteSciNums		2 Overloads
 * 					: freadNums			2 Overloads
 * 					: freadNums2
 * 
 */
import java.io.*;
import java.util.*;

public class Operations2 {


	public static void write9e3Nums(double[][] data, String fpath){ 
		try{
			FileOutputStream f1;
			BufferedOutputStream b1;
			f1= new FileOutputStream(fpath);
			b1= new BufferedOutputStream(f1);
			int k,pow=0;
			int[] b=new int[24];
			double num;
			int nr=data.length;
			int nc=data[0].length;
			b[18]=69;
			b[2]=46;
			for (int i=0;i<nr;i++){
				nc=data[i].length;
				for (int j=0;j<nc;j++){
					pow=0;
					if(data[i][j]==0){
						b[0]=43; b[1]=48;b[19]=43;
						for(k=3;k<=17;k++) 
							b[k]=48;
						for(k=20;k<=23;k++) 
							b[k]=48;
					}else{
						if(data[i][j]>0)	
							b[0]=43;
						else			
							b[0]=45;
						num=Math.abs(data[i][j]);
						while(num>=10 && pow<9999){
							num=num/10; 
							pow++;
						}
						if(num>=1.0){
							num=Math.floor(num*Math.pow(10,15));
							for(k=17;k>=3;k--){
								b[k]=48+(int)(num%10);
								num=Math.floor(num/10);
							}
							b[1]=48+(int)(num%10);
							b[19]=43;
						}else{
							while(num<1.0 && pow>-999){
								num=num*10; pow--;
							}
							num=Math.floor(num*Math.pow(10,15));
							for(k=17;k>=3;k--){
								b[k]=48+(int)(num%10);
								num=Math.floor(num/10);
							}
							b[1]=48+(int)(num%10);
							b[19]=45;			
						}
						pow=Math.abs(pow);
						for(k=23;k>=20;k--){
							b[k]=48+pow%10;
							pow=pow/10;
						}
					}
					for (k=0;k<=23;k++){
						b1.write(b[k]);
					}
					b1.write(9);
				}
				b1.write(13);
				b1.write(10);
			}
			b1.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static double[][] readAllNums(String fpath) { 
		double[][] data;
		ArrayList<ArrayList<Double>> datl = new ArrayList<ArrayList<Double>>();
		datl.ensureCapacity(10);
		datl.add(new ArrayList<Double>());
		try{
			// Declarations
			double num;
			int n,t,nr,nc,mc,i, snum, dcount=0, mode=0;
			FileInputStream f1;
			BufferedInputStream b1;
			// Initializations
			f1 = new FileInputStream(fpath);
			n=f1.available();
			b1=new BufferedInputStream(f1);
			n=b1.available();
			num=0;
			snum=1;
			nc=0;mc=0;
			nr=0;
			/*Modes
			 * 0	Idle State 
			 * 1	Reading Mantissa number Part
			 * 2	Reading Mantissa decimal Part
			 * 3	Reading Exponent number Part
			 * 4	Reading Exponent decimal Part
			 * 5	Comment Reading
			 */
			for (i=1;i<=n+1;i=i+1){
				t= b1.read();
				if (t>=48 && t<=57){				// digit
					if (mode==0)	{	num=num*10+(t-48);dcount++;mode=1;	}
					else if(mode==1){	num=num*10+(t-48);dcount++;		}
					else if(mode==2){	num=num+(t-48)*Math.pow(10,-(++dcount));}
					else if(mode==3){	num=num*10+(t-48);dcount++;		}
					else if(mode==4){	num=num+(t-48)*Math.pow(10,-(++dcount));}
				} else if (t==45 || t==43){			// plus or minus
					if (mode==0)	{	snum=snum*(44-t);										}
					else if(mode==1){	datl.get(nr).add(snum*num);num=0;dcount=0;snum=(44-t);nc++;mode=0;					}
					else if(mode==2){	datl.get(nr).add(snum*num);num=0;dcount=0;snum=(44-t);nc++;mode=0;					}
					else if(mode==3){	if (dcount==0) { snum=snum*(44-t); }
									else{ datl.get(nr).set(nc,datl.get(nr).get(nc)*Math.pow(10,snum*num));num=0;dcount=0;snum=(44-t);nc++;mode=0;}	}
					else if(mode==4){	datl.get(nr).set(nc,datl.get(nr).get(nc)*Math.pow(10,snum*num));num=0;dcount=0;snum=(44-t);nc++;mode=0;			}
				} else if (t==46)	{			// dot
					if (mode==0)	{	mode=2;							}
					else if(mode==1){	dcount=0;mode=2;					}
					else if(mode==2){	datl.get(nr).add(snum*num);num=0;dcount=0;snum=1;nc++;mode=2;}
					else if(mode==3){	dcount=0;mode=4;					}
					else if(mode==4){	datl.get(nr).add(snum*num);num=0;dcount=0;snum=1;nc++;mode=2;}
				} else if (t==69 || t==101){			// E or e
					if (mode==0)	{	datl.get(nr).add(1.0);mode=3;												}
					else if(mode==1){	datl.get(nr).add(snum*num);num=0;dcount=0;snum=1;mode=3;								}
					else if(mode==2){	datl.get(nr).add(snum*num);num=0;dcount=0;snum=1;mode=3;								}
					else if(mode==3){	datl.get(nr).set(nc,datl.get(nr).get(nc)*Math.pow(10,snum*num));num=0;dcount=0;snum=1;nc++;datl.get(nr).add(1.0);mode=3;	}
					else if(mode==4){	datl.get(nr).set(nc,datl.get(nr).get(nc)*Math.pow(10,snum*num));num=0;dcount=0;snum=1;nc++;datl.get(nr).add(1.0);mode=3;	}
				} else if (t==10){				// new line
					if (mode==0)	{	nc=0;nr++;													}
					else if(mode==1){	datl.get(nr).add(snum*num);num=0;dcount=0;snum=1;nc=0;nr++;mode=0;						}
					else if(mode==2){	datl.get(nr).add(snum*num);num=0;dcount=0;snum=1;nc=0;nr++;mode=0;						}
					else if(mode==3){	datl.get(nr).set(nc,datl.get(nr).get(nc)*Math.pow(10,snum*num));num=0;dcount=0;snum=1;nc=0;nr++;mode=0;	}
					else if(mode==4){	datl.get(nr).set(nc,datl.get(nr).get(nc)*Math.pow(10,snum*num));num=0;dcount=0;snum=1;nc=0;nr++;mode=0;	}
					else if(mode==5){	nc=0;nr++;mode=0;	}
					datl.add(new ArrayList<Double>());
				} else if (t==9 || t==32 || t==33) {			// Space or Tab or !Comment
					if (mode==0)	{													}
					else if(mode==1){	datl.get(nr).add(snum*num);num=0;dcount=0;snum=1;nc++;mode=0;					}
					else if(mode==2){	datl.get(nr).add(snum*num);num=0;dcount=0;snum=1;nc++;mode=0;					}
					else if(mode==3){	datl.get(nr).set(nc,datl.get(nr).get(nc)*Math.pow(10,snum*num));num=0;snum=1;nc++;mode=0;	}
					else if(mode==4){	datl.get(nr).set(nc,datl.get(nr).get(nc)*Math.pow(10,snum*num));num=0;snum=1;nc++;mode=0;	}
					if(t==33){mode=5;}
				}else if (t==-1){				// End of File
					if (mode==0)	{										}	
					else if(mode==1){	datl.get(nr).add(snum*num); 						}
					else if(mode==2){	datl.get(nr).add(snum*num);						}
					else if(mode==3){	datl.get(nr).set(nc,datl.get(nr).get(nc)*Math.pow(10,snum*num));	}
					else if(mode==4){	datl.get(nr).set(nc,datl.get(nr).get(nc)*Math.pow(10,snum*num));	}
					break;	
				}
			}
			
			for(i=0;i<datl.size();i++){
				int sz = datl.get(i).size();
				if(sz==0) { datl.remove(i);}
				if(sz>mc) { mc=sz;}

			}
			data= new double[datl.size()][];
			for(i=0;i<datl.size();i++){
				data[i]=new double[datl.get(i).size()];
				for(int j=0;j<datl.get(i).size();j++)
					data[i][j]=datl.get(i).get(j);
			}
			b1.close();
			f1.close();
			return data;
		}catch(IOException e){
			e.printStackTrace();
			data= new double[10][10];
			return data;
		}
	}
}


