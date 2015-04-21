package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.projet.controleur.Controleur;
import com.projet.outiles.CalculeSimilariteSig;
import com.projet.outiles.Signature;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.tools.javac.code.Attribute.Array;

import services.ImageForWeb;
import services.ImageRepository;
 
import services.SignatureRepository;
import dao.Image;
import dao.Signatures;
import util.Utils;

/**
 * Servlet implementation class uploadImage
 */

// taille maximal du fichier
@WebServlet(name = "recherche", description = "trouver des images similaire  en calculant la distance entres les signatures  ", urlPatterns = { "/recherche" })
@MultipartConfig(maxFileSize = 1177215)
public class RechercheImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(RechercheImage.class
			.getCanonicalName());
	File destinationDir = new File("/tmp");
	ArrayList<Image> listImage = new ArrayList<Image>();
	ArrayList<ImageForWeb> ltw = new ArrayList<ImageForWeb>();
	ArrayList<ImageForWeb> ltw2 = new ArrayList<ImageForWeb>();
	Signatures sigUploaded = new Signatures();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// final String filePath = request.getParameter("image");
		final Part filePart = request.getPart("image");
		final String fileName = getFileName(filePart);
		OutputStream out = null;
		InputStream filecontent = null;

		final PrintWriter writer = response.getWriter();
		File tmpFile = new File(destinationDir + File.separator + fileName);

		try {

			// System.out.println(filePath.substring(filePath.lastIndexOf("\\")+1)
			// );
			out = new FileOutputStream(tmpFile);

			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			// writer.println("New file " + fileName + " created at " +
			// destinationDir);

			LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
					new Object[] { fileName });

			Image img = new Image(readBytesFromFile(tmpFile), fileName);
			ImageForWeb imgW=Utils.encodeForWeb(img, 0);
			sigUploaded = Utils.calculerSignature(tmpFile);
			
			double distance = 0;
			double distance2 = 0;
			Iterator<Signatures> signatures = SignatureRepository
					.getAllSignatures().iterator();
			ltw.clear();
			while (signatures.hasNext()) {

				Signatures sigFromDB = signatures.next();
				distance = (Utils.calculerDistanceEuclidienne(sigUploaded, sigFromDB))   ;
				 
				System.out.println("la distance est de : " + distance);
				if (distance <= 0.2) {
					 
					ltw.add(Utils.encodeForWeb( sigFromDB.getImage(),distance ));
				}
 
				  
				   Signature sig2Uploaded=new Signature(sigUploaded.getTabRG(),sigUploaded.getTabBY(),sigUploaded.getTabWB());
				    
				   ltw2.clear();
				   while (signatures.hasNext()) {
				    
				    Signatures s2 = signatures.next(); 
				    Signature sig2fromDB=new  Signature(s2.getTabRG(),s2.getTabBY(),s2.getTabRG());
				   
				   distance2=Controleur.calculerDeSimilarite(sig2Uploaded,sig2fromDB) ; 
				   
				   System.out.println("la distance est de" +
				   distance);
				   if (distance2 <= 0.5 ) {
				   
				   
				    
				   
				 ltw2.add(Utils.encodeForWeb(s2.getImage(),distance2) );
				 
				   }} //
				 
			}

			/*
			 * getServletContext().getRequestDispatcher(
			 * "/success.jsp").forward(request,response);
			 */
			// request.getRequestDispatcher("/success.jsp").forward(request,
			// response);
			//
			  request.setAttribute("images2", ltw2);
			request.setAttribute("images", ltw);
		 request.setAttribute("uploadedImg", imgW);
			RequestDispatcher requestDispatcher;
			requestDispatcher = request
					.getRequestDispatcher("/result_search.jsp");
			requestDispatcher.forward(request, response);

		} catch (FileNotFoundException fne) {
			writer.println("You either did not specify a file to upload or are "
					+ "trying to upload a file to a protected or nonexistent "
					+ "location.");
			writer.println("<br/> ERROR: " + fne.getMessage());

			LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
					new Object[] { fne.getMessage() });
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
			if (writer != null) {
				writer.close();
			}
		}

	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");

		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

	public static byte[] readBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			throw new IOException("Could not completely read file "
					+ file.getName() + " as it is too long (" + length
					+ " bytes, max supported " + Integer.MAX_VALUE + ")");
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}
}
