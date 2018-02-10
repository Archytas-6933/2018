package org.usfirst.frc.team6933.robot.commands.grabber;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrabberOff extends Command {

    public GrabberOff() {
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
    	Robot.grabber.off();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return false;  // never done - default command
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
