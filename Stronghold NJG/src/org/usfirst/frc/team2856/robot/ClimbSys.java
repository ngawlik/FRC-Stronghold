package org.usfirst.frc.team2856.robot;

public class ClimbSys {
	public ClimbSys() {
		
	}

	public void placeholder() {
	}

	public void setEffort(double effort) {
		RobotMap.CLIMB_MOTOR.set(effort);
	}

	public void stop() {
		RobotMap.CLIMB_MOTOR.stopMotor();
	}
}
