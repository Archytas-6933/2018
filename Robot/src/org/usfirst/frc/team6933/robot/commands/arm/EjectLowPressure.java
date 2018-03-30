package org.usfirst.frc.team6933.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EjectLowPressure extends CommandGroup {

	public EjectLowPressure() {

		addSequential(new EjectSetLowPressure());
		addSequential(new Eject());
	}
}
