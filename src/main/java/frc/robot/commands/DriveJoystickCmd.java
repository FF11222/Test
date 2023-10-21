package frc.robot.commands;
import frc.robot.subsystems.DriveMotorSubsystem;
import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class DriveJoystickCmd extends CommandBase{
    
    private final DriveMotorSubsystem MotorSubsystem;
    private final Supplier<Double> speedFunction;
    private final Supplier<Double> turnFunction;


    public DriveJoystickCmd(DriveMotorSubsystem subsystem, Supplier<Double> speedFunction, Supplier<Double> turnFunction) {

        MotorSubsystem = subsystem;
        this.speedFunction = speedFunction;
        this.turnFunction = turnFunction;
    
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        // 1. Get real-time joystick inputs
        double speed = speedFunction.get();
        double turn = turnFunction.get() * 0.7;
        double leftSpeed = speed - turn;
        double rightSpeed = speed + turn;
        MotorSubsystem.move(rightSpeed, leftSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        MotorSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
