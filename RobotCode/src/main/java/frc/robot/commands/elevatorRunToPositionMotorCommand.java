/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevatorMotorSubsystem;


public class elevatorRunToPositionMotorCommand extends CommandBase {

  elevatorMotorSubsystem m_subsystem;
  double encoderReading;
  double encoderTarget;
  double speedTarget;

  public elevatorRunToPositionMotorCommand(double targetValue, double speed, elevatorMotorSubsystem motorSubsystem) {
     
    super();
    m_subsystem = motorSubsystem;
    addRequirements(m_subsystem);

    encoderTarget = targetValue;
    speedTarget = speed;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    m_subsystem.resetEncoders();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    encoderReading = m_subsystem.getEncoderValue();

    if (encoderTarget > encoderReading) {

      m_subsystem.runElevator(speedTarget);

    } else if (encoderTarget <= encoderReading) {

      m_subsystem.stopElevator();

    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    m_subsystem.stopElevator();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
   {
    if (encoderReading <= encoderTarget) {

      return false;

    } else {

      return true;

    }
  }
}
