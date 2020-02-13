package com.target.clicker.Menu;

import java.io.*;

public class TimedPreferences implements Serializable {
    int spawnSpeed;
    int targetSize;
    int time;
    int quickness;
    public TimedPreferences(int spawnSpeed, int targetSize, int time, int quickness){
        this.spawnSpeed = spawnSpeed;
        this.targetSize= targetSize;
        this.time = time;
        this.quickness = quickness;
    }
    public void storeEvents() {
        try {
            FileOutputStream fs = new FileOutputStream("core/assets/TimedPreferences.bin");
            ObjectOutputStream os = new ObjectOutputStream(fs);


            os.writeObject(this);

            os.close();
            fs.close();
        } catch (IOException e) {
            System.out.println("IOException is caught");
            System.out.println(e);
        }
    }


    /** Gets events from file */
    public TimedPreferences readFile()  {
        // TODO: If file is out of date, delete and create new
        try {
            FileInputStream fs = new FileInputStream("core/assets/TimedPreferences.bin");
            ObjectInputStream is = new ObjectInputStream(fs);
            TimedPreferences output = (TimedPreferences)is.readObject();
            is.close();
            fs.close();
            return output;

        } catch (IOException e) {
            System.out.println(e);
            storeEvents();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException is caught");
        }
        return new TimedPreferences(1,50,20,1);
    }

}
