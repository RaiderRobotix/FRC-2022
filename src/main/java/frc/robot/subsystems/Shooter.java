package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class Shooter extends SubsystemBase {
    private static Shooter m_instance;

    //Belt that brings balls up the shooter tower
    private final CANSparkMax conveyorSpark;
    //Fast-spinning motor that launches the balls
    private final CANSparkMax shooterSpark;

    private boolean isShooterRotating;


    private Shooter() {
        this.conveyorSpark = new CANSparkMax(Constants.SHOOTER_CONVEYOR_CAN_ID, MotorType.kBrushed);
        this.shooterSpark = new CANSparkMax(Constants.SHOOTER_SHOOTER_CAN_ID, MotorType.kBrushed);
        //TODO determine if these motors need to be inverted

    }

    public static Shooter getInstance() {
        if (m_instance == null) {
            m_instance = new Shooter();
        }
        return m_instance;
    }


    public void toggleConveyor(boolean state, boolean inverted) {
        this.conveyorSpark.setInverted(inverted);
        //TODO determine appropriate conveyor speed
        if (state) {
            this.conveyorSpark.set(0.5);
        }
        else {
            this.conveyorSpark.set(0.0);
        }
    }

    public void setShooterSpeed(double speed) {
        this.shooterSpark.set(speed);
        this.isShooterRotating = true;
    }

    public double getConveyorSpeed() { return conveyorSpark.get(); }

    public double getShooterSpeed() { return shooterSpark.get(); }

    public boolean isConveyorInverted() { return conveyorSpark.getInverted(); }

    public boolean isShooterInverted() { return shooterSpark.getInverted();}

    public boolean isShooterRotating(){ return isShooterRotating;}
}