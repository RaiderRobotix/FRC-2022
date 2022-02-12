/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveBase.*;
import frc.robot.commands.Intake.ToggleIntake;
import frc.robot.commands.Shooter.ToggleConveyor;
import frc.robot.commands.Shooter.ToggleShooter;



public class CrossLineAndShootDiagonal extends SequentialCommandGroup {
  /**
   * Add your docs here.
   */
  public CrossLineAndShootDiagonal() {
    //Pickup ball and turn
    addCommands(new ToggleIntake(true));
    addCommands(new DriveStraight(100.0, 0.6));
    addCommands(new ToggleIntake(false));
    addCommands(new WaitCommand(0.5));
    addCommands(new Turn(180, 0.6));

    //Drive straight then shoot
    addCommands(new WaitCommand(0.3));
    addCommands(new DriveStraight(110.0, 0.6));
    addCommands(new WaitCommand(1));
    addCommands(new Turn(-45, 0.6));
    // addCommands(new WaitCommand(0.3));
    addCommands(new ToggleShooter(true));
    addCommands(new WaitCommand(2));
    addCommands(new ToggleIntake(true));
    addCommands(new ToggleConveyor(true));
    addCommands(new WaitCommand(5));
    addCommands(new ToggleIntake(false));
    addCommands(new ToggleShooter(false));
    addCommands(new ToggleConveyor(false));

  }
}
