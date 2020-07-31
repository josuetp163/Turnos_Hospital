package ec.edu.espol.util;

import ec.edu.espol.views.TurnosView;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Iterator;

public class HiloVideos implements Runnable{

    CircularSimplyLinkedList<Video> CSLL;
    private boolean stop;
    MediaPlayer mediaPlayer;

    public HiloVideos(CircularSimplyLinkedList<Video> c) {
        CSLL = c;
    }

    @Override
    public void run() {
        Iterator<Video> it = CSLL.iterator();
        while (!stop) {
            Video video = it.next();
            String s = new File(video.getMedia()).getAbsolutePath();
            Media media = new Media((new File(s).toURI().toString()));
            mediaPlayer = new MediaPlayer(media);
            Long tiempo = video.getDuracion();
            mediaPlayer.play();
            mediaPlayer.setMute(true);
            Platform.runLater(() -> {TurnosView.mediaView.setMediaPlayer(mediaPlayer);});
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void iniciarVideo(){
        mediaPlayer.setMute(false);
    }

    public void mutearVideo(){
        mediaPlayer.setMute(true);
    }

    public void stopThread() {
        mediaPlayer.setMute(true);
        stop = true;
    }
}
