package hwmonitorserver;

public class Hardware {

    class CPU
    {
        String Name;
        double[] Clock;
        int Cores;
        float Temp;

    }

    class GPU
    {
        String Name;
        double MemoryClock;
        int CoreClock;
        int CoreLoad;
        int UsedMemory;
        int AvailableMemory;
        float Temperature;
        int FanSpeed;
    }

    class MotherBoard
    {
        String Name;
        float[] Temperature;
    }

    class RAM
    {
        int UsedMemory;
        int AvailableMemory;
        float Temperature;  
    }

    class Disk
    {
        int UsedMemory;
        int AvailableMemory;
        float Temperature;
    }

    class FAN
    {
        int RPM;
    }

}