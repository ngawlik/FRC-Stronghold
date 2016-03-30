package org.usfirst.frc.team2856.robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class Teleop {
//	private DriverStation ds;
	private DriveSys drive;
	private IntakeSys intake;
	private Joystick joystick;
	private boolean button9PrevValue;
	private boolean button10PrevValue;
	private boolean button11PrevValue;
	private boolean button12PrevValue;

	public Teleop(DriveSys driveObj, IntakeSys intakeObj) {
//		ds = DriverStation.getInstance();
		drive = driveObj;
		intake = intakeObj;
		joystick = new Joystick(0);
	}

	public void init() {
		drive.initTele();
	}

	public void periodic(boolean debug) {
		drive.arcadeDrive(joystick.getY(), joystick.getX());
		//drive.arcadeDrive(joystick.getY(), joystick.getX(), joystick.getRawButton(1));
		
		double effort = 0;
		if (joystick.getRawButton(5))
		{
			effort = -1.0;
		}
		else if (joystick.getRawButton(3))
		{
			effort = -0.25;
		}
		else if (joystick.getRawButton(6))
		{
			effort = 1.0;
		}
		else if (joystick.getRawButton(4))
		{
			effort = 0.25;
		}
        intake.setEffort(effort);
        
    	boolean button9Value = joystick.getRawButton(9);
    	if (button9Value && !button9PrevValue)
    	{
    		double angle = drive.gyroGetAngle();
    		System.out.printf("Drive Gyro Angle:%.0f\n", angle);
    	}
    	button9PrevValue = button9Value;

    	boolean button10Value = joystick.getRawButton(10);
    	if (button10Value && !button10PrevValue)
    	{
    		drive.gyroReset();
    		System.out.println("Drive Gyro Reset");
    	}
    	button10PrevValue = button10Value;

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
    	
    	drive.update(debug);
	}
}
