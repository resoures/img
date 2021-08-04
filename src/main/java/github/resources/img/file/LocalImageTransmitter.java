package github.resources.img.file;

import cn.hutool.core.io.FileUtil;
import github.resources.img.config.ImageFileProperties;
import github.resources.img.util.ImageFileUtil;
import github.resources.img.web.dao.ImgMapMapper;
import github.resources.img.web.entity.ImageMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 保存图片到本地
 */
@Component
public class LocalImageTransmitter implements ImageTransmitter{

    @Override
    public void writeImage(Image image) throws IOException {
        String path = image.getRelativePath() + File.separator + image.getName() + "." +image.getSuffix();
        File file = ImageFileUtil.createFile(image.getParentPath(), path);
        if (file == null) {
            throw new IOException();
        }
        try (InputStream inputStream = image.getInputStream();
             BufferedOutputStream outputStream = FileUtil.getOutputStream(file)) {
            byte[] buff = new byte[1024];
            for (int size; (size = inputStream.read(buff)) != -1; ) {
                outputStream.write(buff, 0, size);
            }
        } catch (Exception e) {
            throw new IOException(e);
        }
    }




}
