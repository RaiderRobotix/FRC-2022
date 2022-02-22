/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveBase.DriveStraight;

public class CrossInitializationLine extends SequentialCommandGroup {
  /**
   * Add your docs here.
   */
  public CrossInitializationLine() {
    addCommands(new DriveStraight(60.0, 0.5));
  }
}
