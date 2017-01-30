package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class DriveSys {
	private RobotDrive robotDrive;
	private PIDController leftPID, rightPID;
	private PowerDistributionPanel power;
	private NetworkTable table;
	
	private double leftInitialPos, rightInitialPos;
	private boolean moveActive;
	private MoveRefGen refGen;
	private double smallNumber;
	
	public DriveSys() {
		// Create robotDrive object
		robotDrive = new RobotDrive(RobotMap.DRIVE_MOTOR_LEFT, RobotMap.DRIVE_MOTOR_RIGHT);
		
		// Set encoder resolution
		RobotMap.DRIVE_ENCODER_LEFT.setDistancePerPulse(RobotMap.DRIVE_ENCODER_RESOLUTION);
		RobotMap.DRIVE_ENCODER_RIGHT.setDistancePerPulse(RobotMap.DRIVE_ENCODER_RESOLUTION);
		
		// Set encoder samples to average
		RobotMap.DRIVE_ENCODER_LEFT.setSamplesToAverage(RobotMap.DRIVE_ENC_SAMPLES_TO_AVERAGE);
		RobotMap.DRIVE_ENCODER_RIGHT.setSamplesToAverage(RobotMap.DRIVE_ENC_SAMPLES_TO_AVERAGE);
		
		// Start encoders
		RobotMap.DRIVE_ENCODER_LEFT.reset();
		RobotMap.DRIVE_ENCODER_RIGHT.reset();
		
//		// Set gyro sensitivity
//		RobotMap.DRIVE_GYRO.setSensitivity(RobotMap.DRIVE_GYRO_SENSITIVITY);
		
//		// Start gyro
//		RobotMap.DRIVE_GYRO.reset();
		
		// Create PID controllers, gains will be updated prior to enabling
		leftPID = new PIDController(0, 0, 0, RobotMap.DRIVE_ENCODER_LEFT, RobotMap.DRIVE_MOTOR_LEFT, RobotMap.DRIVE_PID_PERIOD);
		rightPID = new PIDController(0, 0, 0, RobotMap.DRIVE_ENCODER_RIGHT, RobotMap.DRIVE_MOTOR_RIGHT, RobotMap.DRIVE_PID_PERIOD);
		
		// Set PID output range
		leftPID.setOutputRange (-RobotMap.DRIVE_PID_EFFORT_MAX, RobotMap.DRIVE_PID_EFFORT_MAX);
		rightPID.setOutputRange (-RobotMap.DRIVE_PID_EFFORT_MAX, RobotMap.DRIVE_PID_EFFORT_MAX);
		
		// PID move initialization
		moveActive = false;
		refGen = new MoveRefGen();
		
		// Create power distribution panel object
		power = new PowerDistributionPanel();

		// Create network table object
		table = NetworkTable.getTable(RobotMap.NT_SOURCE);
		
		// Set initial network table values
		table.putNumber("Drive.AccelRate", RobotMap.DRIVE_ACCEL_RATE);
		table.putNumber("Drive.MaxSpeed", RobotMap.DRIVE_SPEED_MAX);
		table.putNumber("Drive.Pos.Kp", RobotMap.DRIVE_PID_POSITION_KP);
		table.putNumber("Drive.Pos.Ki", RobotMap.DRIVE_PID_POSITION_KI);
		table.putNumber("Drive.Pos.Kd", RobotMap.DRIVE_PID_POSITION_KD);
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		robotDrive.arcadeDrive(moveValue, rotateValue, true);
	}

//	public void arcadeDrive(double yValue, double xValue, boolean useGyro) {
//		if (useGyro)
//		{// Field-oriented driving - integrator needed?
//			double moveValue, rotateValue;
//			double magnitude = Math.sqrt(Math.pow(yValue, 2) + Math.pow(xValue, 2));
//			if (magnitude != 0)
//			{
//				double angleCord = (magnitude != 0) ? Math.atan2(xValue, -yValue) : 0;
//				double angleGyro = Math.toRadians(RobotMap.DRIVE_GYRO.getAngle());
//				double angleTheta = angleCord - angleGyro;
//				moveValue = -magnitude * Math.cos(angleTheta);
//				rotateValue = magnitude * Math.sin(angleTheta);
//				
//				table.putNumber("Drive.yValue", yValue);
//				table.putNumber("Drive.xValue", xValue);
//				table.putNumber("Drive.angleCord", Math.toDegrees(angleCord));
//				table.putNumber("Drive.angleGyro", Math.toDegrees(angleGyro));
//				table.putNumber("Drive.angleTheta", Math.toDegrees(angleTheta));
//				table.putNumber("Drive.moveValue", moveValue);
//				table.putNumber("Drive.rotateValue", rotateValue);
//			}
//			else
//			{
//				moveValue = 0;
//				rotateValue = 0;
//			}
//			robotDrive.arcadeDrive(moveValue, rotateValue, false);
//		}
//		else
//		{// Robot-oriented driving
//			robotDrive.arcadeDrive(yValue, xValue, true);
//		}
//	}

	public double encoderGetDistLeft() {
		return RobotMap.DRIVE_ENCODER_LEFT.getDistance();
	}

	public double encoderGetDistRight() {
		return RobotMap.DRIVE_ENCODER_RIGHT.getDistance();
	}

	public double encoderGetRateLeft() {
		return RobotMap.DRIVE_ENCODER_LEFT.getRate();
	}

	public double encoderGetRateRight() {
		return RobotMap.DRIVE_ENCODER_RIGHT.getRate();
	}

	public void encoderReset() {
		RobotMap.DRIVE_ENCODER_LEFT.reset();
		RobotMap.DRIVE_ENCODER_RIGHT.reset();
	}

//	public double gyroGetAngle() {
//		return RobotMap.DRIVE_GYRO.getAngle();
//	}

//	public double gyroGetRate() {
//		return RobotMap.DRIVE_GYRO.getRate();
//	}

//	public void gyroReset() {
//		RobotMap.DRIVE_GYRO.reset();
//	}

	public void initAuto() {
		// Disable user watchdog (since RobotDrive will not be called)
		robotDrive.setSafetyEnabled(false);

		// Set motor drive directions (both forward)
		RobotMap.DRIVE_MOTOR_LEFT.setInverted(RobotMap.DRIVE_MOTOR_LEFT_AUTO_DIR);
		RobotMap.DRIVE_MOTOR_RIGHT.setInverted(RobotMap.DRIVE_MOTOR_RIGHT_AUTO_DIR);
	}

	public void initTele() {
		// Enable user watchdog (default when using RobotDrive)
		robotDrive.setSafetyEnabled(true);

		// Set motor drive directions (left backward, right forward)
		RobotMap.DRIVE_MOTOR_LEFT.setInverted(RobotMap.DRIVE_MOTOR_LEFT_TELE_DIR);
		RobotMap.DRIVE_MOTOR_RIGHT.setInverted(RobotMap.DRIVE_MOTOR_RIGHT_TELE_DIR);
	}

	public boolean moveGetActive() {
		return moveActive;
	}
	
	public void moveStart(double distance) {
		double Kp, Ki, Kd;
		double accelRate;
		double maxSpeed;

		// Update local parameters
		Kp = table.getNumber("Drive.Pos.Kp", RobotMap.DRIVE_PID_POSITION_KP);
		Ki = table.getNumber("Drive.Pos.Ki", RobotMap.DRIVE_PID_POSITION_KI);
		Kd = table.getNumber("Drive.Pos.Kd", RobotMap.DRIVE_PID_POSITION_KD);
		accelRate = table.getNumber("Drive.AccelRate", RobotMap.DRIVE_ACCEL_RATE);
		maxSpeed = table.getNumber("Drive.MaxSpeed", RobotMap.DRIVE_SPEED_MAX);

		// Reset PID controllers
		leftPID.reset();
		rightPID.reset();

		// Latch in initial positions
		leftInitialPos = RobotMap.DRIVE_ENCODER_LEFT.getDistance();
		rightInitialPos = RobotMap.DRIVE_ENCODER_RIGHT.getDistance();

        // Set encoders to output position to PID controllers
		RobotMap.DRIVE_ENCODER_LEFT.setPIDSourceType(PIDSourceType.kDisplacement);
		RobotMap.DRIVE_ENCODER_RIGHT.setPIDSourceType(PIDSourceType.kDisplacement);

		// Set PID parameters
		leftPID.setPID(Kp, Ki, Kd);
		rightPID.setPID(Kp, Ki, Kd);

		// Set PID set points to initial positions
		leftPID.setSetpoint(leftInitialPos);
		rightPID.setSetpoint(rightInitialPos);

		// Enable PID controllers
		leftPID.enable();
		rightPID.enable();

		// Configure and start move reference generator
		refGen.configure(accelRate, maxSpeed, RobotMap.DRIVE_PID_POS_SETTLE);
		refGen.start(distance);
		moveActive = true;
	}

	public void moveStop() {
		// Disable PID controllers
		leftPID.disable();
		rightPID.disable();

		// Position move finished
		moveActive = false;
	}

	public void stop() {
		moveStop();
		robotDrive.stopMotor();
	}

	public void update(boolean debug) {
		if (moveActive)
		{
			refGen.update();
			if (refGen.isActive())
			{
				double refPos = refGen.getRefPosition();
				leftPID.setSetpoint(refPos + leftInitialPos);
				rightPID.setSetpoint(refPos + rightInitialPos);
			}
			else
			{
				moveStop();
			}
		}

//		table.putNumber("Gyro", RobotMap.DRIVE_GYRO.getAngle());

		if (debug)
		{
			smallNumber = (smallNumber == 0) ? 0.001: 0;
			
			// Left wheels
			table.putNumber("Drive.Left.Pos", RobotMap.DRIVE_ENCODER_LEFT.getDistance() + smallNumber);
			table.putNumber("Drive.Left.Vel",  RobotMap.DRIVE_ENCODER_LEFT.getRate() + smallNumber);
			table.putNumber("Drive.Left.Effort", RobotMap.DRIVE_MOTOR_LEFT.get() + smallNumber);
			table.putNumber("Drive.LF.Current", power.getCurrent(RobotMap.DRIVE_POWER_LEFT_FRONT) + smallNumber);
			table.putNumber("Drive.LR.Current", power.getCurrent(RobotMap.DRIVE_POWER_LEFT_REAR) + smallNumber);
	
			// Right wheels
			table.putNumber("Drive.Right.Pos", RobotMap.DRIVE_ENCODER_RIGHT.getDistance() + smallNumber);
			table.putNumber("Drive.Right.Vel",  RobotMap.DRIVE_ENCODER_RIGHT.getRate() + smallNumber);
			table.putNumber("Drive.Right.Effort", RobotMap.DRIVE_MOTOR_RIGHT.get() + smallNumber);
			table.putNumber("Drive.RF.Current", power.getCurrent(RobotMap.DRIVE_POWER_RIGHT_FRONT) + smallNumber);
			table.putNumber("Drive.RR.Current", power.getCurrent(RobotMap.DRIVE_POWER_RIGHT_REAR) + smallNumber);
		}
	}
}
