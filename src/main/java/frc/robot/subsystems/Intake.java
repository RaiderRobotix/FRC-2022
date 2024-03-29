package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.commands.Intake.DefaultIntakeCommand;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class Intake extends SubsystemBase {
	private static Intake m_instance;

	private final Spark rollerSpark;

	private Intake() {
		this.rollerSpark = new Spark(Constants.INTAKE_PWM);
	}

	public static Intake getInstance() {
		if (m_instance == null) {
			m_instance = new Intake();
		}
		return m_instance;
	}

	public void startRoller() { this.rollerSpark.set(1.0); }

    public void stopRoller() { this.rollerSpark.set(0.0); }
	
	public double getSpeed() { return rollerSpark.get(); }

	public void setInverted(boolean state) { rollerSpark.setInverted(state); }

    public boolean isInverted() { return rollerSpark.getInverted(); }

	public void startRollerInverted() { rollerSpark.set(-1.0); }

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DefaultIntakeCommand());
	}
}