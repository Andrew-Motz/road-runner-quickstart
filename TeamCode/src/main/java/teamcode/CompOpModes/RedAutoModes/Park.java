package teamcode.CompOpModes.RedAutoModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import teamcode.CompOpModes.TeleopAndRobotFiles.TauBot;
import teamcode.roadrunner.hardware.SampleMecanumDrive;

@Autonomous(name = "Park")

public class Park extends LinearOpMode {

    private SampleMecanumDrive r;
    private TauBot robot;
    double   rightHookUp    = 0.376;
    double   rightHookDown  = 0;
    double   leftHookUp     = 0.34;
    double   leftHookDown   = 0.69;
    boolean  colelctJam     = false;


    public void runOpMode() throws InterruptedException {
        r = new SampleMecanumDrive(hardwareMap);
        robot = new TauBot();

        Trajectory trajectory = r.trajectoryBuilder(new Pose2d()).forward(35).build();
        robot.initAuto(hardwareMap);
        waitForStart();


        if (isStopRequested()) return;

        r.followTrajectory(
                r.trajectoryBuilder(new Pose2d())
                        .forward(8)
                .build()
        );


    }

    public void intake(String direction, double speed){
        if (direction == "in") {
            robot.leftIntake.setPower(speed);
            robot.rightIntake.setPower(speed);
        }
        if (direction == "out"){
            robot.leftIntake.setPower(speed * -1);
            robot.rightIntake.setPower(speed * -1);
        }
        if (direction == "off"){
            robot.leftIntake.setPower(0);
            robot.rightIntake.setPower(0);
        }
    }

    public void hooksDown(){
        robot.leftHook.setPosition(leftHookDown);
        robot.rightHook.setPosition(rightHookDown);
    }

    public void hooksUp(){
        robot.leftHook.setPosition(leftHookUp);
        robot.rightHook.setPosition(rightHookUp);
    }
}
