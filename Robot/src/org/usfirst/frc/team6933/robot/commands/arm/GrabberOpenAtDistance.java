package org.usfirst.frc.team6933.robot.commands.arm;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class GrabberOpenAtDistance extends Command {

	protected double threshold;
	boolean active = false;

	public GrabberOpenAtDistance(double threshold) {
		this.threshold = threshold;
		// Use requires() here to declare subsystem dependencies
		// requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		active = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double dist = Robot.ultrasonic.getDistance();
		if (active && dist < threshold) {
			Command command = new GrabberOpen();
			command.start();
			active = false;

		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
