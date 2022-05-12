/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Autonomous.CrossInitializationLine;
import frc.robot.commands.Autonomous.DoNothing;
import frc.robot.commands.DriveBase.DefaultDriveBaseCommand;
//import frc.robot.commands.Autonomous.*;
import frc.robot.subsystems.DriveBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private final DriveBase drives;

  private final OperatorInterface oi; 


  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;
  
  int random_int;  

  // public final DigitalInput linebreaker = new DigitalInput(Constants.LINE_BREAKER_DIO);

  private SendableChooser<Command> autonomousChooser;
  private Command autonomousCommand;

  //private Command autonomousCommand;




  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  Robot() {

    this.drives = DriveBase.getInstance();
    this.oi = OperatorInterface.getInstance();

    CommandScheduler.getInstance().registerSubsystem(this.drives);
    this.drives.setDefaultCommand(new DefaultDriveBaseCommand());
  }

  @Override
  public void robotInit() {

    // autonomousChooser = new SendableChooser<Command>();
    // autonomousChooser.setDefaultOption("Cross Initialization Line", new CrossInitializationLine());
    // autonomousChooser.addOption("Cross Line and Shoot", new CrossLineAndShoot());
    // autonomousChooser.addOption("Cross Line and Shoot Diagonal", new CrossLineAndShootDiagonal());
    // autonomousChooser.addOption("Cross Line and Shoot Three Balls", new CrossLineAndShootThree());
    // SmartDashboard.putData("Autonomous mode chooser", autonomousChooser);

    
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

  }

  @Override
  public void disabledPeriodic() {
    CommandScheduler.getInstance().run();
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
    SmartDashboard.putString("Autonomous mode chosen", "None");
    super.autonomousInit();
    if(oi.getSwitchBox(1)){
      System.out.println("switch 1 is down");
      autonomousCommand = new DoNothing();
      SmartDashboard.putString("Autonomous mode chosen", "(Switch 1) Do Nothing");
    } 
    else if (oi.getSwitchBox(2)) {
      System.out.println("switch 2 is down");
      autonomousCommand = new CrossInitializationLine();
      SmartDashboard.putString("Autonomous mode chosen", "(Switch 2) Cross Initialization Line");
    } 
    else if (oi.getSwitchBox(3)) {
      System.out.println("switch 3 is down");
      SmartDashboard.putString("Autonomous mode chosen", "(Switch 3) Cross Line and Shoot");
    } 
    else if (oi.getSwitchBox(4)) {
      System.out.println("switch 4 is down");
      SmartDashboard.putString("Autonomous mode chosen", "(Switch 4) Cross Line and Shoot Diagonal");
    } 
    else if (oi.getSwitchBox(5)) {
      System.out.println("switch 5 is down");
      SmartDashboard.putString("Autonomous mode chosen", "(Switch 5) Cross Line and Shoot Three");
    } 
    else if (oi.getSwitchBox(6)){
      System.out.println("switch 6 is down");
      autonomousCommand = new DoNothing();
      SmartDashboard.putString("Autonomous mode chosen", "(Switch 6) Do Nothing");
    }
    else{
      System.out.println("default selected");
      autonomousCommand = new DoNothing();
    }
    autonomousCommand.schedule();
    
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
    
    Update_Limelight_Tracking();
    

    CommandScheduler.getInstance().run();

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

  public void Update_Limelight_Tracking()
  {

        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0); //If Target is Found
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0); // Horazontal Offset
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); // Vertical Offset
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0); //Target Area 

        while(tv == 0){
            drives.setSpeed(-0.2, 0.2);
            System.out.println("Searching for Target!!");
        }
        if (tv == 1){
          System.out.println("Target Found!!"); 
          
          while( tx > 4 && tx < -4 && tx != 0 ){
            System.out.println("Alligning With Target!!");
            drives.setSpeed(-0.2, 0.2);
          }
          System.out.println("Success Alligned With Target!! ");
          
          while(getDistance() != 30 ){
              while(getDistance() > 30){
                drives.setSpeed(0.2, 0.2);
                System.out.println("Moving Forward");
              }
              while(getDistance() < 30){
                drives.setSpeed(-0.2, -0.2);
                System.out.println("Moving Backward");
              }
          }
          System.out.println("Target Locked");


        }

        
  }

  public double getDistance(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);
    // how many degrees back is your limelight rotated from perfectly vertical?
    double limelightMountAngleDegrees = 20.0;
    // distance from the center of the Limelight lens to the floor
    double limelightLensHeightInches = 13.5;
    // distance from the target to the floor
    double goalHeightInches = 44.4375;
    double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
    // calculate distance
    double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)/ Math.tan(angleToGoalRadians);
    System.out.println("Distance is " + distanceFromLimelightToGoalInches);

    return distanceFromLimelightToGoalInches;
  }
}
