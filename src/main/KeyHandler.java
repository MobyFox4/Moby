package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    Gamepanel gp;
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean enterPressed;
    public boolean rPressed;
    public boolean spacePressed;
    boolean checkDrawTime = false;

    public KeyHandler(Gamepanel gp) {
        this.gp = gp;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState) {
            titleState(code);
        }
        if (gp.gameState == gp.playState) {
            playState(code);
        } 
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        } 
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        } 
        else if (gp.gameState == gp.inventorystate) {
            inventoryState(code);
        } 
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        } 
        else if (gp.gameState == gp.passState) {
            passState(code);
        } 
        else if (gp.gameState == gp.winState) {
            winState(code);
        }

    }

    public void titleState(int code) {

        if (code == KeyEvent.VK_W) {
            gp.playSE(8);
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.playSE(8);
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.currentMap = 0;
                gp.setupGame();
                gp.player.getPlayerAttackImage();
                gp.gameState = gp.playState;
                gp.playSE(4);
            }

            if (gp.ui.commandNum == 1) {
                System.exit(0);
            }
        }

    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.pauseState;
        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (code == KeyEvent.VK_R) {
            rPressed = true;
        }

        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        if (code == KeyEvent.VK_I) {
            gp.gameState = gp.inventorystate;
        }

        if (code == KeyEvent.VK_DELETE) {
            System.exit(0);
        }

    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (code == KeyEvent.VK_W) {
            gp.playSE(8);
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 3;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.playSE(8);
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 3) {
                gp.ui.commandNum = 0;
            }
        }

        if (code == 65 && gp.ui.subState == 0 && gp.ui.commandNum == 1 && gp.sound.volumeScale > 0) {
            gp.sound.volumeScale--;
            gp.playSE(8);
        }

        if (code == 68 && gp.ui.subState == 0 && gp.ui.commandNum == 1 && gp.sound.volumeScale < 5) {
            gp.sound.volumeScale++;
            gp.playSE(8);
        }

    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }

    }

    public void inventoryState(int code) {
        if (code == KeyEvent.VK_I || code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }

        if (code == KeyEvent.VK_W && gp.ui.slotRow != 0) {
            gp.playSE(8);
            --gp.ui.slotRow;
        }

        if (code == KeyEvent.VK_A && gp.ui.slotCol != 0) {
            gp.playSE(8);
            --gp.ui.slotCol;
        }

        if (code == KeyEvent.VK_S && gp.ui.slotRow != 3) {
            gp.playSE(8);
            ++gp.ui.slotRow;
        }

        if (code == KeyEvent.VK_D && gp.ui.slotCol != 4) {
            gp.playSE(8);
            ++gp.ui.slotCol;
        }

        if (code == KeyEvent.VK_ENTER) {
            gp.playSE(7);
            gp.player.selectItem();
        }

    }

    public void gameOverState(int code) {

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }

            gp.playSE(8);
        }

        if (code == KeyEvent.VK_S) {
            ++gp.ui.commandNum;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }

            gp.playSE(8);
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.currentMap = 0;
                gp.setupGame();
                gp.player.getPlayerAttackImage();
                gp.gameState = gp.playState;
                gp.playSE(4);
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = 0;
                gp.playSE(4);
            }
        }

    }

    public void passState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }

            gp.playSE(8);
        }

        if (code == KeyEvent.VK_S) {
            ++gp.ui.commandNum;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }

            gp.playSE(8);
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.currentMap++;
                gp.gameState = gp.playState;
                gp.playSE(4);
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.playSE(4);
            }
        }

    }

    public void winState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }

            gp.playSE(8);
        }

        if (code == KeyEvent.VK_S) {
            ++gp.ui.commandNum;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }

            gp.playSE(8);
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.currentMap = 0;
                gp.setupGame();
                gp.gameState = 1;
                gp.player.getPlayerAttackImage();
                gp.playSE(4);
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = 0;
                gp.playSE(4);
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_R) {
            rPressed = false;
        }

    }
}
