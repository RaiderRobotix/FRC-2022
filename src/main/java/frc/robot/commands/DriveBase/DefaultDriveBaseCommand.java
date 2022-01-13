/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OperatorInterface;
import frc.robot.subsystems.DriveBase;

public class DefaultDriveBaseCommand extends CommandBase {

  private DriveBase drives;
  private OperatorInterface oi;

  public DefaultDriveBaseCommand() {
    // Use requires() here to declare subsystem dependencies
    drives = DriveBase.getInstance();
    addRequirements(drives);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drives.setSpeed(-1.0 * oi.getLeftY(), -1.0 * oi.getRightY());

    if (oi.getRightButton(Constants.SENSOR_RESET_BUTTON)) {
      drives.resetGyro();
      drives.resetEncoders();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drives.setSpeed(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
