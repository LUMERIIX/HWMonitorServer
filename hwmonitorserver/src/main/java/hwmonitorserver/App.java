package hwmonitorserver;

import java.util.Arrays;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

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
        //CentralProcessor.ProcessorIdentifier cpu = new CentralProcessor.ProcessorIdentifier();
        CentralProcessor cpu;
        //String cpuModel = cpu.getModel();
        System.out.println(si.getOperatingSystem());
        System.out.println(hal.getProcessor());
        long[] table = hal.getProcessor().getCurrentFreq();
        System.out.println(Arrays.toString(table));
        //for(table.length)

        /*System.out.println("Memory: " + FormatUtil.formatBytes(hal.getMemory().getAvailable())
        + "/" + FormatUtil.formatBytes(hal.getMemory().getTotal())); //Mempry usage*/
    }
}
