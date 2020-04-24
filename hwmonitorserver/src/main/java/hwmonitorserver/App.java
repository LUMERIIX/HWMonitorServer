package hwmonitorserver;

import java.util.Arrays;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.FormatUtil;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu;
        //String cpuModel = cpu.getModel();
        System.out.println(si.getOperatingSystem());
        System.out.println(hal.getProcessor().getName());
        long[] table = hal.getProcessor().getCurrentFreq();
        double[] doubletable = new double[table.length];
        for (int i = 0; i < table.length; i++) {
            doubletable[i] = (double) table[i] / 1000000000.0;
        }
        System.out.println(Arrays.toString(doubletable));
        System.out.println("Memory: " + FormatUtil.formatBytes(hal.getMemory().getAvailable()) + "/" + FormatUtil.formatBytes(hal.getMemory().getTotal())); //Mempry usage
        System.out.println("CPU temp: " + hal.getSensors().getCpuTemperature());
        int[] FanSpeeds = hal.getSensors().getFanSpeeds();
        System.out.println("Fan Speeds: " + Arrays.toString(FanSpeeds));
    }
}
