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
public class AutonomousLeft extends CommandGroup {
private boolean leftScale = false;
private boolean rightScale = false;
	public AutonomousLeft() {
		addSequential(new PrintCommand("autonomous left group"));
	}

	public void leftScale() {
		System.out.print("left to left scale");
		addSequential(new ArmUp());
		addSequential(new ArmUnlatch());
		addSequential(new DriveDistance(3.2));
		addSequential(new TurnDegrees(90));
    	addSequential(new DriveDistance(0.9));
		addSequential(new GrabberOpen());
		
	}
	public void rightScale() {
		System.out.print("left to right scale");
		addSequential(new ArmUp());
		addSequential(new ArmUnlatch());
		addSequential(new DriveDistance(4.8));
		addSequential(new TurnDegrees(90));
		addSequential(new DriveDistance(3.35));
	}
}
