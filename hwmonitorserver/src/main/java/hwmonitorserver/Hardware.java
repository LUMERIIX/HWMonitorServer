package hwmonitorserver;

import java.util.*;

public class Hardware {
    class CPU
    {
        public String Name;
        public ArrayList <Double> Clock;
        public ArrayList <Double> Load;
        public int Cores;
        public float Temp;
        public CPU()
        {
            Name = new String();
            Clock = new ArrayList <Double>();
            Load = new ArrayList <Double>();
        }
    }

    class GPU
    {
        public String Name;
        public double MemoryClock;
        public int CoreClock;
        public double CoreLoad;
        public double UsedMemory;
        public double AvailableMemory;
        public double Temperature;
        public int FanSpeed;

        public GPU()
        {
            Name = new String();
        }

    }

    class MotherBoard
    {
        public String Name;
        public float[] Temperature;

        public MotherBoard()
        {
            Name = new String();
            Temperature  = new float[]{};
        }
    }

    class RAM
    {
        public int UsedMemory;
        public int AvailableMemory;
        public float Temperature;  

        public RAM()
        {
        }
    }

    class DISK
    {
        public int UsedMemory;
        public int AvailableMemory;
        public float Temperature;

        public DISK()
        {
        }
    }

    class FAN
    {
        public int RPM;

        public FAN()
        {
        }
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