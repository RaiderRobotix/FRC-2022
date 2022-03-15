package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class NotifyDriver extends InstantCommand {
  
  private String message;
  
  public NotifyDriver(String description) {
    super();
    this.message = description;
  }

  // Called once when the command executes
  public void initialize() {
  }
}
