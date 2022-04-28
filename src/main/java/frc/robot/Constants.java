package frc.robot;

public final class Constants {

  /**
   * CAN IDs & PWM ports.
   */
  public static final int LEFT_FRONT_DRIVE_CAN_ID = 7;
  public static final int LEFT_BACK_DRIVE_CAN_ID = 8;
  public static final int RIGHT_FRONT_DRIVE_CAN_ID = 1;
  public static final int RIGHT_BACK_DRIVE_CAN_ID = 0;

  //TODO Find the correct CAN ID values
  public static final int SHOOTER_CAN_ID = 6;
  public static final int LEFT_GRABBER_CAN_ID = 9;
  public static final int RIGHT_GRABBER_CAN_ID = 10;

  public static final int INTAKE_PWM = 0;
  public static final int CONVEYOR_PWM = 3;
  public static final int ARM_PWM = 4; 
  public static final int LEFT_ELEVATOR_MOTOR_PWM = 5;
  public static final int RIGHT_ELEVATOR_MOTOR_PWM = 6;


  public static final int LINE_BREAKER_DIO = 2;
  public static final int LIMIT_SWITCH_DIO = 7;

 
  /**
   * Climber
   */
  public static final boolean LEFT_ELEVATOR_MOTOR_INVERTED = false;
  public static final boolean RIGHT_ELEVATOR_MOTOR_INVERTED = true;
  public static final boolean LEFT_GRABBER_MOTOR_INVERTED = false;
  public static final boolean RIGHT_GRABBER_MOTOR_INVERTED = true;
  public static final boolean ARM_MOTOR_INVERTED = false;


  /**
   * OPERATOR INTERFACE.
   */
  public static final int LEFT_JOYSTICK_PORT = 1;
  public static final int RIGHT_JOYSTICK_PORT = 0;
  public static final int OPERATOR_JOYSTICK_PORT = 2;
  public static final int SWITCH_BOX_PORT = 3;

  public static final double JOYSTICK_DEADBAND = 0.15;
  public static final double OPERATOR_JOYSTICK_DEADBAND = 0.16;
  public static final int OPERATOR_REVERSE_BUTTON = 2;
  public static final int OPERATOR_ROLLER_BUTTON = 12;
  public static final int OPERATOR_CONVEYOR_BUTTON = 11;
  public static final int OPERATOR_SHOOTER_BUTTON = 1;
  public static final int OPERATOR_GRABBER_BUTTON = 10;
  public static final int OPERATOR_ELEVATOR_UP_BUTTON = 8;
  public static final int OPERATOR_ELEVATOR_DOWN_BUTTON = 3;
  public static final int OPERATOR_ARM_BUTTON = 7;
  public static final int RIGHT_SHOOTER_BUTTON = 11;

  public static final int DRIVE_SENSOR_RESET_BUTTON = 7;

  public static final double UPPER_LIMIT = 0.1525;
  public static final double UPPER_LIMIT_2 = 0.23;

  public static final double LOWER_LIMIT = 0.315;





  /**
   * DRIVEBASE.
   */
  public static final boolean RIGHT_DRIVE_MOTORS_INVERTED = true;
  public static final boolean LEFT_DRIVE_MOTORS_INVERTED = false;
  
  // Auto-Driving Constants (in degrees)
  public static final double TURN_TOLERANCE = 1.0;
  public static final double DRIVE_STRAIGHT_ANGLE_TOLERANCE = 1.0;
  public static final double DRIVE_SPEED_CORRECTION = 0.10;
  public static final double DRIVE_STRAIGHT_DISTANCE_TOLERANCE = 1.0;
  public static final double SLOW_SPEED_WEAK = 0.12;
  public static final double SLOW_SPEED_STRONG = 0.18;
  public static final double DRIVE_STRAIGHT_SLOW_RANGE = 12.0;

  // Encoder distance calculations
  private static final double TIRE_CIRCUMFERENCE = 28.375;
  private static final double COUNTS_PER_REVOLUTION = 128;

  // (Gear ratio: 16 down to (42 : 18 transmission) to 144 wheel (down))
  private static final double GEAR_RATIO = 0.047619; //0.0714286;
  public static final double INCHES_PER_REVOLUTION = GEAR_RATIO * TIRE_CIRCUMFERENCE;
  //public static final double INCHES_PER_COUNT = INCHES_PER_REVOLUTION / COUNTS_PER_REVOLUTION;
  public static final double ELEVATOR_INCHES_PER_REVOLUTION = 29.125;
   public static final double ELEVATOR_INCHES_PER_COUNT = ELEVATOR_INCHES_PER_REVOLUTION / COUNTS_PER_REVOLUTION;

  /**
   * ELEVATOR.
   */



}
