package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;

public class Turn extends CommandBase {

  private final DriveBase drives;
  private boolean isDone;
  private boolean turningClockwise;
  double targetAngle;
  double speed;

  public Turn(double angle, double speed) {
    drives = DriveBase.getInstance();
    addRequirements(drives);

    isDone = false;
    this.targetAngle = angle;
    this.speed = Math.copySign(speed, angle);
    this.turningClockwise = angle > 0;
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    isDone = false;
    drives.resetGyro();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
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