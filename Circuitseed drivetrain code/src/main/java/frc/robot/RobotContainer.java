// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
//import frc.robot.commands.DEBUGGING;
//import frc.robot.commands.CutoffSolenoid;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.EncoderDrive;
//import frc.robot.commands.ExtendSolenoid;
//import frc.robot.commands.RetractSolenoid;
import frc.robot.commands.Stop;
//import frc.robot.commands.ToggleSolenoid;
import frc.robot.subsystems.DriveTrain;
//import frc.robot.subsystems.Pneumatics;
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


  private final DriveTrain driveTrain;
  //private final Pneumatics pneumatics;
  private final DriveWithJoystick driveWithJoystick;
  private final DriveForwardTimed driveForwardTimed;
  private final EncoderDrive encoderDrive;
  private final Stop stop;
  //private final ToggleSolenoid toggleSolenoid;
  //private final ExtendSolenoid extendSolenoid;
  //private final RetractSolenoid retractSolenoid;
  //private final DEBUGGING debugging;
  //private final CutoffSolenoid cutoffSolenoid;
  
  public static Joystick flightstick;
  public static JoystickButton flightstickTrigger;
  public static JoystickButton flightstickButton2;
  public static JoystickButton flightstickButton3;
  
  public static Joystick flightstick2;
  public static JoystickButton flightstick2Trigger;
  public static JoystickButton flightstick2Button2;

  public static XboxController xbox1;
  public static JoystickButton xboxButtonA;

  

  public RobotContainer() {

    //Drive Setup
    driveTrain = new DriveTrain();
    driveWithJoystick = new DriveWithJoystick(driveTrain);
    driveWithJoystick.addRequirements(driveTrain);
    driveTrain.setDefaultCommand(driveWithJoystick);
    xbox1 = new XboxController(0);
    

    //Auto command setup
    driveForwardTimed = new DriveForwardTimed(driveTrain);
    driveForwardTimed.addRequirements(driveTrain);

    encoderDrive = new EncoderDrive(18.85, 0.5, driveTrain);
    encoderDrive.addRequirements(driveTrain);
    
    //Other command setup
    //pneumatics = new Pneumatics();
    stop = new Stop(driveTrain);
    //toggleSolenoid = new ToggleSolenoid(pneumatics);
    //cutoffSolenoid = new CutoffSolenoid(pneumatics);
    //extendSolenoid = new ExtendSolenoid(pneumatics);
    //retractSolenoid = new RetractSolenoid(pneumatics);
    //debugging = new DEBUGGING(driveTrain);

    
    
    // Configure the button bindings
    configureButtonBindings();
  }



  private void configureButtonBindings() {

    //Flightstick 1
    flightstick = new Joystick(0);
    flightstickTrigger = new JoystickButton(flightstick, 1);
    flightstickButton2 = new JoystickButton(flightstick, 2);
    flightstickButton3 = new JoystickButton(flightstick, 3);

    //Flightstick 2
    flightstick2 = new Joystick(1);
    flightstick2Trigger = new JoystickButton(flightstick2, 1);
    flightstick2Button2 = new JoystickButton(flightstick2, 2);

    //Xbox
    xboxButtonA = new JoystickButton(xbox1, XboxController.Button.kA.value);

    //Button assignments
    flightstick2Button2.whileHeld(stop);
    //flightstickButton2.whenPressed(extendSolenoid);
    //flightstickButton3.whenPressed(retractSolenoid);
    //xboxButtonA.whenActive(debugging);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return encoderDrive;
  }
}
