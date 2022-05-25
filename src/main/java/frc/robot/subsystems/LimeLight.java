// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLight extends SubsystemBase {
    /** Creates a new ExampleSubsystem. */
    private static LimeLight m_instance;

    private static DriveBase drives;

    double tv;
    double tx;
    double ty;
    double ta;

    double distanceFromLimelightToGoalInches;

    double motorsGivenInstructions;

    private LimeLight() {
        this.tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0); // If Target is                                                                                        // Found
        this.tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0); // Horazontal       // Offset
        this.ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); // Vertical// Offset
        this.ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0); // Target Area
    }

    public static LimeLight getInstance() {
        System.out.println("x");
        if (m_instance == null) {
            m_instance = new LimeLight();
        }
        return m_instance;
    }


    @Override
    public void periodic() {
        Update_Tracking();
        if (tv == 1) {
            Find_Target();
            Align_Target();
            Move_To_Target();
        }
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
    
    public void teleopPeriodic() {
        
    }

    public void Update_Tracking() {
        this.tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0); // Target Found
        this.tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0); // Horazontal
        this.ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); // Vertical                                                                                    // Offset
        this.ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0); // Target Area
    }

    public void Find_Target() {
        if(tv == 0 && drives.getSpeed() != Constants.SENTRY_SPEED) {
            drives.setSpeed(Constants.SENTRY_SPEED);
        }
    }

    public void Align_Target() {
        if (tx > 4 && tx < -4 && tx != 0 
        && drives.getSpeed(Constants.LEFT_FRONT_DRIVE_CAN_ID) != -0.2 
        && drives.getSpeed(Constants.RIGHT_FRONT_DRIVE_CAN_ID) != 0.2) {
            System.out.println("Alligning With Target!!");
            drives.setSpeed(-0.2, 0.2);
        }
    }

    public void Move_To_Target() {    
        if (getDistance() > 30
        && drives.getSpeed() != 0.2) {
            drives.setSpeed(0.2, 0.2);
            System.out.println("Moving Forward");
        } else if (getDistance() < 30
        && drives.getSpeed() != -0.2) {
            drives.setSpeed(-0.2, -0.2);
            System.out.println("Moving Backward");
        } else if (getDistance() == 30) {
            drives.setSpeed(0, 0);
            System.out.println("Target Locked");
        }
    }

    public double getDistance() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry ty = table.getEntry("ty");
        double targetOffsetAngle_Vertical = ty.getDouble(0.0);
        // how many degrees back is your limelight rotated from perfectly vertical?
        double limelightMountAngleDegrees = 20.0;
        // distance from the center of the Limelight lens to the floor
        double limelightLensHeightInches = 13.5;
        // distance from the target to the floor
        double goalHeightInches = 44.4375;
        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
        // calculate distance
        double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)
                / Math.tan(angleToGoalRadians);
        System.out.println("Distance is " + distanceFromLimelightToGoalInches);

        return this.distanceFromLimelightToGoalInches;
    }

}
