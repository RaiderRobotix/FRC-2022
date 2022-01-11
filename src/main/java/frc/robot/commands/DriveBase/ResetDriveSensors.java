/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.subsystems.DriveBase;

/**
 * Add your docs here.
 */
public class ResetDriveSensors extends TimedCommand {
  
  private DriveBase drives;

  public ResetDriveSensors() {
    super(0.25);
    drives = DriveBase.getInstance();
    requires(drives);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drives.resetEncoders();
    drives.resetGyro();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Called once after timeout
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
