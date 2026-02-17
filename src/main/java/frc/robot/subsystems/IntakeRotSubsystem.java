// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.intakeConstants;

public class IntakeRotSubsystem extends SubsystemBase {
  /** Creates a new IntakeRotSubsystem. */
  public static TalonFX intakeRotationMotor = new TalonFX(intakeConstants.intakeRotationMotorID);

  public static TalonFXConfiguration intakeRotationMotorConfig = new TalonFXConfiguration();

  public static PositionDutyCycle intakeRotatoinDutyCycle = new PositionDutyCycle(0);

  public IntakeRotSubsystem() {
    intakeRotationConfig();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void rotateIntake(double speed) {
    intakeRotationMotor.set(speed);
  }

  public static void intakeSetPoint(double position) {
    intakeRotationMotor.setControl(intakeRotatoinDutyCycle.withPosition(position));
  }

  public static void intakeRotationConfig() {
    intakeRotationMotor.setPosition(0);

    intakeRotationMotor.setNeutralMode(NeutralModeValue.Coast);

    intakeRotationMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    intakeRotationMotorConfig.Slot0.kP = 0;
    intakeRotationMotorConfig.Slot0.kI = 0;
    intakeRotationMotorConfig.Slot0.kD = 0;

    intakeRotationMotor.getConfigurator().apply(intakeRotationMotorConfig);
  }
}
