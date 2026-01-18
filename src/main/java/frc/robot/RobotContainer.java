// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AimManualControl;
import frc.robot.commands.Autos;
import frc.robot.commands.ControlSpindexerExit;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeControl;
import frc.robot.commands.ShootShooter;
import frc.robot.commands.UptakeFuel;
import frc.robot.commands.controlSpindexer;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterPrototype;
import frc.robot.subsystems.SpindexerExitSubsystem;
import frc.robot.subsystems.UptakeSubsystem;
import frc.robot.subsystems.spindexerSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ShooterPrototype mShooterPrototype = new ShooterPrototype();
  private final IntakeSubsystem mIntakeSubsystem = new IntakeSubsystem();
  private final UptakeSubsystem mUptakeSubsystem = new UptakeSubsystem();
  private final spindexerSubsystem mSpindexerSubsystem = new spindexerSubsystem();
  private final SpindexerExitSubsystem mSpindexerExitSubsystem = new SpindexerExitSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController driver =
      new CommandXboxController(OperatorConstants.driverController);
  private final CommandXboxController operator = new CommandXboxController(OperatorConstants.operatorController);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    //Shooter Controls
    driver.rightTrigger().whileTrue(new ShootShooter(mShooterPrototype, 65));
    driver.leftTrigger().whileTrue(new ShootShooter(mShooterPrototype, -65));
    driver.rightBumper().whileTrue(new AimManualControl(mShooterPrototype, .1));
    driver.leftBumper().whileTrue(new AimManualControl(mShooterPrototype, -.1));

    //Uptake Controls
    driver.y().whileTrue(new UptakeFuel(mUptakeSubsystem, 50));
    driver.a().whileTrue(new UptakeFuel(mUptakeSubsystem, -50));

    //Intake Controls
    operator.y().whileTrue(new IntakeControl(mIntakeSubsystem, .9));
    operator.a().whileTrue(new IntakeControl(mIntakeSubsystem, -.9));

    //Spindexer Controls
    operator.rightBumper().whileTrue(new ControlSpindexerExit(mSpindexerExitSubsystem, 1));
    operator.leftBumper().whileTrue(new ControlSpindexerExit(mSpindexerExitSubsystem, -1));
    operator.rightTrigger().whileTrue(new controlSpindexer(mSpindexerSubsystem, .3));
    operator.leftTrigger().whileTrue(new controlSpindexer(mSpindexerSubsystem, -.3));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
