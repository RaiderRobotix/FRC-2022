package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class Intake extends SubsystemBase {
	private static Intake m_instance;

	private final CANSparkMax rollerSpark;

	private boolean isRotating = false;  

	private Intake() {
		this.rollerSpark = new CANSparkMax(Constants.INTAKE_ROLLER_CAN_ID, MotorType.kBrushed);
        this.isRotating = false;
        
	}

	public static Intake getInstance() {
		if (m_instance == null) {
			m_instance = new Intake();
		}
		return m_instance;
	}

	public void startRoller(boolean inversed) {
        this.rollerSpark.setInverted(inversed);
		this.rollerSpark.set(0.5);
        this.isRotating = true;
	}

    public void stopRoller() {
        this.rollerSpark.set(0.0);
        this.isRotating = false;
    }
	
	public double getSpeed() {
        return rollerSpark.get();

	}

    public boolean isInverted() {
        return rollerSpark.getInverted();

	}

	public boolean isRotating(){
		return isRotating;
	}
}