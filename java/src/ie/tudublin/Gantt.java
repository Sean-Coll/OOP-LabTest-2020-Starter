package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	public ArrayList<Task> tasks = new ArrayList<Task>();
	
	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		Table t = loadTable("tasks.csv", "header");

		for(TableRow row:t.rows())
		{
			Task a = new Task(row);
			tasks.add(a);
		}
	}

	public void printTasks()
	{
		for(Task t:tasks)
		{
			System.out.println(t);
		}
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	public void displayTasks()
	{
		float x = 0;
		float textY = height * 0.05f;
		float lineY = height * 0.075f;
		float rightMargin = width * 0.2f;
		float leftMargin = width - (width * 0.05f);

		for(int i = 1; i <= 30; i++)
		{
			x = map(i, 1, 30, rightMargin, leftMargin);
			text(i, x, textY);
			line(x, lineY, x, height - lineY);
		}
	}
	
	public void setup() 
	{
		loadTasks();
		printTasks();
		fill(255);
		stroke(255);
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
