package org.usfirst.frc.team6933.robot.control;


import com.kauailabs.navx.frc.AHRS;

public class AhrsControl implements IChassisControl {

	private AhrsPIDSubsystem controller;

	public AhrsControl(AHRS ahrs, VelocityControl velocityControls) {
		controller = new AhrsPIDSubsystem("Ahrs", 1.0, 0.1, 3.0, ahrs, velocityControls);
	}

	@Override
	public void sendInfo() {
		controller.sendInfo();
	}

	@Override
	public void enable() {
		controller.resetTraveled(); // reset every time we enable to all measurements are relative to that instant
		controller.resetAngle();
		controller.enable();
	}

	@Override
	public void disable() {
		controller.disable();
	}

	@Override
	public void setMotorOutput(double leftMotorOutput, double rightMotorOutput) {
		// n/a
	}

	public void setTargetAngle(double angle) {
		controller.setTargetAngle(angle);
	}

	public boolean onTarget() {
		return controller.onTarget();
	}

}
