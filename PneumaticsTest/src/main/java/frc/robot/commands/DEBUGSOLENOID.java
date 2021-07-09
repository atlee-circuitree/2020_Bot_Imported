// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pneumatics;

public class DEBUGSOLENOID extends CommandBase {
  private final Pneumatics solenoid;
  private int solNumber;
  //private boolean initialized = false;

  public DEBUGSOLENOID(Pneumatics ps, int solenoidNumber) {
    
    solenoid = ps;
    solNumber = solenoidNumber;
    addRequirements(solenoid);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(solNumber == 1){
      solenoid.toggleSolenoid(1);
    }
    else if(solNumber == 2){
      solenoid.toggleSolenoid(2);
    }
    else if(solNumber == 3){
      solenoid.toggleSolenoid(3);
    }
    else if(solNumber == 4){
      solenoid.toggleSolenoid(4);
    }
    else if(solNumber == 5){
      solenoid.retractAllSolenoids();
    }
    else{
      solenoid.cutoffSolenoid(1);
      solenoid.cutoffSolenoid(2);
      solenoid.cutoffSolenoid(3);
      solenoid.cutoffSolenoid(4);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
