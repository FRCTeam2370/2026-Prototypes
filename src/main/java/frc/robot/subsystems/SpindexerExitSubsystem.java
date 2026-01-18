// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SpindexerExitSubsystem extends SubsystemBase {
  /** Creates a new SpindexerExitSubsystem. */
  public static TalonFX spindexerExitMotor = new TalonFX(Constants.spindexerConstants.spindexerExitMotorID);

  public static TalonFXConfiguration spindexerExitMotorConfig = new TalonFXConfiguration();

  public SpindexerExitSubsystem() {}

  public static void controlSpindexerExit(double speed) {
    spindexerExitMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Spindexer Exit Motor Current", spindexerExitMotor.getStatorCurrent().getValueAsDouble());
  }

  public static void spindexerExitConfiguration() {
    spindexerExitMotor.setNeutralMode(NeutralModeValue.Coast);

    spindexerExitMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    spindexerExitMotor.getConfigurator().apply(spindexerExitMotorConfig);
  }
}
