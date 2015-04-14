package dao;

import java.io.File;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "image_id")
	private Long imageId;

	@Column(name = "file", columnDefinition = "mediumblob")
	private byte[] fichier;
	
	@Column(name = "name")
	private String name;
	
	
	
	@OneToOne(cascade=CascadeType.ALL,  mappedBy="image")
 	private Signature signature;

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		
		this.signature = signature;
	}

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image(byte[] fichier, String name) {
		super();
		this.fichier = fichier;
		this.name = name;
	}

	public Image(File tmpFile, String fileName) {
		// TODO Auto-generated constructor stub

	}

	public Long getId() {
		return imageId;
	}

	public byte[] getFichier() {
		return fichier;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.imageId = id;
	}

	public void setFichier(byte[] fichier) {
		this.fichier = fichier;
	}

	public void setName(String name) {
		this.name = name;
	}

	 

}
