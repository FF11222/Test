// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.ElevatorMotorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
    private final DriveMotorSubsystem driveSubsystem = new DriveMotorSubsystem();
    private final ElevatorMotorSubsystem elevatorSubsystem = new ElevatorMotorSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final XboxController driverJoystick = new XboxController(GamepadJoystick.kDriverControllerPort);
    private final XboxController operatorJoystick = new XboxController(GamepadJoystick.kElevatorControllerPort);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      // Configure the trigger bindings
        driveSubsystem.setDefaultCommand(new DriveJoystickCmd(driveSubsystem
                , () -> -driverJoystick.getLeftY()
                , () -> -driverJoystick.getRightX()));

        configureBindings();
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
        new Trigger(m_exampleSubsystem::exampleCondition)
            .onTrue(new ExampleCommand(m_exampleSubsystem));

        if (operatorJoystick.getYButtonPressed()) {
            elevatorSubsystem.setDefaultCommand(new ElevatorJoystickCmd(elevatorSubsystem,Constants.OperatorConstants.speed));
        } else if (operatorJoystick.getAButtonPressed()) {
            elevatorSubsystem.setDefaultCommand(new ElevatorJoystickCmd(elevatorSubsystem, -Constants.OperatorConstants.speed));
        } else {
            elevatorSubsystem.setDefaultCommand(new ElevatorJoystickCmd(elevatorSubsystem, 0));
        }

        if (operatorJoystick.getBButtonPressed()) {
            intakeSubsystem.setDefaultCommand(new IntakeJoystickCmd(intakeSubsystem, Constants.OperatorConstants.speed));
        } else if (operatorJoystick.getXButtonPressed()) {
            intakeSubsystem.setDefaultCommand(new IntakeJoystickCmd(intakeSubsystem, -Constants.OperatorConstants.speed));
        } else {
            intakeSubsystem.setDefaultCommand(new IntakeJoystickCmd(intakeSubsystem, 0));
        }
    }
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return new ParallelRaceGroup(new AutoDriveCmd(driveSubsystem, 2));
    }

}
