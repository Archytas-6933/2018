package org.usfirst.frc.team6933.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * A {@link Button} that gets its state from a {@link GenericHID}.
 */
public class AxisButton extends Button {

	GenericHID joystick;
	private double threshold;
	private int axis;

	public AxisButton(GenericHID joystick, int axis, double threshold) {
		this.joystick = joystick;
		this.axis = axis;
		this.threshold = threshold;
	}

	@Override
	public boolean get() {
		return joystick.getRawAxis(axis) >= threshold;
	}

}

