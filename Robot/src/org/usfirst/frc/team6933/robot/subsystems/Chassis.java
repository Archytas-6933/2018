package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.Robot;
import org.usfirst.frc.team6933.robot.RobotMap;
import org.usfirst.frc.team6933.robot.commands.basic.ChassisDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SPI;

/**
 *
 */
public class Chassis extends Subsystem {


	 
		
	// Define the two motors as CANTalons
	WPI_TalonSRX leftMotorA = new WPI_TalonSRX(RobotMap.CAN.motorLeftA);
	WPI_TalonSRX leftMotorB = new WPI_TalonSRX(RobotMap.CAN.motorLeftB);
	WPI_TalonSRX rightMotorA = new WPI_TalonSRX(RobotMap.CAN.motorRightA);
	WPI_TalonSRX rightMotorB = new WPI_TalonSRX(RobotMap.CAN.motorRightB);
	
	SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftMotorA, leftMotorB);
	SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightMotorA, rightMotorB);
	

	DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);
	
	Encoder leftEncoder = new Encoder(RobotMap.DIO.motorLeftEncoderA, RobotMap.DIO.motorLeftEncoderB, true);
	Encoder rightEncoder = new Encoder(RobotMap.DIO.motorRightEncoderA, RobotMap.DIO.motorRightEncoderB);
	AHRS navx_ahrs = new AHRS(SPI.Port.kMXP);
	
	public Chassis() {
		double distancePerPulse = 6 * 2.54 * Math.PI / 100 /360 ;  // meters
		
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);

	}
	// Basic arcade drive
	public void drive(double forwardAxis, double turnAxis) 
	{
		drive.arcadeDrive(forwardAxis, turnAxis, false);
		//temp disabled
	}

    public void initDefaultCommand() {
		setDefaultCommand(new ChassisDrive());
    }
    

	public void enableRateControl() {
		// TODO Auto-generated method stub	
	}

	public void disableRateControl() {
		// TODO Auto-generated method stub
		
	}
	public double getAngle() {
		return navx_ahrs.getAngle();
	}
	
	public void sendInfo() {
	   	System.out.println("left  " + Double.toString(leftEncoder.getRate()));
		System.out.println("right  " + Double.toString(rightEncoder.getRate()));
	//	System.out.println(Float.toString(navx_ahrs.getDisplacementX()));  
	}
    
}

