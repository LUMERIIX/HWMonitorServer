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
        public ArrayList <Double> Power;
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
        public double Power;

        public GPU()
        {
            Name = new String();
        }

    }

    class MotherBoard
    {
        public String Name;
        public ArrayList<Float> Temperature;
        public double CoreVoltage;
        public double BatteryVoltage;
        public double Voltage3V3;

        public MotherBoard()
        {
            Name = new String();
            Temperature  = new ArrayList <Float>();
        }
    }

    class RAM
    {
        public String Name;
        public int UsedMemory;
        public int AvailableMemory;
        public float Temperature;  

        public RAM()
        {
        }
    }

    class DISK
    {
        public String Name;
        public int UsedMemory;
        public int AvailableMemory;
        public float Temperature;
        public float ReadRate;
        public float WriteRate;

        public DISK()
        {
        }
    }

    class NetworkInterface
    {
        public String Name;
        public double UploadSpeed;
        public double DownloadSpeed;
        public double UploadedData;
        public double DownloadedData;

        public NetworkInterface()
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