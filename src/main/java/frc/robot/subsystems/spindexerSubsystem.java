// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class spindexerSubsystem extends SubsystemBase {
  public static TalonFX spindexerMotor = new TalonFX(Constants.spindexerConstants.spindexerMotorID);
  public static TalonFX spindexerExitMotor = new TalonFX(Constants.spindexerConstants.spindexerExitMotorID);

  public static TalonFXConfiguration spindexerMotorConfig = new TalonFXConfiguration();
  public static TalonFXConfiguration spindexerExitMotorConfig = new TalonFXConfiguration();

  /** Creates a new spindexerSubsystem. */
  public spindexerSubsystem() {
    spindexerConfiguration();
  }
  
  public static void controlSpindexerExit(double speed) {
    spindexerExitMotor.set(speed);
  }

  public static void controlSpindexer(double speed){
    spindexerMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void spindexerConfiguration() {
    spindexerMotor.setNeutralMode(NeutralModeValue.Coast);
    spindexerExitMotor.setNeutralMode(NeutralModeValue.Coast);

    spindexerMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    spindexerExitMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    spindexerMotor.getConfigurator().apply(spindexerMotorConfig);
    spindexerExitMotor.getConfigurator().apply(spindexerExitMotorConfig);
  }
}
