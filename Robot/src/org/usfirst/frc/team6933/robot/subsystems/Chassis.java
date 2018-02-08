package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.RobotMap;
import org.usfirst.frc.team6933.robot.commands.drive.JoystickDriveDefault;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Chassis extends Subsystem {

	final double distancePerPulse = 6 * 2.54 * Math.PI / 100 / 360; // meters

	// Define the two motors as CANTalons
	WPI_TalonSRX leftMotorA = new WPI_TalonSRX(RobotMap.CAN.motorLeftA);
	WPI_TalonSRX leftMotorB = new WPI_TalonSRX(RobotMap.CAN.motorLeftB);
	WPI_TalonSRX rightMotorA = new WPI_TalonSRX(RobotMap.CAN.motorRightA);
	WPI_TalonSRX rightMotorB = new WPI_TalonSRX(RobotMap.CAN.motorRightB);

	SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftMotorA, leftMotorB);
	SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightMotorA, rightMotorB);

	Encoder leftEncoder = new Encoder(RobotMap.DIO.motorLeftEncoderA, RobotMap.DIO.motorLeftEncoderB, true);
	Encoder rightEncoder = new Encoder(RobotMap.DIO.motorRightEncoderA, RobotMap.DIO.motorRightEncoderB);
	AHRS ahrs = new AHRS(SPI.Port.kMXP);

	// AhrsPIDSubsystem ahrsPIDSubsystem;
	WheelPIDSubsystem leftWheelPIDSubsystem;
	WheelPIDSubsystem rightWheelPIDSubsystem;

	boolean squaredInputs = false;
	double decimator = 1.0;
	boolean openLoop = true;

	public Chassis() {

		// initialize encoders before passing into PID controllers
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);
		leftEncoder.setName("leftEncoder");
		rightEncoder.setName("rightEncoder");
		
		rightGroup.setInverted(true);
		
		// initialize PIDSubsystems as a way to encapsulate the PID behavior
		leftWheelPIDSubsystem = new WheelPIDSubsystem("LeftWheelPID", .5, .05, 0, leftEncoder, leftGroup);
		rightWheelPIDSubsystem = new WheelPIDSubsystem("RightWheelPID", .5, .05, 0, rightEncoder, rightGroup);
		// ahrsPIDSubsystem = new AhrsPIDSubsystem(0.04, 0.0, 0.0, ahrs, leftWheelPIDSubsystem, rightWheelPIDSubsystem);
		enableOpenLoopDrive();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveDefault());
	}

	// open loop arcade drive
	public void enableOpenLoopDrive() {
		openLoop = true;
		leftWheelPIDSubsystem.disable();
		rightWheelPIDSubsystem.disable();
	}

	// closed loop arcade drive
	public void enableClosedLoopDrive() {
		openLoop = false;
		leftWheelPIDSubsystem.enable();
		rightWheelPIDSubsystem.enable();
	}

	public void drive(double forwardAxis, double turnAxis) {
		arcadeDrive(forwardAxis * decimator, turnAxis * decimator, squaredInputs);
	}

	public void enableAhrsDriveClosedLoop() {
		// this.ahrsPIDSubsystem.enable();
	}

	public void disableAhrsDriveClosedLoop() {
		// this.ahrsPIDSubsystem.disable();
	}

	public void ahrsDrive(double speed, double angle) {
		// this.ahrsPIDSubsystem.setSetpoint(degrees);
	}

	public double getAngle() {
		return ahrs.getAngle();
	}

	public void sendInfo() {

//		SmartDashboard.putNumber("AhrsDisplacementX", ahrs.getDisplacementX());
//		SmartDashboard.putNumber("AhrsDisplacementY", ahrs.getDisplacementY());
//		SmartDashboard.putNumber("AhrsDisplacementZ", ahrs.getDisplacementZ());
//		SmartDashboard.putNumber("AhrsAngle", ahrs.getAngle());
//		SmartDashboard.putNumber("EncoderLeftDistance", leftEncoder.getDistance());
//		SmartDashboard.putNumber("EncoderRightDistance", rightEncoder.getDistance());
//		SmartDashboard.putNumber("EncoderLeftSpeed", leftEncoder.getRate());
//		SmartDashboard.putNumber("EncoderRightSpeed", rightEncoder.getRate());
		SmartDashboard.putData(this);

		leftWheelPIDSubsystem.sendInfo();
		rightWheelPIDSubsystem.sendInfo();
		// ahrsPIDSubsystem.sendInfo();
	}

	// arcadeDrive code from wpilib modified to set setpoints on the wheel pid
	// controllers
	public void arcadeDrive(double xSpeed, double zRotation, boolean squaredInputs) {

		SmartDashboard.putNumber("A - xSpeed", xSpeed);
		SmartDashboard.putNumber("A - zRotation", zRotation);
		
		// Square the inputs (while preserving the sign) to increase fine control
		// while permitting full power.
		if (squaredInputs) {
			xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
			zRotation = Math.copySign(zRotation * zRotation, zRotation);
		}

		double leftMotorOutput;
		double rightMotorOutput;

		double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

		if (xSpeed >= 0.0) {
			// First quadrant, else second quadrant
			if (zRotation >= 0.0) {
				leftMotorOutput = maxInput;
				rightMotorOutput = xSpeed - zRotation;
			} else {
				leftMotorOutput = xSpeed + zRotation;
				rightMotorOutput = maxInput;
			}
		} else {
			// Third quadrant, else fourth quadrant
			if (zRotation >= 0.0) {
				leftMotorOutput = xSpeed + zRotation;
				rightMotorOutput = maxInput;
			} else {
				leftMotorOutput = maxInput;
				rightMotorOutput = xSpeed - zRotation;
			}
		}

		SmartDashboard.putNumber("A - leftOutput", leftMotorOutput);
		SmartDashboard.putNumber("A - rightOutput", rightMotorOutput);

		if (openLoop) {
			leftGroup.set(leftMotorOutput);
			rightGroup.set(rightMotorOutput);
		} else {
			leftWheelPIDSubsystem.setSetpoint(leftMotorOutput);
			rightWheelPIDSubsystem.setSetpoint(rightMotorOutput);
		}
	}

	public void resetTraveled() {
		// TODO Auto-generated method stub

	}

	public double getTraveled() {
		// TODO Auto-generated method stub
		return 0;
	}

}
