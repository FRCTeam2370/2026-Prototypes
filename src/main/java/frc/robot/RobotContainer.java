// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.intakeConstants;
import frc.robot.Constants.shooterConstants;
import frc.robot.Constants.spindexerConstants;
import frc.robot.Constants.uptakeConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Intake.IntakeControl;
import frc.robot.commands.Shooter.AimManualControl;
import frc.robot.commands.Shooter.ControlShooterRotate;
import frc.robot.commands.Shooter.ShootShooter;
import frc.robot.commands.Shooter.ShooterRotatePos;
import frc.robot.commands.Spindexer.ControlSpindexerExit;
import frc.robot.commands.Spindexer.controlSpindexer;
import frc.robot.commands.Uptake.UptakeFuel;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterAimSubsystem;
import frc.robot.subsystems.ShooterPrototype;
import frc.robot.subsystems.ShooterRotateSubsystem;
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
  private final ShooterAimSubsystem mShooterAimSubsystem = new ShooterAimSubsystem();
  private final ShooterRotateSubsystem mShooterRotateSubsystem = new ShooterRotateSubsystem();

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
    driver.rightTrigger().whileTrue(new ShootShooter(mShooterPrototype, shooterConstants.shooterSpeed));
    driver.leftTrigger().whileTrue(new ShootShooter(mShooterPrototype, -shooterConstants.shooterSpeed));
    driver.y().whileTrue(new AimManualControl(mShooterAimSubsystem, shooterConstants.shooterAimSpeed));
    driver.a().whileTrue(new AimManualControl(mShooterAimSubsystem, -shooterConstants.shooterAimSpeed));

    //Uptake Controls
    driver.rightBumper().whileTrue(new UptakeFuel(mUptakeSubsystem, uptakeConstants.uptakeSpeed));
    driver.leftBumper().whileTrue(new UptakeFuel(mUptakeSubsystem, -uptakeConstants.uptakeSpeed));

    //Shooter Rotation Controls
    driver.x().whileTrue(new ControlShooterRotate(mShooterRotateSubsystem, shooterConstants.shooterRotateSpeed));
    driver.b().whileTrue(new ControlShooterRotate(mShooterRotateSubsystem, -shooterConstants.shooterRotateSpeed));
    // driver.x().onTrue(new ShooterRotatePos(mShooterRotateSubsystem, 0));
    // driver.b().onTrue(new ShooterRotatePos(mShooterRotateSubsystem, -1.4));
    // driver.povUp().onTrue(new ShooterRotatePos(mShooterRotateSubsystem, 1.7));

    //Intake Controls
    operator.y().whileTrue(new IntakeControl(mIntakeSubsystem, intakeConstants.intakeSpeed));
    operator.a().whileTrue(new IntakeControl(mIntakeSubsystem, -intakeConstants.intakeSpeed));

    //Spindexer Controls
    operator.rightBumper().whileTrue(new ControlSpindexerExit(mSpindexerExitSubsystem, spindexerConstants.spindexerExitSpeed));
    operator.leftBumper().whileTrue(new ControlSpindexerExit(mSpindexerExitSubsystem, -spindexerConstants.spindexerExitSpeed));
    operator.rightTrigger().whileTrue(new controlSpindexer(mSpindexerSubsystem, spindexerConstants.spindexerSpeed));
    operator.leftTrigger().whileTrue(new controlSpindexer(mSpindexerSubsystem, -spindexerConstants.spindexerSpeed));

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
