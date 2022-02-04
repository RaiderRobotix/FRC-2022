package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.commands.Climber.DefaultClimberCommand;
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

public class Climber extends SubsystemBase {

    private static Climber m_instance;

    private final CANSparkMax leftElevatorMotor;
    private final CANSparkMax rightElevatorMotor;

    private final RelativeEncoder leftElevatorEncoder;
    private final RelativeEncoder rightElevatorEncoder;


    private double leftElevatorDistance;
    private double rightElevatorDistance;


    private Climber() {
        this.leftElevatorMotor = new CANSparkMax(Constants.LEFT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless);
        this.rightElevatorMotor = new CANSparkMax(Constants.LEFT_BACK_DRIVE_CAN_ID, MotorType.kBrushless);

        this.leftElevatorMotor.restoreFactoryDefaults();
        this.rightElevatorMotor.restoreFactoryDefaults();


        this.leftElevatorMotor.clearFaults();
        this.rightElevatorMotor.clearFaults();

        this.leftElevatorMotor.setInverted(Constants.LEFT_ELEVATOR_MOTOR_INVERTED);
        this.rightElevatorMotor.setInverted(Constants.RIGHT_ELEVATOR_MOTOR_INVERTED);

        this.leftElevatorMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        this.rightElevatorMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

        this.leftElevatorMotor.follow(rightElevatorMotor);

        this.leftElevatorMotor.setSmartCurrentLimit(80);
        this.rightElevatorMotor.setSmartCurrentLimit(80);

        this.leftElevatorEncoder = leftElevatorMotor.getEncoder();
        this.rightElevatorEncoder = rightElevatorMotor.getEncoder();

        this.leftElevatorDistance = this.getLeftElevatorDistance();
        this.rightElevatorDistance = this.getRightElevatorDistance();

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

    public void setElevatorSpeed(double leftSpeed, double rightSpeed) {
        this.leftElevatorMotor.set(leftSpeed);
        this.rightElevatorMotor.set(rightSpeed);
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

    public void resetEncoders() {
        this.leftElevatorDistance = this.getLeftElevatorEncoder();
        this.rightElevatorDistance = this.getRightElevatorDistance();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DefaultClimberCommand());
    }
}
