package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveMotorSubsystem;


public class AutoDriveCmd extends CommandBase {
    private final DriveMotorSubsystem motorSubsystem;
    private static final Timer timer = new Timer();
    private final PIDController controller = new PIDController(Constants.AutoConstants.kP, Constants.AutoConstants.kI, Constants.AutoConstants.kD);
    private double seconds;
    public AutoDriveCmd(DriveMotorSubsystem motorSubsystem, double seconds) {
        this.motorSubsystem = motorSubsystem;
        this.seconds = seconds;
        addRequirements(motorSubsystem);
    }
    @Override
    public void initialize() {
        timer.restart();
        controller.setSetpoint(this.seconds);
    }
    @Override
    public void execute() {
        this.motorSubsystem.autoMove(controller.calculate(timer.get()));
    }
    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return controller.atSetpoint();
    }
    @Override
    public void end(boolean interrupted) {
        this.motorSubsystem.stopModules();
    }
}
