package github.resources.img.application.service;

import github.resources.img.application.model.dto.Response;
import github.resources.img.manager.bo.ImageBo;

public interface ImgService {

    Response upload(ImageBo imageBo);

    ImageBo readImg(String fileName);

    Response saveImage(ImageBo imageBo);

    ImageBo getImg(String fileName);

}
