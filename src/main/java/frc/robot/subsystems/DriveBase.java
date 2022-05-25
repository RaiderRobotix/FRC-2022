package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveBase.DefaultDriveBaseCommand;

public class DriveBase extends SubsystemBase {
  
  private static DriveBase m_instance;

  public final VictorSP leftFrontTalon;
  private final VictorSP leftBackTalon;
  private final VictorSP rightFrontTalon;
  public final VictorSP rightBackTalon;

  private double leftDistance;
  private double rightDistance;

  private DriveBase() {

    this.leftFrontTalon = new VictorSP(Constants.LEFT_FRONT_DRIVE_CAN_ID);
    this.leftBackTalon = new VictorSP(Constants.LEFT_BACK_DRIVE_CAN_ID);
    this.rightFrontTalon = new VictorSP(Constants.RIGHT_FRONT_DRIVE_CAN_ID);
    this.rightBackTalon = new VictorSP(Constants.RIGHT_BACK_DRIVE_CAN_ID);

    this.leftFrontTalon.setInverted(Constants.LEFT_DRIVE_MOTORS_INVERTED);
    this.leftBackTalon.setInverted(Constants.LEFT_DRIVE_MOTORS_INVERTED);
    this.rightFrontTalon.setInverted(Constants.RIGHT_DRIVE_MOTORS_INVERTED);
    this.rightBackTalon.setInverted(Constants.RIGHT_DRIVE_MOTORS_INVERTED);
    // this.leftFrontTalon.setIdleMode(CANSparkMax.IdleMode.kBrake);
    // this.leftBackTalon.setIdleMode(CANSparkMax.IdleMode.kCoast);
    // this.rightBackTalon.setIdleMode(CANSparkMax.IdleMode.kCoast);
    // this.rightFrontTalon.setIdleMode(CANSparkMax.IdleMode.kBrake);

    // this.leftBackTalon.follow(leftFrontTalon);
    // this.rightBackTalon.follow(rightFrontTalon);

    // this.leftFrontTalon.configPeakCurrentLimit(80);
    // this.rightFrontTalon.configPeakCurrentLimit(80);
    // this.leftBackTalon.configPeakCurrentLimit(80);
    // this.rightBackTalon.configPeakCurrentLimit(80);
  }

  /**
   * @return The DriveBase singleton.
   */
  public static DriveBase getInstance() {
    System.out.println("x");
    if (m_instance == null) {
      m_instance = new DriveBase();
    }
    return m_instance;
  }

  public void setSpeed(double speed){
    //System.out.println("a");
    this.leftFrontTalon.set(speed);
    this.leftBackTalon.set(speed);
    this.rightBackTalon.set(speed);
    this.rightFrontTalon.set(speed);
  }

  public void setSpeed(double leftSpeed, double rightSpeed) {
    // System.out.println("Passed");
    // System.out.println(this.leftBackTalon);
    this.leftFrontTalon.set(leftSpeed);
    this.leftBackTalon.set(leftSpeed);
    this.rightBackTalon.set(rightSpeed);
    this.rightFrontTalon.set(rightSpeed);
  }
 
  public double getSpeed(int d) {
    switch (d) {
      case 1: return leftFrontTalon.get();
      case 2: return leftBackTalon.get();
      case 3: return rightBackTalon.get();
      case 4: return rightFrontTalon.get();
    }
    return 0.0;
    }

  public double getSpeed() {
    for (int i = 0; i < 4; i++) {
      if (getSpeed(i) != 0.0) {
        return getSpeed(i);
      }
    }
    return 0.0;
  }
  public boolean getMoving() {
    if (getSpeed() != 0.0){
      return true;
    } else {
      return false;
    }
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultDriveBaseCommand());
  }
}
