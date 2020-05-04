package hwmonitorserver;

public class Hardware {
    class CPU
    {
        public String Name;
        public double[] Clock;
        public double[] Load;
        public int Cores;
        public float Temp;
        public CPU()
        {
            Name = new String();
            Clock = new double[]{};
            Load = new double[]{};
        }
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
        cpu = new CPU();
        gpu = new GPU();
        mb = new MotherBoard();
        ram = new RAM(); 
        disk = new DISK();
        fan = new FAN();
    }

}