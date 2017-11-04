package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by space cookies ftc on 10/14/2017.
 */
@TeleOp
public class MyFIRSTjavaopmodeleftandright extends LinearOpMode {
    private DcMotor motor1;
    private DcMotor motor2; /*another test*/
    /*
    Each private variable (gyroscope, dcmotor, diital channel, distance sensor, and servo) is for
    everything we configured. However we didn't use some of these things but this is for practicing/
    examples. TeleOp (at the beginning) indicates that this was written for a driver controlled period,
    but if we wanted to change it to the hands off section for the robot we would change that to @Autonomous
     */

    @Override
    public void runOpMode () {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //Wait for game to start (driver presses PLAY)
        waitForStart();

        /*
        the overridden runOpMode method happens with every OpMode using the LinearOpMode type.
        hardwareMap is an object that references the hardware listed above (private variables).
        The hardwareMap.get method matches the  name of the device used in the configuration, so
        we would have to change the names (ex. motorTest to Motor 1 or Motor 2 (or something else))
        if not, the opMode won't recognize the device

        in the second half of this section, it uses something called telemetry. In the first and
        second lines it sends a message to the driver station saying ("Status", "Initialized") and
        then it prompts the driver to press start and waits. All linear functions should have a wait
        for start command so that the robot doesn't start executing the commands before the driver
        wants to (or pushes the start button)
        */

        //run until end of match (driver presses STOP)
        double leftpower = 0;
        double rightpower = 0;
        while (opModeIsActive()) {
            leftpower = -this.gamepad1.left_stick_y;
            rightpower = -this.gamepad1.right_stick_y;
            motor1.setPower(leftpower);
            motor2.setPower(rightpower);
            telemetry.addData("Motor1 Power", motor1.getPower());
            telemetry.addData("Motor2 Power", motor2.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update ();

        /*
        After the driver presses start,the opMode enters a while loop until the driver presses stop,
        while the loop is running it will continue to send messages of ("Status", "Running") to the
        driver station
        */

        }
    }
}