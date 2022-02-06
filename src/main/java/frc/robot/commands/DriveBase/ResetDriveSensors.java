/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveBase;

/**
 * Add your docs here.
 */

public class ResetDriveSensors extends CommandBase {
  
  private DriveBase drives;

  public ResetDriveSensors() {
    // TODO why was there a timeout here?
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
