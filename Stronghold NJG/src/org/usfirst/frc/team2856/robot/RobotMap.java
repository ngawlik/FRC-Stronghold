package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class RobotMap {
	public static final String NT_SOURCE = "SmartDashboard"; // Network table
	public static final double PERIODIC_UPDATE_PERIOD = 0.020; // Periodic update period (s)

	// Autonomous
	public static final double
		AUTO_DIST = 3.0;					// (feet)

	// Drive system directions
	public static final boolean
		DRIVE_MOTOR_LEFT_AUTO_DIR = false,
		DRIVE_MOTOR_LEFT_TELE_DIR = true,
		DRIVE_MOTOR_RIGHT_AUTO_DIR = true,
		DRIVE_MOTOR_RIGHT_TELE_DIR = true;

	// Drive system parameters
	public static final int
		DRIVE_ENC_SAMPLES_TO_AVERAGE = 4;
	public static final double
		DRIVE_ACCEL_RATE = 5.0,				// (ft/s^2)
		DRIVE_ENCODER_RESOLUTION = 0.003522,// (feet/count)
		DRIVE_GYRO_SENSITIVITY = 0.007,		// (volts/(degree/second))
		DRIVE_PID_EFFORT_MAX = 0.5,			// (0-1)
		DRIVE_PID_PERIOD = 0.010,			// (s)
		DRIVE_PID_POS_SETTLE = 0.25,		// (s)
		DRIVE_SPEED_MAX = 2.5;				// (ft/s)

	// Drive system PID Parameters
	public static final double
		DRIVE_PID_POSITION_KP = 2.00,
		DRIVE_PID_POSITION_KI = 0.01,
		DRIVE_PID_POSITION_KD = 1.00;

	// Power channels
	public static final int
		DRIVE_POWER_LEFT_FRONT = 0,
		DRIVE_POWER_RIGHT_FRONT = 1,
		DRIVE_POWER_LEFT_REAR = 2,
		DRIVE_POWER_RIGHT_REAR = 3,
		CAMERA_POWER = 4,
		VRM_POWER = 5,
		INTAKE_POWER = 15;

	// Object declarations
	public static AnalogGyro
		DRIVE_GYRO;
	public static Encoder
		DRIVE_ENCODER_LEFT,
		DRIVE_ENCODER_RIGHT;
	public static SpeedController
		DRIVE_MOTOR_LEFT,
		DRIVE_MOTOR_RIGHT,
		INTAKE_MOTOR;

	public static void init() {
		// Analog In (0-3, 4-7)
		DRIVE_GYRO = new AnalogGyro(0);
		
		// Analog Out (none, 0-1)
		
		// DIO (0-9, 10-25)
		DRIVE_ENCODER_LEFT = new Encoder(0, 1, true, EncodingType.k4X);
		DRIVE_ENCODER_RIGHT = new Encoder(2, 3, false, EncodingType.k4X);
		
		// PWM (0-9, 10-19)
		DRIVE_MOTOR_LEFT = new Jaguar(0);
		DRIVE_MOTOR_RIGHT = new Jaguar(1);
		INTAKE_MOTOR = new Talon(2);
		
		// Relay (0-3, none)
	}
}
