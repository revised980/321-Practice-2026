// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.hood.Hood;
import frc.robot.subsystems.hood.hoodCommands.GoToAngle;
import frc.robot.subsystems.hood.hoodCommands.HomeHood;
import frc.robot.subsystems.hood.hoodCommands.RunVoltage;

public class RobotContainer {

  private final CommandXboxController driver = new CommandXboxController(0);

  Hood hood = new Hood();

  public RobotContainer() {
    configureBindings();
  }

  // driver.leftBumper().whileTrue(new IntakeFuel(intakeRollers, intakePivot));

  private void configureBindings() {
    hood.setDefaultCommand(new RunVoltage(hood, () -> Volts.of(0)));
    driver.y().onTrue(new GoToAngle(hood, () -> Degrees.of(80)));
    driver.a().onTrue(new HomeHood(hood));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}