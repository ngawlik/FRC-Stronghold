package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;

public class RobotMap {
	public static SpeedController DRIVE_MOTOR_LEFT;
	public static SpeedController DRIVE_MOTOR_RIGHT;

	public static void init() {
		// Analog In (0-3, 4-7)
		
		// Analog Out (none, 0-1)
		
		// DIO (0-9, 10-25)
		
		// PWM (0-9, 10-19)
		DRIVE_MOTOR_LEFT = new Jaguar(0);
		DRIVE_MOTOR_RIGHT = new Jaguar(1);
		
		// Relay (0-3, none)
		
	}
}
