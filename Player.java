import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 28;
        solidArea.height = 28;

        setDefaultValue();
        getPlayerImage();
    }

    public void setDefaultValue() {
        x = 100;
        y = 100;
        speed = 4;
        v_speed = 0;
        direction = "right";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("res/B_V_Right.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("res/B_V_Right.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("res/B_V_Right.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("res/B_V_Left.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("res/B_Left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("res/B_Left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("res/B_Right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("res/B_Right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // if collsion is false, player can move
            if (!collisionOn) {
                y += 3;
                switch (direction) {
                    case "right":
                        x += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "up":
                        y -= speed;
                        break;

                }
            } else if (collisionOn) {
                switch (direction) {
                    case "right":
                        if (!rightCollision) {
                            x += speed;
                        }
                    case "left":
                        if (!leftCollision) {
                            x -= speed;
                        }
                }
            }


            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.WHITE);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}