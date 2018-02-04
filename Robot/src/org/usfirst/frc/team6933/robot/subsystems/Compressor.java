package org.usfirst.frc.team6933.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Compressor extends Subsystem {

	edu.wpi.first.wpilibj.Compressor compressor = new edu.wpi.first.wpilibj.Compressor();
	
    public void initDefaultCommand() {
        //setDefaultCommand(new CompressorRun());
    }
    
    public void start() {
    		compressor.start();
    }
    
    public void stop() {
    		compressor.stop();
    }
    
    public boolean isEnabed() {
    		return compressor.getClosedLoopControl();
    }
    
    // find out whether its up to pressure and has turned off
    public boolean isRunning() {
		return compressor.enabled();
    }
}

