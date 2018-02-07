package org.usfirst.frc.team6933.robot.commands.autonomous;

import org.usfirst.frc.team6933.robot.commands.drive.DriveTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class AutonomousLeft extends CommandGroup {

	public AutonomousLeft() {

		addSequential(new PrintCommand("autonomous left group"));
		addSequential(new DriveTimed(.25/* speed */, -0.5/* turn */, 2.5/* seconds */));
		addSequential(new DriveTimed(.5/* speed */, +0.5/* turn */, 2.50/* seconds */));
		addSequential(new DriveTimed(.25/* speed */, -0.5/* turn */, 1.50/* seconds */));

	}
}
