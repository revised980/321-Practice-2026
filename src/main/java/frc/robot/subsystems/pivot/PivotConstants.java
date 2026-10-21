package frc.robot.subsystems.pivot;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Volts;
import static edu.wpi.first.units.Units.Amps;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.units.measure.Current;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class PivotConstants {
    public static final Current kMaxCurrent = Amps.of(40);

    public static final Current kStatorCurrentLimit = Amps.of(40);
    public static final Current kSupplyCurrentLimit = Amps.of(40);

    public static final boolean kStatorLimitEnabled = true;
    public static final boolean kSupplyLimitEnabled = true;

    public static final int kPivotMotorID = 16;
    public static final double kPivotGearRatio = 142.9;

    public static final InvertedValue kInversion = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue kNeutralMode = NeutralModeValue.Brake;
}
