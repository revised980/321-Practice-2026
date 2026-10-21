package frc.robot.subsystems.elevator;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public class Elevator {
    TalonFX elevatorMotorLeft = new TalonFX(ElevatorConstants.kElevatorMotorLeftID);
    TalonFX elevatorMotorRight = new TalonFX(ElevatorConstants.kElevatorMotorRightID);

    public Elevator() {
        configureMotors();
    }

    final CurrentLimitsConfigs currentLimits =
        new CurrentLimitsConfigs()
            .withSupplyCurrentLimit(ElevatorConstants.kSupplyCurrentLimit)
            .withSupplyCurrentLimitEnable(ElevatorConstants.kSupplyLimitEnabled)
            .withStatorCurrentLimit(ElevatorConstants.kStatorCurrentLimit)
            .withStatorCurrentLimitEnable(ElevatorConstants.kStatorLimitEnabled);
                
    final MotorOutputConfigs motorOuputsLeft =
        new MotorOutputConfigs()
            .withInverted(ElevatorConstants.KInvertedValueLeft)
            .withNeutralMode(ElevatorConstants.kNeutralMode);

    final MotorOutputConfigs motorOuputsRight =
        new MotorOutputConfigs()
            .withInverted(ElevatorConstants.KInvertedValueRight)
            .withNeutralMode(ElevatorConstants.kNeutralMode);

    final FeedbackConfigs feedback =
        new FeedbackConfigs()
            .withSensorToMechanismRatio(ElevatorConstants.kElevatorGearRatio);

    final TalonFXConfiguration elevatorMotorLeftConfigurations =
        new TalonFXConfiguration()
            .withCurrentLimits(currentLimits)
            .withMotorOutput(motorOuputsLeft)
            .withFeedback(feedback);

    final TalonFXConfiguration elevatorMotorRightConfigurations =
        new TalonFXConfiguration()
            .withCurrentLimits(currentLimits)
            .withMotorOutput(motorOuputsRight)
            .withFeedback(feedback);
    
    private void configureMotors() {
        elevatorMotorLeft.getConfigurator().apply(elevatorMotorLeftConfigurations);
        elevatorMotorRight.getConfigurator().apply(elevatorMotorRightConfigurations);
    }
}
