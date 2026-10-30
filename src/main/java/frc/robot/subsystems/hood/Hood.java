package frc.robot.subsystems.hood;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.DegreesPerSecondPerSecond;
import static edu.wpi.first.units.Units.Volts;

import java.util.function.Supplier;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hood extends SubsystemBase{
    private Angle targetAngle = HoodConstants.kStartingAngle;

    private double kS = 0;
    private double kG = 0;
    private double kV = 0;
      
    private ArmFeedforward hoodFeedforward = new ArmFeedforward(kS, kG, kV);

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

    public double kP = 0;
    public double kI = 0;
    public double kD = 0;

    private TrapezoidProfile.Constraints hoodConstraints = 
        new TrapezoidProfile.Constraints(
            HoodConstants.kMaxVelocity.in(DegreesPerSecond), 
            HoodConstants.kMaxAcceleration.in(DegreesPerSecondPerSecond));

    private ProfiledPIDController hoodController =
        new ProfiledPIDController(kP, kI, kD, hoodConstraints);

    public void setVoltage(Voltage desiredVoltage) {
        hoodMotor.setVoltage(desiredVoltage.in(Volts));
    }

    public void setTargetAngle(Supplier<Angle> angle) {
        this.angle = targetAngle;
    }

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

    // @Logged(name = "atHomeAngle")
    // public boolean atHomeAngle() {
        
    // }

    // public Angle currentAngle = Degrees.of(30);
    // public Angle targetAngle = Degrees.of(45);
    // private Voltage desiredVoltage = Volts.of(hoodController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));

    // public void goToAngle(Angle targetAngle) {
    //     Angle currentAngle = getAngle();
    //     Voltage desiredVoltage = Volts.of(hoodController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));
    //     hoodMotor.setVoltage(desiredVoltage.in(Volts));
    // }

    public void goToAngle(Angle targetAngle) {
        Angle currentAngle = getAngle();
        Voltage calculatedPIDVoltage = Volts.of(hoodController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));
        Voltage calculatedFFVoltage = Volts.of(hoodFeedforward.calculate(targetAngle.in(Degrees), 0));
        Voltage desiredVoltage = Volts.of(calculatedPIDVoltage.in(Volts) + calculatedFFVoltage.in(Volts));
        hoodMotor.setVoltage(desiredVoltage.in(Volts));
    }

    // @Override
    public void periodic() {
        SmartDashboard.putNumber("Hood Angle", hoodMotor.getPosition().getValue().in(Degrees));
    }
}
