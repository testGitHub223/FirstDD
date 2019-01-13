import jssc.SerialPort;
import jssc.SerialPortException;


class Listener03 {
    public void getData(SerialPort serialPort) throws InterruptedException, SerialPortException {

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