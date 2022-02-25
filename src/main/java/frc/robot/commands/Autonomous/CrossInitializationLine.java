package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveBase.DriveStraight;

public class CrossInitializationLine extends SequentialCommandGroup {

  public CrossInitializationLine() {
    addCommands(new DriveStraight(70.0, 0.5));
  }
}
