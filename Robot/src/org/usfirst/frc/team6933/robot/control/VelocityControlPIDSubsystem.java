package org.usfirst.frc.team6933.robot.control;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VelocityControlPIDSubsystem extends PIDSubsystem {

	Encoder encoder;
	public SpeedController wheel;

	
	// Initialize your subsystem here
	public VelocityControlPIDSubsystem(String name, double kP, double kI, double kD, Encoder encoder,
			SpeedController wheel) {
		super(name + "VelocityPID", kP, kI, kD);

		this.encoder = encoder;
		this.wheel = wheel;

		setInputRange(-1.0, +1.0);
		setSetpoint(0.0); // initialize setpoint to zero
		
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
		double input = encoder.getRate() / Robot.chassis.chassisNoLoadMps;
		SmartDashboard.putNumber("A - PIDInput - " + getName(), input);
		return input;
	}

	@Override
	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output)
//		if (getSetpoint() != 0)
//			System.out.println(getName() + " output at " + Double.toString(output) + 
//					" goal: " + Double.toString(getSetpoint()));
		SmartDashboard.putNumber("A - PIDOutput - " + getName(), output);
		wheel.set(output);
	}

	public void sendInfo() {
		SmartDashboard.putData(this);
	}

	
}
