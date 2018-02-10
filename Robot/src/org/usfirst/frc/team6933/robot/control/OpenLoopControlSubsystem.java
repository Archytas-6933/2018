package org.usfirst.frc.team6933.robot.control;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class OpenLoopControlSubsystem extends Subsystem {

	Encoder encoder;
	SpeedController wheel;

	// determine max speed
	int cimNoLoadRpm = 5310; // 2.5" CIM Motor (am-0255)
	double toughBoxMiniRatio = 10.71; // ToughBox Mini for KoP Chassis (am-14u3), 10.71:1 Ratio (am-2598_107)
	double toughBoxMiniOutputRpm = cimNoLoadRpm / toughBoxMiniRatio;
	double wheelCircumfrenceMeters = 6 * 2.54 * Math.PI / 100;
	double chassisNoLoadMps = toughBoxMiniOutputRpm * wheelCircumfrenceMeters;

	// Initialize your subsystem here
	public OpenLoopControlSubsystem(String name, Encoder encoder, SpeedController wheel) {
		super(name + "OpenLoop");
		this.encoder = encoder;
		this.wheel = wheel;
	}

	@Override
	public void initDefaultCommand() {
		// n/a
	}

	public void sendInfo() {
		SmartDashboard.putData(this);
	}

	public void enable() {
		// n/a
	}

	public void disable() {
		// n/a
	}

	public void setMotorOutput(double speed) {
		wheel.set(speed);
	}
}
