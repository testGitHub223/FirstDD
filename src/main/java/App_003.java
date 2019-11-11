import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;


import java.util.ArrayList;
import java.util.List;

public class App_003 {

    static Boolean isReady = false;
    static Boolean isSendingStart = false;
    static Boolean isSending = false;
    static Boolean isSendingEnd = false;


    private static SerialPort serialPort;
    //private static Sender03 sender03;

    private static String strData;


    private static List<String> requests = new ArrayList<>(1000000);


    public static void main(String[] args) {
        //Передаём в конструктор имя порта
        serialPort = new SerialPort("COM3");

        //for (int i = 0; i < 10; i++) {
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
                serialPort.addEventListener(new  PortReader(), SerialPort.MASK_RXCHAR);
                //Отправляем запрос устройству

                ////sender03 = new Sender03(serialPort);

                System.out.println("starting ...");
                Thread.sleep(2000);
                System.out.println("start");

                for (int j = 0; j < 100; j++) {
                    if (j > 50) {
                        //System.out.println("a1" + Thread.currentThread().getName());
                        //sender03.sendKeybordInit();
                        //sender03.sendData(getIntArray());
                        //sender03.sendPackageNumber(101, 102);
                        //sender03.sendKeybordEnd();
                        j = 0;

//                        try {
//                            Thread.sleep(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }

                    }
                }

                serialPort.closePort();

            } catch (SerialPortException ex) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ex);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}

    }


    protected static int[] getIntArray() {
        int[] inttt = new int[15];
        inttt[0] = 75;
        inttt[1] = 49;
        inttt[2] = 49;
        inttt[3] = 50;
        inttt[4] = 50;
        inttt[5] = 51;
        inttt[6] = 51;
        inttt[7] = 52;
        inttt[8] = 52;
        inttt[9] = 53;//
        inttt[10] = 53;
        inttt[11] = 54;
        inttt[12] = 54;
        inttt[13] = 176;
        inttt[14] = 176;
        return inttt;
    }




























































    private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {

            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    String data = serialPort.readString(event.getEventValue());
                    requests.add(data);

                    System.out.println("!!!" + data);

//                    strData += data;
                    strData = data;
                    if (strData.contains("[BUSY  ]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace("[BUSY  ]", "");
//                        int  tt = 0;
                        System.out.println("---Плата занята---" + strData);
                    }
                    if (strData.contains("[BUSY R]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace("[BUSY  ]", "");
//                        int  tt = 0;
                        System.out.println("---Плата занята СБРОС---" + strData);
                    }
                    if (strData.contains("[KSEND ]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace(" [13 HIGH] ", "");
//                        int  tt = 0;
                        System.out.println("---Плата отправила данные---" + strData);
                    }
                    if (strData.contains("[MSEND ]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace(" [13 HIGH] ", "");
//                        int  tt = 0;
                        System.out.println("---Плата отправила данные---" + strData);
                    }
                    if (strData.contains("[DEFALT]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace(" [13 LOW] ", "");
//                        int  tt = 0;
                        System.out.println("---Плата не сформировала пакет---" + strData);
                    }
                    if (strData.contains("[ERREND]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace(" [13 TEST] ", "");
//                        int  tt = 0;
                        System.out.println("---Не правильное заершение пакета---" + strData);
                    }
                    if (strData.contains("[REFRES]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace(" [13 TEST] ", "");
//                        int  tt = 0;
                        System.out.println("---Плата получила 251---" + strData);
                    }

//                    strData = strData.replace("   ", "");


                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

}
