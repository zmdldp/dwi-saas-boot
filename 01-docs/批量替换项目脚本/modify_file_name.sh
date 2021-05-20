#!/bin/bash
# 该脚本用于修改文件夹名， 比如，将com下的dwi文件夹，改成你的公司名， 将com.dwi下的saas 改成你的项目名

# TODO 需要修改 NEW_PROJECT_NAME
# 公司名、 项目名要和 modify_content.sh 中的公司名、 项目名保持一致
#OLD_PROJECT_NAME=dwi
#NEW_PROJECT_NAME=公司名
OLD_PROJECT_NAME=saas
NEW_PROJECT_NAME=项目名

# TODO 需要你修改项目的绝对路径
PROJECT_PATH=/Users/dwi/Downloads/saas-cloud-plus
#PROJECT_PATH=/Users/dwi/Downloads/test/saas-boot
#PROJECT_PATH=/Users/dwi/Downloads/test/saas-web
#PROJECT_PATH=/Users/dwi/Downloads/test/saas-util

###############下面的请勿改动#################

function changeName(){
    new=`echo $1|sed "s/${OLD_PROJECT_NAME}/${NEW_PROJECT_NAME}/g"`
    if [ $1 != $new ];then
        echo changeName old: $1 new: $new
        mv $1 $new
    fi
}

function travFolder(){
    #echo "travFolder start"
    flist=`ls $1`
    cd $1
    for f in $flist
    do
        #echo traverse do $f
        local old=$f
        if test -d $f
        then
            #echo "traverse dir:${f}"
            travFolder $f
            #echo "traverse rename dir:${f}"
            # 新加的rename文件夹名字
            changeName $old
        else
            #echo "traverse file:$f"
            changeName $f
        fi
    done
    cd ../
}



travFolder $PROJECT_PATH
#travFolder $PROJECT_PATH

echo 'modify file name success'
