/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class NotifyDriver extends InstantCommand {
  
  private String message;
  
  public NotifyDriver(String description) {
    super();
    this.message = description;
  }

  // Called once when the command executes
  public void initialize() {
    SmartDashboard.putString("Sandstorm: ", this.message);
  }
}
