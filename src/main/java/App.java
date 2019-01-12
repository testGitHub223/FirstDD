import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.List;

public class App {

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

    private void sendData(MouseData mouseData) throws InterruptedException, SerialPortException {
        Thread.sleep(delayTimeStartMouse);
        serialPort.writeInt(mouseData.getType());
        Thread.sleep(delayTimeMouse);
        serialPort.writeInt(mouseData.getButtonsPress());
        Thread.sleep(delayTimeMouse);
        serialPort.writeInt(mouseData.getDy());
        Thread.sleep(delayTimeMouse);
        serialPort.writeInt(mouseData.getDy());
        Thread.sleep(delayTimeMouse);
        serialPort.writeInt(mouseData.getDw());
        Thread.sleep(delayTimeMouse);
        serialPort.writeInt(mouseData.getButtonsRelease());
        Thread.sleep(delayTimeMouse);

    }

    private class KeybordData{

        private final static int type = 75;
        private int buttonsPress;
        private int dx = 0;
        private int dy = 0;
        private int dw = 0;
        private int buttonsRelease;

    }

    private class KeyModifer{

        private boolean KEY_LEFT_CTRL = false;      // 0x80  128
        private boolean KEY_LEFT_SHIFT = false;     // 0x81  129
        private boolean KEY_LEFT_ALT = false;       // 0x82  130
        private boolean KEY_LEFT_GUI = false;       // 0x83  131
        private boolean KEY_RIGHT_CTRL = false;     // 0x84  132
        private boolean KEY_RIGHT_SHIFT = false;    // 0x85  133
        private boolean KEY_RIGHT_ALT = false;      // 0x86  134
        private boolean KEY_RIGHT_GUI = false;      // 0x87  135
    }


    private class MouseData{

        public MouseData(Button buttonsPress, int dx, int dy, int dw, Button buttonsRelease) {

            int ttPress = 0;
            if (buttonsPress.isLeft()) ttPress += 0b00000001;
            if (buttonsPress.isRight()) ttPress += 0b00000010;
            if (buttonsPress.isMiddle()) ttPress += 0b00000100;
            this.buttonsPress = ttPress;

            if (dx > 127)    dx = 127;
            if (dx < -127)   dx = -127;
            if (dx < 0)      dx = 256 + dx;
            this.dx = dx;

            if (dy > 127)    dy = 127;
            if (dy < -127)   dy = -127;
            if (dy < 0)      dy = 256 + dy;
            this.dy = dy;
            if (dw > 127)    dw = 127;
            if (dw < -127)   dw = -127;
            if (dw < 0)      dw = 256 + dw;
            this.dw = dw;
            int ttRelease = 0;
            if (buttonsRelease.isLeft()) ttRelease += 0b00000001;
            if (buttonsRelease.isRight()) ttRelease += 0b00000010;
            if (buttonsRelease.isMiddle()) ttRelease += 0b00000100;
            this.buttonsRelease = ttRelease;
        }

        private final static int type = 75;
        private int buttonsPress;
        private int dx = 0;
        private int dy = 0;
        private int dw = 0;
        private int buttonsRelease;


        public int getType() {
            return type;
        }
        public int getButtonsPress() {
            return buttonsPress;
        }
        public int getDx() {
            return dx;
        }
        public int getDy() {
            return dy;
        }
        public int getDw() {
            return dw;
        }
        public int getButtonsRelease() {
            return buttonsRelease;
        }

    }



    private class Button{
        public Button(boolean left, boolean right, boolean middle) {
            this.left = left;
            this.right = right;
            this.middle = middle;
        }

        private boolean left;
        private boolean right;
        private boolean middle;

        public boolean isLeft() {
            return left;
        }
        public boolean isRight() {
            return right;
        }
        public boolean isMiddle() {
            return middle;
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
