// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.DriveBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLight extends SubsystemBase {
    /** Creates a new ExampleSubsystem. */
    private static LimeLight m_instance;

    private final DriveBase drives;

    double tv;
    double tx;
    double ty;
    double ta;

    public LimeLight() {
        tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0); // If Target is                                                                                        // Found
        tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0); // Horazontal       // Offset
        ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); // Vertical// Offset
        ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0); // Target Area
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
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
    
    public void teleopPeriodic() {
        
    }

    public void Update_Limelight_Tracking() {

        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0); // If Target is
                                                                                                         // Found
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0); // Horazontal
                                                                                                         // Offset
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); // Vertical
                                                                                                         // Offset
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0); // Target Area

        while (tv == 0) {
            drives.setSpeed(-0.2, 0.2);
            System.out.println("Searching for Target!!");
            if (tv == 1) {
                drives.setSpeed(0);
                break;

            }
        }

        if (tv == 1) {

            System.out.println("Target Found!!");

            while (tx > 4 && tx < -4 && tx != 0) {
                System.out.println("Alligning With Target!!");
                drives.setSpeed(-0.2, 0.2);
            }
            System.out.println("Success Alligned With Target!! ");

            while (getDistance() != 30) {
                while (getDistance() > 30) {
                    drives.setSpeed(0.2, 0.2);
                    System.out.println("Moving Forward");
                }
                while (getDistance() < 30) {
                    drives.setSpeed(-0.2, -0.2);
                    System.out.println("Moving Backward");
                }
            }

            if (getDistance() == 30) {
                drives.setSpeed(0, 0);
                System.out.println("Target Locked");

            }

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

        return distanceFromLimelightToGoalInches;
    }

}
