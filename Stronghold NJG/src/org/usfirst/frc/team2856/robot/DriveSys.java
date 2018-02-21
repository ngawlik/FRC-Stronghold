package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;

public class DriveSys {
	private DifferentialDrive robotDrive;
	private PIDController leftPID, rightPID;
	
	private double leftMultiplier, rightMultiplier, thetaRatio;
	private double leftInitialPos, rightInitialPos, thetaInitial;
	private double angErrKp, angErrKi, angErrKd, angErrSum, angErrPrev;
	private boolean moveActive;
	private MoveRefGen refGen;

	private static final String
		kDashDriveAngKp = "DriveAngleKp",
		kDashDriveAngKi = "DriveAngleKi",
		kDashDriveAngKd = "DriveAngleKd",
		kDashDriveAccelRate = "DriveAccelRate",
		kDashDriveMaxSpeed = "DriveMaxSpeed",
		kDashDrivePosKp = "DrivePosKp",
		kDashDrivePosKi = "DrivePosKi",
		kDashDrivePosKd = "DrivePosKd";
//		kDashDriveTheta = "DriveTheta",
//		kDashDriveGyro = "DriveGyro",
//		kDashDriveAngErr = "DriveAngleError",
//		kDashDriveGyroCor = "DriveGyroCor";

	public DriveSys() {
		// Create robotDrive object
		robotDrive = new DifferentialDrive(RobotMap.DRIVE_MOTOR_LEFT, RobotMap.DRIVE_MOTOR_RIGHT);
		
		// Set encoder resolution
		RobotMap.DRIVE_ENCODER_LEFT.setDistancePerPulse(RobotMap.DRIVE_ENCODER_RESOLUTION);
		RobotMap.DRIVE_ENCODER_RIGHT.setDistancePerPulse(RobotMap.DRIVE_ENCODER_RESOLUTION);
		
		// Set encoder samples to average
		RobotMap.DRIVE_ENCODER_LEFT.setSamplesToAverage(RobotMap.DRIVE_ENC_SAMPLES_TO_AVERAGE);
		RobotMap.DRIVE_ENCODER_RIGHT.setSamplesToAverage(RobotMap.DRIVE_ENC_SAMPLES_TO_AVERAGE);
		
		// Start encoders
		RobotMap.DRIVE_ENCODER_LEFT.reset();
		RobotMap.DRIVE_ENCODER_RIGHT.reset();
		
		// Start gyro
		RobotMap.DRIVE_GYRO.reset();
		
//		// Start Ultrasonic
//		RobotMap.DRIVE_ULTRASONIC.setAutomaticMode(true);
		
		// Create PID controllers, gains will be updated prior to enabling
		leftPID = new PIDController(0, 0, 0, RobotMap.DRIVE_ENCODER_LEFT, RobotMap.DRIVE_MOTOR_LEFT, RobotMap.DRIVE_PID_PERIOD);
		rightPID = new PIDController(0, 0, 0, RobotMap.DRIVE_ENCODER_RIGHT, RobotMap.DRIVE_MOTOR_RIGHT, RobotMap.DRIVE_PID_PERIOD);
		
		// Set PID output range
		leftPID.setOutputRange (-RobotMap.DRIVE_PID_EFFORT_MAX, RobotMap.DRIVE_PID_EFFORT_MAX);
		rightPID.setOutputRange (-RobotMap.DRIVE_PID_EFFORT_MAX, RobotMap.DRIVE_PID_EFFORT_MAX);
		
		// PID move initialization
		moveActive = false;
		refGen = new MoveRefGen();
		
		// Set initial network table values
		SmartDashboard.putNumber(kDashDriveAngKp, RobotMap.DRIVE_PID_ANGLE_KP);
		SmartDashboard.putNumber(kDashDriveAngKi, RobotMap.DRIVE_PID_ANGLE_KI);
		SmartDashboard.putNumber(kDashDriveAngKd, RobotMap.DRIVE_PID_ANGLE_KD);
		SmartDashboard.putNumber(kDashDriveAccelRate, RobotMap.DRIVE_ACCEL_RATE);
		SmartDashboard.putNumber(kDashDriveMaxSpeed, RobotMap.DRIVE_SPEED_MAX);
		SmartDashboard.putNumber(kDashDrivePosKp, RobotMap.DRIVE_PID_POSITION_KP);
		SmartDashboard.putNumber(kDashDrivePosKi, RobotMap.DRIVE_PID_POSITION_KI);
		SmartDashboard.putNumber(kDashDrivePosKd, RobotMap.DRIVE_PID_POSITION_KD);
//		SmartDashboard.putNumber(kDashDriveTheta, 0);
//		SmartDashboard.putNumber(kDashDriveGyro, 0);
//		SmartDashboard.putNumber(kDashDriveAngErr, 0);
//		SmartDashboard.putNumber(kDashDriveGyroCor, 0);
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		robotDrive.arcadeDrive(moveValue, rotateValue, true);
	}

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

	public void gyroCalibrate() {
		RobotMap.DRIVE_GYRO.calibrate();
		RobotMap.DRIVE_GYRO.reset();
	}

	public double gyroGetAngle() {
		return RobotMap.DRIVE_GYRO.getAngle();
	}

	public double gyroGetRate() {
		return RobotMap.DRIVE_GYRO.getRate();
	}

	public void gyroReset() {
		RobotMap.DRIVE_GYRO.reset();
	}

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

//	public double irGetDist() {
//		double dist;
//		double volt;
//		
//		volt = RobotMap.DRIVE_IR.getVoltage();
//		dist = volt > RobotMap.DRIVE_IR_THRESHOLD ? RobotMap.DRIVE_IR_CONVERSION / volt : RobotMap.DRIVE_IR_MAX_DIST;
//
//		return dist;
//	}

	public boolean moveGetActive() {
		return moveActive;
	}
	
	private void moveStart(double distance) {
		double accelRate;
		double maxSpeed;
		double Kp, Ki, Kd;

		// Update object parameters
		angErrKp = SmartDashboard.getNumber(kDashDriveAngKp, RobotMap.DRIVE_PID_ANGLE_KP);
		angErrKi = SmartDashboard.getNumber(kDashDriveAngKi, RobotMap.DRIVE_PID_ANGLE_KI);
		angErrKd = SmartDashboard.getNumber(kDashDriveAngKd, RobotMap.DRIVE_PID_ANGLE_KD);
		angErrSum = 0;
		angErrPrev = 0;

		// Update local parameters
		accelRate = SmartDashboard.getNumber(kDashDriveAccelRate, RobotMap.DRIVE_ACCEL_RATE);
		maxSpeed = SmartDashboard.getNumber(kDashDriveMaxSpeed, RobotMap.DRIVE_SPEED_MAX);
		Kp = SmartDashboard.getNumber(kDashDrivePosKp, RobotMap.DRIVE_PID_POSITION_KP);
		Ki = SmartDashboard.getNumber(kDashDrivePosKi, RobotMap.DRIVE_PID_POSITION_KI);
		Kd = SmartDashboard.getNumber(kDashDrivePosKd, RobotMap.DRIVE_PID_POSITION_KD);

		// Reset PID controllers
		leftPID.reset();
		rightPID.reset();

		// Latch in initial positions
		leftInitialPos = RobotMap.DRIVE_ENCODER_LEFT.getDistance();
		rightInitialPos = RobotMap.DRIVE_ENCODER_RIGHT.getDistance();
		thetaInitial = gyroGetAngle();

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

	public void moveStraight(double distance) {
		leftMultiplier = 1.0;
		rightMultiplier = 1.0;
		thetaRatio = 0.0;
		moveStart(distance);
	}

	public void moveTurn(double angle, double radius) {
		double leftRadius, rightRadius;
		double fullRadius, distance;
		
		if(radius < 0.0)
		{
			leftRadius = -radius - RobotMap.DRIVE_BASE_WIDTH / 2.0;
			rightRadius = -radius + RobotMap.DRIVE_BASE_WIDTH / 2.0;
			fullRadius = rightRadius;
			leftMultiplier = leftRadius / fullRadius;
			rightMultiplier = 1.0;
		}
		else
		{
			leftRadius = radius + RobotMap.DRIVE_BASE_WIDTH / 2.0;
			rightRadius = radius - RobotMap.DRIVE_BASE_WIDTH / 2.0;
			fullRadius = leftRadius;
			leftMultiplier = 1.0;
			rightMultiplier = rightRadius/fullRadius;
		}
		distance = (angle / 360.0) * 2.0 * Math.PI * fullRadius;
		thetaRatio = angle / distance;
		moveStart(distance);
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

//	public double ultrasonicGetRange() {
//		return RobotMap.DRIVE_ULTRASONIC.getRangeInches() / 12.0;
//	}

	public void update(boolean debug) {
		if (moveActive)
		{
			refGen.update();
			if (refGen.isActive())
			{
				double refPos = refGen.getRefPosition();
				double theta = thetaRatio * refPos + thetaInitial;
				double gyro = gyroGetAngle();
				double angleError = theta - gyro;
				double proportional, integral, derivative, gyroCorrection;
				
				angErrSum += angleError;
				proportional = angErrKp * angleError;
				integral = angErrKi * angErrSum;
				derivative = angErrKd * (angleError - angErrPrev);
				gyroCorrection = proportional + integral + derivative;
				angErrPrev = angleError;
				
				leftPID.setSetpoint(leftMultiplier * refPos + leftInitialPos + gyroCorrection);
				rightPID.setSetpoint(rightMultiplier * refPos + rightInitialPos - gyroCorrection);
				
//				SmartDashboard.putNumber(kDashDriveTheta, theta);
//				SmartDashboard.putNumber(kDashDriveGyro, gyro);
//				SmartDashboard.putNumber(kDashDriveAngErr, angleError);
//				SmartDashboard.putNumber(kDashDriveGyroCor, gyroCorrection);
			}
			else
			{
				moveStop();
			}
		}

//		if (debug)
//		{
//			/* unused */
//		}
	}
}
