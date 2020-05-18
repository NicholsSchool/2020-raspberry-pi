import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class Tester {

    // Replace this with your installation path
    public static final String OPENCV_INSTALLATON = "C:\\Users\\Junqi\\Documents\\opencv\\build\\java\\x64\\opencv_java401.dll";

	public static void main(String[] args) {
		System.load(OPENCV_INSTALLATON);
		
		// Replace this String with the path to your vision image
		Mat img = Imgcodecs.imread(System.getProperty("user.dir") + "\\example images\\BlueGoal-084in-Center.jpg");
		
		InfiniteRechargePipeline p = new InfiniteRechargePipeline();

		
		p.process(img);

		HighGui.imshow("src", img);
		HighGui.imshow("dst", p.getDst());
		
		// System.out.println("translation vector: " + p.getTVec());
				
		HighGui.waitKey();
	}

	
}
