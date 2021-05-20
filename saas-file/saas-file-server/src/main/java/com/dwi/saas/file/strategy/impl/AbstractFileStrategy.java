package com.dwi.saas.file.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.dwi.basic.exception.BizException;
import com.dwi.basic.utils.DateUtils;
import com.dwi.saas.file.domain.domain.FileDeleteDO;
import com.dwi.saas.file.domain.entity.Attachment;
import com.dwi.saas.file.domain.enumeration.IconType;
import com.dwi.saas.file.properties.FileServerProperties;
import com.dwi.saas.file.strategy.FileStrategy;
import com.dwi.saas.file.utils.FileDataTypeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.dwi.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;
import static com.dwi.basic.utils.DateUtils.SLASH_DATE_FORMAT;


/**
 * 文件抽象策略 处理类
 *
 * @author dwi
 * @date 2020/06/17
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractFileStrategy implements FileStrategy {

    private static final String FILE_SPLIT = ".";
    protected final FileServerProperties fileProperties;

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return 附件
     */
    @Override
    public Attachment upload(MultipartFile multipartFile) {
        try {
            if (!StrUtil.contains(multipartFile.getOriginalFilename(), FILE_SPLIT)) {
                throw BizException.wrap(BASE_VALID_PARAM.build("缺少后缀名"));
            }

            Attachment file = Attachment.builder()
                    .submittedFileName(multipartFile.getOriginalFilename())
                    .contextType(multipartFile.getContentType())
                    .dataType(FileDataTypeUtil.getDataType(multipartFile.getContentType()))
                    .size(multipartFile.getSize())
                    .ext(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))
                    .build();
            file.setIcon(IconType.getIcon(file.getExt()).getIcon());
            setDate(file);
            uploadFile(file, multipartFile);
            return file;
        } catch (Exception e) {
            log.error("ex=", e);
            throw BizException.wrap(BASE_VALID_PARAM.build("文件上传失败"), e);
        }
    }

    /**
     * 具体类型执行上传操作
     *
     * @param file          附件
     * @param multipartFile 文件
     * @throws Exception 异常
     */
    protected abstract void uploadFile(Attachment file, MultipartFile multipartFile) throws Exception;

    private void setDate(Attachment file) {
        LocalDateTime now = LocalDateTime.now();
        file.setCreateMonth(DateUtils.formatAsYearMonthEn(now))
                .setCreateWeek(DateUtils.formatAsYearWeekEn(now))
                .setCreateDay(DateUtils.formatAsDateEn(now));
    }

    @Override
    public boolean delete(List<FileDeleteDO> list) {
        if (list.isEmpty()) {
            return true;
        }
        boolean flag = false;
        for (FileDeleteDO file : list) {
            try {
                delete(list, file);
                flag = true;
            } catch (Exception e) {
                log.error("删除文件失败", e);
            }
        }
        return flag;
    }

    /**
     * 具体执行删除方法， 无需处理异常
     *
     * @param list 删除集合
     * @param file 文件
     * @author dwi
     * @date 2020-05-07
     */
    protected abstract void delete(List<FileDeleteDO> list, FileDeleteDO file);
    
    /**
     * 获取年月日 2020/09/01
     *
     * @return
     */
    protected static String getDateFolder() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(SLASH_DATE_FORMAT));
    }

}
