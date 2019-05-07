package in.pkc.sph;

public class LeapFrogs {
	
	public static void firstLeap(StateVars sv, SimParams sp){
		double[][] a = sv.getA();
		double[][] vh = sv.getVh();
		double[][] v = sv.getV();
		double[][] x = sv.getX();
		double dt=sp.getDt();
		int n = sv.getN();
		int i;
		for (i=0;i<n;i++){
			vh[i][0]=v[i][0]+a[i][0]*dt/2;
			vh[i][1]=v[i][1]+a[i][1]*dt/2;
			vh[i][2]=v[i][2]+a[i][2]*dt/2;
			
			x[i][0]+=vh[i][0]*dt;
			x[i][1]+=vh[i][1]*dt;
			x[i][2]+=vh[i][2]*dt;
			
			v[i][0]+=a[i][0]*dt;
			v[i][1]+=a[i][1]*dt;
			v[i][2]+=a[i][2]*dt;	
		}

		sv.setV(v);
		sv.setVh(vh);
		sv.setX(x);
		BoundaryConditions.reflect_bc(sv,sp);
		SmoothFuncs.computeDensity(sv, sp);
		SmoothFuncs.computeAcceleration(sv, sp);
		sv.tsPlus1();
	}

	public static void leapStep(StateVars sv, SimParams sp){
		double[][] a = sv.getA();
		double[][] vh = sv.getVh();
		double[][] v = sv.getV();
		double[][] x = sv.getX();
		double dt=sp.getDt();
		int n = sv.getN();
		int i;
		for (i=0;i<n;i++){
			vh[i][0]+=a[i][0]*dt;
			vh[i][1]+=a[i][1]*dt;
			vh[i][2]+=a[i][2]*dt;
			
			x[i][0]+=vh[i][0]*dt;
			x[i][1]+=vh[i][1]*dt;
			x[i][2]+=vh[i][2]*dt;
			
			v[i][0]=vh[i][0]+a[i][0]*dt/2;
			v[i][1]=vh[i][1]+a[i][1]*dt/2;
			v[i][2]=vh[i][2]+a[i][2]*dt/2;	
		}

		sv.setV(v);
		sv.setVh(vh);
		sv.setX(x);
		BoundaryConditions.reflect_bc(sv,sp);
		SmoothFuncs.computeDensity(sv, sp);
		SmoothFuncs.computeAcceleration(sv, sp);
		sv.tsPlus1();
	}
}
