package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class DriveSys {
	private RobotDrive robotDrive;
	
	public DriveSys() {
		robotDrive = new RobotDrive(RobotMap.DRIVE_MOTOR_LEFT, RobotMap.DRIVE_MOTOR_RIGHT);
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

	public void encoderReset() {
		RobotMap.DRIVE_ENCODER_LEFT.reset();
		RobotMap.DRIVE_ENCODER_RIGHT.reset();
	}

	public void stop() {
		robotDrive.stopMotor();
	}
}
