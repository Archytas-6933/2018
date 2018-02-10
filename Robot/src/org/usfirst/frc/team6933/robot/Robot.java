/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6933.robot;

import org.usfirst.frc.team6933.robot.commands.arm.ArmLatch;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousLeft;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousRight;
import org.usfirst.frc.team6933.robot.commands.compressor.CompressorStart;
import org.usfirst.frc.team6933.robot.commands.video.VideoStart;
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
		new VideoStart().start();

		// start the compressor
		new CompressorStart().start();
		
		// latch the arm
		new ArmLatch().start();
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

}
