/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveBase.DriveStraight;
import frc.robot.commands.DriveBase.Turn;
import frc.robot.commands.Intake.ToggleIntake;


public class CrossLineAndShoot extends SequentialCommandGroup {
  /**
   * Add your docs here.
   */
  public CrossLineAndShoot() {
    addCommands(new ToggleIntake(true, true));
    addCommands(new DriveStraight(60.0, 0.5));
    addCommands(new ToggleIntake(false, true));
    addCommands(new Turn(180, 0.5));
    addCommands(new DriveStraight(30.0, 0.5));

  }
}
