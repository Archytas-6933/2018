package org.usfirst.frc.team6933.robot.commands.drive;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;


public class DriveTimed extends TimedCommand {

	double speed = 0.0;
	double turn = 0.0;

	public DriveTimed(double speed, double turn, double timeout) {
		super(timeout);
		requires(Robot.chassis);
		this.speed = speed;
		this.turn = turn;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println(this.getClass().getSimpleName() + " initalize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.chassis.drive(speed, turn);
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
