package in.pkc.sph;

public class SmoothFuncs {

	public static void computeDensity(StateVars sv, SimParams sp){
		
		int i,j,n;
		double dx,dy,dz,r2,h,h2,h6,z,trho;
		n= sv.getN();
		h=sp.getH();
		h2=h*h;
		h6=h2*h2*h2;
		double[] rho= new double[n];
		double C1=4*sv.getMass()/Math.PI/(h2);
		double C2=C1/h6;
		
		double[][] X=sv.getX();
		for(i=0;i<n;i++){
			rho[i]=0;
		}
		
		for(i=0;i<n;i++){
			rho[i]+= C1 ;
			for(j=i+1;j<n;j++){
				dx=X[i][0]-X[j][0];
				dy=X[i][1]-X[j][1];
				dz=X[i][2]-X[j][2];
				r2=dx*dx+dy*dy+dz*dz;
				z=h2-r2;
				if(z>0){
					trho=C2*z*z*z;
					rho[i]+=trho;
					rho[j]+=trho;
				}
			}	
		}
		
		sv.setRho(rho);
	}
	
	public static void computeAcceleration(StateVars sv, SimParams sp){
		int i,j,n;
		double h = sp.getH();
		double rho0 = sp.getRho0();
		double k = sp.getK();
		double mu = sp.getMu();
		double g = sp.getG();
		double mass = sv.getMass();
		double h2 = h*h;
		double[] rho = sv.getRho();
		double[][] x = sv.getX();
		double[][] v = sv.getV();
		n=sv.getN();
		double[][] a = new double[n][3];
		
		
		
		/*
		 * Initializing the acceleration array
		 */
		for (i = 0; i < n; i++) {
			a[i][0] = 0;
			a[i][1] = 0;
			a[i][2] = -g;
		}
		
		// Constants for interaction term
		double C0 = mass / Math.PI/ ( (h2)*(h2) );
		double Cp = 15*k;
		double Cv = -40*mu;
		
		// Now compute interaction forces
		for (i = 0; i < n; ++i) {
		double rhoi = rho[i];
			for (j = i+1; j < n; ++j) {
				double dx = x[i][0]-x[j][0];
				double dy = x[i][1]-x[j][1];
				double dz = x[i][2]-x[j][2];
				double r2 = dx*dx + dy*dy +dz*dz;
				if (r2 < h2) {
					double rhoj = rho[j];
					double q = Math.sqrt(r2)/h;
					double u = 1-q;
					double w0 = C0 * u/rhoi/rhoj;
					double wp = w0 * Cp * (rhoi+rhoj-2*rho0) * u/q;
					double wv = w0 * Cv;
					double dvx = v[i][0]-v[j][0];
					double dvy = v[i][1]-v[j][1];
					double dvz = v[i][2]-v[j][2];
					a[i][0] += (wp*dx + wv*dvx);
					a[i][1] += (wp*dy + wv*dvy);
					a[i][2]	+= (wp*dz + wv*dvz);
					a[j][0] -= (wp*dx + wv*dvx);
					a[j][1]	-= (wp*dy + wv*dvy);
					a[j][2]	-= (wp*dz + wv*dvz);
					}
			}
		}
		sv.setA(a);
	}
}
