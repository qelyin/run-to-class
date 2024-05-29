import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;
    public int v_speed;
    public int gravity;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    public boolean downCollision = false;

    public boolean upCollision = false;

    public boolean rightCollision = false;

    public boolean leftCollision = false;

    public boolean inAir = false;

}