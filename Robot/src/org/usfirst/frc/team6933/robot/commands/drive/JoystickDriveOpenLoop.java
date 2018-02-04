package org.usfirst.frc.team6933.robot.commands.drive;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickDriveOpenLoop extends Command {

	public JoystickDriveOpenLoop() {
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println(this.getClass().getName() + " initalize");
		Robot.chassis.setOpenLoop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// get values of gamepad1 axes
		double lxAxis = Robot.oi.operator.getLXAxis();
		double lyAxis = Robot.oi.operator.getLYAxis();
		
		// run the drive function given the above values
		Robot.chassis.drive(lyAxis, lxAxis);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false; // default command never finish
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println(this.getClass().getName() + " end");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println(this.getClass().getName() + " interrupted");
	}
}
