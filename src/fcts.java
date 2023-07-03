import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

class fcts{
	//fct for fonts :
			public Font myFont(String Path) {
				//***********************************Font area**************************
			     
		        try {
		            // Specify the path to the font file
		        	File fontFile=new File(Path);
		            
		            // Create a Font object from the font file
		        	Font customFont;
		            return customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		            
		        } catch (IOException | FontFormatException e) {
		            e.printStackTrace();
		            return null;
		        } //*****************************************************
			}
			
			
			
			
}
