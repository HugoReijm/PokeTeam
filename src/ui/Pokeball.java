package ui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Pokeball extends StackPane {
	public Pokeball(int radius)
	{
		Arc top = new Arc();
        top.setRadiusX(radius);
        top.setRadiusY(radius);
        top.setStartAngle(0);
        top.setLength(180);
        top.setFill(Color.RED);
        
        Rectangle center = new Rectangle(2*radius,radius/10);
        center.setTranslateY(radius/2);
        
        Circle largeCenterCircle = new Circle(0.35*radius);
        largeCenterCircle.setTranslateY(radius/2);
        
        Circle smallCenterCircle = new Circle(0.25*radius);
        smallCenterCircle.setTranslateY(radius/2);
        smallCenterCircle.setFill(Color.WHITE);
        
        Circle tinyCenterCircle = new Circle(0.15*radius);
        tinyCenterCircle.setTranslateY(radius/2);
        
        Arc bottom = new Arc();
        bottom.setRadiusX(radius);
        bottom.setRadiusY(radius);
        bottom.setStartAngle(180);
        bottom.setLength(180);
        bottom.setTranslateY(radius);
        bottom.setFill(Color.WHITE);
        
        getChildren().addAll(bottom,top,center,largeCenterCircle,smallCenterCircle,tinyCenterCircle);
	}
}
