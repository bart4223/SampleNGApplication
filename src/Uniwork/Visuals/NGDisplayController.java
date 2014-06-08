package Uniwork.Visuals;

import Uniwork.Base.NGObject;
import Uniwork.Graphics.NGPoint2D;
import Uniwork.Misc.NGImageList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static java.lang.Math.abs;

public class NGDisplayController extends NGObject {

    protected Boolean FInitialized;
    protected GraphicsContext FGC;
    protected Canvas FCanvas;
    protected String FName;
    protected NGPoint2D FPosition;
    protected int FPixelSize;
    protected Color FBackgroundColor;
    protected double FWidth;
    protected double FHeight;
    protected String FImageName;
    protected Image FImage;

    protected void drawPixel(int aX, int aY, Color aColor) {
        int x = aX * FPixelSize;
        int y = aY * FPixelSize;
        if (FImageName.length() == 0) {
            FGC.setFill(aColor);
            FGC.fillRect(x, y, FPixelSize, FPixelSize);
        }
        else {
            if (FImage == null) {
                FImage = NGImageList.getGlobalImage(FImageName);
            }
            if (FImage != null) {
                FGC.drawImage(FImage, x, y, FPixelSize, FPixelSize);
            }
        }
    }

    protected void drawLine(int aX0, int aY0, int aX1, int aY1, Color aColor) {
        int dx =  abs(aX1 - aX0), sx = aX0 < aX1 ? 1 : -1;
        int dy = -abs(aY1 - aY0), sy = aY0 < aY1 ? 1 : -1;
        int err = dx + dy, e2;
        for(;;){
            drawPixel(aX0, aY0, aColor);
            if (aX0 == aX1 && aY0 == aY1)
                break;
            e2 = 2 * err;
            if (e2 > dy) { err += dy; aX0 += sx; }
            if (e2 < dx) { err += dx; aY0 += sy; }
        }
    }

    protected void drawRectangle(int aX, int aY, int aA, int aB, Color aColor) {
        int dx = aA/2;
        int dy = aB/2;
        int TLX = aX-dx;
        int TLY = aY-dy;
        int BRX = aX+aA-dx;
        int BRY = aY+aB-dy;
        drawLine(TLX, TLY, BRX, TLY, aColor);
        drawLine(BRX, TLY, BRX, BRY, aColor);
        drawLine(TLX, TLY, TLX, BRY, aColor);
        drawLine(TLX, BRY, BRX, BRY, aColor);
    }

    protected void drawEllipse(int aX, int aY, int aRadiusX, int aRadiusY, Color aColor) {
        int dx = 0, dy = aRadiusY;
        long a2 = aRadiusX*aRadiusX, b2 = aRadiusY*aRadiusY;
        long err = b2-(2*aRadiusY-1)*a2, e2;
        do {
            drawPixel(aX + dx, aY + dy, aColor);
            drawPixel(aX - dx, aY + dy, aColor);
            drawPixel(aX - dx, aY - dy, aColor);
            drawPixel(aX + dx, aY - dy, aColor);

            e2 = 2*err;
            if (e2 <  (2*dx+1)*b2) { dx++; err += (2*dx+1)*b2; }
            if (e2 > -(2*dy-1)*a2) { dy--; err -= (2*dy-1)*a2; }
        } while (dy > 0);
        dx--;
        while (dx++ < aRadiusX) {
            drawPixel(aX + dx, aY, aColor);
            drawPixel(aX - dx, aY, aColor);
        }
    }

    protected void drawCircle(int aX, int aY, int aRadius, Color aColor) {
        int f = 1 - aRadius;
        int ddF_x = 0;
        int ddF_y = -2 * aRadius;
        int x = 0;
        int y = aRadius;
        drawPixel(aX, aY + aRadius, aColor);
        drawPixel(aX, aY - aRadius, aColor);
        drawPixel(aX + aRadius, aY, aColor);
        drawPixel(aX - aRadius, aY, aColor);
        while(x < y)
        {
            if(f >= 0)
            {
                y--;
                ddF_y += 2;
                f += ddF_y;
            }
            x++;
            ddF_x += 2;
            f += ddF_x + 1;
            drawPixel(aX + x, aY + y, aColor);
            drawPixel(aX - x, aY + y, aColor);
            drawPixel(aX + x, aY - y, aColor);
            drawPixel(aX - x, aY - y, aColor);
            drawPixel(aX + y, aY + x, aColor);
            drawPixel(aX - y, aY + x, aColor);
            drawPixel(aX + y, aY - x, aColor);
            drawPixel(aX - y, aY - x, aColor);
        }
    }

    protected void fillCircle(int aX, int aY, int aRadius, Color aColor) {
        int f = 1 - aRadius;
        int ddF_x = 0;
        int ddF_y = -2 * aRadius;
        int x = 0;
        int y = aRadius;
        drawLine(aX + aRadius, aY, aX - aRadius, aY, aColor);
        while(x < y)
        {
            if(f >= 0)
            {
                y--;
                ddF_y += 2;
                f += ddF_y;
            }
            x++;
            ddF_x += 2;
            f += ddF_x + 1;
            drawLine(aX + x, aY + y, aX - x, aY + y, aColor);
            drawLine(aX + x, aY - y, aX - x, aY - y, aColor);
            drawLine(aX + y, aY + x, aX - y, aY + x, aColor);
            drawLine(aX + y, aY - x, aX - y, aY - x, aColor);
        }
    }

    protected void RecalculateDimensions() {
    }

    protected void InternalUpdate() {
        RecalculateDimensions();
    }

    protected void InternalRender() {
        BeforeRender();
        DoRender();
        AfterRender();
    }

    protected void DoBeforeRender() {

    }

    protected void BeforeRender() {
        DoBeforeRender();
    }

    protected void DoRender() {

    }

    protected void DoAfterRender() {

    }

    protected void AfterRender() {
        DoAfterRender();
    }


    protected void InternalInitialize() {
        BeforeInitialize();
        DoInitialize();
        AfterInitialize();
    }

    protected void DoInitialize() {

    }

    protected void DoAfterInitialize() {

    }

    protected void AfterInitialize() {
        DoAfterInitialize();
        InternalUpdate();
    }

    protected void DoBeforeInitialize() {

    }

    protected void BeforeInitialize() {
        DoBeforeInitialize();
    }

    @Override
    public void setProperty(Object aObject, String aName, java.lang.Object aValue) {
        super.setProperty(aObject, aName, aValue);
        InternalUpdate();
    }

    public NGDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }


    public NGDisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, new NGPoint2D(0, 0));
    }

    public NGDisplayController(Canvas aCanvas, String aName, NGPoint2D aPosition) {
        super();
        FInitialized = false;
        FCanvas = aCanvas;
        FName = aName;
        FWidth = 0;
        FHeight = 0;
        if (aCanvas != null) {
            FWidth = aCanvas.getWidth();
            FHeight = aCanvas.getHeight();
        }
        FPosition = aPosition;
        FBackgroundColor = Color.WHITE;
        FPixelSize = 1;
        BaseWidth = 0;
        BaseHeight = 0;
        FGC = null;
        FImageName = "";
        FImage = null;
    }

    public int BaseWidth;
    public int BaseHeight;

    public void Initialize() {
        if (!FInitialized) {
            if (FCanvas != null) {
                FGC = FCanvas.getGraphicsContext2D();
            }
            InternalInitialize();
            FInitialized = true;
        }
    }

    public void Render() {
        if (FInitialized) {
            InternalRender();
        }
    }

    public String getName() {
        return FName;
    }

    public void setPixelSize(int aValue) {
        FPixelSize = aValue;
        InternalUpdate();
    }

    public int getPixelSize() {
        return FPixelSize;
    }

    public NGPoint2D getPosition() {
        return FPosition;
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
        InternalUpdate();
    }

    public void setBackgroundColor(Color aValue) {
        FBackgroundColor = aValue;
        InternalUpdate();
    }

    public Color getBackgroundColor() {
        return FBackgroundColor;
    }

    public double getWidth() {
        return FWidth;
    }

    public double getHeight() {
        return FHeight;
    }

}
