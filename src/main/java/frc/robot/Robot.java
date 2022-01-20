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

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private final Compressor compressor;

  private final DriveBase drives;

  //private final Vision vision;

  private final OperatorInterface oi;

  private SendableChooser<Command> autonomousChooser;
  //private Command autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  Robot() {

    this.compressor = new Compressor(Constants.PCM_CAN_ADDRESS, PneumaticsModuleType.CTREPCM);
    // this.compressor.setClosedLoopControl(1, true);

    this.drives = DriveBase.getInstance();
    this.oi = OperatorInterface.getInstance();
  }

  @Override
  public void robotInit() {
    compressor.enableDigital();

    autonomousChooser = new SendableChooser<Command>();
    
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
    SmartDashboard.putNumber("Left Encoder", this.drives.getLeftDistance());
    SmartDashboard.putNumber("Right Encoder", this.drives.getRightDistance());
    SmartDashboard.putNumber("Gyro Angle", this.drives.getGyroAngle());

    SmartDashboard.putNumber("Left Joystick Y", this.oi.getLeftY());
    SmartDashboard.putNumber("Right Joystick Y", this.oi.getRightY());

		SmartDashboard.putBoolean("Left-back following?", this.drives.leftBackSpark.isFollower());
		SmartDashboard.putBoolean("Left-front following?", this.drives.leftFrontSpark.isFollower());
		SmartDashboard.putBoolean("Right-back following?", this.drives.rightBackSpark.isFollower());
		SmartDashboard.putBoolean("Right-front following?", this.drives.rightFrontSpark.isFollower());

		SmartDashboard.putNumber("Right-front set speed", this.drives.rightFrontSpark.get());
		SmartDashboard.putNumber("Right-back set speed", this.drives.rightBackSpark.get());
		SmartDashboard.putNumber("Left-front set speed", this.drives.leftFrontSpark.get());
		SmartDashboard.putNumber("Left-back set speed", this.drives.leftBackSpark.get());

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

}
