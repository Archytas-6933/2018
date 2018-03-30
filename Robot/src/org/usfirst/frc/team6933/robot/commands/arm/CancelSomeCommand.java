package org.usfirst.frc.team6933.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

public class CancelSomeCommand extends Command {

	Command command;
	
	public CancelSomeCommand(Command command) {
		this.command = command;
	}
	
	@Override 
	public void initialize() {
		command.cancel();
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
