package org.usfirst.frc.team6933.robot.control;


public interface IChassisControl {

	public abstract void sendInfo();

	public abstract void enable();

	public abstract void disable();

	public abstract void setMotorOutput(double leftMotorOutput, double rightMotorOutput);

}

