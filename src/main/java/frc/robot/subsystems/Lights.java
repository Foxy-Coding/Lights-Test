// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.LYNKutil.Shift;

public class Lights extends SubsystemBase {

  private static final int iPort = 9;
  private static final int iLength = 120;

  private static final Distance distLightSpacing = Meters.of(1 / 120.0);

  private final AddressableLED objLights;
  private final AddressableLEDBuffer objBuffer;
  private Shift objShift;
  
  /** Creates a new Lights. */
  public Lights() {
    objLights = new AddressableLED(iPort);
    objBuffer = new AddressableLEDBuffer(iLength);

    objLights.setLength(iLength);
    objLights.start();



    setDefaultCommand(matchTimeProgress());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    objLights.setData(objBuffer);
  }

  public Command runPattern(LEDPattern pattern){
    return run(() -> pattern.applyTo(objBuffer));
  }

  public Command matchTimeProgress(){
    LEDPattern pattern = LEDPattern.progressMaskLayer(() -> DriverStation.getMatchTime());

    return run(() ->
      pattern.applyTo(objBuffer)
    );
  }

  public Command shiftProgress(){
    LEDPattern pattern;
    if (objShift.isNextActive) {
      pattern = LEDPattern.gradient(LEDPattern.GradientType.kContinuous, Color.kGreen, Color.kBlack).
      scrollAtAbsoluteSpeed(MetersPerSecond.of(1), distLightSpacing);
    } else if (Shift.SHIFT_1 != null) {
      
    }
  
  }
}
