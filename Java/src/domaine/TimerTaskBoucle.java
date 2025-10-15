package domaine;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskBoucle extends TimerTask {
    private Triplet triplet;
    private Instrument instrument;
    
        @Override
        public void run() {
            Son son = triplet.getFirst().getSon();
            if(triplet.getThird()){
                instrument.stopApresPersistance((Note) son);
            }else{
                instrument.jouerSon(son);
            }
        }

        public void setTriplet(Triplet triplet, Instrument instrument){
            this.triplet = triplet;
            this.instrument = instrument;
        }
}