package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Solenoid armDown = new Solenoid(RobotMap.Solenoid.pcmId,RobotMap.Solenoid.armDown);
	Solenoid armUp = new Solenoid(RobotMap.Solenoid.pcmId,RobotMap.Solenoid.armUp);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void down() {
    		armDown.set(true);
    		armUp.set(false);
    }
    
    public void up() {
    		armDown.set(false);
		armUp.set(true);
    }
    
    public void stationary() {
		armDown.set(false);
		armUp.set(false);
}
    
    public void sendInfo()
	{
		SmartDashboard.putBoolean("ArmUp", armUp.get());
		SmartDashboard.putBoolean("ArmDown", armDown.get());
	}
  
    
}
