package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveMotorModule {
    private VictorSPX motor;

    public DriveMotorModule(int Motor_Port, boolean Direction) {        
        motor = new VictorSPX(Motor_Port);
        motor.setInverted(Direction);
        this.motor.setNeutralMode(NeutralMode.Brake);
    }

    public void setDesiredState(Double speed) {
        motor.set(ControlMode.PercentOutput, speed * Constants.DriveConstants.kSpeed);
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput, 0);
    }
}
