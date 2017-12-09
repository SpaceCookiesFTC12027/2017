
package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by space cookies ftc on 11/23/2017.
 */
@Autonomous
public class nAutoBluePosition2 extends LinearOpMode{
    private ColorSensor colorsensor;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private Servo servo1;
    private Servo servo2;
    private Servo servo3;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        colorsensor = hardwareMap.get(ColorSensor.class, "color sensor");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        servo3 = hardwareMap.get(Servo.class, "servo3");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        final double SCALE_FACTOR = 250;
        float hsvValues[] = {0F, 0F, 0F};
        servo1.setPosition(1);
        servo2.setPosition(0);

        waitForStart();
        //runtime.reset();
        boolean needs_set_position = true;
        boolean sensed_color = false;
        boolean blue = false;
        boolean red = false;
        boolean task_done = false;
        while (opModeIsActive()) {
            Color.RGBToHSV((int) (colorsensor.red() * SCALE_FACTOR),
                    (int) (colorsensor.green() * SCALE_FACTOR),
                    (int) (colorsensor.blue() * SCALE_FACTOR),
                    hsvValues);

            if (!task_done) {
                servo3.setPosition(1);

                runtime.reset();
                while (opModeIsActive() && !sensed_color && runtime.seconds() < 2.0) {
                    telemetry.addData("Time Elapsed", runtime.seconds());
                    telemetry.addData("Servo3 Position", servo3.getPosition());
                    telemetry.addData("Alpha", colorsensor.alpha());
                    telemetry.addData("Red", colorsensor.red());
                    telemetry.addData("Green", colorsensor.green());
                    telemetry.addData("Blue", colorsensor.blue());
                    telemetry.update();
                    blue = colorsensor.blue() > colorsensor.red();
                    red = colorsensor.blue() < colorsensor.red();
                }

                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.3) ){
                    motor3.setPower(0);
                    if (blue) {
                        //move forward to knock red jewel
                        motor1.setPower(0.2);
                        motor2.setPower(-0.5);
                    }
                }

                //drive to the base
                motor3.setPower(0);


                if (red) {

                    while (opModeIsActive() && (runtime.seconds() < 0.85)) {
                        motor1.setPower(-0.6);
                        motor2.setPower(0.6);
                    }
                    motor1.setPower(0);
                    motor2.setPower(0);


                }

                if (blue) {
                    servo3.setPosition(0.0);
                    while (opModeIsActive() && (runtime.seconds() <=0.85)) {
                        motor1.setPower(-0.7);
                        motor2.setPower(1);
                    }
                    motor1.setPower(0);
                    motor2.setPower(0);

                }

                runtime.reset();
                servo3.setPosition(0.0);
                while (opModeIsActive() && (runtime.seconds() < 0.7)) {
                    motor1.setPower(-1);
                    motor2.setPower(1);
                }
                motor1.setPower(0);
                motor2.setPower(0);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.6)) {
                    motor1.setPower(1);
                    motor2.setPower(1);
                }
                motor1.setPower(0);
                motor2.setPower(0);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.0)){
                    motor1.setPower(1);
                    motor2.setPower(-1);
                }
                motor1.setPower(0);
                motor2.setPower(0);
            }
            servo1.setPosition(0);
            servo2.setPosition(1);
            task_done=true; //all out task is done!

        }

    }
}
