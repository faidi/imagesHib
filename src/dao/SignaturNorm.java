package dao;

public class SignaturNorm {
private float[] tabRG;
private float[] tabBY;
private float[] tabWB;
public SignaturNorm() {
	super();
	// TODO Auto-generated constructor stub
}

 
public SignaturNorm(float[] tabRG, float[] tabBY, float[] tabWB) {
	super();
	this.tabRG = tabRG;
	this.tabBY = tabBY;
	this.tabWB = tabWB;
}
public float[] getTabRG() {
	return tabRG;
}
public float[] getTabBY() {
	return tabBY;
}
public float[] getTabWB() {
	return tabWB;
}
public void setTabRG(float[] tabRG) {
	this.tabRG = tabRG;
}
public void setTabBY(float[] tabBY) {
	this.tabBY = tabBY;
}
public void setTabWB(float[] tabWB) {
	this.tabWB = tabWB;
}
}
