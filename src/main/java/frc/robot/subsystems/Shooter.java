package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;


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

    private Shooter() {
        this.conveyorSpark = new Spark(Constants.CONVEYOR_PWM);
        this.conveyorSpark.setInverted(true);
        this.shooterTalon = new TalonSRX(Constants.SHOOTER_CAN_ID);    
    }

    public static Shooter getInstance() {
        if (m_instance == null) {
            m_instance = new Shooter();
        }
        return m_instance;
    }

    public void startConveyor() { this.conveyorSpark.set(1.0); }

    public void backConveyor() { this.conveyorSpark.set(-1.0); }

    public void stopConveyor() { this.conveyorSpark.set(0.0); }

    public void setShooterSpeed(double speed) { this.shooterTalon.set(ControlMode.PercentOutput, speed); }

    public void setShooterInverted(boolean state) { shooterTalon.setInverted(state); }

    public double getConveyorSpeed() { return conveyorSpark.get(); }

    public double getShooterSpeed() { return shooterTalon.getSelectedSensorVelocity(); }

    public boolean isConveyorInverted() { return conveyorSpark.getInverted(); }

    public boolean isShooterInverted() { return shooterTalon.getInverted();}

    public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DefaultShooterCommand());
	}
}