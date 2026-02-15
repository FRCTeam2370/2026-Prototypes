// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class LED_SubSystem extends SubsystemBase {
  public static DigitalOutput output1 = new DigitalOutput(0);
  public static DigitalOutput output2 = new DigitalOutput(1);
  public static DigitalOutput output3 = new DigitalOutput(2);
  /** Creates a new LED_SubSystem. */
  public LED_SubSystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Output 1", output1.get());
    SmartDashboard.putBoolean("Output 2", output2.get());
    SmartDashboard.putBoolean("Output 3", output3.get());

  }

  public static void idleAnim() {
      output1.set(false);
      output2.set(false);
      output3.set(false);
    }

  public static void targetingAnim() {
    output1.set(true);
    output2.set(false);
    output3.set(false);
  }

  public static void unableToShootAnim() {
    output1.set(false);
    output2.set(true);
    output3.set(false);
  }

  public static void ableToShootAnim() {
    output1.set(true);
    output2.set(true);
    output3.set(false);
  }

}
