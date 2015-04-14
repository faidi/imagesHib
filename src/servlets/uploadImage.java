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
import java.util.Arrays;
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

 



import com.projet.outiles.ObjetImage;
import com.sun.tools.javac.code.Attribute.Array;

import services.ImageRepository;
import services.SignatureRepository;
import dao.Image;
import dao.Signature;
import util.Utils;

/**
 * Servlet implementation class uploadImage
 */
 
// taille maximal du fichier
@WebServlet(name = "upload", description = "récupérer une image ,calculer ces signature et la stocker dans la base de données", urlPatterns = { "/upload" })
@MultipartConfig(maxFileSize = 16177215) 
public class  uploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger( uploadImage.class
			.getCanonicalName());
	File destinationDir = new File("/tmp");

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//final String filePath = request.getParameter("image");
		final Part filePart = request.getPart("image");
		final String fileName = getFileName(filePart);
		OutputStream out = null;
		InputStream filecontent = null;
		
		 final PrintWriter writer = response.getWriter();
		 File tmpFile = new File(destinationDir + File.separator+fileName);
       
             
		try {
			 
		//	System.out.println(filePath.substring(filePath.lastIndexOf("\\")+1)  );
			  out = new FileOutputStream(tmpFile);

			  filecontent = filePart.getInputStream();
			
			
			 
			  int read = 0;
			  final byte[] bytes = new byte[1024];
			    
			  while ((read = filecontent.read(bytes)) != -1) { 
				  out.write(bytes,0, read); }
			  
			// writer.println("New file " + fileName + " created at " +
			// destinationDir);

			LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
					new Object[] { fileName });
         
		 Image img=new Image( readBytesFromFile(tmpFile)  ,fileName );
			// System.out.println("iciiiiii"+img.getFichier().exists());
			/*
			 * calcule de la signature ,et enregistrement
			 */
			 
 
		 Signature sig=new Signature();
		  sig= Utils.calculerSignature(tmpFile);
		  img.setSignature(sig);
		  sig.setImage(img);
		  ImageRepository.addImage(img );
		/*  getServletContext().getRequestDispatcher( "/success.jsp").forward(request,response);
		*/ 
		  //request.getRequestDispatcher("/success.jsp").forward(request, response);
		//
		  
		  RequestDispatcher requestDispatcher; 
		  requestDispatcher = request.getRequestDispatcher("/success.jsp");
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
	        throw new IOException("Could not completely read file " + file.getName() + " as it is too long (" + length + " bytes, max supported " + Integer.MAX_VALUE + ")");
	      }
	  
	      // Create the byte array to hold the data
	      byte[] bytes = new byte[(int)length];
	  
	      // Read in the bytes
	      int offset = 0;
	      int numRead = 0;
	      while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	          offset += numRead;
	      }
	  
	      // Ensure all the bytes have been read in
	      if (offset < bytes.length) {
	          throw new IOException("Could not completely read file " + file.getName());
	      }
	  
	      // Close the input stream and return bytes
	      is.close();
	      return bytes;
	  }
}
