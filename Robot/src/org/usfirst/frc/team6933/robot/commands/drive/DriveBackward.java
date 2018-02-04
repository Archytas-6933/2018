package org.usfirst.frc.team6933.robot.commands.drive;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveBackward extends Command {

	double speed = 0.0;
	double distance = 0.0;
	double initialX = 0.0;
	double initialY = 0.0;
	
    public DriveBackward(double speed, double distance) {
        requires(Robot.chassis);
        this.speed = speed;
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	    
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
