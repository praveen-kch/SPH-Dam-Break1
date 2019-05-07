package in.pkc.sph;

public class StateVars {
	private int ts;
	private int n;
	private double mass;
	private double[]  rho;
	private double[][]  X;
	private double[][] Vh;
	private double[][]  V;
	private double[][]  a;

	public StateVars(FluidSpreader fluidSpreader, SimParams sp){
		xMaker(fluidSpreader,sp);
	}
	
	public StateVars(double[][] outMat){
	
		ts=(int) outMat[0][1];
		n=(int) outMat[0][2];
		mass=outMat[0][3];
		
		n=outMat.length;
		
		if(outMat[1].length<14)
			System.exit(0);
		
		X= new double[n-1][3];
		V= new double[n-1][3];
		Vh= new double[n-1][3];
		a= new double[n-1][3];
		rho=new double[n-1];
		
		for(int i=1;i<n;i++){
			X[i-1][0]=outMat[i][1];
			X[i-1][1]=outMat[i][2];
			X[i-1][2]=outMat[i][3];
			V[i-1][0]=outMat[i][4];
			V[i-1][1]=outMat[i][5];
			V[i-1][2]=outMat[i][6];
			Vh[i-1][0]=outMat[i][7];
			Vh[i-1][1]=outMat[i][8];
			Vh[i-1][2]=outMat[i][9];
			a[i-1][0]=outMat[i][10];
			a[i-1][1]=outMat[i][11];
			a[i-1][2]=outMat[i][12];
			rho[i-1]=outMat[i][13];
		}
		n=n-1;
	}

	/**
	 * The particle distributor
	 * @param fs
	 * @param hh
	 */
	private void xMaker(FluidSpreader fs, SimParams sp){
		
		
		double hh=sp.getH()/1.3;
		double[][] Xb = sp.getXbounds();
		/*
		 * Counting number of particles
		 */
		n=0;
		double x,y,z;
		for(x=Xb[0][0]+hh;x<Xb[0][1];x=x+hh)
			for(y=Xb[1][0]+hh;y<Xb[1][1];y=y+hh)
				for(z=Xb[2][0]+hh;z<Xb[2][1];z=z+hh)
					if(fs.isFluidy(new double[] {x,y,z}))
						n++;
		
		/*
		 * Initializing the arrays
		 */
		rho= new double[n];
		X= new double[n][3];
		Vh= new double[n][3];
		V= new double[n][3];
		a= new double[n][3];
		mass=1;
		
		int c=0;
		for(x=Xb[0][0]+hh;x<Xb[0][1];x=x+hh)
			for(y=Xb[1][0]+hh;y<Xb[1][1];y=y+hh)
				for(z=Xb[2][0]+hh;z<Xb[2][1];z=z+hh)
					if(fs.isFluidy(new double[] {x,y,z})){
						X[c][0]=x;
						X[c][1]=y;
						X[c][2]=z;
						
						V[c][0]=0;
						V[c][1]=0;
						V[c][2]=0;
						
						Vh[c][0]=0;
						Vh[c][1]=0;
						Vh[c][2]=0;
						
						a[c][0]=0;
						a[c][1]=0;
						a[c][2]=0;						
						c++;
					}
		/*
		 * Normalizing the mass				
		 */
		SmoothFuncs.computeDensity(this, sp);
		
		double rhos=0;
		double rho2s=0;
		
		for(int i=0;i<n;i++){
			rhos+=rho[i];
			rho2s+=rho[i]*rho[i];
		}
		mass=sp.getRho0()*rhos/rho2s;
		
		SmoothFuncs.computeDensity(this, sp);
		SmoothFuncs.computeAcceleration(this, sp);
		
		setTs(0);
	}

	/*
	 * Method to return an output matrix to write to a file
	 */
	public double[][] getTotalData(){
		double[][] outMat= new double[n+1][14];
	
		outMat[0][0]=0;
		outMat[0][1]=ts;
		outMat[0][2]=n;
		outMat[0][3]=mass;
		
		for(int i=4;i<=13;i++)
			outMat[0][i]=i;
	
		for(int i=0;i<n;i++){
			outMat[i+1][0]=i;
			outMat[i+1][1]=X[i][0];
			outMat[i+1][2]=X[i][1];
			outMat[i+1][3]=X[i][2];
			outMat[i+1][4]=V[i][0];
			outMat[i+1][5]=V[i][1];
			outMat[i+1][6]=V[i][2];
			outMat[i+1][7]=Vh[i][0];
			outMat[i+1][8]=Vh[i][1];
			outMat[i+1][9]=Vh[i][2];
			outMat[i+1][10]=a[i][0];
			outMat[i+1][11]=a[i][1];
			outMat[i+1][12]=a[i][2];
			outMat[i+1][13]=rho[i];
		}

		return outMat;
	}
	
	/*
	 * Method to return an output matrix to write to a file
	 */
	public double[] getNData(int i){
		double[] outMat= new double[14];
		
			outMat[0]=i;
			outMat[1]=X[i][0];
			outMat[2]=X[i][1];
			outMat[3]=X[i][2];
			outMat[4]=V[i][0];
			outMat[5]=V[i][1];
			outMat[6]=V[i][2];
			outMat[7]=Vh[i][0];
			outMat[8]=Vh[i][1];
			outMat[9]=Vh[i][2];
			outMat[10]=a[i][0];
			outMat[11]=a[i][1];
			outMat[12]=a[i][2];
			outMat[13]=rho[i];

		return outMat;
	}
	
	public void tsPlus1(){
		setTs(getTs() + 1);
	}
	/**
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @param mass the mass to set
	 */
	public void setMass(float mass) {
		this.mass = mass;
	}

	/**
	 * @return the rho
	 */
	public double[] getRho() {
		return rho;
	}

	/**
	 * @param rho the rho to set
	 */
	public void setRho(double[] rho) {
		this.rho = rho;
	}

	/**
	 * @return the x
	 */
	public double[][] getX() {
		return X;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double[][] x) {
		X = x;
	}

	/**
	 * @return the vh
	 */
	public double[][] getVh() {
		return Vh;
	}

	/**
	 * @param vh the vh to set
	 */
	public void setVh(double[][] vh) {
		Vh = vh;
	}

	/**
	 * @return the v
	 */
	public double[][] getV() {
		return V;
	}

	/**
	 * @param v the v to set
	 */
	public void setV(double[][] v) {
		V = v;
	}

	/**
	 * @return the a
	 */
	public double[][] getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(double[][] a) {
		this.a = a;
	}

	public int getTs() {
		return ts;
	}

	public void setTs(int ts) {
		this.ts = ts;
	}
	
}
