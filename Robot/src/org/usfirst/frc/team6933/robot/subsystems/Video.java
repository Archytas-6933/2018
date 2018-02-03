package org.usfirst.frc.team6933.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Video extends Subsystem {

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Video() {
		CameraServer.getInstance().startAutomaticCapture();
    }
}

