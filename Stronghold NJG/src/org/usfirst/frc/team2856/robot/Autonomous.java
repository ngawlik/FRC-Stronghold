package org.usfirst.frc.team2856.robot;

public class Autonomous {
	private DriveSys drive;
	private IntakeSys intake;

	public Autonomous(DriveSys driveObj, IntakeSys intakeObj) {
		drive = driveObj;
		intake = intakeObj;
		
		intake.placeholder();
	}

	public void init() {
		drive.initAuto();
		drive.moveStart(RobotMap.AUTO_DIST);
	}

	public void periodic(boolean debug) {
		drive.update(debug);
	}
}
