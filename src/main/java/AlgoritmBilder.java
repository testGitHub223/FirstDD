import java.util.*;

public class AlgoritmBilder {

    public AlgoritmBilder(){

        grupToSendList = new ArrayList<>();




    }

    List<GrupToSend_v002> grupToSendList;

    public List<GrupToSend_v002> getAlgoritm(){
        return  grupToSendList;
    }


    public static List<GrupToSend_v002> getDD(){

/*

        KeyToSend F1 = new KeyToSend(10, 10, new int[] {0, 64, 0, 0, 0});
        KeyToSend F2 = new KeyToSend(10, 10, new int[] {0, 65, 0, 0, 0});
        KeyToSend F3 = new KeyToSend(10, 10, new int[] {0, 66, 0, 0, 0});
        KeyToSend F4 = new KeyToSend(10, 10, new int[] {0, 67, 0, 0, 0});
        KeyToSend F5 = new KeyToSend(10, 10, new int[] {0, 68, 0, 0, 0});
        KeyToSend F6 = new KeyToSend(10, 10, new int[] {0, 69, 0, 0, 0});
        KeyToSend F7 = new KeyToSend(10, 10, new int[] {0, 70, 0, 0, 0});
        KeyToSend F8 = new KeyToSend(10, 10, new int[] {0, 71, 0, 0, 0});
        KeyToSend F9 = new KeyToSend(10, 10, new int[] {0, 72, 0, 0, 0});
        KeyToSend F10 = new KeyToSend(10, 10, new int[] {0, 73, 0, 0, 0});
        KeyToSend F11 = new KeyToSend(10, 10, new int[] {0, 74, 0, 0, 0});
        KeyToSend F12 = new KeyToSend(6, 60, new int[] {0, 75, 0, 0, 0});
*/



        KeyToSend F1 = new KeyToSend(10, 10, new int[] {0, 194, 0, 0, 0});
        KeyToSend F2 = new KeyToSend(10, 10, new int[] {0, 195, 0, 0, 0});
        KeyToSend F3 = new KeyToSend(10, 10, new int[] {0, 196, 0, 0, 0});
        KeyToSend F4 = new KeyToSend(10, 10, new int[] {0, 197, 0, 0, 0});
        KeyToSend F5 = new KeyToSend(10, 10, new int[] {0, 198, 0, 0, 0});
        KeyToSend F6 = new KeyToSend(10, 10, new int[] {0, 199, 0, 0, 0});
        KeyToSend F7 = new KeyToSend(10, 10, new int[] {0, 200, 0, 0, 0});
        KeyToSend F8 = new KeyToSend(10, 10, new int[] {0, 201, 0, 0, 0});
        KeyToSend F9 = new KeyToSend(10, 10, new int[] {0, 202, 0, 0, 0});
        KeyToSend F10 = new KeyToSend(10, 10, new int[] {0, 203, 0, 0, 0});
        KeyToSend F11 = new KeyToSend(10, 10, new int[] {0, 204, 0, 0, 0});
        KeyToSend F12 = new KeyToSend(6*1000, 60*1000, new int[] {0, 205, 0, 0, 0});



        List<KeyToSend> firstList = new ArrayList<KeyToSend>();
        firstList.add(F1);
        firstList.add(F2);
        firstList.add(F3);
        firstList.add(F4);

        List<KeyToSend> secondList = new ArrayList<KeyToSend>();
        secondList.add(F5);
        secondList.add(F6);
        secondList.add(F7);
        secondList.add(F8);
        secondList.add(F9);
        secondList.add(F10);
        secondList.add(F11);

        List<KeyToSend> thirdList = new ArrayList<KeyToSend>();
        thirdList.add(F12);


        GrupToSend_v002 firstGrup = new GrupToSend_v002(firstList, false, false, 110, true, 1, 2 );

        GrupToSend_v002 secondGrup = new GrupToSend_v002(secondList, false, false, 100, false, 1, 3 );

        GrupToSend_v002 thirdGrup = new GrupToSend_v002(firstList, false, true, 200, true, 6*1000,  60*1000);

        List<GrupToSend_v002> grupToSends = new ArrayList<>();

        grupToSends.add(firstGrup);
        grupToSends.add(secondGrup);
        grupToSends.add(thirdGrup);

        return grupToSends;

    }



}
