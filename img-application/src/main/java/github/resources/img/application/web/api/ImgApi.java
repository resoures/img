package github.resources.img.application.web.api;

import github.resources.img.application.configuration.DefaultImageServiceConf;
import github.resources.img.core.model.dto.Response;
import github.resources.img.application.service.ImgService;
import github.resources.img.application.utils.BeanConventUtil;
import github.resources.img.application.utils.ImageUtil;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.core.model.bo.ImageBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/img")
public class ImgApi {

    @Autowired
    private ImgService imgService;

    @Autowired
    private DefaultImageServiceConf conf;

    @PostMapping("")
    public Response postImage(@RequestPart MultipartFile file) {
        if (!ImageUtil.supportedImageType(file,conf)){
            return ResponseUtil.fail("unsupported image type");
        }
        ImageBo imageBo = BeanConventUtil.toBo(file);
        return imgService.saveImage(imageBo);
    }




}


