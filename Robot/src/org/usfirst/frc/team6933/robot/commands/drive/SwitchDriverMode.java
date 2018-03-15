package org.usfirst.frc.team6933.robot.commands.drive;

import org.usfirst.frc.team6933.robot.OI;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SwitchDriverMode extends InstantCommand {

    public SwitchDriverMode() {
        super();
    }
    
    @Override
    public void initialize() {
    	if (OI.driverMode == 0)
    		OI.driverMode = 1;
    	else
    		OI.driverMode = 0;
    	System.out.println("Switching to driver mode: " + Integer.toString(OI.driverMode));
    }

}
