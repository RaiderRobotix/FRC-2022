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

    public double tv;
    public double tx;
    public double ty;
    public double ta;
    public double leftSpeed;
    public double rightSpeed;

    double distanceFromLimelightToGoalInches;

    double motorsGivenInstructions;

    public LimeLight() {
        drives = DriveBase.getInstance();

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

    public void Update_Tracking() {
        this.tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0); // Target Found
        this.tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0); // Horazontal
        this.ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); // Vertical                                                                                    // Offset
        this.ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0); // Target Area
    }

    public void Find_Target() {
        System.out.println("Finding Target ");
        if (!targetFound()) {
            drives.setSpeed(Constants.SENTRY_SPEED, -Constants.SENTRY_SPEED);
        }
        if (targetFound()) {
            drives.setSpeed(0);
        }
    }

    public void Align_Target() {
        if (tx > 1){
            this.leftSpeed = .2;
            this.rightSpeed = 0;
        } else if (tx < -1) {
            this.leftSpeed = 0;
            this.rightSpeed = .2;
        }
    }

    public boolean targetFound(){
        if (tv == 1
        && tx <= Constants.HORIZONTAL_THRESHOLD
        && tx >= -Constants.HORIZONTAL_THRESHOLD) {
            System.out.println("Target Found!!");
            return true;
        }
        System.out.println("Target Not Found!!");
        return false;
    }

    public void Move_To_Target() {
        drives.setSpeed(0.2);
        if (getDistance() > 30) {
            drives.setSpeed(0.2, 0.2);
            System.out.println("Moving Forward");
        } else if (getDistance() < 30
                && drives.getSpeed() != -0.2) {
            drives.setSpeed(-0.2, -0.2);
            System.out.println("Moving Backward");
        } else if (getDistance() == 30) {
            // drives.setSpeed(0, 0);
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

        return distanceFromLimelightToGoalInches;
    }

}
