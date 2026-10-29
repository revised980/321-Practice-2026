package frc.robot.subsystems.pivot;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.DegreesPerSecondPerSecond;
import static edu.wpi.first.units.Units.Volts;
import static edu.wpi.first.units.Units.Amps;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.units.measure.Current;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class PivotConstants {
    public static final Angle kTargetAngle = Degrees.of(30);
    public static final Angle kToleranceAngle = Degrees.of(1.5);

    public static final Current kMaxCurrent = Amps.of(40);

    public static final Current kStatorCurrentLimit = Amps.of(40);
    public static final Current kSupplyCurrentLimit = Amps.of(40);

    public static final boolean kStatorLimitEnabled = true;
    public static final boolean kSupplyLimitEnabled = true;

    public static final int kPivotMotorID = 16;
    public static final double kPivotGearRatio = 142.9;

    public static final InvertedValue kInversion = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue kNeutralMode = NeutralModeValue.Brake;

    public static final AngularVelocity kMaxVelocity = DegreesPerSecond.of(0);
    public static final AngularAcceleration kMaxAcceleration = DegreesPerSecondPerSecond.of(0);
}
