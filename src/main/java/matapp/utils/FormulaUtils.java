package matapp.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import net.sourceforge.jeuclid.LayoutContext;
import net.sourceforge.jeuclid.context.LayoutContextImpl;
import net.sourceforge.jeuclid.context.StyleAttributeLayoutContext;
import net.sourceforge.jeuclid.converter.Converter;
import uk.ac.ed.ph.snuggletex.SnuggleEngine;
import uk.ac.ed.ph.snuggletex.SnuggleInput;
import uk.ac.ed.ph.snuggletex.SnuggleSession;

public class FormulaUtils {
	/**
	 * @author Francisco Vargas, Kilian González
	 * @param formula
	 * @param size
	 * @param forecolor
	 * @return
	 * @throws IOException
	 */
	public static Image formulaToImage(String formula, int size, Color forecolor) throws IOException {
		
		SnuggleSession session = new SnuggleEngine().createSession();
    	SnuggleInput input = new SnuggleInput("$$" + formula + "$$");
		session.parseInput(input);
		LayoutContext layout = new StyleAttributeLayoutContext(LayoutContextImpl.getDefaultLayoutContext(), size + "pt", forecolor);
    	BufferedImage renderedImage = Converter.getInstance().render(session.buildDOMSubtree().item(0), layout);
    	return SwingFXUtils.toFXImage(renderedImage, null);		
	}

}
