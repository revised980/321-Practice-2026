package frc.robot.subsystems.flywheel;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Current;

public class FlywheelConstants {
    public static final Current kMaxCurrent = Amps.of(40); 

    public static final Current kSupplyCurrentLimit = Amps.of(40);
    public static final Current kStatorCurrentLimit = Amps.of(40);

    public static final boolean kSupplyLimitEnabled = true;
    public static final boolean kStatorLimitEnabled = true;

    public static final InvertedValue kInvertedValue = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue kNeutralModeValue = NeutralModeValue.Brake;

    public static final int kFlywheelMotorID = 67;
    public static final double kFlywheelGearRatio = 67.67;
}
