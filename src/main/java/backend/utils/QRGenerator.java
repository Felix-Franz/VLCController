package backend.utils;

import backend.CONFIG;
import backend.general.Factory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Felix on 26.10.2017.
 */
public class QRGenerator {
    String rawQR;

    public QRGenerator(String rawQR) {
        this.rawQR = rawQR + "/";
    }

    public byte[] create(){
        try {
            return createByteArray(generateQR());
        } catch (IOException | WriterException e) {
            Factory.getLogger().log(Level.WARNING, "Failed to generate QR-Code!");
        }
        return null;
    }

    private BitMatrix generateQR() throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = qrCodeWriter.encode(rawQR, BarcodeFormat.QR_CODE, CONFIG.QR_WIDTH, CONFIG.QR_HEIGHT, hints);
        return bitMatrix;
    }

    private byte[] createByteArray(BitMatrix bitMatrix) throws IOException {
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }
}
