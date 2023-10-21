package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class IntakeSubsystem extends SubsystemBase {
    private static DriveMotorModule motor;
    public IntakeSubsystem() {
        motor = new DriveMotorModule(RobotMap.IntakePort.PWM_Port.motor, true);
    }

    public void move(double speed) {
        motor.setDesiredState(speed);
    }
}

