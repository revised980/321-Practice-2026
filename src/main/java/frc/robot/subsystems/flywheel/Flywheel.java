package frc.robot.subsystems.flywheel;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public class Flywheel {
    TalonFX flywheelMotor = new TalonFX(FlywheelConstants.kFlywheelMotorID);

    public Flywheel() {
        configureMotors();
    }

    final CurrentLimitsConfigs currentLimits =
        new CurrentLimitsConfigs()
            .withSupplyCurrentLimit(FlywheelConstants.kSupplyCurrentLimit)
            .withSupplyCurrentLimitEnable(FlywheelConstants.kSupplyLimitEnabled)
            .withStatorCurrentLimit(FlywheelConstants.kStatorCurrentLimit)
            .withStatorCurrentLimitEnable(FlywheelConstants.kStatorLimitEnabled);

    final MotorOutputConfigs motorOutputs =
        new MotorOutputConfigs()
            .withInverted(FlywheelConstants.kInvertedValue)
            .withNeutralMode(FlywheelConstants.kNeutralModeValue);

    final FeedbackConfigs feedback =
        new FeedbackConfigs()
            .withSensorToMechanismRatio(FlywheelConstants.kFlywheelGearRatio);

    final TalonFXConfiguration flywheelMotorConfigurations =
        new TalonFXConfiguration()
            .withCurrentLimits(currentLimits)
            .withMotorOutput(motorOutputs)
            .withFeedback(feedback);

    public void configureMotors() {
        flywheelMotor.getConfigurator().apply(flywheelMotorConfigurations);
    }
}
