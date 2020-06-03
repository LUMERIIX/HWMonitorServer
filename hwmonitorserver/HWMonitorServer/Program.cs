using System;
using LibreHardwareMonitor.Hardware;
using LibreHardwareMonitor.Interop;
using LibreHardwareMonitor.Software;

using System.Text.Json;
using System.Text.Json.Serialization;
using System.Collections.Generic;
using System.Threading;

namespace HWMonitorServer
{

  public class System
  {
    public string Name { get; set; }

    public List<Hardware> Components { get; set; }

  }
  public class Component
  {
    public string Name { get; set; }

    public List<Sensor> Sensors { get; set; }

  }

  public class Sensor
  {
    public string Name { get; set; }

    public List<Child> SensorNodes;
  }

  public class Child
  {
    public string Name { get; set; }

    public float Value { get; set; }

    public float Min { get; set; }

    public float Max { get; set; }

  }

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

            System sys = new System();

            while(true)
            {
                computer.Open();
                sys.Name = "PC";
                foreach (var hw in computer.Hardware)
                {
                  Component comp = new Component(); 
                  Console.WriteLine("Hardware: {0}", hw.Name);

                  foreach (var sensor in hw.Sensors)
                    {
                      Console.WriteLine("Sensor: {0}, type: {1}, value: {2}, min: {3}, max: {4}", sensor.Name, sensor.SensorType, sensor.Value, sensor.Min, sensor.Max);
                      Console.WriteLine("=======");
                    }

                sys.Components.Add(comp);
                  Console.WriteLine("=======");
                  Console.WriteLine("=======");
                }
                  computer.Close();
                  Thread.Sleep(1000);
      }
    }
  }
}
