package pl.main.values;

import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class GroupPanesAndGcSet {

	private HashMap<String, Pane> pMap;
	private HashMap<String, GraphicsContext> gcMap;
	
	public GroupPanesAndGcSet(HashMap<String, Pane> pMap, HashMap<String, GraphicsContext> gcMap) {
		
		this.pMap = pMap;
		this.gcMap = gcMap;
	}

	public Pane getPane(String string) {
		return pMap.get(string);
	}

	public GraphicsContext getGc(String string) {
		return gcMap.get(string);
	}	
}
