import java.util.ArrayList;
import java.util.List;

public class AlgoritmBilder_v002 {



    public static AAL_v002 getDD(){

        /*
        KeyToSend_v002 F1 = new KeyToSend_v002(10, 10, new int[] {0, 64, 0, 0, 0});
        KeyToSend_v002 F2 = new KeyToSend_v002(10, 10, new int[] {0, 65, 0, 0, 0});
        KeyToSend_v002 F3 = new KeyToSend_v002(10, 10, new int[] {0, 66, 0, 0, 0});
        KeyToSend_v002 F4 = new KeyToSend_v002(10, 10, new int[] {0, 67, 0, 0, 0});

        KeyToSend_v002 F5 = new KeyToSend_v002(10, 10, new int[] {0, 68, 0, 0, 0});
        KeyToSend_v002 F6 = new KeyToSend_v002(10, 10, new int[] {0, 69, 0, 0, 0});
        KeyToSend_v002 F7 = new KeyToSend_v002(10, 10, new int[] {0, 70, 0, 0, 0});
        KeyToSend_v002 F8 = new KeyToSend_v002(10, 10, new int[] {0, 71, 0, 0, 0});
        KeyToSend_v002 F9 = new KeyToSend_v002(10, 10, new int[] {0, 72, 0, 0, 0});
        KeyToSend_v002 F10 = new KeyToSend_v002(10, 10, new int[] {0, 73, 0, 0, 0});
        KeyToSend_v002 F11 = new KeyToSend_v002(10, 10, new int[] {0, 74, 0, 0, 0});

        KeyToSend_v002 F12 = new KeyToSend_v002(10*1000, 18*1000, new int[] {0, 75, 0, 0, 0});
        //KeyToSend_v002 F13 = new KeyToSend_v002(1*1000, 5*1000, new int[] {0, 76, 0, 0, 0});
*/


        KeyToSend_v002 F1 = new KeyToSend_v002(10, 10, new int[] {0, 194, 0, 0, 0});
        KeyToSend_v002 F2 = new KeyToSend_v002(10, 10, new int[] {0, 195, 0, 0, 0});
        KeyToSend_v002 F3 = new KeyToSend_v002(10, 10, new int[] {0, 196, 0, 0, 0});
        KeyToSend_v002 F4 = new KeyToSend_v002(10, 10, new int[] {0, 197, 0, 0, 0});

        KeyToSend_v002 F5 = new KeyToSend_v002(10, 10, new int[] {0, 198, 0, 0, 0});
        KeyToSend_v002 F6 = new KeyToSend_v002(10, 10, new int[] {0, 199, 0, 0, 0});
        KeyToSend_v002 F7 = new KeyToSend_v002(10, 10, new int[] {0, 200, 0, 0, 0});
        KeyToSend_v002 F8 = new KeyToSend_v002(10, 10, new int[] {0, 201, 0, 0, 0});
        KeyToSend_v002 F9 = new KeyToSend_v002(10, 10, new int[] {0, 202, 0, 0, 0});
        KeyToSend_v002 F10 = new KeyToSend_v002(10, 10, new int[] {0, 203, 0, 0, 0});
        KeyToSend_v002 F11 = new KeyToSend_v002(10, 10, new int[] {0, 204, 0, 0, 0});

//        KeyToSend_v002 F12 = new KeyToSend_v002(7*1000, 12*60*1000, new int[] {0, 205, 0, 0, 0});
        KeyToSend_v002 F12 = new KeyToSend_v002(4*1000, 12*60*1000, new int[] {0, 205, 0, 0, 0});
        //KeyToSend_v002 F13 = new KeyToSend_v002(1*1000, 5*1000, new int[] {0, 76, 0, 0, 0});



        List<KeyToSend_v002> firstList = new ArrayList<>();
        firstList.add(F1);
        firstList.add(F2);
        firstList.add(F3);
        firstList.add(F4);

        List<KeyToSend_v002> secondList = new ArrayList<>();
        secondList.add(F5);
        secondList.add(F6);
        secondList.add(F7);
        secondList.add(F8);
        secondList.add(F9);
        secondList.add(F10);
        secondList.add(F11);

        List<KeyToSend_v002> thirdList = new ArrayList<>();
        thirdList.add(F12);
        //thirdList.add(F13);




        GrupToSendNotTimed_v002 firstGrup = new GrupToSendNotTimed_v002(firstList, 120);
        firstGrup.initialize();
        GrupToSendNotTimed_v002 secondGrup = new GrupToSendNotTimed_v002(secondList, 110);
        secondGrup.initialize();

        GrupToSendTimed_v002 thirdGrup = new GrupToSendTimed_v002(thirdList, 1100);


        //////////////////////////////////////////////
        int firstGrupSize = firstGrup.getSize();
        firstGrup.addNextGrup(firstGrupSize - 1, secondGrup);
        int secondGrupSize = secondGrup.getSize();
        for (int i = 0; i < secondGrupSize; i++){
            secondGrup.addNextGrup(i, firstGrup);
        }
        //////////////////////////////////////


        List<GrupToSendNotTimed_v002> grupToSendNotTimed_v002List = new ArrayList<>();
        List<GrupToSendTimed_v002> grupToSendTimed_v002List = new ArrayList<>();

        GrupNotTimed_v002 grupNotTimed_v002 = new GrupNotTimed_v002(firstGrup);
        GrupTimed_v002 grupTimed_v002 = new GrupTimed_v002(thirdGrup);
        //grupToSendNotTimed_v002List.add(secondGrup);
        //grupToSendTimed_v002List.add(thirdGrup);


        AAL_v002 aal_v002 = new  AAL_v002(grupNotTimed_v002, grupTimed_v002);

        return aal_v002;
    }


}
