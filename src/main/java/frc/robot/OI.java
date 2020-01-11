/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.shooter.SetTargetShooterSpeed;

/**
 * Add your docs here.
 */
public class OI {

  private static XboxController gamepad1;
  private static JoystickButton buttonA, buttonB;

  static {
    gamepad1 = new XboxController(0);

    buttonA = new JoystickButton(gamepad1, 1);
    buttonB = new JoystickButton(gamepad1, 2);

    buttonA.whenPressed(new SetTargetShooterSpeed(Constants.Values.SHOOTER_SPEED_PRIME));
    buttonB.whenPressed(new SetTargetShooterSpeed(0.0));
  }
}
