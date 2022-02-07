package frc.robot.commands.Shooter;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class ToggleShooter extends CommandBase{
    private Shooter shooter;
    private boolean inverted;

    //Roller on or off
    private boolean state;

    public ToggleShooter(boolean shooter_state, boolean shooter_inverted) {
        shooter = Shooter.getInstance();
        inverted = shooter_inverted;
        state = shooter_state;
        addRequirements(shooter);
    }

    // Called just before this Command runs the first time
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run

    public void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    public void end() {
        if (this.state) {
            shooter.startConveyor();
            shooter.setShooterSpeed(0.75);
        } else {
            shooter.stopConveyor();
            shooter.setShooterSpeed(0);
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
        end();
    }

}
