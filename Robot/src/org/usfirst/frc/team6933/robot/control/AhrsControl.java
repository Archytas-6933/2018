package org.usfirst.frc.team6933.robot.control;


import com.kauailabs.navx.frc.AHRS;

public class AhrsControl implements IChassisControl {

	private AhrsPIDSubsystem controller;
	VelocityControl velocityControl;

	public AhrsControl(AHRS ahrs, VelocityControl velocityControl) {
		controller = new AhrsPIDSubsystem("Ahrs", 1.0, 0.3, 0.3, ahrs, velocityControl);
		this.velocityControl = velocityControl;
	}

	@Override
	public void sendInfo() {
		controller.sendInfo();
	}

	@Override
	public void enable() {
		velocityControl.enable();
	//	controller.resetTraveled(); // reset every time we enable to all measurements are relative to that instant
	//	controller.resetAngle();
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

	public double getAngle() {
		return controller.getAngle();
	}

}
