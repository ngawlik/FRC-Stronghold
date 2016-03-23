package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class DriveSys {
	private RobotDrive robotDrive;
	
	public DriveSys() {
		robotDrive = new RobotDrive(RobotMap.DRIVE_MOTOR_LEFT, RobotMap.DRIVE_MOTOR_RIGHT);
		
		RobotMap.DRIVE_ENCODER_LEFT.setDistancePerPulse(RobotMap.DRIVE_ENCODER_DIST_PER_PULSE);
		RobotMap.DRIVE_ENCODER_RIGHT.setDistancePerPulse(RobotMap.DRIVE_ENCODER_DIST_PER_PULSE);
		RobotMap.DRIVE_GYRO.setSensitivity(RobotMap.DRIVE_GYRO_SENSITIVITY);
	}

	public void placeholder() {
		
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
