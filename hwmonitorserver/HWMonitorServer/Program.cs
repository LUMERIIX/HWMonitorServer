using System;
using LibreHardwareMonitor.Hardware;
using LibreHardwareMonitor.Interop;
using LibreHardwareMonitor.Software;

using System.Text.Json;
using System.Text.Json.Serialization;

namespace HWMonitorServer
{
    class Program
    {

        static void Main(string[] args)
        {
          var computer = new Computer
            {
              IsCpuEnabled = true,
              IsGpuEnabled = true,
              IsMemoryEnabled = true,
              IsMotherboardEnabled = true,
              //IsControllerEnabled = true,
              IsNetworkEnabled = true,
              IsStorageEnabled = true
            };

            while(true)
            {
                computer.Open();

                foreach(var hw in computer.Hardware)
                {
                  Console.WriteLine("Hardware: {0}", hw.Name);

                  foreach (var sensor in hw.Sensors)
                    {
                      Console.WriteLine("Sensor: {0}, type: {1}, value: {2}, min: {3}, max: {4}", sensor.Name, sensor.SensorType, sensor.Value, sensor.Min, sensor.Max);
                      Console.WriteLine("=======");
                    }
                  Console.WriteLine("=======");
                  Console.WriteLine("=======");

        } 
                  computer.Close();
                  System.Threading.Thread.Sleep(1000);
      }
    }
  }
}
