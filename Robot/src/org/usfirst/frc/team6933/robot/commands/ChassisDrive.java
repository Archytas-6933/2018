package org.usfirst.frc.team6933.robot.commands;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChassisDrive extends Command {

    public ChassisDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		// get values of gamepad1 axes
		double lxAxis = Robot.oi.gamepad1.getLXAxis();
		double lyAxis = Robot.oi.gamepad1.getLYAxis();	
		
		// run the drive function given the above values		
		Robot.chassis.drive(lyAxis, lxAxis);
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
