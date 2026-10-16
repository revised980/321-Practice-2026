package frc.robot.subsystems.hood;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Volts;
import static edu.wpi.first.units.Units.Amps;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.units.measure.Current;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class HoodConstants {
    public static final String kRobotName = "Walter";

    public static final Angle kFeedAngle = Degrees.of(15); // converts double to degrees
    public static final double kFeedAngleDouble = kFeedAngle.in(Degrees); // converts degrees to double
    public static final double kFeedAngleRadian = kFeedAngle.in(Radians); // converts degrees to double
    public static final Angle kStartingAngle = Degrees.of(0); // converts double to degrees

    public static final Voltage kMaxVoltage = Volts.of(12); // converts double to volts
    public static final Current kMaxCurrent = Amps.of(40); // converts double to amps

    public static final Current kSupplyCurrentLimit = Amps.of(40);
    public static final Current kStatorCurrentLimit = Amps.of(40);

    public static final boolean kSupplyLimitEnabled = true;
    public static final boolean kStatorLimitEnabled = true;

    public static final int kHoodMotorID = 11;
    public static final double kHoodGearRatio = 142.9;

    public static final InvertedValue kInversion = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue kNeutralMode = NeutralModeValue.Brake; // when the motor is not spinning it will be locked at a stop
}
