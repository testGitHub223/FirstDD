import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.List;

public class App_002 {

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




            Sender sender = new Sender();
            SetingsOfSendData setingsOfSendData1 = new SetingsOfSendData();




            System.out.println("starting ...");
            Thread.sleep(2000);
            System.out.println("start");

            KeyToSend keyToSend_00 = new KeyToSend();
            keyToSend_00.tt();
            //keyToSend.tt();
            //Thread.sleep(10000);
            //keyToSend.tt();

            //int [] data = new int[] {197, 194, 195, 196, 197};
            //sender.sendData(serialPort, setingsOfSendData1, data);
            //sender.sendData(serialPort, setingsOfSendData1, data);



/*

            Algoritm algoritm = new Algoritm(AlgoritmBilder.getDD());

            for (int i = 0; i < 2304;){
            //for (int i = 0; i < 2304; i++){
                KeyToSend keyToSend = algoritm.getKey();
                if (keyToSend != null) {
                    sender.sendData(serialPort, setingsOfSendData1, keyToSend.getData());
                }
                Thread.sleep(0);
            }
*/



            Algoritm_v002 algoritm_v002 = new Algoritm_v002(AlgoritmBilder_v002.getDD());

            for (int i = 0; i < 230400; i++){
                //for (int i = 0; i < 2304; i++){
                KeyToSend_v002 keyToSend_v002 = algoritm_v002.getKey();
                if (keyToSend_v002 != null) {
                    sender.sendKeybordData(serialPort, setingsOfSendData1, keyToSend_v002.getData(algoritm_v002.getCurrentTime()));
                }
                Thread.sleep(0);
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
