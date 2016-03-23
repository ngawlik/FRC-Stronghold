package org.usfirst.frc.team2856.robot;

public class Disabled {
	private DriveSys drive;
	private IntakeSys intake;

	public Disabled(DriveSys driveObj, IntakeSys intakeObj) {
		drive = driveObj;
		intake = intakeObj;
	}

	public void init() {
		drive.stop();
		intake.stop();
	}

	public void periodic(boolean debug) {
		
	}
}
