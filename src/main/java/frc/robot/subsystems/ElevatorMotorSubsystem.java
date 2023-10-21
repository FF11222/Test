package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class ElevatorMotorSubsystem extends SubsystemBase {
    private static DriveMotorModule lMotor;
    private static DriveMotorModule rMotor;

    public ElevatorMotorSubsystem() {
        lMotor = new DriveMotorModule(RobotMap.ElevatorPort.PWM_Port.l_Motor, true);
        rMotor = new DriveMotorModule(RobotMap.ElevatorPort.PWM_Port.r_Motor, false);
    }

    public void move(Double speed) {
        lMotor.setDesiredState(speed);
        rMotor.setDesiredState(speed);
    }
}

