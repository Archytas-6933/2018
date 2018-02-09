package org.usfirst.frc.team6933.robot.commands.drive;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetOpenLoopDrive extends InstantCommand {

	public SetOpenLoopDrive() {
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.chassis.setOpenLoopDrive();
	}

}
