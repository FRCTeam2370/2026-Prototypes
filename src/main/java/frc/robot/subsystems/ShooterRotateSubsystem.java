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

public class ShooterRotateSubsystem extends SubsystemBase {
  /** Creates a new ShooterRotateSubsystem. */
  public static TalonFX shooterRotateMotor = new TalonFX(shooterConstants.shooterRotateMotorID);

  public static TalonFXConfiguration shooterRotateConfig = new TalonFXConfiguration();

  public static MotionMagicDutyCycle shooterPositionDutyCycle = new MotionMagicDutyCycle(0);


  public ShooterRotateSubsystem() {
    shooterRotateConfiguration();
  }

  public static void shooterRotate(double position) {
    shooterRotateMotor.setControl(shooterPositionDutyCycle.withPosition(position));
  }

  public static void shooterManualRotate(double speed) {
    shooterRotateMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Shooter Rotation Pos", shooterRotateMotor.getPosition().getValueAsDouble());
    SmartDashboard.putNumber("Shooter Rotate Motor Current", shooterRotateMotor.getStatorCurrent().getValueAsDouble());
  }

  public static void shooterRotateConfiguration() {
    shooterRotateMotor.setPosition(0);

    shooterRotateMotor.setNeutralMode(NeutralModeValue.Coast);

    shooterRotateConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    shooterRotateConfig.Slot0.kP = 0.31;
    shooterRotateConfig.Slot0.kI = 0.02;
    shooterRotateConfig.Slot0.kD = 0.015;

    shooterRotateConfig.MotionMagic.MotionMagicAcceleration = 100;
    shooterRotateConfig.MotionMagic.MotionMagicCruiseVelocity = 60;

    shooterRotateMotor.getConfigurator().apply(shooterRotateConfig);
  }
}
