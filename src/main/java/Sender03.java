import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


class Sender03 {

    SerialPort serialPort;
    protected BlockingQueue<String> requests;

    protected final static String strRefresh = "[REFRES][251 251]";
    protected final static String strRefreshBusy = "[BUSY R][251 251]";
    protected final static String strKSend = "[KSEND ]";
    protected final static String strMSend = "[MSEND ]";
    protected final static String strDefalt = "[DEFALT]";
    protected final static String strErrorEnd = "[ERREND]";


    public Sender03() {

    }

    public boolean init(String port) {
        boolean isInit = false;
        serialPort = new SerialPort(port);
        //Открываем порт
        try {
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
            System.out.println("starting ...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("start");
            requests = new ArrayBlockingQueue(10);
            isInit = true;
        } catch (SerialPortException e) {
            System.out.println("catch (SerialPortException e)");
            close();
            serialPort = null;
            e.printStackTrace();
        }
        return isInit;
    }

    public void close() {
        requests = null;
        try {
            serialPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }


    public Sender03(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public void sendKeybordInit() throws SerialPortException {
        serialPort.writeInt(251);
        //System.out.println("Send init");
    }

    public void sendPackageNumber(int number1, int number2) throws SerialPortException {
        serialPort.writeInt(number1);
        serialPort.writeInt(number2);
        //System.out.println("Send sendPackageNumber");
    }

    public void sendKeybordEnd() throws SerialPortException {
        serialPort.writeInt(254);
        serialPort.writeInt(255);
        //System.out.println("Send end");
    }

    public void sendData(int[] data) throws SerialPortException {
        // проверить на размер массива
        int rr0 = data[0];
        int rr1 = data[1];
        int rr2 = data[2];
        int rr3 = data[3];
        int rr4 = data[4];
        int rr5 = data[5];
        int rr6 = data[6];
        int rr7 = data[7];
        int rr8 = data[8];
        int rr9 = data[9];
        int rr10 = data[10];
        int rr11 = data[11];
        int rr12 = data[12];
        int rr13 = data[13];
        int rr14 = data[14];

//        System.out.print("output " +
//                Integer.toString(rr0) + " " +
//                Integer.toString(rr1) + " " +
//                Integer.toString(rr2) + " " +
//                Integer.toString(rr3) + " " +
//                Integer.toString(rr4) + " " +
//                Integer.toString(rr5) + " " +
//                Integer.toString(rr6) + " " +
//                Integer.toString(rr7) + " " +
//                Integer.toString(rr8) + " " +
//                Integer.toString(rr9) + " " +
//                Integer.toString(rr10) + " " +
//                Integer.toString(rr11) + " " +
//                Integer.toString(rr12) + " " +
//                Integer.toString(rr13) + " " +
//                Integer.toString(rr14) + " ");

        serialPort.writeInt(rr0);
        serialPort.writeInt(rr1);
        serialPort.writeInt(rr2);
        serialPort.writeInt(rr3);
        serialPort.writeInt(rr4);
        serialPort.writeInt(rr5);
        serialPort.writeInt(rr6);
        serialPort.writeInt(rr7);
        serialPort.writeInt(rr8);
        serialPort.writeInt(rr9);
        serialPort.writeInt(rr10);
        serialPort.writeInt(rr11);
        serialPort.writeInt(rr12);
        serialPort.writeInt(rr13);
        serialPort.writeInt(rr14);

        //System.out.println("Send data");
    }

    public boolean sendKeybordData(int[] data) throws SerialPortException {
        boolean isSended = false;

        sendKeybordInit();
        String strInit = null;
        String strData = null;
        try {
            strInit = requests.poll(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---Плата отправила---" + strInit);
        switch (strInit){
            case strRefresh:

                sendData(data);

                int random_number1 = 101 + (int) (Math.random() * 140);
                int random_number2 = 101 + (int) (Math.random() * 140);
                sendPackageNumber(random_number1, random_number2);
                sendKeybordEnd();

                try {
                    strData = requests.poll(100, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("---Плата отправила---" + strData);
                switch (strData){
                    case strKSend:
                        System.out.println(strKSend + "[" + random_number1 + " " + random_number2 + "]");
                        if (strData.equals(strKSend + "[" + random_number1 + " " + random_number2 + "]")){
                            isSended = true;
                        }
                        break;
                    case strMSend:
                        System.out.println(strMSend + "[" + random_number1 + " " + random_number2 + "]");
                        if (strData.equals(strMSend + "[" + random_number1 + " " + random_number2 + "]")){
                            isSended = true;
                        }
                        break;
                    case strDefalt:
                        System.out.println(strDefalt + "[" + random_number1 + " " + random_number2 + "]");
                        if (strData.equals(strDefalt + "[" + random_number1 + " " + random_number2 + "]")){
                            //isSended = true;
                        }
                        break;
                    case strErrorEnd:
                        System.out.println(strErrorEnd + "[" + random_number1 + " " + random_number2 + "]");
                        if (strData.equals(strErrorEnd + "[" + random_number1 + " " + random_number2 + "]")){
                            //isSended = true;
                        }
                        break;
                }

                break;
            case strRefreshBusy:
                isSended = false;
                break;
        }

        return isSended;
    }


    private class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {

            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    String data = serialPort.readString(event.getEventValue());
                    try {
                        requests.put(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("!!!" + data);
/*

//                    strData += data;

                    if (data.contains("[BUSY  ]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace("[BUSY  ]", "");
//                        int  tt = 0;
                        System.out.println("---Плата занята---" + data);
                    }
                    if (data.contains("[BUSY R]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace("[BUSY  ]", "");
//                        int  tt = 0;
                        System.out.println("---Плата занята СБРОС---" + data);
                    }
                    if (data.contains("[KSEND ]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace(" [13 HIGH] ", "");
//                        int  tt = 0;
                        System.out.println("---Плата отправила данные---" + data);
                    }
                    if (data.contains("[MSEND ]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace(" [13 HIGH] ", "");
//                        int  tt = 0;
                        System.out.println("---Плата отправила данные---" + data);
                    }
                    if (data.contains("[DEFALT]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace(" [13 LOW] ", "");
//                        int  tt = 0;
                        System.out.println("---Плата не сформировала пакет---" + data);
                    }
                    if (data.contains("[ERREND]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace(" [13 TEST] ", "");
//                        int  tt = 0;
                        System.out.println("---Не правильное заершение пакета---" + data);
                    }
                    if (data.contains("[REFRES]")) {
                        // плата занята, действия безполезны
//                        strData = strData.replace(" [13 TEST] ", "");
//                        int  tt = 0;
                        System.out.println("---Плата получила 251---" + data);
                    }

//                    strData = strData.replace("   ", "");
*/

                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}

