/*
	Name: Sumeir Khinda
	Student ID: V00933760
*/ 

import java.awt.Color;
import java.util.*;

public class GraphAlgorithms{

	/* FloodFillDFS(v, writer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillDFS(PixelVertex v, PixelWriter writer, Color fillColour){
		LinkedList<PixelVertex> visitedList = new LinkedList<PixelVertex>();
		LinkedList<PixelVertex> neighbourList = new LinkedList<PixelVertex>();
		Stack<PixelVertex> pixelStack = new Stack<PixelVertex>();
		PixelVertex cur = new PixelVertex(v.getX(), v.getY());

		pixelStack.push(v);

		while (!pixelStack.isEmpty()) {
			cur = pixelStack.pop();
			if (!visitedList.contains(cur)) {
				visitedList.add(cur);
				writer.setPixel(cur.getX(), cur.getY(), fillColour);
				neighbourList = cur.getNeighbours();
				for (int i=0; i<neighbourList.size(); i++) {
					pixelStack.push(neighbourList.get(i));
				}
			}
		}
	}
	
	/* FloodFillBFS(v, writer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, PixelWriter writer, Color fillColour){
		LinkedList<PixelVertex> visitedList = new LinkedList<PixelVertex>();
		LinkedList<PixelVertex> neighbourList = new LinkedList<PixelVertex>();
		Queue<PixelVertex> pixelQueue = new LinkedList<PixelVertex>();
		PixelVertex cur = new PixelVertex(v.getX(), v.getY());
		
		pixelQueue.add(v);
		visitedList.add(v);
		writer.setPixel(v.getX(), v.getY(), fillColour);

		while (!pixelQueue.isEmpty()) {
			cur = pixelQueue.remove();
			neighbourList = cur.getNeighbours();
			for (int i=0; i<neighbourList.size(); i++) {
				if (!visitedList.contains(neighbourList.get(i))) {
					visitedList.add(neighbourList.get(i));
					writer.setPixel(neighbourList.get(i).getX(), neighbourList.get(i).getY(), fillColour);
					pixelQueue.add(neighbourList.get(i));
				}
			}
		}
	}
	
}