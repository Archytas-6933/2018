package org.usfirst.frc.team6933.robot.control;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AhrsPIDSubsystem extends PIDSubsystem {

	final double heading_Kp = 0.8;
	final double headingTolerance = 2.0f; // Heading will read as "on target" if + or - this many degrees

	VelocityControl velocityControl;
	
	AHRS ahrs;

	// Initialize your subsystem here
	public AhrsPIDSubsystem(String name, double kP, double kI, double kD, AHRS ahrs, VelocityControl velocityControl ) {
		super(name, kP, kI, kD);

		this.ahrs = ahrs;
		this.velocityControl = velocityControl;
		
		setSetpoint(0.0); // initialize to zero
		setInputRange(-180.0f, 180.0f);
		setOutputRange(-heading_Kp, heading_Kp);
		setAbsoluteTolerance(headingTolerance);
		// setContinuous(true);

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
		double input = ahrs.getAngle();
		SmartDashboard.putNumber("A - PIDInput - " + getName(), input);
		return input;
	}

	@Override
	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		output /= 2;
		SmartDashboard.putNumber("A - PIDoutput - " + getName(), output);
		velocityControl.setSetpointsSymmetrical(output);
	}

	public boolean getOnTarget() {
		return this.onTarget();
	}
	
	public void sendInfo() {
		SmartDashboard.putData(this);
	}

	public void resetTraveled() {
		ahrs.resetDisplacement();
	}
	
	public void resetAngle() {
		ahrs.reset();
	}
	
	public void setTargetAngle(double angle) {
		setSetpoint(angle);
	}

	public double getAngle() {
		return ahrs.getAngle();
	}
	
}
