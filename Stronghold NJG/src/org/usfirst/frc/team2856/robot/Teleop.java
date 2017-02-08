package org.usfirst.frc.team2856.robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class Teleop {
//	private ClimbSys climb;
//	private DriverStation ds;
	private DriveSys drive;
	private IntakeSys intake;
	private Joystick joystick;
//	private Joystick xbox;
	private boolean button6PrevValue;
	private boolean button7PrevValue;
	private boolean button10PrevValue;
	private boolean button11PrevValue;

	public Teleop(DriveSys driveObj, IntakeSys intakeObj/*, ClimbSys climbObj*/) {
//		ds = DriverStation.getInstance();
		drive = driveObj;
		intake = intakeObj;
//		climb = climbObj;
		joystick = new Joystick(0);
//		xbox = new Joystick(1);
	}

	public void init() {
		drive.initTele();
	}

	public void periodic(boolean debug) {
		if (joystick.getRawButton(1))
		{
			// Reduced effort
			drive.arcadeDrive(joystick.getY()/2, joystick.getX()/2);
		}
		else
		{
			// Full effort
			drive.arcadeDrive(joystick.getY(), joystick.getX());
		}
		
		double effort = 0;
		// In, fast
		if (joystick.getRawButton(4)/* || xbox.getRawButton(2)*/) //5
		{
			effort = -1.0;
		}
		// In, slow - fixed
		else if (joystick.getRawButton(2)/* || xbox.getRawButton(1)*/) //3
		{
			effort = -0.50;
		}
		// In, slow - variable (left xBox)
//		else if (xbox.getRawAxis(3) < -0.1)
//		{
//			effort = 0.25 * xbox.getRawAxis(3);
//		}
		// Out, fast
		else if (joystick.getRawButton(3)/* || xbox.getRawButton(4)*/) //6
		{
			effort = 1.0;
		}
		// Out, slow
		else if (joystick.getRawButton(5)/* || xbox.getRawButton(3)*/) //4
		{
			effort = 0.50;
		}
//		// Out, slow - variable (right xBox)
//		else if (xbox.getRawAxis(3) > 0.1)
//		{
//			effort = 0.25 * xbox.getRawAxis(3);
//		}
        intake.setEffort(effort);
        
    	boolean button7Value = joystick.getRawButton(7); //9
    	if (button7Value && !button7PrevValue)
    	{
    		double angle = drive.gyroGetAngle();
    		System.out.printf("Drive Gyro Angle:%.0f\n", angle);
    	}
    	button7PrevValue = button7Value;

    	boolean button6Value = joystick.getRawButton(6); //10
    	if (button6Value && !button6PrevValue)
    	{
    		drive.gyroReset();
    		System.out.println("Drive Gyro Reset");
    	}
    	button6PrevValue = button6Value;

    	boolean button10Value = joystick.getRawButton(10); //11
    	if (button10Value && !button10PrevValue)
    	{
    		double leftDist = drive.encoderGetDistLeft();
    		double rightDist = drive.encoderGetDistRight();
    		double leftRate = drive.encoderGetRateLeft();
    		double rightRate = drive.encoderGetRateRight();
    		System.out.printf("Drive Encoder Dist L:%.2f R:%.2f Rate L:%.2f R:%.2f\n",
    				leftDist, rightDist, leftRate, rightRate);
    	}
    	button10PrevValue = button10Value;
    	
    	boolean button11Value = joystick.getRawButton(11); //12
    	if (button11Value && !button11PrevValue)
    	{
    		drive.encoderReset();
    		System.out.println("Drive Encoder Reset");
    	}
    	button11PrevValue = button11Value;
    	
//    	// Arm (xBox rightY)
//    	double axisValueArm = xbox.getRawAxis(5);
//    	if (!xbox.getRawButton(6) || ((axisValueArm > -0.1) && (axisValueArm < 0.1)))
//    	{
//    		axisValueArm = 0;
//    	}
//    	climb.setArmEffort(axisValueArm);
    	
//    	// Lift (xBox leftY)
//    	double axisValueLift = xbox.getRawAxis(2);
//    	if (!xbox.getRawButton(5) || ((axisValueLift > -0.1) && (axisValueLift < 0.1)))
//    	{
//    		axisValueLift = 0;
//    	}
//    	climb.setLiftEffort(axisValueLift);

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
