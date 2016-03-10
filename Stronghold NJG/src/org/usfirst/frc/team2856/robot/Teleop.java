package org.usfirst.frc.team2856.robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class Teleop {
//	private DriverStation ds;
	private DriveSys drive;
	private IntakeSys intake;
	private Joystick joystick;
	private boolean button11PrevValue;
	private boolean button12PrevValue;

	public Teleop(DriveSys driveObj, IntakeSys intakeObj) {
//		ds = DriverStation.getInstance();
		drive = driveObj;
		intake = intakeObj;
		joystick = new Joystick(0);
	}

	public void init() {
		
	}

	public void periodic() {
		drive.arcadeDrive(joystick.getY(), joystick.getX());
		
		double effort = 0;
		if (joystick.getRawButton(5))
		{
			effort = 0.2;
		}
		else if (joystick.getRawButton(3))
		{
			effort = 1.0;
		}
		else if (joystick.getRawButton(6))
		{
			effort = -0.2;
		}
		else if (joystick.getRawButton(4))
		{
			effort = -1.0;
		}
        intake.setEffort(effort);
        
    	boolean button11Value = joystick.getRawButton(11);
    	if (button11Value && !button11PrevValue)
    	{
    		double leftDist = drive.encoderGetDistLeft();
    		double rightDist = drive.encoderGetDistRight();
    		double leftRate = drive.encoderGetRateLeft();
    		double rightRate = drive.encoderGetRateRight();
    		System.out.printf("Drive Encoder Dist L:%.2f R:%.2f Rate L:%.2f R:%.2f\n",
    				leftDist, rightDist, leftRate, rightRate);
    	}
    	button11PrevValue = button11Value;
    	
    	boolean button12Value = joystick.getRawButton(12);
    	if (button12Value && !button12PrevValue)
    	{
    		drive.encoderReset();
    		System.out.println("Drive Encoder Reset");
    	}
    	button12PrevValue = button12Value;
	}
}
