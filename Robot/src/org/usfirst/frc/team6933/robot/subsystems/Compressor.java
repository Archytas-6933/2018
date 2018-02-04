package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.Robot;
import org.usfirst.frc.team6933.robot.commands.compressor.CompressorStart;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Compressor extends Subsystem {

	edu.wpi.first.wpilibj.Compressor compressor = new edu.wpi.first.wpilibj.Compressor();

	public void initDefaultCommand() {
		setDefaultCommand(new CompressorStart());
	}

	public void start() {
		if (!compressor.getClosedLoopControl()) {
			compressor.start();
		}
	}

	public void stop() {
		if (compressor.getClosedLoopControl()) {
			compressor.stop();
		}
	}

	public void toggleRun() {
		if (compressor.getClosedLoopControl()) {
			compressor.stop();
		} else {
			compressor.start();
		}
	}

	public void sendInfo() {
		SmartDashboard.putBoolean("CompressorStarted", compressor.getClosedLoopControl());
		SmartDashboard.putBoolean("CompressorRunning", compressor.enabled());
	}

}