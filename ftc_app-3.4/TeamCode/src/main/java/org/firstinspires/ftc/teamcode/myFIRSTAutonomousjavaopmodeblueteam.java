package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by space cookies ftc on 11/4/2017.
 */

@Autonomous
public class myFIRSTAutonomousjavaopmodeblueteam extends LinearOpMode {
    private ColorSensor colorsensor;
    private DcMotor motor1;
    private DcMotor motor2;
    private Servo servo3;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        colorsensor = hardwareMap.get(ColorSensor.class, "color sensor");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        servo3= hardwareMap.get(Servo.class, "servo3");
        final double SCALE_FACTOR = 250;
        float hsvValues[] = {0F, 0F, 0F};

        waitForStart();
        while (opModeIsActive()) {
            Color.RGBToHSV((int) (colorsensor.red() * SCALE_FACTOR),
                    (int) (colorsensor.green() * SCALE_FACTOR),
                    (int) (colorsensor.blue() * SCALE_FACTOR),
                    hsvValues);
            boolean blue= colorsensor.blue() > colorsensor.red();
            boolean red= colorsensor.blue() < colorsensor.red();

            servo3.setPosition(0.5);



            if (blue) {
                motor1.setPower(-1);
                motor2.setPower(1);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 10.0)) {
                    telemetry.addData("Time Elapsed", runtime.seconds());
                    telemetry.update();}
            } else if (red) {
                motor1.setPower(1);
                motor2.setPower(-1);
            } else {
                motor1.setPower(0);
                motor2.setPower(0);
            }

            servo3.setPosition(0);


            telemetry.addData("Servo3 Position", servo3.getPosition());
            telemetry.addData("Alpha", colorsensor.alpha());
            telemetry.addData("Red", colorsensor.red());
            telemetry.addData("Green", colorsensor.green());
            telemetry.addData("Blue", colorsensor.blue());
            telemetry.update();



        }

    }
}






