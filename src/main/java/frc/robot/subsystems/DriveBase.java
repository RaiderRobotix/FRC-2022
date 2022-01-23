package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.Constants;
import frc.robot.commands.DriveBase.DefaultDriveBaseCommand;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import java.util.ArrayList;

public class DriveBase extends SubsystemBase {
  
  private static DriveBase m_instance;

  public final CANSparkMax leftFrontSpark;
  public final CANSparkMax leftBackSpark;
  public final CANSparkMax rightFrontSpark;
  public final CANSparkMax rightBackSpark;

  private final RelativeEncoder leftEncoder;
  private final RelativeEncoder rightEncoder;

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

    this.leftFrontSpark.setInverted(Constants.LEFT_DRIVE_MOTORS_INVERTED);
    this.rightFrontSpark.setInverted(Constants.RIGHT_DRIVE_MOTORS_INVERTED);

    this.leftBackSpark.follow(leftFrontSpark,false);
    this.rightBackSpark.follow(rightFrontSpark, false);

    leftEncoder = leftFrontSpark.getEncoder();
    rightEncoder = rightFrontSpark.getEncoder();

    leftDistance = this.getLeftEncoder();
    rightDistance = this.getRightEncoder();

    ultrasonic = new AnalogInput(0);

    navX = new AHRS(Port.kUSB1);
    headingYaw = 0.0;

  }

  /**
   * @return The DriveBase singleton.
   */
  public static DriveBase getInstance() {
    System.out.println("made singleton class");
    if (m_instance == null) {
      m_instance = new DriveBase();
    }

    return m_instance;
  }

  public void setSpeed(double speed) {
    setSpeed(speed, speed);
  }

  public void setSpeed(double leftSpeed, double rightSpeed) {
    this.leftFrontSpark.set(leftSpeed);
    this.rightFrontSpark.set(rightSpeed);
  }

  private double getLeftEncoder() {
    return (this.leftEncoder.getPosition() * Constants.INCHES_PER_REVOLUTION);
  }

  private double getRightEncoder() {
    return (this.rightEncoder.getPosition() * Constants.INCHES_PER_REVOLUTION);
  }

  public double getAverageDistance() {
    return (getLeftDistance() + getRightDistance()) / 2.0;
  }

  public double getLeftDistance() {
    return this.getLeftEncoder() - this.leftDistance;
  }

  public double getRightDistance() {
    return this.getRightEncoder() - this.rightDistance;
  }

  public void resetEncoders() {
    this.leftDistance = this.getLeftEncoder();
    this.rightDistance = this.getRightEncoder();
  }

  public double getUltrasonicDistance() {
    return ultrasonic.getVoltage();
  }

  public double getGyroAngle() {
		return navX.getAngle() - this.headingYaw;
  }
  
  public void resetGyro() {
		headingYaw = navX.getAngle();
	}

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
