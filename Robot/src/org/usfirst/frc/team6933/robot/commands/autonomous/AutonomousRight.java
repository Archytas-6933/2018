package org.usfirst.frc.team6933.robot.commands.autonomous;

import org.usfirst.frc.team6933.robot.commands.drive.DriveTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class AutonomousRight extends CommandGroup {

	public AutonomousRight() {

		addSequential(new PrintCommand("autonomous right group"));
//		addSequential(new TurnTo(-45));
//		addSequential(new DriveDistance(1.5));
//		addSequential(new GrabberOpen());
	}
}
