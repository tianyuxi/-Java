package BookManager.book;

public class BookList {
    private Book[] books= new Book[100];
    private int useSize = 0;

    public int getUseSize() {
        return useSize;
    }

    public void setUseSize(int useSize) {
        this.useSize = useSize;
    }

    public BookList() {
        books[0] = new Book("西游记","吴承恩",100,"小说");
        books[1] = new Book("水浒传","施耐庵",100,"小说");
        books[2] = new Book("三国演义","罗贯中",100,"小说");
        this.useSize = 3;
    }

    public void setBook(int pos,Book book) {
        this.books[pos] = book;
    }

    public Book getBook(int pos) {
        return this.books[pos];
    }
}
