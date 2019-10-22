#!/bin/bash
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin

OIFS="$IFS"
IFS=':'
read -a new_string <<< "$1"
IFS="$OIFS"

project_name=${new_string[0]}
port=${new_string[1]}
version=$2

ori_jar_file_name="$project_name-$version".jar
exec_jar_file_name="$project_name".jar
old_jar_file=""
source_dir=~/docker-service/
target_dir=~/deploy/
project_dir_path=$target_dir$project_name
backup_dir=~/backup_jar/

file_full_path="$source_dir$ori_jar_file_name"
time=$(date +"%Y%m%d_%H%M%S")

echo "Jar file : $ori_jar_file_name"
echo "Source directory : $source_dir"

if [ -d "$project_dir_path" ]
then
       old_jar_file=`find "$project_dir_path" -type f -name "$project_name*"`
       echo "Old jar file name : $old_jar_file"
       echo "Move to $backup_dir"
       /bin/cp -Ra "$old_jar_file" "$backup_dir"$project_name"_"$time".jar"
       echo "Remove $project_dir_path directory files."
       /bin/rm -rf "$project_dir_path"/*
else
       echo "Create $project_dir_path directory."
       /bin/mkdir -p $project_dir_path
fi

if [ -f "$file_full_path" ]
then
	echo "$file_full_path found."
        /bin/cp -Ra "$file_full_path" "$project_dir_path"/"$exec_jar_file_name"
else
	echo "$file_full_path not found."
        exit 1
fi

if docker ps | awk -v app="$project_name" 'NR > 1 && $NF == app{ret=1; exit} END{exit !ret}'; then
  docker stop "$project_name" && docker start "$project_name"
else
  docker run -d --name "$project_name" -p "$port":"$port" -v /etc/localtime:/etc/localtime:ro -v /usr/share/zoneinfo/Asia/Taipei:/etc/timezone:ro -v "$project_dir_path/$exec_jar_file_name":/usr/service.jar java:openjdk-8 java -jar /usr/service.jar && docker start "$project_name"
fi

echo "success"
