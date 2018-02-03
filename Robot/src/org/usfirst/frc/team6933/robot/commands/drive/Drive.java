package org.usfirst.frc.team6933.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6933.robot.*;


/**
 *
 */
public class Drive extends Command {

	public long startticks_;
	
	public double speed_;
	public double direction_;
	
	public double distance_;
	public double goalAngle_;
	public double goalDegrees_;
	
	public static double SPINERROR = 60;
	public static double GOALERROR = 2;
	
    public Drive() {
	    	requires(Robot.chassis);
	    	
	    	speed_ =.2;
	    	distance_ = 0;
	    	direction_ = 0;
	    	goalAngle_ = 0;
	    	goalDegrees_ = 0;
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Drive Initializing...");
    	speed_ = Robot.testSpeed;
    	Robot.testSpeed = Robot.testSpeed - 0.01;
    	System.out.println("speed: " + Double.toString(speed_));
    	startticks_ = System.currentTimeMillis();
    	System.out.println( "B" + Double.toString(Robot.chassis.getAngle()));
    	if (goalDegrees_ != 0) {
    		goalAngle_ = Robot.chassis.getAngle() + goalDegrees_;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (goalAngle_ != 0) {
    		double difference = (goalAngle_) - (Robot.chassis.getAngle());
    		if (difference > SPINERROR) {
    			direction_ = 1;
    		}
    		else if (difference < -SPINERROR) {
    			direction_ = -1;
    		}
    		else {
    			direction_ = difference / SPINERROR;
    		}
    		
    		//System.out.println("direction" + Double.toString(direction_));
    	}
 
    	Robot.chassis.drive(speed_, direction_);
    	
    	   Robot.chassis.sendInfo();
    	   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (goalAngle_ != 0) {
    		//System.out.println(Double.toString(Robot.navx_ahrs_.getAngle()));
    		//if (Math.abs(Robot.navx_ahrs_.getAngle() - goalAngle_) < GOALERROR)
    			//return true;
    		//if (direction_ < 0 && Robot.navx_ahrs_.getAngle() < goalAngle_)
    			//return true;
    	}
    	
    	if(System.currentTimeMillis() < 1000 + startticks_) {
    		return false;
    	}
    	else {
    		return true;
    	}
        
    }
                        
    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Drive Ending...");
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
