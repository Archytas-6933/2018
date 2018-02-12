package org.usfirst.frc.team6933.robot.control;

import edu.wpi.first.wpilibj.Encoder;

public class PositionControl implements IChassisControl {

	public static final int L = 0;
	public static final int R = 1;
	
	private PositionControlPIDSubsystem[] controller = new PositionControlPIDSubsystem[2];
	private Encoder[] encoder;
	
	public PositionControl(Encoder[] encoder, VelocityControl velocityControl) {
		this.encoder = encoder;
		controller[L] = new PositionControlPIDSubsystem("Left", 1.0, 0.1, 0.3, encoder[L], velocityControl.getLeft());
		controller[R] = new PositionControlPIDSubsystem("Right", 1.0, 0.1, 0.3, encoder[R], velocityControl.getRight());
	}

	@Override
	public void disable() {
		controller[L].disable();
		controller[R].disable();
	}

	@Override
	public void enable() {
		controller[L].enable();
		controller[R].enable();
	}

	@Override
	public void sendInfo() {
		controller[L].sendInfo();
		controller[R].sendInfo();
	}

	@Override
	public void setMotorOutput(double leftMotorOutput, double rightMotorOutput) {
		// n/a
	}

	public void setTargetDistance(double distance) {
		encoder[L].reset();
		encoder[R].reset();
		controller[L].setSetpoint(distance);
		controller[R].setSetpoint(distance);
	}
	
	public boolean onTarget() {
		return controller[L].onTarget() && controller[R].onTarget();
	}

}