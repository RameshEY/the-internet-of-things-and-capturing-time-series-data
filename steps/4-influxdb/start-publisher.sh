#!/usr/bin/env bash
MAXCOUNT=1000
count=1

array[0]="front"
array[1]="back"
array[2]="side"
size=${#array[@]}

echo
echo "- Start publishing $MAXCOUNT messages"
while [ "$count" -le $MAXCOUNT ]      # Generate 10 ($MAXCOUNT) random integers.
do
  number=$(( ( RANDOM % 40 )  + 15 ))  
  index=$(($RANDOM % $size))
  location=${array[$index]}

  mosquitto_pub -t "myhome/livingroom/temperature" -m "{ \"location\": \"$location\", \"temperature\": $number }"
  let "count += 1"  # Increment count.
done
echo "- Done publishing"