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
import static edu.wpi.first.wpilibj.GenericHID.Hand.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.time.*;
import java.time.temporal.TemporalUnit;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  //the four TalonSRXs that control the wheels
  public TalonSRX right1;
  public TalonSRX right2;
  public TalonSRX left1;
  public TalonSRX left2;

  public TalonSRX doorMotor;
  public TalonSRX ballGear;

  //ultrasonic sensor
  public AnalogInput ultrasonicSensor; //not using this?

  //flag to check if we should turn the ball gear
  public boolean isBallGearOn = false;

  //timer
  public Timer timer;

  //controller
  public XboxController controller;

  //double solenoid piston
  public DoubleSolenoid piston1;

  //percent of max speed to run at
  public double aspeed = 0.25;

  public boolean debugMode = false;

  //should move
  public boolean mov = false;

  Instant trapdoorInstant;
  boolean open = false;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    //System.out.println("robot init");

    //init talon
    right1 = new TalonSRX(5); //port 5 on CAN
    right2 = new TalonSRX(4); //port 4 on CAN
    left1 = new TalonSRX(3); //port 3 on CAN
    left2 = new TalonSRX(1); //port 1 on CAN*/

    ballGear = new TalonSRX(2); //port 2 on CAN

    doorMotor = new TalonSRX(6);

    //ultrasonicSensor = new AnalogInput(0); // port () on CAN

    controller = new XboxController(0); //port 0 on driver station

    /* comment out becasue we don't have a piston attached rn
      piston1 = new DoubleSolenoid(0, 1); //set ports used to be 0 and 1 on the pcm 
      piston1.set(kForward); //set it to start in the forwards position
    */

    timer = new Timer(); // init timer

    trapdoorInstant = Instant.now(); //init instant with current time
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    //System.out.println("autonomous init");
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    //System.out.println("autonomous periodic");
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    timer.start();
    //System.out.println("teleop init");
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    if(controller.getAButtonPressed()) {
      trapdoorInstant = Instant.now();
      open = !open;
    }

    if(Duration.between(trapdoorInstant, Instant.now()).toMillis() < 300) {
      if(open) {
        doorMotor.set(ControlMode.PercentOutput, 0.2);
      }else {
        doorMotor.set(ControlMode.PercentOutput, -0.2);
      }
    }else {
      doorMotor.set(ControlMode.PercentOutput, 0);
    }

    if(controller.getBButtonPressed()) {
      isBallGearOn = !isBallGearOn;
    }

    if(isBallGearOn) {
      ballGear.set(ControlMode.PercentOutput, 0.4);
    }else {
      ballGear.set(ControlMode.PercentOutput, 0);
    }

    if(controller.getBumper(kLeft)) {
      right1.set(ControlMode.PercentOutput, -aspeed);
      right2.set(ControlMode.PercentOutput, -aspeed);
      left1.set(ControlMode.PercentOutput, -aspeed);
      left2.set(ControlMode.PercentOutput, -aspeed);
    }else if(controller.getBumper(kRight)) {
      right1.set(ControlMode.PercentOutput, aspeed);
      right2.set(ControlMode.PercentOutput, aspeed);
      left1.set(ControlMode.PercentOutput, aspeed);
      left2.set(ControlMode.PercentOutput, aspeed);
    }else {
      //y is negated because AFAIK joystick up is negative for some unknowable reason (thanks WPILIB, appreciate it)
      double speed = Math.min(-controller.getY(kLeft) * aspeed, 1.0); //dont go faster than 90%
      right1.set(ControlMode.PercentOutput, -speed);
      right2.set(ControlMode.PercentOutput, -speed);
      left1.set(ControlMode.PercentOutput, speed);
      left2.set(ControlMode.PercentOutput, speed);
      //System.out.println("speed: " + controller.getY(kLeft));
    }

    if(controller.getYButton()) {
      right1.set(ControlMode.PercentOutput, 0);
      right2.set(ControlMode.PercentOutput, 0);
      left1.set(ControlMode.PercentOutput, 0);
      left2.set(ControlMode.PercentOutput, 0);
    }
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
