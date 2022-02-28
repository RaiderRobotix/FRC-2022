package frc.robot.commands.Climber;

import frc.robot.subsystems.Climber;
import frc.robot.Constants;
import frc.robot.OperatorInterface;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.simulation.AnalogInputSim;
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
        System.out.println("10 Turn: " + climber.tenTurnPot());



       if(oi.getOperatorButton(Constants.OPERATOR_ELEVATOR_BUTTON)){
            climber.setElevatorSpeed(1);
        }
        else if(oi.getOperatorButton(3)){
            if(climber.tenTurnPot() <= 0.31){
                climber.setElevatorSpeed(0.0);
            }
            if(climber.tenTurnPot() >= 0.7){
                climber.setElevatorSpeed(0.0);
            }
            climber.setElevatorSpeed(-1);
        }
        else if(oi.getOperatorButton(Constants.OPERATOR_ARM_BUTTON)){
            climber.setArmSpeed(0.50 * oi.getOperatorY());
            if(oi.getOperatorButton(5)){
                climber.setGrabberSpeed(0.15);
            } 
            else if(oi.getOperatorButton(6)){
                climber.setGrabberSpeed(-0.75);
            }  
        }
        else if(oi.getRightButton(3)){
            climber.setGrabberSpeed(0.15);
        } 
        else if(oi.getLeftButton(3)){
            climber.setGrabberSpeed(-0.8);
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
