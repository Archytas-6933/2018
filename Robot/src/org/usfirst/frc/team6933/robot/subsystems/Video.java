package org.usfirst.frc.team6933.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Video extends Subsystem {

	
	CameraServer server = CameraServer.getInstance();
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startAutomaticCapture() {
    		server.startAutomaticCapture();
    }
   
}

