package org.usfirst.frc.team6933.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;


/**
 *
 */
public class GrabberOpenAndOptionalEject extends CommandGroup {

	public GrabberOpenAndOptionalEject() {

		addSequential(new GrabberOpen());
		addSequential(new TimedCommand(0.015));
		addSequential(new EjectConditional());

	}

}
