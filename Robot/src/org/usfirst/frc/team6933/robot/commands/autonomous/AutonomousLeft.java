package org.usfirst.frc.team6933.robot.commands.autonomous;

import org.usfirst.frc.team6933.robot.commands.arm.ArmUnlatch;
import org.usfirst.frc.team6933.robot.commands.arm.ArmUp;
import org.usfirst.frc.team6933.robot.commands.arm.GrabberOpen;
import org.usfirst.frc.team6933.robot.commands.drive.DriveDistance;
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
		addSequential(new DriveDistance(3.5));
		addSequential(new TurnDegrees(90));
    	addSequential(new DriveDistance(0.7));
		//addSequential(new ArmUp());
		//addSequential(new ArmUnlatch());
		addSequential(new GrabberOpen());
		
	}
	public void rightScale() {
		System.out.print("left to right scale");
		addSequential(new DriveDistance(5.6));//changed from 5.1
		addSequential(new TurnDegrees(75));
		addSequential(new DriveDistance(2.0)); //changed from 3.35 to 1.0 due to turn error
		//addSequential(new ArmUp());
		//addSequential(new ArmUnlatch());
	}
}
