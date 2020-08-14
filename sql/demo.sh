#!/bin/bash
# 전역 변수
str=""
# 공백 제거
download_folder="$(echo -e "$1" | sed -e 's/^[[:space:]]*//')"
# 이미지 다운로드 폴더
echo "image folder :${download_folder}"

if [ "$download_folder" = "" ]; then

	echo "#### input download image folder name ! ###"

else 	
	echo "### image folder inputed ! ####"
	
	# 폴더 점검 및 생성	
	if [ "$(ls -d $download_folder)" == $download_folder ]; then
		echo "$download_folder exists"
	else
		echo "$download_folder is Empty ! Now Make Folder !"
		mkdir $download_folder
	fi
		
    # 이미지 폴더로 이동 => 기존 파일 삭제 => 다운로드	
    cd $download_folder
    rm *.* -f
	
	declare -a Books=(
		"90339705" 
		"90344496" 
		"90617738" 
		"90452827" 
		"89904189" 
		"90004431" 
		"90003539" 
		"90440033" 
		"11781589" 
		"77669043" 
		"78233628" 
		"90118480" 
		"74419916" 
		"67883659" 
		"67883315" 
		"61929871" 
		"84803146" 
		"85019231" 
		"64494679" 
		"64340061" 
		"90391442" 
		"90619474" 
		"90409154" 
		"90365965" 
		"90367403" 
		"90628036" 
		"90176697" 
		"90443286" 
		"90164265" 
		"90081428" 
		"90693052" 
		"90537663" 
		"90384912" 
		"90593436" 
		"90636147" 
		"90275173" 
		"89871484" 
		"90427625" 
		"90534413" 
		"37300128" 
		"90397771" 
		"89309569" 
		"79297023" 
		"80499154" 
		"90084493" 
		"90526327" 
		"90686937" 
		"65282018" 
		"90687516" 
		"90866231" 
		"90594107" 
		"90687185" 
		"90600806" 
		"90602123" 
		"85963416" 
		"42683819" 
		"8949343" 
		"90234637" 
		"59715259" 
		"3736582" 
		"90125491" 
		"82223884" 
		"78524826" 
		"76897656" 
		"78860337" 
		"89016194" 
		"74406341" 
		"79177512" 
		"70962644" 
		"89641193" 
		"59158805" 
		"67505670" 
		"74036140" 
		"68883051" 
		"64367382" 
		"1394142" 
		"78599358" 
		"378870" 
		"372136" 
		"370132"
	)
	
	# 이미지 파일 다운로드
	loop
	for val in ${Books[@]}; do
		echo $val
	
	   # 이미지 파일명 생성 
		
	   # http://image.yes24.com/goods/$str/800x0
	   	   
	   wget --no-check-certificat http://image.yes24.com/goods/$val/800x0
	   
	   cd $download_folder
	   
	   # 이미지 파일명 변경
		mv 800x0 $val.jpg
	   	  
   done   	
fi 