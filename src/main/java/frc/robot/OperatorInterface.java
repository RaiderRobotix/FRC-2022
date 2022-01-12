/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.BallArm.*;
import frc.robot.commands.Elevator.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OperatorInterface {

  private static OperatorInterface m_instance;

  // Joysticks
  private final Joystick leftStick;
  private final Joystick rightStick;
  private final Joystick operatorStick;

  private final JoystickButton 
    operator3,
    operator5,
    // operator7,
    // operator9,
    operator10,
    // operator11,
    operator12,
    left6,
    right2,
    right3;


  private OperatorInterface() {

    this.leftStick = new Joystick(Constants.LEFT_JOYSTICK_PORT);
    this.rightStick = new Joystick(Constants.RIGHT_JOYSTICK_PORT);
    this.operatorStick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);
    
    left6 = new JoystickButton(leftStick, 6);

    right2 = new JoystickButton(rightStick, 2);
    right3 = new JoystickButton(rightStick, 3);

    operator3 = new JoystickButton(operatorStick, 3);
    operator5 = new JoystickButton(operatorStick, 5);
    // operator7 = new JoystickButton(operatorStick, 7);
    // operator9 = new JoystickButton(operatorStick, 9);
    operator10 = new JoystickButton(operatorStick, 10);
    // operator11 = new JoystickButton(operatorStick, 11);
    operator12 = new JoystickButton(operatorStick, 12);

    left6.whenPressed(new ShootBallSequence());

    right3.whenPressed(new TiltElevatorForward());
    right2.whenPressed(new TiltElevatorBack());
  
    // operator7.whenPressed(new RaiseElevatorToHeight(Constants.ELEVATOR_HIGH_PRESET));
    // operator9.whenPressed(new RaiseElevatorToHeight(Constants.ELEVATOR_MIDDLE_PRESET));
    // operator11.whenPressed(new RaiseElevatorToHeight(Constants.ELEVATOR_LOW_PRESET));
    
    operator3.whenPressed(new ContractBallArm());
    operator5.whenPressed(new ExtendBallArm());

    operator10.whenPressed(new TiltBallArmUp());
    operator12.whenPressed(new TiltBallArmDown());
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
    return this.operatorStick.getY();
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

  public boolean getOperatorTrigger() {
    return this.operatorStick.getTrigger();
  }
}
