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
		addSequential(new DriveTimed(.25/* speed */, -0.5/* turn */, 1.5/* seconds */));
		addSequential(new DriveTimed(.4/* speed */, +1.0/* turn */, 1.50/* seconds */));
		addSequential(new DriveTimed(.25/* speed */, -1.0/* turn */, 2.50/* seconds */));
	}
}
