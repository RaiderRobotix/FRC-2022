package frc.robot.commands.Shooter;

import frc.robot.Constants;
import frc.robot.OperatorInterface;
import frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ToggleShooter extends CommandBase {

  private Shooter shooter;
  private boolean state;

  public ToggleShooter(boolean state) {
    this.shooter = Shooter.getInstance();
    this.state = state;
    addRequirements(shooter);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    if (this.state) {
        shooter.setShooterSpeed(0.75);
    } else {
        shooter.setShooterSpeed(0.0);
    }
    return true;
  }

  // Called once after isFinished returns true
  public void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }
}
