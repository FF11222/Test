package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;


public class IntakeJoystickCmd extends CommandBase {
    private final IntakeSubsystem subsystem;
    private final double speed;

    public IntakeJoystickCmd(IntakeSubsystem subsystem, double speed) {
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

    }
}
