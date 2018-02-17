package org.usfirst.frc.team6933.robot.commands.autonomous;

import org.usfirst.frc.team6933.robot.commands.drive.DriveTimed;

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
		leftScale = true;
		
	}
	public void rightScale() {
		rightScale = true;
		
	}
}
