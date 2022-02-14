package frc.robot.commands.Climber;

import frc.robot.subsystems.Climber;
import frc.robot.Constants;
import frc.robot.OperatorInterface;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultClimberCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private Climber climber;
    private OperatorInterface oi;

    public DefaultClimberCommand() {
        climber = Climber.getInstance();
        oi = OperatorInterface.getInstance();
        addRequirements(climber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(oi.getOperatorButton(Constants.OPERATOR_ELEVATOR_OVERRIDE)){
            climber.setElevatorSpeed(oi.getOperatorY());
        }

        if(oi.getOperatorButton(Constants.OPERATOR_ARM_OVERRIDE)){
            climber.setArmSpeed(oi.getOperatorY());
        }
        //TODO fix how grabbers work
        if(oi.getOperatorButton(Constants.OPERATOR_GRABBER_BUTTON)){
            climber.stepGrabber();
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
