package org.usfirst.frc.team6933.robot.control;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class OpenLoopControl implements IChassisControl {

	public static final int L = 0;
	public static final int R = 1;
	private OpenLoopControlSubsystem[] controller = new OpenLoopControlSubsystem[2];

	public OpenLoopControl(Encoder[] encoder, SpeedControllerGroup[] motor) {
		controller[L] = new OpenLoopControlSubsystem("Left", encoder[L], motor[L]);
		controller[R] = new OpenLoopControlSubsystem("Right", encoder[R], motor[R]);
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
		controller[L].setMotorOutput(leftMotorOutput);
		controller[R].setMotorOutput(rightMotorOutput);
	}

}