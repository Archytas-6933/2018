package org.usfirst.frc.team6933.robot.commands;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UltrasonicWaitForLessThan extends Command {

	double threshold = 0;
	
	public UltrasonicWaitForLessThan(double threshold) {
		this.threshold = threshold;
	}
   

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(this.getClass().getSimpleName() + " initalize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.ultrasonic.getDistance() < this.threshold;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println(this.getClass().getSimpleName() + " end");   	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println(this.getClass().getSimpleName() + " interrupted");	
    }
}
