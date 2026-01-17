// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterPrototype extends SubsystemBase {

  public static TalonFX shooterMotorOne = new TalonFX(Constants.shooterConstants.shooterMotorOneID);
  public static TalonFX shooterMotorTwo = new TalonFX(Constants.shooterConstants.shooterMotorTwoID);
  public static TalonFX shooterAimMotor = new TalonFX(Constants.shooterConstants.shooterAimMotorID);

  public static TalonFXConfiguration shooterMotorOneConfig = new TalonFXConfiguration();
  public static TalonFXConfiguration shooterMotorTwoConfig = new TalonFXConfiguration();
  public static TalonFXConfiguration shooterAimMotorConfig = new TalonFXConfiguration();

  public static VelocityDutyCycle shooterVelocityDutyCycle = new VelocityDutyCycle(0);
  public static PositionDutyCycle shooterAimDutyCycle = new PositionDutyCycle(0);

  /** Creates a new ShooterPrototype. */
  public ShooterPrototype() {
    shooterMotorConfig();
  }

  public static void shootWithVelocity(double speed) {
    shooterMotorOne.setControl(shooterVelocityDutyCycle.withVelocity(speed));
    shooterMotorTwo.setControl(shooterVelocityDutyCycle.withVelocity(speed));
  }

  public static void shootPrototype(double speed) {
    shooterMotorOne.set(speed);
    shooterMotorTwo.set(speed);
  }

  public static void shooterAim(double position) {
    shooterAimMotor.setControl(shooterAimDutyCycle.withPosition(position));
  }

  public static void shooterManualAim(double speed) {
    shooterAimMotor.set(speed);
    //Speed intake as %
  }

  public static void shooterAimZero() {
    while (shooterAimMotor.getStatorCurrent().getValueAsDouble() < 20) {
      shooterManualAim(.10);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Motor One Velocity", shooterMotorOne.getVelocity().getValueAsDouble());
    SmartDashboard.putNumber("Motor Two Velocity", shooterMotorTwo.getVelocity().getValueAsDouble());
    SmartDashboard.putNumber("Shooter Aim Motor Current", shooterAimMotor.getStatorCurrent().getValueAsDouble());
  }


  public static void shooterMotorConfig() {
    shooterMotorOne.setNeutralMode(NeutralModeValue.Coast);
    shooterMotorTwo.setNeutralMode(NeutralModeValue.Coast);
    shooterAimMotor.setNeutralMode(NeutralModeValue.Brake);

    shooterMotorOneConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    shooterMotorTwoConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    shooterAimMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    shooterMotorOneConfig.Slot0.kV = 0.01;
    shooterMotorTwoConfig.Slot0.kV = 0.01;
    shooterMotorOneConfig.Slot0.kS = 0;
    shooterMotorTwoConfig.Slot0.kS = 0;
    shooterMotorOneConfig.Slot0.kA = 0;
    shooterMotorTwoConfig.Slot0.kA = 0;

    shooterAimMotorConfig.Slot0.kP = 0.01;
    shooterAimMotorConfig.Slot0.kI = 0;
    shooterAimMotorConfig.Slot0.kD = 0;

    shooterMotorOne.getConfigurator().apply(shooterMotorOneConfig);
    shooterMotorTwo.getConfigurator().apply(shooterMotorTwoConfig);
    shooterAimMotor.getConfigurator().apply(shooterAimMotorConfig);
  }
}
