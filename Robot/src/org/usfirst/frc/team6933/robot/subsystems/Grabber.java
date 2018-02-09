package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.RobotMap;
import org.usfirst.frc.team6933.robot.commands.grabber.GrabberOff;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Grabber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Command
	DoubleSolenoid grabberSolenoid = new DoubleSolenoid(RobotMap.CAN.pcmId, RobotMap.Solenoid.grabberOpen, RobotMap.Solenoid.grabberClose);
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new GrabberOff());
	}

	public void open() {
		grabberSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void close() {
		grabberSolenoid.set(DoubleSolenoid.Value.kReverse);;
	}

	public void off() {
		grabberSolenoid.set(DoubleSolenoid.Value.kOff);;
	}

	public void sendInfo() {
		SmartDashboard.putData(this);
	}


}
