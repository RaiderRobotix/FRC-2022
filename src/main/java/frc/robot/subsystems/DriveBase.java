package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.commands.DriveBase.DefaultDriveBaseCommand;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import java.util.ArrayList;

public class DriveBase extends SubsystemBase {
  
  private static DriveBase m_instance;

  private final TalonSRX leftFrontTalon;
  private final TalonSRX leftBackTalon;
  private final TalonSRX rightFrontTalon;
  private final TalonSRX rightBackTalon;

  private double leftDistance;
  private double rightDistance;

  private DriveBase() {

    this.leftFrontTalon = new TalonSRX(Constants.LEFT_FRONT_DRIVE_CAN_ID);
    this.leftBackTalon = new TalonSRX(Constants.LEFT_BACK_DRIVE_CAN_ID);
    this.rightFrontTalon = new TalonSRX(Constants.RIGHT_FRONT_DRIVE_CAN_ID);
    this.rightBackTalon = new TalonSRX(Constants.RIGHT_BACK_DRIVE_CAN_ID);

    this.leftBackTalon.setInverted(Constants.LEFT_DRIVE_MOTORS_INVERTED);
    this.rightFrontTalon.setInverted(Constants.RIGHT_DRIVE_MOTORS_INVERTED);

    // this.leftFrontTalon.setIdleMode(CANSparkMax.IdleMode.kBrake);
    // this.leftBackTalon.setIdleMode(CANSparkMax.IdleMode.kCoast);
    // this.rightBackTalon.setIdleMode(CANSparkMax.IdleMode.kCoast);
    // this.rightFrontTalon.setIdleMode(CANSparkMax.IdleMode.kBrake);

    this.leftBackTalon.follow(leftFrontTalon);
    this.rightBackTalon.follow(rightFrontTalon);

    this.leftFrontTalon.configPeakCurrentLimit(80);
    this.rightFrontTalon.configPeakCurrentLimit(80);
    this.leftBackTalon.configPeakCurrentLimit(80);
    this.rightBackTalon.configPeakCurrentLimit(80);
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
    System.out.println("a");
    this.leftFrontTalon.set(ControlMode.PercentOutput, speed);
    this.rightFrontTalon.set(ControlMode.PercentOutput, speed);
  }

  public void setSpeed(double leftSpeed, double rightSpeed) {
    System.out.println("Passed");
    this.leftFrontTalon.set(ControlMode.PercentOutput,leftSpeed);
    this.rightFrontTalon.set(ControlMode.PercentOutput, rightSpeed);
  }
 
  public double getSpeed(int CANID) {
    // switch (CANID) {
      // case 1: return leftFrontTalon.get();
      // case 2: return leftBackTalon.get();
      // case 3: return rightBackTalon.get();
      // case 4: return rightFrontTalon.get();
    // }
    return 0.0;
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultDriveBaseCommand());
  }
}
