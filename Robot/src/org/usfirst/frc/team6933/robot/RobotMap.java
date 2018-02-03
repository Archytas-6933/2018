/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6933.robot;

import edu.wpi.first.wpilibj.Encoder;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * Please change generic names like can0 to representative names like motorRightA, etc.
 * 
 */
public class RobotMap {
	
	public static class CAN {
		public static int motorRightA = 11;
		public static int motorLeftA = 12;
		public static int motorRightB = 13;
		public static int motorLeftB = 14;
	}
	
	public static class Solenoid {
		public static final int armUp = 0;
		public static final int armDown = 1;
		public static final int grabberOpen = 2;
		public static final int grabberClose = 3;
		public static final int pcmId = 20;
	}
	
	public static class PWM {
		public static int pwm0 = 0;
		public static int pwm1 = 1;
		public static int pwm2 = 2;
		public static int pwm3 = 3;
		public static int pwm4 = 4;
		public static int pwm5 = 5;
		public static int pwm6 = 6;
		public static int pwm7 = 7;
		public static int pwm8 = 8;
		public static int pwm9 = 9;
	}
	
	public static class DIO {
		public static int motorLeftEncoderA = 0;
		public static int motorLeftEncoderB = 1;
		public static int motorRightEncoderA = 2;
		public static int motorRightEncoderB = 3;
		public static int dio0 = 0;
		public static int dio1 = 1;
		public static int dio2 = 2;
		public static int dio3 = 3;
		public static int dio4 = 4;
		public static int dio5 = 5;
		public static int dio6 = 6;
		public static int dio7 = 7;
		public static int dio8 = 8;
		public static int dio9 = 9;
	}
	
	public static class Analog {	
		public static int analog0 = 0;
		public static int analog1 = 1;
		public static int analog2 = 2;
		public static int analog3 = 3;
	}
}
