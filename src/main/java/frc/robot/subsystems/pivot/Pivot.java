package frc.robot.subsystems.pivot;

import static edu.wpi.first.units.Units.Degree;
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
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pivot {
    private double kS = 0;
    private double kG = 0;
    private double kV = 0;

    private ArmFeedforward pivotFeedforward = new ArmFeedforward(kS, kG, kV);

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

    private double kP = 0;
    private double kI = 0;
    private double kD = 0;

    private TrapezoidProfile.Constraints pivotConstraints =
        new TrapezoidProfile.Constraints(
            PivotConstants.kMaxVelocity.in(DegreesPerSecond),
            PivotConstants.kMaxAcceleration.in(DegreesPerSecondPerSecond));

    private ProfiledPIDController pivotController =
        new ProfiledPIDController(kP, kI, kD, pivotConstraints);

    @Logged(name = "pivotPitch")
    public Angle getAngle() {
        Angle angle = pivotMotor.getPosition().getValue();
        return angle;
    }

    @Logged(name = "pivotAtTargetAngle")
    public boolean atTargetAngle() {
        boolean atTargetAngle = Math.abs(getAngle().in(Degrees) - PivotConstants.kTargetAngle.in(Degrees)) < PivotConstants.kToleranceAngle.in(Degrees);
        return atTargetAngle;
    }

    @Logged(name = "pivtoVoltage")
    public Voltage getVoltage() {
        Voltage voltage = pivotMotor.getMotorVoltage().getValue();
        return voltage;
    }

    @Logged(name = "pivotStatorCurrent")
    public Current getCurrent() {
        Current current = pivotMotor.getStatorCurrent().getValue();
        return current;
    }

    public void goToAngle(Angle targetAngle) {
        Angle currentAngle = getAngle();
        Voltage calculatedPIDVoltage = Volts.of(pivotController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));
        Voltage calculatedFFVoltage = Volts.of(pivotFeedforward.calculate(targetAngle.in(Degrees), 0));
        Voltage desiredVoltage = Volts.of(calculatedPIDVoltage.in(Volts) + calculatedFFVoltage.in(Volts));
        pivotMotor.setVoltage(desiredVoltage.in(Volts));

    }

    //Override
    public void periodic() {
        SmartDashboard.putNumber("Pivot Angle", pivotMotor.getPosition().getValue().in(Degrees));
    }
}