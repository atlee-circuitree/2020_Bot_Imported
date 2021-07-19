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
import frc.robot.subsystems.DrivetrainSubsystem;

public class rotateToAngleCommand extends CommandBase {

  DrivetrainSubsystem m_subsystem;

  double targetAngle;
  double targetPower;

  public rotateToAngleCommand(double angle, double power, DrivetrainSubsystem m_subsystem) {
     
    super();
    addRequirements(m_subsystem);

    targetAngle = angle;
    targetPower = power;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    System.out.println("Target Angle");
    //System.out.println(m_subsystem.getNavxAngle());


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_subsystem.turnLeftSetPercentAuto(targetPower);
 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
 

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
