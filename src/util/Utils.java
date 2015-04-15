package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import services.ImageForWeb;
 

import com.projet.outiles.StaticValues;
import com.projet.outiles.Utiles;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import dao.Image;
import dao.SignaturNorm;
import dao.Signatures;

public class Utils {

	private int rg;
	private int by;
	private int wb;

	public static Signatures calculerSignature(File img) {
		int[] tabRg = new int[8];
		int[] tabBy = new int[16];
		int[] tabWb = new int[16];

		int rg = 0;
		int by = 0;
		int wb = 0;
		Signatures sig;
		BufferedImage image = null;
		try {
			image = ImageIO.read(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int w = image.getWidth();
		int h = image.getHeight();

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				/* Acc�der au rgb pixel (i, j) */
				int rgb = image.getRGB(i, j);
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = rgb & 0xff;

				rg = r - g;
				by = 2 * b - r - g;
				wb = r + g + b;
				int a = deffinirIntervale(255, 8, rg);
				int b1 = deffinirIntervale(1024, 16, by);
				int c = deffinirIntervale(768, 16, wb);
				tabRg[a] += 1;

				tabBy[b1] += 1;
				tabWb[c] += 1;

			}

		}
		sig = new Signatures(tabRg, tabBy, tabWb);
		return sig;
	}

	public static int deffinirIntervale(int intervaleTotale, int nbrDeDivision,
			int nombre) {

		int j = 0;
		int inter = intervaleTotale * 2 / nbrDeDivision;
		for (int i = -intervaleTotale; i <= intervaleTotale; i += inter) {
			if (nombre >= i && nombre <= i + inter) {
				i = intervaleTotale;
			} else {
				i++;
				j++;
			}
		}

		return j;
	}

	private void calculerValeur(int r, int g, int b) {
		this.rg = r - g;
		this.by = 2 * b - r - g;
		this.wb = r + g + b;
	}

	// calcule de la distance eucludienne entre les deux valeurs
	public static double calculerDistanceEuclidienne(Signatures sig1,
			Signatures sig2) {
		double chi2Res = 0;

		SignaturNorm sig1N = normaliser(sig1);
        
		SignaturNorm sig2N = normaliser(sig2);
		 

		double resRg = 0, resBy = 0, resWb = 0;

		resRg = (Math.sqrt(soustPowSum(sig2N.getTabRG(), sig1N.getTabRG())));

		resBy = (Math.sqrt(soustPowSum(sig2N.getTabBY(), sig1N.getTabBY())));

		resWb = (Math.sqrt(soustPowSum(sig2N.getTabWB(), sig1N.getTabWB())));

		chi2Res = (float) ((resRg + resBy + resWb) / 3);
		return chi2Res;
	}

	private static double soustPowSum(double[] v1, double[] v2) {
		double rs = 0;
		for (int i = 0; i < v1.length; i++) rs = (float) Math.pow((v1[i] - v2[i]), 2);
		return rs;

	}

	private static SignaturNorm normaliser(Signatures sig) {
		SignaturNorm sigNorm;
		sigNorm = new SignaturNorm(normaliserVecteur(sig.getTabRG()),
				normaliserVecteur(sig.getTabBY()),
				normaliserVecteur(sig.getTabWB()));

		return sigNorm;

	}

	private static double[] normaliserVecteur(int[] v) {
		double[] vResult = new double[v.length];
		for (int i = 0; i < v.length; i++){
			vResult[i] = v[i] / LongeurVecteur(v);
		}
		return vResult; 
	}

	private static double LongeurVecteur(int[] v) {
		double somme = 0;
		for (int i = 0; i < v.length; i++)
		{
			somme += Math.pow( v[i],2);
		
		 
		}
		return (double) Math.sqrt(somme);

	}

public static   ImageForWeb encodeForWeb(Image img){
	
	String encoded = Base64.encode(img.getFichier());
	String encodedString = new String(encoded);

	ImageForWeb imgToWeb = new ImageForWeb(img.getId(),  
			img.getName(), encodedString);

	
	return imgToWeb;
	
}


}
