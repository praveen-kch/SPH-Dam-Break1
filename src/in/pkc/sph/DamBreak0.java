package in.pkc.sph;

public class DamBreak0 implements FluidSpreader {

	@Override
	public boolean isFluidy(double[] x) {
		// TODO Auto-generated method stub
		double x1,y1,z1,x2,y2,z2;
		x1=0;
		x2=1;
		y1=0;
		y2=2;
		z1=0;
		z2=1;
		if(x[0]>=x1 && x[0]<=x2 && x[1]>=y1 && x[1]<=y2 && x[2]>=z1 && x[2]<=z2)
		return true;
		else
		return false;
	}

}
