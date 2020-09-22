import java.io.IOException;

public class StreamLimiter {
    // ANSI Codes for ~~~color~~~
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    // ~~~ end ANSI codes ~~~

    private static boolean setupSuccessful = true;
    public static String tautulliAPI = "";
    public static String tautulliIP = "";
    public static String tautulliPORT = "";
    public static String filePath = "";
    public static String killStreamMessage = "";
    public static int scanRate = 30;

    public static void main(String[] args) throws Exception {
        System.out.println(WHITE + "Loading configuration");
        tautulliAPI = Configurations.readFile("tautulli_APIKEY");
        tautulliIP = Configurations.readFile("tautulli_IP");
        tautulliPORT = Configurations.readFile("tautulli_PORT");
        filePath = Configurations.readFile("data_dir");
        killStreamMessage = Configurations.readFile("kill_Stream_Message");
        scanRate = Integer.valueOf(Configurations.readFile("scan_Rate"));

        if (tautulliAPI.equals("REPLACEME")) {
            System.out.println(RED + "API Key not set in configuration file!!");
            setupSuccessful = false;
        }
        if (tautulliIP.equals("REPLACEME")) {
            System.out.println(RED + "Tautulli IP not set in configuration file!!");
            setupSuccessful = false;
        }
        if (tautulliPORT.equals("REPLACEME")) {
            System.out.println(RED + "Tautulli Port not set in configuration file!!");
            setupSuccessful = false;
        }
        if (setupSuccessful) {
            System.out.println(GREEN + "Configurations loaded successfully, starting worker.");
        }
        while (setupSuccessful) {
            TautulliAPI.worker();
            Thread.sleep(scanRate * 1000);
        }
    }

}
