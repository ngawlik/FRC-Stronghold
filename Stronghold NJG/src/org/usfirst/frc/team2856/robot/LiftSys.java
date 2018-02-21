package org.usfirst.frc.team2856.robot;

public class LiftSys {
	public LiftSys() {
		
	}

	public void placeholder() {
	}

	public void setEffort(double effort) {
		RobotMap.LIFT_MOTOR.set(effort);
	}

	public void stop() {
		RobotMap.LIFT_MOTOR.stopMotor();
	}
}
