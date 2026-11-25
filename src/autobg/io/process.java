package autobg.io;

import autobg.cli.cli;
import java.io.IOException;

//Class for running external processes, in this case swaybg and pkill
public class process {

  private static String wallpaperMode = "fill"; //Default mode
  public static void setWallpaperMode() {
    String mode = cli.getWallpaperMode();
    if (mode == null) return;
    for (String acceptedMode : new String[]{"stretch", "fit", "fill", "center", "tile"}) {
      if (mode.equals(acceptedMode)) wallpaperMode = mode;
      return;
    }
    stdout.error("The provided wallpaper mode "+mode+" is not supported!\nUse one of the following modes: stretch, fit, fill, center, tile\nDefaulting to fill");
  }

  
  //Runs swaybg in the background to set the wallpaper
  public static Process swaybg_setWallpaper(String wallpaperPath, Process previousInstance) {

    //Kill all previous swaybg instances and run the latest one
    if (previousInstance == null) {
      exec(true, "pkill", "-x", "swaybg");
      Process p = exec(false, "swaybg", "-m", wallpaperMode, "-i", wallpaperPath);
      return p;
    }
    //For cycling between multiple wallpapers, run the latest instance first and after a while kill the previous one
    //Makes it so the wallpaper change is seamless instead of resulting in a blank screen for a moment
    Process p = exec(false, "swaybg", "-m", wallpaperMode, "-i", wallpaperPath);
    try {Thread.sleep(300);}
    catch (InterruptedException e) {stdout.error("Thread was unexpectedly interrupted!");}
    previousInstance.destroy();
    return p;
  }
  
  private static Process exec(boolean awaitCompletion, String... args) {
    if (stdout.isDebugMode()) {stdout.printSeq("Executing process with arguments:", args);}
    try {
      var pb = new ProcessBuilder(args);
      Process p = pb.start();
      if (awaitCompletion) p.waitFor();
      return p;
    }
    catch (IOException e) {stdout.error("Error running process "+args[0]+"\nThe respective program might not be present in your system"); return null;}
    catch (InterruptedException e) {stdout.error("Error running process"+args[0]+"\nUnexpected program interruption!"); return null;}
  }
}
