package org.usfirst.frc.team2856.robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class Teleop {
	private ClimbSys climb;
	private DriveSys drive;
	private IntakeSys intake;
	private LiftSys lift;
//	private DriverStation ds;
	private Joystick joystick;
//	private XboxController xbox;

	public Teleop(DriveSys driveObj, ClimbSys climbObj, IntakeSys intakeObj, LiftSys liftObj) {
//		ds = DriverStation.getInstance();
		drive = driveObj;
		climb = climbObj;
		intake = intakeObj;
		lift = liftObj;
		joystick = new Joystick(0);
//		xbox = new XboxController(1);
	}

	public void init() {
		drive.initTele();
		climb.placeholder();
		intake.placeholder();
		lift.placeholder();
	}

	public void periodic(boolean debug) {
		/***** Joystick *****/
		if (joystick.getRawButton(1))
		{
			// Reduced effort
			drive.arcadeDrive(-joystick.getY()/2, joystick.getZ()/2);
			climb.setEffort(0);
		}
		else if (joystick.getRawButton(2))
		{
			// Climbing (extend +0.25, retract -0.5, hold -0.1)
			drive.arcadeDrive(0, 0);
			climb.setEffort(-joystick.getY());  // Away from user is negative
		}
		else
		{
			// Full effort
			drive.arcadeDrive(-joystick.getY(), joystick.getZ());
			climb.setEffort(0);
		}

		if (joystick.getRawButtonPressed(3))
		{
			double leftDist = drive.encoderGetDistLeft();
			double rightDist = drive.encoderGetDistRight();
			double leftRate = drive.encoderGetRateLeft();
			double rightRate = drive.encoderGetRateRight();
			System.out.printf("Drive Encoder Dist L:%.2f R:%.2f Rate L:%.2f R:%.2f\n",
					leftDist, rightDist, leftRate, rightRate);
		}

		if (joystick.getRawButtonPressed(4))
		{
			drive.encoderReset();
			System.out.println("Drive Encoder Reset");
		}

//		if (joystick.getRawButtonPressed(5));
//		{
//			drive.gyroCalibrate();
//			System.out.println("Drive Gyro Calibrate");
//		}

		if (joystick.getRawButtonPressed(6))
		{
			drive.gyroReset();
			System.out.println("Drive Gyro Reset");
		}

		if (joystick.getRawButton(7))
		{
			// Down?
			lift.setEffort(-0.5);
		}
		else if (joystick.getRawButton(8))
		{
			// Up?
			lift.setEffort(0.5);
		}
		else
		{
			// Off
			lift.stop();
		}

		if (joystick.getRawButton(9))
		{
			// In
			intake.setEffort(0.6);
		}
		else if (joystick.getRawButton(10))
		{
			// Out
			intake.setEffort(-1.0);
		}
		else
		{
			// Off
			intake.stop();
		}


		/***** Xbox *****/
//		drive.tankDrive(-xbox.getY(Hand.kLeft), -xbox.getY(Hand.kRight));
//		xbox.setRumble(RumbleType.kLeftRumble, xbox.getTriggerAxis(Hand.kLeft));
//		xbox.setRumble(RumbleType.kRightRumble, xbox.getTriggerAxis(Hand.kRight));


		/***** Drive controller update *****/
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
