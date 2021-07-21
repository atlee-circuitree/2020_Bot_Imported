package frc.robot.commands;

import frc.robot.subsystems.ballObstructionSensorSubsystem;
import frc.robot.subsystems.shooterIntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooterMotorSubsystem;
 
 
public class runConveyorBySpeedMotorCommand extends CommandBase {

  shooterIntakeSubsystem m_subsystem;

  double speed;

  public runConveyorBySpeedMotorCommand(double targetSpeed, shooterIntakeSubsystem motorSubsystem) {
           
          super();
          
          speed = targetSpeed;

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
 
          m_subsystem.setConveyorSpeed(speed);  

        }
 
        // Called once the command ends or is interrupted.
        @Override
        public void end(boolean interrupted) {

          m_subsystem.setConveyorSpeed(0); 
      
      
        }
      
        // Returns true when the command should end.
        @Override
        public boolean isFinished() {
            return false;
        }
    }
