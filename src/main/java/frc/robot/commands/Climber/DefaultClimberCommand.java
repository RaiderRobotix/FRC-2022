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
       if(oi.getOperatorButton(4)){
            climber.setElevatorSpeed(1);
        }
        else if(oi.getOperatorButton(3)){
            climber.setElevatorSpeed(-1);
        }
        else if(oi.getOperatorButton(Constants.OPERATOR_ARM_BUTTON)){
            climber.setArmSpeed(0.50 * oi.getOperatorY());
        }
        else if(oi.getOperatorButton(Constants.OPERATOR_GRABBER_BUTTON)){
            climber.setGrabberSpeed(0.3 * oi.getOperatorY());
        }  
        else{
            climber.setElevatorSpeed(0.0);
            climber.setArmSpeed(0.0);
            climber.setGrabberSpeed(0.0);
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
