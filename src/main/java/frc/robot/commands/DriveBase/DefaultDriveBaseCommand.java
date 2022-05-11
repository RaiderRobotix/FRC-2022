package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
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
    System.out.println("Drivebase");

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    // System.out.println(oi.getLeftY());
    // System.out.println(oi.getRightY());

    drives.setSpeed(-1.0 * oi.getLeftY(), -1.0 * oi.getRightY());

    if (oi.getLeftButton(Constants.DRIVE_SENSOR_RESET_BUTTON)) {
      // drives.resetGyro();
    }

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
