package org.usfirst.frc.team6933.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 * A Joystick definition for the Logitech Gamepad F310
 * 
 * Use this class instead of Joystick when you are using a Logitech F310. This
 * class has definitions for every button and axis on the controller
 * 
 * @author vtleavs
 *
 */
public class LogitechGamepadF310 extends Joystick {

	int LXAxis = 0; // was '4'
	int LYAxis = 1;
	int RXAxis = 3;
	int RYAxis = 0;

	int triggerAxis = 2;

	int DPadAxis = 6;

	JoystickButton AButton = new JoystickButton(this, 1);
	JoystickButton BButton = new JoystickButton(this, 2);
	JoystickButton XButton = new JoystickButton(this, 3);
	JoystickButton YButton = new JoystickButton(this, 4);
	JoystickButton LBButton = new JoystickButton(this, 5);
	JoystickButton RBButton = new JoystickButton(this, 6);
	JoystickButton BackButton = new JoystickButton(this, 7);
	JoystickButton StartButton = new JoystickButton(this, 8);
	JoystickButton LeftJoyClick = new JoystickButton(this, 9);
	JoystickButton RightJoyClick = new JoystickButton(this, 10);

	PovButton DPadButtonN = new PovButton(this, 0); 
	PovButton DPadButtonNE = new PovButton(this, 45);
	PovButton DPadButtonE = new PovButton(this, 90);
	PovButton DPadButtonSE = new PovButton(this, 135);
	PovButton DPadButtonS = new PovButton(this, 180);
	PovButton DPadButtonSW = new PovButton(this, 225);
	PovButton DPadButtonW = new PovButton(this, 270);
	PovButton DPadButtonNW = new PovButton(this, 315);

	public LogitechGamepadF310(int port) {
		super(port);

		// add button debug print commands
		AButton.whenPressed(new PrintCommand("AButton"));
		BButton.whenPressed(new PrintCommand("BButton"));
		XButton.whenPressed(new PrintCommand("XButton"));
		YButton.whenPressed(new PrintCommand("YButton"));
		LBButton.whenPressed(new PrintCommand("LBButton"));
		RBButton.whenPressed(new PrintCommand("RBButton"));
		BackButton.whenPressed(new PrintCommand("BackButton"));
		StartButton.whenPressed(new PrintCommand("StartButton"));
		LeftJoyClick.whenPressed(new PrintCommand("LeftJoyClick"));
		RightJoyClick.whenPressed(new PrintCommand("RightJoyClick"));

		DPadButtonN.whenPressed(new PrintCommand("DPadButtonN"));
		DPadButtonNE.whenPressed(new PrintCommand("DPadButtonNE"));
		DPadButtonE.whenPressed(new PrintCommand("DPadButtonE"));
		DPadButtonSE.whenPressed(new PrintCommand("DPadButtonE"));
		DPadButtonS.whenPressed(new PrintCommand("DPadButtonE"));
		DPadButtonSW.whenPressed(new PrintCommand("DPadButtonSW"));
		DPadButtonW.whenPressed(new PrintCommand("DPadButtonW"));
		DPadButtonNW.whenPressed(new PrintCommand("DPadButtonNW"));

	}

	public double getLXAxis() {
		return this.getRawAxis(LXAxis);
	}

	public double getLYAxis() {
		return -this.getRawAxis(LYAxis);
	}

	public double getRXAxis() {
		return this.getRawAxis(RXAxis);
	}

	public double getRYAxis() {
		return this.getRawAxis(RYAxis);
	}

}
