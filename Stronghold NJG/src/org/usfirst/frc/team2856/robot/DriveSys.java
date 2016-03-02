package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveSys {
	private RobotDrive robotDrive;
	
	public DriveSys() {
		robotDrive = new RobotDrive(RobotMap.DRIVE_MOTOR_LEFT, RobotMap.DRIVE_MOTOR_RIGHT);
	}

	public void placeholder() {
		
	}
	
	public void arcadeDrive(GenericHID stick) {
		robotDrive.arcadeDrive(stick, true);
	}
	
	public void stop() {
		robotDrive.stopMotor();
	}
}
