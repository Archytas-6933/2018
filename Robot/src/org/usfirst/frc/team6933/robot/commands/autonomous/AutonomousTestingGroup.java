package org.usfirst.frc.team6933.robot.commands.autonomous;

import org.usfirst.frc.team6933.robot.commands.drive.DriveTimedOpenLoop;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class AutonomousTestingGroup extends CommandGroup {

    public AutonomousTestingGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new PrintCommand("autonomous testing"));
		addSequential(new DriveTimedOpenLoop(.25/* speed */, -0.5/* turn */, 1.5/* seconds */));
		addSequential(new DriveTimedOpenLoop(.4/* speed */, +1.0/* turn */, 1.50/* seconds */));
		addSequential(new DriveTimedOpenLoop(.25/* speed */, -1.0/* turn */, 2.50/* seconds */));

    	
//    addSequential(new TurnRight(.5 /*percent*/, 90 /*degrees*/));
//    addSequential(new TurnLeft(.5 /*percent*/, 45 /*degrees*/));
//    	addSequential(new DriveForward(.5 /*percent*/, 1.0 /*meters*/));
//    	addSequential(new DriveBackward(.5 /*percent*/, 1.0 /*meters*/));
     	
    	
    }
}
