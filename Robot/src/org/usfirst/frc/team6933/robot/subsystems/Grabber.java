package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.RobotMap;
import org.usfirst.frc.team6933.robot.commands.grabber.GrabberStationary;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Grabber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Command
	Solenoid grabberClose = new Solenoid(RobotMap.Solenoid.pcmId, RobotMap.Solenoid.grabberClose);
	Solenoid grabberOpen = new Solenoid(RobotMap.Solenoid.pcmId, RobotMap.Solenoid.grabberOpen);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
//		 setDefaultCommand(new GrabberStationary());
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

	public void sendInfo() {
		SmartDashboard.putBoolean("GrabberOpen", grabberOpen.get());
		SmartDashboard.putBoolean("GrabberClose", grabberClose.get());
	}

}
