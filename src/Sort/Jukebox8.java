package Sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Jukebox8 {
    ArrayList <Song> songList = new ArrayList<Song>();
    

    public static void main(String[] args) {
        new Jukebox8().go();
    }

    class ArtistCompare implements Comparator<Song>{

        @Override
        public int compare(Song one, Song two) {
            return one.getArtist().compareTo(two.getArtist());
        }
    }
    class RatingCompare implements Comparator<Song>{
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getRating().compareTo(o2.getRating());
        }
    }

    private void go() {
        getSongs();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);
        TreeSet<Song> songSet = new TreeSet<>();
        songSet.addAll(songList);
        System.out.println(songSet);

    }

    private void getSongs() {
        try {
            File file = new File("src/Sort/SongFile/SongListMore");
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bf.readLine()) != null) {
                addSong(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addSong(String line) {
        String[] tokens = line.split("/");
        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3] );
        songList.add(nextSong);
    }


}
