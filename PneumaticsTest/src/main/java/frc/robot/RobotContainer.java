// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DEBUGSOLENOID;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
// The robot's subsystems and commands are defined here...

  private final Pneumatics pneumatics;

  private final DEBUGSOLENOID debugSolenoid;
  private final DEBUGSOLENOID debugSolenoid2;
  private final DEBUGSOLENOID debugSolenoid3;
  private final DEBUGSOLENOID debugSolenoid4;
  private final DEBUGSOLENOID debugSolenoid5;
  private final DEBUGSOLENOID debugSolenoid6;

  public static XboxController Xbox1 = new XboxController(0);

  JoystickButton DriverA = new JoystickButton(Xbox1, XboxController.Button.kA.value); 
  JoystickButton DriverB = new JoystickButton(Xbox1, XboxController.Button.kB.value); 
  JoystickButton DriverX = new JoystickButton(Xbox1, XboxController.Button.kX.value); 
  JoystickButton DriverY = new JoystickButton(Xbox1, XboxController.Button.kY.value);
  JoystickButton DriverR = new JoystickButton(Xbox1, XboxController.Button.kBumperRight.value);
  JoystickButton DriverL = new JoystickButton(Xbox1, XboxController.Button.kBumperLeft.value); 


  public RobotContainer() {

    pneumatics = new Pneumatics();

    debugSolenoid = new DEBUGSOLENOID(pneumatics,1);
    debugSolenoid2 = new DEBUGSOLENOID(pneumatics,2);
    debugSolenoid3 = new DEBUGSOLENOID(pneumatics,3);
    debugSolenoid4 = new DEBUGSOLENOID(pneumatics,4);
    debugSolenoid5 = new DEBUGSOLENOID(pneumatics,5);
    debugSolenoid6 = new DEBUGSOLENOID(pneumatics,6);
    
    // Configure the button bindings
    configureButtonBindings();
  }



  private void configureButtonBindings() {

    DriverA.whenPressed(debugSolenoid);
    DriverB.whenPressed(debugSolenoid2);
    DriverX.whenPressed(debugSolenoid3);
    DriverY.whenPressed(debugSolenoid4);
    DriverR.whenPressed(debugSolenoid5);
    DriverL.whenPressed(debugSolenoid6);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Run path following command, then stop at the end.
    return debugSolenoid;

  }
}
