package services;

public class ImageForWeb {
	private Long id;
	private String name;
	private String image;
	private double distance;
	public ImageForWeb(String name, String image, double distance) {
		super();
		this.name = name;
		this.image = image;
		this.distance = distance;
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
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	

}
