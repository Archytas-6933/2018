package org.usfirst.frc.team6933.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team6933.robot.RobotMap;
import org.usfirst.frc.team6933.robot.commands.drive.JoystickDriveDefault;
import org.usfirst.frc.team6933.robot.control.AhrsControl;
import org.usfirst.frc.team6933.robot.control.IChassisControl;
import org.usfirst.frc.team6933.robot.control.OpenLoopControl;
import org.usfirst.frc.team6933.robot.control.PositionControl;
import org.usfirst.frc.team6933.robot.control.VelocityControl;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Chassis extends Subsystem {

	public static final int L = 0;
	public static final int R = 1;

	public enum ControlType {
		Velocity, Position, Ahrs, OpenLoop
	};

	// determine max speed
	public double wheelCircumfrenceMeters = 6 * 2.54 * Math.PI / 100;
	public final double distancePerPulse = wheelCircumfrenceMeters / 360; // meters
	public int cimNoLoadRpm = 5310; // 2.5" CIM Motor (am-0255)
	public double toughBoxMiniRatio = 10.71; // ToughBox Mini for KoP Chassis (am-14u3), 10.71:1 Ratio (am-2598_107)
	public double toughBoxMiniOutputRpm = cimNoLoadRpm / toughBoxMiniRatio;
	public double chassisNoLoadMps = toughBoxMiniOutputRpm * wheelCircumfrenceMeters / 60; // 3.95

	// define AHRS
	AHRS ahrs = new AHRS(SPI.Port.kMXP);

	SpeedControllerGroup[] motor = new SpeedControllerGroup[2];
	Encoder[] encoder = new Encoder[2];

	VelocityControl velocityControl;
	PositionControl positionControl;
	OpenLoopControl openLoopControl;
	AhrsControl ahrsControl;

	boolean squaredInputs = false;
	double decimator = 1.0;

	Map<ControlType, IChassisControl> allControls = new HashMap<ControlType, IChassisControl>();
	ControlType currentControlMode;

	public Chassis() {

		// initialize encoders before passing into PID controllers
		encoder[L] = new Encoder(RobotMap.DIO.motorLeftEncoderA, RobotMap.DIO.motorLeftEncoderB, true);
		encoder[L].setDistancePerPulse(distancePerPulse);
		encoder[L].setName("leftEncoder");

		encoder[R] = new Encoder(RobotMap.DIO.motorRightEncoderA, RobotMap.DIO.motorRightEncoderB);
		encoder[R].setDistancePerPulse(distancePerPulse);
		encoder[R].setName("rightEncoder");

		// initialize motor speed controller groups with pairs of Talon motor
		// controllers
		motor[L] = new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.CAN.motorLeftA),
				new WPI_TalonSRX(RobotMap.CAN.motorLeftB));
		motor[R] = new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.CAN.motorRightA),
				new WPI_TalonSRX(RobotMap.CAN.motorRightB));
		motor[R].setInverted(true);

		// initialize control subsystems to encapsulate the PID behavior
		velocityControl = new VelocityControl(encoder, motor);
		positionControl = new PositionControl(encoder, velocityControl); 
		openLoopControl = new OpenLoopControl(encoder, motor);
		ahrsControl = new AhrsControl(ahrs, velocityControl);

		// add controls to map
		allControls.put(ControlType.OpenLoop, openLoopControl);
		allControls.put(ControlType.Velocity, velocityControl);
		allControls.put(ControlType.Position, positionControl);
		allControls.put(ControlType.Ahrs, ahrsControl);

		this.currentControlMode = ControlType.OpenLoop;

	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveDefault());
	}

	// open loop arcade drive
	public void setOpenLoopDrive() {
		setCurrentControlMode(ControlType.OpenLoop);
	}

	// open loop arcade drive
	public void setAhrsControlDrive() {
		setCurrentControlMode(ControlType.Ahrs);
	}

	public void setAhrsTarget(double angle) {
		ahrsControl.setTargetAngle(angle);
	}

	public boolean isAtTargetAngle() {
		return ahrsControl.onTarget();
	}

	public void stopAhrsPID() {
		ahrsControl.disable();
	}

	public void setDistanceTarget(double distance) {
		positionControl.setTargetDistance(distance);
	}

	public boolean isAtTargetDistance() {
		return positionControl.onTarget();
	}

	public void stopDistancePID() {
		positionControl.disable();
	}

	// open loop arcade drive
	public void setPositionControlDrive() {
		setCurrentControlMode(ControlType.Position);
	}

	// open loop arcade drive
	public void setVelocityControlDrive() {
		setCurrentControlMode(ControlType.Velocity);
	}

	public void drive(double forwardAxis, double turnAxis) {
		arcadeDrive(forwardAxis * decimator, turnAxis * decimator, squaredInputs);
	}

	public void ahrsDrive(double speed, double angle) {
		// this.ahrsPIDSubsystem.setSetpoint(degrees);
	}

	public double getAngle() {
		return ahrs.getAngle();
	}

	public void sendInfo() {
		SmartDashboard.putData(this);
		allControls.get(currentControlMode).sendInfo();
	}

	// arcadeDrive code from wpilib modified to set setpoints on the wheel pid
	// controllers
	private void arcadeDrive(double xSpeed, double zRotation, boolean squaredInputs) {

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

		// send to any control mode, if in appropriate for that mode they are just stub
		// functions
		this.allControls.get(currentControlMode).setMotorOutput(leftMotorOutput, rightMotorOutput);

	}

	private void setCurrentControlMode(ControlType mode) {

		if (mode != currentControlMode) {
			System.out.println("Switching from " + currentControlMode.toString() + " to " + mode.toString());
		}

		// disable all
		for (Map.Entry<ControlType, IChassisControl> entry : allControls.entrySet()) {
			entry.getValue().disable();
		}

		// enable given mode
		allControls.get(mode).enable();
		this.currentControlMode = mode;
	}

}
