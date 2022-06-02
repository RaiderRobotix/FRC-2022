// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.LimeLight;
import frc.robot.OperatorInterface;

public class DefaultLimeLightCommand extends CommandBase {
  private final OperatorInterface oi;
  private final LimeLight ll;
  private final DriveBase drives;

  public DefaultLimeLightCommand() {
    oi = frc.robot.OperatorInterface.getInstance();
    ll = LimeLight.getInstance();
    drives = DriveBase.getInstance();
    addRequirements(ll);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ll.Update_Tracking();
    if (oi.getLeftButton(8)) {
      if (ll.targetFound()) {
        ll.Align_Target();
        System.out.println("Speed" + ll.leftSpeed + "   " + ll.rightSpeed);
        drives.setSpeed(ll.leftSpeed + .1, ll.rightSpeed + .1);
        if(ll.getDistance() <= 55) {
          drives.setSpeed(0.0);
        }
      } else {
        ll.Find_Target();
      }
    } else if (oi.getLeftY() == 0 && oi.getRightY() == 0) {
      drives.setSpeed(0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
