/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6933.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6933.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class JoystickDriveRateControl extends Command {

	public JoystickDriveRateControl() {
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println(this.getClass().getName() + " initialize");
		Robot.chassis.setRateControl(); // needs values for rate control
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// get values of gamepad1 axes
		double lxAxis = Robot.oi.operator.getLXAxis();
		double lyAxis = Robot.oi.operator.getLYAxis();

		// set rate control goals in chassis
		Robot.chassis.setRates(lyAxis, lxAxis);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false; // never finished - may be preempted
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println(this.getClass().getName() + " end");
		Robot.chassis.setOpenLoop(); // restore open loop when done
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		System.out.println(this.getClass().getName() + " interrupted");
	}

}