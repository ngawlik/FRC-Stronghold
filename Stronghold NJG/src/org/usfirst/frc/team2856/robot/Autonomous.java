package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	private DriveSys drive;

	private SendableChooser<String> m_chooser = new SendableChooser<>();
	private String m_autoSelected;
//	private String autoSelected;
	private static final String
		kModeDefault = "Default",
		kModeForward = "Forward",
		kModeFwdRev  = "FwdRev",
		kModeLine    = "Line",
		kModeSquare  = "Square",
		kModeTurn    = "Turn";
		

	private Integer state;	

	public Autonomous(DriveSys driveObj) {
		drive = driveObj;

		m_chooser.addDefault(kModeDefault, kModeDefault);
		m_chooser.addObject(kModeForward, kModeForward);
		m_chooser.addObject(kModeFwdRev, kModeFwdRev);
		m_chooser.addObject(kModeLine, kModeLine);
		m_chooser.addObject(kModeSquare, kModeSquare);
		m_chooser.addObject(kModeTurn, kModeTurn);
		SmartDashboard.putData("Auto modes", m_chooser);
	}

	public void init() {
		m_autoSelected = m_chooser.getSelected();
		System.out.println("Auto selected: " + m_autoSelected);
//		autoSelected = SmartDashboard.getString("Auto Selector", "None");
//		System.out.println("Auto selected: " + autoSelected);
		state = 0;

		drive.initAuto();
		stateMachine();
	}

	public void periodic(boolean debug) {
		stateMachine();
		drive.update(debug);
	}

	private void stateMachine() {
		switch(m_autoSelected /*autoSelected*/) {
			case kModeForward:
				switch(state) {
					case 0:
						if(!drive.moveGetActive())
						{
							drive.moveStraight(RobotMap.AUTO_DIST);
							state++;
						}
						break;
					default:
						break;
				}
				break;
			case kModeFwdRev:
				switch(state) {
					case 0:
						if(!drive.moveGetActive())
						{
							drive.moveStraight(RobotMap.AUTO_DIST);
							state++;
						}
						break;
					case 1:
						if(!drive.moveGetActive())
						{
							drive.moveStraight(-RobotMap.AUTO_DIST);
							state++;
						}
						break;
					default:
						break;
				}
				break;
			case kModeLine:
				switch(state) {
					case 0:
						if(!drive.moveGetActive())
						{
							drive.moveStraight(6.0);
							state++;
						}
						break;
					case 1:
						if(!drive.moveGetActive())
						{
							drive.moveTurn(180.0, 0.0);
							state++;
						}
						break;
					case 2:
						if(!drive.moveGetActive())
						{
							drive.moveStraight(6.0);
							state++;
						}
						break;
					case 3:
						if(!drive.moveGetActive())
						{
							drive.moveTurn(-180.0, 0.0);
							state++;
						}
						break;
					default:
						break;
				}
				break;
			case kModeSquare:
				switch(state) {
					case 0:
						if(!drive.moveGetActive())
						{
							drive.moveStraight(3.0);
							state++;
						}
						break;
					case 1:
						if(!drive.moveGetActive())
						{
							drive.moveTurn(90.0, 2.0);
							state++;
						}
						break;
					case 2:
						if(!drive.moveGetActive())
						{
							drive.moveStraight(3.0);
							state++;
						}
						break;
					case 3:
						if(!drive.moveGetActive())
						{
							drive.moveTurn(90.0, 2.0);
							state++;
						}
						break;
					case 4:
						if(!drive.moveGetActive())
						{
							drive.moveStraight(3.0);
							state++;
						}
						break;
					case 5:
						if(!drive.moveGetActive())
						{
							drive.moveTurn(90.0, 2.0);
							state++;
						}
						break;
					case 6:
						if(!drive.moveGetActive())
						{
							drive.moveStraight(3.0);
							state++;
						}
						break;
					case 7:
						if(!drive.moveGetActive())
						{
							drive.moveTurn(90.0, 2.0);
							state++;
						}
						break;
					default:
						break;
				}
				break;
			case kModeTurn:
				switch(state) {
					case 0:
						if(!drive.moveGetActive())
						{
							drive.moveTurn(90.0, 0.0);
							state++;
						}
						break;
					default:
						break;
				}
				break;
			default:
				// No match found, do nothing
				break;
		}
	}
}
