package in.pkc.sph;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.gl2.GLUT;

public class GraphicsPanel implements GLEventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5738310305988011978L;
	private double[][] data;
	
	public GraphicsPanel(double[][] stateVar) {
		// TODO Auto-generated constructor stub
		this.data = stateVar;
		
/*		for(int i=0;i<stateVar.length;i++){
			for(int j=0;j<stateVar[0].length;j++){
				System.out.print(" "+this.data[i][j]+" ");
			}
			System.out.println(" ");
		}*/
	}

	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		// method body
		
		final GL2 gl = arg0.getGL().getGL2();
		
		gl.glColor3f(0f, 0f, 1f);
		gl.glClearColor(1f, 1f, 1f, 1f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		int n = data.length;
		 
				
		double xc,yc,zc;
		
		for(int j=1;j<n;j++){
		
		   xc=data[j][1]*1.8-0.9;
		   yc=data[j][3]*1.8-0.9;
		   zc=data[j][2]*1.8-0.9;
			gl.glColor3f(0.0f, 0.0f, 1.0f);
	        gl.glPushMatrix();
	        /* glTranslatef() as viewing transformation */
	        gl.glTranslated(xc, yc, zc);
	        new GLUT().glutSolidSphere(0.01f, 10, 10);
	        gl.glPopMatrix();

	        gl.glFlush();
		}
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub

	}
	

}
