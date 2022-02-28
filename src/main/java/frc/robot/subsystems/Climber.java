package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.commands.Climber.DefaultClimberCommand;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Climber extends SubsystemBase {

    private static Climber m_instance;

    private final Spark leftElevatorMotor;
    private final Spark rightElevatorMotor;
    private final Spark armMotor;

    private final Encoder elevatorEncoder;

    private final TalonSRX leftGrabberMotor;
    private final TalonSRX rightGrabberMotor;


    private Climber() {
        this.leftGrabberMotor = new TalonSRX(Constants.LEFT_GRABBER_CAN_ID);
        this.rightGrabberMotor = new TalonSRX(Constants.RIGHT_GRABBER_CAN_ID);

        this.leftElevatorMotor = new Spark(Constants.LEFT_ELEVATOR_MOTOR_PWM);
        this.rightElevatorMotor = new Spark(Constants.RIGHT_ELEVATOR_MOTOR_PWM);
        
        this.armMotor = new Spark(Constants.ARM_PWM);

        this.armMotor.setInverted(Constants.ARM_MOTOR_INVERTED);

        this.elevatorEncoder = new Encoder(8,7);

        leftGrabberMotor.setInverted(Constants.LEFT_GRABBER_MOTOR_INVERTED);
        rightGrabberMotor.setInverted(Constants.RIGHT_GRABBER_MOTOR_INVERTED);

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

    public boolean getRightElevatorInverted(){ return rightElevatorMotor.getInverted(); }

    public boolean getLeftElevatorInverted(){ return leftElevatorMotor.getInverted(); }

    public double getElevatorSpeed() { return (rightElevatorMotor.get()+leftElevatorMotor.get())/2; }

    public void setGrabberSpeed(double speed) {
        this.leftGrabberMotor.set(ControlMode.PercentOutput, speed);
        this.rightGrabberMotor.set(ControlMode.PercentOutput, speed);
    }

    public void setGrabberBrakeMode(boolean state){
        if (state == true){
            leftGrabberMotor.setNeutralMode(NeutralMode.Brake);
            rightGrabberMotor.setNeutralMode(NeutralMode.Brake);            
        } 
        else if (state == false) {
            leftGrabberMotor.setNeutralMode(NeutralMode.Coast);
            rightGrabberMotor.setNeutralMode(NeutralMode.Coast);
        }
    }

    public double getGrabberSpeed() { return leftGrabberMotor.getSelectedSensorPosition(); }

    public double getGrabberPositionLeft() { return leftGrabberMotor.getSelectedSensorPosition(); }
    
    public double getGrabberPositionRight() { return rightGrabberMotor.getSelectedSensorPosition(); }

    public double getAverageGrabberPosition() { return (rightGrabberMotor.getSelectedSensorPosition()+leftGrabberMotor.getSelectedSensorPosition())/2; }

    public double getHeight(){
        return elevatorEncoder.getDistance();
    }
    
    public void setArmSpeed(double speed) { this.armMotor.set(speed); }

    public double getArmSpeed() { return armMotor.get(); }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DefaultClimberCommand());
    }
}
