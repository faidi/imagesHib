package services;

public class ImageForWeb {
	private Long id;
	private String name;
	private String image;
	public ImageForWeb(Long id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}
	public ImageForWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getImage() {
		return image;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

}