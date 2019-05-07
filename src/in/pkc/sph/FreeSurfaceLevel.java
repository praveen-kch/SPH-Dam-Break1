package in.pkc.sph;

public class FreeSurfaceLevel implements FluidSpreader {

	@Override
	public boolean isFluidy(double[] x) {
		// TODO Auto-generated method stub
		double Level=0.5;
		if(x[2]>Level)
		return true;
		else
		return false;
	}

}
