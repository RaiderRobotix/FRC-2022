package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Vision {

  private static volatile Vision instance = null;

  private final CameraServer cameraServer;
  private final UsbCamera camera;

  private final int img_height = 180;
  private final int img_width = 320;
  private final int fps = 30;

  private Vision() {
    cameraServer = CameraServer.getInstance();
    camera = cameraServer.startAutomaticCapture("FrontCam", 0);

    camera.setResolution(img_width, img_height);
    camera.setFPS(fps);
  }

  /**
   * @return The Vision singleton.
   */
  public static Vision getInstance() {
    if (instance == null) { // Thread safety
      synchronized (Vision.class) { // Multiple threads can reach here
        if (instance == null) {
          instance = new Vision();
        }
      }
    }
    return instance;
  }
}
