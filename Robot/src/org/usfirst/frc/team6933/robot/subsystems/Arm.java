package org.usfirst.frc.team6933.robot.subsystems;

import org.usfirst.frc.team6933.robot.RobotMap;
import org.usfirst.frc.team6933.robot.commands.arm.ArmOff;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {

	public final double solenoidPulseWidth = 0.1; // seconds

	// Put methods for controlling this subsystem
	DoubleSolenoid elevator = new DoubleSolenoid(RobotMap.CAN.pcmId, RobotMap.Solenoid.armUp,
			RobotMap.Solenoid.armDown);
	DoubleSolenoid release = new DoubleSolenoid(RobotMap.CAN.pcmId, RobotMap.Solenoid.armLatch,
			RobotMap.Solenoid.armUnlatch);
	DoubleSolenoid grabberSolenoid = new DoubleSolenoid(RobotMap.CAN.pcmId, RobotMap.Solenoid.grabberOpen,
			RobotMap.Solenoid.grabberClose);
	public Solenoid ejector = new Solenoid(RobotMap.CAN.pcmId, RobotMap.Solenoid.armEjector);
	public Solenoid ejectorPressure = new Solenoid(RobotMap.CAN.pcmId, RobotMap.Solenoid.ejectorPressure25);

	public Arm() {
		ejectorPressure.setPulseDuration(2.0);
	}
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ArmOff());
	}

	public void armUp() {
		elevator.set(DoubleSolenoid.Value.kForward);
	}

	public void armDown() {
		elevator.set(DoubleSolenoid.Value.kReverse);
	}

	public void armLatch() {
		release.set(DoubleSolenoid.Value.kForward);
	}

	public void armUnlatch() {
		release.set(DoubleSolenoid.Value.kReverse);
	}

	public void ejectorSetHighPressure() {
		ejectorPressure.set(true);		
	}

	public void eject() {
		ejector.set(true);
	}

	public void grabberOpen() {
		grabberSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void grabberClose() {
		grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	public void armOff() {
		elevator.set(DoubleSolenoid.Value.kOff);
		release.set(DoubleSolenoid.Value.kOff);
		grabberSolenoid.set(DoubleSolenoid.Value.kOff);
		ejector.set(false);
	}

	public void sendInfo() {
		SmartDashboard.putData(this);
	}

}
