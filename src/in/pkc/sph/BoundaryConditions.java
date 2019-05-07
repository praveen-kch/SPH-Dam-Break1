package in.pkc.sph;

public class BoundaryConditions {
	
	public static void reflect_bc(StateVars sv, SimParams sp){
		int i,j;
		double[][] x=sv.getX();
		double[][] v=sv.getV();
		double[][] vh=sv.getVh();
		double[][] xb=sp.getXbounds();
		int n=sv.getN();
		
		for(i=0;i<n;i++)
			for(j=0;j<3;j++){
				if(x[i][j]<=xb[j][0])
					dampReflect(j,xb[j][0],x[i],v[i],vh[i]);
				if(x[i][j]>=xb[j][1])
					dampReflect(j,xb[j][1],x[i],v[i],vh[i]);
			}
	}
	
	public static void dampReflect(int dir,double xb,double[] x,double[] v,double[] vh){
		double damp=0.75;
		
		/*
		 * Scaling back the distances 
		 */
		double tbounce =(x[dir]-xb)/v[dir];
		x[0]-=v[0]*(1-damp)*tbounce;
		x[1]-=v[1]*(1-damp)*tbounce;
		x[2]-=v[2]*(1-damp)*tbounce;
		
		/*
		 * Reflecting the positions
		 */
		x[dir]=2*xb-x[dir];
		v[dir]=-v[dir];
		vh[dir]=-vh[dir];
		
		/*
		 * Damp the velocities
		 */
		v[0]*=damp; v[1]*=damp; v[2]*=damp;
		vh[0]*=damp; vh[1]*=damp; vh[2]*=damp;
		
	}

}
