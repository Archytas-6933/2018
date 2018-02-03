package org.usfirst.frc.team6933.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6933.robot.*;
/**
 *iiiiiiiuui778wtyrw4se jnke4ed-=]
 */
public class TestDrive extends Command {

	public long startticks_;
	
    public TestDrive() {
	    	requires(Robot.chassis);
	    	startticks_ = System.currentTimeMillis();
	        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("TestDrive Initializing...");
    	System.out.println("TestDrive was Initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.drive(0.5, 0);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(System.currentTimeMillis() < 5000 + startticks_) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("TestDrive Ending...");
    	Robot.chassis.drive(0,0);
    	System.out.println("Testdrive Ended");
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
