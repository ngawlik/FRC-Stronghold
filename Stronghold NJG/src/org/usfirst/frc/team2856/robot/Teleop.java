package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Teleop {
	private DriveSys drive;
	private IntakeSys intake;
	private Joystick joystick;

	public Teleop(DriveSys driveObj, IntakeSys intakeObj) {
		drive = driveObj;
		intake = intakeObj;
		joystick = new Joystick(0);
	}

	public void init() {
		
	}

	public void periodic() {
		drive.arcadeDrive(joystick);
		
		double effort = 0;
        if (joystick.getRawButton(2))
        {
        	if (joystick.getRawButton(1))
        	{
        		effort = -1.0;
        	}
        	else
        	{
        		effort = -0.2;
        	}
        }
        else if (joystick.getRawButton(3))
        {
        	if (joystick.getRawButton(1))
        	{
        		effort = 1.0;
        	}
        	else
        	{
        		effort = 0.2;
        	}
        }
        intake.setEffort(effort);
	}
}
