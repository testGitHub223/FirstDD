//import newPackage.Command;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AlgDD implements Alg{
//
//    List<Command> commandList = new ArrayList<>();
//
//    @Override
//    public int[] getKeys(){
//        if (Command.isSended()){
//            return Command.getData();
//        } else {
//            return Command.getDataBusy();
//        }
//    }
//
//    @Override
//    public void setSend(Boolean isSend) {
//        Command.setSended(isSend);
//
//    }
//
//    @Override
//    public boolean nextTick(long microSeconds) {
//        // Time
//        Command.setCurrentTime(System.nanoTime());
//        for (int i = 0; i < commandList.size(); i++){
//            commandList.get(i).nextTick();
//        }
//
//        int ggg = -1;
//        long max = commandList.get(0).getImportance();
//        for (int i = 1; i < commandList.size(); i++){
//            if (max < commandList.get(i).getImportance()){
//                ggg = i;
//                max = commandList.get(i).getImportance();
//            }
//        }
//        commandList.get(ggg).setData();
//        return true;
//    }
//}
