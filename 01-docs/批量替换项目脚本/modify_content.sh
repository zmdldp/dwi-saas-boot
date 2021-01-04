#!/bin/bash
# 该脚本用于修改文本文件中的字符串，如：将 ${OLD_PACKAGE_STR} 替换成 ${NEW_PACKAGE_STR}

# TODO 请修改 NEW_PACKAGE_STR 和  NEW_STR
# 将包名中  com.dwi.basic 改成 com.公司.basic , 不支持增加4级或者5级包！！！
# 将包名中 com.dwi.saas 改成 com.公司.项目 , 不支持增加4级或者5级包！！！
# 会将源码中 package 和 import 关键字后面的 ${OLD_PACKAGE_STR} 替换成 NEW_PACKAGE_STR
#OLD_PACKAGE_STR="com.dwi.basic"
OLD_PACKAGE_STR="com.dwi.saas"
NEW_PACKAGE_STR="改成你想要的项目包名"
# 公司名、 项目名要和 modify_file_name.sh 中的公司名、 项目名保持一致
# 这里会将注释、配置文件、默认的密码等改成 NEW_PARAM_PREFIX
OLD_PARAM_PREFIX="saas-"
NEW_PARAM_PREFIX="改成你想要的前缀"

# TODO 这里修改成你要替换等项目路径
PROJECT_PATH=/Users/dwi/Downloads/saas-cloud-plus
#PROJECT_PATH=/Users/dwi/Downloads/test/saas-boot
#PROJECT_PATH=/Users/dwi/Downloads/test/saas-web
#PROJECT_PATH=/Users/dwi/Downloads/test/saas-util

###############下面的请勿改动#################

# 这段命令用于清理项目中无用等文件
rm -rf $PROJECT_PATH/.git
rm -rf $PROJECT_PATH/third-party
find $PROJECT_PATH/ -name "*.iml"  | xargs rm -rf
find $PROJECT_PATH/ -name "target"  | xargs rm -rf
find $PROJECT_PATH/ -name ".DS_Store"  | xargs rm -rf
find $PROJECT_PATH/ -name ".idea"  | xargs rm -rf


# mac 使用这段命令实现替换字符串   （mac和centos的区别在于 -i 后面有没有 ""）
grep -rl  "${OLD_PACKAGE_STR}" $PROJECT_PATH | xargs sed -i "" "s/${OLD_PACKAGE_STR}/${NEW_PACKAGE_STR}/g"
#grep -rl  "${OLD_PARAM_PREFIX}" $PROJECT_PATH | xargs sed -i "" "s/${OLD_PARAM_PREFIX}/${NEW_PARAM_PREFIX}/g"

# centos 使用下面的命令
#grep -rl  "${OLD_PACKAGE_STR}" $PROJECT_PATH | xargs sed -i "s/${OLD_PACKAGE_STR}/${NEW_PACKAGE_STR}/g"
#grep -rl  "${OLD_PARAM_PREFIX}" $PROJECT_PATH | xargs sed -i "s/${OLD_PARAM_PREFIX}/${NEW_PARAM_PREFIX}/g"




