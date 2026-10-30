package frc.robot.subsystems.hood.hoodCommands;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Volts;

import java.util.function.Supplier;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.hood.Hood;
import frc.robot.subsystems.hood.HoodConstants;

public class GoToAngle extends Command{
    Hood hood;

    Supplier<Angle> targetAngle;

    public void homeHood(Hood hood, Supplier<Angle> targetAngle) {
        this.hood = hood;
        this.targetAngle = targetAngle;
        addRequirements(hood);
    }
    
    @Override
    public void initialize() {
        hood.setTargetAngle(targetAngle);
    }

    @Override
    public void execute() {
        hood.setVoltage(HoodConstants.kHomingVoltage);
    }

    // @Override
    // public boolean isFinished() {
    //    // return hood.atHomePosition();
    // }

    @Override
    public void end(boolean interrupted) {
       // hood.zeroEncoder();
        hood.setVoltage(Volts.of(0));
    }
}
