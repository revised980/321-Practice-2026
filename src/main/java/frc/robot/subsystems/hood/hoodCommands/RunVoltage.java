package frc.robot.subsystems.hood.hoodCommands;

import static edu.wpi.first.units.Units.Volts;

import java.util.function.Supplier;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.hood.Hood;

public class RunVoltage extends Command{
    
    Hood hood;
    Supplier<Voltage> voltage;

    public RunVoltage(Hood hood, Supplier<Voltage> voltage) {
        this.hood = hood;
        this.voltage = voltage;
        addRequirements(hood);
    }

    @Override 
    public void initialize() {
    }

    @Override 
    public void execute() {
        hood.setVoltage(voltage.get());
    }

    @Override 
    public boolean isFinished() {
        return false;
    }

    @Override 
    public void end(boolean interrupted) {
        hood.setVoltage(Volts.of(0));
    }
}