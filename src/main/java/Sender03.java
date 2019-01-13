import jssc.SerialPort;
import jssc.SerialPortException;


class Sender03 {
    public void SendKeybordData(SerialPort serialPort, SetingsOfSendData setingsOfSendData,  int[] data) throws InterruptedException, SerialPortException {


        int rr2 = data[0];
        int rr3 = data[1] ;
        int rr4 = data[2] ;
        int rr5 = data[3] ;
        int rr6 = data[4];

        int rr1 = 75;
        //int rr2 = 197;
        //int rr3 = 194 ;
        //int rr4 = 195 ;
        //int rr5 = 196 ;
        //int rr6 = 197;

        System.out.println("output "
                + Integer.toString(rr1) + " "
                + Integer.toString(rr2) + " "
                + Integer.toString(rr3) + " "
                + Integer.toString(rr4) + " "
                + Integer.toString(rr5) + " "
                + Integer.toString(rr6) + " ");

        serialPort.writeInt(rr1);
        Thread.sleep(setingsOfSendData.getDelayTimeKeybord());
        serialPort.writeInt(rr2);
        Thread.sleep(setingsOfSendData.getDelayTimeKeybord());
        serialPort.writeInt(rr3);
        Thread.sleep(setingsOfSendData.getDelayTimeKeybord());
        serialPort.writeInt(rr4);
        Thread.sleep(setingsOfSendData.getDelayTimeKeybord());
        serialPort.writeInt(rr5);
        Thread.sleep(setingsOfSendData.getDelayTimeKeybord());
        serialPort.writeInt(rr6);
        Thread.sleep(setingsOfSendData.getDelayTimeKeybord());

        try{
            int[] tt1 = serialPort.readIntArray(1);
            //int tt2 = serialPort.readBytes(1)[0];
            //int tt = tt1<<8 + tt2;
            //if (data.contains("S")){
            //    System.out.print("!!!!!!!!!!!!!!!!!!!!!!" + data);
            //    isReady.setMybool(true);
            //}
            //System.out.print(data);
            System.out.print("get: +99 " + tt1[0]);
            tt1 = serialPort.readIntArray(1);
            System.out.println(" " + tt1[0]);
        }catch (Exception ex){
            System.out.println("get: - ");
        }

    }
}