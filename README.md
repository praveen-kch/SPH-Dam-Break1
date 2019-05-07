# SPH-Dam-Break1
Simulation of dam break phenomenon by applying SPH techniques using JAVA

The repository contains a Folder names "src" 
This src folder contains source files defining java classes 
it works just the way "src" folder works in eclipse Java IDE

There are two packages 

Package in.pkc.file 
	(src/in/pkc/file/) which contains the classes that declare file operation methods (Reading and writing data)

Package in.pkc.sph  
	  (Path: src/in/pkc/sph/) which contains the classes that actually setup and solve the numerical simulation
	  This package has the following classes  
	  
	  Class: MainClass 
	    It has the main method that invokes the simulator.run by passing a directory name and the number of steps to it
	    
	  Class: simulator  
	    It has the methods run and continueRun
	    run method does the following:
	      It makes a directory to store results
	      Sets up simulation parameters by instantiating SimParam class object "sp"
	      Sets up initial conditions by instantiating a FluidSpreader class object "icond"
	      Sets up statevariable by instantiating a StateVars class object and passing "sp" and "icond"
	      Writes initial data in to files in the directory created earlier
	      It runs the simulation by time stepping the state variable using the methods "LeapFrogs.firstLeap" and 					"LeapFrogs.leapstep"
	      It writes the state variable data into the directory after every time step
	      Note that system time is printed to console at key points
	    continueRun method 
	      It does the same operations like run method but serves the purpose of continuing an already initiated and stopped 			simulation by reading the data that was already written into the directory in previous run
	  
	  Class: SimParams
	    This class defines all the simulation parameters and constructors and methods to initialize and access the parameters
	  
	  Class: StateVars
	    This class defines all the state variables and the constructors and methods to initialize and access the variables
	  
	  Class: LeapFrog
	    This class defines two methods "firstLeap" and "leapStep" to update the state Variabels object "sv" to next time step
	    These methods invoke the following methods
			BoundaryConditions.reflect_bc(sv,sp);
			SmoothFuncs.computeDensity(sv, sp);
			SmoothFuncs.computeAcceleration(sv, sp);
	  
	  Class: BoundaryCondition
	    This class applies the boundary condition of damped reflection at the edges of 3D box in which simulation is carried out.
	    So that the water particles are not going out of the box. They are reflected with a dampened velocity.
	 
	  Class: SmoothFuncs
	    It defines two methods
	    computeDensity
	      	To compute the density by using a smoothing function after updating the state variable particle Velocity and positions
	    computeAcceleration
	      	To compute the paticle acceleration by using a smoothing function after updating the state variable particle Velocity 			and positions
	  
	  Class Dambreak0
	      it implements the interface FluidSpreader to make initial conditions of a dam break problem
	  
	  Class Emulator
	      it defines the run method that reads the state variables that are created at every step during the run and plots them 
	      the plots at each time step saved as images in a directory. 
	      OpenGL was used.   



