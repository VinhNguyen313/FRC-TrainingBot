/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class PIDControllerDrive extends Command {
  double inches;

  public PIDControllerDrive(double inches) {
    requires(Robot.drive);
    this.inches = inches/RobotMap.Constants.InchesPerTick;
  }

  // Called just before this Command runs the first time
  @Override
  //raw sensor ticks
  protected void initialize() {
    Robot.drive.zeroCurrentPosition(Hand.kRight);
    Robot.drive.zeroCurrentPosition(Hand.kLeft);
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drive.pidController.setSetpoint(inches);
    Robot.drive.pidController.enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.drive.pidController.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.pidController.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
