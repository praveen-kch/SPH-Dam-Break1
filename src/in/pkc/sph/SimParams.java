package in.pkc.sph;

public class SimParams {

	private int nframes;
	private int npframes;
	private double h;
	private double dt;
	private double rho0;
	private double k;
	private double mu;
	private double g;
	private double[][] Xbounds;
	
	public SimParams(){
		nframes=100;
		npframes=400;
		dt=0.0004;
		h=0.05;
		rho0=1000;
		k=1e3;
		mu=0.001;
		g=9.8;
		setXbounds(new double[][]{{0,2},{0,2},{0,2}});
	}
	
	public double[][] getSimParams(){
		double[][] params= new double[2][14];
		for(int i=0;i<14;i++)
			params[0][i]=i;
		params[1][0]=nframes;
		params[1][1]=npframes;
		params[1][2]=h;
	    params[1][3]=dt;
	    params[1][4]=rho0;
	    params[1][5]=k;
	    params[1][6]=mu;
	    params[1][7]=g;
	    params[1][8]=Xbounds[0][0];
	    params[1][9]=Xbounds[0][1];
	    params[1][10]=Xbounds[1][0];
	    params[1][11]=Xbounds[1][1];
	    params[1][12]=Xbounds[2][0];
	    params[1][13]=Xbounds[2][1];
		return params;
	}
	
	public SimParams(double[][] params){
		Xbounds = new double[3][2];
		nframes=(int) params[1][0];
		npframes=(int) params[1][1];
		h=params[1][2];
		dt=params[1][3];
		rho0=params[1][4];
	    k=params[1][5];
	    mu=params[1][6];
	    g=params[1][7];
	    Xbounds[0][0]=params[1][8];
	    Xbounds[0][1]=params[1][9];
	    Xbounds[1][0]=params[1][10];
	    Xbounds[1][1]=params[1][11];
	    Xbounds[2][0]=params[1][12];
	    Xbounds[2][1]=params[1][13];
	}
	/**
	 * @return the nframes
	 */
	public int getNframes() {
		return nframes;
	}
	/**
	 * @param nframes the nframes to set
	 */
	public void setNframes(int nframes) {
		this.nframes = nframes;
	}
	/**
	 * @return the npframes
	 */
	public int getNpframes() {
		return npframes;
	}
	/**
	 * @param npframes the npframes to set
	 */
	public void setNpframes(int npframes) {
		this.npframes = npframes;
	}
	/**
	 * @return the h
	 */
	public double getH() {
		return h;
	}
	/**
	 * @param h the h to set
	 */
	public void setH(double h) {
		this.h = h;
	}
	/**
	 * @return the dt
	 */
	public double getDt() {
		return dt;
	}
	/**
	 * @param dt the dt to set
	 */
	public void setDt(double dt) {
		this.dt = dt;
	}
	/**
	 * @return the rho0
	 */
	public double getRho0() {
		return rho0;
	}
	/**
	 * @param rho0 the rho0 to set
	 */
	public void setRho0(double rho0) {
		this.rho0 = rho0;
	}
	/**
	 * @return the k
	 */
	public double getK() {
		return k;
	}
	/**
	 * @param k the k to set
	 */
	public void setK(double k) {
		this.k = k;
	}
	/**
	 * @return the mu
	 */
	public double getMu() {
		return mu;
	}
	/**
	 * @param mu the mu to set
	 */
	public void setMu(double mu) {
		this.mu = mu;
	}
	/**
	 * @return the g
	 */
	public double getG() {
		return g;
	}
	/**
	 * @param g the g to set
	 */
	public void setG(double g) {
		this.g = g;
	}
	public double[][] getXbounds() {
		return Xbounds;
	}
	public void setXbounds(double[][] xbounds) {
		Xbounds = xbounds;
	}
	
}
