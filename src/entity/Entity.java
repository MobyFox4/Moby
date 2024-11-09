package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Gamepanel;
import main.UtilityTool;

public class Entity {

    Gamepanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, attackLeft, attackRight;
    public BufferedImage image, image2;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    String[] dialogues = new String[30];
    public boolean collision = false;
    public int worldX;
    public int worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean invincible = false;
    public boolean collisionOn = false;
    boolean attacking = false;
    public boolean knockBack = false;
    public boolean grow = false;
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int knockBackCounter = 0;
    public int defaultSpeed;
    public int speed;
    public String name;
    public int maxHitPoint;
    public int hitpoint;
    public int attack;
    public Entity currentWeapon;
    public int attackValue;
    public String description = "";

    // TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_ghost = 2;
    public final int type_knife = 3;
    public final int type_axe = 4;
    public final int type_obstacle = 5;
    public final int type_consumable = 6;
    public final int type_scissors = 7;

    public Entity(Gamepanel gp) {
        this.gp = gp;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol() {
        return (worldX + solidArea.x) / gp.tileSize;
    }

    public int getRow() {
        return (worldY + solidArea.y) / gp.tileSize;
    }

    public boolean use(Entity entity) {
        return false;
    }

    public void setAction() {
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }

    }

    public void interact() {
    }

    public void update() {
        if (knockBack == true) {

            checkCollision();

            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else if (collisionOn == false) {
                switch (gp.player.direction) {
                    case "up":worldY -= speed;break;
                    case "down":worldY += speed;break;
                    case "left":worldX -= speed;break;
                    case "right":worldX += speed;break;
                }
            }

            ++knockBackCounter;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        } 
        else {
            setAction();
            checkCollision();
            
            if(collisionOn == false){
                switch (direction) {
                    case "up":worldY -= speed;break;
                    case "down":worldY += speed;break;
                    case "left":worldX -= speed;break;
                    case "right":worldX += speed;break;
                }
            }
        }

        ++spriteCounter;
        if (spriteCounter > 14) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }

            spriteCounter = 0;
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.ghost);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if (type == type_ghost && contactPlayer && gp.player.invincible == false) {
            gp.playSE(0);
            gp.player.hitpoint -= 25;
            gp.player.invincible = true;
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX) {
            if (worldX - gp.tileSize < gp.player.worldX + gp.player.screenX) {
                if (worldY + gp.tileSize > gp.player.worldY - gp.player.screenY) {
                    if (worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                        
                        switch (direction) {
                            case "up":
                                if (spriteNum == 1) {
                                    image = up1;
                                }
                                if (spriteNum == 2) {
                                    image = up2;
                                }
                                break;
                            case "down":
                                if (spriteNum == 1) {
                                    image = down1;
                                }
                                if (spriteNum == 2) {
                                    image = down2;
                                }
                                break;
                            case "left":
                                if (spriteNum == 1) {
                                    image = left1;
                                }
                                if (spriteNum == 2) {
                                    image = left2;
                                }
                                break;
                            case "right":
                                if (spriteNum == 1) {
                                    image = right1;
                                }
                                if (spriteNum == 2) {
                                    image = right2;
                                }
                                break;
                        }

                        if (invincible) {
                            g2.setComposite(AlphaComposite.getInstance(3, 0.4F));
                        }
                        
                        g2.drawImage(image, screenX, screenY, 48, 48, null);
                        g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
                    }
                }
            }
        }

    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, 48, 48);
        } catch (IOException e) {
            System.out.println("Error loading image at path: " + "/res" + imagePath + ".png");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Image not found at path: " + "/res" + imagePath + ".png");
            e.printStackTrace();
        }

        return scaledImage;
    }

    public int getDetected(Entity user, Entity[][] targer, String targetName) {
        int index = 999;
        
        // Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();
        
        switch (user.direction) {
            case "up":nextWorldY = user.getTopY() - 10;break;
            case "down":nextWorldY = user.getBottomY() + 10;break;
            case "left":nextWorldX = user.getLeftX() - 10;break;
            case "right":nextWorldX = user.getRightX() + 10;break;  
        }

        int col = nextWorldX / gp.tileSize;
        int row = nextWorldY / gp.tileSize;

        for (int i = 0; i < targer[1].length; ++i) {
            if (targer[gp.currentMap][i] != null && targer[gp.currentMap][i].getCol() == col && targer[gp.currentMap][i].getRow() == row && targer[gp.currentMap][i].name.equals(targetName)) {
                index = i;
                break;
            }
        }

        return index;
    }
}
