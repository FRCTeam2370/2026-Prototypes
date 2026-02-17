// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.shooterConstants;

public class ShooterAimSubsystem extends SubsystemBase {
  /** Creates a new ShooterAimSubsystem. */
  public static TalonFX shooterAimMotor = new TalonFX(shooterConstants.shooterAimMotorID);

  public static TalonFXConfiguration shooterAimMotorConfig = new TalonFXConfiguration();

  public static MotionMagicDutyCycle shooterAimDutyCycle = new MotionMagicDutyCycle(0);

  public ShooterAimSubsystem() {
    shooterAimConfiguration();
  }

  public static void shooterAim(double position) {
    shooterAimMotor.setControl(shooterAimDutyCycle.withPosition(position));
  }

  public static void shooterManualAim(double speed) {
    shooterAimMotor.set(speed);
  }

  public static void shooterAimZero() {
    while (shooterAimMotor.getStatorCurrent().getValueAsDouble() < 20) {
      shooterManualAim(.10);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Shooter Aim Motor Current", shooterAimMotor.getStatorCurrent().getValueAsDouble());
    SmartDashboard.putNumber("Shooter Elevation Position", shooterAimMotor.getPosition().getValueAsDouble());
  }

  public static void shooterAimConfiguration() {
    shooterAimMotor.setPosition(0);

    shooterAimMotor.setNeutralMode(NeutralModeValue.Brake);

    shooterAimMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    shooterAimMotorConfig.Slot0.kP = 1.8;
    shooterAimMotorConfig.Slot0.kI = 0.12;
    shooterAimMotorConfig.Slot0.kD = 0.01;

    shooterAimMotorConfig.MotionMagic.MotionMagicAcceleration = 100;
    shooterAimMotorConfig.MotionMagic.MotionMagicCruiseVelocity = 50;

    shooterAimMotor.getConfigurator().apply(shooterAimMotorConfig);
  }
}
