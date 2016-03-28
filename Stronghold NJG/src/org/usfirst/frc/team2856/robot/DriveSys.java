package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
//import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class DriveSys {
	private RobotDrive robotDrive;
	private PIDController leftPID, rightPID;
//	private PowerDistributionPanel power;
	private NetworkTable table;
	
//	private boolean moveActive;
//	private MoveRefGen refGen;
//	private double smallNumber;
	
	public DriveSys() {
		// Create robotDrive object
		robotDrive = new RobotDrive(RobotMap.DRIVE_MOTOR_LEFT, RobotMap.DRIVE_MOTOR_RIGHT);
		
		// Set encoder resolution
		RobotMap.DRIVE_ENCODER_LEFT.setDistancePerPulse(RobotMap.DRIVE_ENCODER_RESOLUTION);
		RobotMap.DRIVE_ENCODER_RIGHT.setDistancePerPulse(RobotMap.DRIVE_ENCODER_RESOLUTION);
		
		// Set encoder samples to average
		RobotMap.DRIVE_ENCODER_LEFT.setSamplesToAverage(RobotMap.DRIVE_ENC_SAMPLES_TO_AVERAGE);
		RobotMap.DRIVE_ENCODER_RIGHT.setSamplesToAverage(RobotMap.DRIVE_ENC_SAMPLES_TO_AVERAGE);
		
		// Start encoders
		RobotMap.DRIVE_ENCODER_LEFT.reset();
		RobotMap.DRIVE_ENCODER_RIGHT.reset();
		
		// Set gyro sensitivity
		RobotMap.DRIVE_GYRO.setSensitivity(RobotMap.DRIVE_GYRO_SENSITIVITY);
		
		// Start gyro
		RobotMap.DRIVE_GYRO.reset();
		
		// Create PID controllers, gains will be updated prior to enabling
		leftPID = new PIDController(0, 0, 0, RobotMap.DRIVE_ENCODER_LEFT, RobotMap.DRIVE_MOTOR_LEFT, RobotMap.DRIVE_PID_PERIOD);
		rightPID = new PIDController(0, 0, 0, RobotMap.DRIVE_ENCODER_RIGHT, RobotMap.DRIVE_MOTOR_RIGHT, RobotMap.DRIVE_PID_PERIOD);
		
		// Set PID output range
		leftPID.setOutputRange (-RobotMap.DRIVE_PID_EFFORT_MAX, RobotMap.DRIVE_PID_EFFORT_MAX);
		rightPID.setOutputRange (-RobotMap.DRIVE_PID_EFFORT_MAX, RobotMap.DRIVE_PID_EFFORT_MAX);
		
		// PID move initialization
//		moveActive = false;
//		refGen = new MoveRefGen();
		
		// Create power distribution panel object
//		power = new PowerDistributionPanel();

		// Create network table object
		table = NetworkTable.getTable(RobotMap.NT_SOURCE);
		
		// Set initial network table values
		table.putNumber("DT.AccelRate", RobotMap.DRIVE_ACCEL_RATE);
		table.putNumber("DT.MaxSpeed", RobotMap.DRIVE_SPEED_MAX);
		table.putNumber("DT.Pos.Kp", RobotMap.DRIVE_PID_POSITION_KP);
		table.putNumber("DT.Pos.Ki", RobotMap.DRIVE_PID_POSITION_KI);
		table.putNumber("DT.Pos.Kd", RobotMap.DRIVE_PID_POSITION_KD);
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		robotDrive.arcadeDrive(moveValue, rotateValue, true);
	}

	public double encoderGetDistLeft() {
		return RobotMap.DRIVE_ENCODER_LEFT.getDistance();
	}

	public double encoderGetDistRight() {
		return RobotMap.DRIVE_ENCODER_RIGHT.getDistance();
	}

	public double encoderGetRateLeft() {
		return RobotMap.DRIVE_ENCODER_LEFT.getRate();
	}

	public double encoderGetRateRight() {
		return RobotMap.DRIVE_ENCODER_RIGHT.getRate();
	}

	public void encoderReset() {
		RobotMap.DRIVE_ENCODER_LEFT.reset();
		RobotMap.DRIVE_ENCODER_RIGHT.reset();
	}

	public double gyroGetAngle() {
		return RobotMap.DRIVE_GYRO.getAngle();
	}

	public double gyroGetRate() {
		return RobotMap.DRIVE_GYRO.getRate();
	}

	public void gyroReset() {
		RobotMap.DRIVE_GYRO.reset();
	}

	public void initAuto() {
		RobotMap.DRIVE_MOTOR_LEFT.setInverted(RobotMap.DRIVE_MOTOR_LEFT_AUTO_DIR);
		RobotMap.DRIVE_MOTOR_RIGHT.setInverted(RobotMap.DRIVE_MOTOR_RIGHT_AUTO_DIR);
	}

	public void initTele() {
		RobotMap.DRIVE_MOTOR_LEFT.setInverted(RobotMap.DRIVE_MOTOR_LEFT_TELE_DIR);
		RobotMap.DRIVE_MOTOR_RIGHT.setInverted(RobotMap.DRIVE_MOTOR_RIGHT_TELE_DIR);
	}

	public void stop() {
		robotDrive.stopMotor();
	}
}
