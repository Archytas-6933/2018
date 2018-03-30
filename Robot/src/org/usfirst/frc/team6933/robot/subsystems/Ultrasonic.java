package org.usfirst.frc.team6933.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6933.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
/**
 *
 */
public class Ultrasonic extends Subsystem {

	//super(RobotMap.AnalogInput.ultrasonic);
	protected double distance = 0;
	protected static double VCC = 5.0;
	
	public AnalogInput m_ultrasonic = new AnalogInput(RobotMap.Analog.ultrasonic);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public double getDistance() {
    	distance = m_ultrasonic.getVoltage()*(1024/VCC)*5/25.4; //Convert to inches
    	return distance;
    }
    
	public void sendInfo() {
		// TODO Auto-generated method stub
		SmartDashboard.putData(this);
		SmartDashboard.putNumber("UltrasonicDistance",getDistance());
	}
}

