package org.usfirst.frc.team6933.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AhrsPIDSubsystem extends PIDSubsystem {

	final double heading_Kp = 0.8;
	final double headingTolerance = 2.0f; // Heading will read as "on target" if + or - this many degrees

	SpeedController left;
	SpeedController right;
	AHRS ahrs;

	// Initialize your subsystem here
	public AhrsPIDSubsystem(String name, double kP, double kI, double kD, AHRS ahrs, SpeedControllerGroup left,
			SpeedControllerGroup right) {
		super(name, kP, kI, kD);

		this.left = left;
		this.right = right;

		setSetpoint(0.0); // initialize to zero
		setInputRange(-180.0f, 180.0f);
		setOutputRange(-heading_Kp, heading_Kp);
		setAbsoluteTolerance(headingTolerance);
		enable();
		// setContinuous(true);
	}

	public void initDefaultCommand() {
		// n/a
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return ahrs.getAngle();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		right.set(output);
		left.set(output);
	}

	public void sendInfo() {
		SmartDashboard.putData(this);
	}
}
