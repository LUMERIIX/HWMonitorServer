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

    class DISK
    {
        int UsedMemory;
        int AvailableMemory;
        float Temperature;
    }

    class FAN
    {
        int RPM;
    }

    public CPU cpu;
    public GPU gpu;
    public MotherBoard mb;
    public RAM ram; 
    public DISK disk;
    public FAN fan;

    public Hardware ()
    {
        CPU cpu = new CPU();
        GPU gpu = new GPU();
        MotherBoard mb = new MotherBoard();
        RAM ram = new RAM(); 
        DISK disk = new DISK();
        FAN fan = new FAN();
    }

}