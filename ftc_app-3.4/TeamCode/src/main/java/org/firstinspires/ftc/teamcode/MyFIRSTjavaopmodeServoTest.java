package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by space cookies ftc on 10/14/2017.
 */
@TeleOp
public class MyFIRSTjavaopmodeServoTest extends LinearOpMode {
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private Servo servo1;

    @Override
    public void runOpMode () {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        servo1 = hardwareMap.get(Servo.class, "servo1");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //Wait for game to start (driver presses PLAY)
        waitForStart();

        //run until end of match (driver presses STOP)
        double tgtpower = 0;
        double armpower = 0;
        while (opModeIsActive()) {
            tgtpower = -this.gamepad1.left_stick_y;
            motor1.setPower(tgtpower);
            motor2.setPower(tgtpower);
            armpower = -this.gamepad2.left_stick_y;
            motor3.setPower(armpower);
            // check to see if we need to move the servo. (miceland!!!!!!!!)r
            if (gamepad1.y){
                //move to zero degrees
                servo1.setPosition(0);

            } else if (gamepad1.x || gamepad1.b){
                //move to 90 degrees
                servo1.setPosition(0.5);

            } else if (gamepad1.a ) {
                // move to 180 degrees.
                servo1.setPosition(1);




            }



            telemetry.addData("Servo Position", servo1.getPosition());
            telemetry.addData("Target Power", tgtpower);
            telemetry.addData("Motor1 Power", motor1.getPower());
            telemetry.addData("Motor2 Power", motor2.getPower());
            telemetry.addData("Motor3 Power", motor3.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update ();


        }
    }
}