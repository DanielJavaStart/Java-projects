package alchemyDproj;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Element {
private String name,imageName;
public int x,y;


private Image image;
boolean isOpened;

public Element(String name, String imageName) {
	this.name = name;
	this.imageName = imageName;
	image = new ImageIcon(imageName).getImage();
	isOpened=false;
	x=0;y=0;
}

public String getName() {
	return name;
}

public String getImageName() {
	return imageName;
}
public Image getImage() {
	return image;
}

@Override
public String toString() {
	return "Element [name=" + name + ", imageName=" + imageName + ", image=" + image + "]";
}

}
