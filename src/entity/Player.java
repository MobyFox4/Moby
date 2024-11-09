package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.Gamepanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Knife;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public boolean hasHealthpotion;
    public boolean canUsePotion = true;
    public double potionCooldownTimeDefalut = 45.0D;
    public double potionCooldownTime;
    public ArrayList<Entity> inventory;
    public final int maxInventorySize = 20;

    public Player(Gamepanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - gp.tileSize;
        screenY = gp.screenHeight / 2 - gp.tileSize;
        solidArea = new Rectangle();
        solidArea.x = 38;
        solidArea.y = 60;
        solidArea.width = 23;
        solidArea.height = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 36;
        attackArea.height = 36;
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 17 + gp.tileSize / 2;
        worldY = gp.tileSize * 1;
        direction = "right";
        defaultSpeed = 5;
        speed = defaultSpeed;
        maxHitPoint = 100;
        hitpoint = maxHitPoint;
        hasHealthpotion = false;
        inventory = new ArrayList();
        currentWeapon = new OBJ_Knife(gp);
        attack = getAttack();
    }

    public void setItems() {
        inventory.add(currentWeapon);
        //inventory.add(new OBJ_ExitKey(gp));
    }

    public int getAttack() {
        return currentWeapon.attackValue;
    }

    public void getPlayerImage() {
        up1 = setupPlayer("player_right", gp.tileSize * 2, gp.tileSize * 2);
        up2 = setupPlayer("player_right2", gp.tileSize * 2, gp.tileSize * 2);
        down1 = setupPlayer("player_right", gp.tileSize * 2, gp.tileSize * 2);
        down2 = setupPlayer("player_right2", gp.tileSize * 2, gp.tileSize * 2);
        left1 = setupPlayer("player_left", gp.tileSize * 2, gp.tileSize * 2);
        left2 = setupPlayer("player_left2", gp.tileSize * 2, gp.tileSize * 2);
        right1 = setupPlayer("player_right", gp.tileSize * 2, gp.tileSize * 2);
        right2 = setupPlayer("player_right2", gp.tileSize * 2, gp.tileSize * 2);
    }

    public void getPlayerAttackImage() {

        if (currentWeapon.type == 3) {
            attackLeft = setupPlayer("player_Attackleft", gp.tileSize * 2, gp.tileSize * 2);
            attackRight = setupPlayer("player_Attackright", gp.tileSize * 2, gp.tileSize * 2);
        }

        if (currentWeapon.type == 4) {
            attackLeft = setupPlayer("player_AttackAxeleft", gp.tileSize * 2, gp.tileSize * 2);
            attackRight = setupPlayer("player_AttackAxeRight", gp.tileSize * 2, gp.tileSize * 2);
        }

        if (currentWeapon.type == 7) {
            attackLeft = setupPlayer("player_Attackscissorleft", gp.tileSize * 2, gp.tileSize * 2);
            attackRight = setupPlayer("player_Attackscissorright", gp.tileSize * 2, gp.tileSize * 2);
        }

    }

    public BufferedImage setupPlayer(String imageName, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scaledImage;
    }

    public void update() {
        if (keyH.spacePressed) {
            gp.playSE(2);
            attacking = true;
        }

        if (attacking) {
            attacking();
        } else if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed && !keyH.enterPressed) {
            spriteNum = 1;
        } else {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int ghostIndex = gp.cChecker.checkEntity(this, gp.ghost);
            contactGhost(ghostIndex);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            ++spriteCounter;
            if (spriteCounter > 20) {
                if (spriteNum == 1) {
                    gp.playSE(12);
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    gp.playSE(13);
                    spriteNum = 1;
                }

                spriteCounter = 0;
            }
        }

        gp.keyH.enterPressed = false;
        gp.keyH.spacePressed = false;
        if (keyH.rPressed && canUsePotion && hasHealthpotion) {
            if (hitpoint != 100) {
                useHealthPotion();
                potionCooldownTime = potionCooldownTimeDefalut;
            } else if (hitpoint == 100) {
                gp.ui.showMessage("You have already full Health!");
            }
        }

        if (!canUsePotion) {
            potionCooldownTime -= 0.016666666666666666D;
            if (potionCooldownTime <= 0.0D) {
                gp.playSE(7);
                canUsePotion = true;
            }
        }

        if (invincible) {
            ++invincibleCounter;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (hitpoint <= 0 || gp.ui.time <= 0.0D) {
            gp.playSE(10);
            gp.gameState = gp.gameOverState;
        }

    }

    public void interactNPC(int i) {
        if (i != 999 && gp.keyH.enterPressed) {
            gp.gameState = gp.dialogueState;
            gp.npc[gp.currentMap][i].speak();
        }

        gp.keyH.enterPressed = false;
    }

    public void contactGhost(int i) {
        if (i != 999 && !invincible) {
            gp.playSE(0);
            hitpoint -= 25;
            gp.ui.time -= 10.0D;
            gp.ui.showMessage("You got hit time -10 Second!");
            invincible = true;
        }

    }

    public void damageGhost(int i) {
        if (i != 999 && !gp.ghost[gp.currentMap][i].invincible) {
            gp.playSE(5);
            knockBack(gp.ghost[gp.currentMap][i]);
            gp.ghost[gp.currentMap][i].hitpoint -= attack;
            gp.ghost[gp.currentMap][i].invincible = true;
            if (gp.ghost[gp.currentMap][i].hitpoint <= 0) {
                gp.ghost[gp.currentMap][i] = null;
            }
        }

    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }

        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            int ghostIndex = gp.cChecker.checkEntity(this, gp.ghost);
            damageGhost(ghostIndex);
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            if (gp.obj[gp.currentMap][i].type == type_obstacle) {
                if (keyH.enterPressed) {
                    gp.obj[gp.currentMap][i].interact();
                }
            } else {
                String text;
                if (inventory.size() != 20) {
                    if (gp.obj[gp.currentMap][i].name == "Healthpotion") {
                        hasHealthpotion = true;
                    } else {
                        inventory.add(gp.obj[gp.currentMap][i]);
                    }

                    gp.playSE(1);
                    text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
                } else {
                    text = "You cannot carry any more!";
                }

                gp.ui.showMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
        }

    }

    public void knockBack(Entity entity) {
        entity.direction = direction;
        entity.speed += 5;
        entity.knockBack = true;
    }

    public void damageInteractiveTile(int i) {
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible && gp.iTile[gp.currentMap][i].isCorrectItem(this) && !gp.iTile[gp.currentMap][i].invincible) {
            gp.playSE(9);
            gp.iTile[gp.currentMap][i].hitpoint -= attack;
            gp.iTile[gp.currentMap][i].invincible = true;
            if (gp.iTile[gp.currentMap][i].hitpoint == 0) {
                gp.iTile[gp.currentMap][i] = null;
            }
        }

    }

    public void useHealthPotion() {
        gp.playSE(6);
        int temp = maxHitPoint - hitpoint;
        hitpoint += 50;
        if (hitpoint > maxHitPoint) {
            gp.ui.showMessage("HP +" + Integer.toString(temp));
            hitpoint = maxHitPoint;
        } else {
            gp.ui.showMessage("HP +50");
        }

        canUsePotion = false;
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()) {
            if (inventory.get(itemIndex).type == type_knife || inventory.get(itemIndex).type == type_axe || inventory.get(itemIndex).type == type_scissors) {
                currentWeapon = inventory.get(itemIndex);
                attack = getAttack();
                getPlayerAttackImage();
            }

            if (inventory.get(itemIndex).type == type_consumable && inventory.get(itemIndex).use(this)) {
                inventory.remove(itemIndex);
            }
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = up1;
                    }

                    if (spriteNum == 2) {
                        image = up2;
                    }
                }

                if (attacking) {
                    if (spriteNum == 1) {
                        image = attackRight;
                    }

                    if (spriteNum == 2) {
                        image = attackRight;
                    }
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = left1;
                    }

                    if (spriteNum == 2) {
                        image = left2;
                    }
                }

                if (attacking) {
                    if (spriteNum == 1) {
                        image = attackLeft;
                    }

                    if (spriteNum == 2) {
                        image = attackLeft;
                    }
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = left1;
                    }

                    if (spriteNum == 2) {
                        image = left2;
                    }
                }

                if (attacking) {
                    if (spriteNum == 1) {
                        image = attackLeft;
                    }

                    if (spriteNum == 2) {
                        image = attackLeft;
                    }
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = right1;
                    }

                    if (spriteNum == 2) {
                        image = right2;
                    }
                }

                if (attacking) {
                    if (spriteNum == 1) {
                        image = attackRight;
                    }

                    if (spriteNum == 2) {
                        image = attackRight;
                    }
                }
                break;
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(3, 0.3F));
        }

        g2.drawImage(image, screenX, screenY, null);
        g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
    }
}
