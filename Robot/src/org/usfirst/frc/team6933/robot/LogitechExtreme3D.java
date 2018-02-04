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
public class LogitechExtreme3D extends Joystick {

	public static final int DPAD_OFF = -1;
	public static final int DPAD_N = 0;
	public static final int DPAD_NE = 45;
	public static final int DPAD_E = 90;
	public static final int DPAD_SE = 135;
	public static final int DPAD_S = 180;
	public static final int DPAD_SW = 225;
	public static final int DPAD_W = 270;
	public static final int DPAD_NW = 315;

	int XAxis = 0;
	int YAxis = 1;
	int ZAxis = 2;

	int throttleAxis = 3;

	int DPadAxis = 4;

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

	}

	/**
	 * Gets the current value of the Extreme 3D Pro DPad
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
	 * Gets the x-axis
	 * 
	 * @return The double value of the axis
	 */
	public double getXAxis() {
		return this.getRawAxis(XAxis);
	}

	/**
	 * Gets the y-axis
	 * 
	 * @return The double value of the axis
	 */
	public double getYAxis() {
		return -this.getRawAxis(YAxis);
	}

	/**
	 * Gets the x-axis
	 * 
	 * @return The double value of the axis
	 */
	public double getZAxis() {
		return this.getRawAxis(ZAxis);
	}

	public int getThrottleAxis() {
		return throttleAxis;
	}

	public int getDPadAxis() {
		return DPadAxis;
	}

}
