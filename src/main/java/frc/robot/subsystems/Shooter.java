package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.Constants;
import frc.robot.commands.Shooter.DefaultShooterCommand;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class Shooter extends SubsystemBase {
    private static Shooter m_instance;

    //Belt that brings balls up the shooter tower
    private final Spark conveyorSpark;
    //Fast-spinning motor that launches the balls
    private final TalonSRX shooterTalon;

    private boolean isShooterRotating;

    private Shooter() {
        //TODO determine if these motors need to be inverted
        this.conveyorSpark = new Spark(Constants.CONVEYOR_PWM);
        this.conveyorSpark.setInverted(true);
        //TODO identify Talon motor CAN ID and update in constants file
        this.shooterTalon = new TalonSRX(Constants.SHOOTER_CAN_ID);
        this.isShooterRotating = false;

    }

    public static Shooter getInstance() {
        if (m_instance == null) {
            m_instance = new Shooter();
        }
        return m_instance;
    }


    public void startConveyor() {
        //TODO determine appropriate conveyor speed
        this.conveyorSpark.set(1.0);

    }

    public void startConveyorInverted() {
        this.conveyorSpark.set(-1.0);
    }

    public void stopConveyor() {
        this.conveyorSpark.set(0.0);
    }

    public void setShooterSpeed(double speed) {
        //TODO determine what type of input speed should be
        this.shooterTalon.set(ControlMode.PercentOutput, speed);
        this.isShooterRotating = true;
    }

    public void setShooterInverted(boolean state) {
        shooterTalon.setInverted(state);

    }

    public double getConveyorSpeed() { return conveyorSpark.get(); }

    //TODO determine how to return shooter speed correctly
    public double getShooterSpeed() { return shooterTalon.getSelectedSensorVelocity(); }

    public boolean isConveyorInverted() { return conveyorSpark.getInverted(); }

    public boolean isShooterInverted() { return shooterTalon.getInverted();}

    public boolean isShooterRotating(){ return isShooterRotating;}

    public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DefaultShooterCommand());
	}
}