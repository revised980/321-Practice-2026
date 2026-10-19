package frc.robot.subsystems.elevator;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public class Elevator {
    TalonFX elevatorMotor = new TalonFX(ElevatorConstants.kElevatorMotorID);

    public Elevator() {
        configureMotors();
    }

    final CurrentLimitsConfigs currentLimits =
        new CurrentLimitsConfigs()
            .withSupplyCurrentLimit(ElevatorConstants.kSupplyCurrentLimit)
            .withSupplyCurrentLimitEnable(ElevatorConstants.kSupplyLimitEnabled)
            .withStatorCurrentLimit(ElevatorConstants.kStatorCurrentLimit)
            .withStatorCurrentLimitEnable(ElevatorConstants.kStatorLimitEnabled);
                
    final MotorOutputConfigs motorOuputs =
        new MotorOutputConfigs()
            .withInverted(ElevatorConstants.KInvertedValue)
            .withNeutralMode(ElevatorConstants.kNeutralMode);

    final FeedbackConfigs feedback =
        new FeedbackConfigs()
            .withSensorToMechanismRatio(ElevatorConstants.kElevatorGearRatio);

    final TalonFXConfiguration elevatorMotorConfigurations =
        new TalonFXConfiguration()
            .withCurrentLimits(currentLimits)
            .withMotorOutput(motorOuputs)
            .withFeedback(feedback);
    
    private void configureMotors() {
        elevatorMotor.getConfigurator().apply(elevatorMotorConfigurations);
    }
}
