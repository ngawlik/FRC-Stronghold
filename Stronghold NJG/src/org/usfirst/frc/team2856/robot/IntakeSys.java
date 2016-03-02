package org.usfirst.frc.team2856.robot;

public class IntakeSys {
	public IntakeSys() {
		
	}

	public void placeholder() {
		
	}

	public void setEffort(double effort) {
		RobotMap.INTAKE_MOTOR.set(effort);
	}

	public void stop() {
		RobotMap.INTAKE_MOTOR.stopMotor();
	}
}
