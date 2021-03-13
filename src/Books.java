
public class Books {
	private int id;
	private String BookName;
	private String BookWriter;
	private String BookType;
	private String BookPublisher;
	
	public Books(int id, String BookName, String BookWriter, String BookType, String BookPublisher)
	{
		this.id = id;
		this.BookName = BookName;
		this.BookWriter = BookWriter;
		this.BookType = BookType;
		this.BookPublisher = BookPublisher;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String BookName) {
		this.BookName = BookName;
	}
	public String getBookWriter() {
		return BookWriter;
	}
	public void setBookWriter(String BookWriter) {
		this.BookWriter = BookWriter;
	}
	public String getBookType() {
		return BookType;
	}
	public void setBookType(String BookType) {
		this.BookType = BookType;
	}
	public String getBookPublisher() {
		return BookPublisher;
	}
	public void setBookPublisher(String BookPublisher) {
		this.BookPublisher = BookPublisher;
	}
	
	
	
}
