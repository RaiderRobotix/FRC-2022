package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.commands.Climber.DefaultClimberCommand;
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

public class Climber extends SubsystemBase {

    private static Climber m_instance;

    private final CANSparkMax leftElevatorMotor;
    private final CANSparkMax rightElevatorMotor;
    private final CANSparkMax armMotor;

    private final RelativeEncoder leftElevatorEncoder;
    private final RelativeEncoder rightElevatorEncoder;
    private final RelativeEncoder armEncoder;

    private double leftElevatorDistance;
    private double rightElevatorDistance;
    private double armDistance;

    private final TalonSRX leftGrabberMotor;
    private final TalonSRX rightGrabberMotor;


    private Climber() {
        this.leftGrabberMotor = new TalonSRX(Constants.LEFT_GRABBER_CAN_ID);
        this.rightGrabberMotor = new TalonSRX(Constants.RIGHT_GRABBER_CAN_ID);

        this.leftElevatorMotor = new CANSparkMax(Constants.LEFT_ELEVATOR_MOTOR_CAN_ID, MotorType.kBrushless);
        this.rightElevatorMotor = new CANSparkMax(Constants.RIGHT_ELEVATOR_MOTOR_CAN_ID, MotorType.kBrushless);
        this.armMotor = new CANSparkMax(Constants.ARM_CAN_ID, MotorType.kBrushless);

        this.leftElevatorMotor.restoreFactoryDefaults();
        this.rightElevatorMotor.restoreFactoryDefaults();
        this.armMotor.restoreFactoryDefaults();

        this.leftElevatorMotor.clearFaults();
        this.rightElevatorMotor.clearFaults();
        this.armMotor.clearFaults();

        this.leftElevatorMotor.setInverted(Constants.LEFT_ELEVATOR_MOTOR_INVERTED);
        this.rightElevatorMotor.setInverted(Constants.RIGHT_ELEVATOR_MOTOR_INVERTED);
        this.armMotor.setInverted(Constants.ARM_MOTOR_INVERTED);

        this.leftElevatorMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        this.rightElevatorMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        this.armMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

        this.leftElevatorMotor.follow(rightElevatorMotor);

        this.leftElevatorMotor.setSmartCurrentLimit(80);
        this.rightElevatorMotor.setSmartCurrentLimit(80);
        this.armMotor.setSmartCurrentLimit(80);

        this.leftElevatorEncoder = leftElevatorMotor.getEncoder();
        this.rightElevatorEncoder = rightElevatorMotor.getEncoder();
        this.armEncoder = armMotor.getEncoder();

        this.leftElevatorDistance = this.getLeftElevatorDistance();
        this.rightElevatorDistance = this.getRightElevatorDistance();
        this.armDistance = this.getArmDistance();

    }

    /**
     * @return The DriveBase singleton.
     */
    public static Climber getInstance() {
        if (m_instance == null) {
            m_instance = new Climber();
        }

        return m_instance;
    }

    public void setElevatorSpeed(double speed) {
        this.leftElevatorMotor.set(speed);
        this.rightElevatorMotor.set(speed);
    }

    public double getElevatorSpeed() {
        return rightElevatorMotor.get();
    }

    private double getLeftElevatorEncoder() {
        return (this.leftElevatorEncoder.getPosition() * Constants.INCHES_PER_REVOLUTION);
    }

    private double getRightElevatorEncoder() {
        return (this.rightElevatorEncoder.getPosition() * Constants.INCHES_PER_REVOLUTION);
    }

    public double getAverageElevatorDistance() {
        return (getLeftElevatorDistance() + getRightElevatorDistance()) / 2.0;
    }

    public double getLeftElevatorDistance() {
        return this.getLeftElevatorEncoder() - this.leftElevatorDistance;
    }

    public double getRightElevatorDistance() {
        return this.getRightElevatorEncoder() - this.rightElevatorDistance;
    }

    public void setGrabberSpeed(double speed) {
        this.leftGrabberMotor.set(ControlMode.PercentOutput, speed);
        this.rightGrabberMotor.set(ControlMode.PercentOutput, speed);
    }

    public void setGrabberInverted(boolean state) {
        leftGrabberMotor.setInverted(state);
        rightGrabberMotor.setInverted(state);
    }

    public double getGrabberSpeed() {
        return leftGrabberMotor.getSelectedSensorVelocity();
    }

    public void setArmSpeed(double speed) {
        this.armMotor.set(speed);
    }

    public double getArmSpeed() {
        return armMotor.get();
    }

    private double getArmEncoder() {
        return (this.armEncoder.getPosition() * Constants.INCHES_PER_REVOLUTION);
    }

    public double getArmDistance() {
        return this.getArmEncoder() - this.armDistance;
    }

    public void resetEncoders() {
        this.leftElevatorDistance = this.getLeftElevatorEncoder();
        this.rightElevatorDistance = this.getRightElevatorDistance();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DefaultClimberCommand());
    }
}
