/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveBase.DriveStraight;
import frc.robot.commands.DriveBase.Turn;
import frc.robot.commands.Intake.ToggleIntake;
import frc.robot.commands.Shooter.ToggleConveyor;
import frc.robot.commands.Shooter.ToggleShooter;

public class CrossLineAndShootThree extends SequentialCommandGroup {
    /**
     * Add your docs here.
     */
    public CrossLineAndShootThree() {
        //SHoots ball
        addCommands(new ToggleShooter(true));
        addCommands(new WaitCommand(2));   
        addCommands(new ToggleConveyor(true));
        addCommands(new ToggleIntake(true));
        addCommands(new WaitCommand(2));
        addCommands(new ToggleConveyor(false));
        addCommands(new ToggleShooter(false));
        
        //Turns and drives towards balls
        addCommands(new Turn(200, 0.3));
        addCommands(new DriveStraight(95.0, 0.3));    
        addCommands(new Turn(260, 0.3));
        addCommands(new DriveStraight(95.0, 0.3));
        addCommands(new Turn(-60, 0.3));
        addCommands(new DriveStraight(95.0, 0.3));
       
        addCommands(new ToggleIntake(false));
        addCommands(new ToggleShooter(true));

        //Goes back to hub
        addCommands(new DriveStraight(95.0, 0.6));
        addCommands(new Turn(25.0, .6));

        //Shoots balls
        addCommands(new ToggleConveyor(true));
        addCommands(new WaitCommand(0.5));
        addCommands(new ToggleShooter(false));
        addCommands(new ToggleConveyor(false));
    }
}
