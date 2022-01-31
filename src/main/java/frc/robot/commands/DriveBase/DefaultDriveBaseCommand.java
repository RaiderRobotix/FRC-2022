/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OperatorInterface;
import frc.robot.subsystems.DriveBase;

public class DefaultDriveBaseCommand extends CommandBase {

  private final DriveBase drives;
  private final OperatorInterface oi;

  public DefaultDriveBaseCommand() {
    drives = DriveBase.getInstance();
    oi = OperatorInterface.getInstance();
    // Use requires() here to declare subsystem dependencies
    addRequirements(drives);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

    drives.setSpeed(-1.0 * oi.getLeftY(), -1.0 * oi.getRightY());

    // if (this.oi.getRightButton(Constants.SENSOR_RESET_BUTTON)) {
    //   drives.resetGyro();
    //   drives.resetEncoders();
    // }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
    drives.setSpeed(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}
