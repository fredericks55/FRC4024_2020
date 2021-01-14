// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  //the four servos that control the wheels
  public SpeedController rightFront;
  public SpeedController rightBack;
  public SpeedController leftFront;
  public SpeedController leftBack;

  public Timer timer;
  public Joystick moveJoystick;
  public Joystick rotateJoystick;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
      //currently initing with Talon, but can be anything since we're using the SpeedController interface
      rightFront = new Talon(0); //port 0 on PMC
      rightBack = new Talon(1); //port 1 on PMC
      leftFront = new Talon(2); //port 2 on PMC
      leftBack = new Talon(3); //port 3 on PMC

      moveJoystick = new Joystick(0); //port 0 on driver station
      rotateJoystick = new Joystick(1); //port 1 on driver station

      timer = new Timer(); // init timer
      m_period = 5; // idk
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    timer.start();
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    rightFront.set(-moveJoystick.getY());
    leftFront.set(-moveJoystick.getY());
    rightBack.set(-moveJoystick.getY());
    leftBack.set(-moveJoystick.getY());
    System.out.println("yVal: " + moveJoystick.getY());
    System.out.println("xVal: " + moveJoystick.getX());

    
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {

  }
}
