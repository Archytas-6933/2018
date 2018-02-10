package org.usfirst.frc.team6933.robot.commands.grabber;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class GrabberClose extends TimedCommand {

	public GrabberClose() {
		super(Robot.arm.solenoidPulseWidth);
		requires(Robot.grabber);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println(this.getClass().getSimpleName() + " initalize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.grabber.close();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		// System.out.println(this.getClass().getSimpleName() + " end");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		// System.out.println(this.getClass().getSimpleName() + " interrupted");
	}
}
