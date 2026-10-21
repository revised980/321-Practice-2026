package frc.robot.subsystems.hood;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.units.measure.Angle;

public class Hood {
    TalonFX hoodMotor = new TalonFX(HoodConstants.kHoodMotorID);    

    public Hood() {
        configureMotors();
    }

    final CurrentLimitsConfigs currentLimits =
        new CurrentLimitsConfigs()
            .withSupplyCurrentLimit(HoodConstants.kSupplyCurrentLimit)
            .withSupplyCurrentLimitEnable(HoodConstants.kSupplyLimitEnabled)
            .withStatorCurrentLimit(HoodConstants.kStatorCurrentLimit)
            .withStatorCurrentLimitEnable(HoodConstants.kStatorLimitEnabled);

    final MotorOutputConfigs motorOutputs =
        new MotorOutputConfigs()
            .withInverted(HoodConstants.kInversion)
            .withNeutralMode(HoodConstants.kNeutralMode);

    final FeedbackConfigs feedback =
        new FeedbackConfigs()
            .withSensorToMechanismRatio(HoodConstants.kHoodGearRatio);

    final TalonFXConfiguration hoodMotorConfigurations =
        new TalonFXConfiguration()
            .withCurrentLimits(currentLimits)
            .withMotorOutput(motorOutputs)
            .withFeedback(feedback);

    private void configureMotors() {
        hoodMotor.getConfigurator().apply(hoodMotorConfigurations);
    }

    @Logged(name = "hoodPitch")
    public Angle getAngle() {
        Angle angle = hoodMotor.getPosition().getValue();
        return angle;
    }

    @Logged(name = "atTargetAngle")
    public boolean atAngle() {
        boolean atTargetAngle = Math.abs(getAngle().in(Degrees) - HoodConstants.kTargetAngle.in(Degrees)) < HoodConstants.kToleranceAngle.in(Degrees);
        return atTargetAngle;
    }
}
