package org.usfirst.frc.team6933.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team6933.robot.RobotMap;
import org.usfirst.frc.team6933.robot.commands.drive.JoystickDriveDefault;
import org.usfirst.frc.team6933.robot.control.AhrsPIDSubsystem;
import org.usfirst.frc.team6933.robot.control.OpenLoopControlSubsystem;
import org.usfirst.frc.team6933.robot.control.PositionControlPIDSubsystem;
import org.usfirst.frc.team6933.robot.control.VelocityControlPIDSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

	final double distancePerPulse = 6 * 2.54 * Math.PI / 100 / 360; // meters

	// define AHRS
	AHRS ahrs = new AHRS(SPI.Port.kMXP);

	// define motors controllers
	WPI_TalonSRX leftMotorA = new WPI_TalonSRX(RobotMap.CAN.motorLeftA);
	WPI_TalonSRX leftMotorB = new WPI_TalonSRX(RobotMap.CAN.motorLeftB);
	WPI_TalonSRX rightMotorA = new WPI_TalonSRX(RobotMap.CAN.motorRightA);
	WPI_TalonSRX rightMotorB = new WPI_TalonSRX(RobotMap.CAN.motorRightB);

	SpeedControllerGroup[] motor = new SpeedControllerGroup[2];
	Encoder[] encoder = new Encoder[2];

	VelocityControl velocityControl;
	PositionControl positionControl;
	OpenLoopControl openLoopControl;
	AhrsControl ahrsControl;
	
	boolean squaredInputs = false;
	double decimator = 1.0;
	boolean openLoop = true;

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

		// initialize motor speed controller groups
		motor[L] = new SpeedControllerGroup(leftMotorA, leftMotorB);

		motor[R] = new SpeedControllerGroup(rightMotorA, rightMotorB);
		motor[R].setInverted(true);

		// initialize control subsystems to encapsulate the PID behavior
		velocityControl = new VelocityControl(encoder, motor);
		positionControl = new PositionControl(encoder,velocityControl);
		openLoopControl = new OpenLoopControl(encoder,motor);
		ahrsControl = new AhrsControl(ahrs, velocityControl); // this one uses the velocity control for more precise driving
		
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
		velocityControl.enable();
	}
	
	public void setAhrsTarget(double angle) {
		ahrsControl.controller.setTargetAngle(angle);
	}
	
	public boolean isAtTargetAngle() {
		return ahrsControl.controller.onTarget();
	}
	
	public void stopAhrsPID() {
		ahrsControl.controller.disable();	
	}
	
	public void setDistanceTarget(double distance) {
		positionControl.setTargetDistance(distance);		
	}
	
	public boolean isAtTargetDistance() {
		return positionControl.onTarget();
	}
	
	public void stopDistancePID() {
		positionControl.controller[L].disable();				
		positionControl.controller[R].disable();				
	}

	// open loop arcade drive
	public void setPositionControlDrive() {
		setCurrentControlMode(ControlType.Position);
		velocityControl.enable();
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

		if ( mode != currentControlMode ) {
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


	private interface IChassisControl {

		public abstract void sendInfo();

		public abstract void enable();

		public abstract void disable();

		public abstract void setMotorOutput(double leftMotorOutput, double rightMotorOutput);

	}

	private class AhrsControl implements IChassisControl {

		AhrsPIDSubsystem controller;

		public AhrsControl(AHRS ahrs, VelocityControl velocityControls) {
			controller = new AhrsPIDSubsystem("Ahrs", 1.0, 0.1, 3.0, ahrs, velocityControls);
		}

		@Override
		public void sendInfo() {
			controller.sendInfo();
		}

		@Override

		public void enable() {
			controller.resetTraveled(); // reset every time we enable to all measurements are relative to that instant
			controller.resetAngle();
			controller.enable();
		}

		@Override
		public void disable() {
			controller.disable();
		}

		@Override
		public void setMotorOutput(double leftMotorOutput, double rightMotorOutput) {
			// n/a
		}

	}

	public class VelocityControl implements IChassisControl {

		VelocityControlPIDSubsystem[] controller = new VelocityControlPIDSubsystem[2];

		public VelocityControl(Encoder[] encoder, SpeedControllerGroup[] motor) {
			controller[L] = new VelocityControlPIDSubsystem("Left", 1, 0, 0.3, encoder[L], motor[L]);
			controller[R] = new VelocityControlPIDSubsystem("Right", 1, 0, 0.3, encoder[R], motor[R]);
		}

		public void setSetpoints(double leftMotorOutput, double rightMotorOutput) {
			controller[L].setSetpoint(leftMotorOutput);
			controller[R].setSetpoint(rightMotorOutput);
		}

		public void setSetpointsSymmetrical(double output) {
			controller[L].setSetpoint(output);
			controller[R].setSetpoint(-output);
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
			setSetpoints(leftMotorOutput, rightMotorOutput);
		}

	}

	private class PositionControl implements IChassisControl {

		PositionControlPIDSubsystem[] controller = new PositionControlPIDSubsystem[2];

		public PositionControl(Encoder[] encoder, VelocityControl velocityControl) {
			controller[L] = new PositionControlPIDSubsystem("Left", 1.0, 0.1, 0.3, encoder[L], velocityControl.controller[L]);
			controller[R] = new PositionControlPIDSubsystem("Right", 1.0, 0.1, 0.3, encoder[R], velocityControl.controller[R]);
		}

		@Override
		public void disable() {
			controller[L].disable();
			controller[R].disable();
		}

		@Override
		public void enable() {
			controller[L].enable();
			controller[R].enable();
		}

		@Override
		public void sendInfo() {
			controller[L].sendInfo();
			controller[R].sendInfo();
		}

		@Override
		public void setMotorOutput(double leftMotorOutput, double rightMotorOutput) {
			// n/a
		}

		public void setTargetDistance(double distance) {
			encoder[L].reset();
			encoder[R].reset();
			controller[L].setSetpoint(distance);
			controller[R].setSetpoint(distance);
		}
		
		public boolean onTarget() {
			return controller[L].onTarget() && controller[R].onTarget();
		}
	}

	private class OpenLoopControl implements IChassisControl {

		OpenLoopControlSubsystem[] controller = new OpenLoopControlSubsystem[2];

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

}
