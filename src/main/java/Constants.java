import org.opencv.core.Scalar;

public class Constants {
    public static final Scalar BLUE = new Scalar(255, 0, 0);
    public static final Scalar GREEN = new Scalar(0, 255, 0);
    public static final Scalar RED = new Scalar(0, 0, 255);
    public static final Scalar YELLOW = new Scalar(0, 255, 255);
    public static final Scalar ORANGE = new Scalar(0, 165, 255);
    public static final Scalar MAGENTA = new Scalar(255, 0, 255);

    public static final double FOCAL_LENGTH = 740; // In pixels, needs tuning if res is changed
    public static final double HFOV = 61;
    public static final double VFOV = 34.3;

    public static final double METERS_PER_INCH = 0.0254;
}