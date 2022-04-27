package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.commands.DriveBase.DefaultDriveBaseCommand;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import java.util.ArrayList;

public class DriveBase extends SubsystemBase {
  
  private static DriveBase m_instance;

  private final CANSparkMax leftFrontSpark;
  private final CANSparkMax leftBackSpark;
  private final CANSparkMax rightFrontSpark;
  private final CANSparkMax rightBackSpark;

  private final RelativeEncoder leftEncoder;
  private final RelativeEncoder rightEncoder;

  private final PowerDistribution examplePD = new PowerDistribution(5, ModuleType.kRev);

  private double leftDistance;
  private double rightDistance;

  private final AnalogInput ultrasonic;

  private final AHRS navX;
  private double headingYaw;

  private DriveBase() {
    this.leftFrontSpark = new CANSparkMax(Constants.LEFT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless);
    this.leftBackSpark = new CANSparkMax(Constants.LEFT_BACK_DRIVE_CAN_ID, MotorType.kBrushless);
    this.rightFrontSpark = new CANSparkMax(Constants.RIGHT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless);
    this.rightBackSpark = new CANSparkMax(Constants.RIGHT_BACK_DRIVE_CAN_ID, MotorType.kBrushless);

    this.leftBackSpark.restoreFactoryDefaults();
    this.leftFrontSpark.restoreFactoryDefaults();
    this.rightBackSpark.restoreFactoryDefaults();
    this.rightFrontSpark.restoreFactoryDefaults();

    this.leftBackSpark.clearFaults();
    this.rightBackSpark.clearFaults();
    this.leftFrontSpark.clearFaults();
    this.rightFrontSpark.clearFaults();
    examplePD.clearStickyFaults();

    this.leftBackSpark.setInverted(Constants.LEFT_DRIVE_MOTORS_INVERTED);
    this.rightFrontSpark.setInverted(Constants.RIGHT_DRIVE_MOTORS_INVERTED);

    this.leftFrontSpark.setIdleMode(CANSparkMax.IdleMode.kBrake);
    this.leftBackSpark.setIdleMode(CANSparkMax.IdleMode.kCoast);
    this.rightBackSpark.setIdleMode(CANSparkMax.IdleMode.kCoast);
    this.rightFrontSpark.setIdleMode(CANSparkMax.IdleMode.kBrake);

    this.leftBackSpark.follow(leftFrontSpark);
    this.rightBackSpark.follow(rightFrontSpark);

    this.leftFrontSpark.setSmartCurrentLimit(80);
    this.rightFrontSpark.setSmartCurrentLimit(80);
    this.leftBackSpark.setSmartCurrentLimit(80);
    this.rightBackSpark.setSmartCurrentLimit(80);

    this.leftEncoder = leftFrontSpark.getEncoder();
    this.rightEncoder = rightFrontSpark.getEncoder();

    this.leftDistance = this.getLeftEncoder();
    this.rightDistance = this.getRightEncoder();

    ultrasonic = new AnalogInput(0);

    navX = new AHRS(Port.kUSB1);
    headingYaw = 0.0;
  }

  /**
   * @return The DriveBase singleton.
   */
  public static DriveBase getInstance() {
    if (m_instance == null) {
      m_instance = new DriveBase();
    }
    return m_instance;
  }

  public void setSpeed(double speed) { setSpeed(speed, speed); }

  public void   setSpeed(double leftSpeed, double rightSpeed) {
    this.leftFrontSpark.set(leftSpeed);
    this.rightFrontSpark.set(rightSpeed);
  }
 
  public double getSpeed(int CANID) {
    switch (CANID) {
      case 1: return leftFrontSpark.get();
      case 2: return leftBackSpark.get();
      case 3: return rightBackSpark.get();
      case 4: return rightFrontSpark.get();
    }
    return 0.0;
  }

  public double getTemp(int CANID) {
    switch (CANID) {
      case 1: return leftFrontSpark.getMotorTemperature();
      case 2: return leftBackSpark.getMotorTemperature();
      case 3: return rightBackSpark.getMotorTemperature();
      case 4: return rightFrontSpark.getMotorTemperature();
    }
    return 0.0;
  }

  private double getLeftEncoder() { return (this.leftEncoder.getPosition() * Constants.INCHES_PER_REVOLUTION); }

  private double getRightEncoder() { return (this.rightEncoder.getPosition() * Constants.INCHES_PER_REVOLUTION); }

  public double getAverageDistance() { return (getLeftDistance() + getRightDistance()) / 2.0; }

  public double getLeftDistance() { return this.getLeftEncoder() - this.leftDistance; }

  public double getRightDistance() { return this.getRightEncoder() - this.rightDistance; }

  public void resetEncoders() {
    this.leftDistance = this.getLeftEncoder();
    this.rightDistance = this.getRightEncoder();
  }

  public double getUltrasonicDistance() { return ultrasonic.getVoltage(); }

  public double getGyroAngle() { return navX.getAngle() - this.headingYaw; }
  
  public void resetGyro() { headingYaw = navX.getAngle(); }

  public ArrayList<String[]> getCanIdFirmwarePairs() {
    ArrayList<String[]> pairs = new ArrayList<String[]>();
    pairs.add(new String[]{"Drive CAN ID Left Front " + Constants.LEFT_FRONT_DRIVE_CAN_ID, this.leftFrontSpark.getFirmwareString()});
    pairs.add(new String[]{"Drive CAN ID Left Back  " + Constants.LEFT_BACK_DRIVE_CAN_ID, this.leftBackSpark.getFirmwareString()});
    pairs.add(new String[]{"Drive CAN ID Right Front" + Constants.RIGHT_FRONT_DRIVE_CAN_ID, this.rightFrontSpark.getFirmwareString()});
    pairs.add(new String[]{"Drive CAN ID Right Back " + Constants.RIGHT_BACK_DRIVE_CAN_ID, this.rightBackSpark.getFirmwareString()});
    return pairs;
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultDriveBaseCommand());
  }
}
