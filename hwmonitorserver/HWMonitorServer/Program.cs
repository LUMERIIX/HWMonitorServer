using System;
using LibreHardwareMonitor.Hardware;
using LibreHardwareMonitor.Interop;
using LibreHardwareMonitor.Software;

namespace HWMonitorServer
{
    class Program
    {

        static void Main(string[] args)
        {
          Computer computer = new Computer();
          computer.Open();

        foreach(Hardware hw in computer.Hardware)
        {
          
        }

          Console.WriteLine("Hello World!");
        }
    }
}
