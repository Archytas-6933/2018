package org.usfirst.frc.team6933.robot.commands.autonomous;

import org.usfirst.frc.team6933.robot.commands.arm.ArmUnlatch;
import org.usfirst.frc.team6933.robot.commands.arm.ArmUp;
import org.usfirst.frc.team6933.robot.commands.arm.GrabberOpen;
import org.usfirst.frc.team6933.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team6933.robot.commands.drive.DriveTimed;
import org.usfirst.frc.team6933.robot.commands.drive.TurnDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class AutonomousRight extends CommandGroup {

	private boolean leftScale = false;
	private boolean rightScale = false;
	public AutonomousRight() {

		addSequential(new PrintCommand("autonomous right group"));
		if (leftScale) {
			addSequential(new PrintCommand("heading left scaleward!"));

		}
		else if (rightScale) {
			addSequential(new PrintCommand("heading right scaleward!"));

		}
//		addSequential(new TurnTo(-45));
		
//		addSequential(new DriveDistance(1.5));
//		addSequential(new GrabberOpen());
	}
	public void leftScale() {
		System.out.print("right to left scale");
////		addSequential(new GrabberOpen());
//		addSequential(new ArmUp());
//		addSequential(new ArmUnlatch());
////		addSequential(new ArmDown());
////		addSequential(new GrabberClose());
//		addSequential(new DriveDistance(2));
		addSequential(new TurnDegrees(-90));
//		addSequential(new DriveDistance(6));
//		addSequential(new TurnDegrees(90));
//		addSequential(new DriveDistance(1.1));
//		addSequential(new GrabberOpen());
//		
	}
	public void rightScale() {
		System.out.print("right to right scale");
////		addSequential(new GrabberOpen());
//		addSequential(new ArmUp());
//		addSequential(new ArmUnlatch());
////		addSequential(new ArmDown());
////		addSequential(new GrabberClose());
		addSequential(new DriveDistance(2));//3.5 meters
//		addSequential(new TurnDegrees(-90));
//		addSequential(new DriveDistance(1.5));
//		addSequential(new GrabberOpen());
	}
}
