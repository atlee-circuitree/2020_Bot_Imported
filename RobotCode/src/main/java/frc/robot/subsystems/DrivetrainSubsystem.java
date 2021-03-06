/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// Added by Panten 3/6/2020
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants; // Added by Panten 3/6/2020
//import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.SPI;
//End Panten additions 3/6/2020

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj.XboxController;
//import frc.robot.Constants;
//import frc.robot.RobotContainer;
import frc.robot.commands.drivetrainPercentPowerAuto;
import com.kauailabs.navx.frc.AHRS;

public class DrivetrainSubsystem extends SubsystemBase {

    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;
    DifferentialDrive robotDrive;
    AHRS ahrs;

    TrajectoryConfig trajectoryConfig;
    Trajectory trajectory; 

    CANSparkMax left_frontmotor;
    CANSparkMax left_backmotor;
    CANSparkMax right_frontmotor;
    CANSparkMax right_backmotor;

    DifferentialDriveOdometry odometry;

    SimpleMotorFeedforward feedForward;

    PIDController leftPIDController;
    PIDController rightPIDController;

    DifferentialDriveKinematics kinematics;

    PIDController turnController;

    /**
     * Creates a new ExampleSubsystem.
     */
    
    public DrivetrainSubsystem() {

        left_frontmotor = new CANSparkMax(Constants.driveFrontleftMotor, MotorType.kBrushless);
        left_backmotor = new CANSparkMax(Constants.driveBackleftMotor, MotorType.kBrushless);
        right_frontmotor = new CANSparkMax(Constants.driveFrontrightMotor, MotorType.kBrushless);
        right_backmotor = new CANSparkMax(Constants.driveBackrightMotor, MotorType.kBrushless);

        left_backmotor.setInverted(false);
        left_frontmotor.setInverted(false);
        right_backmotor.setInverted(false);
        right_frontmotor.setInverted(false);

        left_frontmotor.setIdleMode(IdleMode.kBrake);
        left_backmotor.setIdleMode(IdleMode.kBrake);
        right_backmotor.setIdleMode(IdleMode.kBrake);
        right_frontmotor.setIdleMode(IdleMode.kBrake);

        m_leftEncoder = left_frontmotor.getEncoder();
        m_rightEncoder = right_frontmotor.getEncoder();


        leftDrive = new SpeedControllerGroup(left_frontmotor, left_backmotor);
        rightDrive = new SpeedControllerGroup(right_frontmotor, right_backmotor);
        m_drive = new DifferentialDrive(leftDrive, rightDrive);
        robotDrive = new DifferentialDrive(leftDrive, rightDrive);

        ahrs = new AHRS(SPI.Port.kMXP);
    }

    @Override
    public void periodic() {
  
    }

    public void driveSetup(CANSparkMax leftFrontMotor, CANSparkMax leftBackMotor, CANSparkMax rightFrontMotor,
            CANSparkMax rightBackMotor) {
        

        // code added for pathfinder by Panten 3/6/2020
        // Sets the distance per pulse for the encoders
        // set scaling factor for CANEncoder.getPosition() so that it matches the output
        // of
        // Encoder.getDistance() method.

        //42 encoder ticks per motor shaft revolution - removed 3/11 by Berg - getPosition is motor revolutions - encoder doesn't factor into the calculation
        //Gear box reduction of 10.71
        //Wheels 8 inch diameter
        //Converted to meters
        //Result = pulses per meter.  Invert to make meters per pulse
        double conversionFactor = 1/( (Constants.kGearReduction/(8*Math.PI)) / 0.0254);  
        m_leftEncoder
                .setPositionConversionFactor(conversionFactor);
        m_rightEncoder
                .setPositionConversionFactor(conversionFactor);

        // Native scale is RPM. Scale velocity so that it is in meters/sec
        m_leftEncoder.setVelocityConversionFactor(
            conversionFactor / 60.0);
        m_rightEncoder.setVelocityConversionFactor(
            conversionFactor / 60.0);
        resetEncoders();
        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    }

    public void driveRobot(Double X, double Y) {

        robotDrive.arcadeDrive(-Y, X, true);
    }
    public void driveRobotLinear(double X, double Y) {
        robotDrive.arcadeDrive(-Y, X, false);
    }

    public void setBrakes()
    {
        /*left_frontmotor.setIdleMode(IdleMode.kBrake);
        left_backmotor.setIdleMode(IdleMode.kBrake);
        right_backmotor.setIdleMode(IdleMode.kBrake);
        right_frontmotor.setIdleMode(IdleMode.kBrake);*/
    }

    public void disableBrakes()
    {
        /*left_frontmotor.setIdleMode(IdleMode.kCoast);
        left_backmotor.setIdleMode(IdleMode.kCoast);
        right_backmotor.setIdleMode(IdleMode.kCoast);
        right_frontmotor.setIdleMode(IdleMode.kCoast);*/
    }

    public void driveBackwards() {

        leftDrive.set(.3);
        rightDrive.set(-.3);

    }

    public void driveForwards() {

        leftDrive.set(-.3);
        rightDrive.set(.3);
    }

    public void driveSetPercentAuto(double Power) {

        leftDrive.set(Power);
        rightDrive.set(-Power);

    }

    public void turnLeftSetPercentAuto(double Power) {

        leftDrive.set(Power);
        rightDrive.set(Power);

    }

    public void driveSetPercentForwards(double Power) {

        leftDrive.set(-Power);
        rightDrive.set(Power);

    }

    public void driveStop() {

        leftDrive.set(0);
        rightDrive.set(0);

    }

    public void tankDrive(double leftSpeed, double rightSpeed){

        tankDrive(leftSpeed, rightSpeed);

    }

    public void printPose() {

        System.out.println(getPose());

    }

    // Code added by Panten 3/6/2020

    // The robot's drive
    DifferentialDrive m_drive;

    // The left-side drive encoder
    CANEncoder m_leftEncoder;

    // The right-side drive encoder
    CANEncoder m_rightEncoder;

    // Odometry class for tracking robot pose
    DifferentialDriveOdometry m_odometry;

    /**
     * Creates a new DriveSubsystem.
     */
    /*
     * Moved to DrivetrainSubsystem by Panten 3/6/2020 public DriveSubsystem() { //
     * Sets the distance per pulse for the encoders
     * m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
     * m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
     * 
     * resetEncoders(); m_odometry = new
     * DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading())); }
     * 
     * 
     * @Override //Moved to periodic public void periodic() { // Update the odometry
     * in the periodic block m_odometry.update(Rotation2d.fromDegrees(getHeading()),
     * m_leftEncoder.getDistance(), m_rightEncoder.getDistance()); }
     */

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */

    /**
     * Drives the robot using arcade controls.
     *
     * @param fwd the commanded forward movement
     * @param rot the commanded rotation
     */
    public void arcadeDrive(double fwd, double rot) {
        m_drive.arcadeDrive(fwd, rot);
    }

    /**
     * Controls the left and right sides of the drive directly with voltages.
     *
     * @param leftVolts  the commanded left output
     * @param rightVolts the commanded right output
     */

    /**
     * Resets the drive encoders to currently read a position of 0.
     */
    public void resetEncoders() {
        m_leftEncoder.setPosition(0);
        m_rightEncoder.setPosition(0);
    }

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (m_leftEncoder.getPosition() + m_rightEncoder.getPosition()) / 2.0;
    }

    /**
     * Gets the left drive encoder.
     *
     * @return the left drive encoder
     */
    public double getLeftEncoder() {
        return m_leftEncoder.getPosition();
    }

    /**
     * Gets the right drive encoder.
     *
     * @return the right drive encoder
     */
    public double getRightEncoder() {
        return m_rightEncoder.getPosition();
    }

    public void driveStraight(double speed){
        m_drive.tankDrive(speed, speed);
    }

    public void setBrake() {

        /*left_frontmotor.setIdleMode(IdleMode.kBrake);
        left_backmotor.setIdleMode(IdleMode.kBrake);
        right_frontmotor.setIdleMode(IdleMode.kBrake);
        right_backmotor.setIdleMode(IdleMode.kBrake);*/

    }

    public void setCoast() {

        /*left_frontmotor.setIdleMode(IdleMode.kCoast);
        left_backmotor.setIdleMode(IdleMode.kCoast);
        right_frontmotor.setIdleMode(IdleMode.kCoast);
        right_backmotor.setIdleMode(IdleMode.kCoast);*/
    
      }
    

    /**
     * Sets the max output of the drive. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param maxOutput the maximum output to which the drive will be constrained
     */
   

    /**
     * Zeroes the heading of the robot.
     */
    public void zeroHeading() {
        ahrs.zeroYaw();
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from -180 to 180
     */
    public double getHeading() {
        // return Math.IEEEremainder(m_gyro.getAngle(), 360) * (Constants.kGyroReversed
        // ? -1.0 : 1.0);
        return Math.IEEEremainder(ahrs.getYaw(), 360) * (Constants.kGyroReversed ? -1.0 : 1.0);
        //return Math.IEEEremainder(ahrs.getFusedHeading() - 180, 360) * (Constants.kGyroReversed ? -1.0 : 1.0); //potential test in case Yaw isn't accurate enough
    }

    public double getContinuousAngle()
    {
        return ahrs.getAngle();
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return ahrs.getRate() * (Constants.kGyroReversed ? -1.0 : 1.0);
    }

    public double getNavxYaw(){
        return ahrs.getYaw();
      }
      public double getNavxRoll(){
        return ahrs.getRoll();
      }
      public double getNavxPitch(){
        return ahrs.getPitch();
      }
      public double getNavxAngle(){
        return ahrs.getAngle();
      }
      public double getNavxVelocityX(){
        return ahrs.getVelocityX();
      }
      public double getNavxVelocityY(){
        return ahrs.getVelocityY();
      }
      public double getNavxVelocityZ(){
        return ahrs.getVelocityZ();
      }
    
      public double getPIDOutput(){
        return turnController.calculate(ahrs.getAngle());
      }
      public boolean getPIDIsFinished(){
        return turnController.atSetpoint();
      }
      public void setPIDTarget(double setpoint){
        turnController.setSetpoint(setpoint);
      }
    
      //TRAJECTORY Functions
      
      public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {
        final double leftFeedforward = feedForward.calculate(speeds.leftMetersPerSecond);
        final double rightFeedforward = feedForward.calculate(speeds.rightMetersPerSecond);
    
        final double leftOutput = leftPIDController.calculate(m_rightEncoder.getVelocity(), speeds.leftMetersPerSecond);
        final double rightOutput = rightPIDController.calculate(m_rightEncoder.getVelocity(),
                speeds.rightMetersPerSecond);
        leftDrive.setVoltage(leftOutput + leftFeedforward);
        rightDrive.setVoltage(rightOutput + rightFeedforward);
    }

    public void drive(double xSpeed, double rot) {
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(new ChassisSpeeds(xSpeed, 0.0, rot));
        setSpeeds(wheelSpeeds);
    }

    public void updateOdometry() {
        odometry.update(ahrs.getRotation2d(), getEncoderMeters(m_leftEncoder), getEncoderMeters(m_rightEncoder));
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    public double getEncoderMeters(CANEncoder encoder){
        return encoder.getPosition() / Constants.encoderCountsPerInch;
      }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(m_leftEncoder.getVelocity(), m_rightEncoder.getVelocity());
      }
    
      //public void resetOdometry(Pose2d pose) {
      //  resetEncoders();
      //  odometry.resetPosition(pose, ahrs.getRotation2d());
      //}
    
      public void tankDriveVolts(double leftVolts, double rightVolts) {
        leftDrive.setVoltage(leftVolts);
        rightDrive.setVoltage(-rightVolts);
      }
      public void setMaxOutput(double maxOutput) {
        robotDrive.setMaxOutput(maxOutput);
      }

}