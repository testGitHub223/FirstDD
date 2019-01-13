import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.List;

public class App_003 {

    static MyBool isReady;
    static boolean nnnnnnnn = true;


    //private SetingsOfSendData setingsOfSendData1 = new SetingsOfSendData();
    //private Sender sender;

    static int delayTimeStartKeybord = 20;
    static int delayTimeStartMouse = 20;

    static int delayTimeKeybord = 15;
    static int delayTimeMouse = 15;

    private static SerialPort serialPort;


    public static void main(String[] args) {
        //Передаём в конструктор имя порта
        serialPort = new SerialPort("COM3");

        for (int i =0; i < 10; i++) {
            try {
                //Открываем порт
                serialPort.openPort();
                //Выставляем параметры
                serialPort.setParams(SerialPort.BAUDRATE_9600,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                //Включаем аппаратное управление потоком
                serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                        SerialPort.FLOWCONTROL_RTSCTS_OUT);
                //Устанавливаем ивент лисенер и маску
                serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
                //Отправляем запрос устройству


                Sender03 sender = new Sender03();
                Listener03 listener03 = new Listener03();

                System.out.println("starting ...");
                Thread.sleep(2000);
                System.out.println("start");

                for (int j = 0; j < 230400; j++) {
                    listener03.getData(serialPort);
                }

                serialPort.closePort();

            } catch (SerialPortException ex) {
                System.out.println(ex);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }








    private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            System.out.println("get: !");
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    //String data = serialPort.readString(event.getEventValue());
                    int[] tt1 = serialPort.readIntArray(2);
                    //int tt2 = serialPort.readBytes(1)[0];
                    //int tt = tt1<<8 + tt2;
                    //if (data.contains("S")){
                    //    System.out.print("!!!!!!!!!!!!!!!!!!!!!!" + data);
                    //    isReady.setMybool(true);
                    //}
                    //System.out.print(data);
                    System.out.println("get:" + tt1[0] + " " + tt1[1]);
                    if (tt1[0] == 251) {
                        nnnnnnnn = true;
                    }
                    //System.out.println(tt2);
                    //System.out.println(tt);
                    //И снова отправляем запрос
                    // serialPort.writeString("Get data");
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

}
