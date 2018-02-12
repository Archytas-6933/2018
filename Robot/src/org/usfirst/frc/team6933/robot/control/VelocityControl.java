package org.usfirst.frc.team6933.robot.control;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class VelocityControl implements IChassisControl {
	
	public static final int L = 0;
	public static final int R = 1;
	private VelocityControlPIDSubsystem[] controller = new VelocityControlPIDSubsystem[2];

	public VelocityControl(Encoder[] encoder, SpeedControllerGroup[] motor) {
		controller[L] = new VelocityControlPIDSubsystem("Left", 1, 0, 0.3, encoder[L], motor[L]);
		controller[R] = new VelocityControlPIDSubsystem("Right", 1, 0, 0.3, encoder[R], motor[R]);
	}

	public void setSetpoints(double leftMotorOutput, double rightMotorOutput) {
		controller[L].setSetpoint(leftMotorOutput);
		controller[R].setSetpoint(rightMotorOutput);
	}

	public void setSetpointsSymmetrical(double output) {
		controller[L].setSetpoint(output);
		controller[R].setSetpoint(-output);
	}

	@Override
	public void sendInfo() {
		controller[L].sendInfo();
		controller[R].sendInfo();
	}

	@Override
	public void enable() {
		controller[L].enable();
		controller[R].enable();
	}

	@Override
	public void disable() {
		controller[L].disable();
		controller[R].disable();
	}

	@Override
	public void setMotorOutput(double leftMotorOutput, double rightMotorOutput) {
		setSetpoints(leftMotorOutput, rightMotorOutput);
	}

	public VelocityControlPIDSubsystem getLeft() {
		return controller[L];
	}
	
	public VelocityControlPIDSubsystem getRight() {
		return controller[R];
	}


}
