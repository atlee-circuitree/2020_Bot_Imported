/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainSubsystem;


public class elevatorMove extends CommandBase {

  XboxController Xbox1;

  drivetrainSubsystem m_subsystem;

  public elevatorMove(XboxController ElevatorController, drivetrainSubsystem motorSubsystem) {
     
    super();
    Xbox1 = ElevatorController;
    m_subsystem = motorSubsystem;
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //m_subsystem.runElevator(xbcElevatorController.getY(Hand.kLeft));

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    //m_subsystem.stopElevator();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
