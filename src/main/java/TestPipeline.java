import java.util.ArrayList;
import java.util.Arrays;

import org.opencv.core.Core;
import org.opencv.core.CvException;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import edu.wpi.first.vision.VisionPipeline;

/**
 * 
 * Vision Pipeline for 2020 season
 *
 */
public class TestPipeline implements VisionPipeline {

    private Mat dst;
    private Mat bitmask;

    private MatOfPoint target;
    private double cx;
    private double cy;

    public void process(Mat src) {
        if (src.empty()) {
            return;
        }

        target = null;

        // dst = new Mat();
        // src.copyTo(dst);
        dst = src;

        mask();

        getTarget();

        if (target == null) {
            return;
        }

        getCenter();
    }

    private void mask() {
        // Filter in bright objects
        bitmask = new Mat();
        Core.inRange(dst, new Scalar(50, 50, 50), new Scalar(255, 255, 255), bitmask);
    }

    private void getTarget() {
        // Find all external contours
        ArrayList<MatOfPoint> contours = new ArrayList<>();
        try {
            Imgproc.findContours(bitmask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        } catch (CvException e) {
            // Sometimes the Mat format gets messed up when switching cameras
            System.out.println(e.getMessage());
        }

        // Save the largest contour
        double largest = 0;

        for (MatOfPoint contour : contours) {
            Imgproc.drawContours(dst, Arrays.asList(contour), -1, Constants.RED);

            double area = Imgproc.contourArea(contour);

            if (area > largest) {
                target = contour;
                largest = area;
            }
        }
    }

    private void getCenter() {
        Moments m = Imgproc.moments(target);
        cx = m.m10 / m.m00;
        cy = m.m01 / m.m00;
        Imgproc.drawMarker(dst, new Point(cx, cy), Constants.BLUE);
    }

    public double getTheta() {
        if (target == null | dst == null) {
            return 0;
        }

        return (0.5 - cx / dst.width()) * Constants.HFOV;
    }

    public double getPhi() {
        if (target == null | dst == null) {
            return 0;
        }

        return (0.5 - cy / dst.height()) * Constants.VFOV;
    }

    public Mat getDst() {
        return dst;
    }

}