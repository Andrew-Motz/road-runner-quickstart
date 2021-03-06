package teamcode.TestFiles;



import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

@Config
public class SimpleLift {
    public static int MAX_LAYER = 9;
    // We separate these out to make changing them with FTCDashboard easier
    public static int LAYER_0 = 0;
    public static int LAYER_SHIFT = 5000;
    public static int UPPER_LAYERS_SHIFT = 0;
    public static int UPPER_LAYERS_START = 5;


    private DoubleMotorLift lift;
    public int layer;
    public int targetPosition;

    // Also initializes the DcMotor
    public SimpleLift(DoubleMotorLift lift, RevBlinkinLedDriver leds) {
        this.lift = lift;
        this.layer = 0;
        this.targetPosition = LAYER_0;
    }

    public void changeLayer(int addend) {
        if (addend + layer >= 0 || addend + layer <= MAX_LAYER) {
            layer += addend;
        }
        setLiftPositionFromLayer();
    }

    public void setLayer(int layer) {
        this.layer = layer;
        setLiftPositionFromLayer();
    }

    void setLiftPositionFromLayer() {
        targetPosition = LAYER_0 + layer * LAYER_SHIFT;
        if (layer >= UPPER_LAYERS_START) {
            targetPosition += UPPER_LAYERS_SHIFT;
        }
        lift.setTarget(targetPosition);
    }

    public void changePosition(int delta) {
        targetPosition += delta;
        lift.setTarget(targetPosition);
    }

    public void goToMin() {
        lift.setTarget(LAYER_0);
    }
}
