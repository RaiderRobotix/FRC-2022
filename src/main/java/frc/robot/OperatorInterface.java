/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class OperatorInterface {

  private static OperatorInterface m_instance;
  //private final Intake intake;

  // Joysticks
  private final Joystick leftStick;
  private final Joystick rightStick;
  private final Joystick operatorStick;
  private final Joystick switchBox;

  private final JoystickButton operator1;
  private final JoystickButton operator2;
  private final JoystickButton operator3;
  private final JoystickButton operator4;
  private final JoystickButton operator5;
  private final JoystickButton operator6;
  private final JoystickButton operator7;
  private final JoystickButton operator8;
  private final JoystickButton operator9;
  private final JoystickButton operator10;
  private final JoystickButton operator11;
  private final JoystickButton operator12;




  private OperatorInterface() {

    this.leftStick = new Joystick(Constants.LEFT_JOYSTICK_PORT);
    this.rightStick = new Joystick(Constants.RIGHT_JOYSTICK_PORT);
    this.operatorStick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);
    this.switchBox = new Joystick(Constants.SWITCH_BOX_PORT);
    
    this.operator1 = new JoystickButton(operatorStick, 1);
    this.operator2 = new JoystickButton(operatorStick, 2);
    this.operator3 = new JoystickButton(operatorStick, 3);
    this.operator4 = new JoystickButton(operatorStick, 4);
    this.operator5 = new JoystickButton(operatorStick, 5);
    this.operator6 = new JoystickButton(operatorStick, 6);
    this.operator7 = new JoystickButton(operatorStick, 7);
    this.operator8 = new JoystickButton(operatorStick, 8);
    this.operator9 = new JoystickButton(operatorStick, 9);
    this.operator10 = new JoystickButton(operatorStick, 10);
    this.operator11 = new JoystickButton(operatorStick, 11);
    this.operator12 = new JoystickButton(operatorStick, 12);

   // operator11.whenPressed(new IntakeCommand());
   // operator11.whenPressed(new RaiseElevatorToHeight(Constants.ELEVATOR_LOW_PRESET));
  
  }

  /**
   * @return The OperatorInterface singleton.
   */
  public static OperatorInterface getInstance() {
    if (m_instance == null) {
      m_instance = new OperatorInterface();
    }

    return m_instance;
  }

  public double getLeftY() {
    double ret = leftStick.getY();
    if (Math.abs(ret) > Constants.JOYSTICK_DEADBAND) {
      return ret;
    }

    return 0.0;
  }

  public double getRightY() {
    double ret = rightStick.getY();
    if (Math.abs(ret) > Constants.JOYSTICK_DEADBAND) 
    {
      return ret;
    }

    return 0.0;
  }

  /**
   * Down on Joystick is positive, up is negative
   * @return
   */
  public double getOperatorY() {
    double ret = this.operatorStick.getY();
    if (Math.abs(ret) > Constants.OPERATOR_JOYSTICK_DEADBAND){
      return ret;
    }
    return 0.0;
  }

  public boolean getLeftButton(int button) {
    return this.leftStick.getRawButton(button);
  }

  public boolean getRightButton(int button) {
    return this.rightStick.getRawButton(button);
  }

  public boolean getOperatorButton(int button) {
    return this.operatorStick.getRawButton(button);
  }

  public boolean getSwitchBox(int button) {
    switch (button) {
      case 1:
        return switchBox.getRawButton(5);
      case 2:
        return switchBox.getRawButton(12);
      case 3:
        return switchBox.getRawButton(7);
      case 4:
        return switchBox.getRawButton(11);
      case 5:
        return switchBox.getRawButton(6);
      case 6:
        return switchBox.getRawButton(8);
      default:
        return false;
    }
  }

  public int getAutonChosen() {
    for (int i = 1; i <= 6; i++)
      if (getSwitchBox(i)){
        return i;
      }
    return 0;
  }

  public boolean getOperatorTrigger() {
    return this.operatorStick.getTrigger();
  }
}
