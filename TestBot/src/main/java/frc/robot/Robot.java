// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.ControlMode; //you need the phoenix framework to deploy the code (once again thanks WPILIB)
import com.ctre.phoenix.motorcontrol.can.TalonSRX; //you need the phoenix framework to deploy the code (once again thanks WPILIB)
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  //the four TalonSRXs that control the wheels
  public TalonSRX rightFront;
  public TalonSRX rightBack;
  public TalonSRX leftFront;
  public TalonSRX leftBack;

  public Timer timer;
  public XboxController controller;

  public DoubleSolenoid piston1;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    System.out.println("robot init");

    //init talon
    rightFront = new TalonSRX(1); //port 1 on CAN
    rightBack = new TalonSRX(2); //port 2 on CAN
    leftFront = new TalonSRX(3); //port 3 on CAN
    leftBack = new TalonSRX(4); //port 4 on CAN*/

    controller = new XboxController(0); //port 0 on driver station

    piston1 = new DoubleSolenoid(0, 1); //i really have no idea how this works. 
    piston1.set(kForward);

    timer = new Timer(); // init timer
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    System.out.println("autonomous init");
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    System.out.println("autonomous periodic");
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    timer.start();
    System.out.println("teleop init");
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    System.out.println("teleop periodic");
    
    if(controller.getAButtonPressed()) {
      piston1.toggle();
    }

    //y is negated because AFAIK joystick up is negative for some unknowable reason (thanks WPILIB, appreciate it)
    /*double speed = Math.min(-controller.getY(), 0.9); //dont go faster than 90%
    rightFront.set(ControlMode.PercentOutput, speed);
    leftFront.set(ControlMode.PercentOutput, speed);
    rightBack.set(ControlMode.PercentOutput, speed);
    leftBack.set(ControlMode.PercentOutput, speed);
    System.out.println("speed: " + controller.getY());*/
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
