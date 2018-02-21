package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.AnalogGyro;
//import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;


// Wood Robot
import edu.wpi.first.wpilibj.Jaguar;
//import edu.wpi.first.wpilibj.Ultrasonic;
public class RobotMap {
	public static final double PERIODIC_UPDATE_PERIOD = 0.020; // Periodic update period (s)

	// Autonomous
	public static final double
		AUTO_DIST = 10.0;					// (feet) 15.0

	// Drive system directions
	public static final boolean
		DRIVE_MOTOR_LEFT_AUTO_DIR = false,
		DRIVE_MOTOR_LEFT_TELE_DIR = false,
		DRIVE_MOTOR_RIGHT_AUTO_DIR = true,
		DRIVE_MOTOR_RIGHT_TELE_DIR = false;

	// Drive system parameters
	public static final int
		DRIVE_ENC_SAMPLES_TO_AVERAGE = 4;
	public static final double
		DRIVE_ACCEL_RATE = 2.5,				// (ft/s^2) 5.0
		DRIVE_ENCODER_RESOLUTION = 0.003522,// (feet/count) 0.003522
//		DRIVE_GYRO_SENSITIVITY = 0.007,		// (volts/(degree/second)) 0.007
//		DRIVE_IR_CONVERSION = 4.277,		// (inch-volts)
//		DRIVE_IR_MAX_DIST = 16.0,			// (inches)
//		DRIVE_IR_THRESHOLD = 0.3,			// (volts)
		DRIVE_PID_EFFORT_MAX = 1.0,			// (0-1) 1.0
		DRIVE_PID_PERIOD = 0.010,			// (s) 0.010
		DRIVE_PID_POS_SETTLE = 0.25,		// (s) 0.25
		DRIVE_SPEED_MAX = 2.5,				// (ft/s) 5.0
		DRIVE_BASE_WIDTH = (23.5 / 12.0);	// (ft) 23.5/12.0

	// Drive system PID Parameters
	public static final double
		DRIVE_PID_POSITION_KP = 2.00,		// 2.00
		DRIVE_PID_POSITION_KI = 0.01,		// 0.01
		DRIVE_PID_POSITION_KD = 1.00,		// 1.00
		DRIVE_PID_ANGLE_KP = 0.02,
		DRIVE_PID_ANGLE_KI = 0.001,
		DRIVE_PID_ANGLE_KD = 0.0;

	// Current channels
	public static final int
		CURRENT_DRIVE_LEFT_FRONT = 15,
		CURRENT_DRIVE_RIGHT_FRONT = 13,
		CURRENT_DRIVE_LEFT_REAR = 14,
		CURRENT_DRIVE_RIGHT_REAR = 12;

	// Object declarations
//	public static AnalogInput
//		DRIVE_IR;
	public static Encoder
		DRIVE_ENCODER_LEFT,
		DRIVE_ENCODER_RIGHT;
	public static GyroBase
		DRIVE_GYRO;
	public static PowerDistributionPanel
		PDP;
	public static SpeedController
		DRIVE_MOTOR_LEFT,
		DRIVE_MOTOR_RIGHT,
		CLIMB_MOTOR,
		INTAKE_MOTOR,
		LIFT_MOTOR;
//	public static Ultrasonic
//		DRIVE_ULTRASONIC;

	// Object initialization
	public static void init() {
		// Analog In (0-3, 4-7)
//		DRIVE_GYRO = new AnalogGyro(0); ((AnalogGyro)DRIVE_GYRO).setSensitivity(DRIVE_GYRO_SENSITIVITY);
//		DRIVE_IR = new AnalogInput(3);

		// Analog Out (none, 0-1)

		// CAN (ID is unique to each type of device)
		PDP = new PowerDistributionPanel(0);

		// DIO (0-9, 10-25)
		DRIVE_ENCODER_LEFT = new Encoder(0, 1, true, EncodingType.k4X);
		DRIVE_ENCODER_RIGHT = new Encoder(2, 3, false, EncodingType.k4X);
//		DRIVE_ULTRASONIC = new Ultrasonic(8, 9);

		// PWM (0-9, 10-19)
		DRIVE_MOTOR_LEFT = new Jaguar(0);
		DRIVE_MOTOR_RIGHT = new Jaguar(1);
		INTAKE_MOTOR = new Spark(2);
		CLIMB_MOTOR = new Victor(3);
		LIFT_MOTOR = new Spark(4);

		// Relay (0-3, none)
		
		// SPI (kOnboardCS0-kOnboardCS1-kOnboardCS2-kOnboardCS3, kMXP)
		DRIVE_GYRO = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	}
}

/*
// 2018 Robot
import edu.wpi.first.wpilibj.Talon;
public class RobotMap {
	public static final double PERIODIC_UPDATE_PERIOD = 0.020; // Periodic update period (s)

	// Autonomous
	public static final double
		AUTO_DIST = 10.0;					// (feet) 15.0

	// Drive system directions
	public static final boolean
		DRIVE_MOTOR_LEFT_AUTO_DIR = false,
		DRIVE_MOTOR_LEFT_TELE_DIR = false,
		DRIVE_MOTOR_RIGHT_AUTO_DIR = true,
		DRIVE_MOTOR_RIGHT_TELE_DIR = false;

	// Drive system parameters
	public static final int
		DRIVE_ENC_SAMPLES_TO_AVERAGE = 4;
	public static final double
		DRIVE_ACCEL_RATE = 2.5,				// (ft/s^2) 5.0
		DRIVE_ENCODER_RESOLUTION = 0.003522,// (feet/count) 0.003522
		DRIVE_PID_EFFORT_MAX = 1.0,			// (0-1) 1.0
		DRIVE_PID_PERIOD = 0.010,			// (s) 0.010
		DRIVE_PID_POS_SETTLE = 0.25,		// (s) 0.25
		DRIVE_SPEED_MAX = 2.5,				// (ft/s) 5.0
		DRIVE_BASE_WIDTH = (23.5 / 12.0);	// (ft) 23.5/12.0

	// Drive system PID Parameters
	public static final double
		DRIVE_PID_POSITION_KP = 2.00,		// 2.00
		DRIVE_PID_POSITION_KI = 0.01,		// 0.01
		DRIVE_PID_POSITION_KD = 1.00,		// 1.00
		DRIVE_PID_ANGLE_KP = 0.02,
		DRIVE_PID_ANGLE_KI = 0.001,
		DRIVE_PID_ANGLE_KD = 0.0;

	// Current channels
	public static final int
		CURRENT_DRIVE_LEFT_FRONT = 15,
		CURRENT_DRIVE_RIGHT_FRONT = 13,
		CURRENT_DRIVE_LEFT_REAR = 14,
		CURRENT_DRIVE_RIGHT_REAR = 12;

	// Object declarations
	public static Encoder
		DRIVE_ENCODER_LEFT,
		DRIVE_ENCODER_RIGHT;
	public static GyroBase
		DRIVE_GYRO;
	public static PowerDistributionPanel
		PDP;
	public static SpeedController
		DRIVE_MOTOR_LEFT,
		DRIVE_MOTOR_RIGHT,
		CLIMB_MOTOR,
		INTAKE_MOTOR,
		LIFT_MOTOR;

	// Object initialization
	public static void init() {
		// Analog In (0-3, 4-7)

		// Analog Out (none, 0-1)

		// CAN (ID is unique to each type of device)
		PDP = new PowerDistributionPanel(0);

		// DIO (0-9, 10-25)
		DRIVE_ENCODER_LEFT = new Encoder(0, 1, true, EncodingType.k4X);
		DRIVE_ENCODER_RIGHT = new Encoder(2, 3, false, EncodingType.k4X);

		// PWM (0-9, 10-19)
		DRIVE_MOTOR_LEFT = new Talon(0);
		DRIVE_MOTOR_RIGHT = new Talon(1);
		INTAKE_MOTOR = new Spark(2);
		CLIMB_MOTOR = new Victor(3);
		LIFT_MOTOR = new Spark(4);

		// Relay (0-3, none)
		
		// SPI (kOnboardCS0-kOnboardCS1-kOnboardCS2-kOnboardCS3, kMXP)
		DRIVE_GYRO = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	}
}
*/