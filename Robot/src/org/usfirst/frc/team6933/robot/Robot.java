/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6933.robot;

import org.usfirst.frc.team6933.robot.commands.arm.ArmLatch;
import org.usfirst.frc.team6933.robot.commands.arm.ArmUnlatch;
import org.usfirst.frc.team6933.robot.commands.arm.GrabberClose;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousCenter;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousLeft;
import org.usfirst.frc.team6933.robot.commands.autonomous.AutonomousRight;
import org.usfirst.frc.team6933.robot.commands.compressor.CompressorStart;
import org.usfirst.frc.team6933.robot.commands.video.VideoStart;
import org.usfirst.frc.team6933.robot.subsystems.Arm;
import org.usfirst.frc.team6933.robot.subsystems.Chassis;
import org.usfirst.frc.team6933.robot.subsystems.Compressor;
import org.usfirst.frc.team6933.robot.subsystems.Ultrasonic;
import org.usfirst.frc.team6933.robot.subsystems.Video;

//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
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
	public static Compressor compressor = new Compressor();
	public static Video video = new Video();
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	public static Ultrasonic ultrasonic = new Ultrasonic();
	// joystick disabled for test robot
	public static OI oi = new OI();

	private CommandGroup autonomousCommand =null 	;
	private AutonomousLeft autoLeft = new AutonomousLeft();
	private AutonomousCenter autoCenter = new AutonomousCenter();
	private AutonomousRight autoRight = new AutonomousRight();
	public static double testSpeed = 2.2;
	public static boolean softEnabled = false;
	public static boolean hardEnabled = false;
	
//	Preferences prefs;


//	AnalogInput regVoltage = new AnalogInput(RobotMap.Analog.ultrasonic);
	
	@Override
	public void robotInit() {
		System.out.println("robotInit");
		m_chooser.addDefault("Autonomous Left", autoLeft);
		m_chooser.addDefault("Autonomous Center", autoCenter);
		m_chooser.addDefault("Autonomous Right", autoRight);
		SmartDashboard.putData("Auto Mode", m_chooser);
//		prefs = Preferences.getInstance();
//		chassis.setPgain(prefs.getDouble("velPgain", 1));
//		chassis.setIgain(prefs.getDouble("velIgain", .1));
//		chassis.setDgain(prefs.getDouble("velDgain", .3));
		
//		AHRS ahrs = new AHRS(SPI.Port.kMXP);
//		ahrs.reset();
		
		
		// start the camera server
		Robot.video.startAutomaticCapture();
		
		// start the compressor
		Robot.compressor.start();
		
		
	}

	//
	// mode init methods
	//

	// enter AUTONOMOUS mode
	@Override
	public void autonomousInit() {

		System.out.println("autonomousInit");
		Robot.chassis.ahrs.reset();
		Command selectedCommand = m_chooser.getSelected();
		// Read game data from the Driver Station and select the Autonomous Mode
		// by setting the autonomousCommand;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("Game data: " + gameData);
		System.out.println("Autonomous start: " + selectedCommand.getName());
		
		// latch the arm & close grabber
//		autonomousCommand = new CommandGroup();
//     	autonomousCommand.addParallel(new ArmLatch());
//		autonomousCommand.addParallel(new GrabberClose());
		
		CommandGroup auto = null;

		if (gameData.charAt(0) == 'L') {
			System.out.println("Left was read");
			if (selectedCommand instanceof AutonomousLeft) {
				auto = new AutonomousLeft();
				auto.addSequential(new GrabberClose());
		     	auto.addSequential(new ArmLatch());
				((AutonomousLeft)auto).leftScale();
//				autonomousCommand.addParallel(auto);
			}
			else if (selectedCommand instanceof AutonomousCenter) {
//				autoCenter.leftScale();				
				System.out.println("In Center Left");
				auto = new AutonomousCenter();
				auto.addSequential(new GrabberClose());
		     	//auto.addSequential(new ArmLatch());
				((AutonomousCenter)auto).leftScale();
//				autonomousCommand.addParallel(auto);	  
			}
			else if (selectedCommand instanceof AutonomousRight) {
//				autoRight.leftScale();
				auto = new AutonomousRight();
				auto.addSequential(new GrabberClose());
		     	auto.addSequential(new ArmLatch());
				((AutonomousRight)auto).leftScale();
//				autonomousCommand.addParallel(auto);	  
			}
		} 
		
		if (gameData.charAt(0) == 'R') {
			System.out.println("Right was read");
			if (selectedCommand instanceof AutonomousLeft) {
//				autoLeft.rightScale();
				auto = new AutonomousLeft();
				auto.addSequential(new GrabberClose());
		     	auto.addSequential(new ArmLatch());
				((AutonomousLeft)auto).rightScale();
//				autonomousCommand.addParallel(auto);	   
			}
			else if (selectedCommand instanceof AutonomousCenter) {
//				autoCenter.rightScale();
				auto = new AutonomousCenter();
				auto.addSequential(new GrabberClose());
		     	//auto.addSequential(new ArmLatch());
				((AutonomousCenter)auto).rightScale();
//				autonomousCommand.addParallel(auto);	  
			}
			else if (selectedCommand instanceof AutonomousRight) {
//				autoRight.rightScale();
				auto = new AutonomousRight();
				auto.addSequential(new GrabberClose());
		     	auto.addSequential(new ArmLatch());
				((AutonomousRight)auto).rightScale();
//				autonomousCommand.addParallel(auto);	  
			}
		}

		// start the chosen autonomous command
//		if (autonomousCommand != null) {
//			autonomousCommand.start();
//		}
		if (auto != null)
			auto.start();

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
		ultrasonic.sendInfo();
		Scheduler.getInstance().run();
		
		
	}

	// periodic for the AUTONOMOUS mode
	@Override
	public void autonomousPeriodic() {
		//Scheduler.getInstance().run();
	}

	// periodic for the DISABLED mode
	@Override
	public void disabledPeriodic() {
	}

	// periodic for the TELEOP mode
	@Override
	public void teleopPeriodic() {
		//.getInstance().run();
	}

	// periodic for the TEST mode
	@Override
	public void testPeriodic() {
//		System.out.println("Regulator Volotage = " + regVoltage.getVoltage());
		System.out.println("Distance = " + ultrasonic.getDistance());
	}

}
