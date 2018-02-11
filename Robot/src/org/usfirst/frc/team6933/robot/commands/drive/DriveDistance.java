package org.usfirst.frc.team6933.robot.commands.drive;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class DriveDistance extends TimedCommand {

	double distanceTarget;
	
    public DriveDistance(double distance) {
    	super(3.0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.chassis);
		distanceTarget = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		System.out.println(this.getClass().getName() + " initalize");
		Robot.chassis.setPositionControlDrive();
		Robot.chassis.setDistanceTarget(distanceTarget);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut() || Robot.chassis.isAtTargetDistance();
    }

    // Called once after isFinished returns true
    protected void end() {
		System.out.println(this.getClass().getName() + " end");
    	Robot.chassis.stopDistancePID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		System.out.println(this.getClass().getName() + " interrupted");
    	Robot.chassis.stopDistancePID();
    }
}
