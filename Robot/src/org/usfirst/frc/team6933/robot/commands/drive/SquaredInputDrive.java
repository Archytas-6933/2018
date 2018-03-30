package org.usfirst.frc.team6933.robot.commands.drive;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SquaredInputDrive extends InstantCommand {

	public SquaredInputDrive() {

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println(this.getClass().getSimpleName() + " initalize");
		Robot.chassis.setSquaredInputs(true);
	}

	@Override
	protected void execute() {

	}

	@Override
	protected void end() {
		System.out.println(this.getClass().getSimpleName() + " end");
		Robot.chassis.setSquaredInputs(false);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
