package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.commands.Climber.DefaultClimberCommand;
import frc.robot.commands.DriveBase.DefaultDriveBaseCommand;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import java.util.ArrayList;

public class Climber extends SubsystemBase {

    private static Climber m_instance;

    private final Spark leftElevatorMotor;
    private final Spark rightElevatorMotor;
    private final Spark armMotor;

    private double leftElevatorDistance;
    private double rightElevatorDistance;

    private final TalonSRX leftGrabberMotor;
    private final TalonSRX rightGrabberMotor;


    private Climber() {
        this.leftGrabberMotor = new TalonSRX(Constants.LEFT_GRABBER_CAN_ID);
        this.rightGrabberMotor = new TalonSRX(Constants.RIGHT_GRABBER_CAN_ID);
// CHANGE TO PWM PORTS
        this.leftElevatorMotor = new Spark(Constants.LEFT_ELEVATOR_MOTOR_CAN_ID);
        this.rightElevatorMotor = new Spark(Constants.RIGHT_ELEVATOR_MOTOR_CAN_ID);
        
        this.armMotor = new Spark(Constants.ARM_PWM);

        // this.leftElevatorMotor.setInverted(Constants.LEFT_ELEVATOR_MOTOR_INVERTED);
        // this.rightElevatorMotor.setInverted(Constants.RIGHT_ELEVATOR_MOTOR_INVERTED);
        this.armMotor.setInverted(Constants.ARM_MOTOR_INVERTED);

        this.setGrabberInverted(false, true);
        this.setGrabberBrakeMode(true);

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
        this.rightElevatorMotor.set(-speed);
    }

    public boolean getRightElevatorInverted(){
        return rightElevatorMotor.getInverted();
    }

    public boolean getLeftElevatorInverted(){
        return leftElevatorMotor.getInverted();
    }

    public double getElevatorSpeed() {
        return rightElevatorMotor.get();
    }

    public void setGrabberSpeed(double speed) {
        this.leftGrabberMotor.set(ControlMode.PercentOutput, speed);
        this.rightGrabberMotor.set(ControlMode.PercentOutput, -speed);
    }

    public void setGrabberInverted(boolean leftState, boolean rightState) {
        leftGrabberMotor.setInverted(leftState);
        rightGrabberMotor.setInverted(rightState);
    }
    public void setGrabberBrakeMode(boolean state){
        if (state == true){
            leftGrabberMotor.setNeutralMode(NeutralMode.Brake);
            rightGrabberMotor.setNeutralMode(NeutralMode.Brake);            

        } else if (state == false) {
            leftGrabberMotor.setNeutralMode(NeutralMode.Coast);
            rightGrabberMotor.setNeutralMode(NeutralMode.Coast);
        }
    }

    public void stepGrabber() {
        setGrabberSpeed(0.05);
    }

    public double getGrabberSpeed() {
        return leftGrabberMotor.getSelectedSensorPosition();
    }

    public double getGrabberPositionLeft() {
        return leftGrabberMotor.getSelectedSensorPosition();
    }
    public double getGrabberPositionRight() {
        return rightGrabberMotor.getSelectedSensorPosition();
    }

    public void setArmSpeed(double speed) {
        this.armMotor.set(speed);
        
    }

    public double getArmSpeed() {
        return armMotor.get();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DefaultClimberCommand());
    }
}
