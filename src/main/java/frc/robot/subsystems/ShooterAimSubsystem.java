// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionDutyCycle;
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

  public static PositionDutyCycle shooterAimDutyCycle = new PositionDutyCycle(0);

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
  }

  public static void shooterAimConfiguration() {
    shooterAimMotor.setNeutralMode(NeutralModeValue.Brake);

    shooterAimMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    shooterAimMotorConfig.Slot0.kP = 0.05;
    shooterAimMotorConfig.Slot0.kI = 0;
    shooterAimMotorConfig.Slot0.kD = 0;

    shooterAimMotor.getConfigurator().apply(shooterAimMotorConfig);
  }
}
