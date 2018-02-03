/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6933.robot;

import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team6933.robot.commands.basic.Drive;
import org.usfirst.frc.team6933.robot.subsystems.Arm;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6933.robot.subsystems.Chassis;
import org.usfirst.frc.team6933.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static Chassis chassis;	
	public static OI oi;
	public static Grabber grabber;
	public static Arm arm;
	
	public CommandGroup auto_;


	public static double testSpeed = 0.2;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
        chassis = new Chassis();
		oi = new OI();
		grabber = new Grabber();
		arm = new Arm();
		
//		CameraServer.getInstance().startAutomaticCapture();

		setupButtons();
	}

	
	public void setupButtons() 
	{
		auto_ = new CommandGroup();
 		//turnRight(90);
		//turnLeft(45);
		driveForward(1.0);
		//driveBackward(.5);
		oi.button1.whenPressed(auto_);
	}
	

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();//?
		Robot.chassis.sendInfo();
		Robot.arm.putDashboard();
		Robot.grabber.putDashboard();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
				
		//Read game data from the Driver Station and select the Autonomous Mode
		// by setting the autonomousCommand();
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L')
		{
			System.out.println("Left was read");
		} else {
			System.out.println("Right was read");
		}
		
		auto_ = new CommandGroup();
		
		//driveForward(2.0);
		//turnLeft(90);
		//driveForward(2.0);
		//turnRight(90);
		//driveBackward(1.0);
	//	turnTo(180);
		
		// schedule the autonomous command (example)
		if (auto_ != null) {
		  auto_.start();
		}
	}

	
	public void driveForward(double distance) {
		Drive drive;
		drive = new Drive();
		drive.distance_ = distance;
		auto_.addSequential(drive);
	}
	
	public void driveBackward(double distance) {
		Drive drive;
		drive = new Drive();
		drive.speed_ = -1;
		drive.distance_ = distance;
		auto_.addSequential(drive);
	}
	
	public void turnLeft(double degrees) {
		Drive drive;
		drive = new Drive();
		drive.direction_ = -1;
		drive.goalAngle_ = chassis.getAngle() - degrees;
		auto_.addSequential(drive);
	}
	
	public void turnRight(double degrees) {
		Drive drive;
		drive = new Drive();
		//drive.direction_ = 0.6;
		//System.out.println( "a" + Double.toString(Robot.navx_ahrs_.getAngle()));
		//drive.goalAngle_ = navx_ahrs_.getAngle() + degrees;
		//System.out.println("goal" + Double.toString(drive.goalAngle_));
		drive.goalDegrees_ = degrees;
		auto_.addSequential(drive);
	}
	
	public void turnTo(double angle) {
		Drive drive;
		drive = new Drive();
		
		if (chassis.getAngle() > angle)
			drive.direction_ = -1;
		else
			drive.direction_ = 1;
		
		drive.goalAngle_ = angle;
		auto_.addSequential(drive);
	}
	
	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		Robot.chassis.sendInfo();
		Robot.arm.putDashboard();
		Robot.grabber.putDashboard();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (auto_ != null) {
			auto_.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Robot.chassis.sendInfo();
		Robot.arm.putDashboard();
		Robot.grabber.putDashboard();
	}

/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		  //LiveWindow.run();  // deprecated
	}
}
