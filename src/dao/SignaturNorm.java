package dao;

import java.util.Arrays;

public class SignaturNorm {
private double[] tabRG;
private double[] tabBY;
private double[] tabWB;
public SignaturNorm() {
	super();
	// TODO Auto-generated constructor stub
}

 
public SignaturNorm(double[] tabRG, double[] tabBY, double[] tabWB) {
	super();
	this.tabRG = tabRG;
	this.tabBY = tabBY;
	this.tabWB = tabWB;
}
public double[] getTabRG() {
	return tabRG;
}
public double[] getTabBY() {
	return tabBY;
}
public double[] getTabWB() {
	return tabWB;
}
public void setTabRG(double[] tabRG) {
	this.tabRG = tabRG;
}
public void setTabBY(double[] tabBY) {
	this.tabBY = tabBY;
}
public void setTabWB(double[] tabWB) {
	this.tabWB = tabWB;
}


@Override
public String toString() {
	return "SignaturNorm [tabRG=" + Arrays.toString(tabRG) + ", tabBY="
			+ Arrays.toString(tabBY) + ", tabWB=" + Arrays.toString(tabWB)
			+ "]";
}
}
