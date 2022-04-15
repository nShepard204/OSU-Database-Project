import java.sql.Connection;

public abstract class MediaType {

    // public String name, genre, creator, type;
    // public int year_pub, length, chapters, songs;
    // public int number;


    //number is a number of chapters, songs or length in seconds of a song
    // this will depend on its type
    public MediaType(){
        // this.name = name;
        // this.genre = genre;
        // this.creator = creator;
        // this.type = type;
        // this.year_pub = year_pub;
        // this.length = number;
        // this.chapters = number;
        // this.songs = number;

    }

    public abstract void add(Connection conn);
    public abstract void edit();
 }
