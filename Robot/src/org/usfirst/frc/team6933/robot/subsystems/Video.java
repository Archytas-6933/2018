package org.usfirst.frc.team6933.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Video extends Subsystem {

	CameraServer server = CameraServer.getInstance();
	UsbCamera cam0;
	UsbCamera cam1;

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void startAutomaticCapture() {

		server.startAutomaticCapture();
		// // Open Front-facing camera
		// try {
		// cam0 = new UsbCamera("Front-facing", 0);
		// cam0.setBrightness(50);
		// cam0.setFPS(10);
		// cam0.setResolution(640, 480);
		// server.startAutomaticCapture(cam0);
		// } catch (Exception e) {
		// DriverStation.reportWarning("Can't open front-facing camera", false);
		// }
		//
		// // Open Overhead camera
		// try {
		// cam1 = new UsbCamera("Overhead", 1);
		// cam1.setBrightness(50);
		// cam1.setFPS(10);
		// cam1.setResolution(640, 480);
		// server.startAutomaticCapture(cam1);
		// } catch (Exception e) {
		// DriverStation.reportWarning("Can't open overhead camera", false);
		// }
	}

	public void sendInfo() {
		SmartDashboard.putData(this);
	}

}
