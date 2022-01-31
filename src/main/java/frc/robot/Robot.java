/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import frc.robot.commands.Autonomous.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Autonomous.CrossInitializationLine;
import frc.robot.commands.Autonomous.CrossLineAndShoot;
import frc.robot.commands.Autonomous.DoNothing;
import frc.robot.commands.DriveBase.DefaultDriveBaseCommand;
import frc.robot.commands.Intake.DefaultIntakeCommand;

import java.util.ArrayList;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  //private final Compressor compressor;

  private final DriveBase drives;

  //private final Vision vision;

  private final OperatorInterface oi;

  private final Intake intake;

  private SendableChooser<Command> autonomousChooser;
  private Command autonomousCommand;

  //private Command autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  Robot() {

    //this.compressor = new Compressor(Constants.PCM_CAN_ADDRESS, PneumaticsModuleType.CTREPCM);
    // this.compressor.setClosedLoopControl(1, true);

    this.drives = DriveBase.getInstance();
    this.oi = OperatorInterface.getInstance();
    this.intake = Intake.getInstance();

    CommandScheduler.getInstance().registerSubsystem(this.drives);
    this.drives.setDefaultCommand(new DefaultDriveBaseCommand());

    CommandScheduler.getInstance().registerSubsystem(this.intake);
    this.intake.setDefaultCommand(new DefaultIntakeCommand());

  }

  @Override
  public void robotInit() {

    autonomousChooser = new SendableChooser<Command>();
    autonomousChooser.setDefaultOption("Do Nothing", new DoNothing());
    autonomousChooser.addOption("Cross Initialization Line", new CrossInitializationLine());
    autonomousChooser.addOption("Cross Line and Shoot", new CrossLineAndShoot());
    
    SmartDashboard.putData("Autonomous mode chooser", autonomousChooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("LEncoder Distance", this.drives.getLeftDistance());
    SmartDashboard.putNumber("REncoder Distance", this.drives.getRightDistance());
    SmartDashboard.putNumber("Gyro Angle", this.drives.getGyroAngle());

    SmartDashboard.putNumber("LJoystick Y", this.oi.getLeftY());
    SmartDashboard.putNumber("RJoystick Y", this.oi.getRightY());

    SmartDashboard.putNumber("Right-Front Speed", this.drives.getSpeed(Constants.RIGHT_FRONT_DRIVE_CAN_ID));
    SmartDashboard.putNumber("Right-Back Speed", this.drives.getSpeed(Constants.RIGHT_BACK_DRIVE_CAN_ID));
    SmartDashboard.putNumber("Left-Front Speed", this.drives.getSpeed(Constants.LEFT_FRONT_DRIVE_CAN_ID));
    SmartDashboard.putNumber("Left-Back speed", this.drives.getSpeed(Constants.LEFT_BACK_DRIVE_CAN_ID));

    SmartDashboard.putBoolean("Intake Spinning?", this.intake.isRotating());
    SmartDashboard.putBoolean("Intake Inverted?", this.intake.isInverted());

  }

  @Override
  public void disabledPeriodic() {
    CommandScheduler.getInstance().run();

    ArrayList<String[]> subsystemCanIdFirmwarePairs = new ArrayList<>();
    subsystemCanIdFirmwarePairs.addAll(this.drives.getCanIdFirmwarePairs());
    
      for (String[] pair : subsystemCanIdFirmwarePairs) {
        SmartDashboard.putString(pair[0] + " ", pair[1]);
    }
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    super.autonomousInit();

    autonomousCommand = autonomousChooser.getSelected();
    autonomousCommand.initialize();;
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    CommandScheduler.getInstance().run();
    
    SmartDashboard.putNumber("Ultrasonic", this.drives.getUltrasonicDistance());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationPeriodic() {
    CommandScheduler.getInstance().run();
  }

}
