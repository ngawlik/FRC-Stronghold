package org.usfirst.frc.team2856.robot;

//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	private DriveSys drive;
	private IntakeSys intake;

	// Chooser
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
//	SendableChooser<String> chooser;

	public Autonomous(DriveSys driveObj, IntakeSys intakeObj) {
		drive = driveObj;
		intake = intakeObj; intake.placeholder();

		// Chooser
//		chooser = new SendableChooser<>();
//		chooser.addDefault("Default Auto", defaultAuto);
//		chooser.addObject("My Auto", customAuto);
//		SmartDashboard.putData("Auto choices", chooser);
	}

	public void init() {
		// Chooser
//		autoSelected = chooser.getSelected();
		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);

		drive.initAuto();
		drive.moveStart(RobotMap.AUTO_DIST);
	}

	public void periodic(boolean debug) {
		drive.update(debug);
	}
}
