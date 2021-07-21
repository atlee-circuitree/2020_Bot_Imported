/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.elevatorMotorCommand;


public class elevatorMotorSubsystem extends SubsystemBase {

  CANSparkMax elevatorMotor = new CANSparkMax(9, MotorType.kBrushless);

  CANEncoder elevatorEncoder = elevatorMotor.getEncoder();

  public elevatorMotorSubsystem() {

  }


public void resetEncoders() {

  elevatorEncoder.setPosition(0);

}

public double getEncoderValue() {

  return elevatorEncoder.getPosition();

}

public void runElevator(double power) {

  elevatorMotor.set(power);

}

public void moveShooterUp()
{
  elevatorMotor.set(1);
}

public void moveShooterDown() {

  elevatorMotor.set(-1);
   
}

public void stopElevator() {

  elevatorMotor.set(0); 
    
}
}