package frc.robot.commands.Climber;

import frc.robot.subsystems.Climber;
import frc.robot.Constants;
import frc.robot.OperatorInterface;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
       if(oi.getRightButton(6)){
            // System.out.println(climber.getLeftElevatorInverted());
            // System.out.println(climber.getRightElevatorInverted());
            // climber.setElevatorSpeed(oi.getOperatorY());
            climber.setElevatorSpeed(1.0);
        }
        else if(oi.getRightButton(7)){
            climber.setElevatorSpeed(-1.0);
        }
        else if(oi.getOperatorButton(Constants.OPERATOR_ARM_OVERRIDE)){
            System.out.println("entered arm");
            climber.setArmSpeed(0.50 * oi.getOperatorY());
        }
        /*if(oi.getOperatorButton(5)){
            climber.setGrabberSpeed(0.05);
            if(climber.getGrabberPositionLeft() == -1800 && climber.getGrabberPositionRight() == 1600){
                climber.setGrabberSpeed(0.0);
            }

        }
        if(oi.getOperatorButton(6)){
            climber.setGrabberSpeed(-0.05);
            if(climber.getGrabberPositionLeft() == -600 && climber.getGrabberPositionRight() == 2900){
                climber.setGrabberSpeed(0.0);
            }

        }*/
        else if(oi.getOperatorButton(Constants.OPERATOR_GRABBER_BUTTON_OPEN)){
            climber.setGrabberBrakeMode(false);
            climber.setGrabberSpeed(0.05 * oi.getOperatorY());
        }
        else if(oi.getOperatorButton(Constants.OPERATOR_GRABBER_BUTTON_CLOSE)){
            climber.setGrabberSpeed(0.0);
        }  
        else{
            climber.setElevatorSpeed(0.0);
        }
        //TODO fix how grabbers work
        // if(oi.getOperatorButton(Constants.OPERATOR_GRABBER_BUTTON)){
        //     climber.set();
        // }        

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
