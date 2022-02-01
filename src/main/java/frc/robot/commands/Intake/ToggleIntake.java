package frc.robot.commands.Intake;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class ToggleIntake extends CommandBase{
    private Intake intake;
    private boolean inverted;

    //Roller on or off
    private boolean state;

    public ToggleIntake(boolean roller_state, boolean roller_inverted) {
        intake = Intake.getInstance();
        inverted = roller_inverted;
        state = roller_state;
        addRequirements(intake);
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
            intake.startRoller();
        } else {
            intake.stopRoller();
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
        end();
    }

}
