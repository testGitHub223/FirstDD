package sender;

import helpers.Helper;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public class Sender03 {

    SerialPort serialPort;
    protected BlockingQueue<String> requests;

    protected final static String strRefresh = " [REFRES][251 251] ";
    protected final static String strRefreshBusy = " [BUSY R][251 251] ";
    protected final static String strKSend = " [KSEND ]";
    protected final static String strMSend = " [MSEND ]";
    protected final static String strDefalt = " [DEFALT]";
    protected final static String strErrorEnd = " [ERREND]";

    protected String strFromBoardTMP = ""; // строка, которую присылает плата


    public Sender03() {
    }

    public boolean init(String port) {
        boolean isInit = false;
        serialPort = new SerialPort(port);
        //Открываем порт
        System.out.println("SerialPort.init " + port);
        try {
            //System.out.println("serialPort.openPort()");
            serialPort.openPort();
            System.out.println("serialPort.openPort() success");

            //Выставляем параметры
            //System.out.println("serialPort.setParams()");
            //serialPort.setParams(SerialPort.BAUDRATE_9600,
            serialPort.setParams(SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Включаем аппаратное управление потоком
            //System.out.println("serialPort.setFlowControlMode()");
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            //Устанавливаем ивент лисенер и маску
            //System.out.println("serialPort.addEventListener()");
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            //Отправляем запрос устройству
            System.out.println("starting ... 2s");
            Helper.sleep(2000);
            System.out.println("start OK");
            requests = new ArrayBlockingQueue(10);
            isInit = true;
        } catch (SerialPortException e) {
            //System.out.println("catch (SerialPortException e)");
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


    /**
     * Посылаем в плату 251, что бы сбросить в начало приема
     *
     * @throws SerialPortException
     */
    protected void sendKeybordInit() {
        try {
        serialPort.writeInt(251);
        //System.out.println("Sender03.sendKeybordInit()");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    /**
     * Корректно указываем, что пакет завершен
     */
    protected void sendKeybordEnd() {
        try {
            //System.out.println("Sender03.sendKeybordEnd()");
            serialPort.writeInt(254);
            serialPort.writeInt(255);
            //System.out.println("Sender03.sendKeybordEnd() success");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }


    /**
     * Передаем отправляемое сообщение
     * @param data пакет с отправляемыми данными
     */
    protected void sendData(int[] data) {
        try {
            //System.out.println("Sender03.sendData()");
            for (int i = 0; i < data.length; i++) {
                serialPort.writeInt(data[i]);
                //System.out.println("Sender03.sendData() " + data[i]);
            }
            //System.out.println("Sender03.sendData() success");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param data
     * @return
     */
    public SendState sendKeybordData(int[] data) {
        //System.out.println("Sender03.sendKeybordData()");
        SendState sendState = SendState.ERROR;

        try {
            // Посылаем в плату 251, что бы сбросить в начало приема
            sendKeybordInit();
            // после этого плата должна прислать strRefresh или strRefreshBusy
            // так же очистить принимаемые поля

            String strInit = null;
            String strData = null;

            try {
                strInit = requests.poll(1000, TimeUnit.MILLISECONDS);
                //System.out.println("Sender03 strInit " + strInit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println("---!Плата отправила!---" + strInit);
            switch (strInit) {
                case strRefresh:
                    //System.out.println("Sender03 strRefresh " + strRefresh);
                    sendData(data);
                    //System.out.println("Sender03 strRefresh " + strRefresh + " success");

                    int random_number1 = 101 + (int) (Math.random() * 140);
                    serialPort.writeInt(random_number1);
                    int random_number2 = 101 + (int) (Math.random() * 140);
                    serialPort.writeInt(random_number2);

                    sendKeybordEnd();

                    try {
                        strData = requests.poll(1000, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //System.out.println("---Плата отправила---" + strData);
                    if (strData != null) {
                        ///////////////////////////////////////////
                        // strData
                        String strTemp = strData.substring(0, 9);
                        //System.out.println("Sender03 strTemp " + strTemp);
                        switch (strTemp) {
                            case strKSend:
                                //System.out.println("Sender03 strData " + strKSend + "[" + random_number1 + " " + random_number2 + "] ");
                                if (strData.equals(strKSend + "[" + random_number1 + " " + random_number2 + "] ")) {
                                    sendState = SendState.SENDED;
                                }
                                break;
                            case strMSend:
                                //System.out.println("Sender03 strData " + strMSend + "[" + random_number1 + " " + random_number2 + "] ");
                                if (strData.equals(strMSend + "[" + random_number1 + " " + random_number2 + "] ")) {
                                    sendState = SendState.SENDED;
                                }
                                break;
                            case strDefalt:
                                //System.out.println("Sender03 strData " + strDefalt + "[" + random_number1 + " " + random_number2 + "] ");
                                if (strData.equals(strDefalt + "[" + random_number1 + " " + random_number2 + "] ")) {
                                    //isSended = true;
                                }
                                break;
                            case strErrorEnd:
                                //System.out.println("Sender03 strData " + strErrorEnd + "[" + random_number1 + " " + random_number2 + "]");
                                if (strData.equals(strErrorEnd + "[" + random_number1 + " " + random_number2 + "]")) {
                                    sendState = SendState.ERROR;
                                }
                                break;
                        }
                    } else {
                        //System.out.println("Sender03 strData == null");
                    }
                    break;
                case strRefreshBusy:
                    //System.out.println("Sender03 strRefreshBusy " + strRefreshBusy);
                    sendState = SendState.BUSY;
                    break;
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        return sendState;
    }


    private class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {

            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    // дописываем в временную строку которая присылает плата, данные с платы
                    strFromBoardTMP += serialPort.readString(event.getEventValue());

                    //System.out.println("strFromBoardTMP " + strFromBoardTMP);

                    int startInt = strFromBoardTMP.indexOf(" [");
                    //System.out.println("startInt " + startInt);
                    int finishInt = strFromBoardTMP.indexOf("] ") + 2;
                    //System.out.println("finishInt " + finishInt);
                    if (startInt >= 0 && startInt < finishInt) {
                        String str = strFromBoardTMP.substring(startInt, finishInt);
                        //System.out.println("str " + str);
                        try {
                            //System.out.println("requests.put()" + str);
                            requests.put(str);
                            //System.out.println("requests.put() " + str + " success");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        strFromBoardTMP = strFromBoardTMP.replace(str, "");
                        //System.out.println("strFromBoardTMP " + strFromBoardTMP);
                    }
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}

