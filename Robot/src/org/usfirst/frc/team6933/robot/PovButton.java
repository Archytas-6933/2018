package org.usfirst.frc.team6933.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * A {@link Button} that gets its state from a {@link GenericHID}.
 */
public class PovButton extends Button {

	GenericHID joystick;
	private int direction;

	public PovButton(GenericHID joystick, int direction) {
		this.joystick = joystick;
		this.direction = direction;
	}

	@Override
	public boolean get() {
		return joystick.getPOV() == direction;
	}

	public int getDirection() {
		return direction;
	}

}

