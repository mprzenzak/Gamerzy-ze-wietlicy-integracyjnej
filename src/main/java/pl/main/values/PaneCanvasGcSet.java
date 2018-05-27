package pl.main.values;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class PaneCanvasGcSet {

	private Pane pane;
	private Canvas canvas;
	private GraphicsContext gc;
	
	public PaneCanvasGcSet(Pane pane, Canvas canvas, GraphicsContext gc) {
		this.pane = pane;
		this.canvas = canvas;
		this.gc = gc;
	}

	public Pane getPane() {
		return pane;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public GraphicsContext getGc() {
		return gc;
	}
	
	
}
