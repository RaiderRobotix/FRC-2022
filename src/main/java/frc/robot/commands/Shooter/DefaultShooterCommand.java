package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.OperatorInterface;
import frc.robot.subsystems.Shooter;

public class DefaultShooterCommand extends CommandBase {
    private Shooter shooter;
    private OperatorInterface oi;

    private final DigitalInput lineBreaker = new DigitalInput(Constants.LINE_BREAKER_DIO);

    public DefaultShooterCommand() {
        shooter = Shooter.getInstance();
        oi = OperatorInterface.getInstance();
        addRequirements(shooter);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {

        if (oi.getOperatorButton(Constants.OPERATOR_SHOOTER_BUTTON) && oi.getOperatorButton(Constants.OPERATOR_REVERSE_BUTTON)) {
            shooter.setShooterSpeed(-0.7);
        }
        else if (oi.getOperatorButton(Constants.OPERATOR_SHOOTER_BUTTON)) {
            shooter.setShooterSpeed(0.7);
        }
        else {
            shooter.setShooterSpeed(0.0);
        }

        if (oi.getOperatorButton(Constants.OPERATOR_CONVEYOR_BUTTON) && oi.getOperatorButton(Constants.OPERATOR_REVERSE_BUTTON)) {
            shooter.backConveyor();
        }
        else if (oi.getOperatorButton(Constants.OPERATOR_CONVEYOR_BUTTON)) {
            shooter.startConveyor();
        }
        else {
            shooter.stopConveyor();
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    public void end() {
        shooter.setShooterSpeed(0);
        shooter.stopConveyor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
        end();
    }
}

