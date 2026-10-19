package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;

public class ElevatorConstants {
    public static final Angle kFeedAngle = Degrees.of(15);
    public static final Angle kStartingAngle = Degrees.of(0);

    public static final Voltage kMaxVoltage = Volts.of(12);
    public static final Current kMaxCurrent = Amps.of(40);

    public static final Current kSupplyCurrentLimit = Amps.of(40);
    public static final Current kStatorCurrentLimit = Amps.of(40);

    public static final boolean kSupplyLimitEnabled = true;
    public static final boolean kStatorLimitEnabled = true;

    public static final int kElevatorMotorID = 1;
    public static final double kElevatorGearRatio = 67;

    public static final InvertedValue KInvertedValue = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue kNeutralMode = NeutralModeValue.Brake;
}
