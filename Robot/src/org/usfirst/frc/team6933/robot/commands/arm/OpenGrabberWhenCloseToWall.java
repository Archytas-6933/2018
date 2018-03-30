package org.usfirst.frc.team6933.robot.commands.arm;

import org.usfirst.frc.team6933.robot.commands.UltrasonicWaitForLessThan;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OpenGrabberWhenCloseToWall extends CommandGroup {

    public OpenGrabberWhenCloseToWall(double threshold) {
       addSequential(new UltrasonicWaitForLessThan(threshold));
       addSequential(new GrabberOpen());
       
    }
}
