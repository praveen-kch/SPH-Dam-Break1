package in.pkc.sph;

import java.awt.Color;
import java.io.File;
import java.sql.Timestamp;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import in.pkc.file.Operations;

public class Emulator {

	public static void run(int i){
		
		/*
		 * Reading the State variable data
		 */
		String fpath="res"+File.separator+"run1"+File.separator+"SV"+File.separator+"SV"+i+".txt";
		
		System.out.println(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Started Reading Data file");
		double[][] stateVar =Operations.readAllNums(fpath);

		

		/*
		 * Displaying
		 */
		System.out.println(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Read the Data Making the Graphic Plot");	
		
	    final GLProfile profile = GLProfile.get(GLProfile.GL2);
	    GLCapabilities capabilities = new GLCapabilities(profile);    
	    final GLCanvas glcanvas = new GLCanvas(capabilities);
	    GraphicsPanel gp = new GraphicsPanel(stateVar);
	    glcanvas.addGLEventListener(gp);
	    glcanvas.setSize(400, 400);
	    
		JFrame mainFrame = new JFrame("Basic Frame");
		mainFrame.getContentPane().add(glcanvas);
		mainFrame.setSize(mainFrame.getContentPane().getPreferredSize());
		mainFrame.setVisible(true);
		mainFrame.setBackground(Color.WHITE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println(new Timestamp(System.currentTimeMillis()));
		System.out.println("    : Finished Plotting");	
	}
	
}
