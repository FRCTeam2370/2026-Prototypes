// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.spindexerConstants;

public class SpindexerExitSubsystem extends SubsystemBase {
  /** Creates a new SpindexerExitSubsystem. */
  public static TalonFX spindexerExitMotor = new TalonFX(spindexerConstants.spindexerExitMotorID);

  public static TalonFXConfiguration spindexerExitMotorConfig = new TalonFXConfiguration();
  
  public static VelocityDutyCycle spindexerExiDutyCycle = new VelocityDutyCycle(0);

  public SpindexerExitSubsystem() {
    spindexerExitConfiguration();
  }

  public static void exitWithVelocity(double speed) {
    if (speed != 0) {
      spindexerExitMotor.setControl(spindexerExiDutyCycle.withVelocity(speed));
    } else {
      spindexerExitMotor.set(speed);
    }
  }

  public static void exitWithoutVelocity(double speed) {
    spindexerExitMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Spindexer Exit Motor Current", spindexerExitMotor.getStatorCurrent().getValueAsDouble());
    SmartDashboard.putNumber("Spindexer Exit Velocity", spindexerExitMotor.getVelocity().getValueAsDouble());
  }

  public static void spindexerExitConfiguration() {
    spindexerExitMotor.setNeutralMode(NeutralModeValue.Coast);

    spindexerExitMotorConfig.CurrentLimits.StatorCurrentLimit = 40;

    spindexerExitMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    spindexerExitMotorConfig.Slot0.kV = 0.01;

    spindexerExitMotorConfig.Slot0.kP = 0.01;
    spindexerExitMotorConfig.Slot0.kI = 0;
    spindexerExitMotorConfig.Slot0.kD = 0;

    spindexerExitMotor.getConfigurator().apply(spindexerExitMotorConfig);
  }
}
