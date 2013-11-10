package jaffaplus.gui.panels;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Hanzik
 */
public class PanelListener implements MouseListener {

    private Panel owner;
    
    public PanelListener(Panel owner) {
        this.owner = owner;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (owner.isSelected()) {
            owner.deselectPanel();
        } else {
            owner.selectPanel();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //do nothing
    }   
}
