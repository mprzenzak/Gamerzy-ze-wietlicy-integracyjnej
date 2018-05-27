package pl.main.values;

public class ScreenAndPaneDimensions {

	private double paneHeight;
	private double paneWidth;
	private double screenHeight;
	private double screenWidth;
	
	public ScreenAndPaneDimensions(double paneHeight, double paneWidth, double screenHeight, double screenWidth) {
		this.paneHeight = paneHeight;
		this.paneWidth = paneWidth;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}

	public double getPaneHeight() {
		return paneHeight;
	}

	public double getPaneWidth() {
		return paneWidth;
	}

	public double getScreenHeight() {
		return screenHeight;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	
}
