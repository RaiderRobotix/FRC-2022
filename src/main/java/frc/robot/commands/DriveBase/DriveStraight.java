/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;

public class DriveStraight extends CommandBase {

  private DriveBase drives;
  private boolean goingForward;
  private boolean isDone;
  private double targetDistance;
  private double startSpeed;

  public DriveStraight(double distance, double speed) {

    this.targetDistance = distance;
    this.startSpeed = Math.copySign(speed, targetDistance);

    goingForward = targetDistance > 0;

    drives = DriveBase.getInstance();
    addRequirements(drives);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    this.isDone = false;
    this.drives.resetEncoders();
    this.drives.resetGyro();
  }

  private double getDistanceTraveledSinceStart() {
    return drives.getLeftDistance();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    double leftSpeed = this.startSpeed;
    double rightSpeed = this.startSpeed;

    if ((goingForward && (getDistanceTraveledSinceStart() > this.targetDistance)
      || (!goingForward && (getDistanceTraveledSinceStart() < this.targetDistance))
      || (Math.abs(getDistanceTraveledSinceStart() - this.targetDistance) < Constants.DRIVE_STRAIGHT_DISTANCE_TOLERANCE))) {
      drives.setSpeed(0.0);
      isDone = true;
      return;
    } else if (Math.abs(drives.getGyroAngle()) 
      > Constants.DRIVE_STRAIGHT_ANGLE_TOLERANCE) {
      // Adjust speeds for in case of veering
      if (drives.getGyroAngle() > 0) { // Too far clockwise
        if (this.targetDistance > 0)
          leftSpeed -= Constants.DRIVE_SPEED_CORRECTION;
        else
          rightSpeed += Constants.DRIVE_SPEED_CORRECTION;
      } else { // Too far counterclockwise
        if (this.targetDistance > 0)
          rightSpeed -= Constants.DRIVE_SPEED_CORRECTION;
        else
          leftSpeed += Constants.DRIVE_SPEED_CORRECTION;
      }

      drives.setSpeed(leftSpeed, rightSpeed);
    } else {
      drives.setSpeed(leftSpeed, rightSpeed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return isDone;
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
