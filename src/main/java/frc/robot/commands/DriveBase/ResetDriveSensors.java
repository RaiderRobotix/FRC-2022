package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveBase;


public class ResetDriveSensors extends CommandBase {
  
  private DriveBase drives;

  public ResetDriveSensors() {
    drives = DriveBase.getInstance();
    addRequirements(drives);
  }

  // Called just before this Command runs the first time
  public void initialize() {
    drives.resetEncoders();
    drives.resetGyro();
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {

  }

  // Called once after timeout
  public void end() {

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    
  }
}
