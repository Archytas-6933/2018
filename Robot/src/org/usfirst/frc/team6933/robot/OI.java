/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6933.robot;

import org.usfirst.frc.team6933.robot.commands.arm.ArmDown;
import org.usfirst.frc.team6933.robot.commands.arm.ArmLatch;
import org.usfirst.frc.team6933.robot.commands.arm.ArmUnlatch;
import org.usfirst.frc.team6933.robot.commands.arm.ArmUp;
import org.usfirst.frc.team6933.robot.commands.arm.CancelSomeCommand;
import org.usfirst.frc.team6933.robot.commands.arm.Eject;
import org.usfirst.frc.team6933.robot.commands.arm.GrabberClose;
import org.usfirst.frc.team6933.robot.commands.arm.GrabberOpen;
import org.usfirst.frc.team6933.robot.commands.arm.GrabberOpenAndOptionalEject;
import org.usfirst.frc.team6933.robot.commands.arm.OpenGrabberWhenCloseToWall;
import org.usfirst.frc.team6933.robot.commands.arm.StartSomeCommand;
import org.usfirst.frc.team6933.robot.commands.drive.PrecisionDrive;
import org.usfirst.frc.team6933.robot.commands.drive.SquaredInputDrive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

//	public LogitechExtreme3D driver = new LogitechExtreme3D(0);
	public LogitechGamepadF310 driver = new LogitechGamepadF310(0);
	public LogitechGamepadF310 operator = new LogitechGamepadF310(1);
	
	public static int driverMode = 0;

	public OI() {
		super();

		// select open/close loop drive
//		driver.LBButton.whenPressed(new SetOpenLoopDrive());
//		driver.RBButton.whenPressed(new SetVelocityControlDrive());
//		driver.ThumbButtonUpperLeft.whenPressed(new SetOpenLoopDrive());
//		driver.ThumbButtonUpperRight.whenPressed(new SetVelocityControlDrive());
		
		// start the autonomous testing group
//		operator.LeftJoyClick.whenPressed(new AutonomousLeft());
//		operator.RightJoyClick.whenPressed(new AutonomousRight());

		// open/close the grabber
		operator.RBButton.whenPressed(new GrabberOpenAndOptionalEject());//GrabberOpen
		operator.LBButton.whenPressed(new GrabberClose());

		// raise/lower and release the arm
		operator.YButton.whenPressed(new ArmUp());
		operator.AButton.whenPressed(new ArmDown());
		operator.StartButton.whenPressed(new ArmUnlatch());
		operator.BackButton.whenPressed(new ArmLatch());

		// opens the grabber when close enough to wall - if button pressed
		Command openGrabberWhenCloseToWall = new OpenGrabberWhenCloseToWall(Robot.chassis.wallProximity);
		operator.RightTriggerButton.whenPressed(new StartSomeCommand(openGrabberWhenCloseToWall));
		operator.RightTriggerButton.whenReleased(new CancelSomeCommand(openGrabberWhenCloseToWall));
		
		// ejector pressure selection
		operator.LeftTriggerButton.whenPressed(new Eject());
		
		driver.RightTriggerButton.whileHeld(new PrecisionDrive());
		driver.LeftTriggerButton.whileHeld(new SquaredInputDrive());
		

		
	}

	public double getYSpeed() {
		double YSpeed = 0;
		YSpeed = driver.getLYAxis();
		return YSpeed;
	}

	public double getZRotation() {
		double ZSpeed = driver.getRXAxis();	
		return ZSpeed;
	}

	// one of the eject pressure buttons is being held down
	public boolean isEjectPressureButtonPressed() {
		return operator.DPadButtonN.get() || operator.DPadButtonS.get();
	}

	public boolean isHighPressurePressed() {
		return operator.DPadButtonN.get();
	}
	

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
