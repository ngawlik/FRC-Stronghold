package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	/**
	 * Default Robot-wide initialization which will be called when the robot is
	 * first powered on. It will be called exactly one time.
	 *
	 * Warning: the Driver Station "Robot Code" light and FMS "Robot Ready"
	 * indicators will be off until RobotInit() exits. Code in RobotInit() that
	 * waits for enable will cause the robot to never indicate that the code is
	 * ready, causing the robot to be bypassed in a match.
	 */
	public void robotInit() {
		RobotMap.init();
	}

	/**
	 * Initialization code which will be called each time the robot enters disabled mode.
	 */
	public void disabledInit() {

	}

	/**
	 * Initialization code which will be called each time the robot enters autonomous mode.
	 */
	public void autonomousInit() {

	}

	/**
	 * Initialization code which will be called each time the robot enters teleop mode.
	 */
	public void teleopInit() {

	}

	/**
	 * Initialization code which will be called each time the robot enters test mode.
	 */
	public void testInit() {

	}

	/**
	 * Code which will be called periodically at a regular rate while the robot is in
	 * disabled mode. (50 Hz)
	 */
	public void disabledPeriodic() {

	}

	/**
	 * Code which will be called periodically at a regular rate while the robot is in
	 * autonomous mode. (50 Hz)
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * Code which will be called periodically at a regular rate while the robot is in
	 * teleop mode. (50 Hz)
	 */
	public void teleopPeriodic() {

	}

	/**
	 * Code which will be called periodically at a regular rate while the robot is in
	 * test mode. (50 Hz)
	 */
	public void testPeriodic() {

	}
}
