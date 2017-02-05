package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	private DriveSys drive;

	private String autoSelected;
	private Integer state;

	public Autonomous(DriveSys driveObj) {
		drive = driveObj;
	}

	public void init() {
		autoSelected = SmartDashboard.getString("Auto Selector", "None");
		System.out.println("Auto selected: " + autoSelected);
		state = 0;

		drive.initAuto();
		stateMachine();
	}

	public void periodic(boolean debug) {
		stateMachine();
		drive.update(debug);
	}

	private void stateMachine() {
		switch(autoSelected) {
			case "Forward":
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
			case "FwdRev":
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
			case "Line":
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
			case "Square":
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
			case "Turn":
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
