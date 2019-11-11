import jssc.SerialPort;
import sender.Sender03;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class App_003_03 {

    static Boolean isReady = false;
    static Boolean isSendingStart = false;
    static Boolean isSending = false;
    static Boolean isSendingEnd = false;


    private static SerialPort serialPort;

    private static String strData;


    private static List<String> requests = new ArrayList<>(1000000);


    public static void main(String[] args) {
        //Передаём в конструктор имя порта
        Sender03 sender03 = new Sender03();
        sender03.init("COM3");

        LocalDateTime timeStart = LocalDateTime.now();
        LocalDateTime timeNext = LocalDateTime.now();

        boolean isGroup = true;
        int grup = 198;

        for (int j = 0; j < 100; j++) {
            if (j > 50) {

                if((timeStart.plusMinutes(21055)).isBefore(LocalDateTime.now())){
                    j = 200;
                    send(sender03, 0);
                } else {

                    sleepT(200);

                    if (timeNext.isBefore(LocalDateTime.now())) {
                        send(sender03, 2);
                        timeNext = LocalDateTime.now().plusMinutes(5);
                        sleepT(3000);
                    } else {
                        if (isGroup) {
                            if (grup > 204) {
                                grup = 198;
                            }
                            send(sender03, 3, grup);
                            grup++;
                            isGroup = false;
                        } else {
                            send(sender03, 1);
                            isGroup = true;
                            // фокус
                            //send(sender03, 4);
                        }


                    }
                    j = 52;
                }
            }
        }
        sender03.close();
    }

    protected static int[] teleport() {
        int[] inttt = new int[15];
        inttt[0] = 75;
        inttt[1] = 130;
        inttt[2] = 0;
        inttt[3] = 61;
        inttt[4] = 61;
        inttt[5] = 0;
        inttt[6] = 130;
        inttt[7] = 130;
        inttt[8] = 0;
        inttt[9] = 61;
        inttt[10] = 61;
        inttt[11] = 0;
        inttt[12] = 130;
        inttt[13] = 0;
        inttt[14] = 0;
        return inttt;
    }

    protected static int[] nextTrg() {
        int[] inttt = new int[15];
        inttt[0] = 75;
        inttt[1] = 194;
        inttt[2] = 194;
        inttt[3] = 195;
        inttt[4] = 195;
        inttt[5] = 196;
        inttt[6] = 196;
        inttt[7] = 197;
        inttt[8] = 197;
        inttt[9] = 0;
        inttt[10] = 0;
        inttt[11] = 0;
        inttt[12] = 0;
        inttt[13] = 0;
        inttt[14] = 0;
        return inttt;
    }


    protected static int[] selfBuff() {
        int[] inttt = new int[15];
        inttt[0] = 75;
        inttt[1] = 205;
        inttt[2] = 205;
        inttt[3] = 0;
        inttt[4] = 0;
        inttt[5] = 0;
        inttt[6] = 0;
        inttt[7] = 0;
        inttt[8] = 0;
        inttt[9] = 0;
        inttt[10] = 0;
        inttt[11] = 0;
        inttt[12] = 0;
        inttt[13] = 0;
        inttt[14] = 0;
        return inttt;
    }

    protected static int[] group(int group) {
        int[] inttt = new int[15];
        inttt[0] = 75;
//        inttt[1] = 198;
//        inttt[2] = 198;
//        inttt[3] = 199;
//        inttt[4] = 199;
//        inttt[5] = 200;
//        inttt[6] = 200;
//        inttt[7] = 201;
//        inttt[8] = 201;
//        inttt[9] = 202;
//        inttt[10] = 202;
//        inttt[11] = 203;
//        inttt[12] = 203;
//        inttt[13] = 204;
//        inttt[14] = 204;
        inttt[1] = group;
        inttt[2] = group;
        inttt[3] = 0;
        inttt[4] = 0;
        inttt[5] = 0;
        inttt[6] = 0;
        inttt[7] = 0;
        inttt[8] = 0;
        inttt[9] = 0;
        inttt[10] = 0;
        inttt[11] = 0;
        inttt[12] = 0;
        inttt[13] = 0;
        inttt[14] = 0;
        return inttt;
    }

    protected static int[] mClick() {
        int[] inttt = new int[15];
        inttt[0] = 77;
        inttt[1] = 1;
        inttt[2] = 0;
        inttt[3] = 0;
        inttt[4] = 0;
        inttt[5] = 1;
        inttt[6] = 0;
        inttt[7] = 0;
        inttt[8] = 0;
        inttt[9] = 0;
        inttt[10] = 0;
        inttt[11] = 0;
        inttt[12] = 0;
        inttt[13] = 0;
        inttt[14] = 0;
        return inttt;
    }



    protected static void send(Sender03 sender03, int number) {
        send(sender03, number,0);
    }


    protected static void send(Sender03 sender03, int number, int group) {
        System.out.println("!!! " + number + " " + group);
        switch (number) {
            case 0:
                System.out.println(sender03.sendKeybordData(teleport()));
                break;
            case 1:
                System.out.println(sender03.sendKeybordData(nextTrg()));
                break;
            case 2:
                System.out.println(sender03.sendKeybordData(selfBuff()));
                break;
            case 3:
                System.out.println(sender03.sendKeybordData(group(group)));
                break;
            case 4:
                System.out.println(sender03.sendKeybordData(mClick()));
                break;
        }
    }




    protected static void sleepT(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
