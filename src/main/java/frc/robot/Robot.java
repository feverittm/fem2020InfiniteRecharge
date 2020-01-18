package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.commands.*;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  private ArrayList<String> commandList;

  public long cycles = 0;
  public final boolean verbose = false; //debug variable, set to true for ALL THE DATA

  private Command m_autonomousCommand;
  public static OI oi;
  Command autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  @Override
  public void robotInit() {
    CommandScheduler.getInstance().cancelAll();
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_chooser.addOption("Do Nothing", new AutoDoNothing());
    

    Hopper.getInstance();
    DriveTrain.getInstance().setDefaultCommand(new ArcadeDrive());
    OI.getInstance();
    Climber.getInstance();

    commandList = new ArrayList<String>();
    if (verbose) {
      CommandScheduler.getInstance().onCommandExecute(command -> {
        commandList.add(command.getName());
      });
    }
  }

  @Override
  public void robotPeriodic() {
    if (verbose) {
      for (String cmdName : commandList) {
        System.out.println("Ran " + cmdName + " on cycle " + cycles);
      }
    }
    commandList.clear();
    cycles++;
  }

  @Override
  public void disabledInit() { }

  @Override
  public void disabledPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
