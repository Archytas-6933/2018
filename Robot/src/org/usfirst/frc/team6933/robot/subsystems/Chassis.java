package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.RobotMap;
import org.usfirst.frc.team6933.robot.commands.ChassisDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Chassis extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
		
	// Define the two motors as CANTalons
	WPI_TalonSRX leftMotorA = new WPI_TalonSRX(RobotMap.CAN.motorLeftA);
	WPI_TalonSRX leftMotorB = new WPI_TalonSRX(RobotMap.CAN.motorLeftB);
	WPI_TalonSRX rightMotorA = new WPI_TalonSRX(RobotMap.CAN.motorRightA);
	WPI_TalonSRX rightMotorB = new WPI_TalonSRX(RobotMap.CAN.motorRightB);
	
	SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftMotorA, leftMotorB);
	SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightMotorA, rightMotorB);
	

	DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);
	
	// Basic arcade drive
	public void drive(double forwardAxis, double turnAxis) 
	{
		drive.arcadeDrive(forwardAxis, turnAxis, false);
		//temp disabled
	}
	
	

    public void initDefaultCommand() {
		setDefaultCommand(new ChassisDrive());
    }
}

