package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.RobotMap;
import org.usfirst.frc.team6933.robot.commands.drive.JoystickDriveDefaultOpenLoop;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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

	DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

	AhrsPIDSubsystem ahrsPIDSubsystem;
	WheelPIDSubsystem leftWheelPIDSubsystem;
	WheelPIDSubsystem rightWheelPIDSubsystem;

	boolean squaredInputs = false;
	double decimator = 0.5;

	public Chassis() {

		// initialize encoders before passing into PID controllers
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);

		// initialize PIDSubsystems as a way to encapsulate the PID behavior
		ahrsPIDSubsystem = new AhrsPIDSubsystem("Ahrs", 0.04,0.0,0.0,ahrs,leftGroup,rightGroup);
		rightWheelPIDSubsystem = new WheelPIDSubsystem("LeftWheelPID", 1.0, 0, 0, leftEncoder, leftGroup);
		leftWheelPIDSubsystem = new WheelPIDSubsystem("RightWheelPID", 1.0, 0, 0, rightEncoder, rightGroup);

	}

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveDefaultOpenLoop());
	}

	// open loop arcade drive
	public void enableOpenLoopDrive() {
		leftWheelPIDSubsystem.disable();
		rightWheelPIDSubsystem.disable();
	}

	public void disableOpenLoopDrive() {
		// seriously, not a thing
		// this method is provided for symmetry
	}

	public void driveOpenLoop(double forwardAxis, double turnAxis) {
		arcadeDriveOpenLoop(forwardAxis * decimator, turnAxis * decimator, squaredInputs);
	}

	// closed loop arcade drive
	public void enableClosedLoopDrive() {
		leftWheelPIDSubsystem.enable();
		rightWheelPIDSubsystem.enable();
	}

	public void disableClosedLoopDrive() {
		leftWheelPIDSubsystem.disable();
		rightWheelPIDSubsystem.disable();
	}

	public void driveClosedLoop(double forwardAxis, double turnAxis) {
		arcadeDriveClosedLoop(forwardAxis * decimator, turnAxis * decimator, squaredInputs);
	}

	public void enableAhrsDriveClosedLoop() {
		this.leftWheelPIDSubsystem.enable();
		this.rightWheelPIDSubsystem.enable();
		this.ahrsPIDSubsystem.enable();
	}

	public void disableAhrsDriveClosedLoop() {
		this.leftWheelPIDSubsystem.disable();
		this.rightWheelPIDSubsystem.disable();
		this.ahrsPIDSubsystem.disable();
	}

	public void ahrsDriveClosedLoop(double ySpeed, double zRotation) {
		// TODO Auto-generated method stub

	}

	public double getAngle() {
		return ahrs.getAngle();
	}

	public void sendInfo() {

		SmartDashboard.putNumber("AhrsDisplacementX", ahrs.getDisplacementX());
		SmartDashboard.putNumber("AhrsDisplacementY", ahrs.getDisplacementY());
		SmartDashboard.putNumber("AhrsDisplacementZ", ahrs.getDisplacementZ());
		SmartDashboard.putNumber("AhrsAngle", ahrs.getAngle());
		SmartDashboard.putNumber("EncoderLeftDistance", leftEncoder.getDistance());
		SmartDashboard.putNumber("EncoderRightDistance", rightEncoder.getDistance());
		SmartDashboard.putNumber("EncoderLeftSpeed", leftEncoder.getRate());
		SmartDashboard.putNumber("EncoderRightSpeed", rightEncoder.getRate());
		SmartDashboard.putData(this);

		leftWheelPIDSubsystem.sendInfo();
		rightWheelPIDSubsystem.sendInfo();
		ahrsPIDSubsystem.sendInfo();
	}

	public void arcadeDriveOpenLoop(double xSpeed, double zRotation, boolean squaredInputs) {
		drive.arcadeDrive(xSpeed, zRotation, squaredInputs);
	}

	// arcadeDrive code from wpilib modified to set setpoints on the wheel pid
	// controllers
	public void arcadeDriveClosedLoop(double xSpeed, double zRotation, boolean squaredInputs) {

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

		leftWheelPIDSubsystem.setSetpoint(leftMotorOutput);
		rightWheelPIDSubsystem.setSetpoint(rightMotorOutput);

	}

}
