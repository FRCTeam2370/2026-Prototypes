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
import frc.robot.Constants;

public class UptakeSubsystem extends SubsystemBase {
  public static TalonFX uptakeMotor = new TalonFX(Constants.uptakeConstants.uptakeMotorID);

  public static TalonFXConfiguration uptakeConfig = new TalonFXConfiguration();

  public static VelocityDutyCycle uptakVelocityDutyCycle = new VelocityDutyCycle(0);

  /** Creates a new UptakeSubsystem. */
  public UptakeSubsystem() {
    uptakeConfiguration();
  }

  public static void uptakeWithVelocity(double speed) {
    uptakeMotor.setControl(uptakVelocityDutyCycle.withVelocity(speed));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Uptake Velocity", uptakeMotor.getVelocity().getValueAsDouble());
  }

  public static void uptakeConfiguration() {
    uptakeMotor.setNeutralMode(NeutralModeValue.Coast);

    uptakeConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    uptakeConfig.Slot0.kV = 0.01;
    uptakeConfig.Slot0.kS = 0;
    uptakeConfig.Slot0.kA = 0;

    uptakeConfig.Slot0.kP = 0.01;

    uptakeMotor.getConfigurator().apply(uptakeConfig);
  }
}
