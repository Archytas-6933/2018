package org.usfirst.frc.team6933.robot.commands.autonomous;

import org.usfirst.frc.team6933.robot.commands.arm.ArmDown;
import org.usfirst.frc.team6933.robot.commands.arm.ArmUnlatch;
import org.usfirst.frc.team6933.robot.commands.arm.ArmUp;
import org.usfirst.frc.team6933.robot.commands.arm.GrabberClose;
import org.usfirst.frc.team6933.robot.commands.arm.GrabberOpen;
import org.usfirst.frc.team6933.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team6933.robot.commands.drive.DriveTimed;
import org.usfirst.frc.team6933.robot.commands.drive.TurnDegrees;
import org.usfirst.frc.team6933.robot.Robot;
import org.usfirst.frc.team6933.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class AutonomousCenter extends CommandGroup {

	private boolean leftScale = false;
	private boolean rightScale = false;
	//requires(Robot.chassis);
	
	
	public AutonomousCenter() {
		System.out.println("In Center Constructor");
		addSequential(new PrintCommand("autonomous center group"));

		
	}
	public void leftScale() {
		System.out.print("center to left scale");
////		addSequential(new GrabberOpen());
		addSequential(new ArmUp());
		addSequential(new ArmUnlatch());
//		addSequential(new ArmDown());
//		addSequential(new GrabberClose());
		addSequential(new DriveDistance(1.5)); //changed from 2.0 to 1.5
		addSequential(new TurnDegrees(-90));
		addSequential(new DriveDistance(1.5));
		addSequential(new TurnDegrees(90));
		addSequential(new DriveDistance(1.7)); //changed from 1.1 to 1.7
		addSequential(new GrabberOpen());
	}
	public void rightScale() {
		System.out.print("center to right scale");
//		addSequential(new GrabberOpen());
		addSequential(new ArmUp());
		addSequential(new ArmUnlatch());
//		addSequential(new ArmDown());
//		addSequential(new GrabberClose());
		addSequential(new DriveDistance(1.5)); //changed from 2.0 to 1.5
		addSequential(new TurnDegrees(90));
		addSequential(new DriveDistance(1.5));
		addSequential(new TurnDegrees(-90));
		addSequential(new DriveDistance(1.7)); //changed from 1.1 to 1.7
		addSequential(new GrabberOpen());
	}
}
