package org.usfirst.frc.team6933.robot.commands.video;

import org.usfirst.frc.team6933.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class VideoStart extends InstantCommand {

	public VideoStart() {
		requires(Robot.video);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.video.startAutomaticCapture();
	}

}
