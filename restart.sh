#!/bin/sh
#kill ft-0.0.1-SNAPSHOT.jar pid

PROCESS=`ps -ef|grep  ft-0.0.1-SNAPSHOT |grep -v grep|grep -v PPID|awk '{ print $2}'`
for i in $PROCESS
do
  echo "Kill the $1 process [ $i ]"
  kill -9 $i
done

export SPRING_OUTPUT_ANSI_ENABLED=ALWAYS

echo "start mp-0.0.1-SNAPSHOT.jar"
nohup  /package/jdk1.8.0_201/bin/java  -jar ft-0.0.1-SNAPSHOT.jar  --spring.profiles.active=prod    &
echo ''
echo '#########################################################'
echo '# 	Welcome to ft-0.0.1-SNAPSHOT.jar!                                 #'
echo '# server is started....                                 #'
echo '# Now print logs                                        #'
echo '# You can press Ctrl+c to breake .                      #'
echo '#########################################################'
echo ''
tail -f -n 0 ./nohup.out
