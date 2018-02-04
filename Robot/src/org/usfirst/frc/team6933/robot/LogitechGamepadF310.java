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
	public static final int DPAD_OFF = -1;
	public static final int DPAD_N = 0;
	public static final int DPAD_NE = 45;
	public static final int DPAD_E = 90;
	public static final int DPAD_SE = 135;
	public static final int DPAD_S = 180;
	public static final int DPAD_SW = 225;
	public static final int DPAD_W = 270;
	public static final int DPAD_NW = 315;

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
		

	}

	/**
	 * Gets the current value of the Logitech F310 DPad
	 * 
	 * @return An integer 0-8 where:
	 *         <ul>
	 *         <li>0 = OFF</li>
	 *         <li>1 = N,</li>
	 *         <li>2 = NE,</li>
	 *         <li>3 = E,</li>
	 *         <li>4 = SE,</li>
	 *         <li>5 = S,</li>
	 *         <li>6 = SW,</li>
	 *         <li>7 = W,</li>
	 *         <li>8 = NW</li>
	 *         </ul>
	 */
	public int getDPad() {
		int pov = this.getPOV(0);
		switch (pov) {
		case DPAD_OFF:
			return 0;
		case DPAD_N:
			return 1;
		case DPAD_NE:
			return 2;
		case DPAD_E:
			return 3;
		case DPAD_SE:
			return 4;
		case DPAD_S:
			return 5;
		case DPAD_SW:
			return 6;
		case DPAD_W:
			return 7;
		case DPAD_NW:
			return 8;
		}
		return -1;
	}

	/**
	 * Gets the x-axis of the left thumbstick
	 * 
	 * @return The double value of the axis
	 */
	public double getLXAxis() {
		return this.getRawAxis(LXAxis);
	}

	/**
	 * Gets the y-axis of the left thumbstick
	 * 
	 * @return The double value of the axis
	 */
	public double getLYAxis() {
		return -this.getRawAxis(LYAxis);
	}

	/**
	 * Gets the x-axis of the right thumbstick
	 * 
	 * @return The double value of the axis
	 */
	public double getRXAxis() {
		return this.getRawAxis(RXAxis);
	}

	/**
	 * Gets the y-axis of the right thumbstick
	 * 
	 * @return The double value of the axis
	 */
	public double getRYAxis() {
		return this.getRawAxis(RYAxis);
	}

}
