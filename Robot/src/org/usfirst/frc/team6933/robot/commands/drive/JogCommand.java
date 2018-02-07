package org.usfirst.frc.team6933.robot.commands.drive;

import org.usfirst.frc.team6933.robot.PovButton;
import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class JogCommand extends Command {

	int direction;
	double speed;
	double turn;
	double distance;
	
	public JogCommand(PovButton dPadButtonN) {
		this.setTimeout(5.0);  // safety timeout
		this.direction = dPadButtonN.getDirection();
		this.setName("Jog " + direction + " Command");
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println(this.getClass().getName() + " initialize");
		// reset nav to so list of points can have zero reference
		// create command instance specific list of points to follow
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	   // TODO	
		// follow a list of points specific to each instance of this command
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut(); // || whatever ;
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
