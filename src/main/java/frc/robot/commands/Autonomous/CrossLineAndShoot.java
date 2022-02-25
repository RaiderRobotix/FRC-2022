package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveBase.*;
import frc.robot.commands.Intake.ToggleIntake;
import frc.robot.commands.Shooter.ToggleConveyor;
import frc.robot.commands.Shooter.ToggleShooter;


public class CrossLineAndShoot extends SequentialCommandGroup {

  public CrossLineAndShoot() {
    //Pickup ball and turn
    addCommands(new ToggleIntake(true));
    addCommands(new DriveStraight(95.0, 0.3));
    addCommands(new WaitCommand(0.5));
    addCommands(new ToggleIntake(false));
    addCommands(new Turn(170, 0.3));

    //Drive straight then shoot
    addCommands(new WaitCommand(0.3));
    addCommands(new DriveStraight(105.0, 0.3));
    addCommands(new Turn(-15, 0.3));
    // addCommands(new WaitCommand(1));
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
