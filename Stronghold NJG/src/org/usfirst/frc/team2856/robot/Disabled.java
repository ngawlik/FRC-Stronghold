package org.usfirst.frc.team2856.robot;

public class Disabled {
	private DriveSys drive;
	private ClimbSys climb;
	private IntakeSys intake;
	private LiftSys lift;

	public Disabled(DriveSys driveObj, ClimbSys climbObj, IntakeSys intakeObj, LiftSys liftObj) {
		drive = driveObj;
		climb = climbObj;
		intake = intakeObj;
		lift = liftObj;
	}

	public void init() {
		drive.stop();
		climb.stop();
		intake.stop();
		lift.stop();
	}

	public void periodic(boolean debug) {
		
	}
}
