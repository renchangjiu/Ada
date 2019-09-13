package com.su.controller.back;

import com.su.pojo.Article;
import com.su.pojo.Result;
import com.su.pojo.UploadImageResult;
import com.su.service.ArticleService;
import com.su.utils.RandomUtil;
import com.su.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController("backEndArticleController")
@RequestMapping("/api/admin")
public class ArticleController {

    @Autowired
    private ArticleService as;

    @Value("${personal.article.image.path}")
    private String imagePath;

    // 分页返回文章列表
    // todo 参数article 用来接收查询条件
    @RequestMapping("/articles/{page}")
    public Result list(@PathVariable Integer page, Article article) throws Exception {

        return Result.success(as.list(page, 10));
    }

    // 新增文章
    @RequestMapping(value = "/article/publish", method = RequestMethod.POST)
    public Result writeArticle(Article article) throws Exception {

        if (StringUtil.isEmpty(article.getTitle()) || StringUtil.isEmpty(article.getSummary()) || StringUtil.isEmpty(article.getContent()) || StringUtil.isEmpty(article.getTags())) {
            return Result.failed(0, "缺少必要字段");
        } else {
            as.insert(article);
            return Result.success(0, "success");
        }
    }


    // 修改文章
    @RequestMapping("/article/update")
    public Result updateArticle(Article article, String isEdit) throws Exception {
        if (StringUtil.isEmpty(article.getTitle()) || StringUtil.isEmpty(article.getSummary()) || StringUtil.isEmpty(article.getContent()) || StringUtil.isEmpty(article.getTags())) {
            return Result.failed(0, "缺少必要字段");
        } else {
            as.update(article);
            return Result.success(0, "success");
        }
    }


    // // 删除文章
    @RequestMapping("/article/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) throws Exception {
        as.delete(id);
        return Result.success(0, "success");
    }


    /**
     * 图片上传
     *
     * @param file image file
     * @return null
     * @throws IOException io
     */
    @RequestMapping(value = "/article/write/image", method = RequestMethod.POST)
    public UploadImageResult fileUpload(MultipartFile file) throws IOException {
        // 原始名称
        String originalFilename = file.getOriginalFilename();
        if (file != null && originalFilename != null && originalFilename.length() > 0) {
            // 1. 校验文件扩展名
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String[] permittedExtensions = new String[]{
                    ".jpg", ".png", ".bmp"
            };
            boolean contain = false;
            for (String permittedExtension : permittedExtensions) {
                if (extension.equalsIgnoreCase(permittedExtension)) {
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                return new UploadImageResult(true, "不支持的文件扩展名, 应为JPG, PNG, BMP", "不支持的文件扩展名, 应为JPG, PNG, BMP");
            }

            // 2. 校验上传文件的大小
            // 3MB
            long maxSize = 1024 * 1024 * 3;
            long size = file.getSize();
            if (size > maxSize) {
                return new UploadImageResult(true, "upload image size must <= 3mb", "upload image size must <= 3mb");
            }

            // 3. 开始上传
            Date now = new Date();
            // 保存图片的第二级路径, 每天新建一个
            String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
            File sec = new File(imagePath + dateStr);
            if (!sec.exists()) {
                sec.mkdir();
            }
            String newFileName = RandomUtil.getRandomChar(12) + extension;
            File newFile = new File(imagePath + dateStr + File.separator + newFileName);

            // 文件写入磁盘
            file.transferTo(newFile);

            // 返回给前端的图片的URL
            String url = "/" + dateStr + "/" + newFileName;
            return new UploadImageResult(false, url, "success");
        } else {
            return new UploadImageResult(true, "null", "failed");
        }

    }


}
