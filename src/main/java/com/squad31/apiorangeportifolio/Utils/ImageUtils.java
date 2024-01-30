package com.squad31.apiorangeportifolio.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

    public static final int BUFFER_SIZE = 4096;

    public static byte[] compressImage(byte[] data) throws IOException {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tempByte = new byte[BUFFER_SIZE];

        while(!deflater.finished()) {
            int size = deflater.deflate(tempByte);
            outputStream.write(tempByte, 0, size);
        }

        outputStream.close();

        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) throws DataFormatException, IOException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tempByte = new byte[BUFFER_SIZE];

        while (!inflater.finished()) {
            int decompressedBytesCount = inflater.inflate(tempByte);
            outputStream.write(tempByte, 0, decompressedBytesCount);
        }

        outputStream.close();

        return outputStream.toByteArray();
    }

}
