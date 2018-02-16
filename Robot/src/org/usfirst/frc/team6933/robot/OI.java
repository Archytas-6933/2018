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
import org.usfirst.frc.team6933.robot.commands.arm.GrabberClose;
import org.usfirst.frc.team6933.robot.commands.arm.GrabberOpen;
import org.usfirst.frc.team6933.robot.commands.compressor.CompressorToggle;
import org.usfirst.frc.team6933.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team6933.robot.commands.drive.DriveTimed;
import org.usfirst.frc.team6933.robot.commands.drive.JogCommand;
import org.usfirst.frc.team6933.robot.commands.drive.SetOpenLoopDrive;
import org.usfirst.frc.team6933.robot.commands.drive.SetVelocityControlDrive;
import org.usfirst.frc.team6933.robot.commands.drive.SwitchDriverMode;
import org.usfirst.frc.team6933.robot.commands.drive.TurnDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public LogitechExtreme3D driver = new LogitechExtreme3D(0);
	public LogitechGamepadF310 operator = new LogitechGamepadF310(1);
	
	public static int driverMode = 0;

	public OI() {
		super();

		
		// select open/close loop drive
		driver.ThumbButtonUpperLeft.whenPressed(new SetOpenLoopDrive());
		driver.ThumbButtonUpperRight.whenPressed(new SetVelocityControlDrive());
		
		// start the autonomous testing group
//		operator.LeftJoyClick.whenPressed(new AutonomousLeft());
//		operator.RightJoyClick.whenPressed(new AutonomousRight());

		// open/close the grabber
		operator.BButton.whenPressed(new GrabberOpen());
		operator.XButton.whenPressed(new GrabberClose());

		// raise/lower and release the arm
		operator.YButton.whenPressed(new ArmUp());
		operator.AButton.whenPressed(new ArmDown());
		operator.StartButton.whenPressed(new ArmUnlatch());
		operator.BackButton.whenPressed(new ArmLatch());

		// toggle compressor on/off - for testing and demo mostly
		operator.RightJoyClick.toggleWhenPressed(new CompressorToggle());
	
		// jog commands
		driver.DPadButtonN.whenPressed(new JogCommand(driver.DPadButtonN));
		driver.DPadButtonE.whenPressed(new JogCommand(driver.DPadButtonE));
		driver.DPadButtonS.whenPressed(new JogCommand(driver.DPadButtonS));
		driver.DPadButtonW.whenPressed(new JogCommand(driver.DPadButtonW));
		
		// button to drive forward Y direction only for testing
//		driver.BaseButtonBottomLeft.whileHeld(new DriveTimed(.5,0,10));
		
		CommandGroup testauto = new CommandGroup();
		testauto.addSequential(new TurnDegrees(45));
		testauto.addSequential(new DriveDistance(1.0));
		testauto.addSequential(new DriveDistance(-1.0));
		testauto.addSequential(new TurnDegrees(-45));
		
		//driver.BaseButtonBottomLeft.whenPressed(testauto);
		
		
		
		driver.BaseButtonBottomLeft.whenPressed(new SwitchDriverMode());
		
	}

	public double getYSpeed() {
		double YSpeed = 0;
		YSpeed = operator.getLYAxis() + driver.getYAxis();
		
		if (YSpeed > 1)
			return 1;
		
		else if (YSpeed < -1)
			return -1;
		
		return YSpeed;
	}

	public double getZRotation() {
		double ZSpeed = driver.getZAxis();
		if (driverMode == 1)
			ZSpeed = driver.getXAxis();
		
		if (Math.abs(ZSpeed) < 0.2)
			ZSpeed = 0;
		
		ZSpeed += operator.getRXAxis();
		
		//SmartDashboard.putNumber("Twist", driver.getZAxis());
		

		if (ZSpeed >  1)
			return 1;
		
		else if (ZSpeed < -1)
			return -1;
		
		return ZSpeed;
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
