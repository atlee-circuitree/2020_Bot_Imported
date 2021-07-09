// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
  
  DoubleSolenoid solenoid;
  DoubleSolenoid solenoid2;
  DoubleSolenoid solenoid3;
  DoubleSolenoid solenoid4;

  public Pneumatics() {
    //DoubleSolenoid(PCU, Forward port, Reverse port)
    solenoid = new DoubleSolenoid(0, 0, 1);
    solenoid2 = new DoubleSolenoid(0, 2, 3);
    solenoid3 = new DoubleSolenoid(0, 4, 5);
    solenoid4 = new DoubleSolenoid(0, 6, 7);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void extendSolenoid(){
    solenoid.set(Value.kForward);
  }
  public void retractSolenoid(){
    solenoid.set(Value.kReverse);
  }
  public void cutoffSolenoid(int solenoidNumber){

    if(solenoidNumber == 1){
      solenoid.set(Value.kOff);
    }
    else if(solenoidNumber == 2){
      solenoid2.set(Value.kOff);
    }
    else if(solenoidNumber == 3){
      solenoid3.set(Value.kOff);
    }
    else if(solenoidNumber == 4){
      solenoid4.set(Value.kOff);
    }
  }

  public void toggleSolenoid(int solenoidNumber){

    if(solenoidNumber == 1){
      solenoid.toggle();
    }
    else if(solenoidNumber == 2){
      solenoid2.toggle();
    }
    else if(solenoidNumber == 3){
      solenoid3.toggle();
    }
    else if(solenoidNumber == 4){
      solenoid4.toggle();
    }
  }
  
  public void retractAllSolenoids(){
    solenoid.set(Value.kReverse);
    solenoid2.set(Value.kReverse);
    solenoid3.set(Value.kReverse);
    solenoid4.set(Value.kReverse);
  }
  

}
