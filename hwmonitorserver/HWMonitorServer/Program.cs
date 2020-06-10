using System;
using LibreHardwareMonitor.Hardware;
using LibreHardwareMonitor.Interop;
using LibreHardwareMonitor.Software;

using System.Text.Json;
using System.Text.Json.Serialization;
using System.Collections.Generic;
using System.Threading;
using Newtonsoft.Json;

namespace HWMonitorServer
{

  public class System
  {
    public string Name { get; set; }

    public List<Component> Components { get; set; }

  }
  public class Component
  {
    public string Name { get; set; }

    public List<Sensor> Sensors { get; set; }

  }

  public class Sensor
  {
    public string Name { get; set; }

    public List<ChildNode> SensorNodes { get; set; }
  }

  public class ChildNode
  {
    public string Name { get; set; }

    public float Value { get; set; }

    public float Min { get; set; }

    public float Max { get; set; }

  }

  class Program
    {

        static List<SensorType> getSensorTypes (IHardware hw)
        {
          List<SensorType> list = new List<SensorType>();
          foreach (var sensor in hw.Sensors)
          {
            if (list.Contains(sensor.SensorType));
            else
              list.Add(sensor.SensorType);
          }
          return list;
    }

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
            sys.Components = new List<Component>();
            sys.Name = "PC";

      while (true)
            {
                computer.Open();

        foreach (var hw in computer.Hardware)
                {
                  Component component = new Component();
                  component.Name = hw.Name;
                  List<SensorType> sensTypes = new List<SensorType>();
                  sensTypes = getSensorTypes(hw);

                  component.Sensors = new List<Sensor>();

                foreach (var sensorType in sensTypes)
                    {
                      Sensor sens = new Sensor();
                      sens.Name = sensorType.ToString();
                      sens.SensorNodes = new List<ChildNode>();
                      foreach (var sensor in hw.Sensors)
                      {
                          if(sensor.SensorType == sensorType)
                          {
                            ChildNode node = new ChildNode();
                            node.Name = sensorType.ToString() + " " + sensor.Name;
                            node.Value = sensor.Value.HasValue ? sensor.Value.Value : default(float);
                            node.Min = sensor.Min.HasValue ? sensor.Min.Value : default(float);
                            node.Max = sensor.Max.HasValue ? sensor.Max.Value : default(float);
                            sens.SensorNodes.Add(node);
                          }
                      }
                      component.Sensors.Add(sens);
                    }

                  sys.Components.Add(component);
                  }
        String JsonString = JsonConvert.SerializeObject(sys);
        Console.WriteLine("{0}", JsonString);
        computer.Close();
        Thread.Sleep(10000);
      }
    }
  }
}
