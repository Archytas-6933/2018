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
public class AutonomousCenter extends CommandGroup {

	private boolean leftScale = false;
	private boolean rightScale = false;
	
	public AutonomousCenter() {
		addSequential(new PrintCommand("autonomous center group"));

		if (leftScale) {
			addSequential(new PrintCommand("heading left scaleward!"));

		}
		else if (rightScale) {
			addSequential(new PrintCommand("heading right scaleward!"));

		}
//		addSequential(new DriveDistance(0.5));
//		addSequential(new TurnDegrees(-90));
//		addSequential(new DriveDistance(0.5));
//		addSequential(new TurnDegrees(90));
//		addSequential(new GrabberOpen());
//		addSequential(new DriveDistance(-0.5));
		
	}
	public void leftScale() {
		leftScale = true;
		
	}
	public void rightScale() {
		rightScale = true;
		
	}
}
