
 public class MediaType {

    public String name, genre, creator, type;
    public int year_pub, length, chapters, songs;
    public int number;


    //number is a number of chapters, songs or length in seconds of a song
    // this will depend on its type
    public MediaType(String name, String genre, String creator,
            String type, int year_pub, int number){
        this.name = name;
        this.genre = genre;
        this.creator = creator;
        this.type = type;
        this.year_pub = year_pub;
        this.length = number;
        this.chapters = number;
        this.songs = number;

    }

 }