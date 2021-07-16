// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//CountsPerInch = (CountsPerPulse * GearReduction) / (WheelDiameter * PI)

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  
  SpeedControllerGroup leftDrive;
  SpeedControllerGroup rightDrive;
  CANSparkMax leftFrontMotor;
  CANSparkMax rightFrontMotor;
  CANSparkMax leftRearMotor;
  CANSparkMax rightRearMotor;
  CANEncoder leftEncoder;
  CANEncoder rightEncoder;
    
  DifferentialDrive drive;
  
  public DriveTrain() {
    leftFrontMotor = new CANSparkMax(1, MotorType.kBrushless);
    rightFrontMotor = new CANSparkMax(2, MotorType.kBrushless);
    leftRearMotor = new CANSparkMax(3, MotorType.kBrushless);
    rightRearMotor = new CANSparkMax(4, MotorType.kBrushless);
    
    leftFrontMotor.setInverted(true);

    leftFrontMotor.setIdleMode(IdleMode.kBrake);
    rightFrontMotor.setIdleMode(IdleMode.kBrake);
    leftRearMotor.setIdleMode(IdleMode.kBrake);
    rightRearMotor.setIdleMode(IdleMode.kBrake);
    
    leftEncoder = leftFrontMotor.getEncoder(EncoderType.kHallSensor, 4096);
    rightEncoder = rightRearMotor.getEncoder(EncoderType.kHallSensor, 4096);

    leftEncoder.setPositionConversionFactor(42);

    leftDrive = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);
    rightDrive = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);
    
    drive = new DifferentialDrive(leftDrive, rightDrive);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void driveWithJoystick(double speed, double X, double Y){
    drive.arcadeDrive(Y, -X, false);
  }
  public void driveForward(double speed){
    drive.tankDrive(speed, speed);
  }
  public void stop(){
    drive.stopMotor();
  }
  public double getLeftEncoder(){
    return leftEncoder.getPosition();
  }
  public double getRightEncoder(){
    return rightEncoder.getPosition();
  }

  public void resetEncoders(){
    leftEncoder.getPosition();
    rightEncoder.getPosition();
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }
  public void runRearLeft(){
    leftRearMotor.set(0.5);
  }
}
