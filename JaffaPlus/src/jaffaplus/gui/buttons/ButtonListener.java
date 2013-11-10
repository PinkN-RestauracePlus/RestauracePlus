package jaffaplus.gui.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * ButtonListener se bude starat o změnu grafiky v závislosti na uživatelovo
 * pohybu myší, pokud se myš nachází na tlačítku, přejde tlačítko do stavu
 * Active, pokud je myš stisknutá a zároveň na tlačítku, přejde do stavu
 * Clicked a pokud myš opustí oblast tlačítka, tak se přesnu do stavu
 * Inactive.
 * 
 * @author Hanzik
 */
public class ButtonListener implements MouseListener {

    private Button owner;

    private boolean clicked = false;
    private boolean mouseOver = false;
    
    public ButtonListener(Button owner) {
        this.owner = owner;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked = true;
        owner.setClickedIcon();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicked = false;
        if (mouseOver) {
            owner.setActiveIcon();
            owner.executeButtonClick();
        } else {
            owner.setInactiveIcon();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseOver = true;
        owner.setActiveIcon();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseOver = false;
        clicked = false;
        owner.setInactiveIcon();
    }   
}
