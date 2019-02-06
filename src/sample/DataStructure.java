package sample;

public class DataStructure {
    private Object[] Queue;
    int dataAmount;
    int putLoc;
    int getLoc;

    DataStructure () {
        Queue = new Object[100];
        int dataAmount = 0;
        int putLoc = 0;
        int getLoc = 0;
    }
   synchronized boolean put(Object newValue) {
        if(dataAmount < 100){
            Queue[putLoc] = newValue;
            if (putLoc < 99) {
                putLoc += 1;
            } else {
               putLoc = 0;
            }
            dataAmount += 1;
            return true;

        } else {
            return false;
        }
   }
    synchronized Object get() {
        if (dataAmount > 0) {
            Object objToReturn = Queue[getLoc];
            if (getLoc < 99) {
                getLoc += 1;
            } else {
                getLoc = 0;
            }
            dataAmount -= 1;
            return objToReturn;
        } else {
            return null;
        }
    }
}
