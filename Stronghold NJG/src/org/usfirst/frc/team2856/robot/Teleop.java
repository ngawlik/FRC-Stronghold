package org.usfirst.frc.team2856.robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class Teleop {
	private ClimbSys climb;
//	private DriverStation ds;
	private DriveSys drive;
	private IntakeSys intake;
	private Joystick joystick;
	private LiftSys lift;
//	private Joystick xbox;
	private boolean button5PrevValue;
	private boolean button6PrevValue;
	private boolean button3PrevValue;
	private boolean button4PrevValue;

	public Teleop(DriveSys driveObj, ClimbSys climbObj, IntakeSys intakeObj, LiftSys liftObj) {
//		ds = DriverStation.getInstance();
		drive = driveObj;
		climb = climbObj;
		intake = intakeObj;
		lift = liftObj;
		joystick = new Joystick(0);
//		xbox = new Joystick(1);
	}

	public void init() {
		drive.initTele();
		climb.placeholder();
		intake.placeholder();
		lift.placeholder();
	}

	public void periodic(boolean debug) {
		if (joystick.getRawButton(1))
		{
			// Reduced effort
			drive.arcadeDrive(-joystick.getY()/2, joystick.getZ()/2);
		}
		else
		{
			// Full effort
			drive.arcadeDrive(-joystick.getY(), joystick.getZ());
		}
		
    	boolean button5Value = joystick.getRawButton(5); //9
    	if (button5Value && !button5PrevValue)
    	{
//    		drive.gyroCalibrate();
//    		System.out.println("Drive Gyro Calibrate");
    	}
    	button5PrevValue = button5Value;

    	boolean button6Value = joystick.getRawButton(6); //10
    	if (button6Value && !button6PrevValue)
    	{
    		drive.gyroReset();
    		System.out.println("Drive Gyro Reset");
    	}
    	button6PrevValue = button6Value;

    	boolean button3Value = joystick.getRawButton(3); //11
    	if (button3Value && !button3PrevValue)
    	{
    		double leftDist = drive.encoderGetDistLeft();
    		double rightDist = drive.encoderGetDistRight();
    		double leftRate = drive.encoderGetRateLeft();
    		double rightRate = drive.encoderGetRateRight();
    		System.out.printf("Drive Encoder Dist L:%.2f R:%.2f Rate L:%.2f R:%.2f\n",
    				leftDist, rightDist, leftRate, rightRate);
    	}
    	button3PrevValue = button3Value;
    	
    	boolean button4Value = joystick.getRawButton(4); //12
    	if (button4Value && !button4PrevValue)
    	{
    		drive.encoderReset();
    		System.out.println("Drive Encoder Reset");
    	}
    	button4PrevValue = button4Value;
    	
    	drive.update(debug);
	}
}

// Xbox Axis
//  1-leftX
//  2-leftY
//  3-trig right-left
//  4-rightX
//  5-rightY
//  6-Dpad Left/Right
