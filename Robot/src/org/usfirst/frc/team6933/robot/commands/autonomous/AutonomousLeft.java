package org.usfirst.frc.team6933.robot.commands.autonomous;

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

	public AutonomousLeft() {

		addSequential(new PrintCommand("autonomous left group"));
		addSequential(new DriveDistance(0.5));
		addSequential(new TurnDegrees(-90));
		addSequential(new DriveDistance(0.5));
		addSequential(new TurnDegrees(90));
		addSequential(new GrabberOpen());
		addSequential(new DriveDistance(-0.5));
		
	}
}
