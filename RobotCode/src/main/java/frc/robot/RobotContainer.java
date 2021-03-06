/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD licenseaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// FROM LARSON!! https://www.chiefdelphi.com/t/error-message-from-robotbase/162791/12

//Starting Position Angle 38 Degrees

// Robot is 158 incines from the cell port

package frc.robot;

import java.util.List;
import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.CenterOnTargetLimelight;
import frc.robot.commands.ShootWaitVelocity;
import frc.robot.commands.TimerCommand;
import frc.robot.commands.autonomusCommand2020;
import frc.robot.commands.ballObstructionSensorCommand;
import frc.robot.commands.checkForShooterVelocity;
import frc.robot.commands.climbArmDownPnumaticCommand;
import frc.robot.commands.climbArmUpPnumaticCommand;
import frc.robot.commands.climbHookExtendPnumaticCommand;
import frc.robot.commands.climbHookRetractPnumaticCommand;
import frc.robot.commands.closeShooterPnumaticCommand;
import frc.robot.commands.conveyorbeltObstructedCommand;
import frc.robot.commands.conveyorbeltclearCommand;
import frc.robot.commands.driveBackwardsCommand;
import frc.robot.commands.driveForwardCommand;
import frc.robot.commands.drivetrainCommand;
import frc.robot.commands.drivetrainPercentPowerAuto;
import frc.robot.commands.elevatorMotorCommand;
import frc.robot.commands.elevatorRunToPositionDownMotorCommand;
import frc.robot.commands.elevatorRunToPositionUpMotorCommand;
//import frc.robot.commands.elevatorMoveToAngleMotorCommand;
import frc.robot.commands.intakeSpitballMotorCommand;
import frc.robot.commands.driveForwardCommand;
import frc.robot.commands.intakeTakeballMotorCommand;
//import frc.robot.commands.levelerLeftMotorCommand;
//import frc.robot.commands.levelerRightMotorCommand;
import frc.robot.commands.limelightAutoAimCommand;
import frc.robot.commands.openShooterPnumaticCommand;
import frc.robot.commands.runShooter50MotorCommand;
import frc.robot.commands.runShooterMotorCommand;
import frc.robot.commands.runShooterVelocityMotorCommand;
import frc.robot.commands.runUntilNotObstructedSensorCommand;
import frc.robot.commands.runUntilObstructedSensorCommand;
import frc.robot.commands.shooterOnlyConveyorMotorCommand;
import frc.robot.commands.shooterOnlyMotorCommand;
//import frc.robot.commands.spinWheelMotorCommand;
import frc.robot.commands.stopConveyorMotorCommand;
import frc.robot.commands.stopShooterMotorCommand;
import frc.robot.commands.straightenballsCommand;
import frc.robot.subsystems.ExampleSubsystem;
//import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.LidarSubsystem;
import frc.robot.subsystems.ballObstructionSensorSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.elevatorMotorSubsystem;
import frc.robot.subsystems.levelerMotorSubsystem;
import frc.robot.subsystems.shooterMotorSubsystem;
import frc.robot.subsystems.shooterIntakeSubsystem;
import frc.robot.subsystems.shooterAndKickoutPnumaticSubsystem;
//import frc.robot.subsystems.wheelMotorSubsystem;
import frc.robot.subsystems.shooterMotorSubsystem.ShooterMotorStatus;
import frc.robot.Constants;
import frc.robot.subsystems.climbPnumaticSubsystem;
import frc.robot.subsystems.ballObstructionSensorSubsystem;
import frc.robot.commands.ballObstructionSensorCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.LimeLightSubsystem;
import frc.robot.commands.limelightValuesCommand;
import frc.robot.commands.moveShooterDownMotorCommand;
import frc.robot.commands.moveShooterUpMotorCommand;
import frc.robot.commands.printPoseCommand;
import frc.robot.commands.robotTurnToAngleCommand;
import frc.robot.commands.rotateToAngleCommand;
import frc.robot.commands.runConveyorBySpeedMotorCommand;
import frc.robot.commands.runFeederBySpeedMotorCommand;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private static DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private static drivetrainCommand c_dDrivetrainCommand;
  private static elevatorMotorCommand c_delevatorMotorCommand;

  // private final climbPnumaticSubsystem m_climbPnumaticSubsystem = new
  // climbPnumaticSubsystem();
  // private final kickoutPnumaticSubsystem m_kickoutPnumaticSubsystem = new
  // kickoutPnumaticSubsystem();
  private final shooterAndKickoutPnumaticSubsystem m_shooterPnumaticSubsystem = new shooterAndKickoutPnumaticSubsystem();

  private final autonomusCommand2020 m_autonomusCommand2020 = new autonomusCommand2020();

  private final shooterMotorSubsystem m_shooterMotorSubsystem = new shooterMotorSubsystem();
  private final shooterIntakeSubsystem m_shooterIntakeSubsystem = new shooterIntakeSubsystem();

  private final climbPnumaticSubsystem m_climbPnumaticSubsystem = new climbPnumaticSubsystem();

  private final elevatorMotorSubsystem m_elevatorMotorSubsystem = new elevatorMotorSubsystem();

  private final levelerMotorSubsystem m_levelerMotorSubsystem = new levelerMotorSubsystem();

  private final LimeLightSubsystem s_limelightSubsystem = new LimeLightSubsystem();

  private final LidarSubsystem s_lidarSubsystem = new LidarSubsystem();

  // private final autonomusCommand2020 m_autonomusCommand2020 = new
  // autonomusCommand2020();

  private final WaitCommand m_Wait500Command = new WaitCommand(.5);

  private final WaitCommand m_Wait1000Command = new WaitCommand(1);

  private final WaitCommand m_Wait2000Command = new WaitCommand(2);

  private final ballObstructionSensorSubsystem m_ballObstructionSensorSubsystem = new ballObstructionSensorSubsystem();

  //private final wheelMotorSubsystem m_wheelMotorSubsystem = new wheelMotorSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final climbArmUpPnumaticCommand m_climbArmUpPnumaticCommand = new climbArmUpPnumaticCommand(
      m_climbPnumaticSubsystem);
  private final climbArmDownPnumaticCommand m_climbArmDownPnumaticCommand = new climbArmDownPnumaticCommand(
      m_climbPnumaticSubsystem);

  private final climbArmDownPnumaticCommand m_climbArmDownPnumaticCommand2 = new climbArmDownPnumaticCommand(
        m_climbPnumaticSubsystem);
  private final climbHookExtendPnumaticCommand m_climbHookExtendPnumaticCommand = new climbHookExtendPnumaticCommand(
      m_climbPnumaticSubsystem);
  private final climbHookRetractPnumaticCommand m_climbHookRetractPnumaticCommand = new climbHookRetractPnumaticCommand(
      m_climbPnumaticSubsystem);
  private final openShooterPnumaticCommand m_openShooterPnumaticCommand = new openShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);
  private final openShooterPnumaticCommand m_openShooterPnumaticCommand2 = new openShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);
  private final openShooterPnumaticCommand m_openShooterPnumaticCommand3 = new openShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);
  private final openShooterPnumaticCommand m_openShooterPnumaticCommandDriver1 = new openShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);

  private final openShooterPnumaticCommand m_openShooterPnumaticCommand4 = new openShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);

  private final openShooterPnumaticCommand m_openShooterPnumaticCommand5 = new openShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);

  private final closeShooterPnumaticCommand m_closeShooterPnumaticCommand = new closeShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);
  private final closeShooterPnumaticCommand m_closeShooterPnumaticCommand2 = new closeShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);

  private final closeShooterPnumaticCommand m_closeShooterPnumaticCommand3 = new closeShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);

  private final closeShooterPnumaticCommand m_closeShooterPnumaticCommand4 = new closeShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);    

  private final runShooterMotorCommand m_runShooterMotorCommand = new runShooterMotorCommand(m_shooterMotorSubsystem);

  private final runShooterMotorCommand m_runShooterMotorCommand2 = new runShooterMotorCommand(m_shooterMotorSubsystem);

  private final stopShooterMotorCommand m_stopShooterMotorCommand = new stopShooterMotorCommand(
      m_shooterMotorSubsystem);
  private final stopShooterMotorCommand m_KillMotorsDriver2 = new stopShooterMotorCommand(m_shooterMotorSubsystem);
  private final stopShooterMotorCommand m_KillMotorsDriver1 = new stopShooterMotorCommand(m_shooterMotorSubsystem);

  private final stopShooterMotorCommand m_stopShooterMotorCommand2 = new stopShooterMotorCommand(
      m_shooterMotorSubsystem);

  private final stopShooterMotorCommand m_stopShooterMotorCommand3 = new stopShooterMotorCommand(
      m_shooterMotorSubsystem);

  //private final stopConveyorMotorCommand m_stopConveyorMotorCommand = new stopConveyorMotorCommand(
      //m_shooterIntakeSubsystem);

      private final stopConveyorMotorCommand m_stopConveyorMotorCommand2 = new stopConveyorMotorCommand(
        m_shooterIntakeSubsystem);    

  private final shooterOnlyConveyorMotorCommand m_shooterOnlyConveyorMotorCommand2 = new shooterOnlyConveyorMotorCommand(
      m_shooterIntakeSubsystem);

  private final shooterOnlyConveyorMotorCommand m_shooterOnlyConveyorMotorCommand = new shooterOnlyConveyorMotorCommand(
      m_shooterIntakeSubsystem);

  private final shooterOnlyConveyorMotorCommand m_shooterOnlyConveyorMotorCommand3 = new shooterOnlyConveyorMotorCommand(m_shooterIntakeSubsystem);

  private final shooterOnlyConveyorMotorCommand m_shooterOnlyConveyorMotorCommand4 = new shooterOnlyConveyorMotorCommand(m_shooterIntakeSubsystem);

  private final shooterOnlyConveyorMotorCommand m_shooterOnlyConveyorMotorCommand_ConveyorCommandGroup = new shooterOnlyConveyorMotorCommand(m_shooterIntakeSubsystem);

  private final shooterOnlyMotorCommand m_shooterOnlyMotorCommand = new shooterOnlyMotorCommand(
      m_shooterMotorSubsystem);
   
  //private final spinWheelMotorCommand m_spinWheelMotorCommand = new spinWheelMotorCommand(m_wheelMotorSubsystem);

  //private final levelerLeftMotorCommand m_levelerLeftMotorCommand = new levelerLeftMotorCommand(
    //  m_levelerMotorSubsystem);

  //private final levelerRightMotorCommand m_levelerRightMotorCommand = new levelerRightMotorCommand(
      //m_levelerMotorSubsystem);

  private final ballObstructionSensorCommand m_BallObstructionSensorCommand = new ballObstructionSensorCommand(
      m_ballObstructionSensorSubsystem);

  private final intakeTakeballMotorCommand m_intakeTakeballMotorCommand = new intakeTakeballMotorCommand(
      m_shooterIntakeSubsystem, m_ballObstructionSensorSubsystem);

  private final intakeSpitballMotorCommand m_intakeSpitballMotorCommand = new intakeSpitballMotorCommand(
      m_shooterIntakeSubsystem);

  private final intakeSpitballMotorCommand m_intakeSpitballMotorCommandDriver1 = new intakeSpitballMotorCommand(
      m_shooterIntakeSubsystem);

  private final ShootWaitVelocity m_ShootWaitVelocity = new ShootWaitVelocity(m_shooterIntakeSubsystem,
      m_shooterMotorSubsystem, m_ballObstructionSensorSubsystem);

  private final closeShooterPnumaticCommand m_CloseBeforeShoot = new closeShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);

  private final ParallelCommandGroup m_CloseShootWaitVelocity = new ParallelCommandGroup(m_CloseBeforeShoot,
      m_ShootWaitVelocity);

  private final runShooter50MotorCommand m_runShooter50MotorCommand = new runShooter50MotorCommand(
      m_shooterMotorSubsystem, false);

  private final runShooter50MotorCommand m_runShooter50MotorCommand2 = new runShooter50MotorCommand(
      m_shooterMotorSubsystem, false);    

  private final limelightValuesCommand m_limelightValuesCommand = new limelightValuesCommand(s_limelightSubsystem);

  private final limelightAutoAimCommand m_limelightAutoAimCommand = new limelightAutoAimCommand(s_limelightSubsystem);

  private final runUntilObstructedSensorCommand m_runUntilObstructedSensorCommand = new runUntilObstructedSensorCommand(m_ballObstructionSensorSubsystem);

  private final runUntilObstructedSensorCommand m_runUntilObstructedSensorCommand2 = new runUntilObstructedSensorCommand(m_ballObstructionSensorSubsystem);

  private final runUntilObstructedSensorCommand m_runUntilObstructedSensorCommand3 = new runUntilObstructedSensorCommand(m_ballObstructionSensorSubsystem);

  private final runUntilObstructedSensorCommand m_runUntilObstructedSensorCommandGroup = new runUntilObstructedSensorCommand(m_ballObstructionSensorSubsystem);

  private final runUntilNotObstructedSensorCommand m_runUntilNotObstructedSensorCommand = new runUntilNotObstructedSensorCommand(m_ballObstructionSensorSubsystem);

  private final runUntilNotObstructedSensorCommand m_runUntilNotObstructedSensorCommandGroup = new runUntilNotObstructedSensorCommand(m_ballObstructionSensorSubsystem);

  private final checkForShooterVelocity m_checkForShooterVelocity = new checkForShooterVelocity(m_shooterMotorSubsystem);

  private final TimerCommand m_centerDriveBackCommand = new TimerCommand(1000);

  private final TimerCommand m_shooterWarmupTimerCommand = new TimerCommand(1000);

  private final TimerCommand m_kickoutTimer = new TimerCommand(800);

  // This is for autonomous to clear all three balls

  private final TimerCommand m_shooterConveyorTimerCommand = new TimerCommand(2000);

  private final runShooter50MotorCommand m_runShooter50MotorCommandAuto = new runShooter50MotorCommand(
      m_shooterMotorSubsystem, true);

  private final closeShooterPnumaticCommand m_closeShooterPnumaticCommandAuto = new closeShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);

  private final closeShooterPnumaticCommand m_closeShooterPnumaticCommandAuto2 = new closeShooterPnumaticCommand(
        m_shooterPnumaticSubsystem);

  private final shooterOnlyConveyorMotorCommand m_shooterOnlyConveyorMotorCommandAuto = new shooterOnlyConveyorMotorCommand(
      m_shooterIntakeSubsystem);

  private final printPoseCommand m_printPoseCommand = new printPoseCommand(m_drivetrainSubsystem);

  private final ParallelDeadlineGroup m_TimedConveyorGroup = new ParallelDeadlineGroup(m_shooterConveyorTimerCommand,
      m_shooterOnlyConveyorMotorCommandAuto);

  private final SequentialCommandGroup m_WarmUpAndShootBalls = new SequentialCommandGroup(m_shooterWarmupTimerCommand,
      m_TimedConveyorGroup);

  private final SequentialCommandGroup m_shoot50PercentAndCloseShooter = new SequentialCommandGroup (m_runShooter50MotorCommand, m_closeShooterPnumaticCommand4);  

  private final ParallelDeadlineGroup m_CenterShootFromLine = new ParallelDeadlineGroup(m_WarmUpAndShootBalls,
      m_runShooter50MotorCommandAuto);

    //   private final ParallelDeadlineGroup m_CenterShootFromLine2 = new ParallelDeadlineGroup(m_WarmUpAndShootBalls,
    //   new runShooter50MotorCommand(m_shooterMotorSubsystem, true));

  private final TimerCommand m_driveBackwardsTimerAuto = new TimerCommand(500);

  private final TimerCommand m_driveFowardsTimerAuto = new TimerCommand(500);

  private final drivetrainPercentPowerAuto m_drivetrainPercentPowerAuto = new drivetrainPercentPowerAuto(-.5,
      m_drivetrainSubsystem);

  private final moveShooterUpMotorCommand m_moveShooterUpMotorCommand = new moveShooterUpMotorCommand(m_elevatorMotorSubsystem);  
  
  private final moveShooterDownMotorCommand m_moveShooterDownMotorCommand = new moveShooterDownMotorCommand(m_elevatorMotorSubsystem); 


  private final ParallelDeadlineGroup m_driveBackwardsAndStop = new ParallelDeadlineGroup(m_driveBackwardsTimerAuto,
      m_drivetrainPercentPowerAuto);

//private final ParallelDeadlineGroup m_driveForwardsAndStop = new ParallelDeadlineGroup(m_driveFowardsTimerAuto, m_drivetrainPercentPowerAuto);

  private final SequentialCommandGroup m_moveConveyorUntilNotObstructed = new SequentialCommandGroup(m_shooterOnlyConveyorMotorCommand3, m_runUntilNotObstructedSensorCommand);

  private final SequentialCommandGroup m_moveConveyorUntilObstructed = new SequentialCommandGroup(m_shooterOnlyConveyorMotorCommand4, m_runUntilObstructedSensorCommand);

  private final SequentialCommandGroup m_shootAndDriveBackwards = new SequentialCommandGroup(
      m_closeShooterPnumaticCommandAuto, m_CenterShootFromLine, m_driveBackwardsAndStop);

//private final SequentialCommandGroup m_shootandDriveForwards = new SequentialCommandGroup(m_closeShooterPnumaticCommandAuto2, m_CenterShootFromLine2, m_driveForwardsAndStop);

  private final straightenballsCommand m_straightenballsCommand = new straightenballsCommand(m_shooterIntakeSubsystem);   

  private final closeShooterPnumaticCommand c_CloseShooterDriver2 = new closeShooterPnumaticCommand(
      m_shooterPnumaticSubsystem);

  private final SequentialCommandGroup m_stopAndCloseShooter = new SequentialCommandGroup(c_CloseShooterDriver2,
      m_stopShooterMotorCommand2);

  private final SequentialCommandGroup m_stopAndOpenShooter = new SequentialCommandGroup(m_openShooterPnumaticCommand5,
      m_stopShooterMotorCommand3);

  // private final autonomusCommand2020 m_autonomusCommand = new
  // autonomusCommand2020();

  // Wait 1 second.

   private final SequentialCommandGroup m_moveShooterUpAndDownForAuto = new SequentialCommandGroup(m_moveShooterUpMotorCommand.withTimeout(1), m_moveShooterDownMotorCommand.withTimeout(.8));

   private final Command m_runConveyorWithObstructionAndVelocity = GenerateShootCommand();

   private final conveyorbeltclearCommand m_ConveyorbeltclearCommand = new conveyorbeltclearCommand(m_shooterMotorSubsystem, m_ballObstructionSensorSubsystem);

  // private final conveyorbeltObstructedCommand m_ConveyorbeltObstructedCommand =
  // new conveyorbeltObstructedCommand(m_shooterMotorSubsystem,
  // m_bBallObstructionSensorSubsystem);

  private final SequentialCommandGroup m_shootallballs = new SequentialCommandGroup(m_closeShooterPnumaticCommand2, m_ConveyorbeltclearCommand);

  //private final SequentialCommandGroup m_waitUntilNoBalls = new SequentialCommandGroup(commands);

  private final SequentialCommandGroup m_takeallballs = new SequentialCommandGroup(m_closeShooterPnumaticCommand, m_runUntilObstructedSensorCommand2);

  private final SequentialCommandGroup m_intakefulltakeball = new SequentialCommandGroup(m_openShooterPnumaticCommand,
      m_intakeTakeballMotorCommand);

  private final SequentialCommandGroup m_intakefullspitball = new SequentialCommandGroup(m_openShooterPnumaticCommand2,
      m_intakeSpitballMotorCommand);
  private final SequentialCommandGroup m_intakefullspitballDriver1 = new SequentialCommandGroup(
      m_openShooterPnumaticCommandDriver1, m_intakeSpitballMotorCommandDriver1);

  // private final SequentialCommandGroup m_auto = new
  // SequentialCommandGroup(m_runShooter50MotorCommand, m_Wait500Command,
  // m_shooterOnlyConveyorMotorCommand2, m_Wait2000Command,
  // m_spinWheelMotorCommand, m_stopShooterMotorCommand);

  private final SequentialCommandGroup m_runShooterAndClosePnumatic = new SequentialCommandGroup(m_runShooterMotorCommand, m_closeShooterPnumaticCommand3);

  private final SequentialCommandGroup m_killPlayer2WithConveyor = new SequentialCommandGroup(m_KillMotorsDriver2, m_stopConveyorMotorCommand2);

  public static Object driveRobot;

  public static RobotContainer m_RobotContainer;

  public static Constants m_constants;

  public static XboxController Xbox1 = new XboxController(0);
  public static XboxController Xbox2 = new XboxController(1);
  public static Joystick Fightstick = new Joystick(2);

  JoystickButton DriverA = new JoystickButton(Xbox1, XboxController.Button.kA.value); // Take Balls
  JoystickButton DriverB = new JoystickButton(Xbox1, XboxController.Button.kB.value); // Spit Balls
  JoystickButton DriverX = new JoystickButton(Xbox1, XboxController.Button.kX.value); // Opens pneumatic shooter
  JoystickButton DriverY = new JoystickButton(Xbox1, XboxController.Button.kY.value); // Closes pneumatic shooter
  JoystickButton DriverL = new JoystickButton(Xbox1, XboxController.Button.kBumperLeft.value);
  JoystickButton DriverR = new JoystickButton(Xbox1, XboxController.Button.kBumperRight.value);
  JoystickButton Driver1Start = new JoystickButton(Xbox1, XboxController.Button.kStart.value);
  // This is how you bind a trigger to a command - kind of confusing...
  Button DriveLeftTrigger = new Button(() -> Xbox1.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.5);
  Button DriveRightTrigger = new Button(() -> Xbox1.getRawAxis(XboxController.Axis.kRightTrigger.value) > 0.5);

  JoystickButton Driver2A = new JoystickButton(Xbox2, XboxController.Button.kA.value);
  JoystickButton Driver2B = new JoystickButton(Xbox2, XboxController.Button.kB.value);
  JoystickButton Driver2X = new JoystickButton(Xbox2, XboxController.Button.kX.value);
  JoystickButton Driver2Y = new JoystickButton(Xbox2, XboxController.Button.kY.value);
  JoystickButton Driver2L = new JoystickButton(Xbox2, XboxController.Button.kBumperLeft.value);
  JoystickButton Driver2R = new JoystickButton(Xbox2, XboxController.Button.kBumperRight.value);
  JoystickButton Driver2Start = new JoystickButton(Xbox2, XboxController.Button.kStart.value);

  Button Drive2LeftTrigger = new Button(() -> Xbox2.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.5);
  Button Drive2RightTrigger = new Button(() -> Xbox2.getRawAxis(XboxController.Axis.kRightTrigger.value) > 0.5);

  JoystickButton FightStickB = new JoystickButton(Fightstick, 2);
  JoystickButton FightStickY = new JoystickButton(Fightstick, 4);
  JoystickButton FightStickLB = new JoystickButton(Fightstick, 5);
  JoystickButton FightStickRB = new JoystickButton(Fightstick, 6);
  JoystickButton FightStickL3 = new JoystickButton(Fightstick, 9);
  JoystickButton FightStickR3 = new JoystickButton(Fightstick, 10);
  JoystickButton FightStickL1 = new JoystickButton(Fightstick, 5);
  JoystickButton FightStickSHARE = new JoystickButton(Fightstick, 7);
  JoystickButton FightStickOPTIONS = new JoystickButton(Fightstick, 8);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    //setUpDrive();

    configureButtonBindings();
  }

  // Operations specific to TeleOp only
  public void teleopInit()
  {
    c_dDrivetrainCommand = new drivetrainCommand(Xbox1, m_drivetrainSubsystem);
    c_delevatorMotorCommand = new elevatorMotorCommand(Xbox2, m_elevatorMotorSubsystem);
    m_drivetrainSubsystem.setDefaultCommand(c_dDrivetrainCommand);
    m_elevatorMotorSubsystem.setDefaultCommand(c_delevatorMotorCommand);
    m_shooterMotorSubsystem.shooterMotorStatus = ShooterMotorStatus.IS_NOT_RUNNING;
    GenerateShootSpeedCommand(0);
    m_drivetrainSubsystem.resetEncoders();
    m_elevatorMotorSubsystem.resetEncoders();

     
    //m_constants.airCompressor = new Compressor(1);

    //m_constants.airCompressor.setClosedLoopControl(true);
    //m_constants.airCompressor.start();

  }

  

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    DriverA.whenPressed(new SequentialCommandGroup(
        new openShooterPnumaticCommand(m_shooterPnumaticSubsystem), 
        new runShooterVelocityMotorCommand(m_shooterMotorSubsystem, -3500)));
    DriverB.whileHeld(m_stopShooterMotorCommand);
    DriverX.whileHeld(new CenterOnTargetLimelight(m_drivetrainSubsystem, s_limelightSubsystem));
    DriveLeftTrigger.whileHeld(m_intakefulltakeball);
    DriveRightTrigger.whileHeld(m_intakefullspitballDriver1);
    DriverY.whileHeld(m_straightenballsCommand);
    //DriverR.whileHeld(m_levelerLeftMotorCommand);
    //DriverL.whileHeld(m_levelerRightMotorCommand);
    Driver1Start.whenPressed(m_KillMotorsDriver1);

    Driver2A.whenPressed(m_runConveyorWithObstructionAndVelocity);
    Driver2B.whenPressed(new robotTurnToAngleCommand(m_drivetrainSubsystem, 90)
        .andThen(new InstantCommand(() -> m_drivetrainSubsystem.setBrakes(), m_drivetrainSubsystem).withTimeout(0.2))
        .andThen(new InstantCommand(() -> m_drivetrainSubsystem.disableBrakes(), m_drivetrainSubsystem))
        );
    //Driver2B.whileHeld(m_shootWaitObstructionParallel);
    //Driver2X.whileHeld(new CenterOnTargetLimelight(m_drivetrainSubsystem, s_limelightSubsystem));
    //Driver2Y.toggleWhenPressed(m_limelightAutoAimCommand);
    Driver2R.whenPressed(m_stopAndOpenShooter);
    Driver2L.whenPressed(m_stopAndCloseShooter);
    Driver2Start.whenPressed(m_killPlayer2WithConveyor);
    Drive2RightTrigger.whileHeld(new SequentialCommandGroup(new runShooterVelocityMotorCommand(m_shooterMotorSubsystem, 12500), GenerateShootCommand()));
    Drive2LeftTrigger.whileHeld(new SequentialCommandGroup(new runShooterVelocityMotorCommand(m_shooterMotorSubsystem, 8500), GenerateShootCommand()));
    
    FightStickB.whenPressed(m_climbArmDownPnumaticCommand);
    FightStickY.whenPressed(m_climbHookExtendPnumaticCommand);
    FightStickRB.whenPressed(m_climbArmUpPnumaticCommand);
    //FightStickRT.whenPressed(m_climbArmDownPnumaticCommand);
    FightStickL3.whenPressed(m_climbArmDownPnumaticCommand);
    FightStickR3.whenPressed(m_CloseBeforeShoot);
    FightStickL1.whenPressed(m_climbHookRetractPnumaticCommand);
    
  }

  public Command GenerateShootCommand()
  {

      ParallelDeadlineGroup m_shootWaitObstructionParallelGenerated = new ParallelDeadlineGroup(new runUntilObstructedSensorCommand(m_ballObstructionSensorSubsystem).withTimeout(1), new shooterOnlyConveyorMotorCommand(m_shooterIntakeSubsystem));

      ParallelDeadlineGroup m_runConveyorWithObstructionCheckGenerated = new ParallelDeadlineGroup(new runUntilNotObstructedSensorCommand(m_ballObstructionSensorSubsystem).withTimeout(1), new shooterOnlyConveyorMotorCommand(m_shooterIntakeSubsystem));
 
      SequentialCommandGroup m_runConveyorWithObstructionAndVelocity = new SequentialCommandGroup(m_runConveyorWithObstructionCheckGenerated);

      return m_runConveyorWithObstructionAndVelocity;
  }

  public Command GenerateEncoderDriveCommand(double inches, double speed)
  {

      double PPR;
      double GearReduction;
      double WheelDiameter;
      double Pi;
      double CPI;

      PPR = 42;
      GearReduction = 10.75;
      WheelDiameter = 8;
      Pi = 3.141592653589793238;
      CPI = (PPR * GearReduction) / (WheelDiameter * Pi);
      //451.5, 25.132


      double encoder = (inches / 42) * CPI;

      System.out.print("Encoder Target, ");
      System.out.print(encoder); 

      Command m_driveStraightUntilEncoderValueCommand = new driveForwardCommand(encoder, speed, m_drivetrainSubsystem);

      return m_driveStraightUntilEncoderValueCommand;
      
  }

  public Command GenerateEncoderDriveBackwardsCommand(double inches, double speed)
  {

      double PPR;
      double GearReduction;
      double WheelDiameter;
      double Pi;
      double CPI;

      PPR = 42;
      GearReduction = 10.75;
      WheelDiameter = 8;
      Pi = 3.141592653589793238;
      CPI = (PPR * GearReduction) / (WheelDiameter * Pi);
      //451.5, 25.132


      double encoder = (inches / 42) * CPI;

      System.out.print("Encoder Target, ");
      System.out.print(encoder); 

      Command m_driveBackwardsUntilEncoderValueCommand = new driveBackwardsCommand(encoder, speed, m_drivetrainSubsystem);

      return m_driveBackwardsUntilEncoderValueCommand;
      
  }


  public Command GenerateTurnCommand(double angle) {

    Command m_TurnToAngleCommand = new robotTurnToAngleCommand(m_drivetrainSubsystem, angle);

    return m_TurnToAngleCommand;

  }

  public Command GenerateTimerCommand(double seconds) {

   Command m_generatedTimerCommand = new TimerCommand(seconds * 1000);

   return m_generatedTimerCommand;

  }

  public Command GenerateShootSpeedCommand(double speed) { 

    Command m_generatedShooterMotorCommand = new runShooterVelocityMotorCommand(m_shooterMotorSubsystem, speed);

    return m_generatedShooterMotorCommand;
           
  }

  public Command GenerateConveyerCommand(boolean IsOn) {

    double targetSpeed;  

    if (IsOn == true) {

      targetSpeed = .3;

    } else {

      targetSpeed = 0;

    }

    Command m_generatedConveyorCommand = new runConveyorBySpeedMotorCommand(targetSpeed, m_shooterIntakeSubsystem);

    return m_generatedConveyorCommand;

  }

  public Command GenerateShooterOpenCommand() {

    Command m_generatedOpenShooterCommand = new openShooterPnumaticCommand(m_shooterPnumaticSubsystem);

    return m_generatedOpenShooterCommand;

  }

  public Command GenerateShooterCloseCommand() {

    Command m_generatedCloseShooterCommand = new closeShooterPnumaticCommand(m_shooterPnumaticSubsystem);

    return m_generatedCloseShooterCommand;

  }

  public Command GenerateFeederCommand(boolean IsOn) {

    double targetSpeed;  

    if (IsOn == true) {

      targetSpeed = 1;

    } else {

      targetSpeed = 0;

    }

    Command m_generatedFeederCommand = new runFeederBySpeedMotorCommand(targetSpeed, m_shooterIntakeSubsystem);

    return m_generatedFeederCommand;

  }

  public Command GenerateElevatorUpCommand(double value, double speed) {

   Command m_generatedElevatorCommand = new elevatorRunToPositionUpMotorCommand(value, speed, m_elevatorMotorSubsystem);

   return m_generatedElevatorCommand;

  }

  public Command GenerateElevatorDownCommand(double value, double speed) {

    Command m_generatedElevatorCommand = new elevatorRunToPositionDownMotorCommand(value, speed, m_elevatorMotorSubsystem);
 
    return m_generatedElevatorCommand;
 
   }

  SequentialCommandGroup m_testAutoTurn = new SequentialCommandGroup(GenerateTurnCommand(90), GenerateTimerCommand(3), GenerateTurnCommand(180), GenerateTimerCommand(3), GenerateTurnCommand(270));

  SequentialCommandGroup m_Auto = new SequentialCommandGroup(GenerateTurnCommand(30), GenerateTimerCommand(3), GenerateEncoderDriveBackwardsCommand(36, .3));

  ParallelCommandGroup SixBallAuto1 = new ParallelCommandGroup(GenerateShooterCloseCommand(), GenerateShootSpeedCommand(6000), GenerateConveyerCommand(true).withTimeout(2));
  ParallelCommandGroup SixBallAuto2 = new ParallelCommandGroup(GenerateElevatorUpCommand(50, 1), GenerateShootSpeedCommand(0), GenerateShooterOpenCommand());
  ParallelCommandGroup SixBallAuto3 = new ParallelCommandGroup(GenerateFeederCommand(true), GenerateElevatorDownCommand(800, 1), GenerateEncoderDriveBackwardsCommand(48, .45));
  ParallelCommandGroup SixBallAuto4 = new ParallelCommandGroup(GenerateFeederCommand(false));

  SequentialCommandGroup SixBallAuto = new SequentialCommandGroup(SixBallAuto1.withTimeout(2), SixBallAuto2.withTimeout(2), SixBallAuto3.withTimeout(8), SixBallAuto4.withTimeout(1));

  public void DisabledInit()
  {
      //reset pose when disabled - per https://www.chiefdelphi.com/t/trajectory-generation-in-autonomous-path-following-issues/378469/6
      //m_drivetrainSubsystem.resetOdometry(new Pose2d(0, 0, Rotation2d.fromDegrees(0)));
      GenerateConveyerCommand(false);
      GenerateShootSpeedCommand(0);
  }

 public Command getAutonomousCommand() {
        return SixBallAuto;
    }

}