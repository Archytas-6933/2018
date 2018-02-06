/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6933.robot;

import org.usfirst.frc.team6933.robot.commands.arm.ArmDown;
import org.usfirst.frc.team6933.robot.commands.arm.ArmRelease;
import org.usfirst.frc.team6933.robot.commands.arm.ArmUp;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousLeft;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousRight;
import org.usfirst.frc.team6933.robot.commands.compressor.CompressorToggle;
import org.usfirst.frc.team6933.robot.commands.drive.SelectChassisClosedLoop;
import org.usfirst.frc.team6933.robot.commands.drive.SelectChassisOpenLoop;
import org.usfirst.frc.team6933.robot.commands.grabber.GrabberClose;
import org.usfirst.frc.team6933.robot.commands.grabber.GrabberOpen;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public LogitechExtreme3D driver = new LogitechExtreme3D(0);
	public LogitechGamepadF310 operator = new LogitechGamepadF310(1);

	public OI() {
		super();

		// start the autonomous testing group
		operator.LeftJoyClick.whenPressed(new AutonomousLeft());
		operator.RightJoyClick.whenPressed(new AutonomousRight());

		// open/close the grabber
		operator.BButton.whenPressed(new GrabberOpen());
		operator.XButton.whenPressed(new GrabberClose());

		// raise/lower and release the arm
		operator.YButton.whenPressed(new ArmUp());
		operator.AButton.whenPressed(new ArmDown());
		operator.StartButton.whenPressed(new ArmRelease());

		// turn on/off the compressor - for testing and demo mostly
		//operator.AButton.toggleWhenPressed(new CompressorToggle());
		
		// select open/close loop drive
		driver.ThumbButtonUpperLeft.whenPressed(new SelectChassisOpenLoop());
		driver.ThumbButtonUpperRight.whenPressed(new SelectChassisClosedLoop());
		
	}

	public double getYSpeed() {
		return driver.getYAxis();
	}

	public double getZRotation() {
		return driver.getZAxis();
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
