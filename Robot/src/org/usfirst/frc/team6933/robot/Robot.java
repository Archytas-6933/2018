/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6933.robot;

import org.usfirst.frc.team6933.robot.commands.arm.ArmLatch;
import org.usfirst.frc.team6933.robot.commands.arm.GrabberClose;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousCenter;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousLeft;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousRight;
import org.usfirst.frc.team6933.robot.commands.compressor.CompressorStart;
import org.usfirst.frc.team6933.robot.commands.video.VideoStart;
import org.usfirst.frc.team6933.robot.subsystems.Arm;
import org.usfirst.frc.team6933.robot.subsystems.Chassis;
import org.usfirst.frc.team6933.robot.subsystems.Compressor;
import org.usfirst.frc.team6933.robot.subsystems.Video;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	public static Video video = new Video();
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	public static OI oi = new OI();

	private Command autonomousCommand = null;
	private AutonomousLeft autoLeft = new AutonomousLeft();
	private AutonomousCenter autoCenter = new AutonomousCenter();
	private AutonomousRight autoRight = new AutonomousRight();
	public static double testSpeed = 2.2;

	boolean initialized = false;
	
	@Override
	public void robotInit() {
		System.out.println("robotInit");
		m_chooser.addDefault("Autonomous Left", autoLeft);
		m_chooser.addDefault("Autonomous Center", autoCenter);
		m_chooser.addDefault("Autonomous Right", autoRight);
		SmartDashboard.putData("Auto Mode", m_chooser);
	}

	//
	// mode init methods
	//

	// enter AUTONOMOUS mode
	@Override
	public void autonomousInit() {

		System.out.println("autonomousInit");
		Command autonomousCommand = m_chooser.getSelected();
		// Read game data from the Driver Station and select the Autonomous Mode
		// by setting the autonomousCommand;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L') {
			System.out.println("Left was read");
			if (autonomousCommand instanceof AutonomousLeft) {
				autoLeft.leftScale();
			}
			else if (autonomousCommand instanceof AutonomousCenter) {
				autoCenter.leftScale();
			}
			else if (autonomousCommand instanceof AutonomousRight) {
				autoRight.leftScale();
			}
			//autonomousCommand = new AutonomousLeft();
		} else {
			System.out.println("Right was read");
			if (autonomousCommand instanceof AutonomousLeft) {
				autoLeft.rightScale();
			}
			else if (autonomousCommand instanceof AutonomousCenter) {
				autoCenter.rightScale();
			}
			else if (autonomousCommand instanceof AutonomousRight) {
				autoRight.rightScale();
			}
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
		video.sendInfo();
		Scheduler.getInstance().run();
		
		// run initialization commands only while we have the scheduler running
		if ( !initialized && this.isEnabled() ) {
			
			// start the camera server
			new VideoStart().start();

			// start the compressor
			new CompressorStart().start();

			// latch the arm
			new ArmLatch().start();

			// close the grabber
			new GrabberClose().start();
			
			initialized = true;
		}
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
