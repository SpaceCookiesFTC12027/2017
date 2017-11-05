package org.firstinspires.ftc.teamcode;

import android.graphics.Color;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;


/**
 * Created by space cookies ftc on 11/4/2017.
 */

@Autonomous
public class myFIRSTAutonomousjavaopmode extends LinearOpMode {
    private ColorSensor colorsensor;
    private DcMotor motor1;
    private DcMotor motor2;
    HardwarePushbot         robot   = new HardwarePushbot();
    private ElapsedTime runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 0.6;
    //static final double     TURN_SPEED    = 0.5;   //sets turn speed

    @Override
    public void runOpMode() {
        telemetry.addData("Elapsed Time", runtime);
        colorsensor = hardwareMap.get(ColorSensor.class, "color sensor");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        final double SCALE_FACTOR = 250;
        float hsvValues[] = {0F, 0F, 0F};

        waitForStart();
        //new: makes robot go forward 3 seconds
        motor1.setPower(FORWARD_SPEED);
        motor2.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //end new
        while (opModeIsActive()) {
            Color.RGBToHSV((int) (colorsensor.red() * SCALE_FACTOR),
                    (int) (colorsensor.green() * SCALE_FACTOR),
                    (int) (colorsensor.blue() * SCALE_FACTOR),
                    hsvValues);

            telemetry.addData("Alpha", colorsensor.alpha());
            telemetry.addData("Red", colorsensor.red());
            telemetry.addData("Green", colorsensor.green());
            telemetry.addData("Blue", colorsensor.blue());
            telemetry.update();



        }

    }
}






