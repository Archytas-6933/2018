/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6933.robot;

import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousLeft;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousRight;
import org.usfirst.frc.team6933.robot.subsystems.Arm;
import org.usfirst.frc.team6933.robot.subsystems.Chassis;
import org.usfirst.frc.team6933.robot.subsystems.Compressor;
import org.usfirst.frc.team6933.robot.subsystems.Grabber;
import org.usfirst.frc.team6933.robot.subsystems.Video;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class Robot extends TimedRobot {

	//
	// Robot initialization
	//

	public static Arm arm = new Arm();
	public static Chassis chassis = new Chassis();
	public static Compressor compressor = new Compressor();;
	public static Grabber grabber = new Grabber();
	public static Video video = new Video();

	public static OI oi = new OI();

	private Command autonomousCommand = null;

	public static double testSpeed = 2.2;

	@Override
	public void robotInit() {
		System.out.println("robotInit");

		// start the camera server
		video.startAutomaticCapture();

		// start the compressor
		compressor.start();
	}

	//
	// mode init methods
	//

	// enter AUTONOMOUS mode
	@Override
	public void autonomousInit() {

		System.out.println("autonomousInit");

		// Read game data from the Driver Station and select the Autonomous Mode
		// by setting the autonomousCommand;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L') {
			System.out.println("Left was read");
			autonomousCommand = new AutonomousLeft();
		} else {
			System.out.println("Right was read");
			autonomousCommand = new AutonomousRight();
		}

		// start the chosen autonomous command
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}

	}

	// enter DISABLED mode
	@Override
	public void disabledInit() {
		System.out.println("disabledInit");
	}

	// enter TELEOP mode
	@Override
	public void teleopInit() {
		System.out.println("teleopInit");

		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}

	}

	// enter TEST mode
	@Override
	public void testInit() {
		System.out.println("testInit");
	}

	//
	// periodic execution methods
	//

	// periodic for ALL modes
	@Override
	public void robotPeriodic() {
		SmartDashboard.putData(Scheduler.getInstance());
		arm.sendInfo();
		chassis.sendInfo();
		compressor.sendInfo();
		grabber.sendInfo();
		video.sendInfo();
		Scheduler.getInstance().run();
	}

	// periodic for the AUTONOMOUS mode
	@Override
	public void autonomousPeriodic() {
	}

	// periodic for the DISABLED mode
	@Override
	public void disabledPeriodic() {
	}

	// periodic for the TELEOP mode
	@Override
	public void teleopPeriodic() {
	}

	// periodic for the TEST mode
	@Override
	public void testPeriodic() {
	}

	// public void driveForward(double distance) {
	// Drive drive;
	// drive = new Drive();
	// drive.distance_ = distance;
	// autonomousCommand.addSequential(drive);
	// }
	//
	// public void driveBackward(double distance) {
	// Drive drive;
	// drive = new Drive();
	// drive.speed_ = -1;
	// drive.distance_ = distance;
	// autonomousCommand.addSequential(drive);
	// }
	//
	// public void turnLeft(double degrees) {
	// Drive drive;
	// drive = new Drive();
	// drive.direction_ = -1;
	// drive.goalAngle_ = chassis.getAngle() - degrees;
	// autonomousCommand.addSequential(drive);
	// }
	//
	// public void turnRight(double degrees) {
	// Drive drive;
	// drive = new Drive();
	// //drive.direction_ = 0.6;
	// //System.out.println( "a" + Double.toString(Robot.navx_ahrs_.getAngle()));
	// //drive.goalAngle_ = navx_ahrs_.getAngle() + degrees;
	// //System.out.println("goal" + Double.toString(drive.goalAngle_));
	// drive.goalDegrees_ = degrees;
	// autonomousCommand.addSequential(drive);
	// }
	//
	// public void turnTo(double angle) {
	// Drive drive;
	// drive = new Drive();
	//
	// if (chassis.getAngle() > angle)
	// drive.direction_ = -1;
	// else
	// drive.direction_ = 1;
	//
	// drive.goalAngle_ = angle;
	// autonomousCommand.addSequential(drive);
	// }
	//

}
