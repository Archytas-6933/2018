package org.usfirst.frc.team6933.robot.control;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PositionControlPIDSubsystem extends PIDSubsystem {

	Encoder encoder;
	VelocityControlPIDSubsystem velocity;
	
	double positionTolerance = 0.05;
	
	// Initialize your subsystem here
	public PositionControlPIDSubsystem(String name, double kP, double kI, double kD, Encoder encoder, 
				VelocityControlPIDSubsystem velocityControl) {
		super(name+"PositionPID", kP, kI, kD);

		this.encoder = encoder;
		this.velocity = velocityControl;

		setInputRange(-10.0, +10.0);
		setSetpoint(0.0); // initialize setpoint to zero
		this.setAbsoluteTolerance(positionTolerance);
		
		velocityControl.enable();

		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
	}

	@Override
	public void initDefaultCommand() {
		// n/a
	}

	@Override
	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		double input = encoder.getDistance();
		SmartDashboard.putNumber("A - PIDInput - " + getName(), input);
//		System.out.println(getName() + " in " + Double.toString(input));
		return input; 
	}

	@Override
	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output)
		output /= 1.5;
		SmartDashboard.putNumber("A - PIDOutput - " + getName(), output);
//		System.out.println(getName() + " out " + Double.toString(output) + " to " + velocity.getName());
		velocity.setSetpoint(output);
	}

	public void sendInfo() {
		SmartDashboard.putData(this);
	}
}
