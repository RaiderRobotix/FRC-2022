package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveStraightUltrasonic extends CommandBase {

  private final DriveBase drives;

  private final AnalogInput ultrasonic = new AnalogInput(2);


  public DriveStraightUltrasonic() {
    drives = DriveBase.getInstance();
    addRequirements(drives);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    this.drives.resetEncoders();
    this.drives.resetGyro();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    System.out.println("entered");
    // while(ultrasonic.getValue() > 100){
    // //drives.setSpeed(0.3, 0.3);
    // }
    // System.out.println("exited");
    // //drives.setSpeed(0, 0);
}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  protected void end() {
    drives.setSpeed(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}
