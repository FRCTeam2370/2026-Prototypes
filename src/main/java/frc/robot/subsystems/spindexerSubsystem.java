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

public class spindexerSubsystem extends SubsystemBase {
  public static TalonFX spindexerMotor = new TalonFX(Constants.spindexerConstants.spindexerMotorID);

  public static TalonFXConfiguration spindexerMotorConfig = new TalonFXConfiguration();

  /** Creates a new spindexerSubsystem. */
  public spindexerSubsystem() {
    spindexerConfiguration();
  }

  public static void controlSpindexer(double speed){
    spindexerMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Spindexer  Motor Current", spindexerMotor.getStatorCurrent().getValueAsDouble());
  }

  public static void spindexerConfiguration() {
    spindexerMotor.setNeutralMode(NeutralModeValue.Coast);

    spindexerMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    spindexerMotor.getConfigurator().apply(spindexerMotorConfig);
  }
}
