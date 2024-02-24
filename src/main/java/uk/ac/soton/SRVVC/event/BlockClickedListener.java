package uk.ac.soton.SRVVC.event;

import uk.ac.soton.SRVVC.component.GameBlock;

/**
 * The Block Clicked listener is used to handle the event when a block in a GameBoard is clicked. It passes the
 * GameBlock that was clicked in the message
 */
public interface BlockClickedListener {

    /**
     * Handle a block clicked event
     * @param block the block that was clicked
     */
    public void blockClicked(GameBlock block);
}
