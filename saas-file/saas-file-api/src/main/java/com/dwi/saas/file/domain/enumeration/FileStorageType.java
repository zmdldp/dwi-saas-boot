package com.dwi.saas.file.domain.enumeration;

/**
 * 文件 存储类型 枚举
 *
 * @author dwi
 * @date 2020/05/06
 */
public enum FileStorageType {
    /**
     * 本地
     */
    LOCAL,
    /**
     * FastDFS
     */
    FAST_DFS,
    /**
     * minIO
     */
    MIN_IO,
    ALI,
    QINIU,
    ;

    public boolean eq(FileStorageType type) {
        for (FileStorageType t : FileStorageType.values()) {
            return t.equals(type);
        }
        return false;
    }
}
