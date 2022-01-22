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

  private final DriveBase drives;
  private OperatorInterface oi;

  public DefaultDriveBaseCommand(DriveBase x) {
    drives = x;
    // Use requires() here to declare subsystem dependencies
    addRequirements(drives);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    drives.leftBackSpark.follow(drives.leftFrontSpark);
    drives.rightBackSpark.follow(drives.rightFrontSpark);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    // System.out.println("executing");
    // double left = -1.0 * this.oi.getLeftY();
    // double right = -1.0 * this.oi.getRightY();
    // if ((String.valueOf(right) != null) && (String.valueOf(left) != null)) {
    //   drives.setSpeed(-1.0 * this.oi.getLeftY(), -1.0 * this.oi.getRightY());

    // }
    // else {
    //   drives.setSpeed(0);
    // }
    // System.out.println("OI left Y:" + this.oi.getLeftY());
    // System.out.print("OI right Y:" + this.oi.getRightY());
    try{
      drives.setSpeed(-1.0 * this.oi.getLeftY(), -1.0 * this.oi.getRightY());
    }
    catch (NullPointerException e) {
      drives.setSpeed(0);

    }
    //this.drives.setSpeed(-0.3, 0.3);

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
