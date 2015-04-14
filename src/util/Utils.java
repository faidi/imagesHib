package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.projet.outiles.StaticValues;
import com.projet.outiles.Utiles;

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

		Integer j = 0;
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

	public static float calculeSimilarité(Signatures sig1, Signatures sig2) {

		return (Float) null;
	}

	// calcule de la distance eucludienne entre les deux valeurs
	public static float calculerDistanceEuclidienne(Signatures sig1,
			Signatures sig2) {
		float chi2Res = 0;
		float chi2ResRg = 0 ,chi2ResBy = 0,chi2ResWb = 0;
		SignaturNorm sig1Norm;
		SignaturNorm sig2Norm;
		sig1Norm = normaliser(sig1);
		sig2Norm = normaliser(sig2);
		double resRg = 0, resBy = 0, resWb = 0;

		chi2ResRg += calculerchiRg(sig1Norm, sig2Norm);

		chi2ResBy += calculerchiBy(sig1Norm, sig2Norm);

		chi2ResWb += calculerchiWb(sig1Norm, sig2Norm);

		resRg = (Math.sqrt(chi2ResRg));
		resBy = (Math.sqrt(chi2ResBy));

		resWb = (Math.sqrt(chi2ResWb));

		chi2Res = (float) ((resRg + resBy + resWb) / 3);
		return chi2Res;
	}

	public static float calculerchiRg(SignaturNorm sig1Norm,
			SignaturNorm sig2Norm) {

		float soust = 0;
		for (int i = 0; i < 8; i++) {
			soust = (float) Math.pow(
					sig1Norm.getTabRG()[i] - sig2Norm.getTabRG()[i], 2);
		}
		return soust;
	}

	public static float calculerchiBy(SignaturNorm sig1Norm,
			SignaturNorm sig2Norm) {

		float soust = 0;
		for (int i = 0; i < 16; i++) {
			soust = (float) Math.pow(
					sig1Norm.getTabBY()[i] - sig2Norm.getTabBY()[i], 2);
		}
		return soust;
	}

	public static float calculerchiWb(SignaturNorm sig1Norm,
			SignaturNorm sig2Norm) {

		float soust = 0;
		for (int i = 0; i < 16; i++) {
			soust = (float) Math.pow(
					sig1Norm.getTabWB()[i] - sig2Norm.getTabWB()[i], 2);
		}
		return soust;
	}

	private static SignaturNorm normaliser(Signatures sig) {
		float[] newTabRG;
		float[] newTabBY;
		float[] newTabWB;
		SignaturNorm sigNorm;

		newTabRG = normaliserRg(sig);
		newTabBY = normaliserBy(sig);
		newTabWB = normaliserWb(sig);

		sigNorm = new SignaturNorm(newTabRG, newTabBY, newTabWB);

		return sigNorm;

	}

	private static float[] normaliserRg(Signatures sig) {
		int tabRg[] = new int[8];
		int somme = 0;
		float[] tabByNormaliser = new float[16];
		float poid;
		tabRg = sig.getTabRG();
		for (int i = 0; i < tabRg.length; i++)
			somme += tabRg[i];

		poid = somme / tabRg.length;
		for (int i = 0; i < tabRg.length; i++)
			tabByNormaliser[i] = (float) (tabRg[i] / poid);
		return tabByNormaliser;

	}

	private static float[] normaliserBy(Signatures sig) {
		int tabBy[] = new int[16];
		int somme = 0;
		float[] tabByNormaliser = new float[16];
		float poid;
		tabBy = sig.getTabBY();
		for (int i = 0; i < tabBy.length; i++)
			somme += tabBy[i];

		poid = somme / tabBy.length;
		for (int i = 0; i < tabBy.length; i++)
			tabByNormaliser[i] = (float) (tabBy[i] / poid);
		return tabByNormaliser;
	}

	private static float[] normaliserWb(Signatures sig) {
		int tabWB[] = new int[16];
		int somme = 0;
		float[] tabByNormaliser = new float[16];
		float poid;
		tabWB = sig.getTabWB();
		for (int i = 0; i < tabWB.length; i++)
			somme += tabWB[i];

		poid = somme / tabWB.length;
		for (int i = 0; i < tabWB.length; i++)
			tabByNormaliser[i] = (float) (tabWB[i] / poid);
		return tabByNormaliser;
	}

}
