package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.RobotMap;
import org.usfirst.frc.team6933.robot.commands.arm.ArmDefaultOff;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {

	final double solenoidPulseWidth = 1.0;  // seconds
	
	// Put methods for controlling this subsystem
	DoubleSolenoid armSolenoid = new DoubleSolenoid(RobotMap.CAN.pcmId, RobotMap.Solenoid.armUp,
			RobotMap.Solenoid.armDown);
	Solenoid releaseSolenoid = new Solenoid(RobotMap.CAN.pcmId, RobotMap.Solenoid.armRelease);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ArmDefaultOff());
	}

	public void armUp() {
		armSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void armDown() {
		armSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	public void armOff() {
		armSolenoid.set(DoubleSolenoid.Value.kOff);
	}
	
	public void release() {
		System.out.println("arm release");
		releaseSolenoid.setPulseDuration(solenoidPulseWidth);
		releaseSolenoid.startPulse();
	}

	public double getSolenoidPulseWidth() {
		return solenoidPulseWidth;
	}
	
	public void sendInfo() {
		SmartDashboard.putData(this);
	}

}
