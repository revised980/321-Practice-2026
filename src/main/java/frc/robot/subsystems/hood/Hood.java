package frc.robot.subsystems.hood;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.DegreesPerSecondPerSecond;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

    private TrapezoidProfile.Constraints hoodConstraints = 
        new TrapezoidProfile.Constraints(
            HoodConstants.kMaxVelocity.in(DegreesPerSecond), 
            HoodConstants.kMaxAcceleration.in(DegreesPerSecondPerSecond));

    private ProfiledPIDController hoodController = new ProfiledPIDController(HoodConstants.kP, HoodConstants.kI, HoodConstants.kD, TrapezoidProfile.Constraints constraints);

    public Angle currentAngle = Degrees.of(30);
    public Angle targetAngle = Degrees.of(45);
    private Voltage desiredVoltage = Volts.of(hoodController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));

    @Logged(name = "hoodPitch")
    public Angle getAngle() {
        Angle angle = hoodMotor.getPosition().getValue();
        return angle;
    }

    @Logged(name = "hoodAtTargetAngle")
    public boolean atTargetAngle() {
        boolean atTargetAngle = Math.abs(getAngle().in(Degrees) - HoodConstants.kTargetAngle.in(Degrees)) < HoodConstants.kToleranceAngle.in(Degrees);
        return atTargetAngle;
    }

    @Logged(name = "hoodVoltage")
    public Voltage getVoltage() {
        Voltage voltage = hoodMotor.getMotorVoltage().getValue();
        return voltage;
    }

    @Logged(name = "hoodStatorCurrent")
    public Current getCurrent() {
        Current statorCurrent = hoodMotor.getStatorCurrent().getValue();
        return statorCurrent;
    }

    // @Override
    public void periodic() {
        SmartDashboard.putNumber("Hood Angle", hoodMotor.getPosition().getValue().in(Degrees));
    }
}
