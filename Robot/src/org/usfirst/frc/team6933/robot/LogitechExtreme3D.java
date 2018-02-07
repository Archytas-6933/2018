package org.usfirst.frc.team6933.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class LogitechExtreme3D extends Joystick {

	int XAxis = 0;
	int YAxis = 1;
	int ZAxis = 2;
	int throttleAxis = 3;
	
	JoystickButton Trigger = new JoystickButton(this, 1);
	JoystickButton SidePickle = new JoystickButton(this, 2);
	JoystickButton ThumbButtonLowerLeft = new JoystickButton(this, 3);
	JoystickButton ThumbButtonLowerRight = new JoystickButton(this, 4);
	JoystickButton ThumbButtonUpperLeft = new JoystickButton(this, 5);
	JoystickButton ThumbButtonUpperRight = new JoystickButton(this, 6);
	JoystickButton BaseButtonUpperLeft = new JoystickButton(this, 7);
	JoystickButton BaseButtonUpperRight = new JoystickButton(this, 8);
	JoystickButton BaseButtonMiddleLeft = new JoystickButton(this, 9);
	JoystickButton BaseButtonMiddleRight = new JoystickButton(this, 10);
	JoystickButton BaseButtonBottomLeft = new JoystickButton(this, 11);
	JoystickButton BaseButtonBottomRight = new JoystickButton(this, 12);

	PovButton DPadButtonN = new PovButton(this, 0);
	PovButton DPadButtonNE = new PovButton(this, 45);
	PovButton DPadButtonE = new PovButton(this, 90);
	PovButton DPadButtonSE = new PovButton(this, 135);
	PovButton DPadButtonS = new PovButton(this, 180);
	PovButton DPadButtonSW = new PovButton(this, 225);
	PovButton DPadButtonW = new PovButton(this, 270);
	PovButton DPadButtonNW = new PovButton(this, 315);

	public LogitechExtreme3D(int port) {
		super(port);

		// add button debug print commands
		Trigger.whenPressed(new PrintCommand("Trigger"));
		SidePickle.whenPressed(new PrintCommand("SidePickle"));
		ThumbButtonLowerLeft.whenPressed(new PrintCommand("ThumbButtonLowerLeft"));
		ThumbButtonLowerRight.whenPressed(new PrintCommand("ThumbButtonLowerRight"));
		ThumbButtonUpperLeft.whenPressed(new PrintCommand("ThumbButtonUpperLeft"));
		ThumbButtonUpperRight.whenPressed(new PrintCommand("ThumbButtonUpperRight"));
		BaseButtonUpperLeft.whenPressed(new PrintCommand("BaseButtonUpperLeft"));
		BaseButtonUpperRight.whenPressed(new PrintCommand("BaseButtonUpperRight"));
		BaseButtonMiddleLeft.whenPressed(new PrintCommand("BaseButtonMiddleLeft"));
		BaseButtonMiddleRight.whenPressed(new PrintCommand("BaseButtonMiddleRight"));
		BaseButtonBottomLeft.whenPressed(new PrintCommand("BaseButtonBottomLeft"));
		BaseButtonBottomRight.whenPressed(new PrintCommand("BaseButtonBottomRight"));

		DPadButtonN.whenPressed(new PrintCommand("DPadButtonN"));
		DPadButtonNE.whenPressed(new PrintCommand("DPadButtonNE"));
		DPadButtonE.whenPressed(new PrintCommand("DPadButtonE"));
		DPadButtonSE.whenPressed(new PrintCommand("DPadButtonSE"));
		DPadButtonS.whenPressed(new PrintCommand("DPadButtonS"));
		DPadButtonSW.whenPressed(new PrintCommand("DPadButtonSW"));
		DPadButtonW.whenPressed(new PrintCommand("DPadButtonW"));
		DPadButtonNW.whenPressed(new PrintCommand("DPadButtonNW"));
	}
	

	public double getXAxis() {
		return this.getRawAxis(XAxis);
	}

	public double getYAxis() {
		return -this.getRawAxis(YAxis);
	}

	public double getZAxis() {
		return this.getRawAxis(ZAxis);
	}

	public int getThrottleAxis() {
		return throttleAxis;
	}



}
