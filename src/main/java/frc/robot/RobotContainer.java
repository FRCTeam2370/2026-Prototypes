// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.intakeConstants;
import frc.robot.Constants.shooterConstants;
import frc.robot.Constants.spindexerConstants;
import frc.robot.Constants.uptakeConstants;
import frc.robot.commands.Intake.IntakeControl;
import frc.robot.commands.Intake.IntakeRotationControl;
import frc.robot.commands.Intake.IntakeSetPosition;
import frc.robot.commands.Shooter.AimManualControl;
import frc.robot.commands.Shooter.ControlShooterRotate;
import frc.robot.commands.Shooter.ShootAtVeolcity;
import frc.robot.commands.Shooter.ShootShooter;
import frc.robot.commands.Spindexer.controlSpindexer;
import frc.robot.commands.Swerve.ResetGyro;
import frc.robot.commands.Swerve.TeleopSwerve;
import frc.robot.commands.Uptake.UptakeFuel;
import frc.robot.subsystems.IntakeRotSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterAimSubsystem;
import frc.robot.subsystems.ShooterPrototype;
import frc.robot.subsystems.ShooterRotateSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.UptakeSubsystem;
import frc.robot.subsystems.spindexerSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ShooterPrototype mShooterPrototype = new ShooterPrototype();
  private final IntakeSubsystem mIntakeSubsystem = new IntakeSubsystem();
  private final UptakeSubsystem mUptakeSubsystem = new UptakeSubsystem();
  private final spindexerSubsystem mSpindexerSubsystem = new spindexerSubsystem();
  private final ShooterAimSubsystem mShooterAimSubsystem = new ShooterAimSubsystem();
  private final ShooterRotateSubsystem mShooterRotateSubsystem = new ShooterRotateSubsystem();
  private final IntakeRotSubsystem mIntakeRotSubsystem = new IntakeRotSubsystem();
  private final SwerveSubsystem mSwerveSubsystem = new SwerveSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController driver = new CommandXboxController(OperatorConstants.driverController);
  private final CommandXboxController operator = new CommandXboxController(OperatorConstants.operatorController);
  private final CommandXboxController testing = new CommandXboxController( 3);

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
    //Swerve
    mSwerveSubsystem.setDefaultCommand(new TeleopSwerve(mSwerveSubsystem, ()->driver.getLeftY(), ()->driver.getLeftX(), ()->-driver.getRightX(), ()-> false));
    driver.rightStick().onTrue(new ResetGyro(mSwerveSubsystem));

    //Shooter Controls
    /*TODO: bring this back later
    driver.rightTrigger().whileTrue(new ShootShooter(mShooterPrototype, shooterConstants.shooterSpeed));
    driver.leftTrigger().whileTrue(new ShootShooter(mShooterPrototype, -shooterConstants.shooterSpeed));
    driver.y().whileTrue(new AimManualControl(mShooterAimSubsystem, shooterConstants.shooterAimSpeed));
    driver.a().whileTrue(new AimManualControl(mShooterAimSubsystem, -shooterConstants.shooterAimSpeed));
    */

    //Uptake Controls
    driver.rightBumper().whileTrue(new UptakeFuel(mUptakeSubsystem, uptakeConstants.uptakeSpeed));
    driver.leftBumper().whileTrue(new UptakeFuel(mUptakeSubsystem, -uptakeConstants.uptakeSpeed));

    //Shooter Rotation Controls
    /*TODO: bring this back later
    driver.x().whileTrue(new ControlShooterRotate(mShooterRotateSubsystem, shooterConstants.shooterRotateSpeed));
    driver.b().whileTrue(new ControlShooterRotate(mShooterRotateSubsystem, -shooterConstants.shooterRotateSpeed));
    */
    // driver.x().onTrue(new ShooterRotatePos(mShooterRotateSubsystem, 0));
    // driver.b().onTrue(new ShooterRotatePos(mShooterRotateSubsystem, -1.4));
    // driver.povUp().onTrue(new ShooterRotatePos(mShooterRotateSubsystem, 1.7));

    //Intake Controls
    driver.y().toggleOnTrue(new IntakeControl(mIntakeSubsystem, intakeConstants.intakeSpeed));
    driver.x().toggleOnTrue(new IntakeControl(mIntakeSubsystem, -intakeConstants.intakeSpeed));
    driver.rightTrigger().toggleOnTrue(new ShootAtVeolcity(20, mShooterPrototype, mUptakeSubsystem, mSpindexerSubsystem));
    driver.a().onTrue(new IntakeSetPosition(mIntakeRotSubsystem, 0));
    driver.b().onTrue(new IntakeSetPosition(mIntakeRotSubsystem, 4));

    //Spindexer Controls
    operator.rightTrigger().whileTrue(new controlSpindexer(mSpindexerSubsystem, spindexerConstants.spindexerSpeed));
    operator.leftTrigger().whileTrue(new controlSpindexer(mSpindexerSubsystem, -spindexerConstants.spindexerSpeed));
    operator.rightBumper().whileTrue(new IntakeRotationControl(mIntakeRotSubsystem, intakeConstants.intakeRotationSpeed));
    operator.leftBumper().whileTrue(new IntakeRotationControl(mIntakeRotSubsystem, -intakeConstants.intakeRotationSpeed));

    //Testing
    // testing.a().onTrue(new ShooterRotatePos(mShooterRotateSubsystem, 0.001));
    // testing.x().onTrue(new ShooterRotatePos(mShooterRotateSubsystem, 2));
    // testing.y().onTrue(new ShooterRotatePos(mShooterRotateSubsystem, 2.25));
    // testing.b().onTrue(new ShooterRotatePos(mShooterRotateSubsystem, 9));

    // testing.a().onTrue(new ShooterElevationPos(mShooterAimSubsystem, 0.01));
    // testing.x().onTrue(new ShooterElevationPos(mShooterAimSubsystem, 1.375));
    // testing.y().onTrue(new ShooterElevationPos(mShooterAimSubsystem, 2.75));
    // testing.b().onTrue(new ShooterElevationPos(mShooterAimSubsystem, 5.5));

    //Do shooter speed 100-20 in incrememnts of 10. Yes it's annoying, deal with it :3
    testing.rightTrigger().whileTrue(new ShootShooter(mShooterPrototype, 100));
    testing.leftTrigger().whileTrue(new ShootShooter(mShooterPrototype, 90));
    testing.rightBumper().whileTrue(new ShootShooter(mShooterPrototype, 80));
    testing.leftBumper().whileTrue(new ShootShooter(mShooterPrototype, 70));
    testing.x().whileTrue(new ShootShooter(mShooterPrototype, 60));
    testing.y().whileTrue(new ShootShooter(mShooterPrototype, 50));
    testing.b().whileTrue(new ShootShooter(mShooterPrototype, 40));
    testing.a().whileTrue(new ShootShooter(mShooterPrototype, 30));
    testing.povUp().whileTrue(new ShootShooter(mShooterPrototype, 20));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new Command() {
    };
  }
}
