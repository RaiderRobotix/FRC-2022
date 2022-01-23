package frc.robot.commands.Intake;

import frc.robot.Constants;
import frc.robot.OperatorInterface;
import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultIntakeCommand extends CommandBase {

  private Intake intake;
  private OperatorInterface oi;

  public DefaultIntakeCommand() {
    intake = Intake.getInstance();
    oi = OperatorInterface.getInstance();

    addRequirements(intake);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

    if (oi.getOperatorButton(Constants.OPERATOR_ROLLER_BUTTON) && oi.getOperatorButton(Constants.OPERATOR_ROLLER_REVERSE_BUTTON)) {
        intake.startRoller(true);
    }
    if (oi.getOperatorButton(Constants.OPERATOR_ROLLER_BUTTON)) {
        intake.startRoller(false);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  public void end() {
    intake.stopRoller();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }
}