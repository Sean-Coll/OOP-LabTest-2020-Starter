package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	public ArrayList<Task> tasks = new ArrayList<Task>();

	float rightMargin = 0;
	float leftMargin = 0;
	float mouseClickX = 0;
	
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
		// println("Mouse pressed");
		float barStart = 0;
		float barEnd = 0;
		float barY = 0;
		float barH = height * 0.07f;
		mouseClickX = mouseX;

	}

	public void mouseDragged()
	{
		// println("Mouse dragged");
		float barStart = 0;
		float barEnd = 0;
		float barY = 0;
		float barH = height * 0.07f;
		// float mouseDragX = mouseX;
		for(int i = 0; i < tasks.size(); i ++)
		{
			Task t = tasks.get(i);
			barStart = map(t.getStart(), 1, 30, rightMargin, leftMargin);
			barEnd = map(t.getEnd(), 1, 30, rightMargin, leftMargin);
			barY = map(i, 0, tasks.size(), height * 0.15f, height - height * 0.15f);
			if(mouseX > barStart - 20 && mouseX <= barStart + 20 && mouseY >= (barY - barH / 2) && mouseY <= (barY + barH / 2))
			{
				System.out.println("Dragging something from left");
			
				if(t.getStart() > 1 && mouseX < barStart - 10)
				{
						t.setStart(t.getStart() - 1);
				}
				if(t.getStart() < 30 && mouseX > barStart + 10)
				{
					if(t.getStart() != (t.getEnd() - 1))
						t.setStart(t.getStart() + 1);
				}
			}
	
			if(mouseX > barEnd - 20 && mouseX <= barEnd + 20 && mouseY >= (barY - barH / 2) && mouseY <= (barY + barH / 2))
			{
				System.out.println("Dragging something from right");
				
				if(t.getEnd() > 1 && mouseX < barEnd - 10)
				{
					if(t.getStart() != (t.getEnd() - 1))
						t.setEnd(t.getEnd() - 1);
				}
				if(t.getEnd() < 30 && mouseX > barEnd + 10)
				{
						t.setEnd(t.getEnd() + 1);
				}
			}
		}
		
	}

	public void displayTasks()
	{
		float x = 0;
		float textY = height * 0.05f;
		float lineY = height * 0.075f;

		float taskY = height * 0.15f;
		float barStart = 0;
		float barEnd = 0;
		float barY = 0;
		float barH = height * 0.07f;
		float hue = 0;
		float barW = 0;

		for(int i = 1; i <= 30; i++)
		{
			x = map(i, 1, 30, rightMargin, leftMargin);
			fill(255);
			textAlign(CENTER, CENTER);
			text(i, x, textY);
			stroke(255);
			line(x, lineY, x, height - lineY);
		}

		for(int i = 0; i < tasks.size(); i++)
		{
			Task t = tasks.get(i);
			barY = map(i, 0, tasks.size(), taskY, height - taskY);
			fill(255);
			textAlign(LEFT, CENTER);
			text(t.getTask(), rightMargin / 4, barY);
			hue = map(i, 0, tasks.size(), 0, 255);
			fill(hue, 255, 255);
			noStroke();
			barStart = map(t.getStart(), 1, 30, rightMargin, leftMargin);
			barEnd = map(t.getEnd(), 1, 30, rightMargin, leftMargin);
			barW = barEnd - barStart;
			rect(barStart, barY - barH / 2, barW, barH, 5);
		}
	}
	
	public void setup() 
	{
		loadTasks();
		printTasks();
		fill(255);
		stroke(255);
		colorMode(HSB);
		rightMargin = width * 0.15f;
		leftMargin = width - (width * 0.05f);
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
