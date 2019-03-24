import jssc.SerialPort;
import jssc.SerialPortException;


class Listener03 {
    public boolean getData(SerialPort serialPort) throws InterruptedException, SerialPortException {
        boolean isReady = false;
        try {
            int[] tt1 = serialPort.readIntArray(1);
            //int tt2 = serialPort.readBytes(1)[0];
            //int tt = tt1<<8 + tt2;
            //if (data.contains("S")){
            //    System.out.print("!!!!!!!!!!!!!!!!!!!!!!" + data);
            //    isReady.setMybool(true);
            //}
            //System.out.print(data);
            System.out.println("get Listener03 +: " + tt1[0]);
            if (tt1[0] == 97){
                int[] tt2 = serialPort.readIntArray(1);
                System.out.println("get Listener03 +: " + tt1[0] + " " + tt2[0]);
                if (tt2[0] > 10) {

                    isReady = true;
                    System.out.println("Redy to send");
                }
            }
        } catch (Exception ex) {
            System.out.println("get Listener03 -: ");
        }
        return isReady;
    }
}