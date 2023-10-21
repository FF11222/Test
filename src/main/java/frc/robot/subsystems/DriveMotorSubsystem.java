package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

import java.util.function.Supplier;

public class DriveMotorSubsystem extends SubsystemBase {
    private static DriveMotorModule l_Motor1;
    private static DriveMotorModule l_Motor2;
    private static DriveMotorModule r_Motor1;
    private static DriveMotorModule r_Motor2;

    public DriveMotorSubsystem() {
        l_Motor1 = new DriveMotorModule(RobotMap.DriverPort.PWM_Port.l_MotorPort1, false);
        l_Motor2 = new DriveMotorModule(RobotMap.DriverPort.PWM_Port.l_MotorPort2, false);
        r_Motor1 = new DriveMotorModule(RobotMap.DriverPort.PWM_Port.r_MotorPort1, true);
        r_Motor2 = new DriveMotorModule(RobotMap.DriverPort.PWM_Port.r_MotorPort2, true);
    }

    public void move(Double rightSpeed, Double leftSpeed) {
        l_Motor1.setDesiredState(leftSpeed * Constants.DriveConstants.kSpeed);
        SmartDashboard.putNumber("l_Motor1 Speed: ", leftSpeed * Constants.DriveConstants.kSpeed);
        l_Motor2.setDesiredState(leftSpeed * Constants.DriveConstants.kSpeed);
        SmartDashboard.putNumber("l_Motor2 Speed: ", leftSpeed * Constants.DriveConstants.kSpeed);
        r_Motor1.setDesiredState(rightSpeed * Constants.DriveConstants.kSpeed);
        SmartDashboard.putNumber("r_Motor1 Speed: ", rightSpeed * Constants.DriveConstants.kSpeed);
        r_Motor2.setDesiredState(rightSpeed * Constants.DriveConstants.kSpeed);
        SmartDashboard.putNumber("r_Motor2 Speed: ", rightSpeed * Constants.DriveConstants.kSpeed);
    }

    public void autoMove(double speed) {
        l_Motor1.setDesiredState(speed * Constants.AutoConstants.speed);
        l_Motor2.setDesiredState(speed * Constants.AutoConstants.speed);
        r_Motor1.setDesiredState(speed * Constants.AutoConstants.speed);
        r_Motor2.setDesiredState(speed * Constants.AutoConstants.speed);
    }

    public void stopModules() {
        l_Motor1.stop();
        l_Motor2.stop();
        r_Motor1.stop();
        r_Motor2.stop();
    }
}
