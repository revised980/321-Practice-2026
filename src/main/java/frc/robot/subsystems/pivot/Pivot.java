package frc.robot.subsystems.pivot;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public class Pivot {
    TalonFX pivotMotor = new TalonFX(PivotConstants.kPivotMotorID);

    public Pivot() {
        configureMotors();
    }

    final CurrentLimitsConfigs currentLimits =
        new CurrentLimitsConfigs()
            .withSupplyCurrentLimit(PivotConstants.kSupplyCurrentLimit)
            .withSupplyCurrentLimitEnable(PivotConstants.kSupplyLimitEnabled)
            .withStatorCurrentLimit(PivotConstants.kStatorCurrentLimit)
            .withStatorCurrentLimitEnable(PivotConstants.kStatorLimitEnabled);

    final MotorOutputConfigs motorOutputs = 
        new MotorOutputConfigs()
            .withInverted(PivotConstants.kInversion)
            .withNeutralMode(PivotConstants.kNeutralMode);

    final FeedbackConfigs feedback =
        new FeedbackConfigs()
            .withSensorToMechanismRatio(PivotConstants.kPivotGearRatio);
        
    final TalonFXConfiguration pivotMotorConfigurations =
        new TalonFXConfiguration()
            .withCurrentLimits(currentLimits)
            .withMotorOutput(motorOutputs)
            .withFeedback(feedback);
    
    private void configureMotors() {
        pivotMotor.getConfigurator().apply(pivotMotorConfigurations);
    }
}