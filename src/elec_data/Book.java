
package elec_data;

import db_util.DB;
import out_err.IO;



public class Book {
    private String name, author, releaseDate;
    private int id;

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getId() {
        return id;
    }
    public static void search(String name) {
        DB.connect();
        DB.createStat("SELECT * FROM book");
        IO.rowContent("03502005", "ID", "Name", "Author", "Date");
        boolean dataFound = false;
        while(DB.shift()) {
            if (DB.getString("name").contains(name)) {
                dataFound = true;
                IO.row("03502005", 
                    DB.getString("id"), DB.getString("name"),
                    DB.getString("author"), DB.getString("release_date"));
            }
        }
        DB.disconnect();
        if (!dataFound) {
            IO.error("No Data Found");
            IO.sleep(2000);
            IO.clear();
            return;
        }
    }
    
    public static void add(Book book) {
        book.setId(0);
        DB.connect();
        String query = "INSERT INTO book VALUES ( '" + String.valueOf(book.getId()) + "', '" +
                book.getName() + "', '" + book.getAuthor()+ "', '" + book.getReleaseDate() + "')";
        DB.createStat(query);
        DB.disconnect();
    }
    
}
