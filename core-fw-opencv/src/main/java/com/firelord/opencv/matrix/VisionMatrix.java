package com.firelord.opencv.matrix;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

@ToString
public class VisionMatrix {
    //#region Fields

    @Setter
    @Getter
    private Mat mat;

    //#endregion

    //#region Construction

    public VisionMatrix() {
        this.mat = new Mat();
    }

    public VisionMatrix(VisionMatrix oSrc) {
        this.mat = Mat.eye(oSrc.getMat().size(), oSrc.getMat().type());
    }

    public VisionMatrix(String strFilePath) {
        this.mat = Imgcodecs.imread(strFilePath, Imgcodecs.IMREAD_COLOR);
    }

    //#endregion

    //#region save

    public void save(String strFilePath) {
        Imgcodecs.imwrite(strFilePath, this.mat);
    }

    //#endregion

    //#region destroy

    public void destroy() {
        this.mat.release();
    }

    //#endregion

    //#region threshold

    public void threshold(VisionMatrix oGray, VisionMatrix oBinary) {
        Imgproc.cvtColor(this.getMat(), oGray.getMat(), Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(oGray.getMat(), oBinary.getMat(),
                0, 255,
                Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
    }

    //#endregion

    //#region isEmpty

    public boolean isEmpty() {
        return this.getMat().empty();
    }

    //#endregion

    //#region toBufferImg

    public BufferedImage toBufferImg() {
        BufferedImage oBufferedImage = null;

        int iWidth = this.getMat().width();
        int iHeight = this.getMat().height();
        int iChannels = this.getMat().channels();
        byte[] arrSourcePixels = new byte[iWidth * iHeight * iChannels];
        this.getMat().get(0, 0, arrSourcePixels);
        if (this.getMat().channels() > 1) {
            oBufferedImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_3BYTE_BGR);
        } else {
            oBufferedImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_BYTE_GRAY);
        }
        final byte[] arrTargetPixels = ((DataBufferByte) oBufferedImage.getRaster().getDataBuffer()).getData();
        System.arraycopy(arrSourcePixels, 0, arrTargetPixels,
                0, arrSourcePixels.length);

        return oBufferedImage;
    }

    //#endregion
}
