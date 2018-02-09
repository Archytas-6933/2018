package org.usfirst.frc.team6933.robot.control;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VelocityControlPIDSubsystem extends PIDSubsystem {

	Encoder encoder;
	SpeedController wheel;

	// determine max speed
	int cimNoLoadRpm = 5310; // 2.5" CIM Motor (am-0255)
	double toughBoxMiniRatio = 10.71; // ToughBox Mini for KoP Chassis (am-14u3), 10.71:1 Ratio (am-2598_107)
	double toughBoxMiniOutputRpm = cimNoLoadRpm / toughBoxMiniRatio;
	double wheelCircumfrenceMeters = 6 * 2.54 * Math.PI / 100;
	double chassisNoLoadMps = toughBoxMiniOutputRpm * wheelCircumfrenceMeters;

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
		double input = encoder.getRate() / chassisNoLoadMps;
		SmartDashboard.putNumber("A - PIDInput - " + getName(), input);
		return input;
	}

	@Override
	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output)
		SmartDashboard.putNumber("A - PIDOutput - " + getName(), output);
		wheel.set(output);
	}

	public void updateSetpoint(double setpoint) {
		setSetpoint(setpoint);
	}

	public void sendInfo() {
		SmartDashboard.putData(this);
	}

	public void setEnabled(boolean state) {
		if (state) {
			this.enable();
		} else {
			this.disable();
		}
	}
}
