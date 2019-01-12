import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.List;

public class App_001 {

    static MyBool isReady;
    static boolean nnnnnnnn = false;

    static int delayTimeStartKeybord =50;
    static int delayTimeStartMouse = 50;

    static int delayTimeKeybord = 15;
    static int delayTimeMouse = 15;

    private static SerialPort serialPort;



    public static void main(String[] args) {
        //Передаём в конструктор имя порта
        serialPort = new SerialPort("COM3");

        List<MyInt> tt = new ArrayList<MyInt>();

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

/*


            for (int i = 0; i < 57; i++) {

                Thread.sleep(10);
                if (isReady.isMybool()) {
                    //serialPort.writeString("KKLMNO");
                    serialPort.writeInt(75);
                    Thread.sleep(10);
                    serialPort.writeInt(76);
                    Thread.sleep(10);
                    serialPort.writeInt(40 + i);
                    Thread.sleep(10);
                    serialPort.writeInt(40 + i);
                    Thread.sleep(10);
                    serialPort.writeInt(40 + i);
                    Thread.sleep(10);
                    serialPort.writeInt(40 + i);
                    Thread.sleep(10);
                    //if (event.isRXCHAR() && event.getEventValue() > 0) {
                    //    tt.add(new MyInt(serialPort.readIntArray(1)[0]));
                    //}

                    //isReady.setMybool(false);
                //} else {
                //    serialPort.writeInt(0);
                }

            }
*/

            for (int i = 120; i < 150; i++) {

                Thread.sleep(800);
                if (isReady.isMybool()) {
                    //serialPort.writeString("KKLMNO");
                    serialPort.writeInt(77);
                    Thread.sleep(10);
                    serialPort.writeInt(0);
                    Thread.sleep(10);
                    serialPort.writeInt(0 + i);
                    Thread.sleep(10);
                    serialPort.writeInt(0 + i);
                    Thread.sleep(10);
                    serialPort.writeInt(0 );
                    Thread.sleep(10);
                    serialPort.writeInt(0);
                    Thread.sleep(10);
                    //if (event.isRXCHAR() && event.getEventValue() > 0) {
                    //    tt.add(new MyInt(serialPort.readIntArray(1)[0]));
                    //}

                    //isReady.setMybool(false);
                    //} else {
                    //    serialPort.writeInt(0);
                }

            }





            serialPort.closePort();

        } catch (SerialPortException ex) {
            System.out.println(ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
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
                    System.out.println(tt1[0] + " " + tt1[1]);
                    if (tt1[0] == 200){
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
