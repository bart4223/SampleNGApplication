package Uniwork.Graphics;

import javafx.scene.paint.Color;

public class NGGraphicMisc {

    public static String colorToWeb(Color aColor) {
        return String.format("#%s", colorToHex(aColor));
    }

    public static String colorToHex(Color aColor) {
        return colorChanelToHex(aColor.getRed())
                + colorChanelToHex(aColor.getGreen())
                + colorChanelToHex(aColor.getBlue())
                + colorChanelToHex(aColor.getOpacity());
    }

    private static String colorChanelToHex(double chanelValue) {
        String rtn = Integer.toHexString((int) Math.min(Math.round(chanelValue * 255), 255));
        if (rtn.length() == 1) {
            rtn = "0" + rtn;
        }
        return rtn;
    }

}
