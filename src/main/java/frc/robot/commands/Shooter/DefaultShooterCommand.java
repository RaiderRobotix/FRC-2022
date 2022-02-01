package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OperatorInterface;
import frc.robot.subsystems.Shooter;

public class DefaultShooterCommand extends CommandBase {
    private Shooter shooter;
    private OperatorInterface oi;

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

        //TODO add buttons for shooting at different speeds
        if (oi.getOperatorButton(Constants.SHOOTER_START_BUTTON)) {
            //TODO determine acceptable speed
            shooter.setShooterSpeed(0.3);
            //TODO figure out how to wait for shooter to get up to speed
            while (shooter.getShooterSpeed() < 0.25) {

            }
            shooter.startConveyor();
            System.out.println("Turning shooter on, speed = " + shooter.getShooterSpeed());
        }

        if (oi.getOperatorButton(Constants.SHOOTER_STOP_BUTTON)) {
            shooter.stopConveyor();
            shooter.setShooterSpeed(0);
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

