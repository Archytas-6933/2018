package org.usfirst.frc.team6933.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

public class StartSomeCommand extends Command {

	Command command;
	
	public StartSomeCommand(Command command) {
		this.command = command;
	}
	
	@Override 
	public void initialize() {
		command.start();
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
