package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Grabber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Solenoid grabberClose = new Solenoid(0, RobotMap.Solenoid.grabberClose);
	Solenoid grabberOpen = new Solenoid(0, RobotMap.Solenoid.grabberOpen);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void open() {
		grabberOpen.set(true);
		grabberClose.set(false);
	}

	public void close() {
		grabberOpen.set(false);
		grabberClose.set(true);
	}

	public void stationary() {
		grabberOpen.set(false);
		grabberClose.set(false);
	}

	public void putDashboard() {
		SmartDashboard.putBoolean("Grabber Open", grabberOpen.get());
		SmartDashboard.putBoolean("Grabber Close", grabberClose.get());
	}

}
