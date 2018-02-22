package org.usfirst.frc.team6933.robot.commands.drive;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickDriveDefault extends Command {

	public JoystickDriveDefault() {
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println(this.getClass().getSimpleName() + " initalize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.chassis.drive(Robot.oi.getYSpeed(), Robot.oi.getZRotation()/2);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false; // default command never finish
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
//		System.out.println(this.getClass().getSimpleName() + " end");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
//		System.out.println(this.getClass().getSimpleName() + " interrupted");
	}
}
