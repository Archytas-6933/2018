package org.usfirst.frc.team6933.robot.commands.drive;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TurnDegrees extends TimedCommand {

	double angleTarget;
	
	public TurnDegrees(double angle) {
		super(5.0);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.chassis);
		angleTarget = angle;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println(this.getClass().getName() + " initalize");
		Robot.chassis.setAhrsControlDrive();
		Robot.chassis.setAhrsTarget(angleTarget);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.chassis.isAtTargetAngle() || isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println(this.getClass().getName() + " end");
		Robot.chassis.stopAhrsPID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		// System.out.println(this.getClass().getName() + " interrupted");
	}
}
