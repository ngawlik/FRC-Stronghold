package org.usfirst.frc.team2856.robot;

//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;

//import edu.wpi.cscore.AxisCamera;
//import edu.wpi.cscore.CvSink;
//import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;

public class VisionSys {
//	private Thread visionThread;
	
	public VisionSys() {
		CameraServer.getInstance().addAxisCamera("axis-camera.local");
//		visionThread = new Thread(() -> {
//			// Get the Axis camera from CameraServer
//			AxisCamera camera = CameraServer.getInstance().addAxisCamera("axis-camera.local");
//			// Set the resolution
//			camera.setResolution(320, 240);
//
//			// Get a CvSink. This will capture Mats from the camera
//			CvSink cvSink = CameraServer.getInstance().getVideo();
//			// Setup a CvSource. This will send images back to the Dashboard
//			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 320, 240);
//
//			// Mats are very memory expensive. Lets reuse this Mat.
//			Mat mat = new Mat();
//
//			// This cannot be 'true'. The program will never exit if it is. This
//			// lets the robot stop this thread when restarting robot code or
//			// deploying.
//			while (!Thread.interrupted()) {
//				// Tell the CvSink to grab a frame from the camera and put it
//				// in the source mat.  If there is an error notify the output.
//				if (cvSink.grabFrame(mat) == 0) {
//					// Send the output the error.
//					outputStream.notifyError(cvSink.getError());
//					// skip the rest of the current iteration
//					continue;
//				}
//				// Put a rectangle on the image
//				Imgproc.rectangle(mat, new Point(80, 60), new Point(240, 180),
//						new Scalar(0, 255, 0), 4);
//				// Give the output stream a new image to display
//				outputStream.putFrame(mat);
//			}
//		});
//		visionThread.setDaemon(true);
//		visionThread.start();		
	}
	
	public void placeholder() {
		
	}
}
