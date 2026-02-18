// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterPrototype;
import frc.robot.subsystems.UptakeSubsystem;
import frc.robot.subsystems.spindexerSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ShootAtVeolcity extends Command {
  double vel;
  /** Creates a new ShootAtVeolcity. */
  public ShootAtVeolcity(double vel, ShooterPrototype mShooterPrototype, UptakeSubsystem mUptakeSubsystem, spindexerSubsystem mSpindexerSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.vel = vel;
    addRequirements(mShooterPrototype, mUptakeSubsystem, mSpindexerSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ShooterPrototype.shootWithVelocity(vel);
    if(ShooterPrototype.getVelocity() > vel * 0.975){
      UptakeSubsystem.uptakeWithVelocity(50);
      spindexerSubsystem.spindexrWithVelocity(50);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ShooterPrototype.shootWithVelocity(0);
    UptakeSubsystem.uptakeWithVelocity(0);
    spindexerSubsystem.spindexrWithVelocity(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
