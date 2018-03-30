package org.usfirst.frc.team6933.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EjectHighPressure extends CommandGroup {

    public EjectHighPressure() {
    	
    	addSequential(new EjectSetHighPressure());
    	addSequential(new Eject());
       }
}
