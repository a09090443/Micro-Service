#!/bin/bash
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin

OIFS="$IFS"
IFS=':'
read -a new_string <<< "$1"
IFS="$OIFS"

project_name=${new_string[0]}
port=${new_string[1]}
version=$2

jar_file_name="$project_name-$version".jar
old_jar_file=""
source_dir=~/micro-service/
target_dir=~/deploy/
project_dir_path=$target_dir$project_name
backup_dir=~/backup_jar/

file_full_path="$source_dir$jar_file_name"
time=$(date +"%Y%m%d_%H%M%S")

echo "Jar file : $jar_file_name"
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
        /bin/cp -Ra "$file_full_path" "$project_dir_path"
else
	echo "$file_full_path not found."
        exit 1
fi

fuser -k "$port"/tcp;java -jar "$project_dir_path/$jar_file_name" >~/log/"$project_name"  2>&1  &

echo "success"
