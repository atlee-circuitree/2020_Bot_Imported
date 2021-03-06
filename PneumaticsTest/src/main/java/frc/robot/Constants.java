// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    //Actual sparkmax encoder is 42
    public final static double encoderPPRMod = 42;
    //NEED TO DOUBLE CHECK MATH ON THIS
    public final static double encoderVelocityMod = 0.000745;

    //Updated characterization values for new test 4/23
    public static final double ksVolts = 0.214;
    public static final double kvVoltSecondsPerMeter = 2.77;
    public static final double kaVoltSecondsSquaredPerMeter = 0.22;
    
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;

    public static final double kPDriveVel = 0.02;

    public static final double trackWidthMeters = 0.53975;

    //About 22.3694 ROTATAIONS per meter (encoderppr of 1)
    public static final double encoderCountsPerMeter = (Constants.encoderPPRMod * 10.71) / (0.1524 * Math.PI);

    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(trackWidthMeters);
    
    public static final double kMaxAccelerationMetersPerSecondSquared = 0.5;
    public static final double kMaxSpeedMetersPerSecond = 0.5;
    
}
