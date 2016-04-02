package org.usfirst.frc.team2856.robot;

public class Disabled {
//	private ClimbSys climb;
	private DriveSys drive;
	private IntakeSys intake;

	public Disabled(DriveSys driveObj, IntakeSys intakeObj/*, ClimbSys climbObj*/) {
		drive = driveObj;
		intake = intakeObj;
//		climb = climbObj;
	}

	public void init() {
		drive.stop();
		intake.stop();
//		climb.stop();
	}

	public void periodic(boolean debug) {
		
	}
}
