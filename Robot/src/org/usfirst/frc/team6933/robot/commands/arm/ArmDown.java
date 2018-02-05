package org.usfirst.frc.team6933.robot.commands.arm;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmDown extends Command {

	public ArmDown() {
		super("ArmDownCommand", 2.0);
		requires(Robot.arm);
	}

	 // use TimedCommand for pulse timing...
	   
	
	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println(this.getClass().getName() + " initalize");

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.arm.armDown();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut(); 
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
