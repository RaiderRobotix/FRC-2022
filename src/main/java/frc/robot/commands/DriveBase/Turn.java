/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;

public class Turn extends Command {

  private final DriveBase drives;
  private boolean isDone;
  private boolean turningClockwise;
  double targetAngle;
  double speed;

  public Turn(double angle, double speed) {
    drives = DriveBase.getInstance();
    requires(drives);

    isDone = false;
    this.targetAngle = angle;
    this.speed = Math.copySign(speed, angle);
    this.turningClockwise = angle > 0;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isDone = false;
    drives.resetGyro();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if ((turningClockwise && drives.getGyroAngle() > targetAngle)
      || (!turningClockwise && drives.getGyroAngle() < targetAngle)
      || (Math.abs(drives.getGyroAngle() - targetAngle) < Constants.TURN_TOLERANCE)) {
      isDone = true;
      drives.setSpeed(0.0);
      return;
    } else {
      drives.setSpeed(speed, -speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isDone;
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
