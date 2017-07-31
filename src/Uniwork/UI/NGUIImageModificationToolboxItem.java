package Uniwork.UI;

import Uniwork.Appl.NGStageManager;
import Uniwork.Appl.NGToolboxItem;
import Uniwork.Graphics.NGColorOctree;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class NGUIImageModificationToolboxItem extends NGToolboxItem {

    protected NGColorOctree FColorOctree;
    protected BufferedImage FImage;
    protected Integer FOrgColorCount;
    protected Integer FCQColorCount;
    protected Boolean FRebuildTree;

    protected void BuildColorOctreeFromImage() {
        FColorOctree = new NGColorOctree();
        for (int y = 0; y < FImage.getHeight(); y++) {
            for (int x = 0; x < FImage.getWidth(); x++) {
                int color = FImage.getRGB(x, y);
                int  red   = (color & 0x00ff0000) >> 16;
                int  green = (color & 0x0000ff00) >> 8;
                int  blue  =  color & 0x000000ff;
                FColorOctree.addColor(red, green, blue);
            }
        }
        FOrgColorCount = FColorOctree.getLeafCount();
    }

    @Override
    protected void DoReset() {
        super.DoReset();
        setCQColorCount(getOrgColorCount());
    }

    @Override
    protected void BeforeRenderStage() {
        super.BeforeRenderStage();
        if (FRebuildTree) {
            BuildColorOctreeFromImage();
        }
        if (FCQColorCount > 0) {
            FColorOctree.Quantize(FCQColorCount);
        }
    }

    @Override
    protected void DoSetContext(Object aContext) {
        super.DoSetContext(aContext);
        NGUIImageModificationToolboxContext context = (NGUIImageModificationToolboxContext)FContext;
        try {
            File chosenFile = new File(context.getFilename());
            FImage = ImageIO.read(chosenFile);
        }
        catch (Exception e) {
            writeError(e.getMessage());
        }
    }

    public NGUIImageModificationToolboxItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "NGUIImageModificationToolbox.fxml";
        FWidth = 800;
        FHeight = 950;
        FCaption = "Image.Modification";
        FPosition.setX(1500);
        FPosition.setY(100);
        FUnique = true;
        FColorOctree = null;
        FImage = null;
        FCQColorCount = -1;
        FIsDialog = true;
        FRebuildTree = true;
    }

    public NGColorOctree getColorOctree() {
        return FColorOctree;
    }

    public BufferedImage getImage() {
        return FImage;
    }

    public Integer getOrgColorCount() {
        return FOrgColorCount;
    }

    public void setCQColorCount(Integer aCQColorCount) {
        FRebuildTree = aCQColorCount > FCQColorCount;
        FCQColorCount = aCQColorCount;
    }

    public Integer getCQColorCount() {
        return FCQColorCount;
    }

}
