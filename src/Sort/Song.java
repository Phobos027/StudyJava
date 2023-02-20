package Sort;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

class Song implements Comparable<Song> {
    String title;
    String artist;
    String rating;
    String bpm;

    @Override
    public boolean equals(Object o) {
        Song s = (Song) o;
        return getTitle().equals(s.getTitle());
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    public Song(String title, String artist, String rating, String bpm) {
        this.title = title;
        this.artist = artist;
        this.rating = rating;
        this.bpm = bpm;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getRating() {
        return rating;
    }

    public String getBpm() {
        return bpm;
    }

    @Override
    public String toString() {
        return title;
    }


    @Override
    public int compareTo(@NotNull Song o) {
        return title.compareTo(o.getTitle());
    }
}
