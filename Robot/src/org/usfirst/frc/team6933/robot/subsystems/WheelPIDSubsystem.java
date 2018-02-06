package org.usfirst.frc.team6933.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WheelPIDSubsystem extends PIDSubsystem {

	Encoder encoder;
	SpeedController wheel;
	
    // Initialize your subsystem here
    public WheelPIDSubsystem(String name, double kP, double kI, double kD, Encoder encoder, SpeedController wheel, double decimator) {
    		super(name,kP,kI,kD);
    	
    		this.encoder = encoder;
    		this.wheel = wheel;
    		
    		setInputRange(-decimator,+decimator);
    	    setSetpoint(0.0);  // initialize setpoint to zero
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    @Override
    public void initDefaultCommand() {
        // n/a
    }

    @Override
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return encoder.getRate();  // TODO wrong comparison
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    		wheel.set(output);
    }
    
    public void updateSetpoint(double setpoint) {
    		setSetpoint(setpoint);
    }
    
	public void sendInfo() {
		SmartDashboard.putData(this);
	}
}
