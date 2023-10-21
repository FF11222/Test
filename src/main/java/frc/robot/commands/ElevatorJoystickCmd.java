package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorMotorSubsystem;


public class ElevatorJoystickCmd extends CommandBase {
    private final ElevatorMotorSubsystem subsystem;
    private final double speed;

    public ElevatorJoystickCmd(ElevatorMotorSubsystem subsystem, double speed) {

        this.subsystem = subsystem;
        this.speed = speed;

        addRequirements(subsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        subsystem.move(speed);
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.move(0.0);
    }
}
